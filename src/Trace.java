import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class Trace {
	
	String traceFile = "ProjCTrace";
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




	public void trace(Thread thread, String info){
		
		String className = thread.getStackTrace()[1].getClassName();
		String methodName = thread.getStackTrace()[1].getMethodName();
		int lineNumber = thread.getStackTrace()[1].getLineNumber();
		
		if(info == null){
			
			traceWriter.println("Trace: " + className + ":" + methodName + ":" + lineNumber + ".");
			return;
			
		}
		
		traceWriter.println("Trace: " + className + ":" + methodName + ":" + lineNumber + ":" + info + ".");
		
	}
	
}
