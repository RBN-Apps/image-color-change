package com.rbn;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorRangeReplacer {
    // Farbbereich definieren
    static Color startRange = new Color(0, 0, 0); // Minimale Farbe des Bereichs
    static Color endRange = new Color(16, 16, 16); // Maximale Farbe des Bereichs

    // Definiere die Farben für den Farbverlauf
    static Color gradientColor1 = new Color(229, 154, 57);
    static Color gradientColor2 = new Color(67, 47, 27);
    static boolean verticalGradiant = true;

    static String fileName = "input.png";

    public static void main(String[] args) {
        try {
            if(args.length == 0) {
                System.out.println("Keine Argumente übergeben. Verwende Standardwerte.");
                System.out.println("Nutze --startRange <r>,<g>,<b> " +
                        "--endRange <r>,<g>,<b> --gradientColor1 <r>,<g>,<b> " +
                        "--gradientColor2 <r>,<g>,<b> --verticalGradiant <true/false> " +
                        "--fileName <filename>");

            } else {
                applyArgs(args);
            }
            String gradientDirection = verticalGradiant ? "Vertikaler " : "Horizontaler ";
            System.out.println("Bildbearbeitung gestartet...");
            System.out.println("Startbereich: " + startRange);
            System.out.println("Endbereich: " + endRange);
            System.out.println(gradientDirection + "Farbverlauf: " + gradientColor1 + " -> " + gradientColor2);

            // Bild laden
            File inputFile = new File(fileName);
            BufferedImage image = ImageIO.read(inputFile);


            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    Color pixelColor = new Color(image.getRGB(x, y));
                    if (isColorInRange(pixelColor, startRange, endRange)) {
                        float fraction = verticalGradiant ? (float) y / image.getHeight() : (float) x / image.getWidth();
                        Color blendColor = blendColors(gradientColor1, gradientColor2, fraction);
                        image.setRGB(x, y, blendColor.getRGB());
                    }
                }
            }

            File outputFile = new File("output.png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("Bildbearbeitung abgeschlossen.");

        } catch (IOException e) {
            System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
        }
    }

    private static void applyArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--startRange" -> {
                    String[] rgb = args[i + 1].split(",");
                    startRange = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                }
                case "--endRange" -> {
                    String[] rgb = args[i + 1].split(",");
                    endRange = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                }
                case "--gradientColor1" -> {
                    String[] rgb = args[i + 1].split(",");
                    gradientColor1 = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                }
                case "--gradientColor2" -> {
                    String[] rgb = args[i + 1].split(",");
                    gradientColor2 = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                }
                case "--verticalGradiant" -> verticalGradiant = Boolean.parseBoolean(args[i + 1]);
                case "--fileName" -> {
                    fileName = args[i + 1];
                    System.out.println("Dateiname: " + fileName);
                }
            }
        }
    }

    public static boolean isColorInRange(Color color, Color startRange, Color endRange) {
        return color.getRed() >= startRange.getRed() && color.getRed() <= endRange.getRed() &&
                color.getGreen() >= startRange.getGreen() && color.getGreen() <= endRange.getGreen() &&
                color.getBlue() >= startRange.getBlue() && color.getBlue() <= endRange.getBlue();
    }

    public static Color blendColors(Color color1, Color color2, float fraction) {
        int red = (int) (color1.getRed() * (1 - fraction) + color2.getRed() * fraction);
        int green = (int) (color1.getGreen() * (1 - fraction) + color2.getGreen() * fraction);
        int blue = (int) (color1.getBlue() * (1 - fraction) + color2.getBlue() * fraction);
        return new Color(red, green, blue);
    }
}