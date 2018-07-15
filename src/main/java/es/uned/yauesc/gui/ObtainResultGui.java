package es.uned.yauesc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.dataUned.DataUnedDefaultConfiguration;
import es.uned.yauesc.dataUned.FitnessUned;
import es.uned.yauesc.dataUned.FormatFileExtension;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class ObtainResultGui extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4350197841151246760L;
	
	private DataUnedController dataUnedController;
	
	private JComboBox<String> comboBoxGrade; 
	private JList<String> listCourse;
	private JScrollPane panelList;
	private JCheckBox chckbxGradeEnable;
	private JCheckBox chckbxCourseEnable;
	private JTextField first;
	private JTextField second;
	private JTextField third;
	private JTextArea textAreaResult;

	private boolean filterGrade;
	private boolean filterCourse;
	
	/**
	 * Create the panel.
	 */
	public ObtainResultGui(DataUnedController dataUnedController) {
		this.dataUnedController = dataUnedController;
		
		filterGrade = false;
		filterCourse = false;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorthWest = new JPanel();
		panelNorth.add(panelNorthWest, BorderLayout.WEST);
		panelNorthWest.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		JButton btnReset = new JButton("Reset Values");
		panelNorthWest.add(btnReset);
		
		JButton btnDefault = new JButton("Default Values");
		panelNorthWest.add(btnDefault);
		
		JPanel panelNorthEast = new JPanel();
		panelNorth.add(panelNorthEast, BorderLayout.EAST);
		panelNorthEast.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		JButton btnExportXML = new JButton("Export XML");
		panelNorthEast.add(btnExportXML);
		
		JButton btnExportCSV = new JButton("Export CSV");
		panelNorthEast.add(btnExportCSV);
		
		JPanel panelWorkingIcon = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelWorkingIcon.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panelWorkingIcon.setPreferredSize(new Dimension(30, 30));
		JLabel working = new JLabel(new ImageIcon("./images/working.gif"));
		working.setEnabled(false);
		working.setVisible(false);
		panelWorkingIcon.add(working);
		panelNorthEast.add(panelWorkingIcon);
		
		JPanel panelMain = new JPanel();
		add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInformation = new JPanel();
		FlowLayout fl_panelInformation = (FlowLayout) panelInformation.getLayout();
		fl_panelInformation.setAlignment(FlowLayout.RIGHT);
		panelInformation.setPreferredSize(new Dimension(520, 500));
		panelInformation.setBorder(null);
		panelMain.add(panelInformation, BorderLayout.WEST);
		
		JPanel panelSolution = new JPanel();
		panelSolution.setPreferredSize(new Dimension(509, 565));
		panelSolution.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Solution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInformation.add(panelSolution);
		
		JLabel lblFitness = new JLabel("Fitness:");
		
		JLabel lblFirst = new JLabel("First");
		
		first = new JTextField();
		first.setEditable(false);
		first.setColumns(10);
		
		JLabel lblSecond = new JLabel("Second");
		
		second = new JTextField();
		second.setEditable(false);
		second.setColumns(10);
		
		JLabel lblThird = new JLabel("Third");
		
		third = new JTextField();
		third.setEditable(false);
		third.setColumns(10);
		
		JScrollPane paneResult = new JScrollPane();
		textAreaResult = new JTextArea();
		textAreaResult.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaResult.setEditable(false);
		paneResult.setViewportView(textAreaResult);
		GroupLayout gl_panelSolution = new GroupLayout(panelSolution);
		gl_panelSolution.setHorizontalGroup(
			gl_panelSolution.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSolution.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSolution.createParallelGroup(Alignment.LEADING)
						.addComponent(paneResult, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelSolution.createSequentialGroup()
							.addComponent(lblFitness)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFirst)
							.addGap(2)
							.addComponent(first, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSecond)
							.addGap(3)
							.addComponent(second, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblThird)
							.addGap(2)
							.addComponent(third, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelSolution.setVerticalGroup(
			gl_panelSolution.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSolution.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSolution.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFitness)
						.addComponent(lblFirst)
						.addComponent(first, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSecond)
						.addComponent(second, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblThird)
						.addComponent(third, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(paneResult, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelSolution.setLayout(gl_panelSolution);
		
		JPanel panelFilter = new JPanel();
		panelFilter.setPreferredSize(new Dimension(520, 500));
		FlowLayout fl_panelFilter = (FlowLayout) panelFilter.getLayout();
		fl_panelFilter.setAlignment(FlowLayout.LEFT);
		panelMain.add(panelFilter, BorderLayout.CENTER);
		
		JPanel panelFilterGrade = new JPanel();
		panelFilter.add(panelFilterGrade);
		panelFilterGrade.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter by Grade", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelFilterGrade.setPreferredSize(new Dimension(509, 110));
		
		chckbxGradeEnable = new JCheckBox("Enable");
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setEnabled(false);
		
		comboBoxGrade = new JComboBox<>();
		comboBoxGrade.setEnabled(false);
		
		JButton btnSetFilterGrade = new JButton("Set");
		btnSetFilterGrade.setEnabled(false);
		GroupLayout gl_panelFilterGrade = new GroupLayout(panelFilterGrade);
		gl_panelFilterGrade.setHorizontalGroup(
			gl_panelFilterGrade.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterGrade.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panelFilterGrade.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFilterGrade.createSequentialGroup()
							.addComponent(lblGrade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxGrade, 0, 411, Short.MAX_VALUE))
						.addGroup(gl_panelFilterGrade.createSequentialGroup()
							.addComponent(chckbxGradeEnable)
							.addPreferredGap(ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
							.addComponent(btnSetFilterGrade)))
					.addContainerGap())
		);
		gl_panelFilterGrade.setVerticalGroup(
			gl_panelFilterGrade.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterGrade.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilterGrade.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxGradeEnable)
						.addComponent(btnSetFilterGrade))
					.addGap(18)
					.addGroup(gl_panelFilterGrade.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGrade)
						.addComponent(comboBoxGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelFilterGrade.setLayout(gl_panelFilterGrade);
		
		JPanel panelFilterCourse = new JPanel();
		panelFilter.add(panelFilterCourse);
		panelFilterCourse.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter by Course", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFilterCourse.setPreferredSize(new Dimension(509, 450));
		
		chckbxCourseEnable = new JCheckBox("Enable");
		
		panelList = new JScrollPane();
		panelList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listCourse = new JList<>();
		panelList.setViewportView(listCourse);
		listCourse.setEnabled(false);
		panelList.getVerticalScrollBar().setEnabled(false);
		panelList.getHorizontalScrollBar().setEnabled(false);
		panelList.getViewport().getView().setEnabled(false);
		panelList.setWheelScrollingEnabled(false);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setEnabled(false);
		
		JButton btnSetFilterCourse = new JButton("Set");
		btnSetFilterCourse.setEnabled(false);
		GroupLayout gl_panelFilterCourse = new GroupLayout(panelFilterCourse);
		gl_panelFilterCourse.setHorizontalGroup(
			gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterCourse.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
						.addComponent(panelList, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
						.addGroup(gl_panelFilterCourse.createSequentialGroup()
							.addComponent(chckbxCourseEnable)
							.addPreferredGap(ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
							.addComponent(btnSetFilterCourse))
						.addComponent(lblCourse))
					.addContainerGap())
		);
		gl_panelFilterCourse.setVerticalGroup(
			gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterCourse.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilterCourse.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCourseEnable)
						.addComponent(btnSetFilterCourse))
					.addGap(18)
					.addComponent(lblCourse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelList, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelFilterCourse.setLayout(gl_panelFilterCourse);
		
		chckbxCourseEnable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxCourseEnable.isSelected()) {
					chckbxGradeEnable.setSelected(false);
					lblCourse.setEnabled(true);
					listCourse.setEnabled(true);
					panelList.getVerticalScrollBar().setEnabled(true);
					panelList.getHorizontalScrollBar().setEnabled(true);
					panelList.getViewport().getView().setEnabled(true);
					panelList.setWheelScrollingEnabled(true);
					btnSetFilterCourse.setEnabled(true);
				} else {
					clearFilter();
					lblCourse.setEnabled(false);
					listCourse.setEnabled(false);
					panelList.getVerticalScrollBar().setEnabled(false);
					panelList.getHorizontalScrollBar().setEnabled(false);
					panelList.getViewport().getView().setEnabled(false);
					panelList.setWheelScrollingEnabled(false);
					btnSetFilterCourse.setEnabled(false);
				}
			}
		});
		
		btnSetFilterCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterCourse = true;
				filterGrade = false;
				List<String> courses = new ArrayList<>(listCourse.getSelectedValuesList());
				textAreaResult.setText(dataUnedController.getByCourseScheduleString(courses));
			}
		});
		
		chckbxGradeEnable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxGradeEnable.isSelected()) {
					chckbxCourseEnable.setSelected(false);
					lblGrade.setEnabled(true);
					comboBoxGrade.setEnabled(true);
					btnSetFilterGrade.setEnabled(true);
				} else {
					clearFilter();
					lblGrade.setEnabled(false);
					comboBoxGrade.setEnabled(false);
					btnSetFilterGrade.setEnabled(false);
				}
			}
		});
		
		btnSetFilterGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterCourse = false;
				filterGrade = true;
				String grade = (String) comboBoxGrade.getSelectedItem();
				textAreaResult.setText(dataUnedController.getByGradeScheduleString(grade));
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				clearFilter();
			}
		});
		
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGradeEnable.setSelected(false);
				chckbxCourseEnable.setSelected(false);
				comboBoxGrade.setSelectedIndex(-1);
				listCourse.clearSelection();
			}
		});
		
		btnExportCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				working.setVisible(true);
				FormatFileExtension formatFile = FormatFileExtension.CSV;
				exportToFile(formatFile);	
				working.setVisible(false);
			}
		});
		
		btnExportXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				working.setVisible(true);
				FormatFileExtension formatFile = FormatFileExtension.XML;
				exportToFile(formatFile);							
				working.setVisible(false);
			}
		});

	}
	
	private void exportToFile(FormatFileExtension formatFile) {
		ImageIcon icon = new ImageIcon("./images/logo.png");
		JFrame frame = new JFrame();
		frame.setIconImage(icon.getImage());
		
		String filePath = null;
		JFileChooser filePathChooser = new JFileChooser(DataUnedDefaultConfiguration.EXPORT_PATH);
		int returnValor = filePathChooser.showSaveDialog(frame);
		if (returnValor == JFileChooser.APPROVE_OPTION) {
			filePath = filePathChooser.getSelectedFile().getAbsolutePath();
			
			if (filterGrade) {
				String grade = (String) comboBoxGrade.getSelectedItem();
				dataUnedController.createByGradeSchedule(filePath, grade, formatFile);
			} else if (filterCourse) {
				List<String> courses = new ArrayList<>(listCourse.getSelectedValuesList());
				dataUnedController.createByCourseSchedule(filePath, courses, formatFile);
			} else {
				dataUnedController.createAllSchedule(filePath, formatFile);
			}
		}
	}
	
	private void reset() {
		filterCourse = false;
		filterGrade = false;
		chckbxGradeEnable.setSelected(false);
		chckbxCourseEnable.setSelected(false);
		comboBoxGrade.setSelectedIndex(-1);
		listCourse.clearSelection();
	}
	
	private void clearFilter() {
		textAreaResult.setText(dataUnedController.getAllScheduleString());
		filterCourse = false;
		filterGrade = false;
	}
	
	public void initialize() {
		reset();
		comboBoxGrade.removeAllItems();
		listCourse.removeAll();
		textAreaResult.setText("");
		first.setText("");
		second.setText("");
		third.setText("");
	}
	
	public void preparePanel(List<String> gradeCodeList, List<String> courseCodeList) {
		textAreaResult.setText(dataUnedController.getAllScheduleString());
		FitnessUned fitnessSolution = dataUnedController.getSolutionFitness();
		first.setText("" +fitnessSolution.getFirstLevel());
		second.setText("" + fitnessSolution.getSecondLevel());
		third.setText("" + fitnessSolution.getThirdLevel());
		gradeCodeList.stream().forEachOrdered(gradeCode -> comboBoxGrade.addItem(gradeCode));
		DefaultListModel<String> listModel = new DefaultListModel<>();
		courseCodeList.stream().forEachOrdered(courseCode -> listModel.addElement(courseCode));
		listCourse.setModel(listModel);
	}
}
