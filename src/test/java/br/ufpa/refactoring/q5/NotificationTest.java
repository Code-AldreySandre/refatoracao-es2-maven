package br.ufpa.refactoring.q5;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void render_shouldContainSubjectAndBody() {
        Notification n = new PasswordResetNotification("Joelma");
        String r = n.render();

        assertTrue(r.contains("Redefinição de senha"));
        assertTrue(r.contains("Olá Joelma"));
    }

    @Test
    void refactorGoal_collapseHierarchy_removeRedundantSubclass() {
        Notification n = new PasswordResetNotification("Joelma");
        String expected = "[" + LocalDate.now() + "] Redefinição de senha\nOlá Joelma, clique no link para redefinir.";

        assertEquals(expected, n.render());
        assertEquals("Redefinição de senha", n.subject());
        assertEquals("Olá Joelma, clique no link para redefinir.", n.body());
    }
}