package lan.zold;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
