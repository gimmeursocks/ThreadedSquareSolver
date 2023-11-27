package com.threadedsquaresolver.shapes;

public class IShape extends TetrisShape {

    public IShape() {
        this.height = 1;
        this.width = 4;
        this.shape = new int[][] {
                { 1, 1, 1, 1 }
        };
    }

    public IShape(int orientation) {
        this();
        for (int i = 0; i < orientation; i++) {
            this.rotate();
        }
    }

    public int getMaxRotations() {
        return 2;
    }
}
