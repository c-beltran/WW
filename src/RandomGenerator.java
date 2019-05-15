/*
 * Question 3
 */
import java.util.*;

public class RandomGenerator {

	/*-Initializing data structured to be used-
	 * A TreeSet is ideal for this situation
	 * because we want unique values and in
	 * sorted order because the smallest 
	 * element will be first and so on
	 */
	private static Set<Integer> uniqueNumbers = new TreeSet<>();
	
	public static void main(String[] Args) {		
		Random rand = new Random();
		
		while(uniqueNumbers.size() < 500) {
			int value = rand.nextInt(10000);
			uniqueNumbers.add(value);
		} // time complexity of this while loop is linear time O(1)
		
		int userInput = userInput();
		
		printNthSmallestNumbber(userInput);
	}
	
	public static void printNthSmallestNumbber(int number) {
		//create a list containing all the unique values that belong to the set
		List<Integer> list = new ArrayList<>(uniqueNumbers);
		
		//we do number - 1 because ArrayList indexing starts at 0
		System.out.println(list.get(number - 1));
	}
	
	//userInput method for convenience and checking 
	//for user unwanted input
	public static int userInput() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a number in the range of 1 - 500.");
		int userInputNumber = input.nextInt();
		
		while(userInputNumber <= 0 || userInputNumber > 500) {
			System.out.println("Zero, negative, or numbers greater than 500 are not allowed, please try a number from 1 to 500.");
			userInputNumber = input.nextInt();
		}
		
		return userInputNumber;
	}
}
