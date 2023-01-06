package com.ftj.demo_quixo.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class InsertBottom implements StrategyInsertion{

    @Override
    public void insertion(SimpleObjectProperty<Pion>[][] plateau, Position pos, Player joueurCourrant) {
        if (pos.getX() == -1) throw new IllegalStateException("You have first to choose a piece to move!");
        if (pos.getY() == 4) throw new IllegalArgumentException("You cannot put the piece where it was!");
        int id = pos.getX();;
        for (int y = pos.getY(); y <4 ; ++y) {
            plateau[y][id].set(plateau[y+1][id].get());
        }
        plateau[4][id].set(joueurCourrant.getPion());
    }
}
