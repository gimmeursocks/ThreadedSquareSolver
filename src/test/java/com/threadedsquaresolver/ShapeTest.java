package com.threadedsquaresolver;

import com.threadedsquaresolver.shapes.*;

public class ShapeTest {
    public static void main(String[] args) {
        TShape shape1 = new TShape(3);
        System.out.println(shape1.getHeight());
        System.out.println(shape1.getWidth());
        printArray(shape1.getShape());
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
