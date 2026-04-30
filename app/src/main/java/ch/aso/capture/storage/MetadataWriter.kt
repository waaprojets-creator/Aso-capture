package ch.aso.capture.storage

import ch.aso.capture.logger.BarometerSample
import ch.aso.capture.logger.GcpMarker
import ch.aso.capture.logger.GnssSample
import ch.aso.capture.logger.SensorSample
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

/**
 * Écrivain du fichier metadata.json synchronisé.
 *
 * Stratégie d'écriture :
 * - Format JSON Lines (JSONL) plutôt que JSON unique : un objet par ligne
 *   pour permettre l'écriture en streaming et la lecture incrémentale.
 * - Buffer en mémoire de 100 samples avant flush disque pour éviter la latence.
 * - Synchronisation thread-safe par Mutex (à ajouter quand on intègre coroutines).
 *
 * Le fichier final est ensuite reformaté en JSON valide en post-prod station,
 * ou parsé directement comme JSONL par les pipelines AliceVision/Metashape.
 *
 * Schéma : voir docs/architecture/METADATA_SCHEMA.md
 */
class MetadataWriter(private val sessionDir: File) {

    private val outputFile: File = File(sessionDir, "metadata.jsonl")
    private val writer: BufferedWriter = BufferedWriter(FileWriter(outputFile))

    fun writeSensorSample(sample: SensorSample) {
        // TODO v0.1 : sérialisation Moshi → écriture ligne JSONL
    }

    fun writeGnssSample(sample: GnssSample) {
        // TODO v0.1 : sérialisation Moshi → écriture ligne JSONL
    }

    fun writeBarometerSample(sample: BarometerSample) {
        // TODO v0.1 : sérialisation Moshi → écriture ligne JSONL
    }

    fun writeGcpMarker(marker: GcpMarker) {
        // TODO v0.1 : sérialisation Moshi → écriture ligne JSONL
    }

    /**
     * Finalise le fichier : flush + fermeture + écriture du header de session.
     */
    fun finalize() {
        writer.flush()
        writer.close()
        // TODO v0.1 : écrire un fichier session.json contenant les métadonnées globales
        //   (device model, app version, durée, nombre de samples par type, etc.)
    }
}
