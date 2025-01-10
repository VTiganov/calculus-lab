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

    // Функция для вычисления значения математического выражения
    public static float func(String expression, float x) {
        Expression e = new ExpressionBuilder(expression)
                .variable("x")
                .build()
                .setVariable("x", x);
        return (float) e.evaluate();
    }

    // Метод дихотомии
    public static float dichotomyMethod(String expression, float a, float b, float tolerance) {
        if (func(expression, a) * func(expression, b) > 0) {
            throw new IllegalArgumentException("Значения функции в точках a и b должны иметь противоположные знаки.");
        }

        float c;
        XYSeries series = new XYSeries("Iterations");
        ArrayList<Float> intervalLengths = new ArrayList<>();  // Список для хранения длин промежутков на каждой итерации

        while ((b - a) / 2.0 > tolerance) {
            c = (a + b) / 2;
            System.out.println("Iteration: x = " + c + ", f(x) = " + func(expression, c));  // Добавлено для проверки
            series.add(c, func(expression, c));  // Добавление точки на график для каждой итерации

            // Добавление длины промежутка на текущей итерации
            intervalLengths.add((b - a));

            if (func(expression, c) == 0) {
                return c;
            } else if (func(expression, c) * func(expression, a) < 0) {
                b = c;
            } else {
                a = c;
            }
        }

        // Добавление последней точки
        series.add((a + b) / 2, func(expression, (a + b) / 2));

        // Построение графика для итераций
        showGraph(series);

        // Построение графика изменения длины промежутка
        plotIntervalLengths(intervalLengths);

        return (a + b) / 2;
    }

    // Метод для отображения графика
    public static void showGraph(XYSeries series) {
        // Создание графика в правильном потоке
        SwingUtilities.invokeLater(() -> {
            // Создание данных для графика
            XYSeriesCollection dataset = new XYSeriesCollection(series);

            // Настройка графика
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Estimated position of extremum",   // Заголовок графика
                    "x",                   // Подпись оси X
                    "f(x)",                // Подпись оси Y
                    dataset,               // Данные для графика
                    PlotOrientation.VERTICAL,
                    true,                  // Легенда
                    true,                  // Подсказки
                    false                  // Всплывающие окна
            );

            // Получаем график и устанавливаем стиль отрисовки
            XYPlot plot = chart.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);

            // Настроим рендерер, чтобы сделать каждый точку видимой и выделить их
            renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-6, -6, 10, 10)); // Круглый маркер для точек (увеличенный)
            renderer.setSeriesPaint(0, Color.BLACK);  // Цвет линии и точек
            renderer.setSeriesStroke(0, new BasicStroke(1.0f));  // Установка толщины линии

            // Добавление рендерера в график
            plot.setRenderer(renderer);

            // Панель для отображения графика
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

            // Окно для графика
            JFrame frame = new JFrame("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }

    // Метод для отображения графика изменения длины промежутка
    public static void plotIntervalLengths(ArrayList<Float> intervalLengths) {
        SwingUtilities.invokeLater(() -> {
            XYSeries intervalSeries = new XYSeries("Interval Lengths");
            for (int i = 0; i < intervalLengths.size(); i++) {
                intervalSeries.add(i + 1, intervalLengths.get(i));  // X - итерация, Y - длина промежутка
            }

            XYSeriesCollection dataset = new XYSeriesCollection(intervalSeries);
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Interval Length vs Iteration",  // Заголовок графика
                    "Iteration",                    // Подпись оси X
                    "Interval Length",              // Подпись оси Y
                    dataset,                        // Данные для графика
                    PlotOrientation.VERTICAL,
                    true,                           // Легенда
                    true,                           // Подсказки
                    false                           // Всплывающие окна
            );

            // Получаем график и устанавливаем стиль отрисовки
            XYPlot plot = chart.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);  // Только линии, без точек
            renderer.setSeriesStroke(0, new BasicStroke(5.0f));  // Установка большей толщины линии

            // Добавление рендерера в график
            plot.setRenderer(renderer);

            // Настройка отображения графика
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

            // Окно для графика
            JFrame frame = new JFrame("Interval Lengths");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
