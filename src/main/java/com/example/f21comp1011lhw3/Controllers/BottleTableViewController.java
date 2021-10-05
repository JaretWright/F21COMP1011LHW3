package com.example.f21comp1011lhw3.Controllers;

import com.example.f21comp1011lhw3.Models.HandSanitizerBottle;
import com.example.f21comp1011lhw3.Utilities.DBUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BottleTableViewController implements Initializable {

    @FXML
    private TableView<HandSanitizerBottle> tableView;

    @FXML
    private TableColumn<HandSanitizerBottle, Integer> bottleIdColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, String> companyColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, String> brandColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, Boolean> scentedColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, Integer> volumeColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, Double> alcoholColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, Boolean> pumpTopColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, Boolean> refillableColumn;

    @FXML
    private TableColumn<HandSanitizerBottle, Integer> unitsSoldColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bottleIdColumn.setCellValueFactory(new PropertyValueFactory<>("bottleId"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        scentedColumn.setCellValueFactory(new PropertyValueFactory<>("scented"));
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volumeOfBottle"));
        alcoholColumn.setCellValueFactory(new PropertyValueFactory<>("alcoholPercentage"));
        pumpTopColumn.setCellValueFactory(new PropertyValueFactory<>("bottleTypePump"));
        refillableColumn.setCellValueFactory(new PropertyValueFactory<>("refillable"));
        unitsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));

        tableView.getItems().addAll(DBUtility.getSalesDataByBottle());
    }
}
