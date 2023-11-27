package com.threadedsquaresolver.shapes;

public abstract class TetrisShape {
    protected int shape[][];
    protected int height;
    protected int width;
    protected int orientation = 0;

    public abstract int getMaxRotations();

    public int[][] getShape() {
        return shape;
    }

    public int getShape(int i, int j) {
        return shape[i][j];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getCurrentOrientation() {
        return orientation;
    }

    protected void rotate() {
        int[][] rotatedArray = new int[this.width][this.height];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                rotatedArray[j][this.height - 1 - i] = shape[i][j];
            }
        }

        this.shape = rotatedArray;

        int temp = this.height;
        this.height = this.width;
        this.width = temp;

        this.orientation = (this.orientation + 1) % getMaxRotations();
    }
}