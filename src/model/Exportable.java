package model;

import java.util.List;

/**
 * Interface implemented by all classes whose responsability is to export the
 * project (roads and vehicles) for a HTML file.
 *
 * @author G11
 */
public interface Exportable {

    /**
     * Exports the data contained in open project in simulator, to file HTML
     *
     * @param networkAnalysis Export the results from the network analysis.
     * @param filePath Path to the file.
     */
    public void exportNetworkAnalysis(NetworkAnalysis networkAnalysis, List<String> vehicles, String filePath);
}
