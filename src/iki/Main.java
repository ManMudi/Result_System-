package iki;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Main {
	public static String name="";
	public static String subject="";
	public static String somo=""; 

	public static void main(String[] args) {
		
		
       

		
		Login l=new Login();
		l.setSize(620,330);
	    l.setVisible(true);
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l.setResizable(false);
		l.setLocation(350,200);
		l.setIconImage(Toolkit.getDefaultToolkit().getImage(l.getClass().getResource("/lo.png")));
		
		/*
		
		 Other oth=new Other(name,subject,somo); oth.setSize(1366,800);
	     oth.setVisible(true); oth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 oth.setResizable(false);
		 oth.setIconImage(Toolkit.getDefaultToolkit().getImage(oth.getClass().getResource("/lo.png")));
		
		
	
		  Admin m=new Admin( name,subject,somo); m.setSize(1366,800);
		  m.setVisible(true); m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  m.setResizable(false);
		  m.setIconImage(Toolkit.getDefaultToolkit().getImage(m.getClass().getResource("/lo.png")));
	
		
		 Academic ac=new Academic(name,subject,somo); 
		 ac.setSize(1366,800);
	     ac.setVisible(true); 
	     ac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 ac.setResizable(false);
		 ac.setIconImage(Toolkit.getDefaultToolkit().getImage(ac.getClass().getResource("/lo.png")));
		
		*/
	
	}

}
