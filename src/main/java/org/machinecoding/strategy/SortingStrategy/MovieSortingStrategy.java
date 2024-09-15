package org.machinecoding.strategy.SortingStrategy;

import org.machinecoding.model.Movie;

import java.util.List;

public interface MovieSortingStrategy {
    List<Movie> sort(List<Movie> movies);
}
