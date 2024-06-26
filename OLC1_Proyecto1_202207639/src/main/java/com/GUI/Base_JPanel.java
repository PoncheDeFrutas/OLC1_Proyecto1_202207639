package com.GUI;

import org.jfree.chart.ChartPanel;

import java.awt.*;

/**
 * @author ponche
 */
public class Base_JPanel extends javax.swing.JPanel {

    private String filePath;
    private String codeTextArea;

    private Num_Line_Text_Area numbers;

    private CombinedGraphs combinedGraphs = null;

    private int last = 0;

    /**
     * Creates new form Base_JPanel
     *
     * @param filePath
     */
    public Base_JPanel(String filePath, String codeTextArea) {
        initComponents();
        this.filePath = filePath;
        this.codeTextArea = codeTextArea;

        if (codeTextArea != null) {
            this.jTextArea_codeArea.setText(this.codeTextArea);
        }

        numbers = new Num_Line_Text_Area(jTextArea_codeArea);
        jScrollPane1.setRowHeaderView(numbers);
    }

    public void setGraphs(CombinedGraphs combinedGraphs) {
        this.combinedGraphs = combinedGraphs;
    }
    public String returnFilePath() {
        return this.filePath;
    }

    public String returnTextAreaCode() {
        return jTextArea_codeArea.getText();
    }

    public void setConsoleText(String code){ this.jTextArea_Console.setText(code);}

    public void graphGraph(){
        if (combinedGraphs == null) {return;}
        ChartPanel tempPanel = combinedGraphs.getChartPanels().get(this.last);
        Dimension panelSize = jPanelGraph.getSize();
        tempPanel.setPreferredSize(panelSize);

        jPanelGraph.removeAll();
        jPanelGraph.setLayout(new java.awt.BorderLayout());
        jPanelGraph.add(tempPanel, BorderLayout.CENTER);
        jPanelGraph.validate();
        jPanelGraph.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method
     * is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  jScrollPane1 = new javax.swing.JScrollPane();
                  jTextArea_codeArea = new javax.swing.JTextArea();
                  jLabel1 = new javax.swing.JLabel();
                  jLabel2 = new javax.swing.JLabel();
                  jLabel3 = new javax.swing.JLabel();
                  jPanelGraph = new javax.swing.JPanel();
                  jScrollPane2 = new javax.swing.JScrollPane();
                  jTextArea_Console = new javax.swing.JTextArea();
                  jButtonbefore = new javax.swing.JButton();
                  jButtonafter = new javax.swing.JButton();

                  jTextArea_codeArea.setColumns(20);
                  jTextArea_codeArea.setFont(new java.awt.Font("AnjaliOldLipi", 0, 12)); // NOI18N
                  jTextArea_codeArea.setRows(5);
                  jTextArea_codeArea.setTabSize(4);
                  jScrollPane1.setViewportView(jTextArea_codeArea);

                  jLabel1.setText("Code Area");

                  jLabel2.setText("Console");

                  jLabel3.setText("Graphs");

                  jPanelGraph.setBackground(new java.awt.Color(255, 0, 204));

                  javax.swing.GroupLayout jPanelGraphLayout = new javax.swing.GroupLayout(jPanelGraph);
                  jPanelGraph.setLayout(jPanelGraphLayout);
                  jPanelGraphLayout.setHorizontalGroup(
                           jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGap(0, 514, Short.MAX_VALUE)
                  );
                  jPanelGraphLayout.setVerticalGroup(
                           jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGap(0, 0, Short.MAX_VALUE)
                  );

                  jTextArea_Console.setEditable(false);
                  jTextArea_Console.setColumns(20);
                  jTextArea_Console.setRows(5);
                  jScrollPane2.setViewportView(jTextArea_Console);

                  jButtonbefore.setText("Anterior");
                  jButtonbefore.addMouseListener(new java.awt.event.MouseAdapter() {
                           public void mousePressed(java.awt.event.MouseEvent evt) {
                                    jButtonbeforeMousePressed(evt);
                           }
                  });

                  jButtonafter.setText("Siguiente");
                  jButtonafter.addMouseListener(new java.awt.event.MouseAdapter() {
                           public void mousePressed(java.awt.event.MouseEvent evt) {
                                    jButtonafterMousePressed(evt);
                           }
                  });

                  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                  this.setLayout(layout);
                  layout.setHorizontalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(layout.createSequentialGroup()
                                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                               .addComponent(jScrollPane2)
                                                               .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel2)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1066, Short.MAX_VALUE)
                                                                        .addComponent(jButtonafter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addComponent(jScrollPane1)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                 .addComponent(jButtonbefore, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                 .addComponent(jPanelGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                      .addContainerGap())
                                             .addGroup(layout.createSequentialGroup()
                                                      .addComponent(jLabel1)
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                      .addComponent(jLabel3)
                                                      .addGap(465, 465, 465))))
                  );
                  layout.setVerticalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                             .addComponent(jLabel1)
                                             .addComponent(jLabel3))
                                    .addGap(5, 5, 5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(jPanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                      .addComponent(jButtonbefore, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(jButtonafter, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                    .addContainerGap())
                  );
         }// </editor-fold>//GEN-END:initComponents

         private void jButtonbeforeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonbeforeMousePressed
                  // TODO add your handling code here:
             if (this.last == 0) {
                 this.last =  combinedGraphs.getChartPanels().size() - 1;
             } else {
                 this.last--;
             }
             this.graphGraph();
         }//GEN-LAST:event_jButtonbeforeMousePressed

         private void jButtonafterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonafterMousePressed
                  // TODO add your handling code here:
             if(this.last == combinedGraphs.getChartPanels().size() - 1){
                 this.last = 0;

             } else {
                 this.last++;
             }
             this.graphGraph();
         }//GEN-LAST:event_jButtonafterMousePressed


         // Variables declaration - do not modify//GEN-BEGIN:variables
         private javax.swing.JButton jButtonafter;
         private javax.swing.JButton jButtonbefore;
         private javax.swing.JLabel jLabel1;
         private javax.swing.JLabel jLabel2;
         private javax.swing.JLabel jLabel3;
         private javax.swing.JPanel jPanelGraph;
         private javax.swing.JScrollPane jScrollPane1;
         private javax.swing.JScrollPane jScrollPane2;
         private javax.swing.JTextArea jTextArea_Console;
         private javax.swing.JTextArea jTextArea_codeArea;
         // End of variables declaration//GEN-END:variables
}
