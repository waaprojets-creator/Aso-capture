# Intégration OpenCV pour la détection ArUco

OpenCV est nécessaire pour la détection automatique des marqueurs ArUco lors du scale-lock du mètre maçon.

---

## Procédure d'intégration

### Option 1 : OpenCV Android SDK officiel (recommandé v0.1)

1. Télécharger le SDK depuis https://opencv.org/releases/
2. Importer le module `OpenCV` dans Android Studio :
   - File > New > Import Module
   - Sélectionner le dossier `OpenCV-android-sdk/sdk`
3. Ajouter la dépendance dans `app/build.gradle.kts` :

```kotlin
dependencies {
    implementation(project(":opencv"))
    // ... autres dépendances
}
```

4. Initialiser dans `AsoCaptureApplication.onCreate()` :

```kotlin
if (!OpenCVLoader.initDebug()) {
    Log.e("OpenCV", "Initialization failed")
}
```

### Option 2 : QuickBirds OpenCV (build pré-compilé pour Gradle)

Plus rapide à intégrer mais dépendance externe au projet ASO.

```kotlin
implementation("com.quickbirdstudios:opencv:4.10.0")
```

---

## Module ArUco dans OpenCV

OpenCV inclut le module `aruco` dans le contrib package depuis la version 4.7. Vérifier la disponibilité :

```kotlin
import org.opencv.aruco.Aruco
import org.opencv.aruco.Dictionary

val dictionary = Aruco.getPredefinedDictionary(Aruco.DICT_4X4_50)
```

Si le module n'est pas disponible (build minimal), il faut compiler OpenCV avec les contribs activés. Voir https://docs.opencv.org/4.x/d5/dae/tutorial_aruco_detection.html

---

## Workflow de détection

```kotlin
// 1. Conversion YUV → Gray
val gray = Mat()
Imgproc.cvtColor(yuvMat, gray, Imgproc.COLOR_YUV2GRAY_NV21)

// 2. Détection
val corners = ArrayList<Mat>()
val ids = Mat()
Aruco.detectMarkers(gray, dictionary, corners, ids)

// 3. Filtrer pour les IDs 0 et 1
// 4. Calculer le centre de chaque marqueur
// 5. Mesurer la distance pixel entre les deux centres
```

---

## Précision attendue

Avec marqueurs ArUco DICT_4X4_50 de 4×4 cm, plastifiés, en éclairage correct :

- Détection sub-pixel : ~0.1 px sur les coins
- Centre du marqueur : ~0.05 px
- Distance pixel-to-pixel : ~0.1 px d'incertitude
- Sur une image 4K, sur un mètre maçon vu à 1.5 m : ~ 0.05 mm d'incertitude métrique

Soit une calibration scale-lock à **moins de 0.1%** d'erreur, ce qui est négligeable devant les autres sources d'erreur.

---

## Limites connues

- ArUco fonctionne mal en éclairage très faible (utiliser le flash en mode calibration)
- Les marqueurs trop inclinés (> 60°) peuvent ne pas être détectés
- Les reflets (papier glacé) peuvent casser la détection — privilégier la plastification mate

---

*Dernière mise à jour : 30 avril 2026*
