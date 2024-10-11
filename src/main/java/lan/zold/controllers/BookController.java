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
import lan.zold.models.Book;
import lan.zold.models.BookSource;
import lan.zold.models.Sqlite;

public class BookController {


    private BookSource bookSource = new BookSource(new Sqlite());

    @FXML
    private TableColumn<Book, Integer> idCol;    

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, Integer> useridCol;


    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField useridField;


    @FXML
    private TableView<Book> bookTable;


    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        useridCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        loadBooks();
    }


    private void loadBooks() {
        ArrayList<Book> books = bookSource.getBooks();
        ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
        bookTable.setItems(observableBooks);
        idField.clear();
        titleField.clear();
        authorField.clear();
        useridField.clear();
    }


    @FXML
    void onClickAddButton(ActionEvent event) {
        bookSource.addBook(
            new Book(
                0,
                titleField.getText(),
                authorField.getText()
            )
        );
        loadBooks();
    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        App.setRoot("mainScene");
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        bookSource.deleteBook(bookTable.getSelectionModel().getSelectedItem());
        loadBooks();
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        Book book = new Book(
            Integer.parseInt(idField.getText()),
            titleField.getText(),
            authorField.getText()
        );
        bookSource.updateBook(book);
        loadBooks();
    }

    @FXML
    void onClickMouseTable(MouseEvent event) {
        if(event.getClickCount() == 2) {
            Book book = bookTable.getSelectionModel().getSelectedItem();
            idField.setText(Integer.toString(book.getId()));
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            useridField.setText(Integer.toString(book.getUser_id()));
        }
    }    

}
