package com.threadedsquaresolver.board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

import com.threadedsquaresolver.shapes.*;

public class TetrisBoardSolver extends Thread {
    public TetrisBoard currentBoard;
    public TetrisShape currentShape;
    private ArrayList<TetrisShape> shapes;
    public List<TetrisBoard> solutionList;
    public ExecutorService executor;

    public TetrisBoardSolver(ArrayList<TetrisShape> shapes, List<TetrisBoard> solutionList, ExecutorService executor) {
        this.currentBoard = new TetrisBoard();
        this.currentShape = shapes.get(0);
        this.shapes = shapes;
        this.solutionList = solutionList;
        this.executor = executor;

        for (int i = 0; i < currentShape.getMaxRotations(); i++) {
            TetrisBoardSolver solver = new TetrisBoardSolver(new TetrisBoard(currentBoard), currentShape.rclone(i),
                    shapes, solutionList, executor);
            try {
                executor.submit(solver);
            } catch (RejectedExecutionException e) {
            }
        }
    }

    public TetrisBoardSolver(TetrisBoard board, TetrisShape shape, ArrayList<TetrisShape> shapes,
            List<TetrisBoard> solutionList, ExecutorService executor) {
        this.currentBoard = board;
        this.currentShape = shape;
        this.shapes = shapes;
        this.solutionList = solutionList;
        this.executor = executor;
    }

    @Override
    public void run() {
        int currentShapeId = currentShape.getId();
        if (currentBoard.placeTetrisShape(currentShape)) {
            if (currentShapeId < 3) {
                TetrisShape nextShape = this.shapes.get(currentShapeId + 1);
                for (int i = 0; i < nextShape.getMaxRotations(); i++) {
                    TetrisBoardSolver childSolver = new TetrisBoardSolver(new TetrisBoard(currentBoard),
                            nextShape.rclone(i), shapes, solutionList, executor);
                    try {
                        executor.submit(childSolver);
        
                    } catch (RejectedExecutionException e) {
                    }
                }
            } else {
                solutionList.add(currentBoard);
                executor.shutdownNow();
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
