import java.util.*;
class busticket
{
	final static String Red = "\u001b[31m";
	final static String Green = "\u001b[32m";
	final static String Yellow = "\u001b[33m";
	final static String Blue = "\u001b[34m";
	final static String Magenta = "\u001b[35m";
	final static String Cyan = "\u001b[36m";
	final static String Reset = "\u001b[0m";
	
	final static String BackgroundRed = "\u001b[41m";
	final static String BackgroundGreen = "\u001b[42m";
	final static String Backgroundyellow = "\u001b[43m";
	final static String BackgroundBlue = "\u001b[44m";
	final static String BackgroundMagenta = "\u001b[45m";
	final static String BackgroundCyan = "\u001b[46m";
	final static String BackgroundWhite = "\u001b[47m";	
	
	HashSet<String> bookedSeats;
	HashSet<String> availableSeats;
    public busticket() {
        bookedSeats = new HashSet<>();
		availableSeats = new HashSet<>();
		initializeAvailableSeats();
    }
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println(BackgroundRed+"------------------------ welcome to ticket booking system ------------------------"+Reset);
		
		busticket ticketbook = new busticket();
		ticketbook.booking();
	}

	public void booking()
	{
		Scanner sc = new Scanner(System.in);
		
		int i = 0;
		while(i != 3)
		{
			System.out.println(Cyan+"\n@@@@@ Enter 1 for view ticket");
			System.out.println("@@@@@ Enter 2 for book a ticket");
			System.out.println("@@@@@ Enter 3 for re-enter ticket details");
			System.out.println("@@@@@ Enter 4 for exit"+Reset);
			
			System.out.print(Yellow+"\nEnter your choise  :  "+Reset);
			int choise = sc.nextInt();
			
			switch(choise)
			{
				case 1:
				{
					viewAvailableSeats();
					
					break;
				}
				
				case 2:
				{
					person_detail();
					
					break;
				}
				
				case 3: 
				{
					person_detail();
					
					break;
				}
				
				case 4:
				{
					System.exit(0);
				}
				
				default:
				{
					System.out.println(Red+"Please enter valid number 1,2 or 3"+Reset);
				}
			}
			
			i = choise;
		}
	}
	void initializeAvailableSeats() {
       
        for (char row = 'A'; row <= 'H'; row++) {
            for (int col = 1; col <= 4; col++) {
                availableSeats.add(row + Integer.toString(col));
            }
        }
    }

    void viewAvailableSeats() {
        System.out.println(Green + "\n~~~~~ Available seats ~~~~~");
        for (char row = 'A'; row <= 'H'; row++) {
            for (int col = 1; col <= 4; col++) {
                String seat = row + Integer.toString(col);
                if (availableSeats.contains(seat)) {
                    System.out.print(Green + seat + " " + Reset);
                } else {
                    System.out.print(Red + "X" + " " + Reset); 
                }
            }
            System.out.println();
        }
    }
	
	void person_detail()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print(Yellow+"How many tickets do you want to boook?  -->  "+Reset);
		int n = sc.nextInt();
		
		String name[] = new String[n];
		int age[] = new int[n];
		
		for(int i=0; i<n; i++)
		{
			System.out.println(Yellow+"\nEnter " +(i+1)+ " person detail"+Reset);
			System.out.println();
			
			System.out.print(Yellow+"Enter name : "+Reset);
			name[i] = sc.nextLine();
			sc.nextLine();
			
			System.out.print(Yellow+"Enetr age : "+Reset);
			age[i] = sc.nextInt();
			bookTicket();
			if(n == 1)
			{
				if(age[i] < 15)
				{
					System.out.println(BackgroundMagenta+"Sorry you are not abel to book ticket"+Reset);
					System.exit(0);
				}
			}
			
			else
			{
				if(age[i] < 15)
				{
					System.out.println(Cyan+"if you have someone over the age of 15 with you so enter 1");
					System.out.println("if you don't have someone over the age of 15 with you so enter 2"+Reset);
					
					System.out.println(Yellow+"Enetr 1 or 2 : "+Reset);
					int choise = sc.nextInt();
					
					switch(choise)
					{
						case 1:
						{
							continue;
						}
						
						case 2:
						{
							System.out.println(BackgroundMagenta+"Sorry you are not abel to book ticket"+Reset);
							System.exit(0);
							
							break;
						}
						
						default:
						{
							System.out.println("enter valid number");
						}
					}
				}
			}
		}		
	}

	void bookTicket() {
		
		Scanner sc = new Scanner(System.in);

		System.out.print(Yellow + "Enter the seat number you want to book: " + Reset);
		String seat = sc.next();
		seat = seat.toUpperCase();
		if (bookedSeats.contains(seat)) {
            System.out.println(BackgroundMagenta + "Sorry, the seat is already booked. Please choose another seat." + Reset);
            // Give user a second chance
            bookTicket();
        } else if (!availableSeats.contains(seat)) {
            System.out.println(BackgroundMagenta + "Invalid seat number. Please choose a valid seat." + Reset);
            // Give user a second chance
            bookTicket();
        } else {
            bookedSeats.add(seat);
            availableSeats.remove(seat);
            System.out.println(Green + "Seat " + seat + " booked successfully!" + Reset);
        }
	}
}