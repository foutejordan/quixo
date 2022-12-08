package com.quixo.projet_quixo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    Random random = new Random();

    //AdversarialSearchTicTacToe ticTacToeAI = new AdversarialSearchTicTacToe();



    ArrayList<Button> pions;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private Button[] bords = new Button[25];
    public void onClick(Event event) {
        Button button = (Button) event.getSource();
        button.setText("F");
        int idClicked = Integer.parseInt(button.getId().split("_")[1]);

    }

    public void onPress(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        int x = GridPane.getColumnIndex(clickedNode) != null ? GridPane.getColumnIndex(clickedNode) : 0;
        int y = GridPane.getRowIndex(clickedNode) != null ? GridPane.getRowIndex(clickedNode) : 0;

        System.out.println(x);
        System.out.println(y);
    }

    public void onNouvelle(ActionEvent actionEvent) {
    }

    public void onQuitter(ActionEvent actionEvent) {
    }
}