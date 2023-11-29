package com.threadedsquaresolver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



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


    @FXML
    private TextField Z_input;

    @FXML
    private Button solve;
    private String inputI;
    private String inputJ;
    private String inputL;
    private String inputO;
    private String inputS;
    private String inputT;
    private String inputZ;

    private int minValue = 0;
    private int maxValue = 4;


    private void saveValues() {
        inputI = I_input.getText();
        inputJ = J_input.getText();
        inputL = L_input.getText();
        inputO = O_input.getText();
        inputS = S_input.getText();
        inputT = T_input.getText();
        inputZ = Z_input.getText();
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

    public void initialize() {
        setDefaultValues();
        setMinMaxValues(I_input);
        setMinMaxValues(J_input);
        setMinMaxValues(L_input);
        setMinMaxValues(O_input);
        setMinMaxValues(S_input);
        setMinMaxValues(T_input);
        setMinMaxValues(Z_input);
    }



    public String getInputI() {
        return inputI;
    }

    public String getInputJ() {
        return inputJ;
    }

    public String getInputL() {
        return inputL;
    }
    public String getInputO() {
        return inputO;
    }

    public String getInputS() {
        return inputS;
    }

    public String getInputT() {
        return inputT;
    }

    public String getInputZ() {
        return inputZ;
    }
    @FXML
    void setValues(MouseEvent event) {
        saveValues();
        String inputIValue = getInputI();
        System.out.println("Input I value from Controller: " + inputIValue);
    }
    private void setMinMaxValues(TextField textField) {
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
