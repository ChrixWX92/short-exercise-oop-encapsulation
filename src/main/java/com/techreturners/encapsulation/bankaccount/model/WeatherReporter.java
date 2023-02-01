package com.techreturners.encapsulation.bankaccount.model;

import java.text.MessageFormat;

public class WeatherReporter {

    private final String location;
    private final String weather;
    private final double inputTemperature;
    private final double fahrenheitTemperature;

    public WeatherReporter(String location, double temperature) throws InvalidTemperatureException {

        this.location = location;
        this.weather = calculateWeather();
        this.inputTemperature = temperature;
        this.fahrenheitTemperature = (1.8d * inputTemperature) + 32.0;

        if (fahrenheitTemperature > 9999999999999999d) { // Too large for accurate addition in the above formula
            throw new InvalidTemperatureException("Input temperature is too large to accurately convert to Fahrenheit.");
        }

    }

    public String print() {
        return MessageFormat.format(
                "I am in {0} and it is {1}. {2} The temperature in Fahrenheit is {3}°.",
                location,
                weather,
                reviewTemperature(),
                fahrenheitTemperature
        );
    }

    private String calculateWeather() {

        return switch (location) {
            case "London" -> "🌦";
            case "California" -> "🌅";
            case "Cape Town" -> "🌤";
            default -> "🔆";
        };

    }

    private String reviewTemperature() {

        if (inputTemperature > 30)

            return "It's too hot 🥵!";

        else if (inputTemperature < 10)

            return "It's too cold 🥶!";

        return "Ahhh...it's just right 😊!";

    }

}
