package ch.aso.capture.logger

import android.content.Context
import java.io.File

/**
 * Enregistreur vidéo principal du scan.
 *
 * Configuration cible v0.1 (Note 20 Ultra) :
 * - Résolution : 3840×2160 (4K UHD)
 * - Framerate : 30 fps
 * - Codec : H.265 (HEVC)
 * - Bitrate : maximum disponible (~70 Mbps avec stabilisation OIS)
 * - Vitesse d'obturation forcée : 1/500s minimum (Camera2 manual exposure)
 * - Format pixel : YUV_420_888
 *
 * Le timestamp de chaque frame est exposé via `TotalCaptureResult.SENSOR_TIMESTAMP`,
 * aligné sur l'horloge système nanoseconde. Cette horloge est la **référence absolue**
 * pour la synchronisation avec les autres capteurs.
 *
 * v0.1 : structure en place, implémentation Camera2 à finaliser.
 */
class VideoRecorder(
    private val context: Context,
    private val sessionDir: File
) {

    private val outputFile: File = File(sessionDir, "video.mp4")
    private var isRecording: Boolean = false

    /**
     * Démarre l'enregistrement vidéo.
     *
     * Étapes :
     * 1. Ouvrir la caméra principale via CameraManager
     * 2. Créer une CaptureSession avec MediaCodec H.265
     * 3. Configurer manuellement ISO, vitesse d'obturation, focus
     * 4. Démarrer la session et attacher un callback pour timestamps
     */
    fun start() {
        // TODO v0.1 : implémentation Camera2
        isRecording = true
    }

    /**
     * Arrête l'enregistrement et finalise le fichier MP4.
     */
    fun stop() {
        // TODO v0.1 : fermeture propre de la session Camera2
        isRecording = false
    }

    /**
     * Indique si l'enregistrement est en cours.
     */
    fun isActive(): Boolean = isRecording

    companion object {
        const val TARGET_WIDTH = 3840
        const val TARGET_HEIGHT = 2160
        const val TARGET_FPS = 30
        const val MIN_SHUTTER_SPEED_NS = 2_000_000L  // 1/500s en nanosecondes
    }
}
