# LIMITATIONS — ASO Capture v0.1

Document de transparence sur les limites techniques connues de la version actuelle.
Mis à jour à chaque évolution. La transparence des limites est un principe directeur du projet.

---

## 1. Limites de précision

### Ce que l'application peut atteindre (théorique)

| Type de cote | Précision relative | Conditions |
|---|---|---|
| Cotes locales (< 5 m, même champ de vue) | ≤ 1 cm | Mètre maçon dans le champ, scale lock validé |
| Cotes façade (5-15 m, même surface continue) | ≤ 3 cm | Loop closure, multi-scale-lock |
| Cotes longues (> 15 m, multi-façades) | ≤ 5 cm | Sans GCP — précision dégradée par dérive SfM |
| Cotes longues avec GCP | ≤ 2 cm | 3 cibles damiers minimum, relevées au tachéomètre |
| Géoréférencement absolu (MN95/RGF93) | ≤ 2 cm | Avec GCP géomètre uniquement |
| Géoréférencement absolu sans GCP | ~ 30 cm à 1 m | GPS smartphone seul, non-RTK |

### Ce que l'application **ne peut pas** atteindre

- ❌ Précision millimétrique (réservée au scanner laser professionnel type BLK360)
- ❌ Géoréférencement absolu MN95 sans GCP géomètre
- ❌ Précision constante sur bâtiment entier sans scale-lock multiple
- ❌ Modèle BIM directement utilisable sans post-traitement station

### Ces limites sont théoriques

**À valider empiriquement par les tests v0.1.** Aucune communication client ne doit utiliser ces chiffres avant validation terrain.

---

## 2. Limites hardware v0.1

### Sur Samsung Galaxy Note 20 Ultra (cible v0.1)

- ⚠️ **Pas de capteur ToF** : retiré entre Galaxy S20 Ultra et Note 20 Ultra. Le live-mesh AR est basé sur ARCore Depth API (estimation par ML), précision 5-10 cm typique.
- ⚠️ **GPS L5 non systématique** : le hardware Note 20 Ultra supporte L1+L5 mais le firmware Samsung peut ne pas l'exposer dans toutes les régions. Test obligatoire avec l'app GPSTest avant utilisation.
- ⚠️ **Pas de durci IP69K** : le Note 20 Ultra est IP68 mais pas certifié chantier. Une coque renforcée est obligatoire pour usage terrain.
- ⚠️ **8K vidéo non recommandé** : le 8K@24 du Note 20 Ultra utilise une compression H.265 trop agressive (80 Mbps pour 33 millions de pixels par image). Le 4K@30 (38 Mbps) est qualitativement meilleur pour la photogrammétrie.
- ⚠️ **Pas de capteur thermique** : reporté à v0.2 sur Ulefone Armor 28 Ultra Thermal.

### Sur Ulefone Armor 28 Ultra Thermal (cible v0.2)

- ⚠️ Capteur 1 pouce Sony IMX989 confirmé sur fiche constructeur — à vérifier en pratique
- ⚠️ Capteur thermique 640×512 NETD <40 mK : précision ±2°C, **non métrologique au sens labo**
- ⚠️ Pas de ToF non plus
- ⚠️ Live-mesh ARCore identique au Note 20 Ultra

---

## 3. Limites logicielles v0.1

### Ce qui n'est pas dans la v0.1

- ❌ Pas de Bluetooth (télémètre, sondes, etc.) — reporté à v0.4
- ❌ Pas de support RTK — reporté à v2.0
- ❌ Pas d'auto-sync NAS — reporté à v0.2
- ❌ Pas de cloud, et c'est volontaire — principe de souveraineté
- ❌ Pas de version iOS — possiblement v0.5+ si collaboration externe
- ❌ Pas de tutoriel guidé en AR — reporté à v0.5
- ❌ Pas de fusion thermique — reporté à v0.2

### Ce qui est limité dans la v0.1

- ⚠️ Le mesh OBJ généré on-device est un **outil de navigation**, pas un livrable géométrique
- ⚠️ Les marqueurs ArUco doivent être collés et calibrés correctement avant usage
- ⚠️ Le pipeline back-end (post-prod) n'est pas inclus dans cette v0.1 — il existe sous forme de scripts séparés

---

## 4. Limites scientifiques connues

### Approche fusion IMU/SfM offline

L'approche choisie (capture brute terrain + post-prod station avec contraintes IMU) est **peu documentée dans la littérature 2022-2026**, qui se concentre sur :
- Le SLAM temps réel (robotique)
- Le VIO temps réel pour AR
- Les approches end-to-end neuronales

**Conséquence :** notre approche est plus une **niche métier** qu'un sujet académique chaud. Les performances réelles devront être validées empiriquement, sans avoir de benchmark littéraire direct pour comparaison.

### Approche Manhattan World

L'hypothèse Manhattan World est **bien documentée** mais peut être trop restrictive pour :
- Les bâtiments à géométrie courbe (rond, ellipsoïdal)
- Les façades à oblique forte (pignons inclinés)
- Les architectures non-orthogonales (vernaculaires, organiques)

**Conséquence :** une option de désactivation des contraintes Manhattan doit être prévue. Bibliographie complémentaire : voir "Atlanta World" (StructVIO 2018) pour scènes plus générales.

### Approche multi-frame super-resolution

Le MFSR ne fonctionne **que** sur des rafales statiques (mouvement < 1 pixel / frame). Inutilisable sur la vidéo continue en mouvement. La v0.1 le réserve donc aux zones critiques pendant les pauses utilisateur.

---

## 5. Limites éthiques et juridiques

- ⚠️ La capture vidéo en lieu public peut être soumise à autorisation (RGPD, droits d'auteur architectural)
- ⚠️ Le scan d'un bâtiment privé sans autorisation est interdit
- ⚠️ Les données de géoréférencement précis peuvent être considérées comme données sensibles selon les contextes
- ⚠️ Les capteurs de profondeur ARCore peuvent capturer involontairement des personnes — anonymisation à prévoir

---

## 6. Limites de la documentation scientifique fournie

Voir [`docs/research/RAPPORT_VERIFICATION_BIBLIOGRAPHIE.md`](docs/research/RAPPORT_VERIFICATION_BIBLIOGRAPHIE.md) pour le détail :

- 12 références sur 19 totalement vérifiées (auteurs + DOI + affiliations)
- 4 références partiellement vérifiées (titre/DOI corrects, attribution auteurs approximative)
- 3 références non revérifiées exhaustivement
- 0 référence inventée détectée

---

## 7. Limites de précision communicationnelle

**Aucune communication client, commerciale ou marketing ne doit promettre "1 cm de précision" sans contexte.**

Formulation pro recommandée :

> *"ASO Capture vise une précision relative inférieure à 1 cm sur les cotes locales (éléments < 5 m visibles dans un même champ de vue), inférieure à 3 cm sur les cotes de façade (5-15 m), et un calage absolu de 1-2 cm avec points de contrôle relevés par géomètre."*

---

*Dernière mise à jour : 30 avril 2026*
*Document évolutif — toute évolution majeure doit être tracée dans le CHANGELOG.*
