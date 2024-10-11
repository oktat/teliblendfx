package lan.zold.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookSource {

  private Database database;

  public BookSource(Database database) {
    this.database = database;
  }

  public ArrayList<Book> getBooks() {
    try {
      return tryGetBooks();
    } catch (SQLException e) {
      System.err.println("Hiba! A könyvek letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
    return null;
  }

  private ArrayList<Book> tryGetBooks() throws SQLException {
    ArrayList<Book> books = new ArrayList<>();
    Connection conn = database.connect();
    String sql = "SELECT * FROM books";
    Statement statement = conn.createStatement();
    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      int id = rs.getInt("id");
      String title = rs.getString("title");
      String author = rs.getString("author");
      int user_id = rs.getInt("user_id");
      books.add(new Book(id, title, author, user_id));
    }
    conn.close();
    return books;
  }

  public Book getBook(int id) {
    try {
      return tryGetBook(id);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyvek letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
    return null;
  }

  private Book tryGetBook(int id) throws SQLException {
    Connection conn = database.connect();
    String sql = "SELECT * FROM books WHERE id = " + id;
    Statement statement = conn.createStatement();
    ResultSet rs = statement.executeQuery(sql);
    if (rs.next()) {
      String title = rs.getString("title");
      String author = rs.getString("author");
      int user_id = rs.getInt("user_id");
      conn.close();
      return new Book(id, title, author, user_id);
    }
    conn.close();
    return null;
  }

  public Book getBookByUserid(int user_id) {
    try {
      return tryGetBookByUserid(user_id);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyvek letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
    return null;
  }

  private Book tryGetBookByUserid(int user_id) throws SQLException {
    Connection conn = database.connect();
    String sql = "SELECT * FROM books WHERE user_id = " + user_id;
    Statement statement = conn.createStatement();
    ResultSet rs = statement.executeQuery(sql);
    if (rs.next()) {
      int id = rs.getInt("id");
      String title = rs.getString("title");
      String author = rs.getString("author");
      conn.close();
      return new Book(id, title, author, user_id);
    }
    conn.close();
    return null;
  }

  public void addBook(Book book) {
    try {
      tryAddBook(book);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyvek letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  private void tryAddBook(Book book) throws SQLException {
    Connection conn = database.connect();
    String sql = """
        insert into books 
        (title, author) 
        values (?, ?)
        """;
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, book.getTitle());
    ps.setString(2, book.getAuthor());
    ps.executeUpdate();
    conn.close();
  }

  public void deleteBook(Book book) {
    try {
      tryDeleteBook(book);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyvek letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  private void tryDeleteBook(Book book) throws SQLException {
    Connection conn = database.connect();
    String sql = "DELETE FROM books WHERE id = " + book.getId();
    Statement st = conn.createStatement();
    st.executeUpdate(sql);
    conn.close();
  }

  public void updateBook(Book book) {
    try {
      tryUpdateBook(book);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyv frissítése sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  private void tryUpdateBook(Book book) throws SQLException {
    Connection conn = database.connect();
    String sql = """
        update books set
        title = ?, 
        author = ?
        where id = ?
        """;
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, book.getTitle());
    ps.setString(2, book.getAuthor());
    ps.setInt(3, book.getId());
    ps.executeUpdate();
    conn.close();
  }

  public void updateBookUser(int book_id, int user_id) {
    try {
      tryUpdateBookUser(book_id, user_id);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyv frissít JSName sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  private void tryUpdateBookUser(int book_id, int user_id) throws SQLException {
    Connection conn = database.connect();
    String sql = """
        update books set
        user_id = ?
        where id = ?
        """;
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, user_id);
    ps.setInt(2, book_id);
    ps.executeUpdate();
    conn.close();
  }

  public boolean returnBook(int book_id) {
    try {
      return tryReturnBook(book_id);
    } catch (SQLException e) {
      System.err.println("Hiba! A könyv visszaadása sikertelen!");
      System.err.println(e.getMessage());
    }
    return false;
  }

  private boolean tryReturnBook(int book_id) throws SQLException {
    Connection conn = database.connect();
    String sql = """
        update books set
        user_id = NULL
        where id = ?;
        """;
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setInt(1, book_id);    
    statement.executeUpdate();
    conn.close();
    return true;
  }
}
