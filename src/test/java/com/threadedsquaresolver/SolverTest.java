package com.threadedsquaresolver;

import com.threadedsquaresolver.board.*;
import com.threadedsquaresolver.shapes.*;
import java.util.ArrayList;

public class SolverTest {
    public static void main(String[] args) {
        ArrayList<TetrisShape> toSolve = new ArrayList<TetrisShape>();
        // toSolve.add(new OShape());
        // toSolve.add(new LShape());
        // toSolve.add(new IShape());
        // toSolve.add(new JShape());
        toSolve.add(new OShape());
        toSolve.add(new OShape());
        toSolve.add(new OShape());
        toSolve.add(new OShape());

        ThreadMaker tm = new ThreadMaker(toSolve);

        try {
            Thread.sleep(150);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(tm.solutionList.size());
        for (TetrisBoard board : tm.solutionList) {
            for (TetrisShape shape : board.getArrayList()) {
                System.out.print(shape.getClass().getSimpleName() + " " + shape.getCurrentOrientation() + " "
                        + "\n");
            }
            System.out.println("///////////////");
        }
    }
}
