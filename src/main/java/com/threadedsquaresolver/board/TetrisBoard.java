package com.threadedsquaresolver.board;

import com.threadedsquaresolver.shapes.*;

public class TetrisBoard {
    private int[][] board;
    private boolean finished = false;

    public TetrisBoard() {
        this.board = new int[4][4];
    }

    public int[][] getBoard() {
        return board;
    }

    private boolean fitTetrisShape(TetrisShape shape, int i, int j) {
        for (int h = 0; h < shape.getHeight(); h++) {
            for (int w = 0; w < shape.getWidth(); w++) {
                if (this.board[h + i][w + j] == 1 && shape.getShape(h, w) == 1) {
                    return false;
                }
            }
        }

        for (int h = 0; h < shape.getHeight(); h++) {
            for (int w = 0; w < shape.getWidth(); w++) {
                if (shape.getShape(h, w) == 1) {
                    this.board[h + i][w + j] = 1;
                }
            }
        }

        return true;
    }

    public int placeTetrisShape(TetrisShape shape) {
        int i = 0;
        int j = 0;
        while (!fitTetrisShape(shape, i, j)) {
            if (j + shape.getWidth() <= 3) {
                j++;
            } else if (i + shape.getHeight() <= 3) {
                i++;
                j = 0;
            } else {
                finished = true;
                break;
            }
        }
        if (finished)
            return -1;
        else
            return 1;
    }
}