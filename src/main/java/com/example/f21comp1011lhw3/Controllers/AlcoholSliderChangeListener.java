package com.example.f21comp1011lhw3.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AlcoholSliderChangeListener implements ChangeListener {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        System.out.printf("The slider changed, old value: %.2f, new value: %.2f%n",oldValue,newValue);
    }
}
