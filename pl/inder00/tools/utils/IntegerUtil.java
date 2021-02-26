package pl.inder00.tools.utils;

public class IntegerUtil {
	
    public static boolean isInteger(String s) {
    	try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

}
