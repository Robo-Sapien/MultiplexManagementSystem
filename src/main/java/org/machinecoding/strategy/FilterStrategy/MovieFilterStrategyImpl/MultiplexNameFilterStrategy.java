package org.machinecoding.strategy.FilterStrategy.MovieFilterStrategyImpl;

import org.machinecoding.model.Movie;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class MultiplexNameFilterStrategy implements MovieFilterStrategy {

    private String multiplexBuildingName;

    public MultiplexNameFilterStrategy(String multiplexBuildingName) {
        this.multiplexBuildingName = multiplexBuildingName;
    }


    @Override
    public List<Movie> filter(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getScreen().getMultiplex().getBuildingName()
                        .equalsIgnoreCase(multiplexBuildingName) && movie.hasAvailableSeats())
                .collect(Collectors.toList());
    }
}
