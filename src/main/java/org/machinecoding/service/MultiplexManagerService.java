package org.machinecoding.service;

import org.jetbrains.annotations.NotNull;
import org.machinecoding.enums.SeatCategory;
import org.machinecoding.model.Movie;
import org.machinecoding.model.Multiplex;
import org.machinecoding.model.Seat;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategy;
import org.machinecoding.strategy.SortingStrategy.MovieSortingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiplexManagerService {

    private List<Multiplex> multiplexList;

    public  MultiplexManagerService() {
        multiplexList = new ArrayList<>();
    }


    public void addMultiplex(Multiplex multiplex) {
        multiplexList.add(multiplex);
    }

    public List<Multiplex> getMultiplexList() {
        return multiplexList;
    }

    public List<Movie> getAvailableMovieShows() {
        return multiplexList.stream()
                .flatMap(multiplex -> multiplex.getScreens().stream()
                        .flatMap(screen -> screen.getMovies().stream()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterMovies(@NotNull MovieFilterStrategy filterStrategy) {
        return filterStrategy.filter(getAvailableMovieShows());
    }

    public List<Movie> sortSchedules(List<Movie> schedules, MovieSortingStrategy movieSortingStrategy) {
        return movieSortingStrategy.sort(schedules);
    }

    public void bookSeat(Movie movie, SeatCategory seatCategory) {
        for (Seat seat : movie.getScreen().getSeats()) {
            if (seat.getCategory().equals(seatCategory)) {
                try {
                    seat.bookSeat();
                    return;
                } catch (IllegalStateException e) {
                    continue;
                }
            }
        }
        throw new IllegalArgumentException("Invalid seat category.");
    }


}
