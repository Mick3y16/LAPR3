package utils;

/**
 * Represents a unit converter, that allows the conversion of units.
 *
 * @author G11
 */
public class UnitConverter {

    /**
     * Converts a given slope from percentage to degrees.
     *
     * @param slope Slope that will be converted.
     * @return Value of a slope in degrees.
     */
    public static double slopeToDegrees(String slope) {
        double slopePercentage = Double.parseDouble(
                slope.replaceAll("[^0-9.\\-]", ""));

        return Math.toDegrees(Math.atan(slopePercentage / 100));
    }

    /**
     * Converts a distance from kilometers to meters.
     *
     * @param distance Distance that will be converted.
     * @return Distance in meters.
     */
    public static double KilometersToMeters(String distance) {
        double lengthKilometers = Double.parseDouble(
                distance.replaceAll("[^0-9.]", ""));

        return lengthKilometers * 1000;
    }

    /**
     * Converts a velocity from kilometers/hour to meters/second.
     *
     * @param velocity Velocity that will be converted.
     * @return Velocity in meters/second.
     */
    public static double KilometersPerHourToMetersPerSecond(String velocity) {
        double velocityKilometersPerHour = Double.parseDouble(
                velocity.replaceAll("[^0-9.]", ""));

        return velocityKilometersPerHour * 1000 / 3600;
    }

    /**
     * Converts the arrival rate of vehicles on a node to vehicles per minute.
     * 
     * @param arrivalRate The arrival rate of vehicles on a node, it can be per
     * second, minute or hour.
     * @return The arrival rate of vehicles on a node per minute.
     */
    public static int arrivalRateToVehiclesPerMinute(String arrivalRate) {
        char unit = (arrivalRate.toLowerCase()).charAt(arrivalRate.length() - 1);
        
        int arrivalRatePerMinute = 0;
        
        switch (unit) {
            // Seconds
            case 's':
                arrivalRatePerMinute
                        = Integer.parseInt(arrivalRate.split(" ")[0]) * 60;
                break;
            // Minutes
            case 'm':
                arrivalRatePerMinute
                        = Integer.parseInt(arrivalRate.split(" ")[0]);
                break;
            // Hours
            case 'h':
                arrivalRatePerMinute
                        = (int) Math.ceil(Integer.parseInt(arrivalRate.split(" ")[0]) / 60.0);
                break;
            default:
                throw new IllegalArgumentException("The conversion is not "
                        + "possible.");
        }
        
        return arrivalRatePerMinute;
    }

}
