# Architecture logicielle — ASO Capture v0.1

Vue d'ensemble de l'architecture de l'application Android.

---

## 1. Découpage en modules

```
ch.aso.capture/
├── ui/             — Activités et fragments, View binding
│   ├── MainActivity
│   └── CaptureActivity
├── logger/         — Orchestration de la capture
│   ├── CaptureService    — Foreground service
│   ├── CaptureSession    — Session de capture unique
│   ├── VideoRecorder     — Camera2 / MediaCodec H.265
│   └── SampleModels      — Data classes des samples
├── sensors/        — Capteurs synchronisés
│   ├── SensorLogger      — IMU 200 Hz (accéléro + gyro + rotation + gravité)
│   ├── GnssLogger        — GPS L1+L5 + pseudo-distances brutes (PPK-ready)
│   └── BarometerLogger   — Pression 5 Hz
├── storage/        — Persistance des données
│   └── MetadataWriter    — Écriture JSONL synchronisée
└── utils/          — Outils transverses
    └── ArucoDetector     — Détection marqueurs ArUco pour scale-lock
```

---

## 2. Flux de données

```
                ┌─────────────────────────────────────┐
                │     CaptureActivity (UI)            │
                └────────────────┬────────────────────┘
                                 │ start
                                 ▼
                ┌─────────────────────────────────────┐
                │     CaptureService (foreground)     │
                └────────────────┬────────────────────┘
                                 │ instancie
                                 ▼
                ┌─────────────────────────────────────┐
                │     CaptureSession (orchestrateur)  │
                └─┬───────────┬──────────┬──────────┬─┘
                  │           │          │          │
                  ▼           ▼          ▼          ▼
         ┌────────────┐ ┌──────────┐ ┌────────┐ ┌──────────┐
         │VideoRecorder│ │ Sensor  │ │  Gnss  │ │Barometer │
         │  (Camera2) │ │ Logger  │ │ Logger │ │  Logger  │
         └──────┬─────┘ └────┬────┘ └───┬────┘ └────┬─────┘
                │            │           │            │
                │            └───────────┴────────────┘
                │                       │
                ▼                       ▼
         ┌────────────┐         ┌──────────────┐
         │ video.mp4  │         │MetadataWriter│
         └────────────┘         │ (JSONL)      │
                                └──────┬───────┘
                                       │
                                       ▼
                                ┌────────────────┐
                                │ metadata.jsonl │
                                └────────────────┘
```

---

## 3. Synchronisation temporelle

**Principe directeur :** une seule horloge de référence — `SystemClock.elapsedRealtimeNanos()`.

Cette horloge correspond au `SENSOR_TIMESTAMP` exposé par Camera2 sur Android moderne, ce qui garantit l'alignement direct entre les frames vidéo et les samples IMU/GNSS/baromètre.

**Vérification empirique requise** : test de latence entre la frame caméra et le SensorEvent IMU le plus proche en temps. Cible : <5 ms d'écart.

---

## 4. Stockage par session

Chaque scan génère un dossier horodaté :

```
/storage/emulated/0/Android/data/ch.aso.capture/files/scans/
└── scan_1714478400000/
    ├── session.json          ─ métadonnées globales (device, version, durée)
    ├── video.mp4             ─ vidéo 4K@30 H.265
    ├── metadata.jsonl        ─ samples synchronisés (1 ligne JSON par sample)
    ├── photos/               ─ rafales RAW DNG des zones critiques
    │   ├── critical_001.dng
    │   └── critical_002.dng
    ├── calibration/          ─ photos macro du mètre maçon
    │   └── scale_lock_001.jpg
    ├── gcp/                  ─ points de contrôle marqués
    │   └── gcp_001.json
    └── mesh/                 ─ mesh ARCore draft (optionnel)
        └── live_mesh.obj
```

---

## 5. Threading

- **Thread UI** : Activités, View binding, gestion des taps utilisateur
- **Thread Camera2** : Handler dédié pour les callbacks Camera2
- **Thread Sensor** : Le SensorManager poste les SensorEvent sur le main par défaut. Pour 200 Hz, **utiliser un HandlerThread dédié** (à implémenter).
- **Thread I/O** : Coroutines `Dispatchers.IO` pour toute écriture disque.
- **Thread ARCore** : Géré par la session ARCore, non bloquant.

---

## 6. Permissions critiques

Voir `AndroidManifest.xml` pour la liste complète. Les permissions **runtime** suivantes doivent être demandées au lancement :

- `CAMERA` (capture vidéo)
- `RECORD_AUDIO` (détection clap pour sync)
- `ACCESS_FINE_LOCATION` (GNSS)
- `HIGH_SAMPLING_RATE_SENSORS` (IMU 200 Hz, Android 12+)

---

## 7. Stratégie de test

### Tests unitaires (JVM)
- Sérialisation des `SensorSample`, `GnssSample`, etc.
- Logique de `MetadataWriter` (écriture/lecture JSONL)
- Calibration ArUco (avec images de test fixtures)

### Tests instrumentés (Android device)
- Démarrage et arrêt d'une session complète
- Synchronisation temporelle vidéo/IMU
- Persistance disque et lecture des fichiers générés
- Permissions runtime

---

## 8. Documents associés

- [`METADATA_SCHEMA.md`](METADATA_SCHEMA.md) — schéma exact du fichier metadata.jsonl
- [`OPENCV_INTEGRATION.md`](OPENCV_INTEGRATION.md) — comment intégrer OpenCV pour ArUco

---

*Dernière mise à jour : 30 avril 2026*
