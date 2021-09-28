package com.example.f21comp1011lhw3.Controllers;

import com.example.f21comp1011lhw3.Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis sizeOfBottleAxis;

    @FXML
    private NumberAxis numberSoldAxis;

    private XYChart.Series<String, Integer> sales;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sales = new XYChart.Series<>();
        sales.getData().add(new XYChart.Data<>("100 ml", 123));
        sales.getData().add(new XYChart.Data<>("150 ml", 150));
        sales.getData().add(new XYChart.Data<>("200 ml", 201));
        sales.getData().add(new XYChart.Data<>("250 ml", 205));
        sales.getData().add(new XYChart.Data<>("300 ml", 179));
        sales.getData().add(new XYChart.Data<>("350 ml", 193));
        sales.getData().add(new XYChart.Data<>("400 ml", 158));

        barChart.getData().addAll(sales);

        //configure look and feel of the chart
//        barChart.setTitle("Sales for Q1");
        numberSoldAxis.setLabel("Number of Bottles Sold");
        sizeOfBottleAxis.setLabel("Size of Bottle");
        barChart.setLegendVisible(false);
    }

    @FXML
    private void changeToCreatorView(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"HandSanitizerBottleCreatorView.fxml","Create a Bottle");
    }
}
