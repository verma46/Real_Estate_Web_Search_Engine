package searchengine;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class PageSorting {

	public static List<Entry<String, Integer>> SortMap(Map<String, Integer> webpages) {

		List<Entry<String, Integer>> list = null;

		if (!webpages.isEmpty()) {

			System.out.println("\nPages after sorting based on no. of occurences : ");

			/* Using Stream API of Java 8 for sorting Hash map values.
			 * In java 8, Map.Entry class has static method comparingByValue() 
			 * to help in sorting by values. This method returns a Comparator 
			 * that compares Map.Entry in natural order on values.*/
			
			Stream<Map.Entry<String, Integer>> sorted = webpages.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));

			System.out.println("Top 5 pages with the best results are : -");

			list = sorted.toList();

			if(list.size() >= 5) {
				for (int k = 0; k < 5; k++) {
					System.out.println(
							"Page Name : " + list.get(k).getKey() + "\nNo. of Occurences : " + list.get(k).getValue());
	
				}
			}else {
				for (int k = 0; k < list.size(); k++) {
					System.out.println(
							"Page Name : " + list.get(k).getKey() + "\nNo. of Occurences : " + list.get(k).getValue());
	
				}
			}

		}

		return list; // Returning the sorted list of the top 5 pages with highest word ranking
	}
}
