package com.threadedsquaresolver.board;

import java.util.ArrayList;
import java.util.List;

import com.threadedsquaresolver.shapes.*;

public class TetrisBoardSolver extends Thread {
    public TetrisBoard currentBoard;
    public TetrisShape currentShape;
    private ArrayList<TetrisShape> shapes;
    public volatile boolean solutionFound = false;
    public List<TetrisBoard> solutionList;

    public TetrisBoardSolver(ArrayList<TetrisShape> shapes, List<TetrisBoard> solutionList) {
        this.currentBoard = new TetrisBoard();
        this.currentShape = shapes.get(0);
        this.shapes = shapes;
        this.solutionList = solutionList;
    }

    public TetrisBoardSolver(TetrisBoard board, TetrisShape shape, ArrayList<TetrisShape> shapes, List<TetrisBoard> solutionList) {
        this.currentBoard = board;
        this.currentShape = shape;
        this.shapes = shapes;
        this.solutionList = solutionList;
    }

    @Override
    public void run() {
        if (!solutionFound && currentBoard.placeTetrisShape(currentShape)) {
            if (currentShape.getId() < 3) {
                TetrisShape nextShape = shapes.get(currentShape.getId() + 1);
                for (int i = 0; i < nextShape.getMaxRotations(); i++) {
                    if (!solutionFound) {
                        TetrisBoardSolver childSolver = new TetrisBoardSolver(new TetrisBoard(currentBoard),
                                nextShape.rclone(i), shapes, solutionList);
                        childSolver.start();
                    }
                }
            } else {
                // solved
                solutionFound = true;
                // System.out.print("\nfound--->");
                // System.out.println(currentBoard.getString(currentBoard.getArrayList()));
                // printArray(currentBoard.getBoard());
                solutionList.add(currentBoard);
                return;
            }
        } else {
            return;
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
