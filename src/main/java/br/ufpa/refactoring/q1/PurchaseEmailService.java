package br.ufpa.refactoring.q1;

import java.time.LocalDate;
import java.util.List;

public class PurchaseEmailService {

    public String buildEmail(Purchase purchase) {
        StringBuilder sb = new StringBuilder();

        appendHeader(sb, purchase);
        appendItems(sb, purchase.items());

        double subtotal = calculateSubtotal(purchase.items());
        double shipping = calculateShipping(purchase);
        double discount = calculateDiscount(purchase, subtotal);

        appendFinancialSummary(sb, subtotal, shipping, discount);
        appendFooter(sb, purchase.address());

        return sb.toString();
    }

    private void appendHeader(StringBuilder sb, Purchase purchase) {
        sb.append("Olá, ").append(purchase.customerName()).append("!\n\n");
        sb.append("Seu pedido #").append(purchase.id()).append(" foi confirmado em ")
          .append(LocalDate.now()).append(".\n\n");
    }

    private void appendItems(StringBuilder sb, List<PurchaseItem> items) {
        sb.append("Itens:\n");
        for (PurchaseItem item : items) {
            double lineTotal = item.unitPrice() * item.quantity();
            sb.append("- ").append(item.name())
              .append(" (").append(item.quantity()).append("x) : R$ ")
              .append(formatMoney(lineTotal)).append("\n");
        }
    }

    private double calculateSubtotal(List<PurchaseItem> items) {
        double subtotal = 0.0;
        for (PurchaseItem item : items) {
            subtotal += item.unitPrice() * item.quantity();
        }
        return subtotal;
    }

    private double calculateShipping(Purchase purchase) {
        return purchase.isExpress() ? 29.90 : 14.90;
    }

    private double calculateDiscount(Purchase purchase, double subtotal) {
        double discount = 0.0;
        if (subtotal >= 300.0) {
            discount = subtotal * 0.10;
        }
        if (purchase.coupon() != null && purchase.coupon().startsWith("VIP")) {
            discount += 20.0;
        }
        return discount;
    }

    private void appendFinancialSummary(StringBuilder sb, double subtotal, double shipping, double discount) {
        double total = subtotal + shipping - discount;

        sb.append("\nSubtotal: R$ ").append(formatMoney(subtotal)).append("\n");
        sb.append("Frete: R$ ").append(formatMoney(shipping)).append("\n");
        sb.append("Descontos: R$ ").append(formatMoney(discount)).append("\n");
        sb.append("TOTAL: R$ ").append(formatMoney(total)).append("\n\n");
    }

    private void appendFooter(StringBuilder sb, Address address) {
        sb.append("Endereço de entrega:\n");
        sb.append(address.street()).append(", ")
          .append(address.number()).append("\n");
        sb.append(address.city()).append(" - ")
          .append(address.zip()).append("\n\n");
        sb.append("Obrigado por comprar com a gente!\n");
    }

    private String formatMoney(double value) {
        return String.format("%.2f", value);
    }

    public record Purchase(String id, String customerName, List<PurchaseItem> items,
                           boolean isExpress, String coupon, Address address) {}

    public record PurchaseItem(String name, int quantity, double unitPrice) {}

    public record Address(String street, String number, String city, String zip) {}
}