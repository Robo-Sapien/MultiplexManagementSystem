package org.machinecoding.strategy.FilterStrategy;

import org.machinecoding.model.Movie;

import java.util.List;

public interface MovieFilterStrategy {
    List<Movie> filter(List<Movie> movies);
}
