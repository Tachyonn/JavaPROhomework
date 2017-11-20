package JavaPROsrc.GSON2;

public class Address {
    private String country;
    private String city;
    private String street;

    @Override
    public String toString() {
        return "Address{" + System.lineSeparator() +
                "country='" + country + '\'' + System.lineSeparator() +
                ", city='" + city + '\'' + System.lineSeparator() +
                ", street='" + street + '\'' +
                '}';
    }
}
