package lan.zold;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<?> userTable;

    @FXML
    void onClickAddButton(ActionEvent event) {

    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        App.setRoot("mainScene");
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {

    }

    @FXML
    void onClickModifyButton(ActionEvent event) {

    }

}
