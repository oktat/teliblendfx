module lan.zold {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens lan.zold to javafx.fxml;
    exports lan.zold;

    exports lan.zold.controllers;
    opens lan.zold.controllers to javafx.fxml;

    exports lan.zold.models;
    opens lan.zold.models to javafx.fxml;
}
