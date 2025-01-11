package com.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.awt.BasicStroke;
import java.util.ArrayList;

public class Task1 {

    public static float func(String expression, float x) {
        Expression e = new ExpressionBuilder(expression)
                .variable("x")
                .build()
                .setVariable("x", x);
        return (float) e.evaluate();
    }

    public static float dichotomyMethod(String expression, float a, float b, float tolerance) {
        if (func(expression, a) * func(expression, b) > 0) {
            throw new IllegalArgumentException("Значения функции в точках a и b должны иметь противоположные знаки.");
        }

        float c;
        XYSeries series = new XYSeries("Iterations");
        ArrayList<Float> intervalLengths = new ArrayList<>();

        while ((b - a) / 2.0 > tolerance) {
            c = (a + b) / 2;
            series.add(c, func(expression, c));

            intervalLengths.add((b - a));

            if (func(expression, c) == 0) {
                return c;
            } else if (func(expression, c) * func(expression, a) < 0) {
                b = c;
            } else {
                a = c;
            }
        }

        series.add((a + b) / 2, func(expression, (a + b) / 2));

        showGraph(series);

        plotIntervalLengths(intervalLengths);

        return (a + b) / 2;
    }

    public static void showGraph(XYSeries series) {
        SwingUtilities.invokeLater(() -> {
            XYSeriesCollection dataset = new XYSeriesCollection(series);

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Estimated position of extremum",
                    "x",
                    "f(x)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            XYPlot plot = chart.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);

            renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-6, -6, 10, 10));
            renderer.setSeriesPaint(0, Color.BLACK);
            renderer.setSeriesStroke(0, new BasicStroke(1.0f));

            plot.setRenderer(renderer);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

            JFrame frame = new JFrame("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }

    public static void plotIntervalLengths(ArrayList<Float> intervalLengths) {
        SwingUtilities.invokeLater(() -> {
            XYSeries intervalSeries = new XYSeries("Interval Lengths");
            for (int i = 0; i < intervalLengths.size(); i++) {
                intervalSeries.add(i + 1, intervalLengths.get(i));
            }

            XYSeriesCollection dataset = new XYSeriesCollection(intervalSeries);
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Interval Length vs Iteration",
                    "Iteration",
                    "Interval Length",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            XYPlot plot = chart.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
            renderer.setSeriesStroke(0, new BasicStroke(5.0f));

            plot.setRenderer(renderer);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

            JFrame frame = new JFrame("Interval Lengths");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
