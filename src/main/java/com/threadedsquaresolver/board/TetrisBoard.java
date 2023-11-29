package com.threadedsquaresolver.board;

import java.util.ArrayList;

import com.threadedsquaresolver.shapes.*;

public class TetrisBoard {
    private int[][] board;
    private boolean finished = false;
    private ArrayList<TetrisShape> shapes;

    public TetrisBoard() {
        this.board = new int[4][4];
        this.shapes = new ArrayList<TetrisShape>();
    }

    public TetrisBoard(TetrisBoard board) {
        this.board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            this.board[i] = board.getBoard()[i].clone();
        }
        this.shapes = new ArrayList<TetrisShape>();
        for (int i = 0; i < board.shapes.size(); i++) {
            this.shapes.add(board.shapes.get(i));
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public ArrayList<TetrisShape> getArrayList() {
        return shapes;
    }

    public String getString(ArrayList<TetrisShape> arr){
        String str = new String(" ");
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i).getClass().getSimpleName() +" "+ arr.get(i).getCurrentOrientation() + "//";
        }
        return str;
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

    public boolean placeTetrisShape(TetrisShape shape) {
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
            return false;
        else {
            shapes.add(shape);
            return true;
        }
    }
}