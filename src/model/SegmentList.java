package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of segments.
 *
 * @author G11
 */
public class SegmentList {

    /**
     * List of segments.
     */
    private List<Segment> segmentList;

    /**
     * Creates an instance of SegmentList with default attributes.
     *
     */
    public SegmentList() {
        this.segmentList = new ArrayList<>();
    }

    /**
     * Return the number of section segments.
     *
     * @return
     */
    public int size() {
        return this.segmentList.size();
    }

    /**
     * Returns the textual description of the object in the following format:
     * segment: index, initial height, angle, lenght, rolling resistence
     * coeficiente, minimum velocity, maximum velocity, maximum number vehicles.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tList of segments:\n");

        for (Segment segment : this.segmentList) {
            sb.append(segment.toString());
        }

        return sb.toString();
    }

    /**
     * Creates a new segment.
     *
     * @return New segment.
     */
    public Segment newSegment() {
        return new Segment();
    }

    /**
     * Add a segment to the segments list.
     *
     * @param segment Segment to be added.
     * @return True if the segment was added , otherwise returns false.
     */
    public boolean addSegment(Segment segment) {
        if (!this.segmentList.contains(segment)) {
            return this.segmentList.add(segment);
        }

        return false;
    }

    /**
     * Remove the segment received by parameter segments list
     *
     * @param segment Segment to be removed.
     * @return True if the segment was added , otherwise returns false.
     */
    public boolean removeSegment(Segment segment) {
        return this.segmentList.remove(segment);
    }

    /**
     * Returns an iterator to allow running through the list of segments.
     *
     * @return Iterator.
     */
    public Iterator<Segment> iterator() {
        return this.segmentList.iterator();
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tList of segments:<ul>\n");

        for (Segment segment : this.segmentList) {
            sb.append(segment.toStringHTML());
        }
        sb.append("\t</ul>\n");

        return sb.toString();
    }
}
