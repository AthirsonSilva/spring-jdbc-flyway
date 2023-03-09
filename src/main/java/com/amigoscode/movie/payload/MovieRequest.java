package com.amigoscode.movie.payload;

import com.amigoscode.actor.Actor;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(Integer id,
                    String name,
                    List<Integer> actors_id,
                    LocalDate releaseDate
) {
}

