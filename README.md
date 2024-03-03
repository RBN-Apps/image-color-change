# Color Range Replacer

## Description

The Color Range Replacer is a Java application designed to modify images by replacing a specified range of colors with a
gradient. It supports both vertical and horizontal gradients.

## Features

- Replace a specific color range in an image with a gradient.
- Customizable color range for replacement.
- Option to apply either vertical or horizontal gradients.
- Command-line arguments for easy customization without altering the code.

## Requirements

- Java Development Kit (JDK)

## Usage

First, compile the Java program using maven.

#### Linux/Mac
```bash
mvn clean install
```

Or download the JAR file from the [releases](https://github.com/RBN-Apps/image-color-change/releases) page.

Then, you can run the program with the following
syntax:

```bash
java -jar ImageColorChange-1.0.0.jar
```

### Options

- `--startRange <r>,<g>,<b>`: Defines the starting color of the range to be replaced (inclusive). Values
  for `<r>`, `<g>`, and `<b>` should be between 0 and 255. 
- `--endRange <r>,<g>,<b>`: Defines the ending color of the range to be replaced (inclusive). Values for `<r>`, `<g>`,
  and `<b>` should be between 0 and 255.
- `--gradientColor1 <r>,<g>,<b>`: Specifies the first color of the gradient. (e.g. 229,154,57).
- `--gradientColor2 <r>,<g>,<b>`: Specifies the second color of the gradient. (e.g. 67,47,27).
- `--verticalGradiant <true/false>`: Determines the orientation of the gradient. Use `true` for a vertical gradient
  or `false` for a horizontal gradient.
- `--fileName <filename>`: Specifies the name of the input image file (e.g., "input.png").

### Example

```bash
java -jar ImageColorChange-1.0.0.jar --startRange 0,0,0 --endRange 16,16,16 --gradientColor1 229,154,57 --gradientColor2 67,47,27 --verticalGradiant true --fileName input.png
```

This example replaces colors in the range from black (0,0,0) to dark gray (16,16,16) with a vertical gradient from a
golden color (229,154,57) to a dark brown (67,47,27) in the `input.png` file.

## Output

The program generates an output image named `output.png` in the same directory as the input file. This image contains
the applied color range replacement.