package com.example.system_demo.util;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class util_graph {

    public static JFreeChart spiderChart(DefaultCategoryDataset dataset, String title){
        if (dataset != null){
            SpiderWebPlot spiderWebPlot = new SpiderWebPlot(dataset);
            return new JFreeChart(title, spiderWebPlot);
        } else return null;
    }

/*    public static JFreeChart barChart(DefaultCategoryDataset dataset, String title){
        if (dataset != null){

        } else return null;
    }*/
}
