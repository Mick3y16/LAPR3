package model;

/**
 *
 * @author G11
 */
public class Section implements HTMLExportable {

    /**
     * Key of the section
     */
    private int key;

    /**
     * Name of the road of the section.
     */
    private String roadName;

    /**
     * Typology of the section.
     */
    private String typology;

    /**
     * Toll of the section.
     */
    private double toll;

    /**
     * Wind speed of the section.
     */
    private double windSpeed;

    /**
     * Wind orientation of the section.
     */
    private double windOrientation;

    /**
     * List of segments of the section.
     */
    private SegmentList segmentList;

    /**
     * Creates an instance of Section with default attributes.
     */
    public Section() {
        this.roadName = "";
        this.typology = "";
        this.toll = 0.0;
        this.windSpeed = 0.0;
        this.windOrientation = 0.0;
        this.segmentList = new SegmentList();
    }

    /**
     * Returns the key of the section.
     *
     * @return Key of the section.
     */
    public int getKey() {
        return this.key;
    }

    /**
     * Returns the name of the road of the section.
     *
     * @return Name of the road of the section.
     */
    public String getRoadName() {
        return roadName;
    }

    /**
     * Returns the typology of the section.
     *
     * @return Typology of the section.
     */
    public String getTypology() {
        return typology;
    }

    /**
     * Returns the toll of the section.
     *
     * @return Toll of the section.
     */
    public double getToll() {
        return this.toll;
    }

    /**
     * Returns the wind speed of the section.
     *
     * @return Wind speed of the section.
     */
    public double getWindSpeed() {
        return this.windSpeed;
    }

    /**
     * Returns the wind orientation of the section.
     *
     * @return Wind orientation of the section in degrees.
     */
    public double getWindOrientation() {
        return this.windOrientation;
    }

    /**
     * Returns the list of segments of the section.
     *
     * @return List of segments of the section.
     */
    public SegmentList getSegmentList() {
        return this.segmentList;
    }

    /**
     * Alters the key of the section.
     *
     * @param key Key of the section.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Alters the name of the road of the section.
     *
     * @param roadName New name of the road of the section.
     */
    public void setRoadName(String roadName) {
        if (roadName == null || roadName.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the road cannot be "
                    + "empty.");
        }

        this.roadName = roadName;
    }

    /**
     * Alters the typology of the section.
     *
     * @param typology New typology of the section.
     */
    public void setTypology(String typology) {
        if (typology == null || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("The typology cannot be empty.");
        }

        this.typology = typology;
    }

    /**
     * Alters the toll of the section.
     *
     * @param toll New toll of the section.
     */
    public void setToll(double toll) {
        if (toll < 0) {
            throw new IllegalArgumentException("The price of the toll should be"
                    + " greater than or equal to 0");
        }

        this.toll = toll;
    }

    /**
     * Alters the wind speed of the section.
     *
     * @param windSpeed New wind speed of the section.
     */
    public void setWindSpeed(double windSpeed) {
        if (windSpeed < 0) {
            throw new IllegalArgumentException("The wind speed should be greater"
                    + " than 0");
        }

        this.windSpeed = windSpeed;
    }

    /**
     * Alters the wind orientation of the section.
     *
     * @param windOrientation New wind orientation of the section.
     */
    public void setWindOrientation(double windOrientation) {
        if (-180 > windOrientation || windOrientation > 180) {
            throw new IllegalArgumentException("The wind orientation should be"
                    + " greater than -180º and less than 180º");
        }

        this.windOrientation = windOrientation;
    }

    /**
     * Returns the textual description of the object in the following format:
     * road name, typology, toll, wind speed, wind orientation and segment list.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tRoad name: ").append(this.roadName).append("\n");
        sb.append("\tTypology: ").append(this.typology).append("\n");
        sb.append("\tToll: ").append(this.toll).append("\n");
        sb.append("\tWind speed: ").append(this.windSpeed).append("m/s\n");
        sb.append("\tWind orientation: ").append(this.windOrientation).append("º\n");
        sb.append(this.segmentList);

        return sb.toString();
    }

    /**
     * Compares two objects, first looking at their memory position, following
     * their content and class of origin and in the end compares a set of
     * attributes.
     *
     * @param otherObject used in the comparison.
     * @return True if both objects are equal and false if not.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        Section otherSection = (Section) otherObject;

        return this.getRoadName().equals(otherSection.getRoadName());
    }

    /**
     * Validate the section.
     *
     * @return True if section is valid else returns false.
     */
    public boolean validateSection() {

        if (this.roadName == null || this.roadName.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the road cannot be "
                    + "empty.");
        }

        if (typology == null || typology.trim().isEmpty()) {
            throw new IllegalArgumentException("The typology cannot be empty.");
        }

        if (this.toll < 0) {
            throw new IllegalArgumentException("The price of the toll should be"
                    + " greater than or equal to 0");
        }

        if (this.windSpeed < 0) {
            throw new IllegalArgumentException("The wind speed should be greater"
                    + " than 0");
        }

        if (-180 > this.windOrientation || this.windOrientation > 180) {
            throw new IllegalArgumentException("The wind orientation should be "
                    + "greater than -180º and less than 180");
        }

        return true;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>Road name: ").append(this.roadName).append("</li>\n");
        sb.append("\t<li>Typology: ").append(this.typology).append("</li>\n");
        sb.append("\t<li>Toll: ").append(this.toll).append("</li>").append("\n");
        sb.append("\t<li>Wind speed: ").append(this.windSpeed).append(" m/s</li>\n");
        sb.append("\t<li>Wind orientation: ").append(this.windOrientation).append("º</li>\n");
        sb.append("\t<li>").append(this.segmentList.toStringHTML());
        sb.append("\t</li>\n");

        return sb.toString();
    }

}
