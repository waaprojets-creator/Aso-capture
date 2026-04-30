# Protocole de recherche scientifique avec un assistant IA (LLM)

**Version :** 1.0
**Date :** 30 avril 2026
**Auteur :** Walid (architecte) + Claude (méthodologie)
**Usage :** Modèle réutilisable pour toutes les missions de recherche bibliographique avec un LLM (Claude, ChatGPT, Gemini, ou autre).

---

## Préambule — Pourquoi ce protocole

Les LLMs sont des assistants de recherche puissants mais imparfaits. Sans cadrage rigoureux, ils peuvent :
- **Halluciner des citations** plausibles mais fausses
- **Mélanger niveaux de qualité** sans distinction
- **Produire de la confirmation** plutôt que de la science
- **Cacher leurs limites** sous une apparence de fluidité

Ce protocole transforme la conversation IA en **méthode de recherche reproductible et scientifiquement défendable**. Il s'inspire des standards des revues systématiques (PRISMA, Cochrane) adaptés à l'usage avec un LLM.

**Principe directeur :** *Une bibliographie courte et vérifiée vaut mieux qu'une bibliographie longue et incertaine.*

---

## Étape 1 — Cadrage de la mission (avant toute requête)

Avant de demander une recherche au LLM, formuler explicitement les 7 paramètres suivants. Sans eux, la mission est mal calibrée.

### 1.1 Finalité d'usage

Préciser à quoi servira la bibliographie :

| Finalité | Niveau de rigueur exigé | Tolérance erreur |
|---|---|---|
| Choix techniques internes | 80% | Tolérée si signalée |
| Dossier de financement (Innosuisse, ANR, ERC) | 99% | Inacceptable |
| Publication académique | 100% | Zéro |
| Identification partenariats | 90% | Tolérée sur affiliations secondaires |
| Veille concurrentielle stratégique | 90% | Tolérée si datée |

### 1.2 Périmètre thématique

Lister les sujets précis à couvrir, avec sous-thèmes si pertinents. Éviter les formulations floues type "intelligence artificielle" → préférer "estimation de profondeur monoculaire par deep learning".

### 1.3 Périmètre géographique

Spécifier explicitement :
- Quels pôles académiques sont prioritaires
- Quels pôles sont acceptés en référence externe pertinente
- Quels pôles sont exclus

Sans ce cadrage, le LLM tend à privilégier les sources anglo-saxonnes par biais d'indexation.

### 1.4 Critères d'inclusion

Définir ce qui compte comme référence valable :

**Niveau A — Maximum exigence :**
- Articles peer-reviewed dans revues à facteur d'impact connu
- Conférences de rang A/A* (CVPR, ICCV, ECCV, SIGGRAPH, NeurIPS, ICML, ISPRS Annals, IEEE TPAMI...)
- Brevets cités si pertinents
- Thèses de doctorat soutenues

**Niveau B — Exigence intermédiaire :**
- Tout le niveau A
- Preprints arXiv déjà cités par d'autres publications peer-reviewed
- Working papers d'institutions académiques publiques

**Niveau C — Exigence minimale :**
- Tout le niveau B
- Preprints arXiv récents non encore cités
- Documents techniques d'organismes normatifs (ISO, IEEE Standards...)

À spécifier explicitement quel niveau s'applique à la mission.

### 1.5 Critères d'exclusion

Lister explicitement ce qui ne doit pas être cité :
- Blogs commerciaux (Pix4D, Agisoft, etc.) sauf signalés comme "référence pratique"
- Articles non vérifiables (paywall sans preprint accessible)
- Working papers de PME ou consultants
- Wikipedia comme source primaire

### 1.6 Budget de recherche

Préciser :
- Nombre de requêtes web envisagé (ex : 15-25)
- Profondeur de lecture par référence (abstract / abstract+intro+conclusion / lecture intégrale)
- Estimation temps de mission (orientation rapide vs profonde)

Cela cadre l'ambition. Sans budget, le LLM peut soit faire trop peu, soit trop disperser.

### 1.7 Format de livrable

Spécifier :
- Format final (Markdown, BibTeX, Word, table)
- Volume cible (10, 20, 30 références ?)
- Mode d'organisation (par thème / par pôle / par date / chronologique)
- Présence ou non d'une synthèse stratégique en fin

---

## Étape 2 — Production de la recherche

### 2.1 Vérifiabilité obligatoire

Pour chaque référence retenue, le livrable doit fournir :
- Auteurs avec affiliation principale
- Titre exact
- Type de publication (revue, conférence, preprint, thèse)
- Année
- DOI ou URL traçable
- **Marqueur de niveau de confirmation** :
  - `[VÉRIFIÉ]` : URL/DOI fonctionnel, accès direct au papier ou son abstract
  - `[PARTIEL]` : URL fonctionnel mais informations vérifiées seulement par titre/abstract
  - `[À VÉRIFIER]` : référence trouvée par recherche secondaire, à confirmer

**Règle absolue :** Une référence sans DOI ou URL traçable ne doit pas être citée.

### 2.2 Risque d'hallucination de citations

Les LLMs peuvent fabriquer des citations qui sonnent juste. Pour s'en prémunir :

**Du côté du demandeur :**
- Toujours demander DOI/URL
- Vérifier soi-même 3-5 références au hasard via Google Scholar ou DOI.org
- Si une référence ne se vérifie pas, **rejeter** la bibliographie entière et demander correction

**Du côté du LLM :**
- Refuser de citer toute référence non confirmée par recherche directe
- Marquer explicitement les incertitudes
- Préférer "je n'ai pas trouvé de référence solide sur ce point" plutôt que d'en inventer une

### 2.3 Méthode de recherche structurée

Le LLM doit suivre l'ordre suivant :

1. **Recherche large** sur le thème principal (3-5 requêtes)
2. **Recherche ciblée** par sous-thème (3-5 requêtes par sous-thème)
3. **Recherche par institution** sur les pôles géographiques prioritaires (2-3 requêtes par pôle)
4. **Recherche de revues systématiques** récentes pour méta-vérification (1-2 requêtes)
5. **Recherche contradictoires** : papiers qui critiquent les approches dominantes (2-3 requêtes)

Cette structure évite les biais de confirmation et garantit la couverture.

---

## Étape 3 — Contre-vérification obligatoire

Le livrable final **doit** comprendre une section de contre-vérification, sinon la mission est incomplète.

### 3.1 Auto-critique méthodologique

Le LLM doit lister honnêtement :
- Les biais probables de sa sélection (géographique, temporel, méthodologique, linguistique)
- Les sujets qu'il aurait fallu couvrir mais qui sont absents
- Les références qu'il a hésité à inclure et pourquoi
- Les pistes qu'il n'a pas pu approfondir faute de budget de requêtes

### 3.2 Exigence de contradicteurs

Pour chaque piste documentée, au moins **1 référence critique ou nuançante** doit être présente. Sans cela, la bibliographie est de la confirmation, pas de la science.

### 3.3 Identification des méta-références

Le LLM doit citer la ou les **revues systématiques récentes** (review papers) qui couvrent le domaine en méta-analyse. Cela permet au demandeur de vérifier la complétude de la sélection en allant lire ces revues.

### 3.4 Matrice de fiabilité

Le livrable doit comporter une vue d'ensemble du niveau de confiance par piste :

| Piste | Nb références | % vérifiées | % à vérifier | Confiance globale |
|---|---|---|---|---|
| Piste 1 | 5 | 100% | 0% | ★★★★★ |
| Piste 2 | 4 | 75% | 25% | ★★★★☆ |
| ... | ... | ... | ... | ... |

---

## Étape 4 — Validation par le demandeur

Le demandeur (toi) doit effectuer un **contrôle de cohérence** avant utilisation :

### 4.1 Vérification aléatoire

Choisir 3 à 5 références au hasard et :
- Cliquer sur le DOI/URL pour confirmer existence
- Vérifier que les auteurs et l'année correspondent
- Vérifier que le titre n'a pas été modifié
- Si **une seule** référence ne passe pas : rejeter le livrable et demander correction.

### 4.2 Test de cohérence thématique

Lire 2-3 abstracts et vérifier que le contenu correspond bien à ce que le LLM en dit dans son résumé. Le LLM peut **mal résumer** un papier sans pour autant l'avoir inventé.

### 4.3 Recoupement croisé

Pour les références pivots (les plus citées dans le livrable), faire une recherche Google Scholar manuelle ou via un thésard pour confirmer leur statut.

### 4.4 Décision finale

Trois options :
- **Validation** : la bibliographie est utilisable telle quelle pour sa finalité
- **Validation conditionnelle** : utilisable après vérifications complémentaires sur les références marquées [À VÉRIFIER]
- **Rejet** : nouvelle passe nécessaire, soit parce qu'une référence est inventée, soit parce que la couverture est insuffisante

---

## Étape 5 — Documentation et reproductibilité

### 5.1 Archivage de la conversation

Garder une trace :
- Des requêtes formulées au LLM
- Des réponses obtenues (au moins le livrable final)
- Des vérifications effectuées
- Des références éliminées et pourquoi

Cela permet de reproduire la mission ou de la mettre à jour ultérieurement.

### 5.2 Mise à jour annuelle

Une bibliographie scientifique vieillit vite (surtout en IA et tech). Prévoir :
- Une revue annuelle complète des références
- Une vérification que les preprints arXiv ont été acceptés en revue
- L'ajout des publications nouvelles depuis la dernière compilation

---

## Annexe A — Template de demande standardisée

Voici un template prêt à copier-coller pour toute mission de recherche bibliographique avec un LLM.

```
MISSION DE RECHERCHE BIBLIOGRAPHIQUE

# Finalité d'usage
[ ] Choix techniques internes (rigueur 80%)
[ ] Dossier de financement (rigueur 99%)
[ ] Publication académique (rigueur 100%)
[ ] Identification de partenariats (rigueur 90%)
[ ] Veille concurrentielle (rigueur 90%)
Préciser : __________________________________

# Périmètre thématique
Sujet principal : __________________________________
Sous-thèmes : __________________________________

# Périmètre géographique
Prioritaire : __________________________________
Acceptable : __________________________________
Exclu : __________________________________

# Niveau de qualité requis
[ ] Niveau A (peer-reviewed strict)
[ ] Niveau B (peer-reviewed + preprints cités)
[ ] Niveau C (peer-reviewed + preprints récents)

# Critères d'exclusion
__________________________________

# Budget de recherche
Nombre de requêtes web : ______
Profondeur de lecture : ______
Temps de mission visé : ______

# Format de livrable
Format : ______ (Markdown, BibTeX, Word, etc.)
Volume cible : ______ références
Organisation : ______
Synthèse stratégique : [ ] oui [ ] non

# Vérifiabilité — RÈGLES ABSOLUES
- Toute référence doit avoir DOI ou URL traçable
- Marquage explicite [VÉRIFIÉ] / [PARTIEL] / [À VÉRIFIER]
- Refus de citer toute référence non confirmable

# Contre-vérification — OBLIGATOIRE EN FIN DE LIVRABLE
- Auto-critique des biais
- Au moins 1-2 références contradictoires par piste
- Liste des sujets manquants
- Identification des revues systématiques récentes
- Matrice de fiabilité par piste

# Validation que je m'engage à effectuer
[ ] Vérification aléatoire de 3-5 références
[ ] Test de cohérence thématique sur 2-3 abstracts
[ ] Recoupement croisé sur les références pivots
```

---

## Annexe B — Adaptation aux projets open source

Pour un projet open source (code, méthodologie, ou bibliographie partagée), le protocole s'enrichit de quelques points spécifiques :

### B.1 Format ouvert et interopérable

- Privilégier **BibTeX** au lieu (ou en complément) du Markdown : c'est le standard académique mondial, lu par tous les outils de gestion de références (Zotero, Mendeley, JabRef).
- Si Markdown : utiliser une syntaxe stable, sans extension propriétaire.
- Hébergement sur dépôt Git public (GitHub, GitLab, Codeberg, sourcehut).

### B.2 Licence claire

- Bibliographie elle-même : **CC-BY 4.0** (attribution requise, réutilisation libre)
- Si elle inclut des résumés ou commentaires originaux : préciser que les résumés sont CC-BY mais que les références citées appartiennent à leurs auteurs/éditeurs respectifs.

### B.3 Reproductibilité

- Documenter la **méthode de compilation** (quelles requêtes, quel LLM, quelle date)
- Permettre à un tiers de reproduire la mission avec le même protocole
- Versionner avec Git : chaque mise à jour devient un commit traçable

### B.4 Contributions externes

- Permettre à la communauté de proposer des corrections via Pull Requests
- Mettre en place un fichier `CONTRIBUTING.md` qui rappelle les règles du protocole
- Tenir un changelog des modifications

### B.5 Outils open source recommandés

Pour aller au-delà du LLM seul, voici des outils gratuits et open source utiles :

| Outil | Usage | URL |
|---|---|---|
| **Zotero** | Gestionnaire de références bibliographiques, plugin navigateur, vérification DOI automatique | https://www.zotero.org |
| **Open Knowledge Maps** | Cartographie visuelle de la recherche scientifique | https://openknowledgemaps.org |
| **Connected Papers** | Visualisation des relations entre papiers | https://www.connectedpapers.com |
| **Semantic Scholar** | Moteur de recherche académique avec API ouverte | https://www.semanticscholar.org |
| **CORE** | Plus de 250 millions de papiers en accès libre | https://core.ac.uk |
| **Unpaywall** | Plugin navigateur qui trouve les versions libres des articles payants | https://unpaywall.org |
| **arXiv Sanity** | Tri intelligent des preprints arXiv | http://arxiv-sanity-lite.com |
| **DOI.org** | Vérification de DOI | https://www.doi.org |
| **Inspire-HEP** | Pour la physique, mais le modèle inspire d'autres domaines | https://inspirehep.net |

### B.6 Workflow open source recommandé

```
[1] Demande au LLM avec template standardisé (Annexe A)
      ↓
[2] Livrable bibliographique brut (Markdown)
      ↓
[3] Import dans Zotero pour vérification automatique des DOI
      ↓
[4] Vérification croisée avec Semantic Scholar (citations descendantes)
      ↓
[5] Visualisation Connected Papers pour repérer les trous
      ↓
[6] Itération avec le LLM si trous identifiés
      ↓
[7] Export BibTeX final, commit Git, publication
```

Ce workflow combine la vitesse du LLM, la rigueur des outils académiques, et la reproductibilité de Git. C'est exactement la philosophie d'ASO appliquée à la recherche scientifique : souveraineté, ouverture, traçabilité.

---

## Annexe C — Mots-clés efficaces pour la recherche LLM

Quelques mots-clés qui améliorent significativement la qualité des recherches LLM, par expérience :

### Ajouter pour qualité scientifique
- "peer-reviewed"
- "systematic review"
- "meta-analysis"
- "benchmark"
- "ablation study"
- "state of the art"
- "open access"
- "reproducible"

### Ajouter pour fraîcheur
- "2024"
- "recent advances"
- "latest"
- (et la date courante explicite)

### Ajouter pour profondeur géographique
- Nom du pays + nom de l'institution + nom du laboratoire
- Ex : "Switzerland EPFL CVLab" plutôt que "European photogrammetry"

### Éviter (génère trop de résultats commerciaux/blogs)
- "best"
- "guide"
- "tutorial"
- "tips"
- "how to"

---

## Conclusion — Esprit du protocole

Ce protocole peut sembler lourd. Il l'est volontairement. Une recherche scientifique avec un LLM est une **collaboration entre une intuition rapide et une vérification rigoureuse**. Sans le second pilier, le LLM produit de la prose plausible — ce qui est dangereux quand on prend des décisions techniques ou financières dessus.

L'efficience vient de la **bonne discipline en amont**, pas de la précipitation en aval.

> *"Honnêteté et efficience"* — la rigueur de cadrage est la condition de l'efficience.

---

*Protocole méthodologique réutilisable — Agence d'architecture Walid — Version 1.0 — 30 avril 2026.*
*Licence : CC-BY 4.0 — Réutilisation libre avec attribution.*
