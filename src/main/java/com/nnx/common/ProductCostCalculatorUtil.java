package com.nnx.common;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.nnx.domain.ProductConfig;

public final class ProductCostCalculatorUtil {

    private static DecimalFormat priceDecimalFormat = new DecimalFormat("0.00");

    public static double getCalculatedCost(double numberOfProductUnits, ProductConfig productConfig) {

        double pricePerUnit = productConfig.getPricePerUnit();
        double pricePerCarton = productConfig.getPricePerCarton();
        long unitsPerCarton = productConfig.getUnitsPerCarton();

        double calculatedCost = 0;
        double numberOfRequiredCartons = Math.floor(numberOfProductUnits / unitsPerCarton);
        double numberOfRemainingUnits = numberOfProductUnits % unitsPerCarton;

        /*
         * Calculate units without cartons and add 30% compensate
         */
        if (numberOfRequiredCartons <= 0) {
            calculatedCost = (numberOfProductUnits * pricePerUnit) * 1.3;
        }

        /*
         * Calculate the cost per cartons
         */
        if (numberOfRequiredCartons >= 1) {
            calculatedCost = ((numberOfRequiredCartons * pricePerCarton) + (numberOfRemainingUnits * pricePerUnit));
        }

        /*
         * Deduct 10% discount for more than 3 required cartons
         */
        if (numberOfRequiredCartons >= 3) {
            calculatedCost = calculatedCost * 0.9;
        }

        priceDecimalFormat.setRoundingMode(RoundingMode.UP);

        return Double.valueOf(priceDecimalFormat.format(calculatedCost));
    }

    private ProductCostCalculatorUtil() {
    }
}
