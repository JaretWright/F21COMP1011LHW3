package com.example.f21comp1011lhw3.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.Scanner;

public class SqlGenerator {
    public static void createSQL()
    {
        SecureRandom rng = new SecureRandom();

        //using a try with resources block will automatically close a resource that is opened in the (...)
        try(
                Formatter formatter = new Formatter(new File("bottleSQL.sql"));
                )
        {
            for (int i=1; i<=5000 ; i++)
            {
                LocalDate purchaseDate = LocalDate.now().minusDays(rng.nextInt(1095));  //365*3 = 1095
                int unitsSold = rng.nextInt(75);
                int bottleId = rng.nextInt(16)+1;

                formatter.format("INSERT INTO bottleSales (dateSold, unitsSold, bottleID) VALUES (%s,%d,%d);%n",
                                                purchaseDate, unitsSold, bottleId);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void readFromFile()
    {
        try {
            Scanner scanner = new Scanner(new File("bottleSQL.sql"));
            while (scanner.hasNext())
            {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        createSQL();
        readFromFile();
    }
}
