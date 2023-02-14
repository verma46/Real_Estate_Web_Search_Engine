package searchengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;


public class Crawler {

	static HashSet<String> uniqueLinks = new HashSet<String>(); 
	private static int max_pages = 50;
	
	// Get links from the given url website
	public static void crawl(String url, int page_count)
	{
		try {
			if(!uniqueLinks.contains(url) && uniqueLinks.size() < max_pages) {
				if(page_count>0) {
					// Add the url into the hashSet
					uniqueLinks.add(url);
					// download the html data and store as html files, from the webpage url
					String currentUrl = url;
					Document doc = Jsoup.connect(currentUrl).get();
					String html = doc.html();
					String filePath = "src/resources/htmlFiles/";
					// Using regular expression to format the url
					String fileName = currentUrl.replaceAll("[^a-zA-Z0-9_-]", "") + ".html";
					File tmpFile = new File(filePath+fileName);
					boolean fileExists = tmpFile.exists();
					// Check if file exists or not
					if(!fileExists) {
						// Write the formatted data into a html file.
						BufferedWriter out = new BufferedWriter(new FileWriter(filePath + fileName, true));
				        out.write(url + " " + html);
				        out.close();
					}
				}
				page_count++;
				// Using Jsoup library to scrape and parse HTML from a URL
				Document document= Jsoup.connect(url).get();
				Elements linkpage= document.select("a[href^=\"https://www.royallepagebinder.com/residential-properties/page\"]");
				// Loop through each page link in the webpage
				for(Element page: linkpage)
				{
					crawl(page.attr("abs:href"), page_count);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
// 	public static void main(String args[])
// 	{
// 		String url = "https://www.royallepagebinder.com/residential-properties/";
// 		crawl(url, 0);
// 		for(String link: uniqueLinks) {
// 			System.out.println(link);
// 		}
// 	}
}
