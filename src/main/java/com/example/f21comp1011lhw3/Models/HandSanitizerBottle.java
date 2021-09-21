package com.example.f21comp1011lhw3.Models;

public class HandSanitizerBottle {
    private String company, brandName;
    private boolean scented;
    private int volumeOfBottle, volumeInBottle;
    private double alcoholPercentage;
    private boolean bottleTypePump, refillable;

    public HandSanitizerBottle(String company, String brandName, boolean scented, int volumeOfBottle, double alcoholPercentage,
                               boolean bottleTypePump, boolean refillable) {
        setCompany(company);
        setBrandName(brandName);
        setScented(scented);
        setVolumeOfBottle(volumeOfBottle);
        setAlcoholPercentage(alcoholPercentage);
        setBottleTypePump(bottleTypePump);
        setRefillable(refillable);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (!company.isBlank())
            this.company = company;
        else
            throw new IllegalArgumentException("Company cannot be blank");
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public boolean isScented() {
        return scented;
    }

    public void setScented(boolean scented) {
        this.scented = scented;
    }

    public int getVolumeOfBottle() {
        return volumeOfBottle;
    }

    public int getVolumeInBottle() {
        return volumeInBottle;
    }

    public void setVolumeInBottle(int volumeInBottle) {
        if (volumeInBottle<=this.volumeOfBottle && volumeInBottle>=0)
            this.volumeInBottle = volumeInBottle;
        else
            throw new IllegalArgumentException("Volume of liquid cannot exceed the size of the bottle");
    }

    public void setVolumeOfBottle(int volume) {
        if (volume > 0)
        {
            this.volumeOfBottle = volume;
            this.volumeInBottle = volumeOfBottle;
        }
        else
            throw new IllegalArgumentException("Volume must be greater than 0");
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public boolean isBottleTypePump() {
        return bottleTypePump;
    }

    public void setBottleTypePump(boolean bottleTypePump) {
        this.bottleTypePump = bottleTypePump;
    }

    public boolean isRefillable() {
        return refillable;
    }

    public void setRefillable(boolean refillable) {
        this.refillable = refillable;
    }

    /**
     * This method will dispense a set amount of liquid out of the bottle and return the amount
     * that came out of the bottle
     */
    public int dispense(int amountToDispense)
    {
        if (this.volumeInBottle>=0 && amountToDispense>=0)
        {
            if (volumeInBottle >= amountToDispense)
            {
                volumeInBottle -= amountToDispense;
                return amountToDispense;
            }
            else
            {
                amountToDispense = volumeInBottle;
                volumeInBottle = 0;
                return amountToDispense;
            }
        }
        return 0;
    }

    public String toString()
    {
        return String.format("%s-%s scented: %b  volume: %d ml",company, brandName, scented,volumeOfBottle);
    }
}