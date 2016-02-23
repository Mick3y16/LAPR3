/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DeleteSimulationController;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Simulator;

/**
 *
 * @author G11
 */
public class DeleteSimulationUI extends javax.swing.JDialog {

    private DeleteSimulationController controller;
    private Frame parentFrame;
    private String simulationSelect;
    private DefaultListModel<String> model;

    /**
     * Creates new form DeleteSimulationUI
     */
    public DeleteSimulationUI(java.awt.Frame parent, boolean modal, Simulator simulator) {
        super(parent, "Delete Simulation", modal);
        try {
            this.controller = new DeleteSimulationController(simulator);
            this.parentFrame = parent;
            initComponents();
            model = new DefaultListModel<>();
            List<String> array = this.controller.getSimulationList();
            for (String str : array) {
                model.addElement(str);
            }
            jListSimulation.setModel(model);
            jListSimulation.setSelectedIndex(0);
            setResizable(false);
            setLocationRelativeTo(null);
            pack();

        } catch (IllegalArgumentException e) {
            Window.displayError(e.getMessage());
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    dispose();
                }
            });
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListSimulation = new javax.swing.JList();
        jBSelect = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "List of simulation"));

        jListSimulation.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListSimulation);

        jBSelect.setText("Select");
        jBSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSelectActionPerformed(evt);
            }
        });

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBSelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBSelect)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSelectActionPerformed
        this.simulationSelect = this.jListSimulation.getSelectedValue().toString();
        this.controller.selectSimulation(simulationSelect);
        String opcoes[] = {"Yes", "No"};
        int resposta = JOptionPane.showOptionDialog(
                this, "Do you want delete " + this.simulationSelect + " ?", this.simulationSelect, 0,
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if (resposta == 0) {
            if (controller.deleteSimulation()) {
                model.removeElementAt(jListSimulation.getSelectedIndex());
                JOptionPane.showMessageDialog(this, "Simulation" + simulationSelect + " deleted with successfull");

                jListSimulation.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Impossible" + simulationSelect + " erased the selected submission ");

            }
        }
        this.dispose();

    }//GEN-LAST:event_jBSelectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSelect;
    private javax.swing.JButton jButton1;
    private javax.swing.JList jListSimulation;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}