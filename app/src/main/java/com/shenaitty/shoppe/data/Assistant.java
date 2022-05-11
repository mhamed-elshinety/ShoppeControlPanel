package com.shenaitty.shoppe.data;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Assistant {

    public static String formatDecimal(int numDecDigits, double num){
        DecimalFormat formatter =  new DecimalFormat(getDecPattern(numDecDigits));
        formatter.setRoundingMode(RoundingMode.DOWN);
        return formatter.format(num);
    }

    private static String getDecPattern(int numDecDigits){
        StringBuffer pattern = new StringBuffer("$0.");
        for(int i=0; i<numDecDigits; i++){
            pattern.append("0");
        }
        return pattern.toString();
    }

}
