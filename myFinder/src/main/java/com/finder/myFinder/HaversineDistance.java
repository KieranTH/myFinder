package com.finder.myFinder;

public class HaversineDistance {
	
	//--- global variables ---
	private double distance;
	private double KM = 6371;
	private double MILES = 3958.756;
	private double radius;

/**
 * @param args
 * arg 1- latitude 1
 * arg 2 — latitude 2
 * arg 3 — longitude 1
 * arg 4 — longitude 2
 */
	
//--- constructor ---
public HaversineDistance() {
	radius = MILES;
	
}
	
//--- method for calculating distance by given args ---
 public void calculateDistance(double lat1, double long1, double lat2, double long2) {
 Double latDistance = toRad(lat2-lat1);
 Double lonDistance = toRad(long2-long1);
 Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
 Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
 Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
 Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
 distance = radius * c;

}

 //--- toRad method ---
 private static Double toRad(Double value) {
 return value * Math.PI / 180;
 }
 
 //--- getter method for distance ---
 public double getDistance() {
	 return distance;
 }
 
 //--- setter method for the Metric ---
public void setMetric(String selection) {
	 
	 if(selection == "KM")
	 {
		 radius = KM;
	 }
	 if(selection == "MILES")
	 {
		 radius = MILES;
	 }
	 
 }

}
