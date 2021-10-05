package com.example.f21comp1011lhw3.Utilities;

import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectURL = "jdbc:mysql://localhost:3306/javaProjects";

    public static XYChart.Series<String, Integer> getSalesDataByCompany()
    {
        XYChart.Series<String, Integer> salesData = new XYChart.Series<>();

        String sql ="SELECT company, SUM(unitsSold) " +
                    "FROM bottles INNER JOIN bottleSales ON bottles.bottleId = bottleSales.bottleId " +
                    "GROUP BY company " +
                    "ORDER BY 2 DESC;";
        //using try with resources, we will open a connection, statement and resultSet to hold the data returned
        //from the database
        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            //loop over the resultSet returned and build the XYChart data
            while (resultSet.next())
            {
                salesData.getData().add(new XYChart.Data<>(resultSet.getString("company"),
                                                        resultSet.getInt(2)));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return salesData;
    }
}
