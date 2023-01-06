module com.ftj.demo_quixo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.ftj.demo_quixo to javafx.fxml;
    exports com.ftj.demo_quixo;
    exports com.ftj.demo_quixo.model;
    opens com.ftj.demo_quixo.model to javafx.fxml;
    exports com.ftj.demo_quixo.controller;
    opens com.ftj.demo_quixo.controller to javafx.fxml;

}