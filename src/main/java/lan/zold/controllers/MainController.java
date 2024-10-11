package lan.zold.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lan.zold.App;

public class MainController {

    @FXML
    void onClickBooksButton(ActionEvent event) {
        App.setRoot( "booksScene");
    }

    @FXML
    void onClickExitButton(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onClickLendingButton(ActionEvent event) {
        App.setRoot( "lendingScene");
    }

    @FXML
    void onClickUsersButton(ActionEvent event) {
        App.setRoot( "usersScene");
    }

}
