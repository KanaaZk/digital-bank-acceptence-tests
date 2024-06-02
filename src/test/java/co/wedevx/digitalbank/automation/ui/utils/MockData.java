package co.wedevx.digitalbank.automation.ui.utils;

import co.wedevx.digitalbank.automation.ui.models.RandomUser;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {

    private FakeValuesService fakeValueService = new FakeValuesService(
            new Locale("en-US"), new RandomService());
    private Faker faker = new Faker();

    public Map<String, String> generateRandomNameAndEmail() {
        String name = fakeValueService.bothify(new Faker().name().firstName());
        String email = fakeValueService.bothify(name + "##@gmail.com");

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);

        return data;
    }

    public String genderRandomSsn() {
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        return ssn;
    }

    public RandomUser generateRandomUser() {
        String title = faker.name().title();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String gender = faker.options().option("M", "F");
        String dateOfBirth = faker.date().birthday().toString().substring(0, 10);  // Adjust date format as needed
        String socialSecurityNumber = faker.idNumber().ssnValid();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password();
        String address = faker.address().streetAddress();
        String locality = faker.address().city();
        String region = faker.address().stateAbbr();
        String postalCode = faker.address().zipCode();
        String country = faker.address().countryCode();
        String homePhone = faker.phoneNumber().phoneNumber();
        String mobilePhone = faker.phoneNumber().cellPhone();
        String workPhone = faker.phoneNumber().phoneNumber();

        return new RandomUser(title, firstName, lastName, gender, dateOfBirth, socialSecurityNumber, emailAddress,
                password, password, address, locality, region, postalCode, country,
                homePhone, mobilePhone, workPhone);
    }



}