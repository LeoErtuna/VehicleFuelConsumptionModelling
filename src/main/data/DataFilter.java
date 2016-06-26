package main.data;

import main.enums.EnumTableModifiedColumnID;
import main.utils.UtilArraysGeneric;

import java.util.ArrayList;

public class DataFilter
{
    /**
     * Select only cars that run on gasoline, with no additional fuel used
     * @param table the full table, read from Excel file
     * @return 2D array with only selected rows
     */
    public static String[][] selectGasolineOnly(final String[][] table) {
        // Create new array list to store selected data
        ArrayList<String[]> selectedVehicleDataRows = new ArrayList<>();

        // For each vehicle in the table
        for (int vehicleDataRowIndex = 0; vehicleDataRowIndex < table.length; vehicleDataRowIndex++)
            // If it is gasoline
            if (isGasoline(table[vehicleDataRowIndex]))
                // Add it to the array list
                selectedVehicleDataRows.add(table[vehicleDataRowIndex]);

        // Return the results, converted into 2D array
        return UtilArraysGeneric.getArray2D(selectedVehicleDataRows, String.class);
    }

    /**
     * Select only cars that run on diesel, with no additional fuel used
     * @param table the full table, read from Excel file
     * @return 2D array with only selected rows
     */
    public static String[][] selectDieselOnly(final String[][] table) {
        // Create new array list to store selected data
        ArrayList<String[]> selectedVehicleDataRows = new ArrayList<>();

        // For each vehicle in data set
        for (int vehicleDataRowIndex = 0; vehicleDataRowIndex < table.length; vehicleDataRowIndex++)
            // If it is diesel
            if (isDiesel(table[vehicleDataRowIndex]))
                // Add it to the array list
                selectedVehicleDataRows.add(table[vehicleDataRowIndex]);

        // Return the results, converted into 2D array
        return UtilArraysGeneric.getArray2D(selectedVehicleDataRows, String.class);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------- Helpers --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private static boolean isGasoline(String[] row) {
        // Redirect call
        return isGasoline(row[EnumTableModifiedColumnID.fuelType1], row[EnumTableModifiedColumnID.fuelType2], row[EnumTableModifiedColumnID.atvtype], row[EnumTableModifiedColumnID.cylinders]);
    }

    private static boolean isDiesel(String[] row) {
        // Redirect call
        return isDiesel(row[EnumTableModifiedColumnID.fuelType1], row[EnumTableModifiedColumnID.fuelType2], row[EnumTableModifiedColumnID.atvtype], row[EnumTableModifiedColumnID.cylinders]);
    }

    private static boolean isGasoline(final String fueltype1, final String fueltype2, final String atvType, final String cylinders) {
        // If the first fuel type is correct
        if (fueltype1.equals("Regular Gasoline") || fueltype1.equals("Midgrade Gasoline") || fueltype1.equals("Premium Gasoline"))
            // And if no secondary fuel is used
            if (fueltype2 == null || fueltype2.equals(""))
                // And if no alternative fuel is used
                if (atvType == null || atvType.equals(""))
                    // And if valid cylinders data is presented
                    if (cylinders != null && !cylinders.equals("") && !cylinders.equals("NA"))
                        // If all conditions are satisfied - return true
                        return true;

        // If some of the conditions fail - return false
        return false;
    }

    private static boolean isDiesel(final String fueltype1, final String fueltype2, final String atvType, final String cylinders) {
        // If the first fuel type is correct
        if (fueltype1.equals("Diesel"))
            // And if no secondary fuel is used
            if (fueltype2 == null || fueltype2.equals(""))
                // And if alternative fuel is diesel
                if (atvType != null && atvType.equals("Diesel"))
                    // And if valid cylinders data is presented
                    if (cylinders != null && !cylinders.equals("") && !cylinders.equals("NA"))
                        // If all conditions are satisfied - return true
                        return true;

        // If some of the conditions fail - return false
        return false;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}