// Homework assignment for Week 5; Due 10/8/12
// Susan Lueck

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;

public class Assignment {

	public static void main(String[] args) {

		// Initialize
		String myElement = null;
		String[] myValues = new String[0];
		
		// Load myValues with arguments
		if( args.length == 0 ) {
			return;
		}
		else {
			myValues = new String[args.length];
			for( int i=0; i<args.length; i++ ) {
				myValues[i] = args[i];
			}
		}
		
		// Get the elements in the array and display them
		for( int i = 0; i < myValues.length; i++ ) {
			myElement = getElement(myValues, i);
			System.out.println("myElement is " + myElement); // Debug
		}
				
		// Canned Parameters
		String taskDesc = "Halloween";
		String taskDueDate = "10/31/2012";
		int taskPriority = 1;
		String taskReminderDate = "10/30/2012";
		
		// Call all versions of Task()
		
		// Create a default Task without specifying parameters
		Task aTask = new Task();
		System.out.println(aTask.toString());
		
		// Create a task specifying all possible parameters
		Task bTask = new Task(taskDesc, taskDueDate, taskPriority, taskReminderDate);
		System.out.println(bTask.toString());
	}

	// Implement the following static method "static String getElement(String values, int index)" 
	// 	1. 	If the index is not a valid array index, return null - In the future we'll try to avoid
	// 		this, but we currently don't have error handling in our toolbox 
	// 	2. 	Otherwise, return the array element at the index

	static String getElement(String[] values, int index) {

		if (values.length == 0 || index < 0 || index > (values.length - 1)) {
			System.out.println("Invalid index");
			return null;
		}

		return values[index];
	}

} // End of Assignment class

 // Implement a class named Task inside the Assignment.java file 
 // 	1. 	Do not create a new file. Place the Task class immediately after the end 
 // 		of the Assignment class in Assignment.java 
 // 	2. 	Do not implement a main method in Task. 
 // 	3. 	Add the instance variables you think belong in a Task object 
 // 	4. 	Implement at least one parameterized constructor for the Task class 
 // 	5. 	Implement a no-argument constructor for the Task class 
 // 	6. 	Implement a main() method in the Assignment class that does the following: 
 // 		1.	Call every constructor implemented in Task at least once, 
 // 			assigning the resulting object to a variable 
 // 		2. 	Pass each Task object created to "System.out.println(aTask);"

class Task {

	private String taskDesc = " "; 			// Task description
	private String dueDate = " "; 			// Task due date
	private int taskPriority = 0; 			// Priority of the task - default is 0
	private boolean isComplete = false; 	// Task complete|incomplete
	private String reminderDate = " "; 		// Task due date

	// Accessor methods for Task (get)
	String getTaskDesc() 	{ return taskDesc; }
	String getDueDate() 	{ return dueDate; }
	int getTaskPriority() 	{ return taskPriority; }
	boolean getIsComplete() { return isComplete; }
	String getReminderDate() { return reminderDate; }

	// Accessor methods for Task (set)
	void setTaskDesc(String tDesc) 	{ taskDesc = tDesc; }
	void setDueDate(String tDate) 	{ dueDate = tDate; }
	void setTaskPriority(int tPri) 	{ taskPriority = tPri; }
	void setIsComplete(boolean tIsC) { isComplete = tIsC; }
	void setReminderDate(String tRem) { reminderDate = tRem; }
	
	// Parameterized constructor
	Task (String description, String inDate, int priority, String remDate) {

		// Set the description
		setTaskDesc(description);  // Probably should be paranoid and limit description length
		
		// Validate inDate
		if (isValidDate(inDate)) {
			setDueDate(inDate);
		}
		// If inDate is invalid, default to current date
		else {
			setDueDate( getCurrentDate() ); // Today's date as a string
		}

		// Set the task priority
		setTaskPriority(priority);

		// If a reminder date was specified, validate it
		if (remDate != null) {
			// Validate remDate
			if (isValidDate(remDate)) {
				setReminderDate(remDate);
			}
			// If reminderDate is invalid, default to event date
			else {
				setReminderDate(dueDate);
			}
		}
	}

	// Constructor with no arguments - init with defaults
	Task() {
		setTaskDesc("Generic task"); 	// Default task description
		setDueDate( getCurrentDate() ); // Today's date as a string
		setTaskPriority(3); 			// Default Task priority is 3 (low)
		setIsComplete(false); 			// Task is incomplete
		setReminderDate(dueDate); 		// Default reminder is event date
	}

	// Get current date
	String getCurrentDate() {
        Calendar cal = Calendar.getInstance() ;
        String dateString = cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
        return dateString;
	}
		
	// Validate Date
	boolean isValidDate(String checkDate) {

		boolean valid = false; /* Assume date is invalid */

		// Sanity check the parameter to make sure it's not null */
		if (checkDate != null) {

			// Set the date format
			SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");

			// If checkDate is the right length (according to the format),
			// continue validation
			if (checkDate.trim().length() == format.toPattern().length()) {

				// Insist on strict adherance to date format
				format.setLenient(false); 

				// Try to parse checkDate
				try {
					format.parse(checkDate.trim());
					valid = true;
				}
				// If parse failed, it will be caught here
				catch (ParseException pe) {
					valid = false;
				}
			}
		}
		return valid;
	}	// End isValidDate ()


	// Implement a toString() method in Task:
	// 	1. 	The signature has to be "public String toString()" exactly 
	// 	2. 	Create a String representation of a Task. This does not need to display
	// 		every piece of data in the class, just the core information. "Task(title)" 
	// 		would be a sparse but acceptable implementation.


	public String toString() {		

		String done = "incomplete";
		String remstr = null;

		// Get the completion status and build a string
		if (getIsComplete()) {
			done = "complete";
		}

		// Get the reminder date (if there is one) and build an appropriate string
		String remDate = getReminderDate();
		if (remDate != null) {
			remstr = "A reminder will be sent on: " + remDate;
		}

		String printString = 	"Task: " + getTaskDesc() + 
								" is due: " + getDueDate() +
								". Priority is: " + getTaskPriority() + 
								". This task is " + done + 
								". " + remstr;
		
		return (printString);
	}

} // End of Task Class
