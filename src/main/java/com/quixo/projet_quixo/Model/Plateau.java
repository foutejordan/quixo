package com.quixo.projet_quixo.Model;

import java.util.ArrayList;

public class Plateau {
    int dim = 5;

    public Pion pionVide = new Pion(0, new Position(-1,-1));
    public Pion pionX = new Pion(1, new Position(-1,-1));
    public Pion pionO = new Pion(-1, new Position(-1,-1));

    Player joueur1 = new Player("jordan", pionX);
    Player joueur2 = new Player("Kevin", pionO);
    Player currentPlayer = joueur1;

    Pion chosen = pionVide;

    private Pion[][] plateau = new Pion[5][5];

    private int etat_du_jeu = 0; // true means that it's 2's turn to play

    public Plateau() {

        for (int x=0; x<5; ++x)
            for (int y=0; y<5; ++y) {
                plateau[x][y] = pionVide;
            }

    }

    public boolean isChooseAllowed(int etat, int x, int y) {
        Position pos = new Position(x,y);
        if(pionCentre(pos)) {
            return false;
        }
        if (currentPlayer.monTourdeJouer){
            if (etat == currentPlayer.getPion().etat || etat == pionVide.etat) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public void choosePion(int etat, int x, int y) {
        if (isChooseAllowed(etat,x,y)) {
            chosen.setPosition(new Position(x,y));
        } else
            throw new IllegalArgumentException("This piece is not choosable");
    }

    /** Cancels the choice of piece to move */
    public void cancelChoice() {
        chosen.setPosition(new Position(-1,-1));
    }

    public boolean canPoseDuPion(int x, int y){
        if (pionCentre(new Position(x,y)))
            return false;
        if ((x == chosen.position.x && y == chosen.position.y) ||
                (x == 4 && y == chosen.position.y) ||
                (x == chosen.position.x && y == 0) ||
                (x == 0 && y == chosen.position.y)){
            return true;
        }
        return false;
    }


    public void checkLeGagnant() {
        for (int i = 0; i < 5; i++){
            int cpt = 0;
            for(int j = 0; j < 5; j++) {
                cpt += plateau[i][j].etat;
            }
            if (cpt == 5){
                System.out.println("Partie gagnee par "+ joueur1.nonJoeur);
                break;
            } else if (cpt == -5) {
                System.out.println("Partie gagnee par "+ joueur2.nonJoeur);
                break;
            }
        }
        for (int i = 0; i < 5; i++) {
            int cpt = 0;
            for(int j = 0; j < 5; j++) {
                cpt += plateau[j][i].etat;
            }
            if (cpt == 5){
                System.out.println("Partie gagnee par "+ joueur1.nonJoeur);
                break;
            } else if (cpt == -5) {
                System.out.println("Partie gagnee par "+ joueur2.nonJoeur);
                break;
            }
            cpt = 0;
            cpt += plateau[i][i].etat;
            if (cpt == 5){
                System.out.println("Partie gagnee par "+ joueur1.nonJoeur);
                break;
            } else if (cpt == -5) {
                System.out.println("Partie gagnee par "+ joueur2.nonJoeur);
                break;
            }
            cpt = 0;
            cpt += plateau[i][4-i].etat;
            if (cpt == 5){
                System.out.println("Partie gagnee par "+ joueur1.nonJoeur);
                break;
            } else if (cpt == -5) {
                System.out.println("Partie gagnee par "+ joueur2.nonJoeur);
                break;
            }

        }

    }


    public boolean pionCentre(Position pos) {
        for (int i = 1; i < 3; i++){
            for (int j = 1; j <3; j++) {
                if (pos.x == i &&  pos.y== j){
                    return true;
                }
            }
        }
        return false;
    }

}
