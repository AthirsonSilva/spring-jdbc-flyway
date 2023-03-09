package com.amigoscode.movie;

import com.amigoscode.actor.Actor;
import com.amigoscode.actor.ActorDao;
import com.amigoscode.exception.NotFoundException;
import com.amigoscode.movie.payload.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieDao movieDao;
    private final ActorDao actorDao;

    public List<Movie> getMovies() {
        return movieDao.selectMovies();
    }

    public void addNewMovie(MovieRequest movie) {
        if (movieDao.existsByName(movie.name())) {
            throw new IllegalStateException(String.format("movie with given name '%s' already exists", movie.name()));
        }

        List<Actor> actors = new ArrayList<>();
        movie.actors_id().forEach(actorId -> {
            Actor actor = actorDao.selectActorById(actorId)
                    .orElseThrow(() -> new NotFoundException(String.format("Actor with id %s not found", actorId)));

            actors.add(actor);
        });

        Movie movieToInsert = new Movie(null, movie.name(), actors, movie.releaseDate());

        int result = movieDao.insertMovie(movieToInsert);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteMovie(Integer id) {
        Optional<Movie> movies = movieDao.selectMovieById(id);
        movies.ifPresentOrElse(movie -> {
            int result = movieDao.deleteMovie(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete movie");
            }
        }, () -> {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        });
    }

    public Movie getMovie(int id) {
        return movieDao.selectMovieById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", id)));
    }

    public Movie updateMovie(int id, Movie movie) {
        Optional<Movie> movies = movieDao.selectMovieById(id);
        movies.ifPresentOrElse(movie1 -> {
            int result = movieDao.updateMovie(id, movie);

            if (result != 1) {
                throw new IllegalStateException("oops could not update movie");
            }
        }, () -> {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        });

        return movie;
    }
}
