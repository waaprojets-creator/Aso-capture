package ch.aso.capture.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.aso.capture.R

/**
 * Activité de capture en mode plein écran (paysage).
 *
 * Responsabilités :
 * - Afficher le preview caméra (Camera2 + CameraX preview)
 * - Afficher le HUD AR (live mesh, indicateurs qualité)
 * - Permettre les actions utilisateur :
 *   * Démarrer/arrêter le scan
 *   * Marquer un GCP (tap dédié)
 *   * Marquer une zone critique pour rafale RAW
 *   * Calibrer le scale-lock (mètre maçon)
 *
 * Le service de capture (CaptureService) tourne en foreground pendant toute la durée.
 */
class CaptureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture)

        // TODO v0.1 : initialisation Camera2 + preview
        // TODO v0.1 : démarrage du CaptureService
        // TODO v0.1 : binding des boutons d'action
        // TODO v0.1 : initialisation ARCore session pour live mesh
    }

    override fun onPause() {
        super.onPause()
        // TODO v0.1 : pause Camera2
        // TODO v0.1 : pause ARCore session
    }

    override fun onResume() {
        super.onResume()
        // TODO v0.1 : resume Camera2
        // TODO v0.1 : resume ARCore session
    }
}
