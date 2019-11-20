/**
 * 
 * Vivian Thach (33939402) Diana Sandil (10443456)
 * 
 * Renter: This class represents a renter and this contains
 * 		a number representing the ID for the renter and a string
 * 		for their first and last name. Renter IDs must be unique.
 * 
 */
public class Renter
{
	// Private variables: renterID, firstName, lastName

	private int renterID;
	private String firstName;
	private String lastName;
	
	// Default constructor for Renter
	public Renter() {
		renterID = 0;
		firstName = "";
		lastName = "";
	}
	
	//Constructor for Renter that initializes with the given
	//renterID, lastName, and firstName
	public Renter(int renterID, String lastName, String firstName) {
		this.renterID = renterID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Starting from here it's the getter methods
	
	public int getRenterID() {
		return renterID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	// Starting from here it's the setter methods
	
	public void setRenterID(int id) {
		renterID = id;
	}
	
	public void setFirstName(String fname) {
		firstName = fname;
	}
	
	public void setLastName(String lname) {
		lastName = lname;
	}
}
