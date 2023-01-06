package com.ftj.demo_quixo.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class InsertionContext {

    private StrategyInsertion strategyInsertion;

    public InsertionContext(StrategyInsertion strategyInsertion){
        this.strategyInsertion = strategyInsertion;
    }

    public void getInsertion(SimpleObjectProperty<Pion>[][] plateau , Position pos, Player joueurCourrant) {
        strategyInsertion.insertion(plateau, pos, joueurCourrant);
    }
}
