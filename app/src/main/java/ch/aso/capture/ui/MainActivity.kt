package ch.aso.capture.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.aso.capture.R

/**
 * Activité principale d'ASO Capture.
 *
 * Affiche le menu principal :
 * - Démarrer un nouveau scan
 * - Consulter les scans précédents
 * - Calibrer le mètre maçon (mode scale-lock)
 * - Paramètres
 *
 * v0.1 : minimal — un seul bouton "Démarrer scan" qui lance CaptureActivity.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO v0.1 : implémenter les boutons du menu
        // TODO v0.1 : vérification des permissions au lancement
        // TODO v0.1 : check ARCore availability
    }

    /**
     * Lance l'activité de capture.
     */
    private fun launchCapture() {
        startActivity(Intent(this, CaptureActivity::class.java))
    }
}
