
public class MyScanner {
	
	private static final int INITIALIZE_TO_ZERO = 0;
	
	private String line;
	private int numberOfWords;
	private String[] wordArray = new String[4];
	private Trace trace;


	public MyScanner(String line, Trace trace){
		
		
		this.line = line;	
		this.trace = trace;
		
		trace.traceToFile(Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		
		numberOfWords = INITIALIZE_TO_ZERO;
		int charIndex = INITIALIZE_TO_ZERO;
		char[] charArray = new char[line.length()];
		
		for(int index = 0; index < line.length(); index++){
			
			if(line.charAt(index) == '\n'){
				
				break;
			
			} if(line.charAt(index) == ' ' || line.charAt(index) == ','){
				
				char[] word = new char[charIndex];
				for(int ind = 0; ind < charIndex; ind++){
					
					word[ind] = charArray[ind];
					
				}
				wordArray[numberOfWords] = new String(word);

				numberOfWords++;
				
				for(int ind = INITIALIZE_TO_ZERO; ind < charArray.length; ind++){
				
					charArray[ind] = '\0';

				}
				charIndex = INITIALIZE_TO_ZERO;
				
			} else {
				
				
				charArray[charIndex] = line.charAt(index);
				charIndex++;
			}
			
			
		}
		
		char[] word = new char[charIndex];
		for(int ind = 0; ind < charIndex; ind++){
			
			word[ind] = charArray[ind];
			
		}
		
		wordArray[numberOfWords] = new String(word);	
		numberOfWords++;
		
	}
	
	
	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public int getNumberOfWords() {
		return numberOfWords;
	}


	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}


	public String[] getWordArray() {
		return wordArray;
	}


	public void setWordArray(String[] wordArray) {
		this.wordArray = wordArray;
	}

}
