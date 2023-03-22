

package mainFiles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// A class for performing various analyses on a set of Instagrammers data

public class GetInstagrammersStats {
	// A class for performing various analyses on a set of Instagrammers data
	
public static Optional<Instagrammer> getTopIGerInCategory(Stream<Instagrammer> instagrammers,String category) {
	// Given a Stream of Instagrammers and a category, return the top Instagrammer 
	// (by rank) in the given category. If there are no Instagrammers in that category, return an empty Optional<Instagrammer>.

	List<Instagrammer> the_List = new ArrayList<Instagrammer> ();
	Stream<Instagrammer> the_List2;
	List<Instagrammer> the_List3 = new ArrayList<Instagrammer> ();

	
	the_List = instagrammers.filter(e -> (e.getCategory().toUpperCase().contains(category.toUpperCase())) || (e.getCategory().toUpperCase().equals(category.toUpperCase()))).collect(Collectors.toList());
	the_List2 = the_List.stream();
	
	the_List3 = the_List2.sorted(((s, t) -> s.getRank() - t.getRank())).collect(Collectors.toList());
	
	
	if (the_List3.size() != 0) {
		return Optional.of(the_List3.get(0));
	}
	else {
		return Optional.empty();
	}
	
}

public static List<String> getTopNCategories(Stream<Instagrammer> instagrammers, int n) {
	// Given a Stream of Instagrammers and an integer n, return a list of the top categories
	
	List<Instagrammer> returned_List = new ArrayList<Instagrammer>();
	
	List<Instagrammer> the_List = new ArrayList<Instagrammer>();
	Stream<Instagrammer> the_List2;
	Map<String, Long> the_List3;
	List<Entry<String, Long>> the_List4;
	Stream<Entry<String, Long>> the_List5;
	List<Entry<String, Long>> the_List6;
	List<String> returnList;
	
	
	List<Instagrammer> numberofItems = instagrammers.collect(Collectors.toList());
	
	if (numberofItems.size() < n) {
		the_List = numberofItems.stream().filter(e -> !e.getCategory().isBlank()).collect(Collectors.toList());
		the_List2 = the_List.stream();
		the_List3 = the_List2.collect(Collectors.groupingBy(e -> e.getCategory(), Collectors.counting()));
		the_List4 = the_List3.entrySet().stream().sorted((s, t) -> ((int)(t.getValue() -  s.getValue()))).collect(Collectors.toList());
		the_List6 = the_List4
				.stream()
				.sorted((s,t) -> {
					if ((t.getValue().compareTo(s.getValue()) ==  0)){
						if((t.getKey().compareTo(s.getKey()) < 0)){
							 return 0;
						}
						else {
							 return -1;
						}
						
					}
					return t.getValue().compareTo(s.getValue());
						
				})
		        .collect(Collectors.toList());
		returnList = the_List6.stream().map(s -> s.getKey()).collect(Collectors.toList());
	}
	else {
		the_List = numberofItems.stream().filter(e -> !e.getCategory().isBlank()).collect(Collectors.toList());
		the_List2 = the_List.stream();
		the_List3 = the_List2.collect(Collectors.groupingBy(e -> e.getCategory(), Collectors.counting()));
		the_List4 = the_List3.entrySet().stream().sorted((s, t) -> ((int)(t.getValue() -  s.getValue()))).collect(Collectors.toList());
		the_List6 = the_List4
				.stream()
				.sorted((s,t) -> {
					if ((t.getValue().compareTo(s.getValue()) ==  0)){
						if((t.getKey().compareTo(s.getKey()) < 0)){
							 return 0;
						}
						else {
							 return -1;
						}
						
					}
					return t.getValue().compareTo(s.getValue());
						
				})
				.limit(n)
		        .collect(Collectors.toList());	
		returnList = the_List6.stream().map(s -> s.getKey()).collect(Collectors.toList());
	}
	
	return returnList;
}

public static List<Instagrammer> getMostFollowedIGerInCountry(Stream<Instagrammer> instagrammers, String country) {
	// Given a Stream of Instagrammers and a country, return a List containing the 
	// Instagrammer(s) in that country with the most followers.
	
	Long biggestFollowing; 
	List<Instagrammer> tempList2;
	
	List<Instagrammer> tempList = instagrammers
		.filter((s) -> s.getCountry().equals(country))
		.sorted((s, t) -> ((int)t.getNumFollowers() - (int)s.getNumFollowers()))
		.collect(Collectors.toList());
	
	if(tempList.size() > 0) {
		biggestFollowing = tempList.get(0).getNumFollowers();
	}
	else {
		return tempList;
	}
	
	List<Instagrammer> returnList = tempList
			.stream()
			.filter((s) -> s.getNumFollowers() == (biggestFollowing))
			.collect(Collectors.toList());
	
	return returnList;
}

public static String getAllIGersInCountry(Stream<Instagrammer> instagrammers, String country) {
	// Given a Stream of Instagrammers and a country name, return a String that
	// contains the names of all Instagrammers in that country,
	
	String returnedString = "";
	
	List<String> tempList2;
	
	List<Instagrammer> tempList = instagrammers
		.filter((s) -> s.getCountry().equals(country))
		.sorted((s, t) -> (s.getName().compareTo(t.getName())))	
		.collect(Collectors.toList());
	
	tempList2 = tempList
		.stream()
		.map((s) -> returnedString + s.getName())
		.collect(Collectors.toList());
	
	String returnedString2 = String.join(" ", tempList2);
	
	return returnedString2; 
}

public static long countCountries(Stream<Instagrammer> instagrammers) {
	// Given a Stream of Instagrammers, return the number of different countries represented by the Instagrammers.
	
	HashSet<String> the_Set = new HashSet<String>();
	
	the_Set = (HashSet<String>) instagrammers
			.filter((s) -> !s.getCountry().isBlank())
			.map((s) -> s.getCountry())
			.collect(Collectors.toSet());
	
	return (long) the_Set.size(); 
}

public static Map<String, Double> getAvgNumFollowersPerIGerPerCountry(Stream<Instagrammer> instagrammers) {
	// Given a Stream of Instragrammers, return a Map<String, Double> that maps
	// each country to the average number of followers per Instagrammer in that country
	
	Map<String, Double> returnedMap;
	List<Entry<String, Double>> returnedMap2;	
	
	returnedMap = instagrammers
		.filter(e -> !e.getCountry().isBlank())
		.collect(Collectors.groupingBy((e) -> e.getCountry(), Collectors.averagingLong(Instagrammer::getNumFollowers)));	
	
	returnedMap2 = returnedMap
			.entrySet()
			.stream()
			.sorted((s,t) -> {
				
				if((t.getKey().compareTo(s.getKey()) < 0)){
					 return 0;
				}
				else {
					 return -1;
				}			
			})
			.collect(Collectors.toList());	
	
	Map<String, Double> returnedMap3 = returnedMap2
			.stream()
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	
	return returnedMap3; 
}

public static Optional<Instagrammer> findIGerByName(Stream<Instagrammer> instagrammers, String name) {
	// Given a Stream of Instagrammers and a name, return the first Instagrammer
	// found with that name. If there are no Instagrammers with that name,
	// return an empty Optional<Instagrammer>.
	
	Optional<Instagrammer> firstInstagrammer;
	
	List<Instagrammer> listofItems = instagrammers
			.filter((s) -> s.getName().equals(name))
			.collect(Collectors.toList());
	
	if(listofItems.size() > 0) {
		 firstInstagrammer = listofItems
				 .stream()
				 .filter((s) -> s.getName().equals(name))
				 .findFirst();
		 
		 return firstInstagrammer;
	}
	else {
		return Optional.empty();
	}
	
}

public static long countDuplicateIGers(Stream<Instagrammer>instagrammers) {
	// Given a Stream of Instagrammers, return the number of Instagrammers whose
	// name is a duplicate of another Instagrammer
	
	Map<String, Long> returnedMap;
	Map<String, Long> returnedMap2;
	long count;
	
	returnedMap = instagrammers
			.collect(Collectors.groupingBy((e) -> e.getName(), Collectors.counting()));	
	
	returnedMap2 = returnedMap
			.entrySet()
			.stream()
			.filter((s) -> (s.getValue() > 1))
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	
	return (long) returnedMap2.size(); 
}

public static List<String> getAllIGersInNumFollowersRange(Stream<Instagrammer> instagrammers, int min, int max) {
	// Given a Stream of Instagrammers and two integers (min and max), return a list
	// of the names of all Instagrammers with num followers within that range
	
	List<Instagrammer> returnedList;
	List<Instagrammer> returnedList2;
	List<Instagrammer> returnedList3;
	List<String> returnedList4;
	
	returnedList = instagrammers
		.filter((s) -> {
		
			if(s.getNumFollowers() >= min) {
				 return true;
			}
			return false;
		
		
	     })
		.collect(Collectors.toList());
	
	
	returnedList2 = returnedList
		.stream()
		.filter((s) -> {
			
			if(s.getNumFollowers() <= max) {
				 return true;
			}
			return false;
		
		
	     })
		.collect(Collectors.toList());
	
	returnedList3 = returnedList2
			.stream()
			.sorted((s,t) -> {
				
				if((t.getName().compareTo(s.getName()) < 0)){
					 return 0;
				}
				else {
					 return -1;
				}			
			})
			.collect(Collectors.toList());
	
	returnedList4 = returnedList3
			.stream()
			.map((s) -> s.getName())
			.collect(Collectors.toList());
	
	return returnedList4; 
}

public static Optional<Instagrammer> getIGerWithLongestName(Stream<Instagrammer> instagrammers) {
	// Given a Stream of Instagrammers, return the Instagrammer with the longest name. 
	// If there is more than one Instagrammer with the longest name, return the first one found.
	
	List<Instagrammer> returnedList;
	List<String> returnedList2;
	List<Instagrammer> returnedList3;
	List<String> returnedList4;
	List<Instagrammer> returnedList5;
	Optional<Instagrammer> foundItem;
		
	Stream<Instagrammer> listofItems ;
	
	returnedList = instagrammers
			.filter((s) -> !s.getName().isBlank())
			.collect(Collectors.toList());
	
	if(returnedList.size() == 0) {
		return  Optional.empty();
	}
	
	listofItems = returnedList
			.stream();
	
	returnedList2 = returnedList
		.stream()
		.map((s) -> s.getName())
		.collect(Collectors.toList());
	
	String returnString = returnedList2
			.stream()
			.max(Comparator.comparingInt(String::length))	
			.get();

	returnedList5 = listofItems
			.filter((s) -> s.getName().equals(returnString))
			.collect(Collectors.toList());
	
	if(returnString.length() == 0) {
		return Optional.empty();
	}
	else {
		foundItem = returnedList5
				.stream()
				.findFirst();
		
		return foundItem; 
	}
}
}

