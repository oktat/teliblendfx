package lan.zold.models;

import java.sql.Connection;

public interface Database {
  public Connection connect(); 
}
