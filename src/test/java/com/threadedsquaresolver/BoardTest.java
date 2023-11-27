package com.threadedsquaresolver;

import com.threadedsquaresolver.shapes.*;
import com.threadedsquaresolver.board.*;

public class BoardTest {
    public static void main(String[] args) {
        ZShape shape1 = new ZShape();
        LShape shape2 = new LShape(3);
        JShape shape3 = new JShape();
        IShape shape4 = new IShape();
        TetrisBoard board1 = new TetrisBoard();

        board1.placeTetrisShape(shape1);
        board1.placeTetrisShape(shape2);
        board1.placeTetrisShape(shape3);
        board1.placeTetrisShape(shape4);

        printArray(board1.getBoard());
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
