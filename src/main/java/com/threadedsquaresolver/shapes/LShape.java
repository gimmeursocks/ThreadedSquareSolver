package com.threadedsquaresolver.shapes;

public class LShape extends TetrisShape {

    public LShape() {
        this.height = 2;
        this.width = 3;
        this.shape = new int[][] {
                { 0, 0, 1 },
                { 1, 1, 1 }
        };
    }

    public LShape(int orientation) {
        this();
        for (int i = 0; i < orientation; i++) {
            this.rotate();
        }
    }

    public int getMaxRotations() {
        return 4;
    }

    public LShape createNewInstance(int orientation) {
        return new LShape(orientation);
    }
}