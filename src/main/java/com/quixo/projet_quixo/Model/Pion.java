package com.quixo.projet_quixo.Model;

public class Pion {
    int etat;

    private Position position = new Position(-1,-1);

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Pion(int etat, Position position) {
        this.etat = etat;
        this.position = position;
    }
}
