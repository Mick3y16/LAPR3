package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Moreira
 */
public class ExportHTMLTest {
    
    public ExportHTMLTest() {
    }

    /**
     * Test of exportNetworkAnalysis method, of class ExportHTML.
     */
    @Test
    public void testExportNetworkAnalysis() {
        System.out.println("exportNetworkAnalysis");
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
        NetworkAnalysis networkAnalysis = new NetworkAnalysis(testProject);
        networkAnalysis.setNodes(junction1, junction2);
        networkAnalysis.addVehicle(dummyVehicle);
        networkAnalysis.calculate(true, false, true);
        
        ExportHTML exportXML = new ExportHTML();
        exportXML.exportNetworkAnalysis(networkAnalysis, vehicles, "./test/xml/results.html");
        
        assertTrue("HTML file should have been created", new File("./test/xml/results.html").exists());
        assertTrue("File should have been deleted", new File("./test/xml/results.html").delete());
    }
    
}
