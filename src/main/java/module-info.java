module com.example.f21comp1011lhw3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.f21comp1011lhw3 to javafx.fxml;
    exports com.example.f21comp1011lhw3;
    exports com.example.f21comp1011lhw3.Controllers;

    opens com.example.f21comp1011lhw3.Controllers to javafx.fxml;
    exports com.example.f21comp1011lhw3.Models;
    
    opens com.example.f21comp1011lhw3.Models to javafx.fxml;
}