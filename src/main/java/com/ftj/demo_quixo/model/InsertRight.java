package com.ftj.demo_quixo.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class InsertRight implements StrategyInsertion{

    @Override
    public void insertion(SimpleObjectProperty<Pion>[][] plateau, Position pos, Player joueurCourrant) {
        if (pos.getX() == -1) throw new IllegalStateException("You have first to choose a piece to move!");
        if (pos.getX() == 4) throw new IllegalArgumentException("You cannot put the piece where it was!");
        int id = pos.getY();;
        for (int x = pos.getX(); x < 4 ; ++x) {
            plateau[id][x].set(plateau[id][x+1].get());
        }
        plateau[id][4].set(joueurCourrant.getPion());
    }
}
