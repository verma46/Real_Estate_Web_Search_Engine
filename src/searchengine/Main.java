package searchengine;

import searchengine.Crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import searchengine.Convert_to_text;

public class Main {

	public static void main(String args[]) throws java.lang.Exception {
		SearchHistory searchHistory = new SearchHistory();
		BestDeal b = new BestDeal();
		SpellCheck spellCheck = new SpellCheck();
		Wordranks wc = new Wordranks();
		WordScrap ws = new WordScrap();

		ArrayList<String> wordCorrection = new ArrayList<String>();
		
		System.out.println("Welcome to Team Crawler's :- \nReal Estate Web Search Engine");
		
		/*
		 * Crawling
		 * */
		// checking if web-page data is crawled or not
		String source_path = "src/resources/textFiles/";
		File dir = new File(source_path);
		String files_list[] = dir.list();
		int crawl = 1;
		
		// if web-page data is crawled
		if(files_list.length > 0) {
			crawl = 0;
			System.out.println("\nCrawled text files are present, Do you want to crawl again to get the latest data 'Y' or 'N' ?: ");
			Scanner read = new Scanner(System.in);
			
			String continue_or_not = read.nextLine();
			if(continue_or_not.equalsIgnoreCase("y")) {
				crawl = 1;
			}
		}
		// if web-page data is need to be crawled
		if(crawl == 1) {
			String url = "https://www.royallepagebinder.com/residential-properties/";
			System.out.println("Fetching all the Html Pages from " + url);
			Crawler.crawl(url, 0);
			System.out.println("\nProcess completed for fetching Html pages!!");
			Convert_to_text.convert_html_to_text();
			ws.scrap(args);
		}
		
		Scanner sc = new Scanner(System.in);
		
		String input = "y";
		String city;
		String bedroomNo;

		while (input.equalsIgnoreCase("y")) {
			
			/**
			 * get recent searches
			 */
			System.out.println("Recent Search History");
			searchHistory.display();
			System.out.println("\n");
			
			System.out.print("Enter a city name: ");
			city = sc.nextLine();

			/**
			 * check spelling of search word and give suggestions if spelling is incorrect. 
			 */
			wordCorrection = spellCheck.correction(city);

			/**
			 * Ask user to enter correct spelling if the search word spelling is incorrect.
			 */
			while (wordCorrection.size() > 0) {
				System.out.println("Are you sure the spelling is correct. Please select a word from the given list");
				System.out.println(wordCorrection);

				System.out.print("Enter a city name: ");
				city = "";
				city = sc.nextLine();

				wordCorrection = spellCheck.correction(city);
			}


			/**
			 * updating search word to searchHistory.txt file
			 */
			searchHistory.updateSearchHistory(city);
			
			b.clearQueue();
			
			wc.getPageRank(city);
			
			System.out.print("\n*******************************\n\n");
			System.out.print("Enter number of bedrooms: ");
			bedroomNo = sc.nextLine();
			
			/**
			 * print the details of building with least price based on the number of bedrooms
			 */
			System.out.println(b.bestDeal((int)Math.floor(Float.valueOf(bedroomNo))));
			System.out.println("Do you want to search again ? Press Y or N");
			input = sc.nextLine();
			
		}

		System.out.println("\nThanks. The Demo ends here. Have a good day.");

	}

}
