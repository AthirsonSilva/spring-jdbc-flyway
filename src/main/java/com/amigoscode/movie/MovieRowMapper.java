package com.amigoscode.movie;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MovieRowMapper implements RowMapper<Movie> {
	@Override
	public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
		int id = resultSet.getInt("id");

		String name = resultSet.getString("name");
		String releaseDate = resultSet.getString("release_date");

		return new Movie(id, name, List.of(), LocalDate.parse(releaseDate));
	}
}
