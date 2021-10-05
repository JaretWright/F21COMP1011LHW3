package com.example.f21comp1011lhw3.Controllers;

import com.example.f21comp1011lhw3.Utilities.DBUtility;
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
        barChart.getData().addAll(DBUtility.getSalesDataByCompany());

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
