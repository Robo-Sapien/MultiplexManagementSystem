package org.machinecoding.strategy.FilterStrategy.MovieFilterStrategyImpl;

import org.machinecoding.enums.MovieLanguage;
import org.machinecoding.model.Movie;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class MovieLanguageFilterStrategy implements MovieFilterStrategy {

    private MovieLanguage movieLanguage;

    public MovieLanguageFilterStrategy(MovieLanguage movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    @Override
    public List<Movie> filter(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getMovieLanguage().equals(movieLanguage) && movie.hasAvailableSeats())
                .collect(Collectors.toList());
    }
}
