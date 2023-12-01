package com.threadedsquaresolver;

import com.threadedsquaresolver.board.TetrisBoard;
import com.threadedsquaresolver.board.ThreadMaker;
import com.threadedsquaresolver.shapes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Box;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private TextField I_input;

    @FXML
    private TextField J_input;

    @FXML
    private TextField L_input;

    @FXML
    private TextField O_input;

    @FXML
    private TextField S_input;

    @FXML
    private TextField T_input;


    public static final int[][] testMatrix = {
            {0, 1, 2, 3},
            {1, 2, 3, 0},
            {2, 3, 0, 1},
            {3, 0, 1, 2}
    };
    @FXML

    private TextField Z_input;

    @FXML
    public Rectangle L1;
    @FXML
    private Rectangle L2;
    @FXML
    private Rectangle L3;
    @FXML
    private Rectangle L4;
    @FXML
    private Rectangle L5;
    @FXML
    private Rectangle L6;
    @FXML
    private Rectangle L7;
    @FXML
    private Rectangle L8;
    @FXML
    private Rectangle L9;
    @FXML
    private Rectangle L10;
    @FXML
    private Rectangle L11;
    @FXML
    private Rectangle L12;
    @FXML
    private Rectangle L13;
    @FXML
    private Rectangle L14;
    @FXML
    private Rectangle L15;
    @FXML
    private Rectangle L16;
    @FXML
    private Rectangle[][] rectangles = new Rectangle[4][4];
    @FXML
    private Button solutionB;
    @FXML
    private Button solve;
    private int inputI;
    private int inputJ;
    private int inputL;
    private int inputO;
    private int inputS;
    private int inputT;
    private int inputZ;

    private int minValue = 0;
    private int maxValue = 4;
    ArrayList<TetrisShape> toSolve = new ArrayList<TetrisShape>();
    private void saveValues() {
        inputI = Integer.parseInt(I_input.getText());
        inputJ = Integer.parseInt(J_input.getText());
        inputL = Integer.parseInt(L_input.getText());
        inputO = Integer.parseInt(O_input.getText());
        inputS = Integer.parseInt(S_input.getText());
        inputT = Integer.parseInt(T_input.getText());
        inputZ = Integer.parseInt(Z_input.getText());

    }

    private void setDefaultValues() {
        I_input.setText("0");
        J_input.setText("0");
        L_input.setText("0");
        O_input.setText("0");
        S_input.setText("0");
        T_input.setText("0");
        Z_input.setText("0");
    }



    @FXML
    private Rectangle myrec;

    public int getInputI() {
        return inputI;
    }

    public int getInputJ() {
        return inputJ;
    }

    public int getInputL() {
        return inputL;
    }
    public int getInputO() {
        return inputO;
    }

    public int getInputS() {
        return inputS;
    }

    public int getInputT() {
        return inputT;
    }

    public int getInputZ() {
        return inputZ;
    }

private void setColorsForMatrix(int[][] matrix) {
    Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    javafx.application.Platform.runLater(() -> {
        for (int value = 0; value < colors.length; value++) {
            int finalValue = value;

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == finalValue) {
                        rectangles[i][j].setFill(colors[finalValue]);
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}

    @FXML
    void setValues(MouseEvent event) {
        saveValues();
        int inputIValue = getInputI();
        System.out.println("Input I value from Controller: " + inputIValue);

        for (int i = 0; i < 4; i++) {
            if (inputI != 0) {
                toSolve.add(new IShape());
                inputI--;
            }
            if (inputJ != 0) {
                toSolve.add(new JShape());
                inputJ--;
            }
            if (inputL != 0) {
                toSolve.add(new LShape());
                inputL--;
            }
            if (inputO != 0) {
                toSolve.add(new OShape());
                inputO--;
            }
            if (inputT != 0) {
                toSolve.add(new TShape());
                inputT--;
            }
            if (inputS != 0) {
                toSolve.add(new SShape());
                inputS--;
            }
            if (inputZ != 0) {
                toSolve.add(new ZShape());
                inputZ--;
            }

        }


        ThreadMaker tm = new ThreadMaker(toSolve);
        try {
            Thread.sleep(5000);
            System.out.println("عدد الحلول"+tm.solutionList.size());

        } catch (Exception e) {
                System.out.println("No Solution found");
            }
        int[][] arr = tm.solutionList.get(1).getBoard();
        setColorsForMatrix(arr);
        }
    public void initialize() {
        solutionB.setVisible(false);
        setDefaultValues();
        setMinMaxValues(I_input);
        setMinMaxValues(J_input);
        setMinMaxValues(L_input);
        setMinMaxValues(O_input);
        setMinMaxValues(S_input);
        setMinMaxValues(T_input);
        setMinMaxValues(Z_input);
        rectangles[0][0] = L1;
        rectangles[0][1] = L2;
        rectangles[0][2] = L3;
        rectangles[0][3] = L4;
        rectangles[1][0] = L5;
        rectangles[1][1] = L6;
        rectangles[1][2] = L7;
        rectangles[1][3] = L8;
        rectangles[2][0] = L9;
        rectangles[2][1] = L10;
        rectangles[2][2] = L11;
        rectangles[2][3] = L12;
        rectangles[3][0] = L13;
        rectangles[3][1] = L14;
        rectangles[3][2] = L15;
        rectangles[3][3] = L16;
    }

        private void setMinMaxValues (TextField textField){
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                } else {
                    int value = Integer.parseInt(newValue);
                    if (value < minValue || value > maxValue) {
                        textField.setText(oldValue);
                    }
                }
            });
        }
    }

