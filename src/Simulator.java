import java.io.*;

public class Simulator {
    public static class AvajException extends Exception {
        public AvajException(String exception) { super (exception); }
        public AvajException(Throwable exception) { super (exception); }
    }

    private static Integer parseAndLoadSimulationFile(String simulationFileName, WeatherTower weatherTower) throws AvajException {
        int amountOfSimulation;
        try {
            BufferedReader bufferedReader = getBufferedReader(simulationFileName);
            amountOfSimulation = getAmountOfSimulation(bufferedReader);
            setSimulationAircrafts(bufferedReader, weatherTower);
            bufferedReader.close();
        } catch (IOException e) {
            throw new AvajException(e);
        }
        return (amountOfSimulation);
    }

    private static void setSimulationAircrafts(BufferedReader bufferedReader, WeatherTower weatherTower) throws AvajException {
        String line;
        String[] aircraftInfo;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                aircraftInfo = line.split("\\s+");
                if (aircraftInfo.length != 5) {
                    throw new AvajException("Unexpected amount of arguments in scenario file");
                }
                AircraftFactory.newAircraft(
                        aircraftInfo[0],
                        aircraftInfo[1],
                        Integer.parseInt(aircraftInfo[2]),
                        Integer.parseInt(aircraftInfo[3]),
                        Integer.parseInt(aircraftInfo[4])
                ).registerTower(weatherTower);
            }
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new AvajException(e);
        }
    }

    private static int getAmountOfSimulation(BufferedReader bufferedReader) throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }

    private static BufferedReader getBufferedReader(String simulationFileName) throws FileNotFoundException {
        File file = new File(simulationFileName);
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

    public static void main(String[] arguments) throws Exception {
        int i = 0;
        int amountOfSimulation = 0;
        WeatherTower weatherTower = new WeatherTower();
        if (arguments.length > 1)  {
            throw new AvajException("There are" + arguments.length + "arguments instead of 1.");
        } else if (arguments.length == 0){
            throw new AvajException("You need to include a simulation file as an argument.");
        }
        try {
            amountOfSimulation = parseAndLoadSimulationFile(arguments[0], weatherTower);
        } catch (AvajException exception) {
            System.out.println(exception.getMessage());
        }
        while (i < amountOfSimulation) {
            weatherTower.changeWeather();
            i++;
        }
    }
}
