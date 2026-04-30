# ROADMAP — ASO Capture

Feuille de route stratégique du projet, du proof-of-concept à la version 1.0.

---

## Phase 1 — v0.1 (Bêta proof-of-concept) — *En cours*

**Objectif unique :** Valider que la chaîne smartphone → NAS → AliceVision/Metashape produit un nuage de points exploitable pour modélisation BIM.

**Hardware cible :** Samsung Galaxy Note 20 Ultra (déjà possédé)

### Modules à implémenter

#### 1. Logger orchestré
- [ ] Capture vidéo 4K@30 H.265 max bitrate
- [ ] Capture photo RAW DNG burst on-demand (zones critiques)
- [ ] Logging JSON synchronisé multi-capteurs
- [ ] Synchronisation timestamp hardware caméra (`SENSOR_TIMESTAMP`)
- [ ] Vitesse d'obturation forcée à 1/500s minimum

#### 2. Capteurs synchronisés
- [ ] IMU 200 Hz (accéléromètre + gyroscope + quaternions)
- [ ] GNSS 1 Hz avec exposition pseudo-distances brutes (PPK-ready)
- [ ] Baromètre 5 Hz
- [ ] Magnétomètre 50 Hz
- [ ] Détection de gravité pour verticale absolue

#### 3. Mode calibration scale-lock
- [ ] Mode dédié de capture macro du mètre maçon 2 m
- [ ] Détection automatique des marqueurs ArUco aux extrémités
- [ ] Validation utilisateur de la calibration

#### 4. Live mesh ARCore (visualisation seulement)
- [ ] Mesh draft AR pendant la capture
- [ ] Code couleur de complétude (vert/jaune/rouge)
- [ ] Indicateur de qualité IMU et GPS

#### 5. Mode marquage GCP
- [ ] Tap dédié pour marquer un GCP
- [ ] Photo + timestamp + note vocale courte
- [ ] Saisie ultérieure des coordonnées géomètre

#### 6. Détection on-device de lignes verticales
- [ ] Intégration OpenCV LSD ou EDLines
- [ ] Validation utilisateur (tap sur une référence verticale)
- [ ] Export contrainte pour solveur back-end

#### 7. Export et transfert
- [ ] Empaquetage du dossier de scan (.mp4 + .json + .dng + .obj draft + GCP)
- [ ] Compression et nommage normalisé
- [ ] Transfert manuel vers NAS (auto-sync v0.2)

### Critères de validation v0.1
- ✅ Capture continue de 5 minutes sans crash
- ✅ Synchronisation timestamps inférieure à 5 ms entre vidéo et IMU
- ✅ Export complet du paquet de scan en moins de 30 secondes
- ✅ Modèle photogrammétrique reconstructible sur AliceVision/Metashape station
- ✅ Précision relative < 3 cm sur un mur de 5 m de long avec mètre maçon

---

## Phase 2 — v0.2 (Premier upgrade hardware)

**Hardware cible additionnel :** Ulefone Armor 28 Ultra Thermal (à acquérir)

### Modules nouveaux
- [ ] Support du capteur thermique radiométrique (pixel = température)
- [ ] Fusion thermique-RGB par alignement de keypoints
- [ ] Auto-sync NAS au retour en zone WiFi
- [ ] Multi-scale-lock (plusieurs poses du mètre par scan)
- [ ] Loop closure detection

---

## Phase 3 — v0.3 (Pipeline back-end consolidé)

**Travaux station de calcul (NAS + station RTX 5090)**

### Modules pipeline
- [ ] Préprocessing MFSR sur rafales RAW DNG (modèle EFIF-Net ou BasicVSR++)
- [ ] Bundle pré-paramétré avec EOPs IMU initiaux pour AliceVision
- [ ] Injection cartes profondeur Metric3D v2 comme contraintes
- [ ] Pipeline parallèle Gaussian Splatting pour rendu client (3DGS originel + GS4Buildings)
- [ ] Export automatique IFC LoD3 pré-paramétré pour Revit

---

## Phase 4 — v0.5 (Ergonomie terrain)

- [ ] Mode tutoriel guidé "double grille" en AR
- [ ] Indicateurs proactifs de qualité (couverture, vitesse, parallaxe)
- [ ] Support télémètre laser Bluetooth (Leica DISTO X4 par défaut)
- [ ] Compatibilité poignée gimbal stabilisateur
- [ ] Profils utilisateur (architecte, géomètre, contrôleur de chantier)

---

## Phase 5 — v1.0 (Production)

- [ ] Tests terrain validés sur 10 chantiers réels
- [ ] Documentation utilisateur complète
- [ ] Compatibilité étendue (Pixel, Sony Xperia)
- [ ] Communication précision validée empiriquement
- [ ] Publication du protocole de capture en revue scientifique

---

## Phase 6 — v2.0 (Horizon +24 mois)

- [ ] Support RTK externe (récepteur GNSS bi-fréquence professionnel)
- [ ] Module CARE — capteurs IoT post-livraison intégrés au modèle IFC
- [ ] Module GENESIS — IA générative locale entraînée sur archives agence
- [ ] Module VISION — XR collaborative pour réunions de coordination

---

## Principes de gouvernance du projet

### Non-objectifs explicites
- ❌ Pas d'iOS avant 2027 (sauf collaboration externe)
- ❌ Pas de cloud propriétaire (tout reste local ou sur NAS de l'utilisateur)
- ❌ Pas de modèle freemium ou de paywall
- ❌ Pas de télémétrie d'usage

### Priorités structurantes
- ✅ La précision documentée prime sur les fonctionnalités
- ✅ La souveraineté prime sur la facilité
- ✅ L'interopérabilité prime sur l'écosystème fermé
- ✅ La transparence prime sur le marketing

---

*Dernière mise à jour : 30 avril 2026*
