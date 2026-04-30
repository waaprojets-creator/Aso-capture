package ch.aso.capture.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import ch.aso.capture.logger.SensorSample

/**
 * Logger IMU haute fréquence : accéléromètre + gyroscope + quaternion + gravité.
 *
 * Configuration cible :
 * - 200 Hz (sampling period 5 ms) — nécessite la permission HIGH_SAMPLING_RATE_SENSORS
 * - Capteurs simultanés : ACCELEROMETER, GYROSCOPE, ROTATION_VECTOR, GRAVITY, MAGNETIC_FIELD
 * - Timestamp en nanosecondes aligné sur SystemClock.elapsedRealtimeNanos()
 *
 * Cette horloge est compatible avec SENSOR_TIMESTAMP de Camera2, ce qui permet
 * une synchronisation exacte vidéo/IMU.
 *
 * v0.1 : structure en place, callback à brancher sur MetadataWriter.
 */
class SensorLogger(private val context: Context) : SensorEventListener {

    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private var onSampleCallback: ((SensorSample) -> Unit)? = null

    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val rotationVector: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    private val gravity: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
    private val magneticField: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    fun start(onSample: (SensorSample) -> Unit) {
        onSampleCallback = onSample
        // 5000 us = 200 Hz
        accelerometer?.let { sensorManager.registerListener(this, it, SAMPLING_PERIOD_US) }
        gyroscope?.let { sensorManager.registerListener(this, it, SAMPLING_PERIOD_US) }
        rotationVector?.let { sensorManager.registerListener(this, it, SAMPLING_PERIOD_US) }
        gravity?.let { sensorManager.registerListener(this, it, SAMPLING_PERIOD_US) }
        // Magnetic field à 50 Hz suffit
        magneticField?.let { sensorManager.registerListener(this, it, 20_000) }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        onSampleCallback = null
    }

    override fun onSensorChanged(event: SensorEvent) {
        // TODO v0.1 : agrégation des samples par type, formatage en SensorSample
        // TODO v0.1 : appel onSampleCallback?.invoke(sample)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // TODO v0.1 : logger les changements d'accuracy pour validation post-prod
    }

    companion object {
        const val SAMPLING_PERIOD_US = 5_000  // 200 Hz
    }
}
