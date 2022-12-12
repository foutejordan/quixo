package com.quixo.projet_quixo.Model;

public class Player {
    String nonJoeur;
    Pion pion;

    public boolean getMonTourdeJouer() {
        return monTourdeJouer;
    }

    public void setMonTourdeJouer(boolean monTourdeJouer) {
        this.monTourdeJouer = monTourdeJouer;
    }

    private boolean monTourdeJouer;

    public Player(String nonJoeur, Pion pion) {
        this.nonJoeur = nonJoeur;
        this.pion = pion;
    }

    public void setNonJoeur(String nonJoeur) {
        this.nonJoeur = nonJoeur;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }

    public String getNonJoeur() {
        return nonJoeur;
    }

    public Pion getPion() {
        return pion;
    }
}
