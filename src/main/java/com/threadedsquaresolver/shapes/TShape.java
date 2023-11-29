package com.threadedsquaresolver.shapes;

public class TShape extends TetrisShape {

    public TShape() {
        this.height = 2;
        this.width = 3;
        this.shape = new int[][] {
                { 0, 1, 0 },
                { 1, 1, 1 }
        };
    }

    public TShape(int orientation) {
        this();
        for (int i = 0; i < orientation; i++) {
            this.rotate();
        }
    }

    public int getMaxRotations() {
        return 4;
    }

    public TShape createNewInstance(int orientation) {
        return new TShape(orientation);
    }
}