package com.GUI;

// Imports

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.Analyzer.Parser;
import com.Analyzer.flexcup;
import com.Classes.Error;
import com.Classes.Interpreter;
import com.Classes.Tree;
import com.Functions.Reports;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

/**
 * @author ponche
 */
public class Main_JFrame extends javax.swing.JFrame {

    /**
     * Creates new form Main_JFrame
     */
    public Main_JFrame() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method
     * is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_File_CloseFile = new javax.swing.JMenu();
        jMenuItem_NewFile = new javax.swing.JMenuItem();
        jMenuItem_OpenFile = new javax.swing.JMenuItem();
        jMenuItem_SaveFile = new javax.swing.JMenuItem();
        jMenuItem_CloseFile = new javax.swing.JMenuItem();
        jMenu_Tabs = new javax.swing.JMenu();
        jMenu_Run = new javax.swing.JMenu();
        jMenu_Reports = new javax.swing.JMenu();
        jMenuItem_TokensReport = new javax.swing.JMenuItem();
        jMenuItem2_ErrorsReport = new javax.swing.JMenuItem();
        jMenuItem3_SymbolTable = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ponche's Interpreter");
        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        jMenu_File_CloseFile.setText("File");

        jMenuItem_NewFile.setText("New File");
        jMenuItem_NewFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_NewFileMouseReleased(evt);
            }
        });
        jMenu_File_CloseFile.add(jMenuItem_NewFile);

        jMenuItem_OpenFile.setText("Open File");
        jMenuItem_OpenFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_OpenFileMouseReleased(evt);
            }
        });
        jMenu_File_CloseFile.add(jMenuItem_OpenFile);

        jMenuItem_SaveFile.setText("Save File");
        jMenuItem_SaveFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_SaveFileMouseReleased(evt);
            }
        });
        jMenu_File_CloseFile.add(jMenuItem_SaveFile);

        jMenuItem_CloseFile.setText("Close FIle");
        jMenuItem_CloseFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_CloseFileMouseReleased(evt);
            }
        });
        jMenu_File_CloseFile.add(jMenuItem_CloseFile);

        jMenuBar1.add(jMenu_File_CloseFile);

        jMenu_Tabs.setText("Tabs");
        jMenuBar1.add(jMenu_Tabs);

        jMenu_Run.setText("Run");
        jMenu_Run.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu_RunMousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu_Run);

        jMenu_Reports.setText("Reports");

        jMenuItem_TokensReport.setText("Tokens Report");
        jMenuItem_TokensReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_TokensReportMouseReleased(evt);
            }
        });
        jMenu_Reports.add(jMenuItem_TokensReport);

        jMenuItem2_ErrorsReport.setText("Errors Report");
        jMenuItem2_ErrorsReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2_ErrorsReportMouseReleased(evt);
            }
        });
        jMenu_Reports.add(jMenuItem2_ErrorsReport);

        jMenuItem3_SymbolTable.setText("Symbol Table Report ");
        jMenuItem3_SymbolTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3_SymbolTableMouseReleased(evt);
            }
        });
        jMenu_Reports.add(jMenuItem3_SymbolTable);

        jMenuBar1.add(jMenu_Reports);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_NewFileMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        JFileChooser CreatenewFile = new JFileChooser();

        //Disable "All Files" in JFileChooser
        CreatenewFile.setAcceptAllFileFilterUsed(false);

        //Set Only ".df" files accepted
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only df", "df");
        CreatenewFile.setFileFilter(filter);

        //Show JFileChooser and get an answer
        int answer = CreatenewFile.showSaveDialog(this);

        if (answer == JFileChooser.APPROVE_OPTION) {
            File selectedFile = CreatenewFile.getSelectedFile();

            //Get the file name
            String fileName = selectedFile.getName();

            //Check if the file name already ends whit ".df"
            if (!fileName.toLowerCase().endsWith(".df")) {
                selectedFile = new File(selectedFile + ".df");
                fileName = selectedFile.getName();
            }

            try {
                //Create the File and  JPanel
                BufferedWriter outBufferedWriter = new BufferedWriter(new FileWriter(selectedFile));
                outBufferedWriter.close();
                jTabbedPane1.addTab(fileName, new Base_JPanel(selectedFile.getPath(), null));
            } catch (IOException e) {

            }
        } else {
        }
    }

    private void jMenuItem_OpenFileMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        JFileChooser openFile = new JFileChooser();

        //Disable "All Files" in JFileChooser
        openFile.setAcceptAllFileFilterUsed(false);

        //Set only ".df" files accepted
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only df", "df");
        openFile.setFileFilter(filter);

        //Show JFileChooser and get an answer
        int answer = openFile.showOpenDialog(this);

        if (answer == JFileChooser.APPROVE_OPTION) {
            // Get selected File
            File selectedFile = openFile.getSelectedFile();

            try (Scanner scanner = new Scanner(selectedFile)) {
                StringBuilder fileContent = new StringBuilder();
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine()).append("\n");
                }
                jTabbedPane1.addTab(selectedFile.getName(), new Base_JPanel(selectedFile.getPath(), fileContent.toString()));
                jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() - 1);
            } catch (FileNotFoundException e) {

            }

        } else {

        }
    }

    private void jMenuItem_CloseFileMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        jTabbedPane1.removeTabAt(jTabbedPane1.getSelectedIndex());
    }

    private void jMenuItem_SaveFileMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        Base_JPanel base_JPanel = (Base_JPanel) jTabbedPane1.getSelectedComponent();

        try {
            BufferedWriter outBufferedWriter = new BufferedWriter(new FileWriter(base_JPanel.returnFilePath()));
            outBufferedWriter.write(base_JPanel.returnTextAreaCode());
            outBufferedWriter.close();
        } catch (IOException e) {

        }
    }

    private void jMenuItem_TokensReportMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        File file = new File("TablaTokens.html");
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                } else {
                    JOptionPane.showMessageDialog(this, "No se ha generado el reporte de tokens", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void jMenuItem2_ErrorsReportMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        File file = new File("TablaErrores.html");
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                } else {
                    JOptionPane.showMessageDialog(this, "No se ha generado el reporte de tokens", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void jMenuItem3_SymbolTableMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        File file = new File("TablaSimbolos.html");
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                } else {
                    JOptionPane.showMessageDialog(this, "No se ha generado el reporte de simbolos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jMenuItem3_SymbolTableMouseReleased

    private void jMenu_RunMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        String textomamalon = "";
        Base_JPanel base_JPanel = (Base_JPanel) jTabbedPane1.getSelectedComponent();
        base_JPanel.setConsoleText("Analizando los analisis analizados");

        Reader stringReader = new StringReader(base_JPanel.returnTextAreaCode().toUpperCase());
        flexcup lexemeAnalyzer = new flexcup(stringReader);
        Parser parser = new Parser(lexemeAnalyzer);

        Tree tree = null;
        try {
            tree = (Tree) parser.parse().value;
        } catch (Exception e) {
            textomamalon += e.getMessage() + "\n";
        }
        Reports reports = new Reports();
        reports.tokensReport(lexemeAnalyzer.tokens);
        reports.errorsReport(parser.TablaES);

        if (tree != null) {
            try{
                tree.saveTree(tree);

                Interpreter interpreter = new Interpreter(tree);
                interpreter.run();
                reports.simbolTable(interpreter.getHash());
                textomamalon += interpreter.getConsole_text();
                base_JPanel.setGraphs(interpreter.getCombinedGraphs());
                base_JPanel.graphGraph();
            } catch (Exception e) {
                textomamalon += "\n" + e.getMessage() + "\n No, se pudo interpretar\n";
            }
            if (parser.TablaES != null) {
                if (!parser.TablaES.isEmpty()) {
                    for (Error error : parser.TablaES) {
                        textomamalon += error.toString() + "\n";
                    }
                }
            }
            base_JPanel.setConsoleText(textomamalon);
        }

    }//GEN-LAST:event_jMenu_RunMousePressed

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
            //UIManager.setLookAndFeel(new FlatLightLaf());
            FlatDraculaIJTheme.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main_JFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2_ErrorsReport;
    private javax.swing.JMenuItem jMenuItem3_SymbolTable;
    private javax.swing.JMenuItem jMenuItem_CloseFile;
    private javax.swing.JMenuItem jMenuItem_NewFile;
    private javax.swing.JMenuItem jMenuItem_OpenFile;
    private javax.swing.JMenuItem jMenuItem_SaveFile;
    private javax.swing.JMenuItem jMenuItem_TokensReport;
    private javax.swing.JMenu jMenu_File_CloseFile;
    private javax.swing.JMenu jMenu_Reports;
    private javax.swing.JMenu jMenu_Run;
    private javax.swing.JMenu jMenu_Tabs;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
