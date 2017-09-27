package ar.com.uade.pfi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
	public static double truncate(double value, int places) {
	    if (places < 0) {
	        throw new IllegalArgumentException();
	    }

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = (long) value;
	    return (double) tmp / factor;
	}
	
	public static double truncate(double value) {
	    BigDecimal number = new BigDecimal(value + 0.000001);
	    number.setScale(5, RoundingMode.HALF_UP);
	   	    
	    String gammaStr = number.toPlainString();
		int pointPos = gammaStr.indexOf('.')+1;
		gammaStr = gammaStr.substring(0, pointPos + 5);
		double response = Double.valueOf(gammaStr).doubleValue();
		
		return response;
	}
}
