#!/usr/bin/env python3
"""
Génère une planche A4 imprimable contenant les marqueurs ArUco
pour le scale-lock du mètre maçon ASO Capture.

Sortie : aruco_planche_v0.1.pdf

Dépendances :
    pip install opencv-contrib-python reportlab pillow

Usage :
    python generate_aruco_sheet.py
"""

import os
import sys

try:
    import cv2
    import numpy as np
    from reportlab.lib.pagesizes import A4
    from reportlab.lib.units import mm
    from reportlab.pdfgen import canvas
    from PIL import Image
except ImportError as e:
    print(f"Dépendance manquante : {e}")
    print("Installer avec : pip install opencv-contrib-python reportlab pillow")
    sys.exit(1)

OUTPUT_PDF = "aruco_planche_v0.1.pdf"
TEMP_DIR = "_tmp_aruco"

MARKER_SIZE_MM = 40
MARKER_PIXELS = 472  # 40 mm @ 300 dpi
MARKER_IDS = [0, 1]
DICT_NAME = cv2.aruco.DICT_4X4_50


def generate_marker_png(marker_id: int, output_path: str):
    """Génère un PNG d'un marqueur ArUco avec bordure blanche."""
    dictionary = cv2.aruco.getPredefinedDictionary(DICT_NAME)
    marker = cv2.aruco.generateImageMarker(dictionary, marker_id, MARKER_PIXELS)

    # Ajouter une bordure blanche de 60 px (5 mm @ 300 dpi)
    bordered = cv2.copyMakeBorder(
        marker, 60, 60, 60, 60, cv2.BORDER_CONSTANT, value=255
    )

    cv2.imwrite(output_path, bordered)


def create_pdf():
    """Crée le PDF A4 avec les deux marqueurs."""
    os.makedirs(TEMP_DIR, exist_ok=True)

    marker_paths = []
    for marker_id in MARKER_IDS:
        path = os.path.join(TEMP_DIR, f"aruco_id{marker_id}.png")
        generate_marker_png(marker_id, path)
        marker_paths.append((marker_id, path))

    c = canvas.Canvas(OUTPUT_PDF, pagesize=A4)
    page_width, page_height = A4

    # Titre
    c.setFont("Helvetica-Bold", 14)
    c.drawString(20 * mm, page_height - 20 * mm, "ASO Capture - Marqueurs ArUco scale-lock")
    c.setFont("Helvetica", 10)
    c.drawString(20 * mm, page_height - 28 * mm, "Imprimer à 100% (sans mise à l'échelle)")
    c.drawString(20 * mm, page_height - 34 * mm, "Vérifier que chaque marqueur fait 40 x 40 mm après impression")

    # Marker ID 0
    marker_size_pdf = MARKER_SIZE_MM * mm + 10 * mm  # 40 mm + bordure 5 mm × 2
    x0 = 30 * mm
    y0 = page_height - 90 * mm
    c.drawImage(marker_paths[0][1], x0, y0 - marker_size_pdf, marker_size_pdf, marker_size_pdf)
    c.setFont("Helvetica-Bold", 12)
    c.drawString(x0, y0 + 4 * mm, "ID 0 - Extrémité gauche du mètre maçon")

    # Marker ID 1
    x1 = 30 * mm
    y1 = y0 - marker_size_pdf - 20 * mm
    c.drawImage(marker_paths[1][1], x1, y1 - marker_size_pdf, marker_size_pdf, marker_size_pdf)
    c.setFont("Helvetica-Bold", 12)
    c.drawString(x1, y1 + 4 * mm, "ID 1 - Extrémité droite du mètre maçon")

    # Pied de page
    c.setFont("Helvetica-Oblique", 8)
    c.drawString(20 * mm, 15 * mm,
                 "Distance théorique entre centres : 2.000 m exactement (mètre maçon standard)")
    c.drawString(20 * mm, 11 * mm,
                 "Plastifier en mat avant utilisation - éviter les pliures et reflets")

    c.save()

    # Cleanup temp
    for _, path in marker_paths:
        os.remove(path)
    os.rmdir(TEMP_DIR)

    print(f"✓ Planche générée : {OUTPUT_PDF}")


if __name__ == "__main__":
    create_pdf()
