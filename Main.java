import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener{

	private Timer myTimer;
	
	private MenuPanel menu;
	
	public Main() {
		super("Aknowtate");
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
	
	private Font raleway;
	private JButton photo = new JButton("Upload Photo");
	private JButton analyze = new JButton("Analyze");
	private JLabel title = new JLabel("Aknowtate");
	
	public MenuPanel() {
	
		setLayout(null);
		
		//set up menu
			
		raleway = new Font("Raleway",Font.PLAIN,48);
		//label
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setSize(550,100);
		title.setFont(raleway);
		title.setLocation(new Point(50,15));
		add(title);
		
		raleway = new Font("Raleway",Font.PLAIN,32);
		//photo button
		photo.setSize(550,100);
		photo.setFont(raleway);
		photo.setLocation(new Point(50,70));
		photo.setBackground(new Color(0,0,0,0));
		photo.setBorderPainted(false);
		photo.setFocusPainted(false);
		photo.addMouseListener(this);
		photo.setContentAreaFilled(false);
		photo.setForeground(Color.BLACK);
		add(photo);
		
		
		// analyze button
		analyze.setSize(550,100);
		analyze.setFont(raleway);
		analyze.setLocation(new Point(50,120));
		analyze.setBackground(new Color(0,0,0,0));
		analyze.setBorderPainted(false);
		analyze.setFocusPainted(false);
		analyze.addMouseListener(this);
		analyze.setContentAreaFilled(false);
		analyze.setForeground(Color.BLACK);
		add(analyze);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		
		Color bforeground = new Color(200,0,0);
		
		if(source == analyze) {
			analyze.setForeground(bforeground);
		}
		else if(source == photo) {
			photo.setForeground(bforeground);
		}
		else {
			analyze.setForeground(new Color(0,0,0));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		
		Color bforeground = new Color(0,0,0);
		
		if(source == analyze) {
			analyze.setForeground(bforeground);
		}
		else if(source == photo) {
			photo.setForeground(bforeground);
		}
		else {
			analyze.setForeground(new Color(0,0,0));
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	//Graphics

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(255,255,255));
		g.fillRect(630, 15, 540, 732);
		//border
		g.setColor(Color.BLACK);
		g.drawRect(630, 15, 540, 732);
	}
}
