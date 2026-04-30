# Scripts utilitaires ASO Capture

Scripts Python pour le pipeline back-end de post-traitement.

---

## Scripts disponibles

### `aso_to_alicevision.py`
Convertit un scan ASO en bundle prêt pour AliceVision/Meshroom.

**Usage :**
```bash
python scripts/aso_to_alicevision.py /chemin/vers/scan_1714478400000/
```

---

## Scripts à venir (roadmap v0.2-v0.3)

- `aso_to_metashape.py` — conversion vers Agisoft Metashape Python API
- `aso_to_colmap.py` — export COLMAP avec EOPs initiaux IMU
- `apply_imu_constraints.py` — injection des contraintes IMU dans bundle adjustment
- `apply_manhattan_world.py` — détection plans verticaux/horizontaux pour contraintes
- `mfsr_burst.py` — multi-frame super-resolution sur les rafales RAW DNG
- `gaussian_splatting_pipeline.py` — pipeline parallèle 3DGS pour rendu client

---

## Dépendances communes

```bash
pip install -r requirements.txt
```

---

*Dernière mise à jour : 30 avril 2026*
