package br.ufpa.refactoring.q3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculatorTest {

    @Test
    void netSalary_examples() {
        PayrollCalculator calc = new PayrollCalculator();

        var e1 = new PayrollCalculator.Employee(
                2800,
                List.of(new PayrollCalculator.Overtime(10, 25)),
                true,
                120
        );
        double salary1 = calc.monthlyNetSalary(e1);
        assertEquals(2800 + (10*25) - ((2800 + (10*25))*0.08) - 120 + 450, salary1, 0.01);

        var e2 = new PayrollCalculator.Employee(
                7500,
                List.of(),
                false,
                200
        );
        double salary2 = calc.monthlyNetSalary(e2);
        assertEquals(7500 - (7500*0.18) - 200, salary2, 0.01);
    }

    @Test
    void refactorGoal_replaceTempsWithQueries() {
        PayrollCalculator calc = new PayrollCalculator();

        // Faixa 8% (bruto <= 3000)
        var lowBracket = new PayrollCalculator.Employee(
                2500,
                List.of(new PayrollCalculator.Overtime(5, 40)), // bruto = 2700
                true,
                100
        );
        assertEquals(2700 - (2700 * 0.08) - 100 + 450, calc.monthlyNetSalary(lowBracket), 0.01);

        // Faixa 12% (3000 < bruto <= 7000)
        var midBracket = new PayrollCalculator.Employee(
                2800,
                List.of(new PayrollCalculator.Overtime(10, 25)), // bruto = 3050
                true,
                120
        );
        assertEquals(3050 - (3050 * 0.12) - 120 + 450, calc.monthlyNetSalary(midBracket), 0.01);

        // Faixa 18% (bruto > 7000)
        var highBracket = new PayrollCalculator.Employee(
                7500,
                List.of(),
                false,
                200
        );
        assertEquals(7500 - (7500 * 0.18) - 200, calc.monthlyNetSalary(highBracket), 0.01);
    }
}