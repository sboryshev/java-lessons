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
    }
}