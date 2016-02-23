/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.ExportHTML;
import model.HTMLExportable;
import model.Simulator;
import utils.Tuple;

/**
 * Class that coordinates the export process of a simulation run's results.
 * @author G11
 */
public class ExportSimulationRunResultsController {
    /**
     * Simulator.
     */
    private Simulator sim;
    
    /**
     * Creates an instance of {@link ExportSimulationRunResultsController} with the
     * specified parameters.
     * @param sim ({@link Simulator}) The simulator that has all the data required.
     */
    public ExportSimulationRunResultsController(Simulator sim)
    {
        this.sim=sim;
        if (sim.getOpenSimulation()==null)
        {
            throw new IllegalArgumentException("You must open a simulation!");
        }
    }
    
    /**
     * Returns the average energy spent for each traffic pattern.
     * @param index (int) The index of the simulation run's result to obtain.
     * @return (List&lt;List&lt;Double&gt;&gt;) The list containing the averages.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    public List<Double> getAverageEnergyConsumptionAll(int index)
    {
        return sim.getOpenSimulation().getRunList().get(index).getAverageEnergy();
    }
    
    /**
     * Returns the average energy spent in each segment for each traffic pattern.
     * @param index (int) The index of the simulation run's result to obtain.
     * @return The list containing the averages.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    public List<List<Tuple<String,Double>>> getAverageEnergyConsumptionAllSegments(int index)
    {
        return sim.getOpenSimulation().getRunList().get(index).getAverageEnergySegments();
    }
    
    /**
     * Returns a list containing the average time and energy spent in each segment.
     * The first element of the tuple contains the average time spent and the
     * second element of the tuple contains the average energy spent.
     * @param vehicleName (String) The name of the vehicle to obtain the information from.
     * @param index (int) The index of the simulation run's result to obtain.
     * @return The list containing the tuples.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    public List<Tuple<String,Tuple<Integer,Double>>> getAverageEnergyConsumptionAll(int index,String vehicleName)
    {
        return sim.getOpenSimulation().getRunList().get(index).getAverageTimeEnergySegments(vehicleName);
    }
    
    /**
     * Exports the averageenergy spent for each veihcle pattern.
     * @param filePath (String) The file path to export to.
     * @param index (int) The index of the result to export.
     */
    public void exportAverageEnergyConsumptionAll(String filePath,int index)
    {
        new AverageEnergyAllHTML(getAverageEnergyConsumptionAll(index), getVehicleList()).writeFileHTML(filePath);
    }
    
    /**
     * Exports the averageenergy spent for each veihcle pattern in all segments.
     * @param filePath (String) The file path to export to.
     * @param index (int) The index of the result to export.
     */
    public void exportAverageEnergyConsumptionAllSegments(String filePath,int index)
    {
        new AverageEnergyAllSegmentHTML(getAverageEnergyConsumptionAllSegments(index), getVehicleList()).writeFileHTML(filePath);
    }
    
    /**
     * Exports the average energy and time spent for a vehicle pattern in all segments.
     * @param filePath (String) The file path to export to.
     * @param vehicle (String) The name of the vehicle pattern.
     * @param index (int) The index of the result to export.
     */
    public void exportAverageEnergyTimeConsumptionVehicleSegments(String filePath,String vehicle,int index)
    {
        new AverageEnergyTimeAllSegmentHTML(getAverageEnergyConsumptionAll(index,vehicle),vehicle).writeFileHTML(filePath);
    }
    
    /**
     * Returns a list containing the names of all vehicles in the simulation's traffic.
     * @return (List&lt;String&gt;) The list of names of the vehicles.
     */
    public List<String> getVehicleList()
    {
        return sim.getOpenSimulation().getSimulationTraffic().getAllVehicles();
    }
    
    /**
     * Returns a list containing the names of all simulation runs available
     * on the simulation.
     * @return (List&lt;String&gt;) The list of names.
     */
    public List<String> getRunList()
    {
        return sim.getOpenSimulation().getRunList().getAllRuns();
    }
    
    /**
     * Class that builds the HTML code for the average energy consumption.
     */
    private final class AverageEnergyAllHTML extends ExportHTML implements HTMLExportable
    {
        private List<Double> list;
        private List<String> listNames;
        
        public AverageEnergyAllHTML(List<Double> list,List<String> listNames)
        {
            this.list=list;
            this.listNames=listNames;
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
       private void writeFileHTML(String filePath) {
           try {
               File file = new File(filePath);

               // If the file doesn't exist, create it
               if (!file.exists()) {
                   file.createNewFile();
               }

               BufferedWriter bw = new BufferedWriter(new FileWriter(file));
               bw.write(openHTML());
               bw.write(this.toStringHTML());
               bw.write(closeHTML());
               bw.close();

           } catch (IOException e) {
               throw new IllegalArgumentException("An error occured while"
                       + " attempting to export the HTML file.");
           }
       }

        @Override
        public String toStringHTML() {
            StringBuilder sb = new StringBuilder();
            
            sb.append("<h1>Average Energy Consumption For Each Traffic Pattern</h1>\n");
            sb.append("<br>\n");
            
            sb.append("<table>\n");
            sb.append("\t<tr><th>Vehicle Name</th><th>Average Energy Consumption</th></tr>\n");
            for (int i=0;i<list.size();i++)
            {
                sb.append("\t<tr><th>").append(listNames.get(i)).append("</th><th>")
                        .append(String.format("%.3e",list.get(i))).append("</th></tr>\n");
            }
            sb.append("</table>\n");
            
            return sb.toString();
        }
    }
    
    /**
     * Class that builds the HTML code for the average energy consumption for
     * each segment.
     */
    private final class AverageEnergyAllSegmentHTML implements HTMLExportable
    {
        private List<List<Tuple<String,Double>>> list;
        private List<String> listNames;
        
        public AverageEnergyAllSegmentHTML(List<List<Tuple<String,Double>>> list,List<String> listNames)
        {
            this.list=list;
            this.listNames=listNames;
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
       private void writeFileHTML(String filePath) {
           try {
               File file = new File(filePath);

               // If the file doesn't exist, create it
               if (!file.exists()) {
                   file.createNewFile();
               }

               BufferedWriter bw = new BufferedWriter(new FileWriter(file));
               bw.write(openHTML());
               bw.write(this.toStringHTML());
               bw.write(closeHTML());
               bw.close();

           } catch (IOException e) {
               throw new IllegalArgumentException("An error occured while"
                       + " attempting to export the HTML file.");
           }
       }

        @Override
        public String toStringHTML() {
            StringBuilder sb = new StringBuilder();
            
            sb.append("<h1>Average Energy Consumption For Each Traffic Pattern In All Segments Of The Path</h1>\n");
            sb.append("<br>\n");
            
            for (int j=0;j<listNames.size();j++)
            {
                sb.append("<h2>Pattern:").append(listNames.get(j)).append("</h2>\n");
                
                sb.append("<table>\n");
                sb.append("\t<tr><th>Segment ID</th><th>Average Energy Consumption</th></tr>\n");
                List<Tuple<String,Double>> lp=list.get(j);
                for (int i=0;i<lp.size();i++)
                {
                    sb.append("\t<tr><th>").append(lp.get(i).getFirstElement()).append("</th><th>")
                            .append(String.format("%.3e",lp.get(i).getSecondElement())).append("</th></tr>\n");
                }
                sb.append("</table>\n");
            }
            
            return sb.toString();
        }
    }
    
    
    /**
     * Class that builds the HTML code for the average energy and time consumption
     * for each segment.
     */
    private final class AverageEnergyTimeAllSegmentHTML implements HTMLExportable
    {
        private List<Tuple<String,Tuple<Integer,Double>>> list;
        private String vehicleName;
        
        public AverageEnergyTimeAllSegmentHTML(List<Tuple<String,Tuple<Integer,Double>>> list,String name)
        {
            this.list=list;
            vehicleName=name;
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
       private void writeFileHTML(String filePath) {
           try {
               File file = new File(filePath);

               // If the file doesn't exist, create it
               if (!file.exists()) {
                   file.createNewFile();
               }

               BufferedWriter bw = new BufferedWriter(new FileWriter(file));
               bw.write(openHTML());
               bw.write(this.toStringHTML());
               bw.write(closeHTML());
               bw.close();

           } catch (IOException e) {
               throw new IllegalArgumentException("An error occured while"
                       + " attempting to export the HTML file.");
           }
       }
        
        @Override
        public String toStringHTML() {
            StringBuilder sb = new StringBuilder();
            
            sb.append("<h1>Average Energy Consumption For Each Traffic Pattern</h1>\n");
            sb.append("<br>\n");
            
             sb.append("<h2>Pattern:").append(vehicleName).append("</h2>\n");
            
            sb.append("<table>\n");
            sb.append("\t<tr><th>Segment ID</th><th>Time Spent</th><th>Energy</th></tr>\n");
            for (int i=0;i<list.size();i++)
            {
                sb.append("\t<tr><th>").append(list.get(i).getFirstElement()).append("</th><th>").
                        append(list.get(i).getSecondElement().getFirstElement()).append("</th><th>")
                        .append(String.format("%.3e",list.get(i).getSecondElement().getSecondElement())).append("</th></tr>\n");
            }
            sb.append("</table>\n");
            
            return sb.toString();
        }
    }
}
