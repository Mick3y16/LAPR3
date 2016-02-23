package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author G11
 */
public class ImportableRegistry {

    /**
     * Map of importables where each file format corresponds to an object that
     * imports it.
     */
    private Map<String, Importable> importablesList;

    /**
     * Creates an instance of Importable Registry.
     */
    public ImportableRegistry() {
        this.importablesList = new HashMap<>();

        // XML Importer
        this.importablesList.put(".xml", new ImportXML());
    }

    /**
     * Returns a list of all the supported file extensions of this {@link ImportableRegistry}.
     * @return (List&lt;String&gt;) The list of supported file extensions.
     */
    public List<String> getListOfImportables()
    {
        List<String> result = new ArrayList();
        Iterator<String> it = importablesList.keySet().iterator();
        while (it.hasNext())
        {
            result.add(it.next());
        }
        return result;
    }
    
    /**
     * Returns an importable based on the file type found the the given path.
     * 
     * @param path Path to the file to be imported.
     * @return The importable responsible for the given file type if found, else
     * it throws an exception.
     * @throws IllegalArgumentException Thrown when the importer is not found.
     */
    public Importable getImportableOfType(String path) {
        int indexOfType = path.lastIndexOf(".");
        
        // Testing if the given path contains a valid file format.
        if (indexOfType == -1) {
            throw new IllegalArgumentException("The given path contains a invalid file format");
        }
        
        // Extracting the file type.
        String type = path.substring(indexOfType);
        
        if (this.importablesList.containsKey(type)) {
            return this.importablesList.get(type);
        }

        throw new IllegalArgumentException("There is no importer to the selected file type.");
    }

}
