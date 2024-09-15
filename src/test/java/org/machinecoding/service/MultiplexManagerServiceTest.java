package org.machinecoding.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.machinecoding.enums.SeatCategory;
import org.machinecoding.model.Movie;
import org.machinecoding.model.Multiplex;
import org.machinecoding.model.Screen;
import org.machinecoding.model.Seat;
import org.machinecoding.strategy.FilterStrategy.MovieFilterStrategy;
import org.machinecoding.strategy.SortingStrategy.MovieSortingStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MultiplexManagerServiceTest {

    private MultiplexManagerService multiplexManagerService;

    @BeforeEach
    public void setUp() {
        multiplexManagerService = new MultiplexManagerService();
    }

    @Test
    public void testAddMultiplex() {
        Multiplex multiplex = mock(Multiplex.class);
        multiplexManagerService.addMultiplex(multiplex);

        assertEquals(1, multiplexManagerService.getMultiplexList().size());
        assertTrue(multiplexManagerService.getMultiplexList().contains(multiplex));
    }

    @Test
    public void testGetAvailableMovieShows() {
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);

        Screen screen1 = mock(Screen.class);
        when(screen1.getMovies()).thenReturn(List.of(movie1));

        Screen screen2 = mock(Screen.class);
        when(screen2.getMovies()).thenReturn(List.of(movie2));

        Multiplex multiplex = mock(Multiplex.class);
        when(multiplex.getScreens()).thenReturn(List.of(screen1, screen2));

        multiplexManagerService.addMultiplex(multiplex);

        List<Movie> availableMovies = multiplexManagerService.getAvailableMovieShows();
        assertEquals(2, availableMovies.size());
        assertTrue(availableMovies.contains(movie1));
        assertTrue(availableMovies.contains(movie2));
    }

    @Test
    public void testFilterMovies() {
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);

        Multiplex multiplex = mock(Multiplex.class);
        Screen screen = mock(Screen.class);
        when(screen.getMovies()).thenReturn(List.of(movie1, movie2));
        when(multiplex.getScreens()).thenReturn(List.of(screen));

        multiplexManagerService.addMultiplex(multiplex);

        MovieFilterStrategy filterStrategy = mock(MovieFilterStrategy.class);
        when(filterStrategy.filter(anyList())).thenReturn(List.of(movie1));

        List<Movie> filteredMovies = multiplexManagerService.filterMovies(filterStrategy);
        assertEquals(1, filteredMovies.size());
        assertTrue(filteredMovies.contains(movie1));
    }

    @Test
    public void testSortSchedules() {
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);

        List<Movie> movies = List.of(movie1, movie2);

        MovieSortingStrategy sortingStrategy = mock(MovieSortingStrategy.class);
        when(sortingStrategy.sort(movies)).thenReturn(List.of(movie2, movie1));

        List<Movie> sortedMovies = multiplexManagerService.sortSchedules(movies, sortingStrategy);
        assertEquals(2, sortedMovies.size());
        assertEquals(movie2, sortedMovies.get(0));
        assertEquals(movie1, sortedMovies.get(1));
    }

    @Test
    public void testBookSeatSuccess() {
        Movie movie = mock(Movie.class);
        Screen screen = mock(Screen.class);
        Seat seat = mock(Seat.class);
        when(seat.getCategory()).thenReturn(SeatCategory.Silver);
        when(movie.getScreen()).thenReturn(screen);
        when(screen.getSeats()).thenReturn(List.of(seat));

        multiplexManagerService.bookSeat(movie, SeatCategory.Silver);

        verify(seat, times(1)).bookSeat();
    }

    @Test
    public void testBookSeatFailure() {
        Movie movie = mock(Movie.class);
        Screen screen = mock(Screen.class);
        Seat seat = mock(Seat.class);
        when(seat.getCategory()).thenReturn(SeatCategory.Silver);
        doThrow(new IllegalStateException()).when(seat).bookSeat();
        when(movie.getScreen()).thenReturn(screen);
        when(screen.getSeats()).thenReturn(List.of(seat));

        assertThrows(IllegalArgumentException.class, () -> multiplexManagerService.bookSeat(movie, SeatCategory.Silver));
    }
}