package com.finder.myFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import org.json.*;

public class searcherMain {
	
	//--- global variables ---
	private static String postcode;
	private static double longCord;
	private static double latCord;
	
	//--- getting Postcode Data using API ---
	public void setPostcodeData(String givenPostcode) throws IOException, MalformedURLException
	{
		
		givenPostcode = replaceSpaces(givenPostcode);
		
		String newURL = ("http://sharedweb.cs.bangor.ac.uk/postcodeapi/?postcode=" + givenPostcode);
		try {
            //--- new URL connection to read data ---
            URL url = new URL(newURL);
            URLConnection urlConnection = url.openConnection();
            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            //--- filter/Parse JSON Object ---
            String line;
            while ((line = in.readLine()) != null) {
            	
            	JSONObject obj = new JSONObject(line);

        		// --- filter ---
        		postcode = obj.getString("postcode");
        		longCord = obj.getDouble("longitude");
        		latCord = obj.getDouble("latitude");
        		
        		//--- printing results as debugging ---
        		System.out.println(postcode);
        		//System.out.println(longCord);
        		//System.out.println(latCord);
            }
            in.close();
            
        }
		//--- exceptions ---
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

	}
	
	
	//--- turns String into URL searchable string ---
	private static String replaceSpaces(String str) 
    {     
        // Trim the given string 
        str = str.trim(); 
          
        // Replace All space (unicode is \\s) to %20 
        str = str.replaceAll("\\s", "%20"); 
          
           
        return str; 
    }
	
	//--- getter method for long double ---
	public double getLong() {
		return longCord;
	}
	
	//--- getter method for lat double ---
	public double getLat() {
		return latCord;
	}
 
}
