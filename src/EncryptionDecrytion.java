import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EncryptionDecrytion implements ActionListener{
	
	private JFrame f1;
	private JPanel p1,p2;
	private JTabbedPane tp1;
	private JLabel l1, l2, l3, l4, l5;
	private JTextField t1, t2, t3;
	private JButton b1,b2,b3,b4,b5,b6;
	
	public void encryptDecrypt()
	{
		f1=new JFrame("AES File Encryption/Decryption");
		p1=new JPanel();
		p2=new JPanel();
		tp1=new JTabbedPane();
		f1.add(p1);
		f1.add(p2);
		f1.add(tp1);
		
		//panel p1 - encrypt
		l1=new JLabel("Select a file to be Encrypted");
		l2=new JLabel("File:");
		t1=new JTextField();
		b1=new JButton("Browse");
		b2=new JButton("Encrypt");
		b3=new JButton("Reset");
		tp1.addTab("Encrypt", p1);
		p1.add(l1);
		p1.add(l2);
		p1.add(t1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		l1.setBounds(110,20,200,25);
		l2.setBounds(25,60,75,25);
		t1.setBounds(70,60,200,25);
		b1.setBounds(290,60,78,25);
		b2.setBounds(85,115,77,25);
		b3.setBounds(200,115,75,25);
		
		//panel p2 - decrypt
		l3=new JLabel("Select a file for decryption");
		l4=new JLabel("File");
		l5=new JLabel("Key");
		t2=new JTextField();
		t3=new JTextField();
		b4=new JButton("Browse");
		b5=new JButton("Decrypt");
		b6=new JButton("Reset");
		tp1.addTab("Decrypt", p2);
		p2.add(l3);
		p2.add(l4);
		p2.add(l5);
		p2.add(t2);
		p2.add(t3);
		p2.add(b4);
		p2.add(b5);
		p2.add(b6);
		l3.setBounds(120,10,150,25);
		l4.setBounds(25,35,75,25);
		l5.setBounds(25,70,75,25);
		t2.setBounds(70,35,210,25);
		t3.setBounds(70,70,210,25);
		b4.setBounds(290,35,78,25);
		b5.setBounds(85,115,80,25);
		b6.setBounds(210,115,75,25);
		
		
		p1.setLayout(null);
		p2.setLayout(null);
		f1.setSize(400, 225);
		f1.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent a) 
	{
		//for panel p1 - encrypt
		if(a.getSource()==b1)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    t1.setText(selectedFile.getAbsolutePath());
			}
		}
		if(a.getSource()==b2)
		{
			//encryption code
			String inputFileName=t1.getText();
			File inputFile = new File(inputFileName);
			int index1 = inputFileName.lastIndexOf("\\");
			int index2 = inputFileName.lastIndexOf(".");
			String extension=inputFileName.substring(index2+1);
			String onlyEncryptedFileName = inputFileName.substring(index1 + 1,index2)+"_"+extension+".encrypted";
			String absolutEncryptedFileName = "D:\\xyz\\"+onlyEncryptedFileName;
			File encryptedFile = new File(absolutEncryptedFileName);
			String onlyFileName=inputFileName.substring(index1 + 1,index2);
			String key=Integer.toString(onlyFileName.hashCode())+"123456789";
			key=key.substring(0, 16);
			
			try 
			{
				EcryptionCode.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
					if(encryptedFile.exists())
					{
						//deleting the input file
						inputFile.delete();
						System.out.println("Success");
						String displayText="Encryption key for the file is:\n"+key+"\nPlease note it down!";
						JOptionPane.showMessageDialog(f1, displayText);
					}
			}
			catch (Exception ex) 
			{
			     System.out.println(ex.getMessage());
		             ex.printStackTrace();
		             JOptionPane.showMessageDialog(f1, "File could not be encrypted!\nEnter a valid file path!");
			
			}
		}
		if(a.getSource()==b3)
		{
			t1.setText("");
		}
		
		//for panel p2 - decrypt
		if(a.getSource()==b4)
		{
			JFileChooser fileChooser = new JFileChooser();
			File currDir=new File("D:\\xyz");
			fileChooser.setCurrentDirectory(currDir);
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    t2.setText(selectedFile.getAbsolutePath());
			}
		}
		if(a.getSource()==b5)
		{
			//decryption code
			//choosing location to store decrypted file
			JOptionPane.showMessageDialog(f1, "Select a location to store\ndecryted file.");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(null);
			String key=t3.getText();
			if (result == JFileChooser.APPROVE_OPTION) 
			{
			    File location = fileChooser.getSelectedFile();
			    
			    String absoluteEncryptedFileName=t2.getText();
				File encryptedFile = new File(absoluteEncryptedFileName);
				int index1=absoluteEncryptedFileName.lastIndexOf("\\");
				int index2=absoluteEncryptedFileName.lastIndexOf("_");
				int index3=absoluteEncryptedFileName.lastIndexOf(".");
				String extension=absoluteEncryptedFileName.substring(index2+1, index3);
				String onlyDecryptedFileName=absoluteEncryptedFileName.substring(index1+1,index2)+"."+extension;
				String absoluteDecryptedFileName=location.getAbsolutePath()+"\\"+onlyDecryptedFileName;
				//key=key+Integer.toString(onlyDecryptedFileName.hashCode())+"123456789";
				//key=key.substring(0, 16);
				
				File decryptedFile = new File(absoluteDecryptedFileName);
				try {
					EcryptionCode.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
				     System.out.println("Success");
				     if(decryptedFile.exists())
				     {
				    	 JOptionPane.showMessageDialog(f1, "File decrypted Sucessfully!");
				 
				     }
				     else
				     {
				    	 JOptionPane.showMessageDialog(f1, "File could not be decrypted!\nEnter valid a key!");
				    	 t2.setText("");
				    	 t3.setText("");
				     }
				}catch (Exception ex) {
				     System.out.println(ex.getMessage());
			             ex.printStackTrace();
			             
				 }
			}
			
			
		}
		if(a.getSource()==b6)
		{
			t2.setText("");
			t3.setText("");
		}
	}
}
