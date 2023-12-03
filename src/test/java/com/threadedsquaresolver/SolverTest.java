package com.threadedsquaresolver;

import com.threadedsquaresolver.board.*;
import com.threadedsquaresolver.shapes.*;
import java.util.ArrayList;

public class SolverTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<TetrisShape> toSolve = new ArrayList<TetrisShape>();
        toSolve.add(new OShape());
        toSolve.add(new LShape());
        toSolve.add(new IShape());
        toSolve.add(new JShape());

        ThreadMaker tm = new ThreadMaker(toSolve);

        System.out.println(tm.solutionList.size());
        if(tm.solutionList.size()==0){
            System.out.println("no solutions");
        }
        else{
            for (TetrisBoard board : tm.solutionList) {
                printArray(board.getBoard());
                for (TetrisShape shape : board.getArrayList()) {
                    System.out.print(shape.getClass().getSimpleName() + " " + shape.getCurrentOrientation() + " "
                            + "\n");
                }
                System.out.println("///////////////");
            }
        }
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
