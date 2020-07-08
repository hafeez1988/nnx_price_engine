package com.nnx.common;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.nnx.domain.ProductConfig;

/**
 * The product cost calculator utility class will have generic calculation algorithms for the price engine.
 * 
 * @author hafeez
 */
public final class ProductCostCalculatorUtil {

    private static DecimalFormat priceDecimalFormat = new DecimalFormat("0.00");

    private ProductCostCalculatorUtil() {
    }

    public static double getCalculatedCost(final double numberOfProductUnits, final ProductConfig productConfig) {

        final double pricePerUnit = productConfig.getPricePerUnit();
        final double pricePerCarton = productConfig.getPricePerCarton();
        final long unitsPerCarton = productConfig.getUnitsPerCarton();

        final double numberOfRequiredCartons = Math.floor(numberOfProductUnits / unitsPerCarton);
        final double numberOfRemainingUnits = numberOfProductUnits % unitsPerCarton;
        double calculatedCost = 0;

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
            calculatedCost = (numberOfRequiredCartons * pricePerCarton) + (numberOfRemainingUnits * pricePerUnit);
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
}
