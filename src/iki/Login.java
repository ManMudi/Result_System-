package iki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

public class Login extends JFrame {

	 private static final long serialVersionUID = 1L;
	protected static final String String = null;
	    private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
     private Connection con;
     private PreparedStatement ps;
	    private ResultSet rs;
	    private static JLabel date;
		private static JLabel empty;
		private static JLabel time;
		private static JLabel kappa;
		private static JLabel hash;
		private JTextField t1;
		public String username="";
		public String name="";
		public String first="";
		public String last="";
		public String subject="";
		public String level="";
		public String somo="";
	
	
	
		
	public Login() {
		
	
		
		super("Result System");
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {

		}
		
		connect();
		 Calendar timer=Calendar.getInstance();
		 timer.getTime();
		 SimpleDateFormat dt=new SimpleDateFormat("MMMM");
		
		
		JLabel n=new JLabel("STUDENT'S RESULT MANAGEMENT SYSTEM");n.setFont(new Font("Segoe UI",+Font.BOLD,28));
		n.setForeground(new Color(128,128,0));
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		p7=new JPanel();
		p8=new JPanel();
		p9=new JPanel();
		p10=new JPanel();
	
		
		p1.setLayout(new BorderLayout());
		add(p1);
		p1.add(p2, BorderLayout.NORTH);
		p2.add(n);
		p1.add(p3, BorderLayout.CENTER);
		Icon log1=new ImageIcon(this.getClass().getResource("/log1.png"));
	//	Icon log2=new ImageIcon(this.getClass().getResource("/logo2.png"));
		Icon ok=new ImageIcon(this.getClass().getResource("/ok.png"));
		
		p3.setLayout(new BorderLayout());p5.setBackground(Color.BLUE);
		
		p3.add(p4, BorderLayout.WEST);
		p3.add(p5, BorderLayout.CENTER);
	//	p3.add(p10, BorderLayout.EAST);
		
		JLabel lll=new JLabel();lll.setIcon(log1);
		p4.add(lll);
		
		//JLabel ll2=new JLabel();ll2.setIcon(log2);
		//p10.add(ll2);
		
		p5.setLayout(new BorderLayout());
		p5.add(p6, BorderLayout.NORTH);
		p5.add(p7, BorderLayout.CENTER);p8.setBackground(new Color(255,228,196));
		p9.setBackground(new Color(255,250,250));
		
		p7.setLayout(new BorderLayout());
		p7.add(p8, BorderLayout.NORTH);
		p7.add(p9, BorderLayout.CENTER);
		JLabel n1=new JLabel("ADMINISTRATOR LOGIN FORM");n1.setFont(new Font("Segoe Script",Font.BOLD,18));
		n1.setForeground(new Color(0,0,0));
		p8.add(n1);	
		
		p9.setLayout(new GridBagLayout());
		JLabel l1=new JLabel("User Name");l1.setFont(new Font("Courier",Font.BOLD,17));l1.setForeground(new Color(0,0,0));
		t1=new JTextField(10);t1.setToolTipText("Enter User Name");
		
		JLabel l2=new JLabel("Password");l2.setFont(new Font("Courier",Font.BOLD,17));l2.setForeground(new Color(0,0,0));
		final JPasswordField t2=new JPasswordField(10);t2.setToolTipText("Enter Password");
		
		JButton b=new JButton("Login");b.setIcon(ok);

		GridBagConstraints c12=new GridBagConstraints();
		c12.fill=GridBagConstraints.HORIZONTAL;
		c12.weightx=1;c12.weighty=0.2;
		c12.insets=new Insets(30,30,0,10);
		c12.gridx=1;c12.gridy=1;
		p9.add(l1, c12);
		c12.gridx=2;c12.gridy=1;
		p9.add(t1, c12);
		
		c12.insets=new Insets(0,30,0,10);
		c12.gridx=1;c12.gridy=2;
		p9.add(l2, c12);
		c12.gridx=2;c12.gridy=2;
		p9.add(t2, c12);
		
		c12.insets=new Insets(0,30,10,10);
		c12.gridx=2;c12.gridy=3;
		p9.add(b, c12);
		
		
		Border p7in=BorderFactory.createEmptyBorder(5,5,5,5);
		Border p7ou=BorderFactory.createTitledBorder("");
		p7.setBorder(BorderFactory.createCompoundBorder(p7in, p7ou));
		//p4.setBorder(BorderFactory.createCompoundBorder(p7in, p7ou));
		
	
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
			
					try {

						
						String p1=t1.getText();	
						@SuppressWarnings("deprecation")
						String p2=t2.getText();
						
					
						
				    	     String sql2 ="select count(Username) from Account where Password = '"+p2+"' and Username='"+p1+"'  ";
					    	 ps=con.prepareStatement(sql2);
					    	 rs=ps.executeQuery();
					    	 
					    	
					    	 int count=Integer.parseInt(rs.getString("count(Username)"));
					    	
					    	 
							if(count>0 )
							{
								
								
								dispose();
							
						    	
						    	 String sql="select * from Account  where Username ='"+t1.getText()+"' ";
						    	 ps=con.prepareStatement(sql);
						    	 rs=ps.executeQuery();
						    	 subject= rs.getString("Level");
						    	 first= rs.getString("FirstName");
						    	 last= rs.getString("LastName");
						    	 level= rs.getString("Level");
						    	 somo= rs.getString("Subject");
						    	
						    	 name=level.toLowerCase()+" "+first+"  "+last;
						    	 
 
                                  if(subject.equals("ADMIN")) {
                                
                                    Admin m=new Admin(name,subject,somo);
      								m.setSize(1366,800);
      								m.setVisible(true);
      								m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      								m.setResizable(false);
      								m.setIconImage(Toolkit.getDefaultToolkit().getImage(m.getClass().getResource("/lo.png")));
                                    con.close();
      								

                                  }else if(subject.equals("TEACHER")) {
                                	  
                                	Other oth=new Other(name,subject,somo);
                              		oth.setSize(1366,800);
                              		oth.setVisible(true);
                              		oth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                              		oth.setResizable(false);
                              		oth.setIconImage(Toolkit.getDefaultToolkit().getImage(oth.getClass().getResource("/lo.png")));
                              		con.close(); 
                                	  
                                  }
                                  
                                  else if(subject.equals("ACADEMIC")) {
                                	  
                                  	    Academic oth=new Academic(name,subject,somo);
                                		oth.setSize(1366,800);
                                		oth.setVisible(true);
                                		oth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                		oth.setResizable(false);
                                		oth.setIconImage(Toolkit.getDefaultToolkit().getImage(oth.getClass().getResource("/lo.png")));
                                		con.close(); 
                                  	  
                                    }
						    	
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect Username or Password.!   Try Again.....");
								t1.setText("");
							  t2.setText("");
							
							}
				    	  
				    	
				    	
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}  finally {
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                if (ps != null) {
			                    ps.close();
			                    
			                } 
			                } catch (Exception ex) {
			                // do something
			                }
			            }
				}

		
			});
		
		//setUsername("Mudi"); 
		
		t2.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("static-access")
			public void keyReleased(KeyEvent e)	{
				
				if(e.getKeyCode()==e.VK_ENTER){
					
					
					try {

						
						String p1=t1.getText();	
						@SuppressWarnings("deprecation")
						String p2=t2.getText();
						
					
						
				    	     String sql2 ="select count(Username) from Account where Password = '"+p2+"' and Username='"+p1+"'  ";
					    	 ps=con.prepareStatement(sql2);
					    	 rs=ps.executeQuery();
					    	 int count=Integer.parseInt(rs.getString("count(Username)"));
					    	
					    	 
							if(count>0 )
							{
								
								
								dispose();
							
						    	
						    	 String sql="select * from Account  where Username ='"+t1.getText()+"' ";
						    	 ps=con.prepareStatement(sql);
						    	 rs=ps.executeQuery();
						    	 subject= rs.getString("Level");
						    	 first= rs.getString("FirstName");
						    	 last= rs.getString("LastName");
						    	 level= rs.getString("Level");
						    	 somo= rs.getString("Subject");
						    	
						    	 name=level.toLowerCase()+" "+first+"  "+last;
						    	 
 
                                  if(subject.equals("ADMIN")) {
                                
                                    Admin m=new Admin(name,subject,somo);
      								m.setSize(1366,800);
      								m.setVisible(true);
      								m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      								m.setResizable(false);
      								m.setIconImage(Toolkit.getDefaultToolkit().getImage(m.getClass().getResource("/lo.png")));
                                    con.close();
      								

                                  }else if(subject.equals("TEACHER")) {
                                	  
                                	Other oth=new Other(name,subject,somo);
                              		oth.setSize(1366,800);
                              		oth.setVisible(true);
                              		oth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                              		oth.setResizable(false);
                              		oth.setIconImage(Toolkit.getDefaultToolkit().getImage(oth.getClass().getResource("/lo.png")));
                              		con.close(); 
                                	  
                                  }
                                  
                                  else if(subject.equals("ACADEMIC")) {
                                	  
                                  	    Academic oth=new Academic(name,subject,somo);
                                		oth.setSize(1366,800);
                                		oth.setVisible(true);
                                		oth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                		oth.setResizable(false);
                                		oth.setIconImage(Toolkit.getDefaultToolkit().getImage(oth.getClass().getResource("/lo.png")));
                                		con.close(); 
                                  	  
                                    }
						    	
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect Username or Password.!   Try Again.....");
								t1.setText("");
							  t2.setText("");
							
							}
				    	  
				    	
				    	
					} catch (SQLException ez) {
						System.out.print(ez.getMessage());
					}   finally {
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                if (ps != null) {
			                    ps.close();
			                }
			                } catch (Exception ex) {
			                // do something
			                }
			            }
				}
				
			}});
		
		t1.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("static-access")
			public void keyReleased(KeyEvent e)	{
				
				if(e.getKeyCode()==e.VK_ENTER){
				
					try {

						
						String p1=t1.getText();	
						@SuppressWarnings("deprecation")
						String p2=t2.getText();
						
					
						
				    	     String sql2 ="select count(Username) from Account where Password = '"+p2+"' and Username='"+p1+"'  ";
					    	 ps=con.prepareStatement(sql2);
					    	 rs=ps.executeQuery();
					    	 int count=Integer.parseInt(rs.getString("count(Username)"));
					    	
					    	 
							if(count>0 )
							{
								
								
								dispose();
								
						    	
						    	 String sql="select * from Account  where Username ='"+t1.getText()+"' ";
						    	 ps=con.prepareStatement(sql);
						    	 rs=ps.executeQuery();
						    	 subject= rs.getString("Level");
						    	 first= rs.getString("FirstName");
						    	 last= rs.getString("LastName");
						    	 level= rs.getString("Level");
						    	 somo= rs.getString("Subject");
						    	 
						    	 name=level.toLowerCase()+" "+first+"  "+last;
						    	 

                                  if(subject.equals("ADMIN")) {
                                	  
                                    Admin m=new Admin(name,subject,somo);
      								m.setSize(1366,800);
      								m.setVisible(true);
      								m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      								m.setResizable(false);
      								m.setIconImage(Toolkit.getDefaultToolkit().getImage(m.getClass().getResource("/lo.png")));
      							    con.close();
                                	  
                                  }else {
                                	  
                                	Other oth=new Other(name,subject,somo);
                              		oth.setSize(1366,800);
                              		oth.setVisible(true);
                              		oth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                              		oth.setResizable(false);
                              		oth.setIconImage(Toolkit.getDefaultToolkit().getImage(oth.getClass().getResource("/lo.png")));
                              	    con.close();
                                	  
                                  }
						    	
								
								
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect Username or Password.!   Try Again.....");
								t1.setText("");
							  t2.setText("");
							
							}
				    	  
				    	
				    	
					} catch (SQLException ez) {
						System.out.print(ez.getMessage());
					} finally {
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                if (ps != null) {
			                    ps.close();
			                }
			                } catch (Exception ex) {
			                // do something
			                }
			            }
				}
				
			}});		
		
		bar();
	//	connect();
	
		
	}
	
	
	
	private void connect() {
		 try {
	            
	            String url = "jdbc:sqlite:Zeus.sqlite";
	            con = DriverManager.getConnection(url);
	            
	        //    System.out.println("Connection to SQLite has been established.");
	            
	        } catch (SQLException e) {
	           // System.out.println(e.getMessage());
	        } 
	}



	private void bar() {



		final Icon alarm=new ImageIcon(this.getClass().getResource("/alarm.png"));
		final Icon cale=new ImageIcon(this.getClass().getResource("/cale.png"));
		Icon ex=new ImageIcon(this.getClass().getResource("/exit.png"));
		Icon ab=new ImageIcon(this.getClass().getResource("/ab.png"));
		JMenuBar bar=new JMenuBar();
		setJMenuBar(bar);

		JMenu file=new JMenu("File");
		bar.add(file);
		final JMenuItem exit=new JMenuItem("Exit");
		
		exit.setIcon(ex);

		
		file.add(exit);
		JMenu help=new JMenu("Help");
		bar.add(help);
		JMenuItem about=new JMenuItem("About System");
		about.setIcon(ab);
		about.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				Icon at=new ImageIcon(this.getClass().getResource("/z.png"));
				   JOptionPane.showMessageDialog(null,  "\tSTUDENT’S RESULT MANAGEMENT SYSTEM\n**************************************************\nThis Software was Designed and Created by\n MOHAMED YUSUPH.This is not a free Software \nand you are not allowed to re-distribute it without\n the prior permission of the developer\n  \nDeveloper’s Contacts:\n************************\nPhone +255 778 939 544 / +255 675 785 592\nEmail: myusuph2@gmail.com\n \nCOPYRIGHT RESERVED ©2018-2019", "About Software", 0, at);
				
			}});
		
		
		help.add(about);


		hash=new JLabel("            " );
		bar.add(hash);
		//bar.add(sign);
		empty=new JLabel("                                   ");
		kappa=new JLabel("                                  ");
		bar.add(empty);

	
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			int mudi=JOptionPane.showConfirmDialog(exit,"Are you sure you want to Exit ?","Message", JOptionPane.YES_NO_OPTION);
			if(mudi==JOptionPane.YES_OPTION){
				System.exit(0);
			}}});
		
		
		//empty=new JLabel("                                                                   ");
		time=new JLabel();time.setForeground(Color.BLACK);time.setFont(new Font("serif",Font.BOLD+Font.PLAIN,12));
		date=new JLabel();date.setForeground(Color.BLACK);date.setFont(new Font("serif",Font.BOLD+Font.PLAIN,12));
		bar.add(kappa);
		bar.add(date);
		bar.add(empty);
		bar.add(time);

	      Thread th=new Thread(){
	      public void run(){
	    	  for(;;){
	    		  Calendar timer=Calendar.getInstance();
	    		  timer.getTime();
	    		  SimpleDateFormat df= new SimpleDateFormat("hh:mm:ss a");
	    		  time.setText(df.format(timer.getTime()));time.setIcon(alarm);

	    		  SimpleDateFormat dt=new SimpleDateFormat("E dd MMM ,  yyyy");
	    		  date.setText(dt.format(timer.getTime()));date.setIcon(cale);


	    		try {
					sleep(1000);
				} catch (InterruptedException e) {

				}
	    	  }
	      }};

	      th.start();
		
		
	}

}
