package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author G11
 */
public class ExportableRegistry {

    /**
     * Map of exportables where each file format corresponds to an object that
     * exports it.
     */
    private Map<String, Exportable> exportablesList;

    /**
     * Creates an instance of Importable Registry.
     */
    public ExportableRegistry() {
        this.exportablesList = new HashMap<>();

        // HTML Exporter
        this.exportablesList.put(".html", new ExportHTML());
    }
    
    /**
     * Returns an exportable based on the file type found the the given path.
     * 
     * @param path Path to the file to be exported.
     * @return The exportable responsible for the given file type if found, else
     * it throws an exception.
     * @throws IllegalArgumentException Thrown when the exporter is not found.
     */
    public Exportable getExportableType(String path) {
        int indexOfType = path.lastIndexOf(".");
        
        // Testing if the given path contains a valid file format.
        if (indexOfType == -1) {
            throw new IllegalArgumentException("The given path contains a invalid file format");
        }
        
        // Extracting the file type.
        String type = path.substring(indexOfType);
        
        if (this.exportablesList.containsKey(type)) {
            return this.exportablesList.get(type);
        }

        throw new IllegalArgumentException("There is no exporter to the selected file type.");
    }
}
