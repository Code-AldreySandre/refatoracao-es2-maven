package br.ufpa.refactoring.q1;

import br.ufpa.refactoring.q1.PurchaseEmailService.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseEmailServiceTest {

    @Test
    void shouldBuildCompleteEmailWithDiscountsAndExpressShipping() {
        PurchaseEmailService service = new PurchaseEmailService();
        Address address = new Address("Av. Augusto Corrêa", "01", "Belém", "66075-110");
        List<PurchaseItem> items = List.of(
                new PurchaseItem("Notebook", 1, 300.00),
                new PurchaseItem("Mouse", 2, 50.00)
        );
        Purchase purchase = new Purchase("123", "Maria", items, true, "VIP10", address);

        String result = service.buildEmail(purchase);

        String expected = "Olá, Maria!\n\n" +
                "Seu pedido #123 foi confirmado em " + LocalDate.now() + ".\n\n" +
                "Itens:\n" +
                "- Notebook (1x) : R$ " + String.format("%.2f", 300.00) + "\n" +
                "- Mouse (2x) : R$ " + String.format("%.2f", 100.00) + "\n" +
                "\nSubtotal: R$ " + String.format("%.2f", 400.00) + "\n" +
                "Frete: R$ " + String.format("%.2f", 29.90) + "\n" +
                "Descontos: R$ " + String.format("%.2f", 60.00) + "\n" +
                "TOTAL: R$ " + String.format("%.2f", 369.90) + "\n\n" +
                "Endereço de entrega:\n" +
                "Av. Augusto Corrêa, 01\n" +
                "Belém - 66075-110\n\n" +
                "Obrigado por comprar com a gente!\n";

        assertEquals(expected, result);
    }
}