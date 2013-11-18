package Backend;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Log {
	static PrintWriter writer;
	
	
	public static void createLogFile(String name){
		try {
			writer = new PrintWriter("logs/" + name + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void writeln(String s){
		writer.println(s);
	}
	
	public static void write(String s){
		writer.print(s);
	}
	
	public static void close(){
		writer.close();
	}
	
	
}