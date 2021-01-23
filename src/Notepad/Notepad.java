package Notepad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Notepad extends JFrame implements ActionListener
{
	JFrame f;
	JMenuBar mb;
	JMenu filemenu,editmenu,colormenu,helpmenu;
	JMenuItem newitem,openitem,saveitem,printitem,exititem,cutitem,copyitem,pasteitem,selectAllitem,fcolor,bcolor;
	JMenuItem helpitem, aboutitem;
	JTextArea jt;
	JScrollPane jp;
	public Notepad() {
		// TODO Auto-generated constructor stub
		f = new JFrame();
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1000, 1000);
		initialize();
		
	}
	public void initialize()
	{
		mb = new JMenuBar();
		
		filemenu = new JMenu("File");
		editmenu = new JMenu("Edit");
		colormenu = new JMenu("Format");
		helpmenu = new JMenu("help");
		
		
		newitem = new JMenuItem("New");
		newitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newitem.addActionListener(this);
		
		openitem = new JMenuItem("Open");
		openitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openitem.addActionListener(this);
		
		saveitem = new JMenuItem("Save");
		saveitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		saveitem.addActionListener(this);
		
		printitem = new JMenuItem("print");
		printitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		printitem.addActionListener(this);
		
		exititem = new JMenuItem("Exit");
		exititem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		exititem.addActionListener(this);
		
		
		filemenu.add(newitem);
		filemenu.addSeparator();
		filemenu.add(openitem);
		filemenu.addSeparator();
		filemenu.add(saveitem);
		filemenu.addSeparator();
		filemenu.add(exititem);
		
		cutitem=new JMenuItem("Cut");
		cutitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		cutitem.addActionListener(this);
		
		copyitem=new JMenuItem("Copy");
		copyitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		copyitem.addActionListener(this);
		
		pasteitem=new JMenuItem("Paste");
		pasteitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		pasteitem.addActionListener(this);
		
		selectAllitem= new JMenuItem("Select All");
		selectAllitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		selectAllitem.addActionListener(this);
		
		editmenu.add(cutitem);
		editmenu.addSeparator();
		editmenu.add(copyitem);
		editmenu.addSeparator();
		editmenu.add(pasteitem);
		editmenu.addSeparator();
		editmenu.add(selectAllitem);
		
		fcolor=new JMenuItem("ForeColor");
		fcolor.addActionListener(this);
		
		bcolor=new JMenuItem("BackColor");
		bcolor.addActionListener(this);
		
		
		colormenu.add(fcolor);
		colormenu.add(bcolor);

		aboutitem=new JMenuItem("About");
		aboutitem.addActionListener(this);

		helpmenu.add(aboutitem);
		
		
		mb.add(filemenu);
		mb.add(editmenu);
		mb.add(colormenu);
		mb.add(helpmenu);
		
		
		jt = new JTextArea();
		jt.setBounds(10, 10, 1340, 700);
		jt.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
		jt.setLineWrap(true);
		jt.setWrapStyleWord(true);
		jp = new JScrollPane(jt);
		jp.setBounds(10, 10, 1340, 700);
		f.add(jp);
		f.setJMenuBar(mb);
		f.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New"))
		{
			//jt.setText("");
			if(!jt.getText().equals(""))
			{
				int n1=JOptionPane.showConfirmDialog(Notepad.this, "Do you want to save changes to untitled?");
				//code to save
				if(n1==0)
				{
					JFileChooser jf=new JFileChooser(".");
					int n=jf.showSaveDialog(Notepad.this);
					if(n==0)
					{
						saveFile(jf);
						jt.setText("");
					}
				}
				
				//code for don't save
				else if(n1==1)
				{
					jt.setText("");
				}			
			}
		}
		else if(e.getActionCommand().equals("Open"))
		{
			if(!jt.getText().equals(""))
			{
				int n1=JOptionPane.showConfirmDialog(Notepad.this, "Do you want to save changes to untitled?");
				//code to save
				if(n1==0)
				{
					JFileChooser jf=new JFileChooser(".");
					int n=jf.showSaveDialog(Notepad.this);
					if(n==0)
					{
						saveFile(jf);
						openFile();
					}
				}
				else if (n1==1)
				{
					openFile();
				}
			}
			else
				openFile();				
		}
		else if(e.getActionCommand().equals("Save"))
		{
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			int action = saveas.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File filename = new File(saveas.getSelectedFile() + ".txt");
			BufferedWriter outfile = null;
			try {
				outfile = new BufferedWriter(new FileWriter(filename));
				jt.write(outfile);
			}
			catch(Exception e1){}
		}
		else if(e.getActionCommand().equals("Print"))
		{
			try {
				jt.print();
			}
			catch(Exception e1){}
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Cut"))
		{
			jt.cut();
		}
		else if(e.getActionCommand().equals("Copy"))
		{
			jt.copy();
		}
		else if(e.getActionCommand().equals("Paste"))
		{
			jt.paste();
		}
		else if(e.getActionCommand().equals("Select All")) 
		{
			jt.selectAll();
		}
		else if(e.getActionCommand().equals("ForeColor"))
		{
			Color c=JColorChooser.showDialog(this,"Select fore Color ", Color.ORANGE);
			jt.setForeground(c);
		}
		else if(e.getActionCommand().equals("BackColor"))
		{

			Color c=JColorChooser.showDialog(this,"Choose",Color.CYAN);  
		    jt.setBackground(c);
		}
		else if(e.getActionCommand().equals("About"))
		{
			AboutDialog a = new AboutDialog();
		}
		
	}
	private void openFile() {
		// TODO Auto-generated method stub
		JFileChooser j=new JFileChooser(".");
		int val=j.showOpenDialog(Notepad.this);
		if(val==0)
		{
			File f=j.getSelectedFile();
			StringBuffer str=new  StringBuffer();
			String s;
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f.getAbsolutePath()));
				while((s=br.readLine())!=null)
				{
					str.append(s);
					str.append("\n");
				}
				jt.setText(str.toString());
				br.close();
			} catch ( IOException e2) {
				
				e2.printStackTrace();
			}

			Notepad.this.setTitle(f.getName());			

		}
		
	}
	private void saveFile(JFileChooser jf) {
		// TODO Auto-generated method stub
		File f=jf.getSelectedFile();
		try {
			
			//to perform write operation on file
			FileOutputStream fout=new FileOutputStream(f);
			
			//read from textarea					
			String text=jt.getText();
			
			//write that text in file
			fout.write(text.getBytes());

			} catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		
	}
	public static void main(String[] args) {
		new Notepad();
	}
}
