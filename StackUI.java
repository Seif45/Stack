package eg.edu.alexu.csd.datastructure.stack.cs34;

import java.util.Scanner;

/**
 * @author cs34
 * user interface to test the Stack implementation and validity of its methods
 */
public class StackUI {

	public static void main(String[] args) {
		Stack action = new Stack();
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\r\n");
		String input;
		int choice;
		Object poped;
		while (true) {
			/**
			 * available actions
			 */
			System.out.println("Choose an action:");
			System.out.println("------------------");
			System.out.println("1: Push");
			System.out.println("2: Pop");
			System.out.println("3: Peek");
			System.out.println("4: Get size");
			System.out.println("5: Check if empty");
			System.out.println("6: Exit");
			System.out.println("==================================================");
			input = scan.next();
			/**
			 * validating the action
			 */
			while (Integer.valueOf(input) != 1 && Integer.valueOf(input) != 2 && Integer.valueOf(input) != 3 && Integer.valueOf(input) != 4 && Integer.valueOf(input) != 5 && Integer.valueOf(input) != 6) {
				System.out.println("Invalid input");
				System.out.println("Choose an action:");
				System.out.println("------------------");
				System.out.println("1: Push");
				System.out.println("2: Pop");
				System.out.println("3: Peek");
				System.out.println("4: Get size");
				System.out.println("5: Check if empty");
				System.out.println("6: Exit");
				System.out.println("==================================================");
				input = scan.next();
			}
			choice = Integer.valueOf(input);
			if (choice == 1) {
				System.out.println("Insert the Object to push:");
				action.push(scan.next());
			}
			else if (choice == 2) {
				poped = action.pop();
				/**
				 * error if stack is empty
				 */
				if (poped == null) {
					System.out.println("Error");
				}
				else {
					System.out.println("Output: " + poped);
				}
			}
			else if (choice == 3) {
				
				if (action.peek() == null) {
					System.out.println("Error");
				}
				else {
					System.out.println("Output: " + action.peek());
				}
			}
			else if (choice == 4) {
				System.out.println("Size is " + action.size());
			}
			else if (choice == 5) {
				if (action.isEmpty()) {
					System.out.println("The Stack is empty.");
				}
				else {
					System.out.println("The Stack isn't empty.");
				}
			}
			else if (choice == 6) {
				System.out.println("Thanks.");
				break;
			}
			System.out.println("==================================================");
		}
		scan.close();
	}

}
