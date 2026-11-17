package br.ufpa.refactoring.q10;

import br.ufpa.refactoring.q10.RoomBooking.BookingRequest;
import br.ufpa.refactoring.q10.RoomBooking.Room;
import br.ufpa.refactoring.q10.RoomBooking.Schedule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomBookingTest {

    @Test
    void quote_examples() {
        RoomBooking rb = new RoomBooking();

        var req1 = new BookingRequest(new Room("B", 1, "101"), new Schedule(8, 10), false, false, 10);
        assertEquals(100.0, rb.quote(req1), 0.001);

        var req2 = new BookingRequest(new Room("A", 3, "101"), new Schedule(8, 10), true, true, 50);
        assertEquals((2 * 50.0 + 30 + 20) * 1.15 + 10, rb.quote(req2), 0.01);

        var req3 = new BookingRequest(new Room("B", 1, "LAB"), new Schedule(8, 10), false, false, 10);
        assertEquals((2 * 50.0) + 40, rb.quote(req3), 0.01);
    }

    @Test
    void refactorGoal_introduceParameterObject() {
        RoomBooking rb = new RoomBooking();

        var req4 = new BookingRequest(new Room("A", 2, "202"), new Schedule(14, 16), true, false, 40);
        assertEquals(2 * 50.0 + 30, rb.quote(req4), 0.01);
    }
}