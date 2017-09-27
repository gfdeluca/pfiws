package ar.com.uade.pfi.utils;

import java.util.ArrayList;
import java.util.List;

import ar.com.uade.pfi.dao.dbo.entities.ExperimentalCodonPoblationEntity;
import ar.com.uade.pfi.lang.Constants;

public class ArrayUtils {
	public static double[] poblationsLnToArray(List<ExperimentalCodonPoblationEntity> arrEcp) {
		double[] outArr = new double[arrEcp.size()];
		
		int pos = 0;
		for (ExperimentalCodonPoblationEntity ecp : arrEcp) {
			outArr[pos++] = ecp.getPoblationLn();			
		}
		
		return outArr;
	}
	
	public static List<Double> arrayOFStringToDouble(String[] input) {
		List<Double> output = new ArrayList<Double>();
		
		for(String d : input) {
			output.add(new Double(d));
		}
		
		return output;
	}
	
	public static Object[] calculateGammaPresicion(double init, double end, double step) {
		int size = (int) (end/init);
		double increment = (size/Constants.resultQuantity) * step;
		
		Object[] res = new Object[size];
		double gamma = init;
		for (int i = 0; i < Constants.resultQuantity; i++) {
			res[i] = gamma;
			gamma += increment;
		}
		
		return res;
	}
}
