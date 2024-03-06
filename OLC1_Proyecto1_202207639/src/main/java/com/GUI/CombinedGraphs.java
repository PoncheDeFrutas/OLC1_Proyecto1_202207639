package com.GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CombinedGraphs {

    private ArrayList<ChartPanel> chartPanels = new ArrayList<>();

    public ChartPanel createBarGraph(String title, ArrayList<String> ejeX, ArrayList<Float> ejeY, String tituloX, String tituloY) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < ejeX.size(); i++) {
            dataset.addValue(ejeY.get(i), title, ejeX.get(i));
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                tituloX,
                tituloY,
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        return chartPanel;
    }

    public ChartPanel createPieGraph(String title, ArrayList<String> labels, ArrayList<Float> values) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < labels.size(); i++) {
            dataset.setValue(labels.get(i), values.get(i));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        return chartPanel;
    }

    public ChartPanel createLineGraph(String title, ArrayList<String> ejeX, ArrayList<Float> ejeY, String tituloX, String tituloY) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < ejeX.size(); i++) {
            dataset.addValue(ejeY.get(i), title, ejeX.get(i));
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                title,
                tituloX,
                tituloY,
                dataset);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        return chartPanel;
    }

    public void replacePanel(JFrame frame, JPanel oldPanel, ChartPanel newPanel) {
        // Guardar las dimensiones y la posición del oldPanel
        Dimension oldPanelSize = oldPanel.getSize();
        Point oldPanelLocation = oldPanel.getLocation();

        // Remover el oldPanel y agregar el newPanel
        frame.getContentPane().remove(oldPanel);
        frame.getContentPane().add(newPanel);

        // Establecer las dimensiones y la posición del newPanel para que sean las mismas que las del oldPanel
        newPanel.setPreferredSize(oldPanelSize);
        newPanel.setLocation(oldPanelLocation);

        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    public ChartPanel createFrequencyBarGraph(String title, ArrayList<Float> numbers) {
        // Calcular la frecuencia de cada número
        Map<Float, Float> frequencyMap = new HashMap<>();
        for (float number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0f) + 1);
        }

        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Float, Float> entry : frequencyMap.entrySet()) {
            dataset.addValue(entry.getValue(), "Frecuencia", entry.getKey().toString());
        }

        // Crear la gráfica de barras
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                "Número",
                "Frecuencia",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        return chartPanel;
    }

    public void addChartPanel(ChartPanel chartPanel) {
        chartPanels.add(chartPanel);
    }

    public ArrayList<ChartPanel> getChartPanels() {
        return chartPanels;
    }
}