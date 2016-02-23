package model;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.UnitConverter;

/**
 * Represents a class responsible for importing XML files.
 *
 * @author G11
 */
public class ImportXML implements Importable {

    /**
     * Type of direction of a section
     */
    private final String directionOfSegment = "bidirectional";

    /**
     * Attributes found in the file.
     */
    private final String idAttribute = "id";
    private final String descriptionAttribute = "description";
    private final String nameAttribute = "name";
    private final String beginAttribute = "begin";
    private final String endAttributes = "end";

    /**
     * Vehicle Types.
     */
    private final String combustionVehicle = "combustion";
    private final String hybridVehicle = "hybrid";
    private final String electricVehicle = "electric";

    /**
     * Tags used in the decoding of the project file data.
     */
    private final String networkTag = "Network";
    //private final String nodeListTag = "node_list";
    private final String nodeTag = "node";
    //private final String sectionListTag = "section_list";
    private final String roadSectionTag = "road_section";
    private final String roadTag = "road";
    private final String typologyTag = "typology";
    private final String directionTag = "direction";
    private final String tollTag = "toll";
    private final String windDirectionTag = "wind_direction";
    private final String windSpeedTag = "wind_speed";
    //private final String segmentListTag = "segment_list";
    private final String segmentTag = "segment";
    private final String heightTag = "height";
    private final String slopeTag = "slope";
    private final String lengthTag = "length";
    private final String maxVelocityTag = "max_velocity";
    private final String minVelocityTag = "min_velocity";
    private final String numberVehiclesTag = "number_vehicles";
    private final String vehicleListTag = "vehicle_list";
    private final String vehicleTag = "vehicle";
    private final String typeTag = "type";
    private final String motorizationTag = "motorization";
    private final String fuelTag = "fuel";
    private final String massTag = "mass";
    private final String loadTag = "load";
    private final String dragCoefficientTag = "drag";
    private final String frontalAreaTag = "frontal_area";
    private final String rollingResistanceCoefficientTag = "rrc";
    private final String wheelSizeTag = "wheel_size";
    //private final String velocityLimitListTag = "velocity_limit_list";
    private final String velocityLimitTag = "velocity_limit";
    private final String segmentTypeTag = "segment_type";
    private final String limitTag = "limit";
    //private final String energyTag = "energy";
    private final String minRPMTag = "min_rpm";
    private final String maxRPMTag = "max_rpm";
    private final String finalDriveRatioTag = "final_drive_ratio";
    private final String energyRegenerationRatioTag = "energy_regeneration_ratio";
    //private final String gearListTag = "gear_list";
    private final String gearTag = "gear";
    private final String ratioTag = "ratio";
    //private final String throttleListTag = "throttle_list";
    private final String throttleTag = "throttle";
    private final String regimeTag = "regime";
    private final String torqueTag = "torque";
    private final String rpmLowTag = "rpm_low";
    private final String rpmHighTag = "rpm_high";
    private final String SFCTag = "SFC";

    /**
     * Tags used in the decoding of the simulation file data.
     */
    private final String simulationTag = "Simulation";
    //private final String trafficListTag = "traffic_list";
    private final String trafficPatternTag = "traffic_pattern";
    private final String arrivalRateTag = "arrival_rate";

    /**
     * Creates an instance of ImportXML, which will serve as a XML importer.
     */
    public ImportXML() {
    }

    /**
     * Imports the data contained in a file into a simulation, loading the
     * traffic found.
     *
     * @param simulation Simulation that will contain the imported traffic.
     * @param filePath Path to the file which contains the data.
     */
    @Override
    public boolean importSimulation(Simulation simulation, String filePath) {
        try {
            // Start by opening the file containing the data.
            Document doc = readFile(filePath);
            Node networkNode = doc.getElementsByTagName(simulationTag).item(0);

            if (networkNode.getNodeType() == Node.ELEMENT_NODE) {
                Element networkElement = (Element) networkNode;

                simulation.setName(networkElement.getAttribute(idAttribute));
                simulation.setDescription(
                        networkElement.getAttribute(descriptionAttribute));

                extractTraffic(networkElement, simulation);
            }

            return simulation.validatesSimulation();
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new IllegalArgumentException("An error occured while"
                    + " attempting to import the XML file.");
        }
    }

    /**
     * Imports the data contained in a file into a project, loading all the
     * roads and vehicles found.
     *
     * @param project Project that will contain the imported roads and vehicles.
     * @param filePath Path to the file which contains the data.
     */
    @Override
    public boolean importProject(Project project, String filePath) {
        try {
            // Start by opening the file containing the data.
            Document doc = readFile(filePath);

            Node networkNode = doc.getElementsByTagName(networkTag).item(0);

            if (networkNode.getNodeType() == Node.ELEMENT_NODE) {
                Element networkElement = (Element) networkNode;

                project.setName(networkElement.getAttribute(idAttribute));
                project.setDescription(
                        networkElement.getAttribute(descriptionAttribute));

                // Extracting the list of nodes
                extractNodes(networkElement, project);

                // Extracting the list of Sections
                extractSections(networkElement, project);

                // Extracting the list of Vehicles
                extractVehicles(networkElement, project);
            }

            return project.validate(true, true);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new IllegalArgumentException("An error occured while"
                    + " attempting to import the XML file.");
        }
    }

    /**
     * Imports the data contained in a file into a project, loading all the
     * roads found.
     *
     * @param project Project that will contain the imported roads.
     * @param filePath Path to the file which contains the data.
     */
    @Override
    public boolean importRoads(Project project, String filePath) {
        try {
            // Start by opening the file containing the data.
            Document doc = readFile(filePath);

            Node networkNode = doc.getElementsByTagName(networkTag).item(0);

            if (networkNode.getNodeType() == Node.ELEMENT_NODE) {
                Element networkElement = (Element) networkNode;

                // Extracting the list of nodes
                extractNodes(networkElement, project);

                // Extracting the list of Sections
                extractSections(networkElement, project);
            }

            // Validates the project and its roads
            return project.validate(true, false);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new IllegalArgumentException("An error occured while"
                    + " attempting to import the XML file.");
        }
    }

    /**
     * Imports the data contained in a file into a project, loading all the
     * vehicles found.
     *
     * @param project Project that will contain the importedd vehicles.
     * @param filePath Path to the file which contains the data.
     */
    @Override
    public boolean importVehicles(Project project, String filePath) {
        try {
            // Start by opening the file containing the data.
            Document doc = readFile(filePath);

            Node vehicleListNode = doc.getElementsByTagName(vehicleListTag).item(0);

            if (vehicleListNode.getNodeType() == Node.ELEMENT_NODE) {
                Element vehicleListElement = (Element) vehicleListNode;

                // Extracting the list of Vehicles
                extractVehicles(vehicleListElement, project);
            }

            // Validates the project and its roads
            return project.validate(false, true);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new IllegalArgumentException("An error occured while"
                    + " attempting to import the XML file.");
        }
    }

    /**
     * Extracts all the vehicles in the vehicles list.
     *
     * @param networkElement Element that contains all the vehicles.
     * @param project Project that will contain the extracted vehicles.
     */
    public void extractVehicles(Element networkElement, Project project) {
        NodeList vehicleNodeList = networkElement.getElementsByTagName(vehicleTag);

        VehicleList vehicleList = project.getVehicleList();

        for (int i = 0; i < vehicleNodeList.getLength(); i++) {
            Node vehicleNode = vehicleNodeList.item(i);

            if (vehicleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element vehicleElement = (Element) vehicleNode;

                Vehicle vehicle;
                String motorization = vehicleElement.getElementsByTagName(
                        motorizationTag).item(0).getTextContent().toLowerCase();
                switch (motorization) {
                    case combustionVehicle:
                        vehicle = vehicleList.newVehicleCombustion();
                        extractVehicleCombustion(vehicleElement,
                                (VehicleCombustion) vehicle);

                        break;
                    case electricVehicle:
                        vehicle = vehicleList.newVehicleElectric();
                        extractVehicleElectric(vehicleElement,
                                (VehicleElectric) vehicle);

                        break;
                    default:
                        throw new IllegalArgumentException("There is no vehicle"
                                + " with the following motorization.");
                }

                vehicle.validateVehicle();
                vehicleList.addVehicle(vehicle);
            }
        }
    }

    /**
     * Extracts all the traffic in the simulation.
     *
     * @param networkElement Element that contains all the traffic.
     * @param simulation Simulation that will contain the extracted traffic.
     */
    private void extractTraffic(Element networkElement, Simulation simulation) {
        SimulationTraffic simulationTraffic
                = simulation.getSimulationTraffic();
        Project project = simulation.getOpenProject();

        NodeList nodeList = networkElement.getElementsByTagName(trafficPatternTag);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node simulationNode = nodeList.item(i);

            if (simulationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element simulationElement = (Element) simulationNode;

                Traffic traffic = simulationTraffic.newTraffic();
                traffic.setBeginNode(project.getJunctionByName(simulationElement.getAttribute(beginAttribute)));
                traffic.setEndNode(project.getJunctionByName(simulationElement.getAttribute(endAttributes)));
                traffic.setVehicle(project.getVehicleByName(simulationElement.getElementsByTagName(vehicleTag).item(0).getTextContent()));
                traffic.setArrivalRate(UnitConverter.arrivalRateToVehiclesPerMinute(simulationElement.getElementsByTagName(arrivalRateTag).item(0).getTextContent()));

                traffic.validate();
                simulationTraffic.addTraffic(traffic);
            }

        }

        simulationTraffic.validate();
    }

    /**
     * Extract all the data in the electric vehicle.
     *
     * @param vehicleElement
     * @param vehicle
     */
    public void extractVehicleElectric(Element vehicleElement,
            VehicleElectric vehicle) {
        extractVehicle(vehicleElement, vehicle);

        vehicle.setRpmMinimum(
                Integer.parseInt(vehicleElement.getElementsByTagName(
                        minRPMTag).item(0).getTextContent()));
        vehicle.setRpmMaximum(
                Integer.parseInt(vehicleElement.getElementsByTagName(
                        maxRPMTag).item(0).getTextContent()));
        vehicle.setFinalDrive(Double.parseDouble(
                vehicleElement.getElementsByTagName(
                        finalDriveRatioTag).item(0).getTextContent()));
        vehicle.setEnergyRegenerationRatio(
                Double.parseDouble(vehicleElement.getElementsByTagName(
                        energyRegenerationRatioTag).item(0).getTextContent()));

        extractGearboxVE(vehicleElement, vehicle);

        extractThrottlesVE(vehicleElement, vehicle);
    }

    /**
     * Extract all the data in the combustion vehicle.
     *
     * @param vehicleElement Element that contains all the data of the
     * combustion vehicle.
     * @param vehicle Vehicle that will contain the extracted data.
     */
    public void extractVehicleCombustion(Element vehicleElement,
            VehicleCombustion vehicle) {
        extractVehicle(vehicleElement, vehicle);

        vehicle.setRpmMinimum(
                Integer.parseInt(vehicleElement.getElementsByTagName(
                        minRPMTag).item(0).getTextContent()));
        vehicle.setRpmMaximum(
                Integer.parseInt(vehicleElement.getElementsByTagName(
                        maxRPMTag).item(0).getTextContent()));
        vehicle.setFinalDrive(Double.parseDouble(
                vehicleElement.getElementsByTagName(
                        finalDriveRatioTag).item(0).getTextContent()));

        extractGearboxVC(vehicleElement, vehicle);

        extractThrottlesVC(vehicleElement, vehicle);
    }

    /**
     * Extracts all data in the vehicle.
     *
     * @param vehicleElement Element that contains all the data of the vehicle.
     * @param vehicle Vehicle that will contain the extracted data.
     */
    public void extractVehicle(Element vehicleElement, Vehicle vehicle) {
        vehicle.setName(vehicleElement.getAttribute(nameAttribute));
        vehicle.setDescription(vehicleElement.getAttribute(descriptionAttribute));
        vehicle.setType(
                vehicleElement.getElementsByTagName(
                        typeTag).item(0).getTextContent());
        vehicle.setFuel(
                vehicleElement.getElementsByTagName(
                        fuelTag).item(0).getTextContent());
        vehicle.setMass(
                Double.parseDouble(vehicleElement.getElementsByTagName(
                        massTag).item(0).getTextContent().split(" ")[0]));
        vehicle.setLoad(
                Double.parseDouble(vehicleElement.getElementsByTagName(
                        loadTag).item(0).getTextContent().split(" ")[0]));
        vehicle.setDragCoefficient(
                Double.parseDouble(vehicleElement.getElementsByTagName(
                        dragCoefficientTag).item(0).getTextContent()));
        vehicle.setFrontalArea(Double.parseDouble(
                vehicleElement.getElementsByTagName(
                        frontalAreaTag).item(0).getTextContent()));
        vehicle.setRollingResistanceCoefficient(
                Double.parseDouble(vehicleElement.getElementsByTagName(
                        rollingResistanceCoefficientTag).item(0).getTextContent()));
        vehicle.setWheelSize(
                Double.parseDouble(vehicleElement.getElementsByTagName(
                        wheelSizeTag).item(0).getTextContent()));

        extractVelocities(vehicleElement, vehicle);
    }

    /**
     * Extracts all the velocities in the vehicle.
     *
     * @param vehicleElement Element that contains all the velocities of the
     * vehicle.
     * @param vehicle Vehicle that will contain the extracted velocities.
     */
    public void extractVelocities(Element vehicleElement, Vehicle vehicle) {
        NodeList velocityLimitNodeList = vehicleElement.getElementsByTagName(
                velocityLimitTag);

        for (int i = 0; i < velocityLimitNodeList.getLength(); i++) {
            Node velocityLimitNode = velocityLimitNodeList.item(i);

            if (velocityLimitNode.getNodeType() == Node.ELEMENT_NODE) {
                Element velocityLimitElement = (Element) velocityLimitNode;

                vehicle.insertVelocityLimit(
                        velocityLimitElement.getElementsByTagName(
                                segmentTypeTag).item(0).getTextContent(),
                        UnitConverter.KilometersPerHourToMetersPerSecond(
                                velocityLimitElement.getElementsByTagName(
                                        limitTag).item(0).getTextContent()));
            }
        }
    }

    /**
     * Extracts all the gears in the combustion vehicle.
     *
     * @param vehicleElement Element that contains all the gears of the vehicle.
     * @param vehicle Vehicle that will contain the extracted gears.
     */
    public void extractGearboxVC(Element vehicleElement, VehicleCombustion vehicle) {
        Gearbox gearbox = vehicle.getGearbox();

        NodeList gearNodeList = vehicleElement.getElementsByTagName(gearTag);

        for (int i = 0; i < gearNodeList.getLength(); i++) {
            Node gearNode = gearNodeList.item(i);

            if (gearNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gearElement = (Element) gearNode;

                gearbox.insertGearAndGearRatio(
                        Integer.parseInt(gearElement.getAttribute(idAttribute)),
                        Double.parseDouble(gearElement.getElementsByTagName(
                                ratioTag).item(0).getTextContent()));
            }
        }

        gearbox.validateGearBox();
    }

    /**
     * Extracts all the gears in the electrical vehicle.
     *
     * @param vehicleElement Element that contains all the gears of the vehicle.
     * @param vehicle Vehicle that will contain the extracted gears.
     */
    public void extractGearboxVE(Element vehicleElement, VehicleElectric vehicle) {
        Gearbox gearbox = vehicle.getGearbox();

        NodeList gearNodeList = vehicleElement.getElementsByTagName(gearTag);

        for (int i = 0; i < gearNodeList.getLength(); i++) {
            Node gearNode = gearNodeList.item(i);

            if (gearNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gearElement = (Element) gearNode;

                gearbox.insertGearAndGearRatio(
                        Integer.parseInt(gearElement.getAttribute(idAttribute)),
                        Double.parseDouble(gearElement.getElementsByTagName(
                                ratioTag).item(0).getTextContent()));
            }
        }

        gearbox.validateGearBox();
    }

    /**
     * Extracts all the throttles in the combustion vehicle.
     *
     * @param vehicleElement Element that contains all the throttles.
     * @param vehicle Vehicle that will contain the extracted throttles.
     */
    private void extractThrottlesVC(Element vehicleElement,
            VehicleCombustion vehicle) {
        AcceleratorPedal accelaratorPedal = vehicle.getAcceleratorPedal();

        NodeList throttleNodeList = vehicleElement.getElementsByTagName(
                throttleTag);

        for (int i = 0; i < throttleNodeList.getLength(); i++) {
            Node throttleNode = throttleNodeList.item(i);

            if (throttleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element throttleElement = (Element) throttleNode;

                extractRegimesVC(throttleElement, accelaratorPedal);
            }

        }

        accelaratorPedal.validateAcceleratorPedal();
    }

    /**
     * Extracts all the throttles in the combustion vehicle.
     *
     * @param vehicleElement Element that contains all the throttles.
     * @param vehicle Vehicle that will contain the extracted throttles.
     */
    private void extractThrottlesVE(Element vehicleElement,
            VehicleElectric vehicle) {
        AcceleratorPedal accelaratorPedal = vehicle.getAcceleratorPedal();

        NodeList throttleNodeList = vehicleElement.getElementsByTagName(
                throttleTag);

        for (int i = 0; i < throttleNodeList.getLength(); i++) {
            Node throttleNode = throttleNodeList.item(i);

            if (throttleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element throttleElement = (Element) throttleNode;

                extractRegimesVE(throttleElement, accelaratorPedal);
            }

        }

        accelaratorPedal.validateAcceleratorPedal();
    }

    /**
     * Extract all the regimes in the throttle.
     *
     * @param throttleElement Element that contains all the regimes.
     * @param accelaratorPedal Accelerator pedal that will contain the extracted
     * regimes.
     */
    private void extractRegimesVC(Element throttleElement,
            AcceleratorPedal accelaratorPedal) {
        Throttle throttle = accelaratorPedal.newThrottle();

        NodeList regimeNodeList = throttleElement.getElementsByTagName(regimeTag);

        for (int i = 0; i < regimeNodeList.getLength(); i++) {
            Node regimeNode = regimeNodeList.item(i);

            if (regimeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element regimeElement = (Element) regimeNode;

                Regime regime = throttle.newRegime();

                regime.setTorque(Integer.parseInt(
                        regimeElement.getElementsByTagName(
                                torqueTag).item(0).getTextContent()));
                regime.setRpmLow(Integer.parseInt(
                        regimeElement.getElementsByTagName(
                                rpmLowTag).item(0).getTextContent()));
                regime.setRpmHigh(Integer.parseInt(
                        regimeElement.getElementsByTagName(
                                rpmHighTag).item(0).getTextContent()));
                regime.setSfc(Double.parseDouble(
                        regimeElement.getElementsByTagName(
                                SFCTag).item(0).getTextContent()));

                regime.validateRegime();
                throttle.insertRegime(regime);
            }
        }

        throttle.validateThrottle();
        accelaratorPedal.insertThrottle(
                Integer.parseInt(throttleElement.getAttribute(idAttribute)),
                throttle);
    }

    /**
     * Extract all the regimes in the throttle.
     *
     * @param throttleElement Element that contains all the regimes.
     * @param accelaratorPedal Accelerator pedal that will contain the extracted
     * regimes.
     */
    private void extractRegimesVE(Element throttleElement,
            AcceleratorPedal accelaratorPedal) {
        Throttle throttle = accelaratorPedal.newThrottle();

        NodeList regimeNodeList = throttleElement.getElementsByTagName(regimeTag);

        for (int i = 0; i < regimeNodeList.getLength(); i++) {
            Node regimeNode = regimeNodeList.item(i);

            if (regimeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element regimeElement = (Element) regimeNode;

                Regime regime = throttle.newRegime();

                regime.setTorque(Integer.parseInt(
                        regimeElement.getElementsByTagName(
                                torqueTag).item(0).getTextContent()));
                regime.setRpmLow(Integer.parseInt(
                        regimeElement.getElementsByTagName(
                                rpmLowTag).item(0).getTextContent()));
                regime.setRpmHigh(Integer.parseInt(
                        regimeElement.getElementsByTagName(
                                rpmHighTag).item(0).getTextContent()));

                regime.validateRegime();
                throttle.insertRegime(regime);
            }
        }

        throttle.validateThrottle();
        accelaratorPedal.insertThrottle(
                Integer.parseInt(throttleElement.getAttribute(idAttribute)),
                throttle);
    }

    /**
     * Extract all the junctions in a road network.
     *
     * @param networkElement Element that contains all the junctions.
     * @param project Project that will contain the extracted junctions.
     */
    private void extractNodes(Element networkElement, Project project) {
        NodeList junctionNodeList
                = networkElement.getElementsByTagName(nodeTag);

        for (int i = 0; i < junctionNodeList.getLength(); i++) {
            Node junctionNode = junctionNodeList.item(i);

            if (junctionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element junctionElement = (Element) junctionNode;

                Junction junction = project.newJunction();
                junction.setName(junctionElement.getAttribute(idAttribute));
                junction.validate();

                project.addJunction(junction);
            }
        }
    }

    /**
     * Extracts all the road sections in a road network.
     *
     * @param networkElement Element that contains all the road sections.
     * @param project Project that will contain all the road sections.
     */
    private void extractSections(Element networkElement, Project project) {
        NodeList roadSectionList
                = networkElement.getElementsByTagName(roadSectionTag);

        for (int i = 0; i < roadSectionList.getLength(); i++) {
            Node roadSectionNode = roadSectionList.item(i);

            if (roadSectionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element roadSectionElement = (Element) roadSectionNode;

                String beginJunction
                        = roadSectionElement.getAttribute(beginAttribute);
                String endJunction
                        = roadSectionElement.getAttribute(endAttributes);
                String roadName
                        = roadSectionElement.getElementsByTagName(
                                roadTag).item(0).getTextContent();
                String typology
                        = roadSectionElement.getElementsByTagName(
                                typologyTag).item(0).getTextContent();
                String direction
                        = roadSectionElement.getElementsByTagName(
                                directionTag).item(0).getTextContent();
                double toll
                        = Double.parseDouble(
                                roadSectionElement.getElementsByTagName(
                                        tollTag).item(0).getTextContent());
                double windOrientation
                        = Double.parseDouble(
                                roadSectionElement.getElementsByTagName(
                                        windDirectionTag).item(0).getTextContent());
                double windSpeed
                        = UnitConverter.KilometersPerHourToMetersPerSecond(
                                roadSectionElement.getElementsByTagName(
                                        windSpeedTag).item(0).getTextContent());

                Section section = project.newSection();

                section.setRoadName(roadName + "-" + beginJunction + "-" + endJunction);
                section.setTypology(typology);
                section.setToll(toll);
                section.setWindOrientation(windOrientation);
                section.setWindSpeed(windSpeed);
                section.validateSection();

                project.addSection(beginJunction, endJunction, section);

                // Extract each segment and add it to the section
                extractSegments(roadSectionElement, section.getSegmentList());

                section.validateSection();

                /* If the section is bidirectional is it necessary to add a
                   section in the opposite direction. */
                if (direction.equalsIgnoreCase(directionOfSegment)) {
                    Section reverseSection = project.newSection();

                    reverseSection.setRoadName(roadName + "-" + endJunction + "-" + beginJunction);
                    reverseSection.setTypology(typology);
                    reverseSection.setToll(toll);
                    reverseSection.setWindOrientation(windOrientation * -1);
                    reverseSection.setWindSpeed(windSpeed);
                    reverseSection.validateSection();

                    project.addSection(endJunction, beginJunction, reverseSection);

                    extractSegmentsReverse(roadSectionElement, reverseSection.getSegmentList());

                    reverseSection.validateSection();
                }
            }
        }
    }

    /**
     * Extracts all the segments in a road section in the reverse order.
     *
     * @param roadSectionElement Element that contains all the segments.
     * @param segmentList List of segments that will contain all the extracted
     * segments.
     */
    private void extractSegmentsReverse(Element roadSectionElement,
            SegmentList segmentList) {
        NodeList sectionNodeList
                = roadSectionElement.getElementsByTagName(segmentTag);

        for (int i = sectionNodeList.getLength() - 1; i >= 0; i--) {
            Node segmentNode = sectionNodeList.item(i);

            if (segmentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element segmentElement = (Element) segmentNode;

                Segment segment = segmentList.newSegment();

                segment.setIndex(
                        Integer.parseInt(
                                segmentElement.getAttribute(idAttribute)));
                segment.setInitialHeight(
                        Double.parseDouble(
                                segmentElement.getElementsByTagName(
                                        heightTag).item(0).getTextContent()));
                segment.setAngle(
                        UnitConverter.slopeToDegrees(
                                segmentElement.getElementsByTagName(
                                        slopeTag).item(0).getTextContent()));
                segment.setLength(
                        UnitConverter.KilometersToMeters(
                                segmentElement.getElementsByTagName(
                                        lengthTag).item(0).getTextContent()));
                segment.setMaximumVelocity(
                        UnitConverter.KilometersPerHourToMetersPerSecond(
                                segmentElement.getElementsByTagName(
                                        maxVelocityTag).item(0).getTextContent()));
                segment.setMinimumVelocity(
                        UnitConverter.KilometersPerHourToMetersPerSecond(
                                segmentElement.getElementsByTagName(
                                        minVelocityTag).item(0).getTextContent()));
                segment.setMaximumNumberVehicles(
                        Integer.parseInt(
                                segmentElement.getElementsByTagName(
                                        numberVehiclesTag).item(0).getTextContent()));
                segment.validateSegment();

                segmentList.addSegment(segment);
            }
        }
    }

    /**
     * Extracts all the segments in a road section.
     *
     * @param roadSectionElement Element that contains all the segments.
     * @param segmentList List of segments that will contain all the extracted
     * segments.
     */
    private void extractSegments(Element roadSectionElement,
            SegmentList segmentList) {
        NodeList sectionNodeList
                = roadSectionElement.getElementsByTagName(segmentTag);

        for (int i = 0; i < sectionNodeList.getLength(); i++) {
            Node segmentNode = sectionNodeList.item(i);

            if (segmentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element segmentElement = (Element) segmentNode;

                Segment segment = segmentList.newSegment();

                segment.setIndex(
                        Integer.parseInt(
                                segmentElement.getAttribute(idAttribute)));
                segment.setInitialHeight(
                        Double.parseDouble(segmentElement.getElementsByTagName(
                                heightTag).item(0).getTextContent()));
                segment.setAngle(
                        UnitConverter.slopeToDegrees(
                                segmentElement.getElementsByTagName(
                                        slopeTag).item(0).getTextContent()));
                segment.setLength(
                        UnitConverter.KilometersToMeters(
                                segmentElement.getElementsByTagName(
                                        lengthTag).item(0).getTextContent()));
                segment.setMaximumVelocity(
                        UnitConverter.KilometersPerHourToMetersPerSecond(
                                segmentElement.getElementsByTagName(
                                        maxVelocityTag).item(0).getTextContent()));
                segment.setMinimumVelocity(
                        UnitConverter.KilometersPerHourToMetersPerSecond(
                                segmentElement.getElementsByTagName(
                                        minVelocityTag).item(0).getTextContent()));
                segment.setMaximumNumberVehicles(
                        Integer.parseInt(
                                segmentElement.getElementsByTagName(
                                        numberVehiclesTag).item(0).getTextContent()));
                segment.validateSegment();

                segmentList.addSegment(segment);
            }
        }
    }

    /**
     * Loads an XML file into a Document in order to ease its reading.
     *
     * @param filePath Path to the file.
     * @return Loaded file in Document format.
     * @throws ParserConfigurationException Thrown if there is a configuration
     * error.
     * @throws SAXException Thrown if the loading of the file into a document
     * fails.
     * @throws IOException Thrown if the file is not found.
     */
    private Document readFile(String filePath) throws
            ParserConfigurationException, SAXException, IOException {
        File file = new File(filePath);
        DocumentBuilderFactory docBuilderF = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderF.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.normalize();
        return doc;
    }

}
