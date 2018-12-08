import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class dictionary {
    
    
    
    public static void main(String[] args) throws IOException{
    	
    	Scanner dict=new Scanner(new File("dictionary.txt"));
    	
    	HashMap<String, String> map=new HashMap<String, String>();
    		
    	while (dict.hasNextLine()){
    		String name=dict.nextLine();
    		String[] key=name.split("  ");
    		System.out.println(name);
    		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    		System.out.println(key[0]);
    		//map.put(key, name);
    		try{
    			dict.nextLine();
    		}
    		catch (Exception e){
    			System.out.println("End of Dictionary");
    		}
    		
    	}
    	//String[] content=total.split("+");
    	dict.close();
    	//System.out.println(content[0]);
    	//System.out.println(total);
    	
    }
}
