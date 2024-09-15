package org.machinecoding.strategy.FilterStrategy.MovieFilterStrategyImpl;

import org.machinecoding.model.Movie;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class MovieTitleFilterStrategy implements MovieFilterStrategy {

    private String movieTitle;

    public MovieTitleFilterStrategy(String movieTitle){
        this.movieTitle = movieTitle;
    }

    @Override
    public List<Movie> filter(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getMovieTitle().equalsIgnoreCase(movieTitle) && movie.hasAvailableSeats())
                .collect(Collectors.toList());
    }
}
