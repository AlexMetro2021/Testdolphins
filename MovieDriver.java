import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDriver {

	public static void main(String[] args) {

		try {

			Connection conn = createConnection();
			if (conn != null) {
				System.out.println("Connected");
			}



			readMSTestData(conn);
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	static void readMSTestData(Connection conn) {
		try {

			String sql = "SELECT * FROM `ms_test_data` WHERE 1";
			PreparedStatement statement = conn.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			int year_made;
			String native_name;
			String title;
			int id;

			while (result.next()) {

				id = result.getInt("id");
				year_made = result.getInt("year_made");

				native_name = result.getString("native_name");
				title = result.getString("title");

				System.out.println("year_made " + year_made + ", native_name " + native_name + ", title " + title);
				String status = processMovieSongs(native_name, year_made, title);

				// updateExecutionStatus(conn, id, status);

			}
			
			result.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public static String processMovieSongs(String native_name, int year_made, String title) throws SQLException {

		Connection conn = createConnection();

		String status;

		int movie_id = readMovieIdByNativeNameAndYearMade(conn, native_name, year_made);
		if (movie_id > 0) {
			System.out.println("This movie is already present");
			status = "[2] M ignored";
		} else {
			System.out.println("Movie does not exist in the db, it will be added");

			createMovie(conn, native_name, native_name, year_made);
			movie_id = readMovieIdByNativeNameAndYearMade(conn, native_name, year_made);
			status = "[1] M created";
		}

		int song_id = readSongIdByTitle(conn, title);
		if (song_id > 0) {
			System.out.println("This song is already present");
			status = status + "[4] S ignored";
		} else {
			System.out.println("Song does not exist in the db, it will be added");

			song_id = readMovieIdByNativeNameAndYearMade(conn, native_name, year_made);
			createSong(conn, title);
			song_id = readSongIdByTitle(conn, title);

			status = status + "[3] S created";
		}

		if (readByMovieIdSongId(conn, movie_id, song_id)) {
			System.out.println("The movie_song entry is already present");
			status = status + "[6] MS ignored";
		} else {
			System.out.println("The movie_song entry does not exist in the db, it will be added");

			createMovieSongEntry(conn, movie_id, song_id);
			status = status + "[5] MS created";

		}
		conn.close();
		
		return status;
	}



	static void updateExecutionStatus(int id, String execution_status) {
		try {

			Connection conn = createConnection();
			String sql = "update ms_test_data set execution_status=? where id = ? ";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, execution_status);
			statement.setInt(2, id);

			statement.executeUpdate();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	static void createMovie(Connection conn, String english_name, String native_name, int year_made) {

		try {
			String sql = "INSERT INTO Movies (english_name, native_name, year_made) VALUES (?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, english_name);
			statement.setString(2, native_name);
			statement.setInt(3, year_made);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new movie was inserted successfully!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	static void createSong(Connection conn,  String title) {

		try {
			String sql = "INSERT INTO Songs (title) VALUES (?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, title);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new song was inserted successfully!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	static void createMovieSongEntry(Connection conn, int movie_id, int song_id) {

		try {
			String sql = "INSERT INTO movie_song (movie_id , song_id) VALUES (?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, movie_id);
			statement.setInt(2, song_id);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new movie_song was inserted successfully!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	static int readMovieIdByNativeNameAndYearMade(Connection conn, String native_name, int year_made) {
		try {

			String sql = "SELECT * from Movies where native_name=? and year_made=? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, native_name);
			statement.setInt(2, year_made);

			ResultSet result = statement.executeQuery();
			
			int movie_id;

			if (result.next()) {
				movie_id = result.getInt("movie_id");
				result.close();
				return movie_id;
			} else {
				result.close();
				return -1;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}

	}
	
	

	static int readSongIdByTitle(Connection conn, String title) {
		try {

			String sql = "SELECT * from Songs where title=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, title);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				int res = result.getInt("song_id");
				result.close();
				return res;

			} else {
				result.close();
				return -1;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}

	}

	static boolean readByMovieIdSongId(Connection conn, int movie_id, int song_id) {
		try {

			String sql = "SELECT * from movie_song where movie_id = ? and song_id = ? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, movie_id);
			statement.setInt(2, song_id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				result.close();
				return true;

			} else {
				result.close();
				return false;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

	static void readAllMovies(Connection conn) {
		try {
			String sql = "SELECT * FROM Movies";

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			int count = 0;

			while (result.next()) {
				String english_name = result.getString("english_name");
				int movie_id = result.getInt("movie_id");
				String native_name = result.getString("native_name");
				int year_made = result.getInt("year_made");

				String output = "Movie #%d: %s - %d - %s - %d";
				System.out.println(String.format(output, ++count, english_name, movie_id, native_name, year_made));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private static Connection createConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/omdb";
		String username = "root";
		String password = "";

		Connection conn = DriverManager.getConnection(dbURL, username, password);
		return conn;
	}

}
