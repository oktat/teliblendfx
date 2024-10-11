package lan.zold.models;

public class Book {

  private int id;
  private String title;
  private String author;
  private int user_id;

  public Book(int id, String title, String author, int user_id) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.user_id = user_id;
  }

  public Book(int id, String title, String author) {
    this.id = id;
    this.title = title;
    this.author = author;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getUser_id() {
    return user_id;
  }

  @Override
  public String toString() {
    return title;
  }
}
