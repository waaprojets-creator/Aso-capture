package ch.aso.capture.logger

import android.content.Context
import ch.aso.capture.sensors.BarometerLogger
import ch.aso.capture.sensors.GnssLogger
import ch.aso.capture.sensors.SensorLogger
import ch.aso.capture.storage.MetadataWriter

/**
 * Session de capture unique.
 *
 * Une session = un scan = un dossier horodaté contenant :
 * - video.mp4 (4K@30 H.265)
 * - metadata.json (logs synchronisés)
 * - photos/*.dng (rafales RAW DNG des zones critiques)
 * - calibration/*.jpg (photos macro du mètre maçon)
 * - gcp/*.json (points de contrôle marqués)
 *
 * Le timestamp de référence est `SENSOR_TIMESTAMP` de Camera2 en nanosecondes,
 * aligné sur le clock système. Tous les autres capteurs s'alignent dessus.
 *
 * v0.1 : architecture en place, implémentation des sous-modules à compléter.
 */
class CaptureSession(private val context: Context) {

    private val sessionId: String = generateSessionId()
    private val sessionDir = createSessionDirectory()

    private val videoRecorder = VideoRecorder(context, sessionDir)
    private val sensorLogger = SensorLogger(context)
    private val gnssLogger = GnssLogger(context)
    private val barometerLogger = BarometerLogger(context)
    private val metadataWriter = MetadataWriter(sessionDir)

    /**
     * Démarre tous les enregistrements en parallèle.
     */
    fun start() {
        // Ordre d'activation : capteurs d'abord, vidéo en dernier
        // pour avoir un buffer de logs dès la première frame.
        sensorLogger.start { sample -> metadataWriter.writeSensorSample(sample) }
        gnssLogger.start { sample -> metadataWriter.writeGnssSample(sample) }
        barometerLogger.start { sample -> metadataWriter.writeBarometerSample(sample) }
        videoRecorder.start()
    }

    /**
     * Arrête tous les enregistrements et finalise les fichiers.
     */
    fun stop() {
        videoRecorder.stop()
        sensorLogger.stop()
        gnssLogger.stop()
        barometerLogger.stop()
        metadataWriter.finalize()
    }

    /**
     * Marque un point de contrôle GCP au moment précis du tap utilisateur.
     */
    fun markGcp(label: String) {
        // TODO v0.1 : capture photo dédiée + timestamp + position GNSS bufférisée
    }

    /**
     * Démarre une rafale RAW DNG sur une zone critique.
     */
    fun captureCriticalBurst() {
        // TODO v0.1 : déclenchement burst RAW via Camera2 ImageReader
    }

    private fun generateSessionId(): String {
        val now = System.currentTimeMillis()
        return "scan_$now"
    }

    private fun createSessionDirectory(): java.io.File {
        val baseDir = context.getExternalFilesDir(null) ?: context.filesDir
        val sessionDir = java.io.File(baseDir, "scans/$sessionId")
        sessionDir.mkdirs()
        return sessionDir
    }
}
