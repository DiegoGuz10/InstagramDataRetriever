
package mainFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstagrammersCSVReader {
//Reads a CSV-formatted file of top Instagrammers data, and returns a list
	
	public static List<Instagrammer> readFile(String filename) {
		List<Instagrammer> IGers = new ArrayList<>();
	
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNextLine()) {
				
			String line = in.nextLine();
			Scanner scanner = new Scanner(line);
			scanner.useDelimiter(",");
			String name = scanner.next();
			int rank = scanner.nextInt();
			String category = scanner.next();
			long numFollowers = parseNumber(scanner.next());
			String country = scanner.next();
			long engagement = parseNumber(scanner.next());
			scanner.close();
			IGers.add(new Instagrammer(name, rank, category,
			numFollowers, country, engagement));
			
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File: " + filename + " not found");
		  }
		
		return IGers;
		}
	
		private static long parseNumber(String str) {
			int multiplier = 1;
			if (str.endsWith("K")) {
			multiplier = 1000;
			str = str.substring(0, str.length()-1);
		}
		else if (str.endsWith("M")) {
			multiplier = 1000000;
			str = str.substring(0, str.length()-1);
		}
		return Math.round(Double.parseDouble(str) * multiplier);
		}
		
		public static void main(String[] args) {
			List<Instagrammer> IGers = InstagrammersCSVReader.readFile("res/top_instagrammers.csv");
			
		}
	}
