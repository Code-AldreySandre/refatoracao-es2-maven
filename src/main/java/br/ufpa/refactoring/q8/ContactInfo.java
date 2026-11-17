package br.ufpa.refactoring.q8;

public record ContactInfo(String email, String phone) {

    public boolean isValid() {
        if (email == null || !email.contains("@")) return false;
        if (phone == null || phone.length() < 10) return false;
        return true;
    }

    public String formatted() {
        return email + " | " + phone;
    }
}