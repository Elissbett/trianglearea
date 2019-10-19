package com.ebolotina.trianglearea;


public class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;
    private Double area;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        if (area == null) {
            area = 1.0 / 2 * Math.abs((a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y));
        }
        return area;
    }

    public boolean isIsosceles() {
        final double ab = length(a, b);
        final double bc = length(c, b);
        final double ac = length(a, c);

        return sidesEquals(ab, bc) || sidesEquals(ac, bc) || sidesEquals(ab, ac);
    }

    @Override
    public String toString() {
        return a.x + " " + a.y + " " + b.x + " " + b.y + " " + c.x + " " + c.y;
    }

    private double length(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private boolean sidesEquals(double first, double second) {
        return Math.abs(first - second) < 0.00001;
    }

}