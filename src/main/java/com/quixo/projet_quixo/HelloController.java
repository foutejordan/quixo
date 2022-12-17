package com.quixo.projet_quixo;

import com.quixo.projet_quixo.Model.Pion;
import com.quixo.projet_quixo.Model.Plateau;
import com.quixo.projet_quixo.Model.Player;
import com.quixo.projet_quixo.Model.Position;
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
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    Random random = new Random();

    //AdversarialSearchTicTacToe ticTacToeAI = new AdversarialSearchTicTacToe();

    private Plateau plateauModel;
    private int x;
    private int y;

    private boolean isClickForChoose;
    private int tourDujoueur;
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

    Player joueur1;
    Player joueur2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        plateauModel = new Plateau();
        this.mettreAjourLaGrille();

        joueur1 = plateauModel.joueur1;
        joueur2 = plateauModel.joueur2;

        this.isClickForChoose = true;
        plateauModel.currentPlayer = joueur1;
        System.out.println("Au tour du joueur 1 de jouer");
        this.tourDujoueur = 1;
        initialiseBtn();

        //System.out.println(plateau.pionVide.getEtat());

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

/*        for (int i = 0; i < 25; i++) {
            buttons[i].setText("");
        }*/
    }


    private Button[] bords = new Button[25];

    public int convertToInt(String val) {
        if (Objects.equals(val, "X"))
            return 1;
        else if (Objects.equals(val, "O"))
            return -1;
        else return 0;
    }
    public String convertToString(int val) {
        if (val == 1)
            return "X";
        else if (val == -1)
            return "O";
        else return "";
    }
    public void onPress(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        y = GridPane.getColumnIndex(clickedNode) != null ? GridPane.getColumnIndex(clickedNode) : 0;
        x = GridPane.getRowIndex(clickedNode) != null ? GridPane.getRowIndex(clickedNode) : 0;

        Button button = (Button) mouseEvent.getSource();
        String etat = button.getText();

        if (isClickForChoose) {
            if (Objects.equals(etat, "")) {
                etat = convertToString(plateauModel.currentPlayer.getPion().getEtat());
            }
           /* System.out.println("print in controller before choosing");
            System.out.println(x);
            System.out.println(y);*/
            if(plateauModel.choosePion(convertToInt(etat), x, y)){
                //mettreAjourLaGrille();
                this.isClickForChoose = false;
                System.out.println("choose");
            }else{
                System.out.println("Veuillez reesayer");
            }
        }else{
            if (plateauModel.placePion(new Pion(convertToInt(etat), new Position(x,y)))){
                System.out.println("place");
                this.isClickForChoose = true;
                this.mettreAjourLaGrille();
                changePlayer();
            }else{
                System.out.println("Veuillez reesayer");
            }
        }
    }
    public void mettreAjourLaGrille() {

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y< 5; y++) {
                Pion pion = plateauModel.plateau[x][y];
                switch(x) {
                    case 0:
                        switch(y) {
                            case 0: btn00.setText(convertToString(pion.getEtat()));
                                break;
                            case 1: btn01.setText(convertToString(pion.getEtat()));
                                break;
                            case 2: btn02.setText(convertToString(pion.getEtat()));
                                break;
                            case 3: btn03.setText(convertToString(pion.getEtat()));
                                break;
                            case 4: btn04.setText(convertToString(pion.getEtat()));
                                break;
                        }
                    case 1:
                        switch(y) {
                            case 0: btn10.setText(convertToString(pion.getEtat()));
                                break;
                            case 1: btn11.setText(convertToString(pion.getEtat()));
                                break;
                            case 2: btn12.setText(convertToString(pion.getEtat()));
                                break;
                            case 3: btn13.setText(convertToString(pion.getEtat()));
                                break;
                            case 4: btn14.setText(convertToString(pion.getEtat()));
                                break;
                        }
                    case 2:
                        switch(y) {
                            case 0: btn20.setText(convertToString(pion.getEtat()));
                                break;
                            case 1: btn21.setText(convertToString(pion.getEtat()));
                                break;
                            case 2: btn22.setText(convertToString(pion.getEtat()));
                                break;
                            case 3: btn23.setText(convertToString(pion.getEtat()));
                                break;
                            case 4: btn24.setText(convertToString(pion.getEtat()));
                                break;
                        }
                    case 3:
                        switch(y) {
                            case 0: btn30.setText(convertToString(pion.getEtat()));
                                break;
                            case 1: btn31.setText(convertToString(pion.getEtat()));
                                break;
                            case 2: btn32.setText(convertToString(pion.getEtat()));
                                break;
                            case 3: btn33.setText(convertToString(pion.getEtat()));
                                break;
                            case 4: btn34.setText(convertToString(pion.getEtat()));
                                break;
                        }
                    case 4:
                        switch(y) {
                            case 0: btn40.setText(convertToString(pion.getEtat()));
                                break;
                            case 1: btn41.setText(convertToString(pion.getEtat()));
                                break;
                            case 2: btn42.setText(convertToString(pion.getEtat()));
                                break;
                            case 3: btn43.setText(convertToString(pion.getEtat()));
                                break;
                            case 4: btn44.setText(convertToString(pion.getEtat()));
                                break;
                        }
                }
            }
        }
    }

    public void reset() {
        this.isClickForChoose = true;
        plateauModel.currentPlayer = joueur2;
        System.out.println("Au tout du joueur 2 de jouer");
        plateauModel.currentPlayer.setMonTourdeJouer(true);
    }

    public void changePlayer() {
        if (plateauModel.currentPlayer == joueur1){
            plateauModel.currentPlayer = joueur2;
            this.tourDujoueur = 2;
            System.out.println("Au tour du joueur 2 de jouer");
        }else {
            plateauModel.currentPlayer = joueur1;
            this.tourDujoueur = 1;
            System.out.println("Au tour du joueur 1 de jouer");
        }

    }

    public void onNouvelle(ActionEvent actionEvent) {
    }

    public void onQuitter(ActionEvent actionEvent) {
    }
}