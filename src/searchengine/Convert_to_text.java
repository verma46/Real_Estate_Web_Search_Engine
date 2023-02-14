package searchengine;

import java.io.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Convert_to_text {
	
	// Fetch html page files and scrap the data from it in required format and store it in a text file.
		public static void convert_html_to_text()
		{
			System.out.println("\nFetch and convert all .html files to .txt files");
			try {
				String source_path = "src/resources/htmlFiles/";
				String dest_path = "src/resources/textFiles/";
				File dir = new File(source_path);
				String files_list[] = dir.list();
				String address, price, type;
				
				// For each file, fetch property details
				for(String filename: files_list) {
					File currentFile = new File(source_path + filename);
					// Using Jsoup to parse the data in the file
					Document doc = Jsoup.parse(currentFile, "UTF-8");
					// Fetching all the property listings in that file
					Elements property_listings = doc.getElementsByClass("listing_quick_info");
					// For each property,format the data as required
					for(Element property: property_listings) {
						// Fetch data
						address = property.getElementsByClass("address").text();
						price = property.getElementsByClass("price").text();
						type = property.getElementsByClass("type").text();
						// Format data
						address = address.replace(",", "");
						price = price.replace("$", "");
						price = price.replace(",", "");
						type = type.replace("|", "");
						String print_value = address+","+price+","+type;
						String fileName = filename.replace(".html", ".txt");
						File tmpFile = new File(dest_path+fileName);
						boolean fileExists = tmpFile.exists();
						// Check if file exists or not
						if(!fileExists) {
							// Write the formatted data into a text file.
							BufferedWriter out = new BufferedWriter(new FileWriter(dest_path + fileName, true));
					        out.write(print_value + "\n");
					        out.close();
						}
					}
				}
				System.out.println("\nAll files converted to .txt!!\n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//		public static void main(String args[]) {
//			convert_html_to_text();
//		}

}
