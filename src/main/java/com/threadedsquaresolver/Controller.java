package com.threadedsquaresolver;

import com.threadedsquaresolver.board.*;
import com.threadedsquaresolver.shapes.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
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
    private TextField Z_input;
    @FXML
    private TextField T_input;

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
    private Rectangle[][] rectangles;
    @FXML
    private Button solutionButton;
    @FXML
    private Button solveButton;
    private int inputI;
    private int inputJ;
    private int inputL;
    private int inputO;
    private int inputS;
    private int inputZ;
    private int inputT;

    private int minValue = 0;
    private int maxValue = 4;
    ArrayList<TetrisShape> toSolve = new ArrayList<>();

    private void saveValues() {
        try {
            inputI = Integer.parseInt(getInt(I_input));
            inputJ = Integer.parseInt(getInt(J_input));
            inputL = Integer.parseInt(getInt(L_input));
            inputO = Integer.parseInt(getInt(O_input));
            inputS = Integer.parseInt(getInt(S_input));
            inputZ = Integer.parseInt(getInt(Z_input));
            inputT = Integer.parseInt(getInt(T_input));

            toSolve.clear();

            for (int i = 0; i < inputI; i++)
                toSolve.add(new IShape());
            for (int i = 0; i < inputJ; i++)
                toSolve.add(new JShape());
            for (int i = 0; i < inputL; i++)
                toSolve.add(new LShape());
            for (int i = 0; i < inputO; i++)
                toSolve.add(new OShape());
            for (int i = 0; i < inputS; i++)
                toSolve.add(new SShape());
            for (int i = 0; i < inputZ; i++)
                toSolve.add(new ZShape());
            for (int i = 0; i < inputT; i++)
                toSolve.add(new TShape());

        } catch (Exception e) {
        }
    }

    private String getInt(TextField tf) {
        String s = tf.getText();
        if (s.isEmpty())
            return "0";
        return s;
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
        Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
        int delay = 250;

        for (int value = 0; value < colors.length; value++) {
            int finalValue = value;

            Thread colorThread = new Thread(() -> {
                try {
                    Thread.sleep(finalValue * delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    for (int i = 0; i < matrix.length; i++) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            if (matrix[i][j] == finalValue) {
                                rectangles[i][j].setFill(colors[finalValue]);
                            }
                        }
                    }
                });
            });

            colorThread.start();
        }
    }

    @FXML
    void setValues(MouseEvent event) {
        saveValues();
        
        if(toSolve.size() != 4){
            Alert x = new Alert(Alert.AlertType.ERROR);
            x.setHeaderText(null);
            x.setContentText("Please choose 4 shapes only");
            x.show();
        }
        else{
            ThreadMaker tm = new ThreadMaker(toSolve);
            try {
                Thread.sleep(1000);
                System.out.println("number of solutions " + tm.solutionList.size());
                int[][] arr = tm.solutionList.get(0).getBoard();
                setColorsForMatrix(arr);
    
            } catch (Exception e) {
                Alert x = new Alert(Alert.AlertType.WARNING);
                x.setHeaderText(null);
                x.setContentText("No Solution found");
                x.show();
            }
        }
    }

    public void initialize() {
        solutionButton.setVisible(false);
        setDefaultValues();
        setMinMaxValues(I_input);
        setMinMaxValues(J_input);
        setMinMaxValues(L_input);
        setMinMaxValues(O_input);
        setMinMaxValues(S_input);
        setMinMaxValues(Z_input);
        setMinMaxValues(T_input);
        this.rectangles = new Rectangle[4][4];

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

    private void setMinMaxValues(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                try {
                    int value = Integer.parseInt(newValue);
                    if (value < minValue || value > maxValue) {
                        textField.setText(oldValue);
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
