#!/usr/bin/env python3
"""
Script de conversion ASO Capture → AliceVision/Meshroom

Lit un dossier de scan ASO (video.mp4 + metadata.jsonl) et produit :
  - frames/ : extraction des frames clés (basée sur netteté + sub-sampling)
  - sfm.json : pré-paramétrage AliceVision avec EOPs initiaux IMU
  - cameras.txt : format COLMAP alternatif

Usage :
    python aso_to_alicevision.py /path/to/scan_xxx/

Pour la v0.1, ce script est un squelette. La logique de pré-positionnement
des caméras à partir des quaternions IMU est à implémenter en v0.3.
"""

import argparse
import json
import os
import subprocess
import sys
from pathlib import Path


def parse_metadata_jsonl(metadata_path: Path) -> dict:
    """Parse le fichier metadata.jsonl en regroupant par type."""
    samples = {"imu": [], "gnss": [], "baro": [], "frame": [], "gcp": []}

    with open(metadata_path) as f:
        for line in f:
            obj = json.loads(line.strip())
            sample_type = obj.get("type")
            if sample_type in samples:
                samples[sample_type].append(obj)

    return samples


def extract_frames(video_path: Path, output_dir: Path, fps_extract: int = 5):
    """Extrait des frames de la vidéo à un fps réduit (sub-sampling intelligent)."""
    output_dir.mkdir(exist_ok=True)
    cmd = [
        "ffmpeg", "-y",
        "-i", str(video_path),
        "-vf", f"fps={fps_extract}",
        "-q:v", "2",
        str(output_dir / "frame_%05d.jpg")
    ]
    print(f"Extraction frames @ {fps_extract} fps...")
    subprocess.run(cmd, check=True, capture_output=True)
    print(f"  → {len(list(output_dir.glob('*.jpg')))} frames extraites")


def main():
    parser = argparse.ArgumentParser(description="Convertit un scan ASO en bundle AliceVision")
    parser.add_argument("scan_dir", type=Path, help="Chemin du dossier scan_xxx/")
    parser.add_argument("--fps", type=int, default=5, help="FPS d'extraction des frames")
    args = parser.parse_args()

    if not args.scan_dir.is_dir():
        sys.exit(f"Erreur : {args.scan_dir} n'est pas un dossier")

    video_file = args.scan_dir / "video.mp4"
    metadata_file = args.scan_dir / "metadata.jsonl"

    if not video_file.exists() or not metadata_file.exists():
        sys.exit("Erreur : video.mp4 ou metadata.jsonl manquant")

    # 1. Parse metadata
    samples = parse_metadata_jsonl(metadata_file)
    print(f"Samples lus :")
    for k, v in samples.items():
        print(f"  - {k}: {len(v)}")

    # 2. Extraction des frames
    frames_dir = args.scan_dir / "frames"
    extract_frames(video_file, frames_dir, args.fps)

    # 3. TODO v0.3 : générer sfm.json avec EOPs initiaux IMU
    # 4. TODO v0.3 : exporter cameras.txt format COLMAP

    print("\n✓ Conversion terminée. Prochaines étapes :")
    print(f"  1. Ouvrir Meshroom et importer {frames_dir}/")
    print(f"  2. Exécuter le pipeline standard de photogrammétrie")
    print(f"  3. Utiliser les GCP du scan pour caler le modèle si disponibles")


if __name__ == "__main__":
    main()
