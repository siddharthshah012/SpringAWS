package com.gcit.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.gcit.domain.Author;
import com.mysql.jdbc.Connection;

@Component
public class AuthorDao {

	// @Autowired
	// JdbcTemplate jdbcTemplate;

	public Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://gcitsolutions.cel9vdzh4eep.us-east-1.rds.amazonaws.com:3306/library",
				"gcitsolutions", "gcitsolutions");
		return conn;
	}

	public Author getAuthorById(int authorId) throws SQLException {
		
		String sql = "SELECT * FROM library.tbl_author where authorId=?";
		PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
		prepareStatement.setInt(1, authorId);
		ResultSet resultSet = prepareStatement.executeQuery();
		Author author = new Author();
		while(resultSet.next()){
			author.setAuthorId(resultSet.getInt("authorId"));
			author.setAuthorName(resultSet.getString("authorName"));
		}
		return author;
	}
}


