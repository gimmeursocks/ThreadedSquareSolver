package com.threadedsquaresolver.shapes;

public class OShape extends TetrisShape {

    public OShape() {
        this.height = 2;
        this.width = 2;
        this.shape = new int[][] {
                { 1, 1 },
                { 1, 1 }
        };
    }

    public OShape(int orientation) {
        this();
        for (int i = 0; i < orientation; i++) {
            this.rotate();
        }
    }

    public int getMaxRotations() {
        return 1;
    }

    public OShape createNewInstance(int orientation) {
        return new OShape(orientation);
    }
}