package br.ufpa.refactoring.q8;

public class StudentRegistration {

    private final String name;
    private final ContactInfo contactInfo;
    private final Address address;

    public StudentRegistration(String name, String email, String phone, String city, String zip) {
        this(name, new ContactInfo(email, phone), new Address(city, zip));
    }

    public StudentRegistration(String name, ContactInfo contactInfo, Address address) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public boolean isValid() {
        if (name == null || name.isBlank()) return false;
        return contactInfo != null && contactInfo.isValid()
                && address != null && address.isValid();
    }

    public String formattedContact() {
        return name + " | " + contactInfo.formatted() + " | " + address.formatted();
    }
}