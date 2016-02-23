package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.graph.Edge;
import model.graph.Vertex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @author G11
 */
public class ProjectTest {

    private Project project;
    private Vehicle vehicle;
    private Junction j1, j2, j3, j4, j5, j6;
    private Section g, g1, g2, g3, g4, g5;

    public ProjectTest() {
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
        sg1.setMaximumVelocity(70);
        Segment sg2 = new Segment();
        sg2.setLength(15);
        sg2.setMaximumVelocity(70);
        this.g.getSegmentList().addSegment(sg1);
        this.g.getSegmentList().addSegment(sg2);

        this.g1 = new Section();
        this.g1.setKey(1);
        this.g1.setRoadName("G1");
        Segment sg3 = new Segment();
        sg3.setLength(10);
        sg3.setMaximumVelocity(80);
        Segment sg4 = new Segment();
        sg4.setLength(10);
        sg4.setMaximumVelocity(80);
        Segment sg5 = new Segment();
        sg5.setLength(10);
        sg5.setMaximumVelocity(80);
        this.g1.getSegmentList().addSegment(sg3);
        this.g1.getSegmentList().addSegment(sg4);
        this.g1.getSegmentList().addSegment(sg5);

        this.g2 = new Section();
        this.g2.setKey(2);
        this.g2.setRoadName("G2");
        Segment sg6 = new Segment();
        sg6.setLength(10);
        sg6.setMaximumVelocity(20);
        Segment sg7 = new Segment();
        sg7.setLength(10);
        sg7.setMaximumVelocity(20);
        this.g2.getSegmentList().addSegment(sg6);
        this.g2.getSegmentList().addSegment(sg7);

        this.g3 = new Section();
        this.g3.setKey(3);
        this.g3.setRoadName("G3");
        Segment sg8 = new Segment();
        sg8.setLength(10);
        sg8.setMaximumVelocity(20);
        this.g3.getSegmentList().addSegment(sg8);

        this.g4 = new Section();
        this.g4.setKey(4);
        this.g4.setRoadName("G4");
        Segment sg9 = new Segment();
        sg9.setLength(45);
        sg9.setMaximumVelocity(30);
        Segment sg10 = new Segment();
        sg10.setLength(45);
        sg10.setMaximumVelocity(30);
        this.g4.getSegmentList().addSegment(sg9);
        this.g4.getSegmentList().addSegment(sg10);

        this.g5 = new Section();
        this.g5.setKey(5);
        this.g5.setRoadName("G5");
        Segment sg11 = new Segment();
        sg11.setLength(20);
        sg11.setMaximumVelocity(100);
        Segment sg12 = new Segment();
        sg12.setLength(20);
        sg12.setMaximumVelocity(100);
        Segment sg13 = new Segment();
        sg13.setLength(20);
        sg13.setMaximumVelocity(100);
        Segment sg14 = new Segment();
        sg14.setLength(20);
        sg14.setMaximumVelocity(100);
        this.g5.getSegmentList().addSegment(sg11);
        this.g5.getSegmentList().addSegment(sg12);
        this.g5.getSegmentList().addSegment(sg13);
        this.g5.getSegmentList().addSegment(sg14);

        this.g.setRoadName("Road A");
        this.g1.setRoadName("Road A");
        this.g2.setRoadName("Road B");
        this.g3.setRoadName("Road C");
        this.g4.setRoadName("Road C");
        this.g5.setRoadName("Road D");
        this.project.addSection("A", "B", this.g);
        this.project.addSection("B", "C", this.g1);
        this.project.addSection("C", "E", this.g2);
        this.project.addSection("E", "F", this.g3);
        this.project.addSection("F", "D", this.g4);
        this.project.addSection("B", "D", this.g5);

        this.project.getVehicleList().addVehicle(this.vehicle);
    }

    @Before
    public void setUp() throws Exception {
    }

    /*
     * Test of set e get name methods, of the class Project.
     */
    @Test
    public void testSetAndGetName() {
        System.out.println("setAndGetName");
        Project instance = new Project();

        try {
            instance.setName(null);
            assertTrue("", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setName("");
            assertTrue("", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setName("text");
        assertTrue("The name should be text", instance.getName().equals("text"));
    }

    /*
     * Test of set e get description methods, of the class Project.
     */
    @Test
    public void testSetAndGetDescription() {
        System.out.println("setAndGetDescription");
        Project instance = new Project();

        try {
            instance.setName(null);
            assertTrue("", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setName("");
            assertTrue("", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setDescription("text");
        assertTrue("The description should be text", instance.getDescription().equals("text"));
    }

    /**
     * Test of getVehicleList method, of class Project.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        Project instance = new Project();

        Class<? extends VehicleList> result = instance.getVehicleList().getClass();
        Class<? extends VehicleList> expResult = new VehicleList().getClass();

        assertTrue("The class name should be the same.", result.toString().equals(expResult.toString()));
    }

    /**
     * Test of toSring method, of class Project.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        System.out.println("toStringHTML");
        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Segment seg1 = new Segment();
        seg1.setIndex(0);
        seg1.setMaximumNumberVehicles(10);
        Section sec1 = new Section();
        sec1.setRoadName("A1-j1-j2");
        sec1.setTypology("Highway");
        sec1.getSegmentList().addSegment(seg1);

        Project instance = new Project();
        instance.setName("ProjA");
        instance.setDescription("Project's description");
        instance.addJunction(j1);
        instance.addJunction(j2);
        instance.addSection("j1", "j2", sec1);

        String expResult = ""
                + "Name: ProjA\n"
                + "Description: Project's description\n"
                + "Roadmap: 2 intersections, 1 roads\n"
                + "j1 (0): \n"
                + "\tRoad name: A1-j1-j2\n"
                + "\tTypology: Highway\n"
                + "\tToll: 0.0\n"
                + "\tWind speed: 0.0m/s\n"
                + "\tWind orientation: 0.0º\n"
                + "\tList of segments:\n"
                + "\t\tSegment: 0\n"
                + "\t\t\tInitial Height: 0.0 m\n"
                + "\t\t\tAngle: 0,000 º\n"
                + "\t\t\tLenght: 0,000 m\n"
                + "\t\t\tMinimum velocity: 0.0 m/s\n"
                + "\t\t\tMaximum velocity: 0.0 m/s\n"
                + "\t\t\tMaximum number of vehicles: 10 vehicles\n"
                + "\n"
                + "j2 (1): \n"
                + "\n"
                + "List of Vehicles:\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of newJunction method, of class Project.
     */
    @Test
    public void testNewJunction() {
        System.out.println("newJunction");
        Project instance = new Project();

        Junction result = instance.newJunction();
        Junction expResult = new Junction();

        assertTrue("The results should be the same", result.equals(expResult));
    }

    /**
     * Test of addJunction method, of class Project.
     */
    @Test
    public void testAddJunction() {
        System.out.println("addJunction");
        Junction junction = new Junction();
        junction.setName("n01");
        Project instance = new Project();

        assertTrue("Junction should have been added", instance.addJunction(junction));

        assertFalse("Junction is already present and should not be added", instance.addJunction(junction));
    }

    /**
     * Test of newSection method, of class Project.
     */
    @Test
    public void testNewSection() {
        System.out.println("newSection");
        Project instance = new Project();

        Section result = instance.newSection();
        Section expResult = new Section();

        assertTrue("The results should be the same", result.equals(expResult));
    }

    /**
     * Test of addSection method, of class Project.
     */
    @Test
    public void testAddSection() {
        System.out.println("addSection");
        Project instance = new Project();

        Junction j1 = new Junction();
        j1.setName("n01");
        Junction j2 = new Junction();
        j2.setName("n02");

        instance.addJunction(j1);
        instance.addJunction(j2);

        Section section = new Section();
        section.setRoadName("A1-n01-n02");

        assertTrue("Section should have been added", instance.addSection("n01", "n02", section));
        assertFalse("Section shouldn't have been added", instance.addSection("n01", "n02", section));
        section.setRoadName("A1-n02-n01");
        assertTrue("Section should have been added", instance.addSection("n02", "n01", section));
    }

    /**
     * Test of allPaths method, of class Project.
     */
    @Test
    public void testAllPaths() {
        System.out.println("allPaths");
        ArrayList<LinkedList<Section>> expResult = new ArrayList<>();
        LinkedList<Section> listSections = new LinkedList<>();
        listSections.add(this.g);
        listSections.add(this.g1);
        LinkedList<Section> listSections2 = new LinkedList<>();
        listSections2.add(this.g5);
        listSections2.add(this.g4);
        listSections2.add(this.g3);
        listSections2.add(this.g2);
        expResult.add(listSections);
        expResult.add(listSections2);
        ArrayList<LinkedList<Section>> result = this.project.allPaths(this.j1, this.j3);

        for (int i = 0; i < result.size(); i++) {

            LinkedList<Section> resultsList = result.get(i);
            LinkedList<Section> expResultsList = expResult.get(i);

            for (int j = 0; j < resultsList.size(); j++) {
                Section resultSection = resultsList.get(i);
                Section expResultSection = expResultsList.get(i);

                assertTrue("Bananas", resultSection.equals(expResultSection));
            }
        }

    }

    /**
     * Test of validate method, of class Project.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Project instance = new Project();
        Junction i1 = new Junction();
        Junction i2 = new Junction();
        i1.setName("I1");
        i2.setName("I2");
        Section s1 = new Section();

        try {
            instance.validate(true, true);
            assertTrue("The project shouln't be valid", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setName("Project's name");
        instance.setDescription("Project's description");
        instance.addJunction(i1);
        instance.addJunction(i2);
        instance.addSection("I1", "I2", s1);

        assertTrue("The project should be valid", instance.validate(true, false));
    }

    /**
     * Test of toStringHTML method, of class Project.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Segment seg1 = new Segment();
        seg1.setIndex(0);
        seg1.setMaximumNumberVehicles(10);
        Section sec1 = new Section();
        sec1.setRoadName("A1-j1-j2");
        sec1.setTypology("Highway");
        sec1.getSegmentList().addSegment(seg1);

        Project instance = new Project();
        instance.setName("ProjA");
        instance.setDescription("Project's description");
        instance.addJunction(j1);
        instance.addJunction(j2);
        instance.addSection("j1", "j2", sec1);

        String expResult = ""
                + "Name: ProjA<br/>\n"
                + "Description: Project's description<br/>\n"
                + "Roadmap: 2 intersections, 1 roads.<br/>\n"
                + "<ul>\n"
                + "<li>j1<ul>\n"
                + "\t<li>Road name: A1-j1-j2</li>\n"
                + "\t<li>Typology: Highway</li>\n"
                + "\t<li>Toll: 0.0</li>\n"
                + "\t<li>Wind speed: 0.0 m/s</li>\n"
                + "\t<li>Wind orientation: 0.0º</li>\n"
                + "\t<li>	List of segments:<ul>\n"
                + "\t\t<li>Segment: 0<ul>\n"
                + "\t\t\t<li>Initial Height: 0.0m</li>\n"
                + "\t\t\t<li>Angle: 0,000º</li>\n"
                + "\t\t\t<li>Lenght: 0,000 m</li>\n"
                + "\t\t\t<li>Minimum velocity: 0.0 m/s</li>\n"
                + "\t\t\t<li>Maximum velocity: 0.0 m/s</li>\n"
                + "\t\t\t<li>Maximum number of vehicles: 10 vehicles</li>\n"
                + "\t\t</li></ul>\n"
                + "\t</ul>\n"
                + "\t</li>\n"
                + "</ul></li>\n"
                + "<li>j2<br/>\n"
                + "</li>\n"
                + "</ul>\n"
                + "List of Vehicles:<ul>\n"
                + "</ul>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJunctions method, of class Project.
     */
    @Test
    public void testGetJunctions() {
        System.out.println("getJunctions");

        Junction j1 = new Junction();
        j1.setName("A");
        Junction j2 = new Junction();
        j2.setName("B");
        Junction j3 = new Junction();
        j3.setName("C");
        Junction j4 = new Junction();
        j4.setName("D");
        Junction j5 = new Junction();
        j5.setName("E");

        List<Junction> junctionList = new ArrayList<>();
        junctionList.add(j1);
        junctionList.add(j2);
        junctionList.add(j3);
        junctionList.add(j4);
        junctionList.add(j5);

        Project instance = new Project();
        instance.addJunction(j1);
        instance.addJunction(j2);
        instance.addJunction(j3);
        instance.addJunction(j4);
        instance.addJunction(j5);

        assertTrue("List should contain the same objects", instance.getJunctions().equals(junctionList));
    }

    /**
     * Test of getRoadNames method, of class Project.
     */
    @Test
    public void testGetRoadNames() {
        System.out.println("getRoadNames");
        List<String> expResult = new ArrayList();
        expResult.add("Road A");
        expResult.add("Road D");
        expResult.add("Road B");
        expResult.add("Road C");
        List<String> result = project.getRoadNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJunctionByName method, of class Project.
     */
    @Test
    public void testGetJunctionByName() {
        System.out.println("getJunctionByName");
        String junctionName = "A";
        Junction expResult = j1;
        Junction result = project.getJunctionByName(junctionName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSections method, of class Project.
     */
    @Test
    public void testGetSections() {
        System.out.println("getSections");
        Section s1 = new Section();
        Section s2 = new Section();
        Section s3 = new Section();
        Section s4 = new Section();
        Junction j1 = new Junction();
        j1.setName("A");
        Junction j2 = new Junction();
        j2.setName("B");
        Junction j3 = new Junction();
        j3.setName("C");
        Junction j4 = new Junction();
        j4.setName("D");
        Junction j5 = new Junction();
        j5.setName("E");

        Project instance = new Project();
        List<Section> expResult = new ArrayList<>();
        expResult.add(s1);
        expResult.add(s2);
        expResult.add(s3);
        expResult.add(s4);
        instance.addJunction(j1);
        instance.addJunction(j2);
        instance.addJunction(j3);
        instance.addJunction(j4);
        instance.addJunction(j5);
        instance.addSection(j1.getName(), j2.getName(), s1);
        instance.addSection(j2.getName(), j3.getName(), s2);
        instance.addSection(j3.getName(), j4.getName(), s3);
        instance.addSection(j4.getName(), j5.getName(), s4);
        assertTrue("List should contain the same objects", instance.getSections().equals(expResult));

    }

    /**
     * Test of getVehicleByName method, of class Project.
     */
    @Test
    public void testGetVehicleByName() {
        System.out.println("getVehicleByName");
        String vehicleName = "name";
        Vehicle expResult = new VehicleCombustion();
        expResult = new VehicleCombustion();
        expResult.setName("name");

        expResult.setDragCoefficient(0.35);
        expResult.setMass(1000);
        expResult.setRollingResistanceCoefficient(0.7);
        Vehicle result = this.project.getVehicleByName(vehicleName);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifySection method, of class Project.
     */
    @Test
    public void testVerifySection() {
        System.out.println("verifySection");
        LinkedList<Section> path = new LinkedList<>();
        Edge<Junction, Section> edge = this.project.getCorrespondentEdge(this.g);
        path.add(this.g);
        boolean expResult = true;
        boolean result = this.project.verifySection(path, edge);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCorrespondentEdge method, of class Project.
     */
    @Test
    public void testGetCorrespondentEdge() {
        System.out.println("getCorrespondentEdge");
        Edge<Junction, Section> expResult = new Edge<>();
        expResult.setElement(this.g);
        Vertex<Junction, Section> vOrig = new Vertex<>();
        vOrig.setElement(this.j1);
        vOrig.setKey(0);
        expResult.setVOrig(vOrig);
        Vertex<Junction, Section> vDest = new Vertex<>();
        vDest.setElement(this.j2);
        vDest.setKey(1);
        expResult.setVDest(vDest);
        expResult.setWeight(0.0);
        Edge<Junction, Section> result = this.project.getCorrespondentEdge(this.g);
        assertEquals(expResult, result);
    }

}
