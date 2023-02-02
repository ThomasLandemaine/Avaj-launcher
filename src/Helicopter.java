public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;
    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
//SUN - Longitude increases with 10, Height increases with 2
//◦ RAIN - Longitude increases with 5
//◦ FOG - Longitude increases with 1
//◦ SNOW - Height decreases with 12
    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude() + 10,
                    this.coordinates.getLatitude(),
                    Math.min(this.coordinates.getHeight() + 2, 100)
            );
            case "RAIN" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude() + 5,
                    this.coordinates.getLatitude(),
                    this.coordinates.getHeight()
            );
            case "FOG" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude() + 1,
                    this.coordinates.getLatitude(),
                    this.coordinates.getHeight()
            );
            case "SNOW" -> this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    Math.max(this.coordinates.getHeight() - 12, 0)
            );
        }
        System.out.print("Helicopter#" + this.name + "(" + this.id + "): ");
        switch (weather) {
            case "SUN" -> System.out.println("I'm walking on sunshine, wooah");
            case "RAIN" -> System.out.println("I set fire to the rain, watched it burned");
            case "FOG" -> System.out.println("And the fog comes up, from the sewers");
            case "SNOW" -> System.out.println("And it's so white as snow");
        }
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("Helicopter " + this.name + " is landing.");
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
        System.out.println("A new Helicopter named " + this.name + " has been registered.");
    }
}
