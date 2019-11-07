package com.finder.myFinder;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.PlainDocument;
import javax.swing.text.*;
import javax.swing.table.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.ArrayList; 
import java.util.List;
import java.net.URL;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;





public class myFrame extends JFrame{
	
	//--- panels ---
	private JPanel container;
	private JPanel searchPanel;
	private JPanel searchResult;
	private JPanel buttonPanel;
	
	//--- components ---
	private JButton searchButton;
	private JTextField searchField;
	private JCheckBox GPBox;
	private JCheckBox OpticianBox;
	private JCheckBox SchoolBox;
	private JCheckBox DentistBox;
	private JCheckBox selectAllBox;
	private ButtonGroup BG;
	private List <RowSorter.SortKey> filters;
	private JButton distance;
	private JRadioButton metricKM;
	private JRadioButton metricMILES;
	private HaversineDistance hD = new HaversineDistance();
	
	//--- inner components ---
	private JLabel postCodeLabel;
	private TableRowSorter sorter;
	
	//--- excel data ---
	//public static final String Data = "epraccur.xls";
	//--- searching data ---
	public String userGivenPostcode;
	private List<Double> coords = new ArrayList();
	private double latCord;
	private double longCord;
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 900;
	
	private int sheet;
	
	public myFrame() {
		
		createSearch();
		createPanel();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
	}
	
	//--- creating panels and adding components ---
	public void createPanel() {
		
		container = new JPanel();
		searchPanel = new JPanel();
		searchPanel.setBorder( new TitledBorder("Search - Currently used for Testing") );
		searchResult = new JPanel();
		searchResult.setBorder( new TitledBorder("Result - Showing Database - WIP") );
		buttonPanel = new JPanel();
		buttonPanel.setBorder( new TitledBorder("Service - WIP") );
		
		container.add(searchPanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.NORTH);
		container.add(searchResult, BorderLayout.SOUTH);
		
		searchPanel.add(postCodeLabel);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		searchPanel.add(distance);
		searchPanel.add(metricKM);
		searchPanel.add(metricMILES);
		
		buttonPanel.add(GPBox);
		buttonPanel.add(OpticianBox);
		buttonPanel.add(SchoolBox);
		buttonPanel.add(DentistBox);
		buttonPanel.add(selectAllBox);

		searchResult.add(createTable());
		searchResult.setMaximumSize(searchResult.getPreferredSize());
		
		
		add(container);
		//add(searchResult);
		
		
	}
	
	//--- creating search/distance function ---
	public void createSearch() {
		
		
		//--- searcher panel filters ---
		filters = new ArrayList<RowSorter.SortKey>();
		
		///--- searchField construction ---
		postCodeLabel = new JLabel("Postcode: ");
		searchField = new JTextField(7);
		searchField.setText("");
		searchField.setDocument(new JTextFieldLimit(8));
		
		//--- can be used to filter uppercase only input ---
		//((AbstractDocument) searchField.getDocument()).setDocumentFilter(new UppercaseDocumentFilter());
		
		//--- calculate distance button ---
		distance = new JButton("Distance");
		
		//--- search | Currently just used to compare two distances ---
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//--- getting user input ---
				userGivenPostcode = searchField.getText();
				try {
					//--- using API to get lat and long of given input ---
					searcherMain sM = new searcherMain();
					sM.setPostcodeData(userGivenPostcode);
					latCord = sM.getLat();
					longCord = sM.getLong();
					
					//--- debugging ---
					System.out.println(latCord);
					System.out.println(longCord);
					
					//--- adding new cords to array ---
					coords.add(latCord);
					coords.add(longCord);
					
					//--- debugging ---
					System.out.println(coords);
					
					
					}
				//--- exceptions ---
				catch(MalformedURLException event)
				{
					System.out.println(event);
				}
				catch(IOException event)
				{
					System.out.println(event);
				}
				
				///--- pop up box ---
				JDialog jd = new JDialog();
				JLabel completedLabel = new JLabel("search done", SwingConstants.CENTER);
				jd.add(completedLabel);
				jd.setSize(200, 100);
		        jd.setVisible(true);
				
			}
		});
		
		///--- distance button listener ---
		distance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				//--- calculating distance of 2 postcodes given by user --- | using array, can be changed
				hD.calculateDistance(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
				double resultDistance = hD.getDistance();
				
				///--- distance dialog box  ---
				String distance = "Distance: " + resultDistance;
				JDialog jd = new JDialog();
				JLabel DistanceLabel = new JLabel(distance, SwingConstants.CENTER);
				jd.add(DistanceLabel);
				jd.setSize(200, 100);
		        jd.setVisible(true);
			}
		});
		
		//--- filter buttons ---
		BG = new ButtonGroup();
		GPBox = new JCheckBox("GP");
		OpticianBox = new JCheckBox("Optician");
		SchoolBox = new JCheckBox("School");
		DentistBox = new JCheckBox("Dentist");
		selectAllBox = new JCheckBox("Select all");
		
		//--- Button Group for filter buttons | Will change later ---
		BG.add(GPBox);
		BG.add(OpticianBox);
		BG.add(SchoolBox);
		BG.add(DentistBox);
		BG.add(selectAllBox);
		
		//--- Metric Buttons ---
		ButtonGroup group = new ButtonGroup();
		metricKM = new JRadioButton("KM");
		metricMILES = new JRadioButton("MILES");
		metricMILES.setSelected(true);
		group.add(metricKM);
		group.add(metricMILES);
		
		
		
		
		//--- filter buttons action listeners
		GPBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(GPBox.isSelected())
				{
					sorter.setRowFilter(RowFilter.regexFilter("GP"));
				}
			}
		});
		DentistBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(DentistBox.isSelected())
				sorter.setRowFilter(RowFilter.regexFilter("Dentist"));
			}
		});
		SchoolBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(SchoolBox.isSelected())
				sorter.setRowFilter(RowFilter.regexFilter("School"));
			}
		});
		OpticianBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(OpticianBox.isSelected())
				sorter.setRowFilter(RowFilter.regexFilter("Optician"));
			}
		});
		selectAllBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(selectAllBox.isSelected())
				sorter.setRowFilter(null);
			}
		});
		
		//--- metric button action listeners
		metricKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(metricKM.isSelected())
				{
					hD.setMetric("KM");
				}
				//sorter.setRowFilter(null);
			}
		});
		metricMILES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(metricMILES.isSelected())
				{
					hD.setMetric("MILES");
				}
				//sorter.setRowFilter(null);
			}
		});
		
		//--- setting default filter ---
		selectAllBox.setSelected(true);
	
		
	}
	
	//--- setting character limit for Postcode entry --- | from Online - Could be improved
	public class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
			if (str == null) return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
	
	
	
	//--- setting uppercase character filter --- | from Online - Could be improved
	class UppercaseDocumentFilter extends DocumentFilter {
	    public void insertString(DocumentFilter.FilterBypass fb, int offset,
	            String text, AttributeSet attr) throws BadLocationException {

	        fb.insertString(offset, text.toUpperCase(), attr);
	    }

	    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
	            String text, AttributeSet attrs) throws BadLocationException {

	        fb.replace(offset, length, text.toUpperCase(), attrs);
	    }
	}
	
	
	//--- creating table ---
	public JScrollPane createTable() {
				
		//--- vectors for JTable ---
		Vector headers = new Vector();
		Vector data = new Vector();
		
		//--- inputting Excel file | if not found, check Project Source ---
		File file = new File("epraccur.xls");
		try {
			//--- getting data from sheet ---
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			headers.clear();
			///--- setting headers into JTable from Sheet ---
			for (int i = 0; i < sheet.getColumns(); i++) {
				Cell cell1 = sheet.getCell(i, 0);
				headers.add(cell1.getContents());
			}
			data.clear();
			//--- adding data to JTable ---
			for (int j = 1; j < sheet.getRows(); j++) {
				Vector d = new Vector();
				for (int i = 0; i < sheet.getColumns(); i++) {
					Cell cell = sheet.getCell(i, j);
					d.add(cell.getContents());
				}
				d.add("\n");
				data.add(d);
			}
		}
		//--- exception ---
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//--- creating JTable Component ---
		JTable table = new JTable();
		//--- adding model from previous loops ---
		DefaultTableModel model = new DefaultTableModel(data,headers);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		model = new DefaultTableModel(data, headers);
		table.setModel(model);
		
		//--- scroll pane for data ---
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//--- resizing table based on supplied data using method ---
		int tableWidth, tableHeight;
		tableWidth = ((int)(myFrame.FRAME_WIDTH * 0.8));
		tableHeight = ((int)(myFrame.FRAME_HEIGHT * 0.7));
		scroll.setPreferredSize(new Dimension((tableWidth), (tableHeight)));
		resizeColumnWidth(table);
		sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);
		sorter.setSortsOnUpdates(true);
		
		sorter.setSortKeys(filters);
		//sorter.setRowFilter(RowFilter.regexFilter(filter));
		return scroll;
	    
	}
	
	//--- making column sizes dynamic with table data --- | from Online - Could be improved | Works as intended
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
}
	    
		
	


