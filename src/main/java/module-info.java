module lan.zold {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens lan.zold to javafx.fxml;
    exports lan.zold;
}
