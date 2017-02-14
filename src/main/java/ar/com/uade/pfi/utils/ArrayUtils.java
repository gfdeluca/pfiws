package ar.com.uade.pfi.utils;

import java.util.ArrayList;
import java.util.List;

import ar.com.uade.pfi.dao.dbo.entities.ExperimentalCodonPoblationEntity;

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
}
