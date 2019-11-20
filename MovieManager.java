/**
 * 
 * Vivian Thach (33939402) Diana Sandil (10443456)
 * 
 * MovieManager: This class contains an array of Movies and the
 * 		total number of current movies.  Contains the main run program
 * 		that handles all custom exceptions
 * 
 */

import java.util.Arrays;

public class MovieManager
{
	/*
	 * Private variables: Movies[] movieArray, which is a list of movies
	 *  and movieCount that keeps track of the total number of current movies
	 */
	//array of movies (max size = 20)
	private Movies[] movieArray;
	// total number of current Movies
	private int movieCount;
	
	
	/*
	 * Constructor to initialize movie array and movie count
	 */
	public MovieManager(){
		movieArray = new Movies[20];
		movieCount = 0;
	}
	
	/*
	 * Getter method to return the count of movies
	 */
	public int getMovieCount(){
		return movieCount;
	}
	
	/*
	 * Getter method to return the array of movies
	 */
	public Movies[] getMovieArray(){
		return movieArray;
	}
		
	/*
	 * run - Runs the main loop of the program.
	 * Displays menu, accepts user input, handles each 
	 *    users commands
	 * Handles the custom Exceptions and displays error
	 *    messages to the user
	 *    -for user commands, call static methods in MovieManagerUI.java
	 */
	public void run(){
		System.out.println("Welcome to Class Roster Manager!");
		try{
			String command = "";
			while (!command.equalsIgnoreCase("q")){
				MovieManagerUI.printMenu();
				command = MovieManagerUI.getCommand();
				
				if (command.equalsIgnoreCase("am")){
					System.out.println("You want to add a movie");
					Movies m = MovieManagerUI.getMovieInfo();
					addMovie(m);
				}
				else if (command.equalsIgnoreCase("dm")){
					System.out.println("You want to discontinue a movie");
					String code = MovieManagerUI.getMovieCode();
					discontinueMovie(code);
				}
				else if (command.equalsIgnoreCase("rm")){
					System.out.println("You want to rent a movie");
					String code = MovieManagerUI.getMovieCode();
					Renter r = MovieManagerUI.getRenterInfo();
					rentMovie(code, r);
				}
				else if (command.equalsIgnoreCase("rr")){
					System.out.println("You want to return a rented movie");
					String code = MovieManagerUI.getMovieCode();
					int rID = MovieManagerUI.getRenterId();
					returnRental(rID, code);

				}
				else if (command.equalsIgnoreCase("p")){
					System.out.println("Printing inventory!");
					printInventory();
				}
				
			}
			System.out.println("Quitting program...");
		}
		catch(DuplicateMovieException e){
			System.out.println("DuplicateMovieException: Movie already exists.");
			run();
		}
		catch(MovieLimitException e){
			System.out.println("MovieLimitException: Reached max limit of movies.");
			run();
		}
		catch(EmptyMovieInfoException e){
			System.out.println("EmptyMovieInfoException: Movie code and/or Movie name is empty.");
			run();
		}
		catch(MovieNotFoundException e){
			System.out.println("MovieNotFoundException: The movie does not exist.");
			run();
		}
		catch(EmptyMovieListException e){
			System.out.println("EmptyMovieListException: The inventory list of movies is empty.");
			run();
		}
		catch(EmptyRenterListException e){
			System.out.println("EmptyRenterListException: Renter is not in the movie's list of renters.");
			run();
		}
		catch(RenterLimitException e) {
			System.out.println("RenterLimitException: No available copies for that movie.");
			run();
		}
		catch(RenterNotFoundException e) {
			System.out.println("RenterNotFoundException: Renter does not exist.");
			run();
		}
		catch(DuplicateRenterException e) {
			System.out.println("DuplicateRenterException: A renter of this ID already exists.");
			run();
		}
		catch(InvalidRenterIDException e) {
			System.out.println("InvalidRenterIDException: Invalid renter ID.");
			run();
		}
		catch(EmptyRenterNameException e) {
			System.out.println("EmptyRenterNameException: Renter name is empty.");
			run();
		}
		catch(RentedMovieException e) {
			System.out.println("RentedMovieException: Cannot remove movie, it is still being rented.");
			run();
		}
		
	}
	
	
	/*
	 * addMovie - adds the movie to the movie array
	 * - throws MovieLimitException, EmptyMovieInfoException,
	 * and DuplicateMovieException
	 */
	public void addMovie(Movies m) throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException {
		if (movieCount == 20) {
			throw new MovieLimitException();
		}
		for(int index = 0; index < movieCount; index++){
			if (movieArray[index].getMovieCode().equalsIgnoreCase(m.getMovieCode())){
				throw new DuplicateMovieException();
			}
		}
		movieArray[movieCount] = m;
		movieCount++;
	}
	
	/*
	 * discontinueMovie - removes the movie from the array of movies
	 * - throws MovieNotFoundException, EmptyMovieListException,
	 * RentedMovieException
	 */
	public void discontinueMovie(String movieCode) throws MovieNotFoundException, RentedMovieException, EmptyMovieListException{
		Movies movie = new Movies();
		boolean found = false;
		int indexToRemove = 0;
		if (movieCount == 0) {
			throw new EmptyMovieListException();
		}
		
		for (int index = 0; index < movieCount; index ++) {
			if (movieArray[index].getMovieCode().equalsIgnoreCase(movieCode)) {
				found = true;
				movie = movieArray[index];
				indexToRemove = index;
				break;
			}
		}
		if (found == false) {
			throw new MovieNotFoundException();
		}
		else if (movie.getRentedCount() > 0) {
			throw new RentedMovieException();
		}
		//remove movie from movieArray  
		
		for(int i = indexToRemove; i < movieCount - 1; i++){
			movieArray[i] = movieArray[i+1];
		}
		movieCount--;
	}
	
	
	/*
	 * rentMovie - adds a Renter to an existing movie code (movie)
	 * - throws RenterLimitException, DuplicateRenterException,
	 * MovieNotFoundException, EmptyRenterNameException
	 */
	public void rentMovie(String movieCode, Renter renter) throws RenterLimitException, 
	DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException{
		Movies movie = new Movies();
		boolean found = false;
		if (movieCount == 0) {
			throw new EmptyMovieListException();
		}
		for (int index = 0; index < movieCount; index ++){
			if (movieArray[index].getMovieCode().equalsIgnoreCase(movieCode)) {
				found = true;
				movie = movieArray[index];
				break;
			}
		}
		if (found == false) {
			throw new MovieNotFoundException();
		}
		movie.rentMovie(renter);		
	}
	
	/*
	 * returnRental - removes a Renter from an already existing movie code (movie)
	 * - throws RenterNotFoundException, EmptyRenterListException,
	 * MovieNotFoundException
	 */
	public void returnRental(int renterId, String movieCode) throws RenterNotFoundException, 
	EmptyRenterListException, MovieNotFoundException, EmptyMovieListException{
		boolean found = false;
		Movies movie = new Movies();
		if (movieCount == 0) {
			throw new EmptyMovieListException();
		}
		for (int index =0; index < movieCount; index ++){
			if (movieArray[index].getMovieCode().equalsIgnoreCase(movieCode)) {
				found = true;
				movie = movieArray[index];
				break;
			}
		}
		if (found == false) {
			throw new MovieNotFoundException();
		}
		movie.returnRental(renterId);	
	}
	
	/*
	 * printInventory - prints the information for all movies and their renters
	 */
	public void printInventory(){
		if (movieCount > 0){
			for (int index = 0; index < movieCount; index++) {
				System.out.println("Movie code: " + movieArray[index].getMovieCode());
				System.out.println("Movie name: " + movieArray[index].getMovieName());
				System.out.println("Number of copies rented: " + movieArray[index].getRentedCount());
				for (int index2 = 0; index2 < movieArray[index].getRentedCount(); index2++) {
					System.out.println("Renter ID: " + movieArray[index].getRentersArray()[index2].getRenterID());
					System.out.println("Name: " + movieArray[index].getRentersArray()[index2].getLastName() +", " 
					+ movieArray[index].getRentersArray()[index2].getFirstName());
				}
			}
		}
	}
	
	

}
