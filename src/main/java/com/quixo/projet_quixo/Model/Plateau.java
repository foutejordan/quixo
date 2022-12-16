package com.quixo.projet_quixo.Model;

import java.util.ArrayList;

public class Plateau {
    int dim = 5;

    public Pion pionVide = new Pion(0, new Position(-1,-1));
    public Pion pionX = new Pion(1, new Position(-1,-1));
    public Pion pionO = new Pion(-1, new Position(-1,-1));

    public Player joueur1 = new Player("jordan", pionX);
    public Player joueur2 = new Player("Kevin", pionO);
    public Player currentPlayer = new Player("", pionVide);

    Pion chosen = pionVide;


    public void setPlateau(Pion[][] plateau) {
        this.plateau = plateau;
    }

    public Pion[][] plateau;

    private int tourDuJoeur = 1; // true means that it's 2's turn to play

    public Plateau() {
        plateau = new Pion[5][5];

        for (int x=0; x<5; x++){
            for (int y=0; y<5; y++) {
                plateau[x][y] = pionVide;
            }
        }

    }

    public boolean isChooseAllowed(int etat, int x, int y) {
        Position pos = new Position(x,y);
        if(pionCentre(pos)) {
            return false;
        }
            //System.out.println(currentPlayer.getPion().etat);
            if (etat == currentPlayer.getPion().etat || etat == pionVide.etat) {
                return true;
            }else{
                System.out.println("Ce n'est pas a vous de jouer");
                return false;
            }

    }
    public boolean choosePion(int etat, int x, int y) {
        if (isChooseAllowed(etat,x,y)) {
            System.out.println(etat);
            //chosen.setEtat(etat);
            //chosen.setPosition(new Position(x,y));
            chosen = new Pion(etat,new Position(x,y));
            return true;
        } else{
            System.out.println("Vous ne pouvez pas deplacer cette piece");
            return false;
        }
    }

    /** Cancels the choice of piece to move */
    public void cancelChoice() {
        chosen.setPosition(new Position(-1,-1));
    }

    public boolean canPoseDuPion(int x, int y){
        if (pionCentre(new Position(x,y)))
        {
            System.out.println("Vous ne pouvez pas deposer cette piece ici");
            return false;
        }
        // x=0, y=4
        // chosen.x == 0 chosen.y = 0
        System.out.println(x);
        return (x == chosen.getPosition().x && y == 4) ||
                (x == 4 && y == chosen.getPosition().y) ||
                (x == chosen.getPosition().x && y == 0) ||
                (x == 0 && y == chosen.getPosition().y);
    }

    public boolean placePion(Pion arrivalPoint){

        if(canPoseDuPion(arrivalPoint.getPosition().x, arrivalPoint.getPosition().y)){
            Pion pionPlateauf = this.plateau[arrivalPoint.getPosition().x][arrivalPoint.getPosition().y];
            Pion saveFinalPiont = new Pion(pionPlateauf.etat, new Position(pionPlateauf.getPosition().x,pionPlateauf.getPosition().y));
            System.out.println("arival pos");
            System.out.println(arrivalPoint.getPosition().x);
            System.out.println(arrivalPoint.getPosition().y);

            this.plateau[arrivalPoint.getPosition().x][arrivalPoint.getPosition().y] = new Pion(chosen.etat, chosen.getPosition());
            System.out.println("displaying before...");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.println(plateau[i][j].getEtat());
                }
            }

            if(isMovingLine(arrivalPoint)){
                System.out.println("moving line");

                if(chosen.getPosition().y < arrivalPoint.getPosition().y){
                    // Deplacement a droite
                    System.out.println(chosen.getPosition().x);
                    for (int j = chosen.getPosition().y + 1; j <= arrivalPoint.getPosition().y; j++) {
                        this.plateau[chosen.getPosition().x][j-1] = this.plateau[chosen.getPosition().x][j];
                        if(j == arrivalPoint.getPosition().y){
                            this.plateau[chosen.getPosition().x][j-1] = saveFinalPiont;
                        }
                    }
                    this.plateau[chosen.getPosition().x][arrivalPoint.getPosition().y-1] = saveFinalPiont;

                }else{
                    for (int j = chosen.getPosition().y - 1; j <= arrivalPoint.getPosition().y; j--) {
                        plateau[chosen.getPosition().x][j] = plateau[chosen.getPosition().x][j+1];
                        if(j == arrivalPoint.getPosition().y){
                            plateau[chosen.getPosition().x][j+1] = saveFinalPiont;
                        }
                    }
                }

            }else {
                System.out.println("moving column");
                if(chosen.getPosition().x < arrivalPoint.getPosition().x){
                    // Deplacement en bas
                    for (int i = chosen.getPosition().x + 1; i <= arrivalPoint.getPosition().x; i++) {
                        plateau[i-1][chosen.getPosition().y] = plateau[i][chosen.getPosition().y];
                        //
                        if(i == arrivalPoint.getPosition().x){
                            plateau[i-1][chosen.getPosition().y] = saveFinalPiont;
                        }
                    }
                }else{
                    for (int i = chosen.getPosition().x - 1; i <= arrivalPoint.getPosition().x; i--) {
                        plateau[i][chosen.getPosition().y] = plateau[i+1][chosen.getPosition().y];
                        if(i == arrivalPoint.getPosition().x){
                            plateau[i+1][chosen.getPosition().y] = saveFinalPiont;
                        }
                    }
                }
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(plateau[i][j].getEtat());
                    }
                }
            }
            return true;
        }else{
            return false;
        }

    }

    private boolean isMovingLine(Pion arrivalPoint){
        int diffX = arrivalPoint.getPosition().x - chosen.getPosition().x;

        return diffX == 0;

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
