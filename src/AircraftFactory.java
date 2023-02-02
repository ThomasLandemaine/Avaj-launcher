public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws Simulator.AvajException {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new Simulator.AvajException("Can't have negative values for longitude, latitude or height.");
        }
        if (height > 100) {
            height = 100;
        }
        return switch (type) {
            case "Helicopter" -> new Helicopter(name, new Coordinates(longitude, latitude, height));
            case "Baloon" -> new Baloon(name, new Coordinates(longitude, latitude, height));
            case "JetPlane" -> new JetPlane(name, new Coordinates(longitude, latitude, height));
            default -> null;
        };
    }
}
