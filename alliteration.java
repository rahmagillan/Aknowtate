import java.util.*;
import java.io.*;

public class alliteration {

    public static void main(String[] args) throws IOException{
    	List<String> uselessWords = Arrays.asList("of","with","at","into","including","until","against","among","throughout","despite","towards","upon","to","in","for","on","by","about","though","there","i","a","ill","that","you","and","im");
	//	try{
	    	Scanner reader = new Scanner(new File("txt.txt"));
	    	ArrayList<String> rawText = new ArrayList<String>();
	    	ArrayList<String> importantWords = new ArrayList<String>();
	    	ArrayList<int[]> indexes = new ArrayList<int[]>();
	    	while(reader.hasNextLine()){
	    		String[] tmp = (reader.nextLine().toLowerCase()).split(" ");
	    		
	    		for(int i=0;i<tmp.length;i++){
	    			rawText.add(tmp[i]);
	    			if(!uselessWords.contains(tmp[i])){
	    				importantWords.add(tmp[i]);
	    			}
		    	}
	    	}
	    	System.out.println("Raw Text: "+rawText);
	    	System.out.println("Important WOrds: "+importantWords);
	    	
	    	int alliterCounter = 0;
	    	for (int i = 0; i < importantWords.size(); i++){
	    		if(i!=importantWords.size()-1){
	    			char iWordChar  = importantWords.get(i).charAt(0);
		    		char i2WordChar = importantWords.get(i+1).charAt(0);
		    		System.out.println(iWordChar+" "+i2WordChar);
		    		if(iWordChar==i2WordChar){
		    			if(alliterCounter==0){
		    				alliterCounter=2;
		    			}
		    			else{
		    				alliterCounter++;
		    			}
		    		}
		    		else{
		    			if(alliterCounter!=0){
		    				int[] tmp = new int[alliterCounter];
		    				for(int j = alliterCounter; j>0;j--){
		    					tmp[alliterCounter-j] = rawText.indexOf(importantWords.get(i-j+1));
		    				}
		    				indexes.add(tmp);
		    				alliterCounter = 0;
		    			}
		    			
		    		}
		    	}
		    	System.out.println("Counter: "+alliterCounter);
	    	}
	    	if(alliterCounter!=0){
	    		int[] tmp = new int[alliterCounter];
	    		for(int j = alliterCounter; j>0;j--){
	    			tmp[alliterCounter-j] = rawText.indexOf(importantWords.get(importantWords.size()-j));
	    		}
	    		indexes.add(tmp);
	    		alliterCounter = 0;
	    	}
		    //System.out.println("Important words: "+importantWords);
		    for(int i =0;i<indexes.size();i++){
		    	for(int j = 0;j<indexes.get(i).length;j++){
		    		System.out.print(rawText.get(indexes.get(i)[j])+" ");
		    	}
		    	System.out.print("\n");
		    }
	/*	}
		catch (Exception e){
			System.out.println(e);
		}*/
    }
}