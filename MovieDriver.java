import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MovieDriver {
       // 
   static String dbURL = "jdbc:mysql://localhost:3306/omdb";
   static String username = "root";
   static String password = "";


       public static void main(String[] args) {
              
              
              try {
              
                  Connection conn = DriverManager.getConnection(dbURL, username, password);
                  
                  String sql = "SELECT * FROM Movies";
                  
                  Statement statement = conn.createStatement();
                  ResultSet result = statement.executeQuery(sql);
                   
                  int count = 0;
                   
                  while (result.next()){
                      String english_name = result.getString("english_name");
                      int movie_id = result.getInt("movie_id");
                      String native_name = result.getString("native_name");
                      int year_made = result.getInt("year_made");
                   
                      String output = "Movie #%d: %s - %d - %s - %d";
                      System.out.println(String.format(output, ++count, english_name, movie_id, native_name, year_made));
                  }
                  readMovie(conn);
                  createMovie(conn);
                  updateMovie(conn);
                  deleteMovie(conn);
                  
               
                  if (conn != null) {
                      System.out.println("Connected");
                  }
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
       }
       
      public static void createMovie(Connection conn) {
              
              try {  
               String sql = "INSERT INTO Movies (english_name, native_name, year_made) VALUES (?, ?, ?)";
              
               PreparedStatement statement = conn.prepareStatement(sql);
              statement.setString(1, "Popeye");
              statement.setString(2, "Popeye");
              statement.setInt(3, 2009);
              
                
               int rowsInserted = statement.executeUpdate();
              if (rowsInserted > 0) {
                   System.out.println("A new movie was inserted successfully!");
              }
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
              
               
               
       
              
               
       }
       
      public static void deleteMovie(Connection conn) {
              try {
                     
                     
                     String sql = "DELETE FROM Movies WHERE english_name=?";
                     
                     PreparedStatement statement = conn.prepareStatement(sql);
                     statement.setString(1, "Popeye");
                     
                     int rowsDeleted = statement.executeUpdate();
                     if (rowsDeleted > 0) {
                         System.out.println("A movie was deleted successfully!");
                     }
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
       }
       
      public static void updateMovie(Connection conn) {
              try {
                     
                     String sql = "UPDATE Movies SET english_name=?, native_name=?, year_made=? WHERE english_name=?";
                     
                     PreparedStatement statement = conn.prepareStatement(sql);
                     statement.setString(1, "PeterPan");
                     statement.setString(2, "NativeName");
                     statement.setInt(3, 2020);
                     statement.setString(4, "Popeye");
                     
                     int rowsUpdated = statement.executeUpdate();
                     if (rowsUpdated > 0) {
                         System.out.println("An existing movie was updated successfully!");
                     }
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
       }
       
      public static void readMovie(Connection conn) {
    	   try {
               String sql = "SELECT * FROM Movies where movie_id = 291";
                 
                 Statement statement = conn.createStatement();
                 ResultSet result = statement.executeQuery(sql);
                  
                 int count = 0;
                  
                 while (result.next()){
                     String english_name = result.getString("english_name");
                     String native_name = result.getString("native_name");
                     int year_made = result.getInt("year_made");
                  
                     String output = "Selected Movie with ID 291 : %s - %d - %s - %d";
                     System.out.println(String.format(output, english_name, 100, native_name, year_made));
                 }
                 
                 
                 
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
    	   try {
              // String sql = "SELECT * FROM movie_data";
               
               String sql = "SELECT * FROM movie_data where movie_id = 291";
               
               
                 Statement statement = conn.createStatement();
                 ResultSet result = statement.executeQuery(sql);
                  
                 int count = 0;
                  
                 while (result.next()){
                     String country = result.getString("country");
                     String genre = result.getString("genre");
                     String language = result.getString("language");
                     String plot = result.getString("plot");
                     String tag_line = result.getString("tag_line");
                     int movie_id = result.getInt("movie_id");
                     String output = "Movie data: country %s - genre %s - language %s -plot %s - tag_line %s -- movie_id %d";
                     System.out.println(String.format(output, country , genre , language , plot , tag_line, movie_id));
                 }
                 
                 
                 
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }

       }

}
