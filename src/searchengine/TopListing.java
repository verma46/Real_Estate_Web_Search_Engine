package searchengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Class to get the top 5 pages by word rank and give the top listings for the Search Location*/

public class TopListing {
	
	static HashMap<String, ArrayList<String>> ListingMap = new HashMap<>();
	
	public static void Listing(Map<String, Integer> searchPages,String SearchLocation)
	{
		
		String Text_file_path = "src/resources/textFiles/";
		
		File dir = new File(Text_file_path);
		
		String Text_files_list[] = dir.list();
		
		for(String filename: Text_files_list) {
			
			if(!filename.equalsIgnoreCase(".DS_Store") && searchPages.containsKey(filename))
			{
			
			try {
			      File myObj = new File(Text_file_path+"/"+filename);
			      Scanner myReader = new Scanner(myObj);  
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        if(!data.isEmpty())
			        {
			        
			        	String[] Listings= data.split("\n");
			        	int len= Listings.length;
			        	String ListingItem="";
			        	String[] item= new String[len];
			        	String Address="";
			        	String Price="";
			        	String LastString="";
			        	String NoOfBedRooms="";
			        	String NoOfBathrooms="";
			        	String Category="";
			        	
			        	ArrayList<String> CombinedData=new ArrayList<>();
			        	CombinedData.clear();
			        	
			        	
			        	String[] LastStringArray= new String[3];
			        	
			        	/* Iterating through the text files and formating data and storing in 
			        	 * Hashmap and Arraylist */
			        	
			        	for(int i=0; i < len ; i++)
			        	{
			        		ListingItem=Listings[i];
			        		item=ListingItem.split(",");
			        		Address=item[0];
			        		Price=item[1];
			        		LastString=item[2];
			        		
			        		/* Conditions to filter out data */
			        		
			        		if(!LastString.equalsIgnoreCase("Vacant Land") && !LastString.equalsIgnoreCase("Residential"))
			        		{
			        			LastStringArray=LastString.split("  ");
			        			int len1 = LastStringArray.length;
			        			
			        			if(len1==3)
			        			{
			        				NoOfBedRooms=LastStringArray[0];
			        				NoOfBathrooms=LastStringArray[1];
			        				Category=LastStringArray[2];
			        			}
			        			else if(len1==2)
			        			{
			        				NoOfBedRooms="NA";
			        				NoOfBathrooms=LastStringArray[0];
			        				Category=LastStringArray[1];
			        			}
			        			
			   
			        		}
			        		else
			        		{
			        			NoOfBedRooms="NA";
		        				NoOfBathrooms="NA";
			        			Category=LastString;
			        		}
			        		
			      
			        		CombinedData.add(Price);
			        		CombinedData.add(NoOfBedRooms);
			        		CombinedData.add(NoOfBathrooms);
			        		CombinedData.add(Category);
			        			
			        			
			        			try {
									ListingMap.put(Address,CombinedData);
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			        				
			        	}
			      	
			        }
			       
			      }      
			      
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    } 
				
		}		
			
		}
		
		System.out.println("\nListings from top 5 pages for the location :- "+SearchLocation);
		
		for (String name: ListingMap.keySet()) {
		    String Listkey = name.toString();
		    String Listvalue = ListingMap.get(name).toString();
		    
		    
		    if(Listkey.toLowerCase().contains(SearchLocation.toLowerCase()))
		    {
		    System.out.println("\n****************************************************************************************************************");
		    System.out.println("Address: " + Listkey+"\n:--> |"+"Price, No. of Bedrooms, No. of Bathrooms, Category are :" + Listvalue+"|");
		    System.out.println("****************************************************************************************************************");
		    }
		    
		    
		}

		
		
	}


}
