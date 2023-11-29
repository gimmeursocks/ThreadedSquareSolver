package com.threadedsquaresolver.shapes;

public class SShape extends TetrisShape {

    public SShape() {
        this.height = 2;
        this.width = 3;
        this.shape = new int[][] {
                { 0, 1, 1 },
                { 1, 1, 0 }
        };
    }

    public SShape(int orientation) {
        this();
        for (int i = 0; i < orientation; i++) {
            this.rotate();
        }
    }

    public int getMaxRotations() {
        return 2;
    }

    public SShape createNewInstance(int orientation) {
        return new SShape(orientation);
    }
}