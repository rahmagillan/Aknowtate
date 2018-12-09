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
import java.util.Scanner;

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
	private JButton photo = new JButton("Upload Photo");
	private JButton analyze = new JButton("Analyze");
	private JButton clear = new JButton("Clear");
	
	private JLabel title = new JLabel("AnKNOWtate");
	private JLabel def = new JLabel("Definition: ");
	private ArrayList<String> text = new ArrayList<String>(); //each element is a line
	
	private JLabel page = new JLabel();
	
	public MenuPanel() {
	
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
		
		
		raleway = new Font("Raleway",Font.PLAIN,36);
		//label
		def.setHorizontalAlignment(SwingConstants.LEFT);
		def.setVerticalAlignment(SwingConstants.TOP);
		def.setSize(550,100);
		def.setFont(raleway);
		def.setLocation(new Point(50,90));
		add(def);
		
		
		raleway = new Font("Raleway",Font.PLAIN,12);
		page.setVerticalAlignment(SwingConstants.TOP);//the page's label 639, 15, 535, 732
		
		page.setSize(new Dimension(535,732));
		page.setLocation(new Point(639,15));;
		page.setFont(raleway);
		add(page);
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
			    	page.setText("");//clear page in case the user did not clear
			    	for (int i = 0; i < text.size(); i++) {
			    		//System.out.println(text.get(i));
			    		
			    		page.setText(page.getText()+text.get(i)+"\n"); ///////////new linedoes not work
			    	}
		   	   }
		    }
		}
		if (source == clear) {
			page.setText("");
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
	
	//Graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(255,255,255));
		g.fillRect(639, 15, 535, 732);
		//border
		g.setColor(Color.BLACK);
		g.drawRect(639, 15, 535, 732);
	
			
	}
	
}
