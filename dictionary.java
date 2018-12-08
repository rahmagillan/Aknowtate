import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class dictionary {
    
    
    
    public static void main(String[] args) throws IOException{
    	
    	Scanner dict=new Scanner(new File("dict2.txt"));
    	
    	String[] chars={"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "|", "[", "]", ";", ":", "'", ",", "<", ".", ">", "/", "?", " "};
    	String[] spcChars={};
    	ArrayList<String> str=new ArrayList<String>();
    	boolean yes=false;
    	
    	HashMap<String, String> map=new HashMap<String, String>();
	
		while (dict.hasNextLine()){
			int n=0;
    		String name=dict.nextLine();
    		for (int i=0; i<name.length(); i++){
    			for (String j:chars){
					if (name.substring(i).equals(j)){
						System.out.println("");
						yes=false;
					}else{
						String newName=name.substring(0,i)+""+name.substring(i);
						str.add(newName);
						yes=true;
    				}
    			}
    		}
    		
    		String[] key=name.split("  ");
    		System.out.println(key[0]);
    		System.out.println(name);
    		/*if (yes){
    			System.out.println("si");
    			System.out.println(str.get(n));
    		}else{
    			System.out.println("non");
    			System.out.println(name);
    		}*/
    		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    		
    		
    		/*for (int i = 0; i < str.get(0).length(); i++){
    			if (str.get(0).charAt(i) == 'ï' || str.get(0).charAt(i) == '»' || str.get(0).charAt(i) == '¿') {
    				System.out.print("");
    			}
    			else{
    				System.out.print(str.get(0).charAt(i));
    			}
    		}*/
    		
    		//map.put(key, name);
    		n+=1;
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
