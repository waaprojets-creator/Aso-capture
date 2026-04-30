# ASO Capture

**Architectural Sovereign Operator — Module de capture terrain**

Application Android souveraine pour le relevé architectural par photogrammétrie smartphone, avec fusion de capteurs inertiels, géoréférencement par points de contrôle, et pipeline back-end ouvert.

> ⚠️ **Statut du projet : v0.1 bêta — Preuve de concept terrain.**
> Cette version n'est **pas un livrable client**. Elle vise à valider empiriquement la chaîne de capture smartphone → station de calcul → modèle BIM exploitable.

---

## 🎯 Vision

ASO Capture est le premier maillon de l'écosystème **A.S.O.** (Architectural Sovereign Operator), conçu pour donner aux architectes une indépendance complète vis-à-vis des solutions cloud propriétaires.

Trois principes directeurs :

1. **Souveraineté des données** — toutes les données restent sur le matériel possédé par l'agence
2. **Interopérabilité** — formats ouverts (IFC, E57, OBJ, JSON) à toutes les étapes
3. **Honnêteté technique** — chaque limite de précision est documentée, aucune promesse irréaliste

---

## 🏗️ Architecture du projet

```
ASO Capture (smartphone Android)
    ↓
Capture vidéo 4K@30 H.265 + photo RAW DNG burst + JSON synchronisé
    ↓
[Transfert vers NAS / station de travail]
    ↓
Pipeline back-end (post-prod) : photogrammétrie + IA + BIM
    ↓
Export IFC pour Revit
```

**Pile technique v0.1 :**
- Langage : Kotlin natif Android
- API : Camera2 + SensorManager + GnssMeasurement
- Min SDK : Android 11 (API 30) — Note 20 Ultra cible
- Target SDK : Android 14 (API 34)

---

## 📐 Niveaux de précision annoncés

| Type de cote | Précision relative | Précision absolue |
|---|---|---|
| Cotes locales (< 5 m) | ≤ 1 cm | — |
| Cotes façade (5-15 m) | ≤ 3 cm | — |
| Cotes longues (> 15 m) | ≤ 5 cm sans GCP | ≤ 2 cm avec GCP |
| Géoréférencement absolu | — | ≤ 2 cm avec GCP géomètre |

**Ces niveaux sont théoriques et doivent être validés empiriquement par les tests v0.1.**
Voir [`LIMITATIONS.md`](LIMITATIONS.md) pour les conditions exactes.

---

## 📚 Documentation du projet

- [`README.md`](README.md) — ce document
- [`ROADMAP.md`](ROADMAP.md) — feuille de route v0.1 → v1.0
- [`LIMITATIONS.md`](LIMITATIONS.md) — limites techniques honnêtes
- [`CONTRIBUTING.md`](CONTRIBUTING.md) — comment contribuer
- [`CHANGELOG.md`](CHANGELOG.md) — journal des versions
- [`LICENSE`](LICENSE) — licence du projet

**Documentation technique approfondie :** voir [`docs/`](docs/)
- [`docs/architecture/`](docs/architecture/) — architecture logicielle détaillée
- [`docs/research/`](docs/research/) — bibliographie scientifique et état de l'art
- [`docs/protocols/`](docs/protocols/) — protocoles méthodologiques

---

## 🚀 Démarrage rapide

### Prérequis
- Android Studio Hedgehog ou plus récent
- JDK 17
- Smartphone Android 11+ (cible : Samsung Galaxy Note 20 Ultra)

### Build
```bash
./gradlew assembleDebug
```

### Installation sur device
```bash
./gradlew installDebug
```

### Tests
```bash
./gradlew test
```

---

## 🔬 Fondements scientifiques

Le projet s'appuie sur 19 références académiques peer-reviewed dans 4 pôles géographiques (Suisse, Chine, Allemagne, Italie). Voir [`docs/research/BIBLIOGRAPHIE_SCIENTIFIQUE.md`](docs/research/BIBLIOGRAPHIE_SCIENTIFIQUE.md).

**Pistes techniques principales :**
- Fusion IMU/SfM par contraintes a priori (Alsubaie & El-Sheimy 2017)
- Manhattan World pour environnements architecturaux (Guo et al. 2022, Li et al. 2020)
- Multi-frame super-resolution sur rafales statiques (Bhat et al. 2021, Politecnico Torino 2024)
- Pipeline parallèle Gaussian Splatting (Kerbl et al. SIGGRAPH 2023, GS4Buildings TUM 2025)
- Estimation profondeur monoculaire IA (Metric3D v2, Depth Anything V2)

---

## 🤝 Contribution

Ce projet est ouvert aux contributions, particulièrement de :
- Développeurs Android Kotlin
- Chercheurs en photogrammétrie et computer vision
- Architectes et bureaux d'études souhaitant tester sur le terrain
- Géomètres-experts pour les protocoles de calage

Voir [`CONTRIBUTING.md`](CONTRIBUTING.md).

---

## 📄 Licence

- Code source : **GPL-3.0**
- Documentation et bibliographie : **CC-BY 4.0**

Voir [`LICENSE`](LICENSE) pour les détails.

---

## 👤 Auteur

**Walid** — Architecte ENSAG, Genève / Rhône-Alpes
Projet personnel de souveraineté numérique pour la pratique architecturale.

---

*Dernière mise à jour : 30 avril 2026*
