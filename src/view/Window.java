package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Simulator;

/**
 *
 * @author G11
 */
public class Window extends javax.swing.JFrame {

    /**
     * Simulator used to analyze
     */
    private Simulator simulator;

    /**
     * Creates new form Window
     *
     * @param simulator
     */
    public Window(Simulator simulator) {
        this.simulator = simulator;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projLabel = new javax.swing.JLabel();
        simLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        itemExit = new javax.swing.JMenuItem();
        menuProject = new javax.swing.JMenu();
        projectCreate = new javax.swing.JMenuItem();
        projectOpen = new javax.swing.JMenuItem();
        projectEdit = new javax.swing.JMenuItem();
        projectCopy = new javax.swing.JMenuItem();
        projectStaticAnalysis = new javax.swing.JMenuItem();
        menuSimulation = new javax.swing.JMenu();
        simulationOpenActionPerformed = new javax.swing.JMenuItem();
        simulationCreate = new javax.swing.JMenuItem();
        simulationCopy = new javax.swing.JMenuItem();
        simulationEdit = new javax.swing.JMenuItem();
        simulationDelete = new javax.swing.JMenuItem();
        simulationRun = new javax.swing.JMenuItem();
        deleteRunItem = new javax.swing.JMenuItem();
        exportRunResultsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        projLabel.setText("Opened Project: None");

        simLabel.setText("Opened Simulation: None");

        menuFile.setText("File");

        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        menuFile.add(itemExit);

        menuBar.add(menuFile);

        menuProject.setText("Project");

        projectCreate.setText("Create");
        projectCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectCreateActionPerformed(evt);
            }
        });
        menuProject.add(projectCreate);

        projectOpen.setText("Open");
        projectOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectOpenActionPerformed(evt);
            }
        });
        menuProject.add(projectOpen);

        projectEdit.setText("Edit");
        projectEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectEditActionPerformed(evt);
            }
        });
        menuProject.add(projectEdit);

        projectCopy.setText("Copy");
        projectCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectCopyActionPerformed(evt);
            }
        });
        menuProject.add(projectCopy);

        projectStaticAnalysis.setText("Static Analysis");
        projectStaticAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectStaticAnalysisActionPerformed(evt);
            }
        });
        menuProject.add(projectStaticAnalysis);

        menuBar.add(menuProject);

        menuSimulation.setText("Simulation");

        simulationOpenActionPerformed.setText("Open");
        simulationOpenActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationOpenActionPerformedActionPerformed(evt);
            }
        });
        menuSimulation.add(simulationOpenActionPerformed);

        simulationCreate.setText("Create");
        simulationCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationCreateActionPerformed(evt);
            }
        });
        menuSimulation.add(simulationCreate);

        simulationCopy.setText("Copy");
        simulationCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationCopyActionPerformed(evt);
            }
        });
        menuSimulation.add(simulationCopy);

        simulationEdit.setText("Edit");
        simulationEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationEditActionPerformed(evt);
            }
        });
        menuSimulation.add(simulationEdit);

        simulationDelete.setText("Delete");
        simulationDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationDeleteActionPerformed(evt);
            }
        });
        menuSimulation.add(simulationDelete);

        simulationRun.setText("Run");
        simulationRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulationRunActionPerformed(evt);
            }
        });
        menuSimulation.add(simulationRun);

        deleteRunItem.setText("Delete Run");
        deleteRunItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRunItemActionPerformed(evt);
            }
        });
        menuSimulation.add(deleteRunItem);

        exportRunResultsItem.setText("Export Run Results");
        exportRunResultsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportRunResultsItemActionPerformed(evt);
            }
        });
        menuSimulation.add(exportRunResultsItem);

        menuBar.add(menuSimulation);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projLabel)
                    .addComponent(simLabel))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(projLabel)
                .addGap(18, 18, 18)
                .addComponent(simLabel)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        dispose();
    }//GEN-LAST:event_itemExitActionPerformed

    private void projectCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectCreateActionPerformed
        new CreateProjectUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_projectCreateActionPerformed

    private void projectOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectOpenActionPerformed
        new OpenProjectUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_projectOpenActionPerformed

    private void projectEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectEditActionPerformed
        // TODO add your handling code here:
        new EditProjectUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_projectEditActionPerformed

    private void projectCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectCopyActionPerformed
        // TODO add your handling code here:
        new CopyProjectUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_projectCopyActionPerformed

    private void projectStaticAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectStaticAnalysisActionPerformed
        new PerformNetworkStaticAnalysisUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_projectStaticAnalysisActionPerformed

    private void simulationCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulationCreateActionPerformed
        new CreateSimulationUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_simulationCreateActionPerformed

    private void simulationCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulationCopyActionPerformed
        new CopySimulationUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_simulationCopyActionPerformed

    private void simulationEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulationEditActionPerformed
        // TODO add your handling code here:
        new EditSimulationUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_simulationEditActionPerformed

    private void simulationDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulationDeleteActionPerformed
        new DeleteSimulationUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_simulationDeleteActionPerformed

    private void simulationRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulationRunActionPerformed
        // TODO add your handling code here:
        new RunSimulationUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_simulationRunActionPerformed

    private void simulationOpenActionPerformedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_simulationOpenActionPerformedActionPerformed
    {//GEN-HEADEREND:event_simulationOpenActionPerformedActionPerformed
        new OpenSimulationUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_simulationOpenActionPerformedActionPerformed

    private void deleteRunItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRunItemActionPerformed
        // TODO add your handling code here:
        new DeleteSimulationRunUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_deleteRunItemActionPerformed

    private void exportRunResultsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportRunResultsItemActionPerformed
        // TODO add your handling code here:
        new ExportSimulationRunResultsUI(this, true, simulator).setVisible(true);
    }//GEN-LAST:event_exportRunResultsItemActionPerformed

    /**
     * Sets the name of the opened project.
     * @param name (String) The name of the project.
     */
    public void setOpenedProject(String name)
    {
        projLabel.setText("Opened Project: "+name);
        flashJLabel(projLabel, Color.GREEN, 1500);
    }
    
    /**
     * Sets the name of the opened simulation.
     * @param name (String) The name of the simulation.
     */
    public void setOpenedSimulation(String name)
    {
        simLabel.setText("Opened Simulation: "+name);
        flashJLabel(simLabel, Color.GREEN, 1500);
    }
    
    /**
     * Flashes a JLabel with the specified color for the a target amount of
     * milliseconds.
     * @param label (JLabel) The jlabel to flash.
     * @param flashColor (Color) The flashing color.
     * @param waitTime (int) The time to wait until color goes back to normal (in milliseconds).
     */
    protected void flashJLabel(final JLabel label,final Color flashColor,final int waitTime)
    {
        final Color originColor = label.getForeground();
        label.setForeground(flashColor);
        
        Timer timer = new Timer(waitTime,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                label.setForeground(originColor);
            }
        });
        timer.start();
    }
    
    /**
     * Displays an error message using JOptionPane.
     *
     * @param message (String) The message to be displayed.
     */
    protected static final void displayError(String message) {
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a generic message using JOptionPane.
     * <p>
     * The generic message does not possess a title.
     *
     * @param message (String) The message to be displayed.
     */
    protected static final void displayGenericMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new Window(new Simulator()).setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem deleteRunItem;
    private javax.swing.JMenuItem exportRunResultsItem;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuProject;
    private javax.swing.JMenu menuSimulation;
    private javax.swing.JLabel projLabel;
    private javax.swing.JMenuItem projectCopy;
    private javax.swing.JMenuItem projectCreate;
    private javax.swing.JMenuItem projectEdit;
    private javax.swing.JMenuItem projectOpen;
    private javax.swing.JMenuItem projectStaticAnalysis;
    private javax.swing.JLabel simLabel;
    private javax.swing.JMenuItem simulationCopy;
    private javax.swing.JMenuItem simulationCreate;
    private javax.swing.JMenuItem simulationDelete;
    private javax.swing.JMenuItem simulationEdit;
    private javax.swing.JMenuItem simulationOpenActionPerformed;
    private javax.swing.JMenuItem simulationRun;
    // End of variables declaration//GEN-END:variables
}
