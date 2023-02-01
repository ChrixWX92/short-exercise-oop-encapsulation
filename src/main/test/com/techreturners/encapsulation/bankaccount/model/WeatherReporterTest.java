package com.techreturners.encapsulation.bankaccount.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class WeatherReporterTest {

    @Test
    public void checkUnhandledLocation() throws InvalidTemperatureException {
        WeatherReporter weatherReporter = new WeatherReporter("Paris", 18);
        assertEquals("I am in Paris and it is 🔆. Ahhh...it's just right 😊! The temperature in Fahrenheit is 64.4°.", weatherReporter.print());
    }

    @Test
    public void checkInvalidLocation() throws InvalidTemperatureException {
        WeatherReporter weatherReporter = new WeatherReporter("⛰", 10);
        assertEquals("I am in ⛰ and it is 🔆. Ahhh...it's just right 😊! The temperature in Fahrenheit is 50°.", weatherReporter.print());
    }

    @Test
    public void checkNegativeTemperature() throws InvalidTemperatureException {
        WeatherReporter weatherReporter = new WeatherReporter("London", -5);
        assertEquals("I am in London and it is 🌦. It's too cold 🥶! The temperature in Fahrenheit is 23°.", weatherReporter.print());
    }

    @Disabled // Double imprecision means that +32 addition is ignored in the conversion to Fahrenheit. The below test will fail.
    @Test
    public void checkAtypicalInputs() throws InvalidTemperatureException {
        String longestPlaceName = "Taumatawhakatangihangakoauauotamateaturipukakapiki-maungahoronukupokaiwhenuakitnatahu";
        double planckTemperature = 1420000000000000000000000000000000d;
        WeatherReporter weatherReporter = new WeatherReporter(longestPlaceName, planckTemperature);
        assertEquals("I am in " + longestPlaceName + " and it is 🔆. It's too hot 🥵! The temperature in Fahrenheit is 2,556,000,000,000,000,000,000,000,000,000,032°.", weatherReporter.print());
    }

    @Test
    public void checkExtremeTemperature() {
        double planckTemperature = 1420000000000000000000000000000000d;
        assertThrows(InvalidTemperatureException.class, () -> new WeatherReporter("The beginning of all creation", planckTemperature));
    }

    @Test
    public void checkAtypicalPlaceName() throws InvalidTemperatureException {
        String longestPlaceName = "Taumatawhakatangihangakoauauotamateaturipukakapiki-maungahoronukupokaiwhenuakitnatahu";
        WeatherReporter weatherReporter = new WeatherReporter(longestPlaceName, 15);
        assertEquals("I am in " + longestPlaceName + " and it is 🔆. Ahhh...it's just right 😊! The temperature in Fahrenheit is 59°.", weatherReporter.print());

    }

    @Test
    public void checkOverlyPreciseTemperature() throws InvalidTemperatureException {
        String precisePlace = "46.2330° N, 6.0557° E";
        double preciseTemperature = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679d;
        double roundedTemperature = BigDecimal.valueOf((1.8d * preciseTemperature) + 32.0).setScale(3, RoundingMode.HALF_UP).doubleValue();
        WeatherReporter weatherReporter = new WeatherReporter(precisePlace, preciseTemperature);
        assertEquals("I am in " + precisePlace + " and it is 🔆. It's too cold 🥶! The temperature in Fahrenheit is " + roundedTemperature + "°.", weatherReporter.print());
    }

}