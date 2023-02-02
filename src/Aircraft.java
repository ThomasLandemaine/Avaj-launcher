public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long staticId = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private Long nextId() {
        staticId += 1;
        return staticId;
    }
}
