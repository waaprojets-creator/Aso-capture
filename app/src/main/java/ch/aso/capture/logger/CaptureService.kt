package ch.aso.capture.logger

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import ch.aso.capture.R

/**
 * Service en foreground qui orchestre toute la capture pendant un scan.
 *
 * Il agrège :
 * - Le VideoRecorder (Camera2)
 * - Le SensorLogger (IMU 200 Hz)
 * - Le GnssLogger (GPS L1+L5 si disponible)
 * - Le BarometerLogger (5 Hz)
 * - Le MetadataWriter (JSON synchronisé)
 *
 * Il garantit la persistance de la capture même si l'app passe en arrière-plan,
 * via une notification permanente (FOREGROUND_SERVICE_TYPE_CAMERA + LOCATION).
 *
 * v0.1 : structure minimale, pas encore d'agrégation des logs.
 */
class CaptureService : Service() {

    private var captureSession: CaptureSession? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_ID, buildNotification())

        // TODO v0.1 : démarrage de la session de capture
        // captureSession = CaptureSession(this).also { it.start() }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        captureSession?.stop()
        captureSession = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "ASO Capture",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notification permanente pendant un scan en cours"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("ASO Capture")
            .setContentText("Scan en cours")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "aso_capture_session"
        private const val NOTIFICATION_ID = 1
    }
}
