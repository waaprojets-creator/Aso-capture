package ch.aso.capture.logger

/**
 * Modèles de données pour les samples synchronisés.
 *
 * Tous les timestamps sont en nanosecondes alignés sur SystemClock.elapsedRealtimeNanos().
 * Cette horloge correspond au SENSOR_TIMESTAMP de Camera2 sur Android moderne.
 */

/**
 * Échantillon IMU regroupant les vecteurs d'un instant donné.
 *
 * Pour optimiser le stockage, on regroupe les valeurs par timestamp commun.
 * Les capteurs Android ne fournissent pas tous au même instant, donc certains
 * champs peuvent être null si le sample n'incluait pas ce capteur.
 */
data class SensorSample(
    val timestampNs: Long,
    val accelerometer: FloatArray? = null,    // m/s² [x, y, z]
    val gyroscope: FloatArray? = null,         // rad/s [x, y, z]
    val rotationVector: FloatArray? = null,    // quaternion [x, y, z, w]
    val gravity: FloatArray? = null,           // m/s² [x, y, z]
    val magneticField: FloatArray? = null      // µT [x, y, z]
)

/**
 * Échantillon GNSS — position + métadonnées.
 *
 * Les pseudo-distances brutes (rawMeasurements) sont disponibles si le hardware
 * et le firmware exposent l'API GnssMeasurement. Indispensable pour PPK.
 */
data class GnssSample(
    val timestampNs: Long,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val accuracyHorizontalM: Float,
    val accuracyVerticalM: Float,
    val numSatellites: Int,
    val numSatellitesL5: Int = 0,        // Satellites observés en L5 (si disponible)
    val rawMeasurements: ByteArray? = null  // Sérialisation des GnssMeasurement bruts
)

/**
 * Échantillon baromètre.
 */
data class BarometerSample(
    val timestampNs: Long,
    val pressureHpa: Float,
    val accuracy: Int       // SensorManager.SENSOR_STATUS_*
)

/**
 * Métadonnées d'un point de contrôle marqué pendant la capture.
 */
data class GcpMarker(
    val timestampNs: Long,
    val label: String,
    val photoFilename: String,
    val voiceNoteFilename: String? = null,
    val gnssAtMark: GnssSample? = null,
    // Coordonnées géomètre — saisies a posteriori
    val surveyedX: Double? = null,
    val surveyedY: Double? = null,
    val surveyedZ: Double? = null,
    val surveyedSystem: String? = null    // ex. "MN95" ou "RGF93/Lambert93"
)
