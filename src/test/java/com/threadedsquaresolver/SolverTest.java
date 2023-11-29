package com.threadedsquaresolver;

import com.threadedsquaresolver.board.*;
import com.threadedsquaresolver.shapes.*;
import java.util.ArrayList;

public class SolverTest {
    public static void main(String[] args) {
        ArrayList<TetrisShape> toSolve = new ArrayList<TetrisShape>();
        toSolve.add(new ZShape());
        toSolve.add(new LShape());
        toSolve.add(new IShape());
        toSolve.add(new JShape());

        ThreadMaker tm = new ThreadMaker(toSolve);
        System.out.println(tm.toString());
    }
}
