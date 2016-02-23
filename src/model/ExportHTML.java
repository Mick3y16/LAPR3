package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a class responsible for exporting HTML files.
 *
 * @author G11
 */
public class ExportHTML implements Exportable {

    /**
     * Creates an instance of ExportHTML, which will serve as a HTML exporter.
     */
    public ExportHTML() {
    }

    /**
     * Exports the results of the network static analysis into HTML format.
     * 
     * @param networkAnalysis Container of the network static analysis results.
     * @param vehicles
     * @param filePath Path to export the file.
     */
    @Override
    public void exportNetworkAnalysis(NetworkAnalysis networkAnalysis, List<String> vehicles, String filePath) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(openHTML());
        sb.append(networkAnalysis.toStringHTML(vehicles));
        sb.append(closeHTML());
        
        writeFileHTML(sb, filePath);
    }

    /**
     * String with the opening of an HTML file.
     *
     * @return Opening of an HTML.
     */
    private String openHTML() {
        return ("<!DOCTYPE html>\n<html>\n<head>\n"
                + "\t<title>LAPR3 - Road Network Simulation</title>\n"
                + "\t<style>\n"
                + "\t\tbody {\n"
                + "\t\t\tmargin: 50px;\n"
                + "\t\t}\n"
                + "\t\ttd, th {\n"
                + "\t\t\tborder: 1px solid #999;\n"
                + "\t\t\tpadding: 5px;\n"
                + "\t\t}\n"
                + "\t</style>\n"
                + "</head>\n<body>\n");
    }

    /**
     * String with the closure of an HTML file.
     *
     * @return Closure of an HTML.
     */
    private String closeHTML() {
        return ("</body>\n</html>\n");
    }

    /**
     * Writes the contents of a StringBuilder Object to an html.
     *
     * @param stringBuilder Object that contains the output format.
     * @param filePath Path where to export the file.
     */
    private void writeFileHTML(StringBuilder stringBuilder, String filePath) {
        try {
            File file = new File(filePath);

            // If the file doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(stringBuilder.toString());
            bw.close();

        } catch (IOException e) {
            throw new IllegalArgumentException("An error occured while"
                    + " attempting to export the HTML file.");
        }
    }

}
