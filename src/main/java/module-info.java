module com.quixo.projet_quixo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.quixo.projet_quixo to javafx.fxml;
    exports com.quixo.projet_quixo;
}