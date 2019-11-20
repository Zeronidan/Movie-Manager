/**
 * 
 * Vivian Thach (33939402) Diana Sandil (10443456)
 * 
 * MovieManagerUI: This is the class representing the I/O between the program and user.
 * there isn't a "state" to keep, so all methods can be
 * static
 * 
 * This class deals with obtaining info from the user including getting
 * a command, and input for those commands
 */

import java.util.Scanner;

public class MovieManagerUI
{
	/*
	 * printMenu - prints menu options to the console
	 */
	public static void printMenu(){
		System.out.println("Select an action based on the following menu:");
		System.out.println("----------");
		System.out.println("am: Add Movie");
		System.out.println("dm: Discontinue Movie");
		System.out.println("rm: Rent Movie");
		System.out.println("rr: Return Rental");
		System.out.println(" p: Print Movie Inventory");
		System.out.println(" q: Quit Program");
		System.out.println("----------");
	}
	
	/*
	 * getCommand - gets the command from the users.
	 * Loops until user enters a valid command and returns it
	 */
	public static String getCommand(){
		System.out.println("Enter Command: ");
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();
		while (!response.equalsIgnoreCase("am") && !response.equalsIgnoreCase("dm") && !response.equalsIgnoreCase("rm")
				&& !response.equalsIgnoreCase("rr") && !response.equalsIgnoreCase("p") && !response.equalsIgnoreCase("q")) {
			System.out.println("Invalid input. Please re-enter command.");
			response = in.nextLine();
		}
		return response;
	}
	
	/*
	 * getMovieInfo - gets movie information from user input
	 * when adding a new movie
	 * - movie code and movie name
	 * - create Movies object and return it
	 */
	public static Movies getMovieInfo() throws EmptyMovieInfoException {
		System.out.println("Enter the movie code: ");
		Scanner in = new Scanner(System.in);
		String movieCode = in.nextLine();
		if (movieCode.length() == 0) {
			throw new EmptyMovieInfoException();
		}
		System.out.println("Enter the movie name: ");
		String movieName = in.nextLine();
		if (movieName.length() == 0){
			throw new EmptyMovieInfoException();

		}
		return new Movies(movieCode, movieName); 
	}
	
	/*
	 * getMovieCode - returns movie code when user
	 * wants to add a renter to a movie or remove a
	 * movie
	 * 
	 */
	public static String getMovieCode() throws EmptyMovieInfoException {
		System.out.println("Enter the movie code: ");
		Scanner in = new Scanner(System.in);
		String movieCode = in.nextLine();
		if (movieCode.length() == 0) {
			throw new EmptyMovieInfoException();
		}
		
		return movieCode; 
	}
	
	/*
	 * getRenterInfo - prompts user to enter
	 * renter information when adding a renter to a movie
	 * - renterID, first name, last name
	 * - create Renter object and return it
	 */
	public static Renter getRenterInfo() throws EmptyRenterNameException, InvalidRenterIDException{
		System.out.println("Enter the Renter ID: ");
		Scanner in = new Scanner(System.in);
		int renterId = Integer.parseInt(in.nextLine());
		if (renterId < 0) {
			throw new InvalidRenterIDException();
		}
		System.out.println("Enter your last name: ");
		String lname = in.nextLine();
		if (lname.length() == 0){
			throw new EmptyRenterNameException();

		}
		System.out.println("Enter your first name: ");
		String fname = in.nextLine();	
		if (fname.length() == 0) {
			throw new EmptyRenterNameException();
		}
		
		
		return new Renter(renterId, lname, fname);
	}
	
	/*
	 * getRenterId - prompts user for renterID when
	 * trying to remove a renter from a movie
	 */
	public static int getRenterId() throws InvalidRenterIDException{
		System.out.println("Enter the Renter ID: ");
		Scanner in = new Scanner(System.in);
		int renterId = in.nextInt();
		
		if (renterId < 0) {
			throw new InvalidRenterIDException();
		}
		
		return renterId; 
	}
	
	

}
