package br.ufpa.refactoring.q3;

import java.util.List;

public class PayrollCalculator {

    public double monthlyNetSalary(Employee e) {
        return roundToTwoDecimals(calculateNetSalary(e));
    }

    private double calculateNetSalary(Employee e) {
        return grossSalary(e) - tax(e) - e.healthPlanDeduction() + mealVoucher(e);
    }

    private double grossSalary(Employee e) {
        return e.baseSalary() + overtimePay(e);
    }

    private double overtimePay(Employee e) {
        double total = 0.0;
        for (Overtime ot : e.overtimes()) {
            total += ot.hours() * ot.hourRate();
        }
        return total;
    }

    private double tax(Employee e) {
        double gross = grossSalary(e);
        if (gross <= 3000) return gross * 0.08;
        if (gross <= 7000) return gross * 0.12;
        return gross * 0.18;
    }

    private double mealVoucher(Employee e) {
        return e.hasMealVoucher() ? 450.0 : 0.0;
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public record Employee(double baseSalary, List<Overtime> overtimes,
                           boolean hasMealVoucher, double healthPlanDeduction) {}

    public record Overtime(int hours, double hourRate) {}
}