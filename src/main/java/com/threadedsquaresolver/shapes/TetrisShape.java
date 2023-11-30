package com.threadedsquaresolver.shapes;

public abstract class TetrisShape implements Cloneable {
    protected int shape[][];
    protected int height;
    protected int width;
    protected int orientation;
    protected volatile int id;

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

    public synchronized int getId() {
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

    public TetrisShape clone() {
        try {
            TetrisShape obj = (TetrisShape) super.clone();

            obj.shape = new int[this.shape.length][this.shape[0].length];
            for (int i = 0; i < this.shape.length; i++) {
                System.arraycopy(this.shape[i], 0, obj.shape[i], 0, this.shape[i].length);
            }

            return (TetrisShape) obj;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public TetrisShape rclone(int a) {
        try {
            TetrisShape obj = (TetrisShape) super.clone();

            obj.shape = new int[this.shape.length][this.shape[0].length];
            for (int i = 0; i < this.shape.length; i++) {
                obj.shape[i] = this.shape[i].clone();
            }

            for (int i = 0; i < a; i++) {
                obj.rotate();
            }
            return (TetrisShape) obj;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}