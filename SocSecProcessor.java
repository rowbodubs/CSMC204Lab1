import java.util.Scanner;

public class SocSecProcessor {
	public static void main(String[] args) {
		//initiate vars
		String yORn;
		Scanner scanner = new Scanner(System.in);
		String ssn;
		String name;
		
		//main loop
		do {
			try {
				//take inputs
				System.out.print("Name? ");
				name = scanner.nextLine();
				System.out.print("SSN? ");
				ssn = scanner.nextLine();
				
				//check if ssn is valid
				if (isValid(ssn)) {
					System.out.println(name + " " + ssn + " is valid");
				}
			}
			
			//catch if the ssn is invalid
			catch(SocSecException e) {
				//output error message
				System.out.println(e.getLocalizedMessage()); //
			}
			
			//handle continuing
			System.out.print("Continue? (y/n) ");
			yORn = scanner.nextLine();
		}
		while (yORn.equals("y"));
		//cleanup
		scanner.close();
	}
	public static boolean isValid(String ssn) throws SocSecException{ //checks if a social security number is valid
		//initialize variables
		boolean output = true;
		String errorMessage ="";
		
		//check if ssn length is correct
		if (ssn.length() != 11) {
			errorMessage = "wrong number of characters";
			output = false;
		}
		
		//if the length is correct iterate through each character and check them
		else {
			for (int i = 0; i < ssn.length(); i++) {
				//check if hyphons are in the spots they're meant to be
				if (i == 3 || i == 6) {
					if (ssn.charAt(i) != '-') {
						errorMessage = "dashes at wrong positions";
						output = false;
						break;
					}
				}
				//check that each character is a digit (except for the hyphons that are meant to be there)
				else {
					if (!Character.isDigit(ssn.charAt(i))) {
						errorMessage = "contains a character that is not a digit";
						output = false;
						break;
					}
				}
			}
		}
		//if an error was found throw an exception
		if (!output) {
			throw new SocSecException(errorMessage);
		}
		
		//return whether or not ssn was valid
		return output;
	}
}
