package com.ebolotina.trianglearea;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Two parameters has been expected");
            return;
        }

        Triangle biggestTriangle;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])))) {
            biggestTriangle = findBiggestTriangle(reader);
        } catch (FileNotFoundException e) {
            System.err.println("File " + args[0] + " not found");
            return;
        } catch (IOException e) {
            System.err.println("Unable to read the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            writer.write(biggestTriangle == null || biggestTriangle.area() == 0 ? "" : biggestTriangle.toString());
        } catch (IOException e) {
            System.err.println("Unable to write the file: " + e.getMessage());
        }
    }

    private static Triangle findBiggestTriangle(BufferedReader reader) throws IOException {
        Triangle biggestTriangle = null;

        String line = reader.readLine();
        while (line != null) {
            try {
                Triangle triangle = readTriangle(line);
                if (triangle.isIsosceles()) {
                    biggestTriangle = compareAndSwap(triangle, biggestTriangle);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number in line: " + line);
            } catch (IllegalArgumentException e){
                System.err.println(e.getMessage());
            }

            line = reader.readLine();
        }

        return biggestTriangle;
    }

    private static Triangle readTriangle(String line) {
        final String[] coordinatesString = line.split("\\s+");
        if (coordinatesString.length != 6) {
            throw new IllegalArgumentException("Invalid quantity of coordinates in line: " + line);
        }

        final Point a = new Point(Integer.valueOf(coordinatesString[0]),
                                  Integer.valueOf(coordinatesString[1]));
        final Point b = new Point(Integer.valueOf(coordinatesString[2]),
                                  Integer.valueOf(coordinatesString[3]));
        final Point c = new Point(Integer.valueOf(coordinatesString[4]),
                                  Integer.valueOf(coordinatesString[5]));

        return new Triangle(a, b, c);
    }

    private static Triangle compareAndSwap(Triangle triangle, Triangle biggestTriangle) {
        return biggestTriangle == null || triangle.area() > biggestTriangle.area() ? triangle : biggestTriangle;
    }
}
