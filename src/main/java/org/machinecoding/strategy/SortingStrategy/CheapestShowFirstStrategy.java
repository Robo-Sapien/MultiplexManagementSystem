package org.machinecoding.strategy.SortingStrategy;

import org.machinecoding.model.Movie;
import org.machinecoding.model.Seat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CheapestShowFirstStrategy implements MovieSortingStrategy {

    @Override
    public List<Movie> sort(List<Movie> movies) {
        return movies.stream()
                .sorted(Comparator.comparingInt(movie -> movie.getScreen().getSeats().stream()
                    .mapToInt(Seat::getPrice)
                    .min()
                    .orElse(Integer.MAX_VALUE)))
                .collect(Collectors.toList());
    }
}
