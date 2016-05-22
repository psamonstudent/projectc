import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class Trace {
	
	private String traceFile = "/root/git/projectc/ProjCTrace.txt";
	private PrintWriter traceWriter;
	private String className;
	private String methodName;
	
	public Trace(){
		
		try{
			
			traceWriter = new PrintWriter(new FileOutputStream(traceFile));
			
		} catch(FileNotFoundException e) {
			
			//TODO: exception handling statements
			System.exit(0);
			
		}
		
	}
	
	
	public PrintWriter getTraceWriter() {
		return traceWriter;
	}




	public void setTraceWriter(PrintWriter traceWriter) {
		this.traceWriter = traceWriter;
	}

	

	
	public void traceToFile(String className, String methodName, int lineNumber){
		
		traceWriter.flush();
		
		this.className = className;
		this.methodName = methodName;
		
		traceWriter.println("Trace: " + className + ": " + methodName + ": " + lineNumber + ":");
		
		traceWriter.flush();
		
	}
	
	public void info(String text){
		
		traceWriter.println(text);
		
		traceWriter.flush();
		
	}
	
	public void info(String text, Object obj){
		
		traceWriter.print("		" + text + obj.toString() + "\n");
		
		traceWriter.flush();
		
	}
	
}
