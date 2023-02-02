public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude() + 2,
                    this.coordinates.getLatitude(),
                    Math.min(this.coordinates.getHeight() + 4, 100)
            );
            case "RAIN" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    Math.max(this.coordinates.getHeight() - 5, 0)
            );
            case "FOG" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    Math.max(this.coordinates.getHeight() - 3, 0)
            );
            case "SNOW" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    Math.max(this.coordinates.getHeight() - 15, 0)
            );
        }
        System.out.print("Baloon#" + this.name + "(" + this.id + "): ");
        switch (weather) {
            case "SUN" -> System.out.println("Sun in the sky, you know how I feel");
            case "RAIN" -> System.out.println("I'm singing in the rain");
            case "FOG" -> System.out.println("If i let you go, you slip into the fog");
            case "SNOW" -> System.out.println("When suddenly, miracle, it snows");
        }
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("Baloon " + this.name + " is landing.");
            System.out.print("Coordinates when landing: Lon: (" + this.coordinates.getLongitude() + ")");
            System.out.print(" Lat : (" + this.coordinates.getLatitude() + ")");
            System.out.println(" Height : (" + this.coordinates.getHeight() + ")");
            this.weatherTower.unregister(this);
        }
    }
    @Override
    public void registerTower(WeatherTower WeatherTower) {
        this.weatherTower = WeatherTower;
        this.weatherTower.register(this);
        System.out.println("A new Baloon named " + this.name + " has been registered.");
    }
}
