package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.dao.DaoManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import utils.Tuple;
import utils.UnitConverter;

/**
 * @author G11
 */
public class NetworkAnalysisTest {

    private Project project;
    private Vehicle vehicle;
    private Junction j1, j2, j3, j4, j5, j6;
    private Section g, g1, g2, g3, g4, g5, g6;

    public NetworkAnalysisTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.project = new Project();

        this.vehicle = new VehicleCombustion();
        this.vehicle.setName("name");

        this.vehicle.setDragCoefficient(0.35);
        this.vehicle.setMass(1000);
        this.vehicle.setRollingResistanceCoefficient(0.7);

        this.j1 = new Junction();
        this.j1.setName("A");
        this.project.addJunction(this.j1);
        this.j2 = new Junction();
        this.j2.setName("B");
        this.project.addJunction(this.j2);
        this.j3 = new Junction();
        this.j3.setName("C");
        this.project.addJunction(this.j3);
        this.j4 = new Junction();
        this.j4.setName("D");
        this.project.addJunction(this.j4);
        this.j5 = new Junction();
        this.j5.setName("E");
        this.project.addJunction(this.j5);
        this.j6 = new Junction();
        this.j6.setName("F");
        this.project.addJunction(this.j6);

        this.g = new Section();
        this.g.setRoadName("G");
        this.g.setKey(0);
        Segment sg1 = new Segment();
        sg1.setLength(15);
        sg1.setMaximumVelocity(19.4);
        sg1.setIndex(0);
        Segment sg2 = new Segment();
        sg2.setIndex(1);
        sg2.setLength(15);
        sg2.setMaximumVelocity(19.4);
        this.g.getSegmentList().addSegment(sg1);
        this.g.getSegmentList().addSegment(sg2);

        this.g1 = new Section();
        this.g1.setKey(1);
        this.g1.setRoadName("G1");
        Segment sg3 = new Segment();
        sg3.setIndex(2);
        sg3.setLength(10);
        sg3.setMaximumVelocity(22.2);
        Segment sg4 = new Segment();
        sg4.setIndex(3);
        sg4.setLength(10);
        sg4.setMaximumVelocity(22.2);
        Segment sg5 = new Segment();
        sg5.setLength(10);
        sg5.setIndex(4);
        sg5.setMaximumVelocity(22.2);
        this.g1.getSegmentList().addSegment(sg3);
        this.g1.getSegmentList().addSegment(sg4);
        this.g1.getSegmentList().addSegment(sg5);

        this.g2 = new Section();
        this.g2.setKey(2);
        this.g2.setRoadName("G2");
        Segment sg6 = new Segment();
        sg6.setLength(10);
        sg6.setIndex(5);
        sg6.setMaximumVelocity(20);
        Segment sg7 = new Segment();
        sg7.setLength(10);
        sg7.setIndex(6);
        sg7.setMaximumVelocity(20);
        this.g2.getSegmentList().addSegment(sg6);
        this.g2.getSegmentList().addSegment(sg7);

        this.g3 = new Section();
        this.g3.setKey(3);
        this.g3.setRoadName("G3");
        Segment sg8 = new Segment();
        sg8.setLength(10);
        sg8.setIndex(7);
        sg8.setMaximumVelocity(20);
        this.g3.getSegmentList().addSegment(sg8);

        this.g4 = new Section();
        this.g4.setKey(4);
        this.g4.setRoadName("G4");
        Segment sg9 = new Segment();
        sg9.setLength(45);
        sg9.setIndex(8);
        sg9.setMaximumVelocity(30);
        Segment sg10 = new Segment();
        sg10.setLength(45);
        sg10.setIndex(9);
        sg10.setMaximumVelocity(30);
        this.g4.getSegmentList().addSegment(sg9);
        this.g4.getSegmentList().addSegment(sg10);

        this.g5 = new Section();
        this.g5.setKey(5);
        this.g5.setRoadName("G5");
        Segment sg11 = new Segment();
        sg11.setLength(20);
        sg11.setIndex(10);
        sg11.setMaximumVelocity(40);
        Segment sg12 = new Segment();
        sg12.setLength(20);
        sg12.setIndex(11);
        sg12.setMaximumVelocity(40);
        Segment sg13 = new Segment();
        sg13.setLength(20);
        sg13.setIndex(12);
        sg13.setMaximumVelocity(40);
        Segment sg14 = new Segment();
        sg14.setLength(20);
        sg14.setIndex(13);
        sg14.setMaximumVelocity(100);
        this.g5.getSegmentList().addSegment(sg11);
        this.g5.getSegmentList().addSegment(sg12);
        this.g5.getSegmentList().addSegment(sg13);
        this.g5.getSegmentList().addSegment(sg14);

        this.g6 = new Section();
        this.g6.setKey(6);
        this.g6.setRoadName("G6");
        Segment sg15 = new Segment();
        sg15.setLength(70);
        sg15.setIndex(14);
        sg15.setMaximumVelocity(13.9);
        this.g6.getSegmentList().addSegment(sg15);

        this.project.addSection(this.j1.toString(), this.j2.toString(), this.g);
        this.project.addSection(this.j2.toString(), this.j3.toString(), this.g1);
        this.project.addSection(this.j3.toString(), this.j5.toString(), this.g2);
        this.project.addSection(this.j5.toString(), this.j6.toString(), this.g3);
        this.project.addSection(this.j6.toString(), this.j4.toString(), this.g4);
        this.project.addSection(this.j2.toString(), this.j4.toString(), this.g5);
        this.project.addSection(this.j1.toString(), this.j3.toString(), this.g6);
        this.vehicle.setWheelSize(0.3);
        //this.vehicle.setRpmMaximum(3);
        VehicleCombustion vh = (VehicleCombustion) vehicle;
        vehicle.setFrontalArea(2);
        vehicle.setDragCoefficient(0.3);
        vehicle.setRollingResistanceCoefficient(0.78);
        vehicle.setWheelSize(0.2);
        vh.getGearbox().insertGearAndGearRatio(1, 0.5);
        vh.setFinalDrive(0.7);
        vh.setRpmMaximum(3000);
        vehicle.setMass(1100);
        this.project.getVehicleList().addVehicle(vehicle);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of setNodes method, of class NetworkAnalysis.
     */
    @Test
    public void testSetNodes() {
        System.out.println("setNodes");
        Junction originNode = new Junction();
        Junction destinyNode = new Junction();
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        instance.setNodes(originNode, destinyNode);
    }

    /**
     * Test of calculate method, of class NetworkAnalysis.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        boolean fastestPath = true;
        boolean efficientPath = true;
        boolean realPath = true;
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        instance.calculate(fastestPath, efficientPath, realPath);
    }

    /**
     * Test of calculateFastestPath method, of class NetworkAnalysis.
     */
    @Test
    public void testCalculateFastestPath() {
        System.out.println("calculateFastestPath");
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        instance.setNodes(this.j1, this.j3);
        instance.addVehicle(vehicle);
        instance.calculate(true, false, false);
        instance.calculateFastestPath(vehicle);
    }

    /**
     * Test of calculateEfficientPath method, of class NetworkAnalysis.
     */
    @Test
    public void testCalculateEfficientPath() {
        System.out.println("calculateEfficientPath");
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        instance.setNodes(this.j1, this.j3);
        instance.addVehicle(vehicle);
        instance.calculate(true, false, false);
        instance.calculateEfficientPath(vehicle);
    }

    /**
     * Test of addVehicle method, of class NetworkAnalysis.
     */
    @Test
    public void testAddVehicle() {
        System.out.println("addVehicle");
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        instance.addVehicle(this.vehicle);
    }

    /**
     * Test of getResultsFastest method, of class NetworkAnalysis. RITA
     */
    @Test
    public void testGetResultsFastest() {
        System.out.println("getResultsFastest");
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> expResult = new LinkedHashMap<>();
        instance.setNodes(this.j1, this.j3);
        VehicleCombustion vehicleTestFastest = new VehicleCombustion();
        vehicleTestFastest.setFrontalArea(2);
        vehicleTestFastest.setDragCoefficient(0.2);
        vehicleTestFastest.setRollingResistanceCoefficient(0.35);
        vehicleTestFastest.setMass(1200);
        vehicleTestFastest.setWheelSize(0.2);
        vehicleTestFastest.getGearbox().insertGearAndGearRatio(1, 0.5);
        vehicleTestFastest.setFinalDrive(0.7);
        vehicleTestFastest.setRpmMaximum(3000);
        vehicleTestFastest.setName("FastestTestVehicle");
        this.project.getVehicleList().addVehicle(vehicleTestFastest);
        instance.addVehicle(vehicleTestFastest);
        instance.calculate(true, false, false);
        LinkedList<SectionResults> results = new LinkedList<>();
        SectionResults s = new SectionResults();
        s.setName(this.g.getRoadName());
        s.setEnergy(126372.246);
        s.setTimeTravel(1.546391753);
        s.setToll(this.g.getToll());
        s.setType(SectionResults.FASTEST);
        SectionResults s1 = new SectionResults();
        s1.setName(this.g1.getRoadName());
        s1.setEnergy(82264.917);
        s1.setTimeTravel(1.351351351);
        s1.setToll(this.g1.getToll());
        s1.setType(SectionResults.FASTEST);
        results.add(s);
        results.add(s1);
        double finalEnergy = 2.8977, time = 323488.62;

        expResult.put(vehicleTestFastest.getName(),
                new Tuple<>(results, new Tuple(finalEnergy, time)));
        Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> result = instance.getResultsFastest();

        assertEquals(String.format("%.2f", expResult.get(vehicleTestFastest.getName()).
                getSecondElement().getSecondElement()), String.format("%.2f",
                        result.get(vehicleTestFastest.getName()).
                        getSecondElement().getSecondElement()));

        assertEquals(String.format("%.4f", expResult.get(vehicleTestFastest.getName()).
                getSecondElement().getFirstElement()), String.format("%.4f",
                        result.get(vehicleTestFastest.getName()).
                        getSecondElement().getFirstElement()));
        assertEquals(Arrays.toString(expResult.get(vehicleTestFastest.getName()).
                getFirstElement().toArray()),
                Arrays.toString(result.get(vehicleTestFastest.getName()).
                        getFirstElement().toArray()));
    }

    /**
     * Test of getResultsShortest method, of class NetworkAnalysis. RITA
     */
    @Test
    public void testGetResultsShortest() {
        System.out.println("getResultsShortest");
        NetworkAnalysis instance = new NetworkAnalysis(this.project);
        Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> expResult = new LinkedHashMap<>();
        instance.setNodes(this.j1, this.j3);
        instance.addVehicle(this.vehicle);
        instance.calculate(false, true, false);
        LinkedList<SectionResults> caminho = new LinkedList<>();
        SectionResults s = new SectionResults();
        s.setName(this.g.getRoadName());
        s.setEnergy(0.09521128524599998);
        s.setTimeTravel(0.21428571428571427);
        s.setToll(0.0);
        SectionResults s1 = new SectionResults();
        s1.setName(this.g1.getRoadName());
        s1.setEnergy(0.09521128524599998);
        s1.setTimeTravel(0.125);
        s1.setToll(0.0);
        caminho.add(s);
        caminho.add(s1);
        expResult.put(this.vehicle.getName(), new Tuple(caminho, new Tuple(2.897743103928671, 578665.7299999999)));
        Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> result = instance.getResultsShortest();
        System.out.println("Result: " + result.toString());
        assertEquals(expResult.toString(), result.toString());

    }

    /**
     * Test of calculateRealEfficientPath method, of class NetworkAnalysis.
     * SORAIA
     */
    @Test
    public void testCalculateRealEfficientPath() {
        System.out.println("calculateRealEfficientPath");
        Project p = new Project();
        
        String filePath = "./test/xml/Project2.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(p, filePath);
        
        
        Vehicle vehicle = p.getVehicleByName("Dummy01");
        NetworkAnalysis instance = new NetworkAnalysis(p);
        instance.addVehicle(vehicle);
        instance.setNodes(p.getJunctionByName("n0"), p.getJunctionByName("n2"));
        instance.calculate(false,false,true);
        
        Map<String, Tuple<LinkedList<SectionResults>, Double[]>> map = instance.getResultsReal();
        
        
        Double[] expectedResult = new Double[]{384d,(double)Math.round(9187682.143),(double)Math.round(1394.187611)};
        Double[] result = map.get("Dummy01").getSecondElement();
        for (int i=0;i<result.length;i++)
        {
            result[i] = (double)Math.round(result[i]);
        }
        
        assertEquals(Arrays.toString(expectedResult),Arrays.toString(result));
    }

    /**
     * Test of getForceInSegment method, of class NetworkAnalysis.
     */
    @Test
    public void testGetForceInSegment() {
        System.out.println("getForceInSegment");
        Section section = new Section();
        section.setWindOrientation(30);
        section.setWindSpeed(UnitConverter.KilometersPerHourToMetersPerSecond("15"));
        VehicleCombustion vehicle = new VehicleCombustion();
        vehicle.setFrontalArea(2);
        vehicle.setDragCoefficient(0.2);
        vehicle.setRollingResistanceCoefficient(0.35);
        vehicle.setWheelSize(0.0982);
        vehicle.getGearbox().insertGearAndGearRatio(1, 0.5);
        vehicle.setFinalDrive(1);
        vehicle.setRpmMaximum(4000);
        vehicle.setMass(1200);
        Segment segment = new Segment();
        segment.setMaximumVelocity(140);
        segment.setAngle(0);
        segment.setLength(1000);
        segment.setInitialHeight(0);
        NetworkAnalysis instance = new NetworkAnalysis(new Project());
        Segment seg2 = new Segment();
        seg2.setMaximumVelocity(140);
        seg2.setAngle(-45);
        seg2.setLength(1000);
        seg2.setInitialHeight(0);

        double expResult = 4465;
        double result = Math.round(instance.getForceInSegment(section, vehicle, segment));
        assertEquals(expResult, result, 0.0);

        expResult = 0;
        result = Math.round(instance.getForceInSegment(section, vehicle, seg2));
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaximumVelocityIn method, of class NetworkAnalysis.
     */
    @Test
    public void testGetMaximumVelocityIn() {
        System.out.println("getMaximumVelocityIn");
        Section section = new Section();
        section.setWindOrientation(30);
        section.setWindSpeed(UnitConverter.KilometersPerHourToMetersPerSecond("15"));
        VehicleCombustion vehicle = new VehicleCombustion();
        vehicle.setFrontalArea(2);
        vehicle.setDragCoefficient(0.2);
        vehicle.setRollingResistanceCoefficient(0.35);
        vehicle.setWheelSize(0.0982);
        vehicle.getGearbox().insertGearAndGearRatio(1, 0.5);
        vehicle.setFinalDrive(1);
        vehicle.setRpmMaximum(4000);
        vehicle.setMass(1200);
        Segment segment = new Segment();
        segment.setMaximumVelocity(140);
        segment.setAngle(0);
        segment.setLength(1000);
        segment.setInitialHeight(0);
        NetworkAnalysis instance = new NetworkAnalysis(new Project());
        String expResult = "37,5";
        String result = String.format("%.1f", instance.getMaximumVelocityIn(segment, vehicle, section));
        assertEquals(expResult, result);
    }

    /**
     * Test of getResultsReal method, of class NetworkAnalysis. SORAIA
     */
    @Test
    public void testGetResultsReal() {
        System.out.println("getResultsReal");
        NetworkAnalysis instance = new NetworkAnalysis(new Project());
        Map<String, Tuple<LinkedList<SectionResults>, Double[]>> expResult = new LinkedHashMap();
        assertEquals(expResult.getClass(), instance.getResultsReal().getClass());
    }

    /**
     * Test of clearResults method, of class NetworkAnalysis.
     */
    @Test
    public void testClearResults() {
        System.out.println("clearResults");
        NetworkAnalysis instance = new NetworkAnalysis(new Project());
        instance.clearResults();
    }

    /**
     * Test of toStringHTML method, of class NetworkAnalysis.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Project testProject = new Project();
        Junction junction1 = new Junction();
        junction1.setName("n0");
        Junction junction2 = new Junction();
        junction2.setName("n2");

        ImportXML importXML = new ImportXML();
        importXML.importProject(testProject, "./test/xml/Project.xml");
        Vehicle dummyVehicle = testProject.getVehicleByName("Dummy02");

        List<String> vehicles = new ArrayList<>();
        vehicles.add("Dummy02");
        NetworkAnalysis instance = new NetworkAnalysis(testProject);
        instance.setNodes(junction1, junction2);
        instance.addVehicle(dummyVehicle);
        instance.calculate(true, false, false);
        instance.calculateFastestPath(dummyVehicle);

        String expResult = ""
                + "<h1>Network Static Analysis Results</h1><br/><h3>Fastest Path Algorithm Results</h3>\n"
                + "<table>\n"
                + "	<tr><th>Vehicle Name</th><th>Vehicle Path</th><th>Workload Energy</th><th>Time Spent</th></tr>\n"
                + "	<tr><td>Dummy02</td><td>E01-n0-n2; </td><td>3,603e+06 J</td><td>384.0 s</td></tr>\n"
                + "</table>\n"
                + "\n"
                + "<h3>Theoretical Most Efficient Algorithm Results</h3>\n"
                + "<ul><li>No vehicle was analised under the theoretical most efficient path algorithm.</li></ul>\n"
                + "\n"
                + "<h3>Real Most Efficient Algorithm Results</h3>\n"
                + "<ul><li>No vehicle was analised under the real most efficient path algorithm.</li></ul>\n";

        String result = instance.toStringHTML(vehicles);
        assertEquals(expResult, result);
    }

}
