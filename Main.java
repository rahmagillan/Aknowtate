import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener{

	private Timer myTimer;
	
	private MenuPanel menu;
	
	public Main() {
		super("AnKNOWtate");
		setSize(new Dimension(1200,800));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		///////////////////////////////////////////////
		
		//panels and their buttons
		menu = new MenuPanel();
						
		getContentPane().add(menu);
	
		
		///////////////////////////////////////////////
		setVisible(true);
		myTimer = new Timer(100,this);
		myTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	public static void main(String[] args) {
		Main frame = new Main();
	}
}


class MenuPanel extends JPanel implements MouseListener {
	
	
	private File bookpage;

	private Font raleway;
	private JButton photo = new JButton("Upload");
	private JButton analyze = new JButton("Analyze");
	private JButton clear = new JButton("Clear");
	
	private JLabel title = new JLabel("AnKNOWtate");
	private JLabel def = new JLabel("Definitions ");
	private JLabel compWords = new JLabel();
	private ArrayList<String> text = new ArrayList<String>(); //each element is a line
	
	////without punc and capatalization
	private ArrayList<String> cleanText = new ArrayList<String>();
	
	
	private JLabel page = new JLabel();
	
	//dictionary
	private ArrayList<String> d_lines = new ArrayList<String>();
	private ArrayList<String[]> d_split = new ArrayList<String[]>();
	private HashMap<String,String> DICT = new HashMap<String,String>();
	
	//alliteratoion
	List<String> uselessWords = Arrays.asList("of","with","at","into","including","until","against","among","throughout","despite","towards","upon","to","in","for","on","by","about","though","there","i","a","ill","that","you","and","im","an","the","she","his","her","had","was","as");
	ArrayList<String> alit = new ArrayList<String>();
	ArrayList<int[]> indexes = new ArrayList<int[]>();
	
	public MenuPanel() {
		
		Scanner d = null;
		//dictionary things 
		try {
			d = new Scanner(new File("Dictionary.txt"));
		} catch (FileNotFoundException e) {}
		
		while(d.hasNextLine()) {
			d_lines.add(d.nextLine());
		}
		//remove blank lines
		for (int i = 0; i < d_lines.size(); i++) {
			if (d_lines.get(i).equals("")) {
				d_lines.remove(i);
			}
			/*else {
				String[] spl;
				spl = d_lines.get(i).split(" ");
				if (i<4000)
					System.out.println(Arrays.toString(spl));
				String key = spl[0].toLowerCase();//**********to avoid discrepencies
				//String[] def = new String[spl.length-1];
				String def = "";
				for(int j = 1; j < spl.length-1; j++) {
					def = def + spl[j] + " "; 
					//if(j < 20)
						//System.out.println(key+" "+def);
				}
			
				
				DICT.put(key, def);
	
			}*/
		}
		
		//d_lines.add("Abacus  n. (pl. -cuses) 1 frame with wires along which beads are slid for calculating. 2 archit. Flat slab on top of a capital. [latin from greek from hebrew]");
		//d_lines.add("Abaft  naut. —adv. In the stern half of a ship. —prep. Nearer the stern than. [from *a2, -baft: see *aft]");
		//d_lines.add("Abandoned  adj. 1 deserted, forsaken. 2 unrestrained, profligate.");
		//d_lines.add("Abandon  —v. 1 give up. 2 forsake, desert. 3 (often foll. By to; often refl.) Yield to a passion, another's control, etc. —n. Freedom from inhibitions.  abandonment n. [french: related to *ad-, *ban]");
		
		int a = 0;
		for (int i = 0; i < d_lines.size();i++) {
			for (int j = 0; j < d_lines.get(i).length();j++) {
				//System.out.println("!! "+d_lines.get(i).charAt(j));
				if (d_lines.get(i).substring(j).charAt(j) == ' ') {
					a = j+3;
					//System.out.println("WAF");
					break;
				}
			}
			if (d_lines.get(i).length() > 5)
				DICT.put(d_lines.get(i).substring(0, a).toLowerCase(),d_lines.get(i).substring(a,(d_lines.get(i).length())));
			
		}
		
		//System.out.println(DICT.toString());
		
		//System.out.println(DICT.toString());
		
		//for (int i = 0; i < d_lines.size(); i++) {
			//System.out.println("RAHMA "+d_lines.get(i));
		//}
		
		
		
		setLayout(null);
		
		//set up menu
		setBackground(new Color(230,230,250));	
		
		raleway = new Font("Raleway",Font.PLAIN,48);
		//label
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setSize(550,100);
		title.setFont(raleway);
		title.setLocation(new Point(50,15));
		add(title);
		
		raleway = new Font("Raleway",Font.PLAIN,20);
		//photo button
		photo.setSize(200,60);
		photo.setFont(raleway);
		photo.setLocation(new Point(220,687));
		photo.setBackground(new Color(178,178,240));
		photo.setFocusPainted(false);
		photo.addMouseListener(this);
		photo.setContentAreaFilled(false);
		photo.setForeground(Color.BLACK);
		photo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(photo);
		
		//analyze button
		analyze.setSize(200,60);
		analyze.setFont(raleway);
		analyze.setLocation(new Point(10,687));
		analyze.setBackground(new Color(178,178,240));
		analyze.setFocusPainted(false);
		analyze.addMouseListener(this);
		analyze.setContentAreaFilled(false);
		analyze.setForeground(Color.BLACK);
		analyze.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(analyze);
		
		//clear button
		clear.setSize(200,60);
		clear.setFont(raleway);
		clear.setLocation(new Point(430,687));
		clear.setBackground(new Color(178,178,240));
		clear.setFocusPainted(false);
		clear.addMouseListener(this);
		clear.setContentAreaFilled(false);
		clear.setForeground(Color.BLACK);
		clear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(clear);
		
		
		raleway = new Font("Raleway",Font.PLAIN,30);
		//label
		def.setHorizontalAlignment(SwingConstants.LEFT);
		def.setVerticalAlignment(SwingConstants.TOP);
		def.setSize(550,100);
		def.setFont(raleway);
		def.setLocation(new Point(50,90));
		add(def);
		
		raleway = new Font("Raleway",Font.PLAIN,10);
		///the complicated words
		compWords.setHorizontalAlignment(SwingConstants.LEFT);
		compWords.setVerticalAlignment(SwingConstants.TOP);
		compWords.setSize(550,550);
		compWords.setFont(raleway);
		compWords.setLocation(new Point(50,135));
		add(compWords);
		
		raleway = new Font("Raleway",Font.PLAIN,16);
		page.setVerticalAlignment(SwingConstants.TOP);//the page's label 639, 15, 535, 732
		page.setSize(new Dimension(531,728));
		page.setLocation(new Point(641,17));;
		page.setFont(raleway);
		add(page);
	
		//TESTING THIS STUPID STUPIDpage.setText("<html><font color=red>RED</font><br><font color=black>I WANT TO QUIT THIS IS TOO AH</font></html>");
	
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source == photo) {
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "TXT File", "txt");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(getParent());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		   	   bookpage = new File(chooser.getSelectedFile().getAbsolutePath());
			
		   	   if (bookpage != null) {
			    	Scanner s = null;
					try {
						s = new Scanner(bookpage);
					} catch (FileNotFoundException e1) {}
			    	while (s.hasNextLine()) {
			    		text.add(s.nextLine());
			    	}
			    	compWords.setText("");
			    	page.setText("<html>");//clear page in case the user did not clear
			    	for (int i = 0; i < text.size(); i++) {
			    		//System.out.println(text.get(i));
			    		
			    		page.setText(page.getText()+text.get(i)+"<br>"); ///////////new linedoes not work
			    	}
			    	page.setText(page.getText()+"</html>");
			    	
			    	clean();
			    	compWords.setText("<html>");	
			    	
			    	ArrayList<String> dwords = new ArrayList<String>();
			    	
			    	for (int i = 0; i < cleanText.size();i++) {
			    		if (cleanText.get(i).length() >= 7) { //HOW TO DFINE COMP WORDS LOL
			    			if (define(cleanText.get(i)) != null) {
			    				dwords.add(cleanText.get(i));
			    				compWords.setText(compWords.getText()+define(cleanText.get(i))+"<br>");
			    			}
			    			//System.out.println(define(cleanText.get(i)));
			    		}
			    	}
			    	//System.out.println(dwords.toString());/////////////////////////////
			    
			    	compWords.setText(compWords.getText()+"</html>");
			    	
			    	//alliteration
			    	
			    	//more cleaning vaccuuuuuum
			    	for (int i = 0; i < cleanText.size(); i++) {
			    		if (cleanText.get(i).length() == 0) {
			    			cleanText.remove(i);
			    		}
			    	}
			    	
			    	
			    	for(int i = 0;i < cleanText.size();i++){
		    			if(!uselessWords.contains(cleanText.get(i))){
		    				alit.add(cleanText.get(i));
		    			}
			    	}
			    	
			    	int alliterCounter = 0;
			    	for (int i = 0; i < alit.size(); i++){
			    		if(i!=alit.size()-1){
			    			char iWordChar  = alit.get(i).charAt(0);
				    		char i2WordChar = alit.get(i+1).charAt(0);
				    		//System.out.println(iWordChar+" "+i2WordChar);
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
				    					tmp[alliterCounter-j] = cleanText.indexOf(alit.get(i-j+1));
				    				}
				    				indexes.add(tmp);
				    				alliterCounter = 0;
				    			}
				    			
				    		}
				    	}
				    	//System.out.println("Counter: "+alliterCounter);
			    	}
			    	if(alliterCounter!=0){
			    		int[] tmp = new int[alliterCounter];
			    		for(int j = alliterCounter; j>0;j--){
			    			tmp[alliterCounter-j] = cleanText.indexOf(alit.get(alit.size()-j));
			    		}
			    		indexes.add(tmp);
			    		alliterCounter = 0;
			    	}
			    	
			    	//printing
			    	ArrayList<String> awords = new ArrayList<String>();
			    	
			    	for(int i =0;i<indexes.size();i++){
				    	for(int j = 0;j<indexes.get(i).length;j++){
				    		if (awords.size() < i)
				    			awords.set(i, awords.get(i)+cleanText.get(indexes.get(i)[j])+" ");
				    		else 
				    			awords.add(cleanText.get(indexes.get(i)[j])+" ");
				    		//System.out.print(cleanText.get(indexes.get(i)[j])+" ");
				    	}
				    	//System.out.print("\n");
				    }
			    	
			    	//System.out.println("awords: "+awords);
			    	
			    	///////////////////////////reseting texxts things
			    	//page.setText("");
			    	
			    	String[] temp;
			    	String[] ctemp; //cleaned
 			    	
			    	page.setText("<html>");//clear page in case the user did not clear
			    	for (int i = 0; i < text.size(); i++) {
			    		//System.out.println(text.get(i));
			    		
			    		temp = text.get(i).split(" ");
			    		ctemp = text.get(i).replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			    		//page.setText("<html><font color=red>RED</font><br><font color=black>I WANT TO QUIT THIS IS TOO AH</font></html>");
			    		
			    		
			    		for (int j = 0; j < temp.length; j++) {
			    		
			    			//System.out.println(dwords);
			    			
			    			//alliteration
			    			if (awords.contains(ctemp[j]+" ") && dwords.contains(ctemp[j])) {
			    				//System.out.println("WoW");
			    				page.setText(page.getText()+"<font color=blue>"+temp[j].charAt(0)+"</font>"+"<font color=red>"+temp[j].substring(1,temp[j].length())+"</font>"+" ");
			    			}
			    			//dictionary
			    			//System.out.println(Arrays.toString(temp));
			    			//System.out.println(Arrays.toString(ctemp));
			    			else if (dwords.contains(ctemp[j])) {
			    				page.setText(page.getText()+"<font color=red>"+temp[j]+"</font>"+" ");
			    			}
			    			
			    			else if (awords.contains(ctemp[j]+" ")) { //il y a a random space so im bash fixing
			    				//System.out.println("AMAZING");
			    				page.setText(page.getText()+"<font color=blue>"+temp[j].charAt(0)+"</font>"+"<font color=black>"+temp[j].substring(1,temp[j].length())+"</font>"+" ");
			    			}
			    			
			    			else {
			    				page.setText(page.getText()+"<font color=black>"+temp[j]+"</font>"+" ");
			    			}
			    			
			    		}
			    		
			    		//page.setText(page.getText()+text.get(i)+"<br>"); ///////////new linedoes not work
			    		page.setText(page.getText()+"<br>");
			    		
			    
			    	}
			    	page.setText(page.getText()+"</html>");
			    	
			    	
		   	   }
		   
		    }
		}
		if (source == clear) {
			page.setText("");
			compWords.setText("");
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		
		Color bforeground = new Color(127,127,230);
		
		if(source == analyze) {
			analyze.setForeground(bforeground);
			analyze.setBorder(BorderFactory.createLineBorder(bforeground));
		}
		else if(source == photo) {
			photo.setForeground(bforeground);
			photo.setBorder(BorderFactory.createLineBorder(bforeground));
		}
		else if(source == clear) {
			clear.setForeground(bforeground);
			clear.setBorder(BorderFactory.createLineBorder(bforeground));
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		
		Color bforeground = new Color(0,0,0);
		
		if(source == analyze) {
			analyze.setForeground(bforeground);
			analyze.setBorder(BorderFactory.createLineBorder(bforeground));
		}
		else if(source == photo) {
			photo.setForeground(bforeground);
			photo.setBorder(BorderFactory.createLineBorder(bforeground));
		}
		else if(source == clear) {
			clear.setForeground(bforeground);
			clear.setBorder(BorderFactory.createLineBorder(bforeground));
		}
	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	///////////////////////////////////////////////////////////
	
	public String define(String w) {
		if (DICT.containsKey(w)) {	
			//System.out.println(w + "	" + DICT.get(w));
			return " -"+w+ "- " + DICT.get(w);
		}
		else {
			//System.out.println("Word Cannot Be Defined");
			return null;
		}
	}
	
	public void clean() {
		//clean text
		String[] tA;
		for (int i = 0; i < text.size(); i++) {
			tA = text.get(i).replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			for (int j = 0; j < tA.length; j++) {
				cleanText.add(tA[j]);
			}
			
		}
		
		//System.out.println(cleanText.toString());
	}
	
	//Graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(255,255,255));
		g.fillRect(639, 15, 535, 732);
		//border
		g.setColor(Color.BLACK);
		g.drawRect(639, 15, 535, 732);
		//
		//define("wolves");
		//define("unchain");
		
	}
	
}
