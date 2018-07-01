import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage implements ActionListener {
	
	private JFrame f1;
	private JPanel p1;
	private JLabel l1, l2;
	private JTextField t1, t2;
	private JButton b1,b2;
	
	public void loginForm()
	{
		f1=new JFrame("Login: Java File Security System");
		p1=new JPanel();
		l1=new JLabel("User ID:");
		l2=new JLabel("Password:");
		t1=new JTextField();
		t2=new JTextField();
		b1=new JButton("Login");
		b2=new JButton("Reset");
		
		f1.add(p1);
		p1.add(l1);
		p1.add(l2);
		p1.add(t1);
		p1.add(t2);
		p1.add(b1);
		p1.add(b2);
		
		l1.setBounds(50,25,75,25);
		l2.setBounds(50,75,75,25);
		t1.setBounds(150,25,200,25);
		t2.setBounds(150,75,200,25);
		b1.setBounds(100,125,75,25);
		b2.setBounds(225,125,75,25);
		
		p1.setLayout(null);
		f1.setSize(400, 200);
		f1.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent a) 
	{
		// TODO Auto-generated method stub
		if(a.getSource()==b1)
		{
			String s1=t1.getText();
			String s2=t2.getText();
			if(s1.equals("monalisa") && s2.equals("sonalika"))
			{
				JOptionPane.showMessageDialog(f1, "Logged In Successfully!\nWelcome!");
				f1.setVisible(false);
				EncryptionDecrytion ed=new EncryptionDecrytion();
				ed.encryptDecrypt();
			}
			else
			{
				JOptionPane.showMessageDialog(f1, "Wrong User ID or Password");
			}
		}
		if(a.getSource()==b2)
		{
			t1.setText("");
			t2.setText("");
		}
	}


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		LoginPage lp=new LoginPage();
		lp.loginForm();
	}

	

}
