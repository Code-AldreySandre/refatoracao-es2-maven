package br.ufpa.refactoring.q6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {

    @Test
    void priceTable_examples() {
        BillingService svc = new BillingService();

        assertEquals(0.0, svc.monthlyPrice(BillingService.Plan.FREE, 10, false), 0.001);
        assertEquals(19.90, svc.monthlyPrice(BillingService.Plan.STUDENT, 10, false), 0.001);

        assertEquals(49.90 + 10*1.25, svc.monthlyPrice(BillingService.Plan.PRO, 10, false), 0.01);
        assertEquals((49.90 + 250*1.25) * 0.95, svc.monthlyPrice(BillingService.Plan.PRO, 250, false), 0.01);

        assertEquals(199.90 + 10*0.90 + 59.90, svc.monthlyPrice(BillingService.Plan.ENTERPRISE, 10, true), 0.01);
    }

    @Test
    void refactorGoal_replaceConditionalWithPolymorphism() {
        BillingService svc = new BillingService();

        assertEquals(0.0, svc.monthlyPrice(BillingService.Plan.FREE, 50, true), 0.001);
        assertEquals(19.90 + 59.90, svc.monthlyPrice(BillingService.Plan.STUDENT, 5, true), 0.01);

        double expectedProWithSupport = Math.round(((49.90 + 250 * 1.25 + 59.90) * 0.95) * 100.0) / 100.0;
        assertEquals(expectedProWithSupport, svc.monthlyPrice(BillingService.Plan.PRO, 250, true), 0.01);
        assertEquals(199.90 + 100 * 0.90, svc.monthlyPrice(BillingService.Plan.ENTERPRISE, 100, false), 0.01);
    }
}