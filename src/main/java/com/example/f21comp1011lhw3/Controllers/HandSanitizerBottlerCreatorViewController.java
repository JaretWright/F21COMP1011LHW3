package com.example.f21comp1011lhw3.Controllers;

import com.example.f21comp1011lhw3.Models.HandSanitizerBottle;
import com.example.f21comp1011lhw3.Utilities.DBUtility;
import com.example.f21comp1011lhw3.Utilities.SceneChanger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HandSanitizerBottlerCreatorViewController implements Initializable {

    @FXML
    private TextField companyTextField;

    @FXML
    private TextField brandTextField;

    @FXML
    private Spinner<Integer> volumeSpinner;

    @FXML
    private Slider alcoholSlider;

    @FXML
    private Label sliderLabel;

    @FXML
    private CheckBox scentedCheckBox;

    @FXML
    private CheckBox pumpBottleCheckBox;

    @FXML
    private CheckBox refillableCheckBox;

    @FXML
    private Button submitButton;


    @FXML
    private Label createdObjectLabel;

    @FXML
    private void submitButtonPushed()
    {
        String company = companyTextField.getText();
        String brand = brandTextField.getText();
        boolean refillable = refillableCheckBox.isSelected();
        boolean pumpBottle = pumpBottleCheckBox.isSelected();
        boolean scented = scentedCheckBox.isSelected();
        double alcohol = alcoholSlider.getValue();
        int volumeOfBottle = volumeSpinner.getValue();

        try{
            HandSanitizerBottle hsb = new HandSanitizerBottle(company,brand,scented,volumeOfBottle,alcohol,pumpBottle,refillable);
//            hsb.setBottleId(DBUtility.saveToDB(hsb));
            createdObjectLabel.setTextFill(Color.BLACK);
            createdObjectLabel.setText(hsb.toString());

        } catch (Exception e)
        {
            createdObjectLabel.setText(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        submitButton.setDisable(true);
        createdObjectLabel.setText("");

        alcoholSlider.setMin(60);
        alcoholSlider.setMax(95);
        alcoholSlider.setValue(80);
        sliderLabel.setText(String.format("%.1f%%",alcoholSlider.getValue()));
//        alcoholSlider.valueProperty().addListener(new AlcoholSliderChangeListener2());

        //create an anonymous inner class
//        alcoholSlider.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
//                sliderLabel.setText(String.format("%.1f",newValue));
//            }
//        });

        alcoholSlider.valueProperty().addListener((observable, oldValue, newValue)->{
            sliderLabel.setText(String.format("%.1f%%",newValue));
        });

        //configuring the spinner with minimum, max, default and increment
        SpinnerValueFactory<Integer> spinnerValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(100,500,200,50);
        volumeSpinner.setValueFactory(spinnerValueFactory);
        volumeSpinner.setEditable(true);

        //update the spinner object to only allow the user to enter a whole number
        TextField spinnerTextField = volumeSpinner.getEditor();
        spinnerTextField.textProperty().addListener((observable, oldValue, newValue)->{
            try{
                createdObjectLabel.setText("");
                Integer.parseInt(newValue);
            }
            catch (Exception e)
            {
                spinnerTextField.setText(oldValue);
                createdObjectLabel.setText("volume can only be a whole number");
                createdObjectLabel.setTextFill(Color.RED);
            }
        });
    }


    public class AlcoholSliderChangeListener2 implements ChangeListener {
        @Override
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            System.out.printf("The slider changed, old value: %.2f, new value: %.2f%n",oldValue,newValue);
            sliderLabel.setText(String.format("%.1f",newValue));
        }
    }

    @FXML
    private void changeToSales(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"sales-view.fxml","Sales");
    }
}