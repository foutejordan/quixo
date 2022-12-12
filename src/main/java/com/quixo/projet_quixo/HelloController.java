package com.quixo.projet_quixo;

import com.quixo.projet_quixo.Model.Plateau;
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

    private Plateau plateau;
    private String etat;
    private int x;
    private int y;

    @FXML
    private Button btn00; //
    @FXML
    private Button btn01; //
    @FXML
    private Button btn02; //
    @FXML
    private Button btn03; //
    @FXML
    private Button btn04; //
    @FXML
    private Button btn10; //
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button btn14; // Bord
    @FXML
    private Button btn20; // Bord
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Button btn23;
    @FXML
    private Button btn24; // Bord
    @FXML
    private Button btn30; // Bord
    @FXML
    private Button btn31;
    @FXML
    private Button btn32;
    @FXML
    private Button btn33;
    @FXML
    private Button btn34; //
    @FXML
    private Button btn40; //
    @FXML
    private Button btn41; //
    @FXML
    private Button btn42; //
    @FXML
    private Button btn43; //
    @FXML
    private Button btn44; //

    private Button[] buttons = new Button[25];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        plateau = new Plateau();
        initialiseBtn();

        System.out.println(plateau.pionVide.getEtat());

    }

    private void initialiseBtn() {
        //Grille
        buttons[0]=btn00;
        buttons[1]=btn01;
        buttons[2]=btn02;
        buttons[3]=btn03;
        buttons[4]=btn04;
        buttons[5]=btn10;
        buttons[6]=btn11;
        buttons[7]=btn12;
        buttons[8]=btn13;
        buttons[9]=btn14;
        buttons[10]=btn20;
        buttons[11]=btn21;
        buttons[12]=btn22;
        buttons[13]=btn23;
        buttons[14]=btn24;
        buttons[15]=btn30;
        buttons[16]=btn31;
        buttons[17]=btn32;
        buttons[18]=btn33;
        buttons[19]=btn34;
        buttons[20]=btn40;
        buttons[21]=btn41;
        buttons[22]=btn42;
        buttons[23]=btn43;
        buttons[24]=btn44;

        for (int i = 0; i < 25; i++) {
            buttons[i].setText("");
        }

    }


    private Button[] bords = new Button[25];
    public void onClick(Event event) {
        Button button = (Button) event.getSource();
        etat = button.getText();
//        int idClicked = Integer.parseInt(button.getId().split("_")[1]);
        System.out.println(etat);


    }

    public void onPress(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        x = GridPane.getColumnIndex(clickedNode) != null ? GridPane.getColumnIndex(clickedNode) : 0;
        y = GridPane.getRowIndex(clickedNode) != null ? GridPane.getRowIndex(clickedNode) : 0;

        System.out.println(x);
        System.out.println(y);
    }

    public void onNouvelle(ActionEvent actionEvent) {
    }

    public void onQuitter(ActionEvent actionEvent) {
    }
}