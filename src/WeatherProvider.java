public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(
                (coordinates.getLongitude() / 2)
                        + (3 * coordinates.getLatitude())
                        + (coordinates.getHeight() / 3)) % 4];
    }
}
