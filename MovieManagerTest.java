/**
 * 
 * Vivian Thach (33939402) Diana Sandil (10443456)
 * 
 * MovieManagerTest: This class is the JUnit Test Suite class.
 * It initiates a total of 18 test cases: normal, error, and
 * boundary cases.
 */



import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MovieManagerTest
{

	
	
	/*
	 * Normal Testing Cases:
	 */
	
	//Testing the add movie method in the MovieManger class
	//Ensures that movie count and movie array is updated correctly
	@Test
	public void addMovieTest() throws MovieLimitException, EmptyMovieInfoException,
	DuplicateMovieException{
		MovieManager manager = new MovieManager();
		Movies m1 = new Movies("HpHd2", "Harry Potter and the Deathly Hollows Part 2");
		manager.addMovie(m1);
		int movieCt = manager.getMovieCount();
		assertEquals(movieCt, 1);
		assertEquals(manager.getMovieArray()[movieCt-1], m1);
		
		Movies m2 = new Movies("s1", "Sing");
		manager.addMovie(m2);
		movieCt = manager.getMovieCount();
		assertEquals(movieCt, 2);
		assertEquals(manager.getMovieArray()[movieCt-1], m2);
		
		Movies m3 = new Movies("FD", "Finding Dory");
		manager.addMovie(m3);
		movieCt = manager.getMovieCount();
		assertEquals(movieCt, 3);
		assertEquals(manager.getMovieArray()[movieCt-1], m3);
	}
	
	//Testing the discontinueMovie method in the MovieManager class
	//Ensures that movie count gets decremented, and the movie that
	//is discontinued is no longer in the movie array
	@Test
	public void  discontinueMovieTest() throws MovieNotFoundException, 
	RentedMovieException, EmptyMovieListException, MovieLimitException, 
	EmptyMovieInfoException, DuplicateMovieException{	
		MovieManager manager1 = new MovieManager();
		
		Movies m2 = new Movies("s1", "Sing");
		manager1.addMovie(m2);
		
		Movies m3 = new Movies("FD", "Finding Dory");
		manager1.addMovie(m3);
		
		manager1.discontinueMovie("S1");
		int movieCt = manager1.getMovieCount();
		assertEquals(movieCt, 1);
		assertEquals(manager1.getMovieArray()[movieCt-1].getMovieCode(), "FD");
	}
	
	//Testing rentMovie method of the MovieManager class
	//Ensures that rented count is incremented and the new
	//renter is added to the movie's renters array
	@Test
	public void rentMovieTest() throws RenterLimitException, DuplicateRenterException,
	MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException, MovieLimitException, 
	EmptyMovieInfoException, DuplicateMovieException{
		MovieManager manager1 = new MovieManager();
		Movies m1 = new Movies("HpHd2", "Harry Potter and the Deathly Hollows Part 2");
		manager1.addMovie(m1);

		Renter r1 = new Renter(123, "Thach", "Vivian");
		
		manager1.rentMovie("HpHd2", r1);
		assertEquals(m1.getRentedCount(), 1);
		assertEquals(m1.getRentersArray()[0], r1);
	}
	
	//Tests the sorting of renter array when adding a renter
	//to the array of renters
	@Test
	public void rentMovieCheckRenterArrayOrderTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException{
		MovieManager manager1 = new MovieManager();
		Movies m1 = new Movies("HpHd2", "Harry Potter and the Deathly Hollows Part 2");
		manager1.addMovie(m1);
		Renter r1 = new Renter(123, "Thach", "Vivian");
		Renter r2 = new Renter(234, "Sandil", "Diana");
		Renter r3 = new Renter(12, "Thach", "Derek");
		manager1.rentMovie("HpHd2", r1);
		manager1.rentMovie("HpHd2", r2);
		assertEquals(m1.getRentersArray()[m1.getRentedCount()-1], r1);
		assertEquals(m1.getRentersArray()[0], r2);
		manager1.rentMovie("HpHd2", r3);
		assertEquals(m1.getRentersArray()[m1.getRentedCount()-1], r1);
		assertEquals(m1.getRentersArray()[m1.getRentedCount()-2], r3);
		assertEquals(m1.getRentersArray()[0], r2);
		
		assertEquals(m1.getRentedCount(), 3);
		
	}
	
	//Testing returnRental method in the MovieManager class
	//Ensures that the rented count gets decremented and the
	//renter is no longer in the renters array
	@Test
	public void returnRentalTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException, RenterNotFoundException, EmptyRenterListException{
		MovieManager manager = new MovieManager();
		Movies m2 = new Movies("s1", "Sing");
		manager.addMovie(m2);
		manager.rentMovie("s1", new Renter(123, "Thach", "Vivian"));
		assertEquals(m2.getRentedCount(), 1);
		manager.returnRental(123, "s1");
		assertEquals(m2.getRentedCount(), 0);
		
	}
	
	//Tests the sorting of renter array when returning a rental
	//removing it from the renters array
	@Test
	public void returnRentalCheckRentalArrayOrderTest() throws RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException, RenterNotFoundException, EmptyRenterListException, MovieLimitException, EmptyMovieInfoException, DuplicateMovieException{
		MovieManager manager = new MovieManager();
		Movies m1 = new Movies("TI", "The Incredibles");
		manager.addMovie(m1);
		Renter r1 = new Renter(123, "Thach", "Vivian");
		Renter r2 = new Renter(234, "Sandil", "Diana");
		Renter r3 = new Renter(456, "Potter", "Harry");
		manager.rentMovie("TI", r1);
		manager.rentMovie("TI", r2);
		manager.rentMovie("TI", r3);
		assertEquals(m1.getRentedCount(), 3);

		manager.returnRental(234, "TI");
		assertEquals(m1.getRentedCount(), 2);
		assertEquals(m1.getRentersArray()[m1.getRentedCount()-1], r1);
		assertEquals(m1.getRentersArray()[m1.getRentedCount()-2], r3);	
	}
	
	
	
	
	/*
	 * Error Testing Cases:
	 */
	
	//Testing EmptyMovieListException when trying to discontinue
	//a movie
	@Test(expected=EmptyMovieListException.class) 
	public void emptyMovieListExceptionDiscontinueMovieTest() throws MovieNotFoundException, RentedMovieException, 
	EmptyMovieListException{
		MovieManager manager2 = new MovieManager();
		manager2.discontinueMovie("movieCode");
	}
	
	//Testing EmptyMovieListException when trying to rent
	//a movie
	@Test(expected=EmptyMovieListException.class) 
	public void emptyMovieListExceptionRentMovieTest() throws RenterLimitException, DuplicateRenterException, 
	MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException{
		MovieManager manager2 = new MovieManager();
		manager2.rentMovie("movieCode", new Renter(123, "Thach", "Vivian"));
	}
	
	//Testing EmptyMovieListException when trying to return
	//a movie
	@Test(expected=EmptyMovieListException.class) 
	public void emptyMovieListExceptionReturnRentalTest() throws RenterNotFoundException, EmptyRenterListException,
	MovieNotFoundException, EmptyMovieListException {
		MovieManager manager2 = new MovieManager();
		manager2.returnRental(123, "movieCode");
	}
	
	//Testing DuplicateMovieException when trying
	//to add two movies of the same movie code
	@Test(expected=DuplicateMovieException.class)
	public void duplicateMovieExceptionTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("OPFZ", "One Piece: Film Z"));
		manager.addMovie(new Movies("OPFZ", "One Punch For Zen"));

	}
	
	//Testing MovieNotFoundException when trying to
	//rent a movie with a movie code that doesn't exist
	@Test(expected=MovieNotFoundException.class)
	public void movieNotFoundExceptionRentMovieTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FN", "Finding Nemo"));
		manager.rentMovie("FD", new Renter(123, "Thach", "Vivian"));
	}
	
	//Testing MovieNotFoundException when trying to discontinue
	// a movie with a movie code that doesn't exist
	@Test(expected=MovieNotFoundException.class)
	public void movieNotFoundExceptionDiscontinueMovieTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, MovieNotFoundException, RentedMovieException, EmptyMovieListException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FN", "Finding Nemo"));
		manager.discontinueMovie("FD");
	}
	
	//Testing MovieNotFoundException when trying to return
	// a rental for a movie code that doesn't exist
	@Test(expected=MovieNotFoundException.class)
	public void movieNotFoundExceptionReturnRentalTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException, RenterNotFoundException, EmptyRenterListException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FN", "Finding Nemo"));
		manager.rentMovie("FN", new Renter(123, "Thach", "Vivian"));
		manager.returnRental(123, "FD");
	}
	
	//Testing RenterNotFoundException when trying to return
	//a rental with a renter ID that doesn't exist
	@Test(expected=RenterNotFoundException.class)
	public void renterNotFoundExceptionTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException, RenterNotFoundException, EmptyRenterListException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FN", "Finding Nemo"));
		manager.rentMovie("FN", new Renter(123, "Thach", "Vivian"));
		manager.returnRental(12, "FN");


	}
	//Testing DuplicateRenterException when trying to rent a movie
	//with a renter ID that already exists
	@Test(expected=DuplicateRenterException.class)
	public void DuplicateRenterExceptionTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FN", "Finding Nemo"));
		manager.rentMovie("FN", new Renter(123, "Thach", "Vivian"));
		manager.rentMovie("FN", new Renter(123, "Thach", "Derek"));
	}
	
	
	//Testing RentedMovieException when trying to discontinue
	//a movie that is currently being rented
	@Test(expected=RentedMovieException.class)
	public void rentedMovieExceptionTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException, RentedMovieException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FN", "Finding Nemo"));
		manager.rentMovie("FN", new Renter(123, "Thach", "Vivian"));
		manager.discontinueMovie("FN");
	}
	
	
	/*
	 * Boundary Testing Cases
	 */
	
	//Test to ensure that the MovieLimitException is thrown
	//when the trying to add more than 20 movies 
	@Test(expected=MovieLimitException.class)
	public void movieLimitExceptionTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException{
		MovieManager manager3 = new MovieManager();
		manager3.addMovie(new Movies("1", "a"));
		manager3.addMovie(new Movies("2", "b"));
		manager3.addMovie(new Movies("3", "c"));
		manager3.addMovie(new Movies("4", "d"));
		manager3.addMovie(new Movies("5", "e"));
		manager3.addMovie(new Movies("6", "f"));
		manager3.addMovie(new Movies("7", "g"));
		manager3.addMovie(new Movies("8", "h"));
		manager3.addMovie(new Movies("9", "i"));
		manager3.addMovie(new Movies("10", "j"));
		manager3.addMovie(new Movies("11", "k"));
		manager3.addMovie(new Movies("12", "l"));
		manager3.addMovie(new Movies("13", "m"));
		manager3.addMovie(new Movies("14", "n"));
		manager3.addMovie(new Movies("15", "o"));
		manager3.addMovie(new Movies("16", "p"));
		manager3.addMovie(new Movies("17", "q"));
		manager3.addMovie(new Movies("18", "r"));
		manager3.addMovie(new Movies("19", "s"));
		manager3.addMovie(new Movies("20", "t"));
		//Trying to add past the limit (20)
		manager3.addMovie(new Movies("21", "CAN'T ADD"));
	}
	
	//Test to ensure that the RenterLimitException is thrown
	//when the trying to add more than 10 renters to a movie
	@Test(expected=RenterLimitException.class)
	public void renterLimitExceptionTest() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, EmptyMovieListException{
		MovieManager manager = new MovieManager();
		manager.addMovie(new Movies("FD", "Finding Dory"));
		manager.rentMovie("FD", new Renter(123, "Thach", "Vivian"));
		manager.rentMovie("FD", new Renter(234, "Franklin", "Benjamin"));
		manager.rentMovie("FD", new Renter(345, "Roronoa", "Zoro"));
		manager.rentMovie("FD", new Renter(456, "Uzumaki", "Naruto"));
		manager.rentMovie("FD", new Renter(567, "Cormier", "Delphine"));
		manager.rentMovie("FD", new Renter(678, "Niehaus", "Cosima"));
		manager.rentMovie("FD", new Renter(789, "Queen", "Oliver"));
		manager.rentMovie("FD", new Renter(91011, "Allen", "Barry"));
		manager.rentMovie("FD", new Renter(9833, "Danvers", "Kara"));
		manager.rentMovie("FD", new Renter(987, "Luthor", "Lex"));
		//Trying to add past the limit (10)
		manager.rentMovie("FD", new Renter(876, "Pratt", "Chris"));


	}
	
	
	
	
//	public static void addMovieTest(MovieManager manager){
//		Movies m1 = new Movies("HPDH2", "Harry Potter and the Deathly Hollows Part 2");
//		manager.addMovie(m1);
//	}

}
