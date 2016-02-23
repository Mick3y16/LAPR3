package model;

/**
 * Represents an interface applied to all objects whose attributes need to be
 * exported to HTML.
 *
 * @author G11
 */
public interface HTMLExportable {
    
    /**
     * Returns the textual description of the object in the HTML format.
     * 
     * @return Textual description of the object in HTML.
     */
    public String toStringHTML();
    
}
