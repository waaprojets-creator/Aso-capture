# Contribuer à ASO Capture

Merci de l'intérêt porté au projet ! ASO Capture est un projet open source ouvert aux contributions externes.

---

## Profils de contributeurs recherchés

- **Développeurs Android Kotlin** — implémentation du Logger, intégration capteurs
- **Chercheurs en computer vision** — fusion IMU/SfM, contraintes géométriques
- **Architectes et bureaux d'études** — tests terrain, retours d'usage
- **Géomètres-experts** — validation des protocoles de calage GCP
- **Documentaristes scientifiques** — enrichissement de la bibliographie

---

## Avant de contribuer

1. Lire intégralement le [`README.md`](README.md), [`ROADMAP.md`](ROADMAP.md) et [`LIMITATIONS.md`](LIMITATIONS.md)
2. Vérifier qu'il n'existe pas déjà une issue ou pull request sur le sujet
3. Ouvrir une **discussion** avant un développement majeur, pour s'aligner sur la vision

---

## Types de contributions acceptées

### ✅ Contributions fortement bienvenues

- Corrections de bugs avérés
- Tests automatisés (unitaires, instrumentés)
- Documentation technique additionnelle
- Validation empirique sur d'autres smartphones Android 11+
- Vérifications scientifiques de la bibliographie
- Traductions de la documentation
- Optimisations de performance avec benchmarks

### 🟡 Contributions discutables (ouvrir une issue d'abord)

- Nouveaux modules fonctionnels — vérifier la cohérence avec la roadmap
- Refactorisations majeures — peuvent casser la trajectoire
- Ajout de dépendances externes — chaque dépendance ajoute de la fragilité

### ❌ Contributions non acceptées

- Intégration de services cloud propriétaires
- Télémétrie d'usage envoyée à des tiers
- Frameworks publicitaires
- Code obfusqué ou non auditable
- Fonctionnalités payantes ou freemium

---

## Workflow de contribution

### 1. Fork du repo

```bash
gh repo fork <user>/aso-capture --clone=true
cd aso-capture
```

### 2. Créer une branche

```bash
git checkout -b feature/ma-contribution
# ou pour un fix
git checkout -b fix/description-du-bug
# ou pour de la doc
git checkout -b docs/sujet
```

### 3. Développer

- Respecter les conventions de code Kotlin officielles
- Ajouter des tests pour tout nouveau code
- Documenter les fonctions publiques (KDoc)
- Garder les commits atomiques et explicites

### 4. Vérifier localement

```bash
./gradlew lint
./gradlew test
./gradlew assembleDebug
```

### 5. Pull Request

- Titre clair et descriptif
- Description : quoi, pourquoi, comment tester
- Lier les issues concernées
- Cocher la checklist du template PR

---

## Conventions de code

### Kotlin
- Suivre le [Kotlin Coding Conventions officiel](https://kotlinlang.org/docs/coding-conventions.html)
- Utiliser `ktlint` pour le formatting (intégré dans Gradle)
- Préférer l'immutabilité (`val` plutôt que `var`)
- Pas d'opérateur `!!` sauf justification documentée

### Architecture
- Séparation claire entre couches : UI / Logger / Sensors / Storage / Utils
- Inversion de dépendance : interfaces dans le module appelant, implémentations dans les modules concrets
- Coroutines pour tout async, jamais de threads explicites
- Pas de Singleton — utiliser l'injection de dépendances (Hilt prévu en v0.3)

### Commits
Format : `<type>(<scope>): <description>`

Types : `feat`, `fix`, `docs`, `refactor`, `test`, `chore`, `perf`

Exemples :
- `feat(logger): ajout de la synchronisation timestamp Camera2`
- `fix(sensors): correction dérive baromètre lors du démarrage`
- `docs(research): ajout de 3 références suisses sur SfM`

---

## Contributions à la bibliographie scientifique

La bibliographie ([`docs/research/BIBLIOGRAPHIE_SCIENTIFIQUE.md`](docs/research/BIBLIOGRAPHIE_SCIENTIFIQUE.md)) est ouverte aux corrections et enrichissements.

### Critères d'inclusion d'une nouvelle référence

- ✅ Article peer-reviewed en revue à comité de lecture, OU conférence de rang A/A*
- ✅ DOI ou URL traçable obligatoire
- ✅ Pertinence directe pour une des 7 pistes techniques d'ASO
- ✅ Date 2018-2026 prioritaire (papiers fondateurs antérieurs acceptés si justifiés)

### Format de citation requis

```
**[N] Auteurs (ANNÉE).** *Titre exact du papier.*
Affiliation principale.
DOI ou Référence revue/conférence.
URL : [URL stable, DOI.org, arXiv, ou page institutionnelle]
**Pertinence ASO :** [2-3 lignes maximum sur l'apport pour le projet]
**Niveau de vérification :** [VÉRIFIÉ] / [PARTIEL] / [À VÉRIFIER]
```

### Procédure pour ajouter une référence

1. Vérifier que la référence n'est pas déjà présente
2. Ouvrir une PR avec le marquage `[VÉRIFIÉ]` confirmé par accès direct
3. Mettre à jour la matrice de fiabilité dans le rapport de vérification

---

## Code de conduite

Ce projet adhère au [Contributor Covenant 2.1](https://www.contributor-covenant.org/version/2/1/code_of_conduct/).

En résumé :
- Respect mutuel, pas de discrimination ou harcèlement
- Critique technique constructive, jamais personnelle
- Reconnaissance des contributions de chacun
- Préservation d'un environnement de travail sain

---

## Questions ?

- Issues techniques : ouvrir une issue GitHub avec le bon template
- Discussions stratégiques : utiliser GitHub Discussions
- Contact direct mainteneur : [coordonnées à compléter par Walid]

---

*Document évolutif — 30 avril 2026*
