package lan.zold.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserSource {
  Database database;

  public UserSource(Database database) {
    this.database = database;
  }
  public ArrayList<User> getUsers()  {
    try {
      return tryGetUsers();
    } catch (SQLException e) {
      System.err.println("Hiba! A felhasználók letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
    return null;
  }

  public ArrayList<User> tryGetUsers() throws SQLException {
    ArrayList<User> users = new ArrayList<>();
    Connection conn = database.connect();
    String sql = "SELECT * FROM users";
    Statement statement = conn.createStatement();
    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      int id = rs.getInt("id");
      String username = rs.getString("username");
      users.add(new User(id, username));
    }
    conn.close();
    return users;
  }

  public void addUser(User user) {
    try {
      tryAddUser(user);
    } catch (SQLException e) {
      System.err.println("Hiba! A felhasználók letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  public void tryAddUser(User user) throws SQLException {
    Connection conn = database.connect();
    String sql = "INSERT INTO users (username) VALUES ('" + user.getUsername() + "')";
    Statement statement = conn.createStatement();
    statement.executeUpdate(sql);
    conn.close();
  }

  public void deleteUser(User user) {
    try {
      tryDeleteUser(user);
    } catch (SQLException e) {
      System.err.println("Hiba! A felhasználók letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  public void tryDeleteUser(User user) throws SQLException {
    Connection conn = database.connect();
    String sql = "DELETE FROM users WHERE id = " + user.getId();
    Statement statement = conn.createStatement();
    statement.executeUpdate(sql);
    conn.close();
  }

  public void updateUser(User user) {
    try {
      tryUpdateUser(user);
    } catch (SQLException e) {
      System.err.println("Hiba! A felhasználók letöltése sikertelen.");
      System.err.println(e.getMessage());
    }
  }

  public void tryUpdateUser(User user) throws SQLException {
    Connection conn = database.connect();
    String sql = "UPDATE users SET username = '" + user.getUsername() + "' WHERE id = " + user.getId();
    Statement statement = conn.createStatement();
    statement.executeUpdate(sql);
    conn.close();
  }
}
