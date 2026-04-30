# QUICKSTART — Démarrer ASO Capture v0.1

Guide pour passer du dépôt GitHub à une APK installée sur ton Note 20 Ultra.

---

## 1. Cloner le repo

```bash
git clone https://github.com/<ton-user>/aso-capture.git
cd aso-capture
```

## 2. Ouvrir dans Android Studio

1. Lancer **Android Studio Hedgehog** (ou plus récent)
2. **File > Open** → sélectionner le dossier `aso-capture/`
3. Attendre la première synchronisation Gradle (peut prendre 5-10 min la première fois)
4. Si Android Studio propose de mettre à jour le SDK ou les versions, **accepter**

## 3. Générer le wrapper Gradle (première fois seulement)

Dans le terminal Android Studio :
```bash
gradle wrapper --gradle-version 8.7
```

Si tu n'as pas Gradle installé localement, Android Studio le fera automatiquement à la première synchronisation.

## 4. Vérifier le SDK Android

**Tools > SDK Manager** :
- ✅ Android 14 (API 34) installé
- ✅ Android 13 (API 33) installé (pour Note 20 Ultra)
- ✅ Android SDK Build-Tools 34.0.0+

## 5. Connecter le Note 20 Ultra

1. Activer le **mode développeur** sur le téléphone : **Paramètres > À propos du téléphone > Tapoter 7 fois sur "Numéro de version"**
2. Activer le **débogage USB** : **Paramètres > Options de développement > Débogage USB**
3. Connecter le téléphone par USB
4. Accepter la demande d'autorisation sur le téléphone
5. Vérifier dans Android Studio : le device doit apparaître dans la barre du haut

## 6. Premier build et install

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Ou via Android Studio : **Run > Run 'app'** (raccourci `Shift+F10`).

## 7. Tester ARCore

Avant de lancer le scan, vérifier qu'**ARCore est installé** sur le téléphone :
- **Play Store > Rechercher "ARCore"** → installer "Services Google Play pour la réalité augmentée"

Si ARCore n'est pas disponible sur ton Note 20 Ultra, le live mesh sera désactivé mais le reste de la capture fonctionnera.

## 8. Vérifier le GPS L5 (recommandé)

1. Installer **GPSTest** depuis Play Store (par BarbeauDev, gratuit)
2. Ouvrir l'app, aller à l'onglet "Status"
3. Sortir dehors avec vue dégagée du ciel
4. Vérifier la colonne "CF" (carrier frequency) : si tu vois des `L5` ou `1176.45 MHz`, ton GPS est bi-fréquence

Ce test conditionne la précision de géoréférencement attendue.

## 9. Imprimer la planche ArUco

```bash
cd tools/aruco/
python generate_aruco_sheet.py
```

Imprimer `aruco_planche_v0.1.pdf` à 100% (sans mise à l'échelle), plastifier en mat, coller sur le mètre maçon.

## 10. Premier scan test

Lancer l'app, démarrer un scan, faire un test simple :
- Une pièce de ton bureau
- 5-10 secondes de capture
- Vérifier que le dossier `scans/scan_xxx/` est créé sur le téléphone

---

## Que faire ensuite ?

- Lire `ROADMAP.md` pour voir les modules à implémenter par priorité
- Lire `docs/architecture/ARCHITECTURE.md` pour la vue d'ensemble technique
- Lire `docs/architecture/METADATA_SCHEMA.md` pour comprendre le format de sortie
- Ouvrir une issue GitHub pour discuter d'un module avant de le coder

---

## Problèmes courants

### "ARCore not available"
Le Note 20 Ultra **doit** supporter ARCore. Vérifier https://developers.google.com/ar/devices.
Si non listé, le live mesh n'est pas disponible mais la capture fonctionnera sans.

### "HIGH_SAMPLING_RATE_SENSORS denied"
Cette permission est runtime sur Android 12+. Elle doit être demandée explicitement avant de configurer le SensorManager à 200 Hz.

### Build Gradle échoue
- Vérifier la version de JDK : doit être JDK 17 (pas 11, pas 21)
- Vérifier la version Android Studio : Hedgehog (2023.1) ou plus récent
- Nettoyer le cache : `./gradlew clean`

---

*Dernière mise à jour : 30 avril 2026*
