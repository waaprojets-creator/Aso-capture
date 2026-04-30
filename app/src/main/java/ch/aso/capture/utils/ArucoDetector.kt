package ch.aso.capture.utils

/**
 * Détecteur de marqueurs ArUco pour le scale-lock automatique.
 *
 * Utilisé pendant le mode "calibration mètre maçon" :
 * - Deux marqueurs ArUco DICT_4X4_50, IDs 0 et 1, collés aux extrémités du mètre 2 m.
 * - Détection sub-pixel via OpenCV `aruco::detectMarkers`.
 * - La distance pixel mesurée entre les centres des deux marqueurs, combinée avec
 *   la distance physique connue (2,000 m), permet de verrouiller l'échelle métrique.
 *
 * Cette technique évite le tap manuel imprécis (~5-10 px) et atteint une précision
 * sub-pixel (<1 mm sur scène typique).
 *
 * v0.1 : intégration OpenCV à finaliser, voir docs/architecture/OPENCV_INTEGRATION.md
 */
class ArucoDetector {

    /**
     * Détecte les deux marqueurs sur une image et retourne leurs centres en pixels.
     *
     * @param imageData Image RGB en YUV_420_888 ou format compatible OpenCV
     * @return Pair de Points (x, y) des centres des deux marqueurs, ou null si détection échouée.
     */
    fun detectScaleLockMarkers(imageData: ByteArray, width: Int, height: Int): Pair<FloatArray, FloatArray>? {
        // TODO v0.1 : intégration OpenCV
        // 1. Convertir YUV vers RGB ou Gray
        // 2. Mat image = ...
        // 3. aruco::Dictionary dict = aruco::getPredefinedDictionary(DICT_4X4_50)
        // 4. aruco::detectMarkers(image, dict, corners, ids)
        // 5. Filtrer pour IDs 0 et 1, retourner les centres
        return null
    }

    companion object {
        const val MARKER_DICT = "DICT_4X4_50"
        const val MARKER_SIZE_M = 0.04f         // 4 cm
        const val SCALE_LOCK_DISTANCE_M = 2.000f  // mètre maçon standard
        const val MARKER_ID_START = 0
        const val MARKER_ID_END = 1
    }
}
