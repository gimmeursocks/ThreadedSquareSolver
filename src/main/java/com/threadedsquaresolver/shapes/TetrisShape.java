package com.threadedsquaresolver.shapes;

public abstract class TetrisShape implements Cloneable {
    protected int shape[][];
    protected int height;
    protected int width;
    protected int orientation;
    protected int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TetrisShape rclone(int a) {
        try {
            TetrisShape obj = (TetrisShape) super.clone();
            for (int i = 0; i < a; i++) {
                obj.rotate();
            }
            return (TetrisShape) obj;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}