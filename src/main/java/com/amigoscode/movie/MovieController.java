package com.amigoscode.movie;

import com.amigoscode.movie.payload.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
	private final MovieService movieService;

	@GetMapping
	public List<Movie> listMovies() {
		return movieService.getMovies();
	}

	@GetMapping("{id}")
	public Movie getMovieId(@PathVariable("id") Integer id) {
		return movieService.getMovie(id);
	}

	@PostMapping
	public void addMovie(@RequestBody MovieRequest movie) {
		movieService.addNewMovie(movie);
	}

	@DeleteMapping("{id}")
	public void deleteMovie(@PathVariable("id") Integer id) {
		movieService.deleteMovie(id);
	}

	@PutMapping("{id}")
	public Movie updateMovie(@PathVariable("id") Integer id, @RequestBody Movie movie) {
		return movieService.updateMovie(id, movie);
	}
}
