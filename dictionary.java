import java.util.*;
import java.io.*;

public class dictionary {
    
    public static void main(String[] args) throws IOException{
    	Scanner dict=new Scanner(new File("dictionary.txt"));
    	String total="";
    	while (dict.hasNextLine()){
    		String name=dict.nextLine();
    		total+=" +";
    		total+=name;
    	}
    	
    	String[] content=total.split("+");
    	dict.close();
    	System.out.println(content[0]);
    	//System.out.println(total);
    	
    }
}
