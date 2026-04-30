package ch.aso.capture.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import ch.aso.capture.logger.BarometerSample

/**
 * Logger baromètre pour stabilité altimétrique.
 *
 * Le baromètre du Note 20 Ultra fournit une pression en hPa avec une résolution
 * d'environ 0.01 hPa (~10 cm de variation altimétrique théorique).
 *
 * Usage en post-prod :
 * - Détection de changement de niveau (escalier monté/descendu)
 * - Stabilisation de l'axe Z entre frames consécutives
 * - Validation de cohérence avec l'IMU intégré
 *
 * Sampling : 5 Hz (200 ms)
 */
class BarometerLogger(private val context: Context) : SensorEventListener {

    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val barometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    private var onSampleCallback: ((BarometerSample) -> Unit)? = null

    fun start(onSample: (BarometerSample) -> Unit) {
        onSampleCallback = onSample
        // 200 000 us = 5 Hz
        barometer?.let {
            sensorManager.registerListener(this, it, SAMPLING_PERIOD_US)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        onSampleCallback = null
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type != Sensor.TYPE_PRESSURE) return
        // TODO v0.1 : créer BarometerSample(timestamp, pressure_hpa) et invoquer le callback
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // TODO v0.1 : logger les changements d'accuracy
    }

    companion object {
        const val SAMPLING_PERIOD_US = 200_000  // 5 Hz
    }
}
