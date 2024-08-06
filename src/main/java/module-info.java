module com.example.animeapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.animeapi to javafx.fxml;
    exports com.example.animeapi;
}