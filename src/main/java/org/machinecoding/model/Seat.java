package org.machinecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.machinecoding.enums.SeatCategory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    private SeatCategory category;
    private int price;
    private int totalSeats;
    private int availableSeats;

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        } else {
            throw new IllegalStateException("No available seats in this category.");
        }
    }
}
