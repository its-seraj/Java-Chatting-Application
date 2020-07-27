import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {

	JPanel p1;	
	JTextField t1;
	static JTextArea a1;
	String cut;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	Client(){
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7, 84, 95));
		p1.setBounds(0, 0, 400, 40);
		add(p1);
		
		//Back Arrow .png
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back-arrow.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(8, 8, 25, 25);
		p1.add(l1);	
		
		l1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					System.exit(0);
				}
		});
		
		
		//User1 .png
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/user3.png"));
		Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40, 0, 40, 40);
		p1.add(l2);	
		
		//Name of user
		JLabel l3 = new JLabel("Donald J. Trump");
		l3.setBounds(84, 2, 100, 20);
		l3.setFont(new Font("SAN_SERIF", Font.BOLD, 12));
		l3.setForeground(Color.WHITE);
		p1.add(l3);
		
		//Active status
		JLabel l4 = new JLabel("Active Now");
		l4.setBounds(88, 22, 100, 10);
		l4.setFont(new Font("SAN_SERIF", Font.BOLD, 8));
		l4.setForeground(Color.WHITE);
		p1.add(l4);
		
		//video call .png
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l5 = new JLabel(i9);
		l5.setBounds(260, 5, 30, 30);
		p1.add(l5);	
		
		//voice call .png
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/call.png"));
		Image i11 = i10.getImage().getScaledInstance(16, 20, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l6 = new JLabel(i12);
		l6.setBounds(300, 5, 30, 30);
		p1.add(l6);	
		
		//Triple Dot .png
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/more.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 20, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l7 = new JLabel(i15);
		l7.setBounds(340, 5, 30, 30);
		p1.add(l7);	
		
		//TextField for typing message
		t1 = new JTextField();
		t1.setBounds(10, 560, 340, 30);
		t1.setFont(new Font("Serif", Font.BOLD, 14));
		add(t1);
		
		//send .png
		ImageIcon i16 = new ImageIcon(ClassLoader.getSystemResource("icons/send.png"));
		Image i17 = i16.getImage().getScaledInstance(50, 44, Image.SCALE_DEFAULT);
		ImageIcon i18 = new ImageIcon(i17);
		JLabel l8 = new JLabel(i18);
		l8.setBounds(350, 552, 50, 44);
		add(l8);	
		
		l8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				cut = t1.getText();
				a1.setText(a1.getText() + "\n\t\t\t\t" + cut);
				t1.setText("");
				
				try {
					dout.writeUTF(cut);
				}
				catch(Exception e) {
					//blank
				}
			}
		});
		
		//message Body -> Text Area
		a1 = new JTextArea();
		a1.setBounds(3, 42, 394, 510);
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		a1.setFont(new Font("Serif", Font.PLAIN, 12));
		add(a1);
		
		
		
		
		
		setLayout(null);
		setLocation(700, 100);
		setSize(400, 600);
		setUndecorated(true);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		
	}
	
	public static void main(String[] args) {
		new Client();
		
		//.net programs
		String msgin = "";

		try {
			s = new Socket("127.0.0.1", 6008);
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(true) {
				msgin = din.readUTF();
				a1.setText(a1.getText() + "\n" + msgin);
			}
				
			//s.close();
					
		}
		catch(Exception e) {
			//blank
		}
		
	}
}
