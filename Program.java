public class Program {
	
	// currently input and output are given through the terminal only
	public static void main(String[] args) {
		String inputStr;
		String outputStr;

		handleArgs(args);
		// if program didn't exit in previous statement, then uses args
		inputStr = args[0]; 
		outputStr = FastConverter.convertToLatex(inputStr);

		// TODO: save program in file
		System.out.println(outputStr);
	}

	/**
	 * Function takes the argument of args from terminal and checks
	 * if the input is valid or if it's asking for help
	 * <p>
	 * If program has invalid input it will throw an IOError
	 * @param args
	*/
	private static void handleArgs(String[] args){
		String argsInput;
		int argsLen = args.length;
		
		// invalid number of arguments
		if(argsLen == 0 || argsLen > 1){
			System.out.println("Invalid number of arguments: " + argsLen + 
			"\nFor more informations on how to use the program use -help");
			System.exit(0);
		}

		argsInput = args[0];

		// print help prompt
		if(argsInput.equals("-help")){
			// TODO: always update this part
			System.out.println("[Fast maths/logics to LaTeX converter] \n\n" +
			"This program takes one string as an argument containing the math expression " +
			"to be converted using the following special symbols preceded by the # character:\n" +
			"- #A : for each");
			System.exit(0);
		}


	}

}
