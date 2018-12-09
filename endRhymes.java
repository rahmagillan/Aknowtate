import java.util.*;
import java.io.*;

public class endRhymes {

    public static void main(String[] args) throws IOException{
    	List<String> puncuation = Arrays.asList("`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "|", "[", "]", ";", ":", "'", ",", "<", ".", ">", "/", "?");
    	ArrayList<int[]> indexes = new ArrayList<int[]>();
    	List<String> vowels = Arrays.asList("a","e","i","o","u","y");
    	ArrayList<String> endWords = new ArrayList<String>();
	//	try{
	    	Scanner reader = new Scanner(new File("txt.txt"));
	    	//need puncuation to be taken out
	    	int index = 0;
	    	while(reader.hasNextLine()){
	    		String[] tmp = (reader.nextLine().toLowerCase()).split(" ");
	    		String lastWord = tmp[tmp.length-1];
	    		int counter = 0;
	    		while(true){
	    			if(counter==lastWord.length()){
	    				break;
	    			}
	    			if(vowels.contains(lastWord.substring(counter,counter+1))){
	    				break;
	    			}
	    			counter++;
	    		}
	    		endWords.add(lastWord.substring(counter,lastWord.length()));
	    		System.out.println(lastWord.substring(counter,lastWord.length()));
	    		if(endWords.size()>1){
	    			if(endWords.get(index).equals(endWords.get(index-1))){
	    				int[] tm = {index-1,index};
	    				indexes.add(tm);
	    			}
	    		}
	    		if(endWords.size()>2){
	    			if(endWords.get(index).equals(endWords.get(index-2))){
	    				int[] tm = {index-2,index};
	    				indexes.add(tm);
	    			}
	    		}
	    		index++;
	    	}
	    	/*for(int i=0; i<indexes.size();i++){
	    		System.out.println(indexes.get(i)[0]);
	    		System.out.println(indexes.get(i)[1]);
	    		for(int j=0;j<indexes.get(i).length;i++){
	    			System.out.print(indexes.get(i)[j]+" ");
	    		}
	    		System.out.println("");*/
	    	}
	    	
	/*	}
		catch (Exception e){
			System.out.println(e);
		}*/
    }
}