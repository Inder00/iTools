package pl.inder00.tools.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static long addTime(int seconds){
		return getTime()+seconds*1000L;
	}
	
	public static long getTime(){
		return System.currentTimeMillis();
	}
	public static int sumTime(long start){
		return (int) ((int) (getTime()-start)/1000L);
	}
	public static int sumTime(long start, long end){
		return (int) ((end-start)/1000L);
	}
	public static int remTime(long from){
		return (int) ((from-getTime())/1000L);
	}
    public static String convertTime(int input) {
    	int numberOfDays;
    	int numberOfHours;
    	int numberOfMinutes;
    	int numberOfSeconds;

    	numberOfDays = input / 86400;
    	numberOfHours = (input % 86400 ) / 3600;
    	numberOfMinutes = ((input % 86400 ) % 3600 ) / 60;
    	numberOfSeconds = ((input % 86400 ) % 3600 ) % 60;
    	
    	String output = "";
    	if(numberOfDays > 0) output = numberOfDays+"d. ";
    	if(numberOfHours > 0) output = output+numberOfHours+"h. ";
    	if(numberOfMinutes > 0) output = output+numberOfMinutes+"m. ";
    	output = output+numberOfSeconds+"s ";
    	
    	return output;
    }
    
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	public static String getDate(long time){
		return sdf.format(new Date(time));
	}
	
	public static double outTime(long sg){
		
		double f = (double)((long) sg/10L) / (double) 100;
		
		return new BigDecimal(f).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	public static long fromTime(long start){
		return (start-getTime());
	}

}
