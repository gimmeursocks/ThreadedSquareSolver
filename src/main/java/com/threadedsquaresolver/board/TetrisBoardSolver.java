package com.threadedsquaresolver.board;

import com.threadedsquaresolver.shapes.*;

public class TetrisBoardSolver implements Runnable {
    public TetrisBoard solvedBoard;
    public TetrisBoard currentBoard;
    public TetrisShape currentShape;
    public int currentLevel;

    public TetrisBoardSolver() {

    }

    @Override
    public void run() {
    }
}
