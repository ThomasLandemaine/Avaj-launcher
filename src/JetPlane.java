public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude() + 10,
                    Math.min(this.coordinates.getHeight() + 2, 100)
            );
            case "RAIN" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude() + 5,
                    this.coordinates.getHeight()
            );
            case "FOG" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude() + 1,
                    this.coordinates.getHeight()
            );
            case "SNOW" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    Math.max(this.coordinates.getHeight() - 7, 0)
            );
        }
        System.out.print("JetPlane#" + this.name + "(" + this.id + "): ");
        switch (weather) {
            case "SUN" -> System.out.println("They are only happy in the sun");
            case "RAIN" -> System.out.println("They might as well be dead if the rain comes");
            case "FOG" -> System.out.println("And in the dark, behind the fog, i'm hearing the piano sing");
            case "SNOW" -> System.out.println("Oh snow ! Look how snow falls");
        }
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("JetPlane " + this.name + " is landing.");
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
        System.out.println("A new JetPlane named " + this.name + " has been registered.");
    }
}
