import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class Trace {
	
	String traceFile = "/root/git/projectc/ProjCTrace.txt";
	PrintWriter traceWriter;
	
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

	
	public void traceToFile(String className, String methodName, int lineNumber, String info){
		
		if(info == null){
			
			traceWriter.println("Trace: " + className + ":" + methodName + ":" + lineNumber + ".");
			return;
			
		}
		
		traceWriter.println("Trace: " + className + ":" + methodName + ":" + lineNumber + ":" + info + ".");
		
	}
	
}
