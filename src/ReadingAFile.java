/*
 * Question 1
 */
import java.io.File;
import java.util.*;
public class ReadingAFile {
	
	/*
	 * we will use a TreeMap because
	 * as we know dictionary are in 
	 * alphabetical order and Treemap
	 * does this for us.
	 */
	private TreeMap<String, String> dictionary = new TreeMap<>();
	private Scanner inputFile;
	
	public static void main(String[] args) {
		
		//instantiating the class object
		ReadingAFile classObj = new ReadingAFile();
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Please enter a file path: (HINT: 'Dictionary.txt') ");
		String userFilePath = userInput.nextLine();
		
		while(!classObj.doesFileExist(userFilePath)) {
			System.out.println("Enter a valid File Path.");
			userFilePath = userInput.nextLine();
		}
		
		if(classObj.doesFileExist(userFilePath)) {
			classObj.readAndPrint(userFilePath);
		}		
	}
	
	public boolean doesFileExist(String path) {
		
		try{
			File f = new File(path);
			inputFile = new Scanner(f);
			
			if(inputFile.hasNextLine()) {
				return true;
			}

		}
		catch(Exception err){
			System.out.println("could not find file, (HINT: use 'Dictionary.txt')");
		}
		
		return false;
	}
	
	public void readAndPrint(String validPath) {
		//reading file and saving to TreeMap
		while(inputFile.hasNextLine()) {
			
			String word = inputFile.nextLine();
			String key = word.split(" -")[0];
			String value = word.split("\\- ")[1];
			dictionary.put(key, value);
		}
		inputFile.close();
		
		//printing words and meanings		
		for(String key : dictionary.keySet()) {
			String value = dictionary.get(key);
			//printing the word
			System.out.println(key);
			
			//printing all the different meanings of the word
			if(value.contains(",")) {
				String[] meanings = value.split(", ");
				
				for(int i=0; i<meanings.length; i++) {
					System.out.println(meanings[i]);
				}
				System.out.println("**************");
			}
			else {
				System.out.println(value);
				System.out.println("**************");
			}
		}
		/*Best case time complexity of this loop is O(logn) since we traverse through the tree when there are no commas.
		 * However, time can increase if the values contain n # of commas i believe the time would be O(nlogn)
		 */
		
	}
	
}
