package com.nutmeg.transactions.holding.factory;

import java.text.DecimalFormat;


public class DecimalFormatUtils {

    public static DecimalFormat decimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        return decimalFormat;
    }
}
