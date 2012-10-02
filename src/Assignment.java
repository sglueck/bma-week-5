class Assignment {

	/* Homework for Week 4 */
	
	public static void main(String[] args) {
		
		/* If the args parameter is empty (length<2), print a
		 * message requesting an action and return from the function */
		int numArgs = args.length;
		String action = args[0];

		/* If there are no arguments, bail out */
		if (numArgs == 0) {
			System.out.println("Please specify an action.");
			return;
		}
		/* If there is only one argument, user forgot values */
		else if (numArgs < 2) {
			System.out.println("Please specify values.");
			return;
		}
		
		/* Declare and initialize a array of Strings called values
		 * to an array that can take 1 less element than args */
		
		String[] values = new String[numArgs-1];

		/* Assign to values all elements in the args array starting with
		 * the second element (skip the first)
		 *   a) Hint: if the args array's length is less than 2, do nothing.
		 *      There are no elements to copy.
		 *   b) Hint: this could be achieved with a for loop with its iteration
		 *      variable initialized to 1 */
		
		for(int i = 1; i <= numArgs; i++) {
			values[i-1] = args[i];
		}
		
		/* Check value of specified action (Remove System.out.println after initial test) */
		
		if (action.equals("for-each")) {
			System.out.println("match: " + action);
			testForEach(values); /* Don't forget objects are passed by reference */
		}
		else if (action.equals("for")) {
			System.out.println("match: " + action);
			testFor(values); /* Don't forget objects are passed by reference*/
		}
		else if (action.equals("while")) {
			System.out.println("match: " + action);
			testWhile(values); /* Don't forget objects are passed by reference*/
		}
		else {
			System.out.println("no match found for " + action);
		}

	} /* End main() */
	
	
	/* Handle for-each loop */	
	static void testForEach(String[] parms) {
		System.out.println("Testing for-each loop:");
		for (String value : parms) {
			System.out.println("Value is "+ value);
		}
	}
	
	/* Handle for loop */
	static void testFor(String[] parms) {
		System.out.println("Testing for loop:");
		for (int i = 0; i < parms.length; i++) {
			System.out.println("Value is "+ parms[i]);
		}
	}
	
	/* Handle while loop */
	static void testWhile(String[] parms) {
		System.out.println("Testing while loop");
		int i = 0;
		while (i < parms.length) {
			System.out.println("Value is "+ parms[i]);
			i++;
		}		
	}

} /* End Homework_10_01_2012 Class */
