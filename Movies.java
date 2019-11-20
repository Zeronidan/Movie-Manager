import java.util.Arrays;

/**
 * 
 * Vivian Thach (33939402) Diana Sandil (10443456)
 * 
 * Movies: This is a class that represents a movie containing
 * 		the movie's code, movie's name, a number representing the rented
 * 		copies of the movie, and an array of renters renting that movie.
 * 		Keeps track of its Renters as well as sorting them.
 */
public class Movies
{
	/*
	 * Private variables: movieCode, movieName, rentedCount, Renter[] rentersArray
	 */
	private String movieCode;
	private String movieName;
	private int rentedCount;
	private Renter[] rentersArray;
	
	// Default constructor for Movies()
	public Movies() {
		movieCode = "";
		movieName = "";
		rentedCount = 0;
		rentersArray = new Renter[10];
	}
	
	//Constructor for Movies that initializes the given
	//movieCode and movieName
	public Movies(String movieCode, String movieName) {
		this.movieCode = movieCode;
		this.movieName = movieName;
		rentedCount = 0;
		rentersArray = new Renter[10];
	}
	/*
	 * rentMovie - adds a renter to the movie and it sorts the array
	 * - throws RenterLimitException, DuplicateRenterException
	 */
	public void rentMovie(Renter r) throws RenterLimitException, DuplicateRenterException{
		boolean renterIDExists = false;
		int indexToReplace = rentedCount;
		if (rentedCount == 10){
			throw new RenterLimitException();
		}
		for (int index = 0; index < rentedCount; index ++){
			if (rentersArray[index].getRenterID() == (r.getRenterID())){
				renterIDExists = true;
				break;
			}
			//Find where renter r fits in sorted array
			System.out.println(index);
			System.out.println(rentedCount);
			System.out.println("Index: " + rentersArray[index].getLastName());
			System.out.println("To Add: " + r.getLastName());
			if(rentersArray[index].getLastName().compareTo(r.getLastName()) > 0){//ordered after
				System.out.println(rentersArray[index].getLastName() + "Is ordered after " + r.getLastName());
				indexToReplace = index;
				break;
			}
			else if (rentersArray[index].getLastName().compareTo(r.getLastName()) == 0){// same last name
				if (rentersArray[index].getRenterID() > (r.getRenterID())){
					indexToReplace = index;
					break;
				}
			}
		}
		if (renterIDExists){
			throw new DuplicateRenterException();
		}
		
		//shift array to be sorted if needed
		for (int i = rentedCount-1; i >= indexToReplace; i--){
			rentersArray[i+1] = rentersArray[i];	
		}
		rentersArray[indexToReplace] = r;
		rentedCount++;

	}
	
	/*
	 * returnRental - removes a renter from the movie based on renter ID
	 * - throws EmptyRenterListException, RenterNotFoundException
	 */
	public void returnRental(int renterId) throws EmptyRenterListException, RenterNotFoundException{
		boolean found = false;
		Renter renter = new Renter();
		int indexToRemove = 0;
		if (rentedCount == 0){
			throw new EmptyRenterListException();
		}
		for (int index = 0; index < rentedCount; index++){
			if (rentersArray[index].getRenterID() == renterId) {
				found = true;
				renter = rentersArray[index];
				indexToRemove = index;
				break;
			}
		}
		
		if (found == false){
			throw new RenterNotFoundException();
		}
		
		for(int i = indexToRemove; i < rentedCount - 1; i++){
			rentersArray[i] = rentersArray[i+1];
		}		
		rentedCount--;
	}
	
	// Starting from here it is getter methods
	public String getMovieCode() {
		return movieCode;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public int getRentedCount() {
		return rentedCount;
	}
	
	public Renter[] getRentersArray() {
		return rentersArray;
	}
	
	
	// Starting from here it is setter methods
	public void setMovieCode(String mc) {
		movieCode = mc;
	}
	
	public void setMovieName(String mn) {
		movieName = mn;
	}
	
	public void setRentedCount(int count) {
		rentedCount = count;
	}
}
