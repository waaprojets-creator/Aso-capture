package ch.aso.capture.sensors

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import ch.aso.capture.logger.GnssSample

/**
 * Logger GNSS exposant les pseudo-distances brutes (PPK-ready).
 *
 * Sur Note 20 Ultra :
 * - Position 1 Hz via LocationManager.GPS_PROVIDER
 * - Si support L1+L5 effectif : enregistrement des GnssMeasurement bruts
 * - Permet un post-processing PPK ultérieur (en attendant le RTK v2.0)
 *
 * IMPORTANT : sur certains firmware Samsung, le L5 est annoncé mais pas exposé.
 * Vérification obligatoire avec l'app GPSTest avant utilisation terrain.
 *
 * v0.1 : structure en place, callbacks GnssMeasurementsEvent à brancher.
 */
class GnssLogger(private val context: Context) {

    private val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private var onSampleCallback: ((GnssSample) -> Unit)? = null

    fun start(onSample: (GnssSample) -> Unit) {
        if (!hasLocationPermission()) {
            // TODO v0.1 : signaler l'erreur via callback dédié
            return
        }
        onSampleCallback = onSample
        // TODO v0.1 : registerGnssMeasurementsCallback pour les pseudo-distances
        // TODO v0.1 : registerGnssStatusCallback pour le suivi des satellites
        // TODO v0.1 : requestLocationUpdates à 1 Hz
    }

    fun stop() {
        // TODO v0.1 : unregisterAll
        onSampleCallback = null
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}
