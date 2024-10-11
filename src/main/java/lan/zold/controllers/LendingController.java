package lan.zold.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lan.zold.App;
import lan.zold.models.Book;
import lan.zold.models.BookSource;
import lan.zold.models.Sqlite;
import lan.zold.models.User;
import lan.zold.models.UserSource;

public class LendingController {

    private UserSource userSource = new UserSource(new Sqlite());
    private BookSource bookSource = new BookSource(new Sqlite());
    private User actUser;
    private Book userBook;

    @FXML
    private ComboBox<User> userCombobox;

    @FXML
    private ComboBox<Book> bookCombobox;

    @FXML
    private TextField borrowedAuthorField;

    @FXML
    private TextField borrowedTitleField;

    @FXML
    private TextField currentTitleField;

    @FXML
    private TextField currentAuthorField;

    @FXML
    void initialize() {
        userCombobox.getItems().addAll(userSource.getUsers());
        bookCombobox.getItems().addAll(bookSource.getBooks());
    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        App.setRoot("mainScene");
    }

    @FXML
    void onClickUserCombobox(ActionEvent event) {
        borrowedTitleField.setText("");
        borrowedAuthorField.setText("");
        actUser = userCombobox.getSelectionModel().getSelectedItem();
        
        userBook = bookSource.getBookByUserid(actUser.getId());
        if (userBook != null) {
            System.out.println(userBook.getTitle());
            borrowedTitleField.setText(userBook.getTitle());
            borrowedAuthorField.setText(userBook.getAuthor());
        }
    }    

    @FXML
    void onClickBookCombobox(ActionEvent event) {
        Book book = bookCombobox.getSelectionModel().getSelectedItem();
        currentAuthorField.setText(book.getAuthor());
        currentTitleField.setText(book.getTitle());
        
    }

    @FXML
    void onClickLoanButton(ActionEvent event) {
        if (userCombobox.getSelectionModel().isEmpty() || bookCombobox.getSelectionModel().isEmpty()) {
            System.err.println("Hiba! Nincs kiválsztva felhasználó vagy könyv!");
            return;
        }
        borrowedTitleField.setText(currentTitleField.getText());
        borrowedAuthorField.setText(currentAuthorField.getText());


        bookSource.updateBookUser(
            userCombobox.getSelectionModel().getSelectedItem().getId(),
            bookCombobox.getSelectionModel().getSelectedItem().getId()
        );


    }

    @FXML
    void onClickReturnButton(ActionEvent event) {
        if (userCombobox.getSelectionModel().isEmpty()) {
            System.err.println("Hiba! Nincs kiválsztva felhasználó!");
            return;
        }

        if (userBook != null) {
            System.out.println(userBook.getTitle());
            borrowedTitleField.setText("");
            borrowedAuthorField.setText("");
            System.out.println(userBook.getId());
            bookSource.returnBook(userBook.getId());
        }else {
            System.err.println("Hiba! Nincs kiválsztva könyv!");
        }

    }

}

