public class FastConverter{

	// global variables
	static String initialStr;
	static int stringLen;
	static int currentStrPos = 0;

	public static String convertToLatex(String inputStr){
		// setting up global variables
		String outputStr = "";
		initialStr = inputStr;
		stringLen = initialStr.length();

		char currentChar = getNextChar();
		while(currentChar != 0){
			// if no # char detected print as it is
			if(currentChar != '#') outputStr += currentChar;

			// special sequence (possibly) detected
			else {
				// look at next char
				currentChar = getNextChar();

				// check for special text sequence #[ ]
				String textSequence = "";
				if(currentChar == '['){
					currentChar = getNextChar();
					while(currentChar != 0 && currentChar != ']'){
						switch (currentChar){
						case 0:
							// end of string before reaching ]
							// TODO: revisit this and see if program should crash
							break;
						default:
							textSequence += currentChar;
							break;
						}
						currentChar = getNextChar();
					}
					// once closure ']' found print text and go forward
					outputStr = outputStr + "\\text{" + textSequence + "}";
				} 
				// else 
				else {
					switch (currentChar) {
					// single chars
					case 'A':
						outputStr += "\\forall";
						break;
					case 'E':
						outputStr += "\\exists";
						break;
					case '!':
						outputStr += "\\not";
						break;

					// double chars
					case '|':
						currentChar = getNextChar();
						if(currentChar == '='){
							outputStr += "\\models";
							break;
						}
						// else error
					case '=':
						currentChar = getNextChar();
						if(currentChar == '='){
							outputStr += "\\equiv";
							break;
						}
						// else error
					case '-':
						currentChar = getNextChar();
						if(currentChar == '>'){
							outputStr += "\\rightarrow";
							break;
						}
						// else error
					case '<':
						currentChar = getNextChar();
						if(currentChar == '-'){
							currentChar = getNextChar();
							if(currentChar == '>'){
								outputStr += "\\leftrightarrow";
								break;
							} else {
								// TODO: fix
								outputStr = outputStr + "\\leftarrow" + currentChar;
								break;
							}
						}
						// else error

					// words
					case 'o':
						currentChar = getNextChar();
						if(currentChar == 'r'){
							outputStr += "\\vee";
							break;
						}
						// else error
					case 'a':
						currentChar = getNextChar();
						if(currentChar == 'n'){
							currentChar = getNextChar();
							if(currentChar == 'd'){
								outputStr += "\\wedge";
								break;
							}
						}
						// else error
					case 'n':
						currentChar = getNextChar();
						if(currentChar == 'o'){
							currentChar = getNextChar();
							if(currentChar == 't'){
								outputStr += "\\neg";
								break;
							}
						}
						// else error

					default:
						// TODO: make this error part better
						// unrecognized special char, print small Error
						outputStr = outputStr + "UNREC";
						break;
					}
				}
				
			}

			// finally look at next char for next loop
			currentChar = getNextChar();
		}

		return outputStr;

	}

	/**
	 * Function used to get next char in the input string
	 * @return current char, or 0 if end of string
	 */
	private static char getNextChar(){
		char currentChar;
		// when end reached return 0
		if(currentStrPos == stringLen){
			return (char) 0;
		}
		// else
		currentChar = initialStr.charAt(currentStrPos);
		currentStrPos++;
		return currentChar;
	}

}