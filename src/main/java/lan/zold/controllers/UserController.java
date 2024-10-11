package lan.zold.controllers;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lan.zold.App;
import lan.zold.models.Sqlite;
import lan.zold.models.User;
import lan.zold.models.UserSource;

public class UserController {

    private UserSource userSource = new UserSource(new Sqlite());

    @FXML
    private TableColumn<User, Integer> idCol;

    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<User> userTable;

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        loadUsers();
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        User user = new User(0, nameField.getText());
        userSource.addUser(user);
        loadUsers();
    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        App.setRoot("mainScene");
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        userSource.deleteUser(userTable.getSelectionModel().getSelectedItem());
        loadUsers();
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        User user = new User(Integer.parseInt(idField.getText()), nameField.getText());
        userSource.updateUser(user);
        loadUsers();
    }

    private void loadUsers() {
        ArrayList<User> users = userSource.getUsers();
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        userTable.setItems(observableUsers);
        idField.clear();
        nameField.clear();
    }

    @FXML
    void onClickMouseTableView(MouseEvent event) {
        if(event.getClickCount() == 2) {
            User user = userTable.getSelectionModel().getSelectedItem();
            idField.setText(Integer.toString(user.getId()));
            nameField.setText(user.getUsername());
        }
    }    

}
