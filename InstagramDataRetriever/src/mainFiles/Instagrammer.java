

package mainFiles;

// Represents an Instagrammer

public class Instagrammer implements Comparable<Instagrammer> {
	private String name;
	private int rank;
	private String category;
	private long numFollowers;
	private String country;
	private long engagement;

	public Instagrammer(String name, int rank, String category, long
		numFollowers, String country,
		long engagement) {
		this.name = name;
		this.rank = rank;
		this.category = category;
		this.numFollowers = numFollowers;
		this.country = country;
		this.engagement = engagement;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getCategory() {
		return category;
	}
	
	public long getNumFollowers() {
		return numFollowers;
	}
	
	public String getCountry() {
		return country;
	}
	
	public long getEngagement() {
		return engagement;
	}
	
	@Override
	public String toString() {
		return name + "," + rank + "," + category + "," + numFollowers + "," +country+ "," + engagement;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Instagrammer) {
			Instagrammer other = (Instagrammer) otherObject;
			return name.equals(other.name);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Instagrammer other) {
		//  compares lexicographically by name
		if (name.compareTo(other.name) < 0)
			return -1;
		if (name.compareTo(other.name) > 0)
			return 1;
			return 0;
		}
	}


