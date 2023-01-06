package com.ftj.demo_quixo.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public interface StrategyInsertion {

     void insertion(SimpleObjectProperty<Pion>[][] plateau ,  Position pos, Player joueurCourrant);
}
