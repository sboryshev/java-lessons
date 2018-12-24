package com.issart.boryshev;

public class MyFirstProgram {
	public static void main(String[] args){

        Point p1 = new Point(2, 0);
        Point p2 = new Point(8, 0);

        double distance = p2.distance(p1);
        System.out.println(distance);

        Point p3 = new Point(3, 9);
        Point p4 = new Point(1, 5);

        double distance1 = p4.distance(p3);
        System.out.println(distance1);

        Point p5 = new Point(5, 9);
        Point p6 = new Point(-2, 3);

        double distance2 = distance(p5, p6);
        System.out.println(distance2);
    }

    public static double distance(Point p1, Point p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
}