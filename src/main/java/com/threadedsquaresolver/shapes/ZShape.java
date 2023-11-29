package com.threadedsquaresolver.shapes;

public class ZShape extends TetrisShape {

    public ZShape() {
        this.height = 2;
        this.width = 3;
        this.shape = new int[][] {
                { 1, 1, 0 },
                { 0, 1, 1 }
        };
    }

    public ZShape(int orientation) {
        this();
        for (int i = 0; i < orientation; i++) {
            this.rotate();
        }
    }

    public int getMaxRotations() {
        return 2;
    }

    public ZShape createNewInstance(int orientation) {
        return new ZShape(orientation);
    }
}