package ch.aso.capture

import android.app.Application
import android.util.Log

/**
 * Classe d'application principale d'ASO Capture.
 *
 * Initialisation au démarrage : pas de cloud, pas de télémétrie.
 * Tous les paramètres sont locaux.
 */
class AsoCaptureApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "ASO Capture v${BuildConfig.VERSION_NAME} started.")
        // TODO v0.2 : initialisation du logger persistant
        // TODO v0.2 : vérification des permissions critiques
        // TODO v0.3 : auto-détection du smartphone et chargement du profil hardware
    }

    companion object {
        private const val TAG = "AsoCapture"
    }
}
