package br.ufpa.refactoring.q9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void finalTotal_examples() {
        var vip = new Order.Customer(true, 6);
        var o1 = new Order(vip, 200.0, 0.10);
        assertEquals(140.0, o1.finalTotal(), 0.001);

        var regular = new Order.Customer(false, 1);
        var o2 = new Order(regular, 200.0, 0.10);
        assertEquals(180.0, o2.finalTotal(), 0.001);
    }

    @Test
    void refactorGoal_moveDiscountLogic_toCustomerOrPolicy() {
        var loyalRegular = new Order.Customer(false, 5);
        assertEquals(165.0, new Order(loyalRegular, 200.0, 0.10).finalTotal(), 0.001);

        var newVip = new Order.Customer(true, 2);
        assertEquals(155.0, new Order(newVip, 200.0, 0.10).finalTotal(), 0.001);

        var smallOrderVip = new Order.Customer(true, 6);
        assertEquals(0.0, new Order(smallOrderVip, 30.0, 0.10).finalTotal(), 0.001);
    }
}