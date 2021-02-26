
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MovieDriver {

       public static void main(String[] args) {
              // 
              String dbURL = "jdbc:mysql://localhost:3306/omdb";
              String username = "root";
              String password = "";
              
              try {
              
                  Connection conn = DriverManager.getConnection(dbURL, username, password);
                  
                  String sql = "SELECT * FROM Movies";
                  
                  Statement statement = conn.createStatement();
                  ResultSet result = statement.executeQuery(sql);
                   
                  int count = 0;
                   
                  while (result.next()){
                      String english_name = result.getString("english_name");
                      int movie_id = result.getInt("movie_id");
                      int year_made = result.getInt("year_made");
                   
                      String output = "Movie #%d: %s - %d - %s - %d";
                      System.out.println(String.format(output, ++count, english_name, movie_id, year_made));
                  }
              
                  if (conn != null) {
                      System.out.println("Connected");
                  }
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
       }

}
