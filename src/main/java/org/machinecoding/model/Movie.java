package org.machinecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.machinecoding.enums.MovieLanguage;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private String movieTitle;
    private Screen screen;
    private LocalTime startTime;
    private LocalTime endTime;
    private MovieLanguage movieLanguage;

    public boolean hasAvailableSeats() {
        return screen.getSeats().stream().anyMatch(seat -> seat.getAvailableSeats() > 0);
    }
}
