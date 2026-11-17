package br.ufpa.refactoring.q6;

import java.util.Objects;

public class BillingService {

    public enum Plan {
        FREE {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                return 0.0;
            }
        },
        STUDENT {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                return 19.90 + supportFee(prioritySupport);
            }
        },
        PRO {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                double price = 49.90 + (activeUsers * 1.25) + supportFee(prioritySupport);
                if (activeUsers > 200) {
                    price *= 0.95;
                }
                return price;
            }
        },
        ENTERPRISE {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                return 199.90 + (activeUsers * 0.90) + supportFee(prioritySupport);
            }
        };

        protected double supportFee(boolean prioritySupport) {
            return prioritySupport ? 59.90 : 0.0;
        }

        public abstract double calculatePrice(int activeUsers, boolean prioritySupport);
    }

    public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {
        Objects.requireNonNull(plan, "Plan must not be null");
        double price = plan.calculatePrice(activeUsers, prioritySupport);
        return Math.round(price * 100.0) / 100.0;
    }
}