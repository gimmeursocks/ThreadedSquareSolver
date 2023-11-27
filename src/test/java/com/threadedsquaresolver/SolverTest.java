package com.threadedsquaresolver;

import com.threadedsquaresolver.shapes.*;

import java.util.ArrayList;
import java.util.Collections;

// import com.threadedsquaresolver.board.*;

public class SolverTest {
    public static void main(String[] args) {
        ArrayList<TetrisShape> toSolve = new ArrayList<TetrisShape>();
        toSolve.add(new ZShape());
        toSolve.add(new LShape(3));
        toSolve.add(new JShape());
        toSolve.add(new IShape());

        toSolve.removeIf(shape -> shape instanceof IShape);
        Collections.shuffle(toSolve);

        for (int i = toSolve.size(); i < 4; i++) {
            toSolve.add(new IShape());
        }

    }
}
