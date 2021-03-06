package com.finder.myFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.*;
import java.util.*;
import org.json.*;

public class searcherMain {
	
	//--- global variables ---
	private String postcode;
	private double longCord;
	private double latCord;
	private int count=0;
	
	//--- getting Postcode Data using API ---
	public void setPostcodeData(String givenPostcode)
	{
		
		givenPostcode = replaceSpaces(givenPostcode);
		
		String newURL = ("http://sharedweb.cs.bangor.ac.uk/postcodeapi/?postcode=" + givenPostcode);
		//String newURL = ("http://sharedweb.cs.bangor.ac.uk/postcodeapi/?postcode=LL55 2UT");
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
        		
        		count++;
        		
        		//--- printing results as debugging ---
        		System.out.println("Postcode: " + count + " " + postcode);
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
            //System.out.println("I/O Error: " + e.getMessage() + " In searcherMain");
        	e.printStackTrace();
        	System.exit(0);
        }
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(0);
		}

	}
	
	
	//--- turns String into URL searchable string ---
	private  String replaceSpaces(String str) 
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
