package org.machinecoding;

import lombok.extern.slf4j.Slf4j;
import org.machinecoding.enums.MovieLanguage;
import org.machinecoding.enums.SeatCategory;
import org.machinecoding.model.Movie;
import org.machinecoding.model.Multiplex;
import org.machinecoding.model.Screen;
import org.machinecoding.model.Seat;
import org.machinecoding.service.MultiplexManagerService;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategy;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategyImpl.MovieLanguageFilterStrategy;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategyImpl.MovieTitleFilterStrategy;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategyImpl.MultiplexNameFilterStrategy;
import org.machinecoding.strategy.SortingStrategy.CheapestShowFirstStrategy;
import org.machinecoding.strategy.SortingStrategy.MovieSortingStrategy;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        MultiplexManagerService service = new MultiplexManagerService();

        // Create multiplexes
        Multiplex centralMall = new Multiplex("Central Mall", new ArrayList<>());
        Multiplex inorbitMall = new Multiplex("Inorbit Mall", new ArrayList<>());

        // Create screens for Central Mall
        Screen screen1Central = new Screen(1, new ArrayList<>(), new ArrayList<>(), centralMall);
        screen1Central.addSeat(new Seat(SeatCategory.Silver, 250, 50, 50));
        screen1Central.addSeat(new Seat(SeatCategory.Gold, 300, 30, 30));
        screen1Central.addSeat(new Seat(SeatCategory.Platinum, 150, 20, 20));
        screen1Central.addSchedule(new Movie("Movie A", screen1Central, LocalTime.of(14, 0), LocalTime.of(17, 0), MovieLanguage.Hindi));
        screen1Central.addSchedule(new Movie("Movie B", screen1Central, LocalTime.of(18, 0), LocalTime.of(21, 0), MovieLanguage.English));

        Screen screen2Central = new Screen(2, new ArrayList<>(), new ArrayList<>(), centralMall);
        screen2Central.addSeat(new Seat(SeatCategory.Silver, 100, 60, 60));
        screen2Central.addSeat(new Seat(SeatCategory.Gold, 150, 40, 40));
        screen2Central.addSeat(new Seat(SeatCategory.Platinum, 200, 30, 30));
        screen2Central.addSchedule(new Movie("Movie C", screen2Central, LocalTime.of(16, 0), LocalTime.of(19, 0), MovieLanguage.English));

        centralMall.addScreenToMultiplex(screen1Central);
        centralMall.addScreenToMultiplex(screen2Central);

        // Create screens for Inorbit Mall
        Screen screen1Inorbit = new Screen(1, new ArrayList<>(), new ArrayList<>(), inorbitMall);
        screen1Inorbit.addSeat(new Seat(SeatCategory.Silver, 130, 50, 50));
        screen1Inorbit.addSeat(new Seat(SeatCategory.Gold, 180, 30, 30));
        screen1Inorbit.addSeat(new Seat(SeatCategory.Platinum, 230, 20, 20));
        screen1Inorbit.addSchedule(new Movie("Movie A", screen1Inorbit, LocalTime.of(15, 0), LocalTime.of(18, 0), MovieLanguage.Hindi));
        screen1Inorbit.addSchedule(new Movie("Movie B", screen1Inorbit, LocalTime.of(19, 0), LocalTime.of(22, 0), MovieLanguage.Hindi));

        Screen screen2Inorbit = new Screen(2, new ArrayList<>(), new ArrayList<>(), inorbitMall);
        screen2Inorbit.addSeat(new Seat(SeatCategory.Silver, 120, 60, 60));
        screen2Inorbit.addSeat(new Seat(SeatCategory.Gold, 170, 40, 40));
        screen2Inorbit.addSeat(new Seat(SeatCategory.Platinum, 220, 30, 30));
        screen2Inorbit.addSchedule(new Movie("Movie C", screen2Inorbit, LocalTime.of(17, 0), LocalTime.of(20, 0), MovieLanguage.Hindi));
        screen2Inorbit.addSchedule(new Movie("Movie D", screen2Inorbit, LocalTime.of(21, 0), LocalTime.of(23, 0), MovieLanguage.English));

        inorbitMall.addScreenToMultiplex(screen1Inorbit);
        inorbitMall.addScreenToMultiplex(screen2Inorbit);

        // Add multiplexes to service
        service.addMultiplex(centralMall);
        service.addMultiplex(inorbitMall);

        // Filters
        MovieFilterStrategy filter1 = new MultiplexNameFilterStrategy("Inorbit Mall");
        MovieFilterStrategy filter2 = new MovieTitleFilterStrategy("Movie A");
        MovieFilterStrategy movieLanguageFilter = new MovieLanguageFilterStrategy(MovieLanguage.English);


        // Get filtered schedules
        List<Movie> filteredSchedules_Multiplex = service.filterMovies(filter1);
        System.out.println("Filtered Schedules based on multiplex:");
        for (Movie schedule : filteredSchedules_Multiplex) {
            System.out.println("Multiplex: " + schedule.getScreen().getMultiplex().getBuildingName()
                    + ", Screen: " + schedule.getScreen().getScreenNumber()
                    + ", Movie: " + schedule.getMovieTitle()
                    + ", Start Time: " + schedule.getStartTime()
                    + ", End Time: " + schedule.getEndTime());
        }

        List<Movie> filteredSchedules_MovieTitle = service.filterMovies(filter2);
        System.out.println("\nFiltered Schedules based on movieTitle:");
        for (Movie schedule : filteredSchedules_MovieTitle) {
            System.out.println("Multiplex: " + schedule.getScreen().getMultiplex().getBuildingName()
                    + ", Screen: " + schedule.getScreen().getScreenNumber()
                    + ", Movie: " + schedule.getMovieTitle()
                    + ", Start Time: " + schedule.getStartTime()
                    + ", End Time: " + schedule.getEndTime());
        }

        List<Movie> filteredSchedules_MovieLanguage = service.filterMovies(movieLanguageFilter);
        System.out.println("\nFiltered Schedules based on MovieLanguage:");
        for (Movie schedule : filteredSchedules_MovieLanguage) {
            System.out.println("Multiplex: " + schedule.getScreen().getMultiplex().getBuildingName()
                    + ", Screen: " + schedule.getScreen().getScreenNumber()
                    + ", Movie: " + schedule.getMovieTitle()
                    + ", Start Time: " + schedule.getStartTime()
                    + ", End Time: " + schedule.getEndTime()
                    + ", Movie Language: " + schedule.getMovieLanguage());
        }

        MovieSortingStrategy cheapestFirstSorter = new CheapestShowFirstStrategy();
        List<Movie> sortedSchedules = service.sortSchedules(filteredSchedules_MovieTitle, cheapestFirstSorter);
        System.out.println("\nSorted Schedules (Cheapest First):");
        for (Movie schedule : sortedSchedules) {
            System.out.println("Multiplex: " + schedule.getScreen().getMultiplex().getBuildingName()
                    + ", Screen: " + schedule.getScreen().getScreenNumber()
                    + ", Movie: " + schedule.getMovieTitle()
                    + ", Start Time: " + schedule.getStartTime()
                    + ", End Time: " + schedule.getEndTime());
        }

        // Book a seat
        Movie scheduleToBook = sortedSchedules.get(0);
        service.bookSeat(scheduleToBook, SeatCategory.Silver);

        // Check availability after booking
        System.out.println("\nAvailable seats after booking:");
        for (Seat seat : scheduleToBook.getScreen().getSeats()) {
            System.out.println("Category: " + seat.getCategory() + ", Available Seats: " + seat.getAvailableSeats());
        }
    }
}