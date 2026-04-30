# Schéma du fichier `metadata.jsonl`

Format **JSON Lines** : un objet JSON par ligne, séparés par `\n`.

Chaque ligne porte un champ `type` qui détermine le schéma du reste de l'objet.

---

## Types de samples supportés

### 1. Sample IMU

```json
{
  "type": "imu",
  "ts_ns": 1714478400123456789,
  "accel": [0.123, -9.812, 0.045],
  "gyro": [0.001, -0.002, 0.000],
  "rot": [0.123, 0.456, 0.789, 0.321],
  "gravity": [0.0, -9.81, 0.0],
  "mag": [12.3, -45.6, 78.9]
}
```

- `ts_ns` : timestamp en nanosecondes (`SystemClock.elapsedRealtimeNanos()`)
- `accel` : accéléromètre m/s² [x, y, z]
- `gyro` : gyroscope rad/s [x, y, z]
- `rot` : quaternion [x, y, z, w] (rotation absolue)
- `gravity` : vecteur gravité m/s² [x, y, z]
- `mag` : magnétomètre µT [x, y, z] (peut être absent à 50 Hz)

Tout champ optionnel peut être absent si le capteur n'a pas mis à jour à ce sample.

---

### 2. Sample GNSS

```json
{
  "type": "gnss",
  "ts_ns": 1714478400000000000,
  "lat": 46.20448,
  "lon": 6.14380,
  "alt": 415.3,
  "acc_h_m": 4.2,
  "acc_v_m": 8.1,
  "n_sats": 12,
  "n_sats_l5": 4,
  "raw_b64": "BASE64..."
}
```

- `lat`, `lon`, `alt` : WGS84 degrés et mètres
- `acc_h_m`, `acc_v_m` : précision horizontale et verticale en mètres
- `n_sats` : nombre total de satellites observés
- `n_sats_l5` : nombre de satellites en bande L5 (si supporté)
- `raw_b64` : pseudo-distances brutes encodées en base64 (pour PPK ultérieur), optionnel

---

### 3. Sample baromètre

```json
{
  "type": "baro",
  "ts_ns": 1714478400123456789,
  "p_hpa": 1013.25,
  "acc": 3
}
```

- `p_hpa` : pression atmosphérique en hPa
- `acc` : accuracy reportée par SensorManager (0-3)

---

### 4. Frame vidéo (timestamp uniquement)

```json
{
  "type": "frame",
  "ts_ns": 1714478400123456789,
  "frame_idx": 1234,
  "exposure_ns": 2000000,
  "iso": 100
}
```

Permet de réaligner les frames vidéo avec les capteurs IMU en post-prod.

---

### 5. Marker GCP

```json
{
  "type": "gcp",
  "ts_ns": 1714478400123456789,
  "label": "GCP_001",
  "photo": "gcp/gcp_001.jpg",
  "voice_note": "gcp/gcp_001.mp3",
  "gnss_at_mark": { "lat": 46.20448, "lon": 6.14380, "alt": 415.3 },
  "surveyed_x": null,
  "surveyed_y": null,
  "surveyed_z": null,
  "surveyed_system": null
}
```

Les coordonnées géomètre (`surveyed_*`) sont saisies a posteriori après le passage du géomètre.

---

### 6. Burst critique

```json
{
  "type": "burst",
  "ts_ns_start": 1714478400000000000,
  "ts_ns_end": 1714478402000000000,
  "label": "fenêtre étage 1 - sud",
  "photos": ["photos/critical_001.dng", "photos/critical_002.dng"]
}
```

---

### 7. Calibration scale-lock

```json
{
  "type": "scale_lock",
  "ts_ns": 1714478400000000000,
  "photo": "calibration/scale_lock_001.jpg",
  "marker_distance_m": 2.000,
  "aruco_id_start": 0,
  "aruco_id_end": 1,
  "pixel_distance": null,
  "validated_by_user": false
}
```

`pixel_distance` est rempli en post-prod par OpenCV ou par tap manuel.

---

## Header de session — `session.json`

Fichier séparé contenant les métadonnées globales :

```json
{
  "session_id": "scan_1714478400000",
  "app_version": "0.1.0-skeleton",
  "device": {
    "manufacturer": "Samsung",
    "model": "SM-N986B",
    "android_version": "13",
    "android_sdk": 33
  },
  "started_at": "2026-04-30T12:00:00Z",
  "ended_at": "2026-04-30T12:05:32Z",
  "duration_s": 332,
  "stats": {
    "n_imu_samples": 66400,
    "n_gnss_samples": 332,
    "n_baro_samples": 1660,
    "n_frames": 9960,
    "n_gcps": 5,
    "n_bursts": 8,
    "n_scale_locks": 2
  },
  "video_file": "video.mp4",
  "metadata_file": "metadata.jsonl"
}
```

---

## Compatibilité pipeline back-end

Ce format est conçu pour être lu directement par :
- AliceVision (script Python custom à fournir en `scripts/import_aso.py`)
- Metashape (Python API)
- COLMAP (export en format `images.txt` + `cameras.txt`)

Voir `scripts/` pour les scripts de conversion.

---

*Dernière mise à jour : 30 avril 2026*
