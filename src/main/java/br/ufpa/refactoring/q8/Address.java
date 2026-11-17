package br.ufpa.refactoring.q8;

import java.util.regex.Pattern;

public record Address(String city, String zip) {

    private static final Pattern ZIP_PATTERN = Pattern.compile("\\d{5}-\\d{3}");

    public boolean isValid() {
        return zip != null && ZIP_PATTERN.matcher(zip).matches();
    }

    public String formatted() {
        return city + " - " + zip;
    }
}