package com.example.f21comp1011lhw3.Utilities;

import com.example.f21comp1011lhw3.Models.HandSanitizerBottle;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<HandSanitizerBottle> getSalesDataByBottle()
    {
        ArrayList<HandSanitizerBottle> bottles = new ArrayList<>();

        String sql ="SELECT company, brand, scented, volumeOfBottle, alcoholPercentage, pumpTop, refillable, bottles.bottleId, SUM(unitsSold) \n" +
                    "FROM bottles INNER JOIN bottleSales ON bottles.bottleId = bottleSales.bottleId\n" +
                    "GROUP BY bottles.bottleId;";

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //loop over the resultSet returned and build the XYChart data
            while (resultSet.next())
            {
                String company = resultSet.getString("company");
                String brand = resultSet.getString("brand");
                Boolean scented = resultSet.getBoolean("scented");
                int volume = resultSet.getInt("volumeOfBottle");
                double alcohol = resultSet.getDouble("alcoholPercentage");
                boolean pumpTop = resultSet.getBoolean("pumpTop");
                boolean refillable = resultSet.getBoolean("refillable");
                int bottleId = resultSet.getInt("bottles.bottleId");
                int unitsSold = resultSet.getInt("SUM(unitsSold)");

                HandSanitizerBottle newBottle = new HandSanitizerBottle(company,brand,scented,volume,alcohol,pumpTop,refillable,unitsSold);
                newBottle.setBottleId(bottleId);

                bottles.add(newBottle);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return bottles;
    }

    public static int saveToDB(HandSanitizerBottle hsb) throws SQLException {
        String sql = "INSERT INTO bottles (company, brand, scented, volumeOfBottle,alcoholPercentage, pumpTop, refillable) values (?,?,?,?,?,?,?);";

        int bottleId = -1;
        ResultSet rs = null;

        try (
                Connection conn = DriverManager.getConnection(connectURL,user,pw);
                PreparedStatement ps = conn.prepareStatement(sql, new String[] {"bottleID"});
                ){

            //bind the parameters
            ps.setString(1, hsb.getCompany());
            ps.setString(2,hsb.getBrandName());
            ps.setBoolean(3, hsb.isScented());
            ps.setInt(4,hsb.getVolumeOfBottle());
            ps.setDouble(5, hsb.getAlcoholPercentage());
            ps.setBoolean(6,hsb.isBottleTypePump());
            ps.setBoolean(7,hsb.isRefillable());

            //execute the insert
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            while (rs.next())
                bottleId = rs.getInt(1);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (rs != null) rs.close();
        }
        return bottleId;
    }
}
