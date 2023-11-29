package com.intuit;

import java.util.ArrayList;

class Asset {
    private String description;
    private String dateAcquired;
    private double originalCost;

    public Asset(String description, String dateAcquired, double originalCost) {
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public double getValue() {
        return originalCost;
    }
}

class House extends Asset {
    private String address;
    private int condition;
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, double originalCost,
                 String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public double getValue() {
        double valuePerSquareFoot;
        switch (condition) {
            case 1:
                valuePerSquareFoot = 180.00;
                break;
            case 2:
                valuePerSquareFoot = 130.00;
                break;
            case 3:
                valuePerSquareFoot = 90.00;
                break;
            case 4:
                valuePerSquareFoot = 80.00;
                break;
            default:
                valuePerSquareFoot = 0.00;
        }

        return squareFoot * valuePerSquareFoot + 0.25 * lotSize;
    }
}

class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, double originalCost,
                   String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public int getYear() {  // Corrected method name here
        return year;
    }

    @Override
    public double getValue() {
        int currentYear = 2023; // Change this with the actual current year
        double depreciationRate;

        if (year >= currentYear - 3) {
            depreciationRate = 0.03;
        } else if (year >= currentYear - 6) {
            depreciationRate = 0.06;
        } else if (year >= currentYear - 10) {
            depreciationRate = 0.08;
        } else {
            return Math.max(0, getOriginalCost() - 1000.00);
        }

        double depreciatedValue = getOriginalCost() - (getOriginalCost() * (currentYear - year) * depreciationRate);

        if (odometer > 100000 && !makeModel.toLowerCase().contains("honda") && !makeModel.toLowerCase().contains("toyota")) {
            depreciatedValue *= 0.75;
        }

        return Math.max(0, depreciatedValue);
    }
}

public class AssetManager {
    public static void main(String[] args) {
        ArrayList<Asset> myAssets = new ArrayList<>();

        // Add assets to the list
        myAssets.add(new House("My House", "01/01/2020", 200000.00, "123 Main St", 2, 1500, 5000));
        myAssets.add(new House("Vacation Home", "01/01/2021", 250000.00, "456 Beach Dr", 1, 2000, 8000));
        myAssets.add(new Vehicle("Tom's Truck", "01/01/2018", 30000.00, "Ford F-150", 2017, 75000));
        myAssets.add(new Vehicle("My Car", "01/01/2022", 20000.00, "Honda Civic", 2020, 30000));

        // Display asset information
        for (Asset asset : myAssets) {
            String message = asset.getDescription() + ": Acquired on " + asset.getDateAcquired() +
                    ", Cost: $" + asset.getOriginalCost() + ", Value: $" + asset.getValue();

            if (asset instanceof House) {
                House house = (House) asset;
                message += ", Address: " + house.getAddress();
            } else if (asset instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) asset;
                message += ", Make/Model: " + vehicle.getMakeModel() + ", Year: " + vehicle.getYear();
            }

            System.out.println(message);
        }
    }
}
