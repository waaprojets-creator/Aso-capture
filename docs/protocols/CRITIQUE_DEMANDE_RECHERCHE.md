# Critique de la demande de recherche ASO + corrections proposées

**Contexte :** Ce document analyse rétrospectivement la demande de recherche bibliographique formulée pour le projet ASO Capture, identifie ses faiblesses méthodologiques, et propose une formulation corrigée pour les prochaines missions.

---

## Analyse honnête de la demande initiale

### Ce qui était bon

- **Cadrage géographique explicite** : Suisse + Chine + Allemagne + Italie. C'est une vraie consigne, pas un vœu pieux. La plupart des demandes de recherche n'ont pas de cadrage géographique du tout.
- **Cadrage thématique structuré** : 7 pistes techniques pré-identifiées. Cela évite la dispersion.
- **Format de livrable précis** : bibliographie structurée, 10-20 références. Pas de demande floue type "fais-moi un état de l'art".
- **Question préalable pertinente** : "as-tu pris une bonne partie des études sur Google Scholar ?" — interroger directement les sources est la bonne réflexe scientifique.

### Ce qui était insuffisant — par ordre d'impact

#### ❌ Faiblesse 1 — Pas de critère d'inclusion / exclusion explicite

**Problème :** Aucun critère pour décider si un papier "compte" comme référence valable. Résultat : j'ai mélangé revues peer-reviewed (Sensors MDPI, ISPRS Annals), preprints arXiv non-relus, conférences sérieuses (CVPR, SIGGRAPH) et working papers d'institutions privées (Ohio State dissertations). Ces sources n'ont pas le même poids scientifique.

**Correction :** Toute demande de recherche doit spécifier les critères de qualité. Exemples :
- Type de source acceptée (revues IF>2, conférences A* uniquement, ou preprints arXiv acceptés ?)
- Date de publication (papiers post-2020 uniquement, ou inclure les fondateurs ?)
- Langue (anglais uniquement, ou français/allemand/italien aussi ?)
- Exclure les working papers de PME / blogs même techniques

#### ❌ Faiblesse 2 — Pas de critère de vérifiabilité

**Problème :** Demande implicite de "trouver des références", sans demander explicitement à les vérifier. C'est exactement la condition pour que le LLM produise des **citations hallucinées plausibles** : un nom d'auteur qui sonne juste, un titre qui colle au sujet, mais qui n'existe pas réellement ou avec une affiliation erronée.

**Correction :** La consigne doit explicitement demander :
- DOI ou URL traçable obligatoire pour chaque référence
- Marquage explicite [VÉRIFIÉ] / [PARTIEL] / [À VÉRIFIER] sur chaque entrée
- Refus de citation si la référence ne peut pas être confirmée par recherche directe

#### ❌ Faiblesse 3 — Pas de profondeur d'engagement définie

**Problème :** "Recherche approfondie" est une expression élastique. Pour moi cela peut signifier 5 requêtes web, pour toi 50. Sans cadrage, la mission est mal calibrée.

**Correction :** Spécifier le **budget de recherche** :
- Nombre approximatif de requêtes acceptables (ex : 15-25 requêtes web)
- Profondeur de lecture par référence (abstract uniquement / introduction + conclusion / lecture intégrale)
- Temps de mission ciblé (si tu veux une mission "rapide" vs "profonde")

#### ❌ Faiblesse 4 — Pas de contre-vérification requise

**Problème :** J'ai produit la bibliographie en une seule passe linéaire, sans demande explicite de contre-vérification. Une bonne méthode scientifique demande à exposer les **limites** et les **angles non couverts**.

**Correction :** Demander explicitement à la fin :
- Quels papiers majeurs **manquent** probablement dans la sélection
- Quels biais de la sélection (géographique, temporel, méthodologique)
- Quels désaccords existent dans la littérature qu'il faudrait citer côté contradicteurs
- Quelle revue systématique récente couvrirait le sujet en méta-analyse

#### ❌ Faiblesse 5 — Pas de finalité spécifiée

**Problème :** Tu m'as demandé une bibliographie, mais l'usage final n'était pas explicite : est-ce pour solidifier des choix techniques internes ? Pour préparer une demande Innosuisse ? Pour rédiger une publication ? Pour identifier des partenariats ?

Selon le cas, le **niveau de rigueur exigible** est très différent :
- Choix techniques internes → 80% de fiabilité suffit, on ajuste en cours de route
- Demande de financement → 99% de fiabilité requis, fact-checking systématique
- Publication → 100% de fiabilité, preuves originales
- Partenariats académiques → niveau intermédiaire, mais besoin d'identifier les bonnes équipes

**Correction :** Toute demande doit préciser la finalité d'usage pour calibrer le niveau de rigueur attendu.

#### ❌ Faiblesse 6 — Pas de demande de contradiction

**Problème :** Je n'ai pas été poussé à chercher des publications qui **contredisent** les pistes choisies. Or la science avance par confrontation. Si je ne cherche que des preuves favorables, je produis une bibliographie de confirmation, pas une bibliographie scientifique.

**Correction :** Demander explicitement à inclure :
- Au moins 1-2 papiers critiques ou contradicteurs par piste
- Les principales **limites empiriques** de chaque approche
- Les benchmarks défavorables s'ils existent

---

## Reformulation corrigée de la demande type

Voici comment ta demande aurait dû être formulée pour un résultat scientifiquement plus rigoureux :

> *Mission de recherche bibliographique pour le projet ASO Capture.*
>
> **Finalité :** [À préciser : choix techniques internes / dossier de financement Innosuisse / publication académique / identification de partenariats].
>
> **Objet :** Documenter scientifiquement 7 pistes techniques pré-identifiées (liste fournie).
>
> **Critères d'inclusion :**
> - Articles peer-reviewed dans revues à comité de lecture, OU conférences de rang A/A* (CVPR, ICCV, ECCV, SIGGRAPH, ISPRS, NeurIPS, ICML)
> - Preprints arXiv acceptés uniquement si déjà cités par des publications peer-reviewed
> - Date : 2018-2026 prioritaire, papiers fondateurs antérieurs acceptés si justifiés
> - Langue : anglais ou français
>
> **Critères d'exclusion :**
> - Blogs techniques d'éditeurs commerciaux (sauf comme référence pratique signalée comme telle)
> - Working papers d'institutions privées non-académiques
> - Articles en accès payant sans preprint disponible (ne pouvant être vérifiés)
>
> **Périmètre géographique :** Suisse, Chine, Allemagne, Italie. Mentionner les autres pôles uniquement si une référence externe est incontournable.
>
> **Vérifiabilité :** Chaque référence doit avoir un DOI ou une URL traçable. Marquer explicitement le niveau de confirmation : [VÉRIFIÉ par accès direct] / [PARTIEL — abstract seul] / [À VÉRIFIER — incertitude]. Si une référence ne peut pas être confirmée, ne pas la citer.
>
> **Budget de recherche :** ~20-25 requêtes web ciblées, lecture abstract systématique + introduction sur les références pivots.
>
> **Contre-vérification obligatoire :** À la fin du livrable, fournir :
> 1. Liste des biais probables de la sélection
> 2. Au moins 2 références qui critiquent ou nuancent les pistes choisies
> 3. Liste des sujets qu'il aurait fallu couvrir mais qui sont absents
> 4. Identification des revues systématiques récentes qui dépasseraient cette compilation
>
> **Format de livrable :** Markdown structuré, 15-25 références au total, classées par piste × géographie × date.

---

## Comparaison avant/après — ce qui changerait concrètement

| Aspect | Demande initiale | Demande corrigée |
|---|---|---|
| Nombre de références | 10-20 (vague) | 15-25 (encadré) |
| Critères qualité | Aucun | Peer-reviewed + arXiv conditionnel |
| Vérifiabilité | Implicite | Marquage explicite obligatoire |
| Profondeur | "À fond" | 20-25 requêtes web budgétées |
| Contre-vérification | Aucune | 4 éléments obligatoires en fin |
| Finalité | Implicite | Explicite (calibre la rigueur) |
| Contradicteurs | Non demandés | 2 minimum par piste |

---

## Le point le plus important : ce que la rigueur change

Une mission cadrée comme ci-dessus me forcerait à :
1. **Refuser certaines références** plutôt que les inclure avec [À VÉRIFIER]
2. **Avouer des trous** au lieu de les combler par des citations approximatives
3. **Te livrer une bibliographie plus courte mais plus solide**
4. **Identifier explicitement les angles morts** plutôt que les masquer

Cela s'aligne directement avec tes mots d'ordre : **honnêteté et efficience**. Une bibliographie de 15 références 100% vérifiées vaut mieux qu'une bibliographie de 20 références dont 3 sont incertaines.

---

*Document interne de méthodologie — Agence Walid — 30 avril 2026.*
