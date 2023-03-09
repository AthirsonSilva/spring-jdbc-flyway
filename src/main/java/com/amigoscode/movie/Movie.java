package com.amigoscode.movie;

import com.amigoscode.actor.Actor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class Movie {
	Integer id;
	String name;
	List<Actor> actors;
	LocalDate releaseDate;
}
