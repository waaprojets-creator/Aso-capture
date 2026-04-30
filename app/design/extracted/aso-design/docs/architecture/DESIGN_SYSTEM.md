# Design System ASO Capture v0.1

Système de design intégré au code Android.

## Direction esthétique

**Instrument scientifique** — sobriété absolue, hiérarchie d'information rigoureuse, données techniques mises en avant. Mode sombre prioritaire pour usage chantier OLED.

## Tokens disponibles

### Couleurs (`res/values/colors.xml`)
- Surfaces : `aso_bg_base`, `aso_bg_surface`, `aso_bg_elevated`
- Bordures : `aso_border_subtle`, `aso_border_default`, `aso_border_strong`
- Texte : `aso_text_primary`, `aso_text_secondary`, `aso_text_tertiary`, `aso_text_disabled`
- Accent : `aso_accent` (#4ADE80 vert technique)
- Statuts : `aso_status_ok`, `aso_status_warn`, `aso_status_crit`, `aso_status_info`

### Espacements (`res/values/dimens.xml`)
Système 4dp : `aso_space_1` à `aso_space_16`

### Rayons
`aso_radius_sm` à `aso_radius_xl` + `aso_radius_round`

### Typographie
- Display : `AsoText.Display`
- Title : `AsoText.Title`
- Body : `AsoText.Body`
- Mono : `AsoText.Mono`, `AsoText.MonoLabel`

### Boutons
- `AsoButton.Primary` — fond clair, action principale
- `AsoButton.Secondary` — outlined, action secondaire
- `AsoButton.Critical` — rouge, stop scan / arrêts critiques

## Évolution

Pour modifier l'esthétique globale, n'éditer que `colors.xml`, `dimens.xml`, `themes.xml`. Les layouts pointent vers les tokens.

Prochaines étapes (v0.2+) :
- Intégration Sora + JetBrains Mono via fichiers `.ttf`
- Mode clair adaptatif
- Composants React-like en Kotlin Compose

---

*30 avril 2026*
