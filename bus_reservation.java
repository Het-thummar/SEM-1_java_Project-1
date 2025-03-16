import java.util.*;

class busticket
{
	// Defining color codes for styling the output in the console.
	final static String Red = "\u001b[31m";
	final static String Green = "\u001b[32m";
	final static String Yellow = "\u001b[33m";
	final static String Blue = "\u001b[34m";
	final static String Magenta = "\u001b[35m";
	final static String Cyan = "\u001b[36m";
	final static String Reset = "\u001b[0m";
	
	// Background color codes for styling output.
	final static String BackgroundRed = "\u001b[41m";
	final static String BackgroundGreen = "\u001b[42m";
	final static String Backgroundyellow = "\u001b[43m";
	final static String BackgroundBlue = "\u001b[44m";
	final static String BackgroundMagenta = "\u001b[45m";
	final static String BackgroundCyan = "\u001b[46m";
	final static String BackgroundWhite = "\u001b[47m";	
	
	// HashSets to keep track of available and booked seats.
	HashSet<String> bookedSeats;
	HashSet<String> availableSeats;

	// Constructor for initializing the available and booked seats.
    public busticket() {
        bookedSeats = new HashSet<>();
		availableSeats = new HashSet<>();
		initializeAvailableSeats(); // Call method to initialize available seats.
    }

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		// Welcome message with some color and formatting
		System.out.println(BackgroundRed+"------------------------ welcome to ticket booking system ------------------------"+Reset);
		
		// Create a new busticket object and start the booking process
		busticket ticketbook = new busticket();
		ticketbook.booking();  // Start the booking process
	}

	// Method for the main ticket booking flow
	public void booking()
	{
		Scanner sc = new Scanner(System.in);
		
		int i = 0;
		while(i != 3)
		{
			// Show menu options for the user
			System.out.println(Cyan+"\n@@@@@ Enter 1 for view ticket");
			System.out.println("@@@@@ Enter 2 for book a ticket");
			System.out.println("@@@@@ Enter 3 for re-enter ticket details");
			System.out.println("@@@@@ Enter 4 for exit"+Reset);
			
			System.out.print(Yellow+"\nEnter your choice  :  "+Reset);
			int choise = sc.nextInt();
			
			// Handle user choice based on input
			switch(choise)
			{
				case 1:
				{
					viewAvailableSeats();  // View available seats
					break;
				}
				
				case 2:
				{
					person_detail();  // Enter person details for booking
					break;
				}
				
				case 3: 
				{
					person_detail();  // Re-enter person details for booking
					break;
				}
				
				case 4:
				{
					System.exit(0);  // Exit the application
				}
				
				default:
				{
					// If the user enters invalid choice
					System.out.println(Red+"Please enter valid number 1, 2 or 3"+Reset);
				}
			}
			
			i = choise;  // Store user's choice to check if the loop should continue
		}
	}

	// Initialize the available seats (A1 to H4)
	void initializeAvailableSeats() {
        for (char row = 'A'; row <= 'H'; row++) {
            for (int col = 1; col <= 4; col++) {
                availableSeats.add(row + Integer.toString(col)); // Add seats A1, A2, ... H4
            }
        }
    }

	// Display available seats (in green) and booked ones (in red 'X')
    void viewAvailableSeats() {
        System.out.println(Green + "\n~~~~~ Available seats ~~~~~");
        for (char row = 'A'; row <= 'H'; row++) {
            for (int col = 1; col <= 4; col++) {
                String seat = row + Integer.toString(col);
                if (availableSeats.contains(seat)) {
                    System.out.print(Green + seat + " " + Reset);  // Print available seats in green
                } else {
                    System.out.print(Red + "X" + " " + Reset); // Print 'X' for booked seats in red
                }
            }
            System.out.println();
        }
    }

	// Collect and process the person details for booking tickets
	void person_detail()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print(Yellow+"How many tickets do you want to book?  -->  "+Reset);
		int n = sc.nextInt();  // Number of tickets to be booked
		
		String name[] = new String[n];  // Array to store names of people
		int age[] = new int[n];  // Array to store ages of people
		
		// Loop through each person
		for(int i = 0; i < n; i++)
		{
			System.out.println(Yellow+"\nEnter " +(i + 1)+ " person's details"+Reset);
			System.out.println();
			
			System.out.print(Yellow+"Enter name : "+Reset);
			name[i] = sc.nextLine();  // Read name
			sc.nextLine();  // Consume the newline character
			
			System.out.print(Yellow+"Enter age : "+Reset);
			age[i] = sc.nextInt();  // Read age
			bookTicket();  // Call method to book ticket
			
			// If the person is under 15, we deny booking
			if(n == 1)
			{
				if(age[i] < 15)
				{
					System.out.println(BackgroundMagenta+"Sorry you are not able to book a ticket"+Reset);
					System.exit(0);  // Exit if the person is under 15
				}
			}
			
			// If there are multiple people
			else
			{
				if(age[i] < 15)
				{
					System.out.println(Cyan+"If you have someone over the age of 15 with you, enter 1");
					System.out.println("If you don't have anyone over 15 with you, enter 2"+Reset);
					
					System.out.println(Yellow+"Enter 1 or 2 : "+Reset);
					int choise = sc.nextInt();
					
					// If the user chooses 1, they can continue
					switch(choise)
					{
						case 1:
						{
							continue;
						}
						
						// If the user chooses 2, deny booking
						case 2:
						{
							System.out.println(BackgroundMagenta+"Sorry you are not able to book a ticket"+Reset);
							System.exit(0);  // Exit the program
							
							break;
						}
						
						// If the user enters invalid choice
						default:
						{
							System.out.println("Enter a valid number");
						}
					}
				}
			}
		}		
	}

	// Method to book a ticket by selecting a seat
	void bookTicket() {
		
		Scanner sc = new Scanner(System.in);

		System.out.print(Yellow + "Enter the seat number you want to book: " + Reset);
		String seat = sc.next();  // Get the seat input from the user
		seat = seat.toUpperCase();  // Convert input to uppercase for consistency
		
		// Check if the seat is already booked
		if (bookedSeats.contains(seat)) {
            System.out.println(BackgroundMagenta + "Sorry, the seat is already booked. Please choose another seat." + Reset);
            bookTicket();  // Give the user another chance to book a different seat
        } 
		// Check if the seat is valid
		else if (!availableSeats.contains(seat)) {
            System.out.println(BackgroundMagenta + "Invalid seat number. Please choose a valid seat." + Reset);
            bookTicket();  // Give the user another chance to select a valid seat
        } else {
            bookedSeats.add(seat);  // Add the seat to the booked list
            availableSeats.remove(seat);  // Remove the seat from the available list
            System.out.println(Green + "Seat " + seat + " booked successfully!" + Reset);  // Confirmation message
        }
	}
}
