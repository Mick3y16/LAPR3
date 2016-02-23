package utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class UnitConverterTest {

    public UnitConverterTest() {
    }

    /**
     * Test of slopeToDegrees method, of class UnitConverter.
     */
    @Test
    public void testSlopeToDegrees() {
        System.out.println("slopeToDegrees");
        String slope = "-1.5%";
        String expResult = "-0,859";

        String result = String.format("%.3f", UnitConverter.slopeToDegrees(slope));

        System.out.println("result:"+result);
        
        assertTrue("Expected -0,859", result.equals(expResult));
    }

    /**
     * Test of KilometersToMeters method, of class UnitConverter.
     */
    @Test
    public void testKilometersToMeters() {
        System.out.println("KilometersToMeters");
        String length = "1.3 Km";
        String expResult = "1300,000";

        String result = String.format("%.3f", UnitConverter.KilometersToMeters(length));

        assertTrue("Expected 1300,000", result.equals(expResult));
    }

    /**
     * Test of KilometersPerHourToMetersPerSecond method, of class
     * UnitConverter.
     */
    @Test
    public void testKilometersPerHourToMetersPerSecond() {
        System.out.println("KilometersPerHourToMetersPerSecond");
        String velocity = "120 Km/h";
        String expResult = "33,333";

        String result = String.format("%.3f", UnitConverter.KilometersPerHourToMetersPerSecond(velocity));

        assertTrue("Expected 33,333", result.equals(expResult));

    }

    /**
     * Test of arrivalRateToVehiclesPerMinute method, of class UnitConverter.
     */
    @Test
    public void testArrivalRateToVehiclesPerMinute() {
        System.out.println("arrivalRateToVehiclesPerMinute");
        String arrivalPerSecond = "1 1/s",
                arrivalPerMinute = "10 1/m",
                arrivalPerHour = "120 1/h";

        // Seconds
        assertTrue("Expected 60 vehicles", UnitConverter.arrivalRateToVehiclesPerMinute(arrivalPerSecond) == 60);

        // Minutes
        assertTrue("Expceted 10 vehicles", UnitConverter.arrivalRateToVehiclesPerMinute(arrivalPerMinute) == 10);

        // Hours
        assertTrue("Expected 2 vehicles", UnitConverter.arrivalRateToVehiclesPerMinute(arrivalPerHour) == 2);
    }

}
