package JavaPROsrc.GSON2;

import java.util.Arrays;

public class BusinessCard {
    private String name;
    private String surname;
    private String[] phones;
    private String[] sites;
    private Address address;

    @Override
    public String toString() {
        return "BusinessCard{" + System.lineSeparator() +
                "name='" + name + '\'' + System.lineSeparator() +
                ", surname='" + surname + '\'' + System.lineSeparator() +
                ", phones=" + Arrays.toString(phones) + System.lineSeparator() +
                ", sites=" + Arrays.toString(sites) + System.lineSeparator() +
                ", address=" + address +
                '}';
    }
}
