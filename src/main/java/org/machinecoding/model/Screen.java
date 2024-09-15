package org.machinecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Screen {

    private int screenNumber;
    private List<Seat> seats;
    private List<Movie> movies;
    private Multiplex multiplex;

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void addSchedule(Movie movie) {
        movies.add(movie);
    }

}
