package br.ufpa.refactoring.q7;

public class SupportChat {

    public String greeting(ChatUser user) {
        return ChatUser.from(user).greetingMessage();
    }

    public sealed interface ChatUser permits User, GuestUser {
        String greetingMessage();

        static ChatUser from(ChatUser user) {
            return user == null ? new GuestUser() : user;
        }
    }

    public record User(String name, boolean banned) implements ChatUser {
        public boolean isBanned() {
            return banned;
        }

        @Override
        public String greetingMessage() {
            if (isBanned()) return "Conta bloqueada. Contate o suporte.";
            if (name == null || name.isBlank()) return "Olá! Como posso ajudar?";
            return "Olá, " + name + "! Como posso ajudar?";
        }
    }

    public record GuestUser() implements ChatUser {
        @Override
        public String greetingMessage() {
            return "Olá! Quer continuar como visitante?";
        }
    }
}