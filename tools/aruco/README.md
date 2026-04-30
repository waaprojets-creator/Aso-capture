# Marqueurs ArUco — Mètre maçon ASO

Marqueurs prêts à imprimer pour le scale-lock automatique.

---

## Procédure de fabrication

### Matériel nécessaire
- Imprimante laser ou jet d'encre (300 dpi minimum)
- Papier blanc 80g ordinaire
- Pochettes de plastification A4 (80 microns, mate de préférence)
- Plastifieuse à chaud (Bureau Vallée, Office Depot ~30 €)
- Cutter ou ciseaux
- Adhésif double face fin

### Étapes
1. Imprimer le fichier `aruco_planche_v0.1.pdf` à **100% (sans mise à l'échelle)**
2. Vérifier que les marqueurs imprimés font bien **40 × 40 mm** (mesure au mètre maçon)
3. Plastifier la planche entière, plastification **mate** pour éviter les reflets
4. Découper précisément autour de chaque marqueur (laisser 5 mm de bordure plastifiée)
5. Coller le marqueur ID 0 à l'extrémité gauche du mètre maçon
6. Coller le marqueur ID 1 à l'extrémité droite, à exactement 2.000 m du marqueur ID 0
7. Vérifier la planéité : les marqueurs ne doivent pas être pliés ni déformés

---

## Spécifications techniques

| Paramètre | Valeur |
|---|---|
| Dictionnaire | DICT_4X4_50 |
| Taille du marqueur | 40 × 40 mm |
| Distance entre centres | 2.000 m |
| ID utilisés | 0 et 1 |
| Bordure blanche obligatoire | 5 mm minimum tout autour |

---

## Cycle de remplacement

Les marqueurs plastifiés ont une durée de vie estimée à :
- **6 mois** en usage chantier intensif (poussière, frottements)
- **2 ans** en usage architecture pure (cabinet + visites occasionnelles)

Refaire si :
- Le marqueur est rayé au point que la trame 4×4 est perturbée
- Le plastique se décolle du papier
- Les bords se courbent ou se voilent

---

## Génération de planches personnalisées

Si tu veux générer tes propres planches avec d'autres tailles ou dictionnaires :

```python
import cv2
import numpy as np

dictionary = cv2.aruco.getPredefinedDictionary(cv2.aruco.DICT_4X4_50)

for marker_id in [0, 1]:
    img = cv2.aruco.generateImageMarker(dictionary, marker_id, 472)  # 472 px = 40 mm @ 300 dpi
    cv2.imwrite(f"aruco_id{marker_id}.png", img)
```

Voir aussi `scripts/generate_aruco_sheet.py` pour générer une planche A4 prête à imprimer.

---

*Note : le PDF prêt-à-imprimer sera généré par `scripts/generate_aruco_sheet.py` au moment du build initial du repo. Voir le script pour les détails.*
