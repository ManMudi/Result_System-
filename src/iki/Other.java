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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border3D;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import net.proteanit.sql.DbUtils;

public class Other  extends JFrame{
	
	
	private static JLabel date;
	private static JLabel empty;
	private static JLabel time;  
	private static JLabel kappa;
	private static JLabel hash;
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,pa8;
	private JTabbedPane tab;
	private JLabel l1,l2,l3,l4,l5,label40,label41,label42,label43;;
	@SuppressWarnings("rawtypes")
	private JComboBox box1,box2,box3,box4,box5,box40,box41,box42,box43;
	private JButton b1,b2,b3,b4;
	private String[]name4={"FORM I","FORM II","FORM III","FORM IV"};
    private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
    private SimpleDateFormat yy;
    private  Calendar timer;
    private JTable table;
    private JScrollPane pane;
    private ResultSet rs1,rs2,rs3;
	
	
	public Other(String name,String subject,String somo) {
		
		
		super("Result System      |       welcome : :   "+name);
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

		 table = new JTable() {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return column == 2;
			    }
			};

			pane=new JScrollPane(table);
			
		 
		
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
		pa8=new JPanel();

		
		Border in=BorderFactory.createEmptyBorder(2,5,2,5);//p6.setBackground(Color.BLACK);
		Border ou=BorderFactory.createTitledBorder("");
		
		p2.setBorder(BorderFactory.createCompoundBorder(in, ou));
		p3.setBorder(BorderFactory.createCompoundBorder(in, ou));
		
		
		tab=new JTabbedPane();add(tab);
		tab.addTab("Update Marks",null, p1,"Student Information");
		
		p1.setLayout(new BorderLayout());
		p1.add(p2,BorderLayout.NORTH);
		p1.add(p3,BorderLayout.CENTER);
		
		
		l1=new JLabel("Class");l1.setFont(new Font("georgia",Font.BOLD,13));l1.setForeground(new Color(46,139,87));
		box1=new JComboBox<String>(name4);box1.setMaximumRowCount(10);box1.setToolTipText("Select Class Level");
		
		String sub=somo;
		String[] subname = {sub};
		l2=new JLabel("Subject");l2.setFont(new Font("georgia",Font.BOLD,13));l2.setForeground(new Color(46,139,87));
		box2=new JComboBox<String>(subname);box2.setMaximumRowCount(10);box2.setToolTipText("Select Subject");
		
		l3=new JLabel("Exam");l3.setFont(new Font("georgia",Font.BOLD,13));l3.setForeground(new Color(46,139,87));
		box3=new JComboBox<String>();box3.setMaximumRowCount(10);box3.setToolTipText("Select Exam");
		
		l4=new JLabel("Term");l4.setFont(new Font("georgia",Font.BOLD,13));l4.setForeground(new Color(46,139,87));
		box4=new JComboBox<String>();box4.setMaximumRowCount(10);box4.setToolTipText("Select Term");
		
		ArrayList<String> years_tmp = new ArrayList<String>();
		for(int years=2021; years<=Calendar.getInstance().get(Calendar.YEAR); years++) {
		    years_tmp.add(years+"");
		}

		 timer=Calendar.getInstance();
		 timer.getTime();
		 yy=new SimpleDateFormat("yyyy");
		
		l5=new JLabel("Year");l5.setFont(new Font("georgia",Font.BOLD,13));l5.setForeground(new Color(46,139,87));
		box5=new JComboBox(years_tmp.toArray());box5.setMaximumRowCount(10);box5.setToolTipText("Select Year");
		box5.setSelectedItem(yy.format(timer.getTime()));
		
		b1=new JButton("Save Marks");b1.setFont(new Font("verdana",Font.BOLD,12));b1.setBackground(new Color(0,100,0));b1.setForeground(Color.white);
		b2=new JButton("Refresh");b2.setFont(new Font("verdana",Font.BOLD,12));b2.setBackground(new Color(0,0,128));b2.setForeground(Color.white);
		b3=new JButton("Delete Marks");b3.setFont(new Font("verdana",Font.BOLD,12));b3.setBackground(new Color(255,0,0));b3.setForeground(Color.white);
		b4=new JButton("Upload Marks");b4.setFont(new Font("verdana",Font.BOLD,12));b4.setBackground(new Color(0,0,0));b4.setForeground(Color.white);

		
		

		label40=new JLabel("Class");label40.setFont(new Font("georgia",Font.BOLD,13));label40.setForeground(new Color(46,139,87));
		box40=new JComboBox<String>(name4);box40.setMaximumRowCount(10);box40.setToolTipText("Select Class Level");
		label41=new JLabel("Exam");label41.setFont(new Font("georgia",Font.BOLD,13));label41.setForeground(new Color(46,139,87));
		box41=new JComboBox();box41.setMaximumRowCount(10);box41.setToolTipText("Select Exam");
		label42=new JLabel("Term");label42.setFont(new Font("georgia",Font.BOLD,13));label42.setForeground(new Color(46,139,87));
		box42=new JComboBox();box42.setMaximumRowCount(10);box42.setToolTipText("Select Term");
		label43=new JLabel("Year");label43.setFont(new Font("georgia",Font.BOLD,13));label43.setForeground(new Color(46,139,87));
		box43=new JComboBox(years_tmp.toArray());box43.setMaximumRowCount(10);box43.setToolTipText("Select Year");
		box43.setSelectedItem(yy.format(timer.getTime()));

		pa8.setLayout(new GridBagLayout());
		GridBagConstraints ccna=new GridBagConstraints();
		ccna.fill=GridBagConstraints.HORIZONTAL;
		ccna.weightx=1;ccna.weighty=1;
		ccna.insets=new Insets(2,2,2,3);

		ccna.gridx=1;ccna.gridy=1;
		ccna.anchor=GridBagConstraints.LINE_END;
		pa8.add(label40,ccna);
		ccna.gridx=1;ccna.gridy=2;
		ccna.anchor=GridBagConstraints.LINE_START;
		pa8.add(box40,ccna);
		
		ccna.gridx=2;ccna.gridy=1;
		ccna.anchor=GridBagConstraints.LINE_END;
		pa8.add(label41,ccna);
		ccna.gridx=2;ccna.gridy=2;
		ccna.anchor=GridBagConstraints.LINE_START;
		pa8.add(box41,ccna);
		
		ccna.gridx=3;ccna.gridy=1;
		ccna.anchor=GridBagConstraints.LINE_END;
		pa8.add(label42,ccna);
		ccna.gridx=3;ccna.gridy=2;
		ccna.anchor=GridBagConstraints.LINE_START;
		pa8.add(box42,ccna);
		
		ccna.gridx=4;ccna.gridy=1;
		ccna.anchor=GridBagConstraints.LINE_END;
		pa8.add(label43,ccna);
		ccna.gridx=4;ccna.gridy=2;
		ccna.anchor=GridBagConstraints.LINE_START;
		pa8.add(box43,ccna);

		
		p3.setLayout(new BorderLayout());
		p3.add(pane);
		
		    p2.setLayout(new GridBagLayout());
			GridBagConstraints c44=new GridBagConstraints();
			c44.insets=new Insets(0,5,0,5);
			c44.gridx=1;c44.gridy=1;
			p2.add(l1, c44);
			c44.gridx=2;c44.gridy=1;
			p2.add(box1, c44);
			
			c44.gridx=3;c44.gridy=1;
			p2.add(l2, c44);
			c44.gridx=4;c44.gridy=1;
			p2.add(box2, c44);
			
			c44.gridx=5;c44.gridy=1;
			p2.add(l3, c44);
			c44.gridx=6;c44.gridy=1;
			p2.add(box3, c44);
			
			c44.gridx=7;c44.gridy=1;
			p2.add(l4, c44);
			c44.gridx=8;c44.gridy=1;
			p2.add(box4, c44);
			
			c44.gridx=9;c44.gridy=1;
			p2.add(l5, c44);
			c44.gridx=10;c44.gridy=1;
			p2.add(box5, c44);
			
			c44.gridx=11;c44.gridy=1;
			p2.add(b1, c44);
			c44.gridx=12;c44.gridy=1;
			p2.add(b2, c44);
			c44.gridx=13;c44.gridy=1;
			p2.add(b4, c44);
			
	
	bar(name);
	combo();
	table();
	button();
	listener();
	
	
	
	}

	private void connect() {



		 try {
	            
	            String url = "jdbc:sqlite:Zeus.sqlite";
	            con = DriverManager.getConnection(url);
	            
	          //  System.out.println("Connection to SQLite has been established...");
	            
	        } catch (SQLException e) {
	         //   System.out.println(e.getMessage());
	        } 
	
	
	
		
	}

	private void listener() {



		box1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					

					String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql1);
				    rs1=ps.executeQuery();
					
					String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql2);
					rs2=ps.executeQuery();
					
					String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql3);
					rs3=ps.executeQuery();
					
					String sql="Select Ouser.id as 'STUDENT ID',(    Ouser.fname||'   '||mname||'   '||lname) as 'FULL NAME',mark as 'MARKS' from Ouser  LEFT JOIN Mark_O ON  Ouser.id=Mark_O.id AND Mark_O.sid='"+rs1.getString("sid")+"' AND  Mark_O.year='"+box5.getSelectedItem().toString()+"' AND Mark_O.eid='"+rs2.getString("eid")+"' AND  Mark_O.tid='"+rs3.getString("tid")+"' WHERE  Ouser.class='"+box1.getSelectedItem().toString()+"'  ORDER BY gender ASC,fname ASC,lname ASC  ";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setFont(new Font("serif",Font.PLAIN,11));
					table.setForeground(new Color(0,0,139));
					
					table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					TableColumnModel colModel=table.getColumnModel();
					colModel.getColumn(0).setPreferredWidth(25); 
					colModel.getColumn(1).setPreferredWidth(400); 
					colModel.getColumn(2).setPreferredWidth(400);
					
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment( JLabel.CENTER );
					
					DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
					rRenderer.setHorizontalAlignment( JLabel.LEFT );
					
					table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
					table.getColumnModel().getColumn(1).setCellRenderer(rRenderer);
					table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
					
					((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
			        .setHorizontalAlignment(JLabel.CENTER);
					
					
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getStackTrace());
				}
				finally{
	 	             try{
	 	            	 rs.close();
	  	                 rs1.close();
	  	                 rs2.close();
	  	                 rs3.close();
	  	                 ps.close();
	  	                 ps.close();
	  	             }catch(Exception ex){
	  	                 ex.getMessage();
	  	             }}
				
				
			}
		});
		
		
		
		box2.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {
				try {
					if (event.getStateChange() != ItemEvent.SELECTED) {
						if(box2.getSelectedItem()!=null) {
							
	                        String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sql1);
						    rs1=ps.executeQuery();
							
							String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sql2);
							rs2=ps.executeQuery();
							
							String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sql3);
							rs3=ps.executeQuery();
							
							String sql="Select Ouser.id as 'STUDENT ID',(    Ouser.fname||'   '||mname||'   '||lname) as 'FULL NAME',mark as 'MARKS' from Ouser  LEFT JOIN Mark_O ON  Ouser.id=Mark_O.id AND Mark_O.sid='"+rs1.getString("sid")+"' AND  Mark_O.year='"+box5.getSelectedItem().toString()+"' AND Mark_O.eid='"+rs2.getString("eid")+"' AND  Mark_O.tid='"+rs3.getString("tid")+"' WHERE  Ouser.class='"+box1.getSelectedItem().toString()+"'  ORDER BY gender ASC,fname ASC,lname ASC  ";
							ps=con.prepareStatement(sql);
							rs=ps.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							table.setFont(new Font("serif",Font.PLAIN,11));
							table.setForeground(new Color(0,0,139));
							
							table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
							TableColumnModel colModel=table.getColumnModel();
							colModel.getColumn(0).setPreferredWidth(25); 
							colModel.getColumn(1).setPreferredWidth(400); 
							colModel.getColumn(2).setPreferredWidth(400);
							
							DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
							centerRenderer.setHorizontalAlignment( JLabel.CENTER );
							
							DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
							rRenderer.setHorizontalAlignment( JLabel.LEFT );
							
							table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
							table.getColumnModel().getColumn(1).setCellRenderer(rRenderer);
							table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
							
							((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
					        .setHorizontalAlignment(JLabel.CENTER);
							
							
						
						
						
						
						}
						//JOptionPane.showMessageDialog(null, box2.getSelectedItem());
						
					}else {
						
						//JOptionPane.showMessageDialog(null, box2.getSelectedItem());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
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
		});
		

		
		box3.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {
				try {
					if (event.getStateChange() != ItemEvent.SELECTED) {
						if(box3.getSelectedItem()!=null) {
							

							try {
								

								String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
								ps=con.prepareStatement(sql1);
							    rs1=ps.executeQuery();
								
								String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
								ps=con.prepareStatement(sql2);
								rs2=ps.executeQuery();
								
								String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
								ps=con.prepareStatement(sql3);
								rs3=ps.executeQuery();
								
								String sql="Select Ouser.id as 'STUDENT ID',(    Ouser.fname||'   '||mname||'   '||lname) as 'FULL NAME',mark as 'MARKS' from Ouser  LEFT JOIN Mark_O ON  Ouser.id=Mark_O.id AND Mark_O.sid='"+rs1.getString("sid")+"' AND  Mark_O.year='"+box5.getSelectedItem().toString()+"' AND Mark_O.eid='"+rs2.getString("eid")+"' AND  Mark_O.tid='"+rs3.getString("tid")+"' WHERE  Ouser.class='"+box1.getSelectedItem().toString()+"'  ORDER BY gender ASC,fname ASC,lname ASC  ";
								ps=con.prepareStatement(sql);
								rs=ps.executeQuery();
								table.setModel(DbUtils.resultSetToTableModel(rs));
								table.setFont(new Font("serif",Font.PLAIN,11));
								table.setForeground(new Color(0,0,139));
								
								table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
								TableColumnModel colModel=table.getColumnModel();
								colModel.getColumn(0).setPreferredWidth(25); 
								colModel.getColumn(1).setPreferredWidth(400); 
								colModel.getColumn(2).setPreferredWidth(400);
								
								DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
								centerRenderer.setHorizontalAlignment( JLabel.CENTER );
								
								DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
								rRenderer.setHorizontalAlignment( JLabel.LEFT );
								
								table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
								table.getColumnModel().getColumn(1).setCellRenderer(rRenderer);
								table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
								
								((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
						        .setHorizontalAlignment(JLabel.CENTER);
								
								
								
							}catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex.getStackTrace());
							}finally{
				 	             try{
				  	                 rs.close();
				  	                 rs1.close();
				  	                 rs2.close();
				  	                 rs3.close();
				  	                 ps.close();
				  	                 ps.close();
				  	             }catch(Exception ex){
				  	                 ex.getMessage();
				  	             }}
							
							
							
						
							
						}
						//JOptionPane.showMessageDialog(null, box2.getSelectedItem());
						
					}else {
						
						//JOptionPane.showMessageDialog(null, box2.getSelectedItem());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
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
		});
		
		box4.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {
				try {
					if (event.getStateChange() != ItemEvent.SELECTED) {
						if(box4.getSelectedItem()!=null) {
							

							

							String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sql1);
						    rs1=ps.executeQuery();
							
							String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sql2);
							rs2=ps.executeQuery();
							
							String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sql3);
							rs3=ps.executeQuery();
							
							String sql="Select Ouser.id as 'STUDENT ID',(    Ouser.fname||'   '||mname||'   '||lname) as 'FULL NAME',mark as 'MARKS' from Ouser  LEFT JOIN Mark_O ON  Ouser.id=Mark_O.id AND Mark_O.sid='"+rs1.getString("sid")+"' AND  Mark_O.year='"+box5.getSelectedItem().toString()+"' AND Mark_O.eid='"+rs2.getString("eid")+"' AND  Mark_O.tid='"+rs3.getString("tid")+"' WHERE  Ouser.class='"+box1.getSelectedItem().toString()+"'  ORDER BY gender ASC,fname ASC,lname ASC  ";
							ps=con.prepareStatement(sql);
							rs=ps.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							table.setFont(new Font("serif",Font.PLAIN,11));
							table.setForeground(new Color(0,0,139));
							
							table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
							TableColumnModel colModel=table.getColumnModel();
							colModel.getColumn(0).setPreferredWidth(25); 
							colModel.getColumn(1).setPreferredWidth(400); 
							colModel.getColumn(2).setPreferredWidth(400);
							
							DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
							centerRenderer.setHorizontalAlignment( JLabel.CENTER );
							
							DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
							rRenderer.setHorizontalAlignment( JLabel.LEFT );
							
							table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
							table.getColumnModel().getColumn(1).setCellRenderer(rRenderer);
							table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
							
							((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
					        .setHorizontalAlignment(JLabel.CENTER);
							
							
							
						
							
						}
						//JOptionPane.showMessageDialog(null, box2.getSelectedItem());
						
					}else {
						
						//JOptionPane.showMessageDialog(null, box2.getSelectedItem());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
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
		});
		
		
		box5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					

					String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql1);
				    rs1=ps.executeQuery();
					
					String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql2);
					rs2=ps.executeQuery();
					
					String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql3);
					rs3=ps.executeQuery();
					
					String sql="Select Ouser.id as 'STUDENT ID',(    Ouser.fname||'   '||mname||'   '||lname) as 'FULL NAME',mark as 'MARKS' from Ouser  LEFT JOIN Mark_O ON  Ouser.id=Mark_O.id AND Mark_O.sid='"+rs1.getString("sid")+"' AND  Mark_O.year='"+box5.getSelectedItem().toString()+"' AND Mark_O.eid='"+rs2.getString("eid")+"' AND  Mark_O.tid='"+rs3.getString("tid")+"' WHERE  Ouser.class='"+box1.getSelectedItem().toString()+"'  ORDER BY gender ASC,fname ASC,lname ASC  ";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setFont(new Font("serif",Font.PLAIN,11));
					table.setForeground(new Color(0,0,139));
					
					table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					TableColumnModel colModel=table.getColumnModel();
					colModel.getColumn(0).setPreferredWidth(25); 
					colModel.getColumn(1).setPreferredWidth(400); 
					colModel.getColumn(2).setPreferredWidth(400);
					
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment( JLabel.CENTER );
					
					DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
					rRenderer.setHorizontalAlignment( JLabel.LEFT );
					
					table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
					table.getColumnModel().getColumn(1).setCellRenderer(rRenderer);
					table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
					
					((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
			        .setHorizontalAlignment(JLabel.CENTER);
					
					
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getStackTrace());
				}finally{
	 	             try{
	 	            	 rs.close();
	  	                 rs1.close();
	  	                 rs2.close();
	  	                 rs3.close();
	  	                 ps.close();
	  	                 ps.close();
	  	             }catch(Exception ex){
	  	                 ex.getMessage();
	  	             }}
				
				
				
			}
		});
		
		
		table.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("unused")
			public void keyPressed(KeyEvent e)	{
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					
					if (table.getCellEditor() != null) {
		                DefaultCellEditor cellEditor = (DefaultCellEditor) table.getCellEditor();
		                String value = ((JTextField) cellEditor.getComponent()).getText();
		                
		                if(value.toString().matches("^(?:100(?:\\.00?)?|\\d?\\d(?:\\.\\d\\d?)?)$")) {
		                	
		                	//System.out.println(value);
		                }else if(value.matches("-")) {
		                	
		                	//System.out.println(value);
		                }else if(value==null || value.isEmpty()) {
		                	
		                	JTextField editor = (JTextField) table.getEditorComponent();
		                	editor.setText(null);
		                	
		                }
		                 else {
		                	 
		                	JOptionPane.showMessageDialog(null, "Enter Valid Value !");
		                	JTextField editor = (JTextField) table.getEditorComponent();
		                	editor.setText(null);
		                	
		                }
		            }else {
		            	
		            	int row=table.getSelectedRow();
						int col=2;
						
						Object value=table.getValueAt(row, col);
						
                       if(value==null  || value.toString().isEmpty()) {
                    	   
                    	  // System.out.println(value);
                    	   
                       }else {
                    	   
                    	   String val=value.toString().trim();

                              if(val.matches("^(?:100(?:\\.00?)?|\\d?\\d(?:\\.\\d\\d?)?)$")) {
	
	                            //  System.out.println(value);
                                  }else if(val.matches("-")) {
	
	                             // System.out.println(value);
                                   }else if(val.matches("")) {
                                		
      	                             // System.out.println(value);
                                         }else {
                                	 
                                	  JOptionPane.showMessageDialog(null, "Enter Valid Value !");
                                	 
                                	 
                                           }
                    	   
                       }
                         
                       
                         
						
		            }
					
				}
				
			}
				
			});
		
		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
             String s1="Are you sure you want to Delete the Marks !";

				int mudi=JOptionPane.showConfirmDialog(b3, s1,"Delete", JOptionPane.YES_NO_OPTION);
				if(mudi==JOptionPane.YES_OPTION)
				{
					
					

		             
					try {
					
						String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
						ps=con.prepareStatement(sql1);
						ResultSet rs1=ps.executeQuery();
						
						String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
						ps=con.prepareStatement(sql2);
						ResultSet rs2=ps.executeQuery();
						
						String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
						ps=con.prepareStatement(sql3);
						ResultSet rs3=ps.executeQuery();
						
						String sql4="Select percentage from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
						ps=con.prepareStatement(sql4);
						ResultSet rs4=ps.executeQuery();
						
						int year=Calendar.getInstance().get(Calendar.YEAR);

						 String sqq ="select count(id) from Mark_O where sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+year+"' and class='"+box1.getSelectedItem().toString()+"'  ";
				    	  ps=con.prepareStatement(sqq);
				    	 rs=ps.executeQuery();
				    	 int cou=Integer.parseInt(rs.getString("count(id)"));
				    	 
						if(cou>0) {
							
							String sq1="delete  from Mark_O where sid='"+rs1.getString("sid")+"' AND  eid='"+rs2.getString("eid")+"' AND tid='"+rs3.getString("tid")+"' AND year='"+year+"' AND  class='"+box1.getSelectedItem().toString()+"' ";
							ps=con.prepareStatement(sq1);
							ps.execute();
								
								JOptionPane.showMessageDialog(null,"Marks Deleted Successfully !");
							
							table();
						
							
						
						}else {
							
							JOptionPane.showMessageDialog(null, "No Data Present !");
							
						}
						
						
						
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					finally{
		 	             try{
		  	                 rs.close();
		  	                 ps.close();
		  	             }catch(Exception ex){
		  	                 ex.getMessage();
		  	             }}
					
				
					
					
				}
				
			}
	});
		
		
	}

	private void button() {

		
		
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				try {


					String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql1);
					ResultSet rs1=ps.executeQuery();

					String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql2);
					ResultSet rs2=ps.executeQuery();

					String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql3);
					ResultSet rs3=ps.executeQuery();

					String sql4="Select percentage from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql4);
					ResultSet rs4=ps.executeQuery();

					int year=Calendar.getInstance().get(Calendar.YEAR);

					String sqq ="select count(id) from Mark_O where sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+year+"' and class='"+box1.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sqq);
					rs=ps.executeQuery();
					int cou=Integer.parseInt(rs.getString("count(id)"));

					String sqll="select * from Grade_O";
					ps=con.prepareStatement(sqll);
					ResultSet rs5=ps.executeQuery();
					
					
					
					if(cou>0) {
						
						
						
						
						
					}else {
						
						


						int rows = table.getRowCount();
						for(int row = 0;row <rows; row++) {
							Double g1=0.0,g2=0.0,g3=0.0,g4=0.0,g5=0.0,g6=0.0,g7=0.0,g8=0.0,g9=0.0,g10=0.0;
							g1=rs5.getDouble("g1");g2=rs5.getDouble("g2");g3=rs5.getDouble("g3");g4=rs5.getDouble("g4");g5=rs5.getDouble("g5");
							g6=rs5.getDouble("g6");g7=rs5.getDouble("g7");g8=rs5.getDouble("g8");g9=rs5.getDouble("g9");g10=rs5.getDouble("g10");
							

							String mark="";
							String pmark="";
							String gr="";
							String sid="";
							String eid="";
							String tid="";
							String id="";
							String yr=Integer.toString(year);
							String cla=box1.getSelectedItem().toString();

							
							id = (String) table.getValueAt(row, 0);
							mark = (String) table.getValueAt(row, 2);
							Double per=Double.parseDouble(rs4.getString("percentage"))/100;

							String sql="insert into Mark_O (id,sid,eid,tid,mark,grade,pmark,year,class) values (?,?,?,?,?,?,?,?,?) ";
							ps=con.prepareStatement(sql);
							ps.setString(1, id);
							ps.setString(2, rs1.getString("sid"));
							ps.setString(3, rs2.getString("eid"));
							ps.setString(4, rs3.getString("tid"));




							if(mark==null) {
								ps.setString(5, "");
								ps.setString(6, "");
								ps.setString(7, "");
								ps.setInt(8, year);
								ps.setString(9, box1.getSelectedItem().toString());
								ps.execute();

							}else {

								if(mark.isEmpty()) {

									ps.setString(5, "");
									ps.setString(6, "");
									ps.setString(7, "");
									ps.setInt(8, year);
									ps.setString(9, box1.getSelectedItem().toString());
									ps.execute();


								}else {

									if((mark.matches("^(?:100(?:\\.00?)?|\\d?\\d(?:\\.\\d\\d?)?)$"))) {

										Double mk=Double.parseDouble(mark);
										Double pm=Double.parseDouble(mark)*per;
										String p=pm.toString();

										String marks=String.format("%.2f",Double.parseDouble(mark));
										String pms=String.format("%.2f",Double.parseDouble(p));

										if(mk>=g1 && mk<=g2) {
											gr="A";
										}else if(mk>=g3 && mk<=g4) {
											gr="B";
										}else if(mk>=g5 && mk<=g6) {
											gr="C";
										}else if(mk>=g7 && mk<=g8) {
											gr="D";
										}else if(mk>=g9 && mk<=g10) {
											gr="F";
										}

										ps.setString(5, mark);
										ps.setString(6, gr);	
										ps.setString(7, pms);
										ps.setInt(8, year);
										ps.setString(9, box1.getSelectedItem().toString());
										ps.execute();



									}else if(mark.equals("-")) {

										ps.setString(5, "-");
										ps.setString(6, "-");	
										ps.setString(7, "-");
										ps.setInt(8, year);
										ps.setString(9, box1.getSelectedItem().toString());
										ps.execute();

										JOptionPane.showMessageDialog(null,"Marks Added Successfully !");


									}
									else  {

										JOptionPane.showMessageDialog(null,"Enter Valid Marks ! ");
										table();
									}

								}

							}






						}                 

					


					
					}



				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getStackTrace());

				}  finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (ps != null) {
							ps.close();
						}
					} catch (Exception e) {
						// do something
					}
				}

			}
		});
		
		
		
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {



				
				try {
					

					String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql1);
					ResultSet rs1=ps.executeQuery();

					String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql2);
					ResultSet rs2=ps.executeQuery();

					String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql3);
					ResultSet rs3=ps.executeQuery();

					String sql4="Select percentage from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql4);
					ResultSet rs4=ps.executeQuery();

					int year=Calendar.getInstance().get(Calendar.YEAR);

					String sqq ="select count(id) from Mark_O where sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+year+"' and class='"+box1.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sqq);
					rs=ps.executeQuery();
					int cou=Integer.parseInt(rs.getString("count(id)"));

					String sqll="select * from Grade_O";
					ps=con.prepareStatement(sqll);
					ResultSet rs5=ps.executeQuery();
					
					
					int rows = table.getRowCount();
					for(int row = 0;row <rows; row++) {

						Double g1=0.0,g2=0.0,g3=0.0,g4=0.0,g5=0.0,g6=0.0,g7=0.0,g8=0.0,g9=0.0,g10=0.0;
						g1=rs5.getDouble("g1");g2=rs5.getDouble("g2");g3=rs5.getDouble("g3");g4=rs5.getDouble("g4");g5=rs5.getDouble("g5");
						g6=rs5.getDouble("g6");g7=rs5.getDouble("g7");g8=rs5.getDouble("g8");g9=rs5.getDouble("g9");g10=rs5.getDouble("g10");
						

						String mark="";
						String pmark="";
						String gr="";
						String sid="";
						String eid="";
						String tid="";
						String id="";
						String yr=Integer.toString(year);
						String cla=box1.getSelectedItem().toString();

						
						id = (String) table.getValueAt(row, 0);
						mark = (String) table.getValueAt(row, 2);
						sid=rs1.getString("sid");
						eid=rs2.getString("eid");
						tid=rs3.getString("tid");

						if(mark==null) {
							mark="";
							gr="";
							pmark="";
						}else {

							if(mark.isEmpty()) {

								mark="";
								gr="";
								pmark="";

							}else {

								if((mark.matches("^(?:100(?:\\.00?)?|\\d?\\d(?:\\.\\d\\d?)?)$"))) {

									Double per=Double.parseDouble(rs4.getString("percentage"))/100;
									Double mk=Double.parseDouble(mark);
									Double pm=Double.parseDouble(mark)*per;
									String p=pm.toString();

									// mark=String.format("%.2f",Double.parseDouble(mark));
									mark = (String) table.getValueAt(row, 2);
									pmark=String.format("%.2f",Double.parseDouble(p));



									if(mk>=g1 && mk<=g2) {
										gr="A";
									}else if(mk>=g3 && mk<=g4) {
										gr="B";
									}else if(mk>=g5 && mk<=g6) {
										gr="C";
									}else if(mk>=g7 && mk<=g8) {
										gr="D";
									}else if(mk>=g9 && mk<=g10) {
										gr="F";
									}



								}else if(mark.equals("-")) {

									mark="-";
									gr="-";
									pmark="-";

								}
								else  {

									// JOptionPane.showMessageDialog(null,"Enter Valid Marks from 0 to 100 ");
								}

							}

						}

						

						if(mark.matches("^(?:100(?:\\.00?)?|\\d?\\d(?:\\.\\d\\d?)?)$") || mark.isEmpty() || mark.matches("-") || mark==null) {
							
							String sq="update Mark_O set sid='"+sid+"',eid='"+eid+"',tid='"+tid+"',mark='"+mark+"',grade='"+gr+"',pmark='"+pmark+"',year='"+yr+"',class='"+cla+"'  where id='"+id+"' and sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+year+"' and class='"+box1.getSelectedItem().toString()+"'  ";
							ps=con.prepareStatement(sq);
							ps.addBatch();
							ps.executeBatch();
							
						}else {
							
							JOptionPane.showMessageDialog(null,"Enter Valid Marks !");
							
						}
						
						
						//System.out.println(id+" "+mark+" "+pmark+" "+gr);


					}

				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getStackTrace());
				}  


			
				
			}
			
			
		});

		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                    table();
				
			}
	});
		
	}

	private void table() {
		

		

		try {
			

			String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
			ps=con.prepareStatement(sql1);
		    rs1=ps.executeQuery();
			
			String sql2="Select eid from Exam where ename= '"+box3.getSelectedItem().toString()+"'  ";
			ps=con.prepareStatement(sql2);
			rs2=ps.executeQuery();
			
			String sql3="Select tid from Term where tname= '"+box4.getSelectedItem().toString()+"'  ";
			ps=con.prepareStatement(sql3);
			rs3=ps.executeQuery();
			
			String sql="Select Ouser.id as 'STUDENT ID',(    Ouser.fname||'   '||mname||'   '||lname) as 'FULL NAME',mark as 'MARKS' from Ouser  LEFT JOIN Mark_O ON  Ouser.id=Mark_O.id AND Mark_O.sid='"+rs1.getString("sid")+"' AND  Mark_O.year='"+box5.getSelectedItem().toString()+"' AND Mark_O.eid='"+rs2.getString("eid")+"' AND  Mark_O.tid='"+rs3.getString("tid")+"' WHERE  Ouser.class='"+box1.getSelectedItem().toString()+"'  ORDER BY gender ASC,fname ASC,lname ASC  ";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.setFont(new Font("serif",Font.PLAIN,11));
			table.setForeground(new Color(0,0,100));
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			TableColumnModel colModel=table.getColumnModel();
			colModel.getColumn(0).setPreferredWidth(25); 
			colModel.getColumn(1).setPreferredWidth(400); 
			colModel.getColumn(2).setPreferredWidth(400);
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			
			DefaultTableCellRenderer rRenderer = new DefaultTableCellRenderer();
			rRenderer.setHorizontalAlignment( JLabel.LEFT );
			
			table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			table.getColumnModel().getColumn(1).setCellRenderer(rRenderer);
			table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
			
			((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
	        .setHorizontalAlignment(JLabel.CENTER);
			
			
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getStackTrace());
		}
		finally{
            try{
            	     rs.close();
	                 rs1.close();
	                 rs2.close();
	                 rs3.close();
	                 ps.close();
                
            }catch(Exception ex){
                ex.getMessage();
            }}
		
		
	

		
	
	}

	private void combo() {
		try {
			
			String sqll="select ename from Exam";
			ps=con.prepareStatement(sqll);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				box3.addItem(rs.getString("ename"));
				box41.addItem(rs.getString("ename"));
				
			}
			
		}catch(Exception ex) {
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
             try{
	                 rs.close();
	                 ps.close();
	             }catch(Exception ex){
	                 ex.getMessage();
	             }}
       
		
		
     try {
			
			String sqll="select tname from Term";
			ps=con.prepareStatement(sqll);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				box4.addItem(rs.getString("tname"));
				box42.addItem(rs.getString("tname"));
				
			}
			
		}catch(Exception ex) {
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}finally{
             try{
	                 rs.close();
	                 ps.close();
	             }catch(Exception ex){
	                 ex.getMessage();
	             }}
       
		
	}

	private void bar(String user) {
		//JOptionPane.showMessageDialog(null, mm);
		
		final Icon alarm=new ImageIcon(this.getClass().getResource("/alarm.png"));
		final Icon cale=new ImageIcon(this.getClass().getResource("/cale.png"));
		Icon ex=new ImageIcon(this.getClass().getResource("/exit.png"));
		Icon log=new ImageIcon(this.getClass().getResource("/log.png"));
		Icon r=new ImageIcon(this.getClass().getResource("/r.png"));
		Icon u=new ImageIcon(this.getClass().getResource("/us.png"));
		Icon ab=new ImageIcon(this.getClass().getResource("/ab.png"));
		Icon cc=new ImageIcon(this.getClass().getResource("/cc.png"));
		Icon cpp=new ImageIcon(this.getClass().getResource("/pa.png"));
		Icon dell=new ImageIcon(this.getClass().getResource("/dell.png"));
		Icon show=new ImageIcon(this.getClass().getResource("/show.png"));
		Icon pdf=new ImageIcon(this.getClass().getResource("/pdf.png"));
		Icon excel=new ImageIcon(this.getClass().getResource("/excel.png"));
		Icon lev=new ImageIcon(this.getClass().getResource("/level.png"));
		Icon ad=new ImageIcon(this.getClass().getResource("/advance.png"));
		Icon clear=new ImageIcon(this.getClass().getResource("/clear.png"));
		Icon pr=new ImageIcon(this.getClass().getResource("/pri.png"));
		//Icon arc=new ImageIcon(this.getClass().getResource("/arc.png"));
		 
		
		
	JMenuBar bar=new JMenuBar();
	setJMenuBar(bar);
	
	JMenu file=new JMenu("File");
	bar.add(file);
	JMenu rep=new JMenu("Report");
	bar.add(rep);
	JMenu set=new JMenu("Setting");
	//bar.add(set);
	JMenu other=new  JMenu("Other");
	bar.add(other);
	
	JMenu att=new JMenu("Attendance Form");
	other.add(att);
	
	JMenuItem att_pdf=new JMenuItem("Pdf");
	att.add(att_pdf);
	
	JMenuItem att_exl=new JMenuItem("Excel");
	att.add(att_exl);
	
	
	JMenu help=new JMenu("Help");
	bar.add(help);
	JMenuItem about=new JMenuItem("About System");
	help.add(about);about.setIcon(ab);
	JMenuItem pri=new JMenuItem("Primary");
	//file.add(pri);pri.setIcon(pr);
	
	JMenuItem level=new JMenuItem("Form 5&6");
	//file.add(level);level.setIcon(ad);
	
	JMenu clas=new JMenu("Class Report(New)");
	JMenu dar=new JMenu("Class Report(Old)");
	JMenuItem cla=new JMenuItem("Pdf");

	JMenuItem exp=new JMenuItem("Excel");
	JMenuItem clao=new JMenuItem("Pdf");
	JMenuItem expo=new JMenuItem("Excel");

	JMenu stu=new JMenu("Student's Report");
	JMenuItem sub=new JMenuItem("Subject Report");
    rep.add(sub);
	//rep.add(stu);stu.setIcon(show);
	JMenuItem nrep=new JMenuItem("New");
	JMenuItem orep=new JMenuItem("Old");
	stu.add(nrep);stu.add(orep);
	
	//rep.add(clas);
	//rep.add(dar);
	clas.add(cla);cla.setIcon(pdf);
	clas.add(exp);exp.setIcon(excel);
	
	dar.add(clao);clao.setIcon(pdf);
	dar.add(expo);expo.setIcon(excel);
	JMenuItem del=new JMenuItem("Delete Class");
	//file.add(del);del.setIcon(dell);
	JMenuItem up=new JMenuItem("Update Class");
	//file.add(up);up.setIcon(show);
	JMenuItem res=new JMenuItem("Resert Marks");
	//file.add(res);res.setIcon(clear);
	JMenuItem cp=new JMenuItem("Change Password");
	file.add(cp);
	cp.setIcon(cpp);
	
	
	
	JMenuItem delete=new JMenuItem("Delete Archive");
	//file.add(delete);//delete.setIcon(arc);
	
	JMenuItem logout=new JMenuItem("Logout");
	file.add(logout);logout.setIcon(log);
	
	
	JMenuItem grade=new JMenuItem("Update Grade");
	set.add(grade);
	
	JMenuItem resu=new JMenuItem("Create Archieve");
	set.add(resu);
	
	sub.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			

			try {
				
				
				
				String sqll = "select * from Grade_O";
				ps = con.prepareStatement(sqll);
				ResultSet rs5 = ps.executeQuery();

				Double g1 = 0.0, g2 = 0.0, g3 = 0.0, g4 = 0.0, g5 = 0.0, g6 = 0.0, g7 = 0.0, g8 = 0.0, g9 = 0.0,
						g10 = 0.0;
				g1 = rs5.getDouble("g1");
				g2 = rs5.getDouble("g2");
				g3 = rs5.getDouble("g3");
				g4 = rs5.getDouble("g4");
				g5 = rs5.getDouble("g5");
				g6 = rs5.getDouble("g6");
				g7 = rs5.getDouble("g7");
				g8 = rs5.getDouble("g8");
				g9 = rs5.getDouble("g9");
				g10 = rs5.getDouble("g10");
		
				
				
			
				int result = JOptionPane.showConfirmDialog(null,pa8, "Select",
				        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(result==JOptionPane.OK_OPTION) {
					
					String sql1="Select sid,fname from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql1);
					ResultSet rs1=ps.executeQuery();

					String sql2="Select eid from Exam where ename= '"+box41.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql2);
					ResultSet rs2=ps.executeQuery();

					String sql3="Select tid from Term where tname= '"+box42.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql3);
					ResultSet rs3=ps.executeQuery();
					

					String sql333="Select info from detail where id= '"+1+"' ";
					ps=con.prepareStatement(sql333);
					ResultSet rsm=ps.executeQuery();
					
					

					int year=Calendar.getInstance().get(Calendar.YEAR);

					String sqq ="select count(id) from Mark_O where sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+box43.getSelectedItem().toString()+"' and class='"+box40.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sqq);
					rs=ps.executeQuery();
					int cou=Integer.parseInt(rs.getString("count(id)"));

                    if(cou>0) {
                    	
                    	
                    	String sql11 ="select * from Mark_O "
                    			+ "INNER JOIN Ouser ON Ouser.id = Mark_O.id "
                    			+ "where Mark_O.sid = '"+rs1.getString("sid")+"' and Mark_O.tid='"+rs3.getString("tid")+"' and Mark_O.eid='"+rs2.getString("eid")+"' and Mark_O.year='"+box43.getSelectedItem().toString()+"' and Mark_O.class='"+box40.getSelectedItem().toString()+"' ORDER BY Ouser.gender ASC,Ouser.fname ASC,Ouser.mname ASC,Ouser.lname ASC  ";
						ps=con.prepareStatement(sql11);
						rs=ps.executeQuery();
						
					  
						PdfDocument pdf = new PdfDocument(
								new PdfWriter(box2.getSelectedItem().toString()+"~"+box40.getSelectedItem().toString().replaceAll("'", "''")+"~"+box41.getSelectedItem().toString().replaceAll("'", "''")+"~"+box42.getSelectedItem().toString().replaceAll("'", "''")+"~"+box43.getSelectedItem().toString().replaceAll("'", "''")+".pdf"));
					
						Document document = new Document(pdf, PageSize.A4);

						Table mudi = new Table(1);
							mudi.setWidth(520).setAutoLayout();
							
							Table mudi2 = new Table(1);
							mudi2.setWidth(520).setAutoLayout();
						//    mudi = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
						    
						    Table mudi1 = new Table(10);
						   mudi1.setWidth(520);
						
							ImageData data = ImageDataFactory.create("image/other.png");
							Image image = new Image(data);
							image.setAutoScale(true);
							image.setAutoScale(true);
							image.setBold();
							Cell c0 = new Cell().add(image);
							mudi.addCell(c0);
							PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
							PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
						
			             	Paragraph para0 = new Paragraph(rsm.getString("info") + " SECONDARY SCHOOL");
			             	Paragraph para2=new Paragraph(box40.getSelectedItem().toString()+" ~ "+box43.getSelectedItem().toString());
			             	Paragraph para3=new Paragraph(box41.getSelectedItem().toString()+"  EXAM  ~  "+box42.getSelectedItem().toString());
			             	Paragraph para4=new Paragraph("SUBJECT: "+rs1.getString("fname"));
			             	Paragraph para5=new Paragraph("Total Student(s): "+cou);
			             	Paragraph para6=new Paragraph("FULL NAMES");
			             	Paragraph para7=new Paragraph("MARKS ");
			             	Paragraph para8=new Paragraph("GRADES");
			             	int a=0,b=0,c=0,d=0,f=0;
			             	
			             	
			             	Cell c1 = new Cell().add(para0).setTextAlignment(TextAlignment.CENTER).setFontSize(19).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
						
							c1 = new Cell().add(para3).setTextAlignment(TextAlignment.CENTER).setFontSize(15).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell().add(para2).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell().add(para4).setTextAlignment(TextAlignment.LEFT).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
						     
							c1 = new Cell().add(para5).setTextAlignment(TextAlignment.LEFT).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(100, 0,0)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell(1,6).add(para6).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,0));
							mudi1.addHeaderCell(c1);
							c1 = new Cell(1,2).add(para7).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,0));
							mudi1.addHeaderCell(c1);
							c1 = new Cell(1,2).add(para8).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,0));
							mudi1.addHeaderCell(c1);
							
							while(rs.next()) {
								
								Paragraph para9=new Paragraph(" "+rs.getString("fname")+"  "+rs.getString("mname")+"  "+rs.getString("lname")).setFont(font1);
				             	Paragraph para10=new Paragraph(rs.getString("mark"));
				             	Paragraph para11=new Paragraph(rs.getString("grade"));
				             	
				             	
				             	c1 = new Cell(1,6).add(para9).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setBold()
										.setFontColor(new DeviceRgb(0, 0,0));
								mudi1.addHeaderCell(c1);
								c1 = new Cell(1,2).add(para10).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setBold()
										.setFontColor(new DeviceRgb(0, 0,0));
								mudi1.addHeaderCell(c1);
								c1 = new Cell(1,2).add(para11).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setBold()
										.setFontColor(new DeviceRgb(0, 0,0));
								mudi1.addHeaderCell(c1);
								
								if (rs.getString("grade").matches("A")) {
	   								a++;
	   							} else if (rs.getString("grade").matches("B")) {
	   							b++;
	   							} else if (rs.getString("grade").matches("C")) {
	   								c++;
	   							} else if (rs.getString("grade").matches("D")) {
	   					           d++;
	   							} else if (rs.getString("grade").matches("F")) {
	   							  f++;
	   							}
								
								
								
							}
							Paragraph pp=new Paragraph("A= "+a+"    B= "+b+"    C= "+c+"    D= "+d+"    F= "+f);
							c1 = new Cell(1,2).add(pp).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,100));
							mudi2.addHeaderCell(c1);
							
			                
		                document.add(mudi); 
		                document.add(mudi1); 
		                document.add(new Paragraph("\n"));
		                document.add(mudi2); 
						document.close();

						JOptionPane.showMessageDialog(null, "Loading...Please Wait !");
						 Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+box2.getSelectedItem().toString()+"~"+box40.getSelectedItem().toString().replaceAll("'", "''")+"~"+box41.getSelectedItem().toString().replaceAll("'", "''")+"~"+box42.getSelectedItem().toString().replaceAll("'", "''")+"~"+box43.getSelectedItem().toString().replaceAll("'", "''")+".pdf");
						
					
                    	
                    	
                    	
                    }else {
                    	JOptionPane.showMessageDialog(null, "No Data Present !");
                    }
		
		        }
			
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
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
});
	
	
	
	b4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				

				
				
			
				int result = JOptionPane.showConfirmDialog(null,pa8, "Select",
				        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(result==JOptionPane.OK_OPTION) {
					
					String sql1="Select sid from Subject_O where sname= '"+box2.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql1);
					ResultSet rs1=ps.executeQuery();

					String sql2="Select eid from Exam where ename= '"+box41.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql2);
					ResultSet rs2=ps.executeQuery();

					String sql3="Select tid from Term where tname= '"+box42.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql3);
					ResultSet rs3=ps.executeQuery();

					String sql4="Select percentage from Exam where ename= '"+box41.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sql4);
					ResultSet rs4=ps.executeQuery();

					int year=Calendar.getInstance().get(Calendar.YEAR);

					String sqq ="select count(id) from Mark_O where sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+year+"' and class='"+box40.getSelectedItem().toString()+"'  ";
					ps=con.prepareStatement(sqq);
					rs=ps.executeQuery();
					int cou=Integer.parseInt(rs.getString("count(id)"));

					String sqll="select * from Grade_O";
					ps=con.prepareStatement(sqll);
					ResultSet rs5=ps.executeQuery();
					
					Double g1=0.0,g2=0.0,g3=0.0,g4=0.0,g5=0.0,g6=0.0,g7=0.0,g8=0.0,g9=0.0,g10=0.0;
					g1=rs5.getDouble("g1");g2=rs5.getDouble("g2");g3=rs5.getDouble("g3");g4=rs5.getDouble("g4");g5=rs5.getDouble("g5");
					g6=rs5.getDouble("g6");g7=rs5.getDouble("g7");g8=rs5.getDouble("g8");g9=rs5.getDouble("g9");g10=rs5.getDouble("g10");
					
					Double per=Double.parseDouble(rs4.getString("percentage"))/100;
					
					
					JFrame parentFrame = new JFrame();
					 
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");   
					
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File Only", "xlsx","xls");
					fileChooser.setFileFilter(filter);
					 
					int userSelection = fileChooser.showSaveDialog(parentFrame);
					 
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					   // System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					    
					    String path=fileToSave.getAbsolutePath();
					  
					    FileInputStream inputstream=new FileInputStream(path);
					    XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
					    XSSFSheet sheet=workbook.getSheetAt(0);
					    
					    int rows=sheet.getLastRowNum();
					    int cols=sheet.getRow(1).getLastCellNum()+1;

				    
					    for(int r=6;r<=rows;r++) {
					    	
					    	XSSFRow row=sheet.getRow(r);
					    	String id = row.getCell(1).getStringCellValue();
					    	String name = row.getCell(2).getStringCellValue();
					    	String mark="";
					    	String gr="";
					    	String pms="";
					    	
					    	XSSFCell cell=row.getCell(3);
					    	
					    	if(cell==null || cell.getCellType()==CellType.BLANK) {
					    		
					    		
					    		
					    	
					    	
					    	
					    }else {
					    	
					    	  if(cell.getCellType()==CellType.NUMERIC) {
					    		  
					    		  double mark1=cell.getNumericCellValue();
					    		 
					    		  if(mark1 >= 0 && mark1 <= 100){
					    			  
					    			   mark=String.format("%.1f",mark1);
					    			   Double pm=Double.parseDouble(mark)*per;
										 pms=String.format("%.1f",pm);

										if(mark1>=g1 && mark1<=g2) {
											gr="A";
										}else if(mark1>=g3 && mark1<=g4) {
											gr="B";
										}else if(mark1>=g5 && mark1<=g6) {
											gr="C";
										}else if(mark1>=g7 && mark1<=g8) {
											gr="D";
										}else if(mark1>=g9 && mark1<=g10) {
											gr="F";
										}
					    			  
					              } else {
					            	  JOptionPane.showMessageDialog(null,"Enter Valid Marks ! ");
					              }
					    		  
					    	  }else if(cell.getCellType()==CellType.STRING) {
					    		  
					    		 String mark2 =cell.getStringCellValue();
					    		 if(mark2.matches("-") ) {
					    			 mark =cell.getStringCellValue();
					    		 }else {
					    			 
					    			 JOptionPane.showMessageDialog(null,"Enter Valid Marks ! ");
					    		 }
					    		  
					    		  
					    	  }
					    }
					    	
					    	
					    	if(cou>0) {
								
					    	//	System.out.println(id+"     "+rs1.getString("sid")+"     "+rs2.getString("eid")+"    "+rs3.getString("tid")+"  "+mark+"  "+pms+"  "+gr+" "+t51.getSelectedItem()+"  "+t47.getSelectedItem());
					    		
					    		String sql="update Mark_O set mark='"+mark+"',pmark='"+pms+"',grade='"+gr+"'  where id='"+id+"' and sid = '"+rs1.getString("sid")+"' and tid='"+rs3.getString("tid")+"' and eid='"+rs2.getString("eid")+"' and year='"+box43.getSelectedItem().toString()+"' and class='"+box40.getSelectedItem().toString()+"'  "; 
								ps=con.prepareStatement(sql);
								ps.execute();
								
								//JOptionPane.showMessageDialog(null,"Marks Added Successfully !");
								
							}else {
								
								String sql="insert into Mark_O (id,sid,eid,tid,mark,pmark,grade,year,class) values (?,?,?,?,?,?,?,?,?) ";
								ps=con.prepareStatement(sql);
								ps.setString(1, id);
								ps.setString(2, rs1.getString("sid"));
								ps.setString(3, rs2.getString("eid"));
								ps.setString(4, rs3.getString("tid"));
								ps.setString(5, mark);
								ps.setString(6, pms);
								ps.setString(7, gr);
								ps.setString(8, box43.getSelectedItem().toString());
								ps.setString(9, box40.getSelectedItem().toString());
								ps.execute();
								
								
								
							}
					    	
					    }
					    JOptionPane.showMessageDialog(null,"Marks Added Successfully !");
				    	
					  }
					
					

		
		        }
			
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
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
	});

	
	att_pdf.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				
				String sql333="Select info from detail where id= '"+1+"' ";
				ps=con.prepareStatement(sql333);
				ResultSet rsm=ps.executeQuery();
				
				

				int result = JOptionPane.showConfirmDialog(null,pa8, "Select",
					        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				

				String sql="Select count(id) from Ouser where Class='"+box40.getSelectedItem().toString()+"'  ";
				ps=con.prepareStatement(sql);
				ResultSet rs1=ps.executeQuery();
				
				String sql1="Select * from Ouser where Class='"+box40.getSelectedItem().toString()+"'  ORDER BY Ouser.gender ASC,Ouser.fname ASC,Ouser.mname ASC,Ouser.lname ASC ";
				ps=con.prepareStatement(sql1);
			    rs=ps.executeQuery();
				
				int count=Integer.parseInt(rs1.getString("count(id)"));
			
				if(result==JOptionPane.OK_OPTION) {
					
					
					if(count>0)	{
						
					
						  
						PdfDocument pdf = new PdfDocument(
								new PdfWriter("Attendance "+box40.getSelectedItem().toString().replaceAll("'", "''")+"~"+box41.getSelectedItem().toString().replaceAll("'", "''")+"~"+box42.getSelectedItem().toString().replaceAll("'", "''")+"~"+box43.getSelectedItem().toString().replaceAll("'", "''")+".pdf"));
					
						Document document = new Document(pdf, PageSize.A4);

						Table mudi = new Table(1);
							mudi.setWidth(520).setAutoLayout();
						//    mudi = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
						    
						    Table mudi1 = new Table(10);
						   mudi1.setWidth(520);
						
							ImageData data = ImageDataFactory.create("image/other.png");
							Image image = new Image(data);
							image.setAutoScale(true);
							image.setAutoScale(true);
							image.setBold();
							Cell c0 = new Cell().add(image);
							mudi.addCell(c0);
							PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
							PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
							
			             	Paragraph  para1 = new Paragraph("ATTENDANCE FORM");
			             	
	             		  
			             	Paragraph para0 = new Paragraph(rsm.getString("info") + " SECONDARY SCHOOL");
			             	Paragraph para2=new Paragraph(box40.getSelectedItem().toString()+" ~ "+box43.getSelectedItem().toString());
			             	Paragraph para3=new Paragraph(box41.getSelectedItem().toString()+"  EXAM                                                                      "+box42.getSelectedItem().toString());
			             	Paragraph para4=new Paragraph("SUBJECT: ........................                                                           DATE: ..../..../ "+box43.getSelectedItem().toString());
			             	Paragraph para5=new Paragraph("Total Student(s): "+count);
			             	Paragraph para6=new Paragraph("FULL NAMES");
			             	Paragraph para7=new Paragraph("SIGNS ");
			             	Paragraph para8=new Paragraph("MARKS");
			             	
			             	
			             	Cell c1 = new Cell().add(para0).setTextAlignment(TextAlignment.CENTER).setFontSize(19).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell().add(para1).setTextAlignment(TextAlignment.CENTER).setFontSize(15).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell().add(para2).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell().add(para3).setTextAlignment(TextAlignment.LEFT).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell().add(para4).setTextAlignment(TextAlignment.LEFT).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 100, 255)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
						     
							c1 = new Cell().add(para5).setTextAlignment(TextAlignment.LEFT).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(100, 0,0)).setBorder(Border3D.NO_BORDER);
							mudi.addCell(c1);
							
							c1 = new Cell(1,6).add(para6).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,0));
							mudi1.addHeaderCell(c1);
							c1 = new Cell(1,2).add(para7).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,0));
							mudi1.addHeaderCell(c1);
							c1 = new Cell(1,2).add(para8).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold()
									.setFontColor(new DeviceRgb(0, 0,0));
							mudi1.addHeaderCell(c1);
							
							while(rs.next()) {
								
								Paragraph para9=new Paragraph(" "+rs.getString("fname")+"  "+rs.getString("mname")+"  "+rs.getString("lname")).setFont(font1);
				             	Paragraph para10=new Paragraph("");
				             	Paragraph para11=new Paragraph("");
				             	
				             	
				             	c1 = new Cell(1,6).add(para9).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setBold()
										.setFontColor(new DeviceRgb(0, 0,0));
								mudi1.addHeaderCell(c1);
								c1 = new Cell(1,2).add(para10).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setBold()
										.setFontColor(new DeviceRgb(0, 0,0));
								mudi1.addHeaderCell(c1);
								c1 = new Cell(1,2).add(para11).setTextAlignment(TextAlignment.CENTER).setFontSize(12).setBold()
										.setFontColor(new DeviceRgb(0, 0,0));
								mudi1.addHeaderCell(c1);
							}
						     
			                
		                document.add(mudi); 
		                document.add(mudi1); 
						document.close();

						JOptionPane.showMessageDialog(null, "Loading...Please Wait !");
						 Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Attendance "+box40.getSelectedItem().toString().replaceAll("'", "''")+"~"+box41.getSelectedItem().toString().replaceAll("'", "''")+"~"+box42.getSelectedItem().toString().replaceAll("'", "''")+"~"+box43.getSelectedItem().toString().replaceAll("'", "''")+".pdf");
						
					}else {
						
						JOptionPane.showMessageDialog(null, "No user with that Record !");
						
					}
					
				}
				
				
			}catch(Exception ex) {
				
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
			}
		}
	});
	
	
	att_exl.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				
				

				int result = JOptionPane.showConfirmDialog(null,pa8, "Select",
					        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				

				String sql="Select count(id) from Ouser where Class='"+box40.getSelectedItem().toString()+"'  ";
				ps=con.prepareStatement(sql);
				ResultSet rs1=ps.executeQuery();
				
				String sql1="Select * from Ouser where Class='"+box40.getSelectedItem().toString()+"'  ORDER BY Ouser.gender ASC,Ouser.fname ASC,Ouser.mname ASC,Ouser.lname ASC ";
				ps=con.prepareStatement(sql1);
			    rs=ps.executeQuery();
			    
			    String sql333="Select info from detail where id= '"+1+"' ";
				ps=con.prepareStatement(sql333);
				ResultSet rsm=ps.executeQuery();
				
				int count=Integer.parseInt(rs1.getString("count(id)"));
			
				if(result==JOptionPane.OK_OPTION) {
					
					
					if(count>0)	{
						
						try {
						
						 XSSFWorkbook workbook = new XSSFWorkbook(); 
					      XSSFSheet spreadsheet = workbook.createSheet("STUDENT'S REPORT");
						 XSSFRow row = spreadsheet.createRow(1);
					     XSSFCell cell = (XSSFCell) row.createCell((short) 1);
					     
					    
					      XSSFFont font = workbook.createFont();
					      font.setFontHeightInPoints((short) 20);
					      font.setFontName("VERDANA");
					      font.setBold(true);
					      XSSFCellStyle style = workbook.createCellStyle();
					      style.setFont(font);
					      
					      XSSFFont font11 = workbook.createFont();
					      font11.setFontHeightInPoints((short) 16);
					      font11.setFontName("VERDANA");
					      font11.setBold(false);
					      XSSFCellStyle style11 = workbook.createCellStyle();
					      style11.setFont(font11);
					      
					      
					      XSSFFont font22 = workbook.createFont();
					      font22.setFontHeightInPoints((short) 10);
					      font22.setFontName("VERDANA");
					      font22.setBold(true);
					      XSSFCellStyle style22 = workbook.createCellStyle();
					      style22.setFont(font22);
					     
					      row = spreadsheet.createRow(1);
					      cell = row.createCell(1);
					      cell.setCellValue(rsm.getString("info")+"  SECONDARY SCHOOL");
					      cell.setCellStyle(style);
					      
					      row = spreadsheet.createRow(2);
					      cell = row.createCell(1);
					      cell.setCellValue(""+box40.getSelectedItem().toString().replaceAll("'", "''")+"  "+box41.getSelectedItem().toString().replaceAll("'", "''")+"  "+box42.getSelectedItem().toString().replaceAll("'", "''")+"   "+box43.getSelectedItem().toString().replaceAll("'", "''"));
					      cell.setCellStyle(style11);
					      
					      
					      row = spreadsheet.createRow(3);
					      cell = row.createCell(1);
					      cell.setCellValue("SUBJECT: .........................."+"   Total Student(s):"+count);
					      cell.setCellStyle(style11);
					      
					      
					      row = spreadsheet.createRow(5);
					      cell = row.createCell(1);
					      cell.setCellValue("STU_ID");  cell.setCellStyle(style22);
					      cell = row.createCell(2);
					      cell.setCellValue("FULL NAME");  cell.setCellStyle(style22);
					      cell = row.createCell(3);
					      cell.setCellValue("MARKS");  cell.setCellStyle(style22);
					     
					      int i = 6;

					      while(rs.next()) {
					         row = spreadsheet.createRow(i);
					         cell = row.createCell(1);
					         cell.setCellValue(rs.getString("id")); 
					         cell = row.createCell(2);
					         cell.setCellValue(rs.getString("fname")+"  "+rs.getString("mname")+"  "+rs.getString("lname"));
					         cell = row.createCell(3);
					         cell.setCellValue("");
					        
					         i++;
					      }

					      JOptionPane.showMessageDialog(null, "Loading...Please Wait !");
					      FileOutputStream out = new FileOutputStream(new File("Attendance "+box40.getSelectedItem().toString().replaceAll("'", "''")+"~"+box41.getSelectedItem().toString().replaceAll("'", "''")+"~"+box42.getSelectedItem().toString().replaceAll("'", "''")+"~"+box43.getSelectedItem().toString().replaceAll("'", "''")+".xlsx"));
					      workbook.write(out);
					      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Attendance "+box40.getSelectedItem().toString().replaceAll("'", "''")+"~"+box41.getSelectedItem().toString().replaceAll("'", "''")+"~"+box42.getSelectedItem().toString().replaceAll("'", "''")+"~"+box43.getSelectedItem().toString().replaceAll("'", "''")+".xlsx");
					      out.close();   
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
						
					}else {
						
						JOptionPane.showMessageDialog(null, "No user with that Record !");
						
					}
					
				}
				
					
			
			}catch(Exception ex) {
				
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	});
	
	
	
	cp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

       try {
    	   
    	      String p1=JOptionPane.showInputDialog(null, "Enter Current Username !", "Username", 1);
	    	  String p2=JOptionPane.showInputDialog(null, "Enter Current Password !", "Password", 1);
	    	  

	    	     String sql2 ="select count(Username) from Account where Password = '"+p2+"' and Username='"+p1+"'  ";
		    	 PreparedStatement pss=con.prepareStatement(sql2);
		    	 ResultSet rss=pss.executeQuery();
		    	 int count=Integer.parseInt(rss.getString("count(Username)"));
		    	 
		    	 
		    	 if(count>0) {
		    		 
		    		 String pass=JOptionPane.showInputDialog(null, "Enter New Password !", "Password", 1);
		    		 
		    		 String sql="Update Account set Password='"+pass+"'  where Username='"+p1+"'  ";
		    		 ps=con.prepareStatement(sql);
		    		 ps.execute();
		    		 table();
		    		 JOptionPane.showMessageDialog(null,"Password Changed Successfully !");
		    		 
		    		 
		    	 }else {
		    		 
		    		 JOptionPane.showMessageDialog(null, "Incorrect Username or Password.!   Try Again.....");
		    	 }
	    	 
    	   
    	   
       }catch(Exception ex) {
    	   
    	   JOptionPane.showMessageDialog(null, ex.getMessage());
       }
			
			
		}
	});
	
	
	
	logout.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
          dispose();

  		Login l=new Login();
  		l.setSize(620,330);
  	    l.setVisible(true);
  		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		l.setResizable(false);
  		l.setLocation(350,200);
  		
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		
	});
		
	about.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			
			Icon at=new ImageIcon(this.getClass().getResource("/z.png"));
		   JOptionPane.showMessageDialog(null, "\tSTUDENT’S RESULT MANAGEMENT SYSTEM\n**************************************************\nThis Software was Designed and Created by\n MOHAMED YUSUPH.This is not a free Software \nand you are not allowed to re-distribute it without\n the prior permission of the developer\n  \nDeveloper’s Contacts:\n************************\nPhone +255 778 939 544 / +255 675 785 592\nEmail: myusuph2@gmail.com\n \nCOPYRIGHT RESERVED ©2018-2019", "About Software", 0, at);
		
			
			
		}});
	
	hash=new JLabel("                                                                                                " );
	bar.add(hash);
	//bar.add(sign);
	empty=new JLabel("                                                                                                 ");
	kappa=new JLabel("FORM  I-IV                                                                ");
	bar.add(empty);
	kappa.setForeground(Color.BLUE);kappa.setFont(new Font("Segoe Script",Font.BOLD+Font.HANGING_BASELINE,11));
	
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
	