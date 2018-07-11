package es.uned.yauesc.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.uned.yauesc.dataUned.DataUnedController;
import es.uned.yauesc.dataUned.DataUnedDefaultConfiguration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DataUnedGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1293442390136906606L;
	
	private JButton btnLoad;
	
	private JTextField firstFitnessLevel;
	private JTextField secondFitnessLevel;
	private JTextField thirdFitnessLevel;
	private JTextField presented;
	private JTextField fileGrade;
	private JTextField fileCentroAsociado;
	private JTextField fileExamTime;
	private JTextField fileCourse;
	private JTextField fileEnrolment;

	/**
	 * Create the panel.
	 */
	public DataUnedGui(DataUnedController dataUnedController, MainFrame mainFrame) {
		
		//this.dataUnedController = dataUnedController;
		//this.mainFrame = mainFrame;
		
		setSize(new Dimension(1030, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorthWest = new JPanel();
		panelNorth.add(panelNorthWest, BorderLayout.WEST);
		panelNorthWest.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		JButton btnReset = new JButton("Reset Values");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstFitnessLevel.setText("");
				secondFitnessLevel.setText("");
				thirdFitnessLevel.setText("");
				presented.setText("");
				fileGrade.setText("");
				fileCentroAsociado.setText("");
				fileExamTime.setText("");
				fileCourse.setText("");
				fileEnrolment.setText("");
			}
		});
		panelNorthWest.add(btnReset);
		
		JButton btnDefault = new JButton("Default Values");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				firstFitnessLevel.setText("" + DataUnedDefaultConfiguration.FIRST_LEVEL_OPTIMAL_FITNESS);
				secondFitnessLevel.setText("" + DataUnedDefaultConfiguration.SECOND_LEVEL_OPTIMAL_FITNESS);
				thirdFitnessLevel.setText("" + DataUnedDefaultConfiguration.THIRD_LEVEL_OPTIMAL_FITNESS);
				presented.setText("" + DataUnedDefaultConfiguration.PRESENTED);
				fileGrade.setText(DataUnedDefaultConfiguration.FILE_GRADE);
				fileCentroAsociado.setText(DataUnedDefaultConfiguration.FILE_CENTROASOCIADO);
				fileExamTime.setText(DataUnedDefaultConfiguration.FILE_EXAMTIME);
				fileCourse.setText(DataUnedDefaultConfiguration.FILE_COURSE);
				fileEnrolment.setText(DataUnedDefaultConfiguration.FILE_ENROLMENT);
			}
		});
		panelNorthWest.add(btnDefault);
		
		JPanel panelNorthEast = new JPanel();
		panelNorth.add(panelNorthEast, BorderLayout.EAST);
		panelNorthEast.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		btnLoad = new JButton("Load Data");
		panelNorthEast.add(btnLoad);
		
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
		add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV & TXT", "csv", "txt");
		
		JPanel panelBasicConfigurations = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBasicConfigurations.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelBasicConfigurations.setPreferredSize(new Dimension(1030, 50));
		panelBasicConfigurations.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Basic configurations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panelBasicConfigurations);
		
		JLabel lblOptimalFitness = new JLabel("Optimal Fitness:");
		panelBasicConfigurations.add(lblOptimalFitness);
		
		JLabel lblFirst = new JLabel("First");
		panelBasicConfigurations.add(lblFirst);
		
		firstFitnessLevel = new JTextField();
		firstFitnessLevel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { 
				//TODO

			}
			@Override
			public void focusGained(FocusEvent e) {
				//TODO
				
			}
		});
		panelBasicConfigurations.add(firstFitnessLevel);
		firstFitnessLevel.setColumns(5);
		
		JLabel lblSecond = new JLabel("Second");
		panelBasicConfigurations.add(lblSecond);
		
		secondFitnessLevel = new JTextField();
		secondFitnessLevel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { 
				//TODO

			}
			@Override
			public void focusGained(FocusEvent e) {
				//TODO
				
			}
		});
		panelBasicConfigurations.add(secondFitnessLevel);
		secondFitnessLevel.setColumns(5);
		
		JLabel lblThird = new JLabel("Third");
		panelBasicConfigurations.add(lblThird);
		
		thirdFitnessLevel = new JTextField();
		thirdFitnessLevel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { 
				//TODO

			}
			@Override
			public void focusGained(FocusEvent e) {
				//TODO
				
			}
		});
		panelBasicConfigurations.add(thirdFitnessLevel);
		thirdFitnessLevel.setColumns(5);
		
		JLabel lblPresented = new JLabel("% Presented");
		panelBasicConfigurations.add(lblPresented);
		
		presented = new JTextField();
		panelBasicConfigurations.add(presented);
		presented.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { 
				//TODO
				if (!presented.getText().equals("")) {
					double percentage = Double.parseDouble(presented.getText());
					if ((percentage <= 0) || (percentage > 1.0)) {
						presented.setBackground(Color.RED);
					} else {
						presented.setBackground(Color.WHITE);
					}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				//TODO
				presented.setBackground(Color.WHITE);
			}
		});
		presented.setColumns(5);
		
		JPanel panelDataFiles = new JPanel();
		panelDataFiles.setPreferredSize(new Dimension(1030, 200));
		panelMain.add(panelDataFiles);
		panelDataFiles.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Data files", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelDataFiles = new GridBagLayout();
		gbl_panelDataFiles.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelDataFiles.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelDataFiles.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDataFiles.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDataFiles.setLayout(gbl_panelDataFiles);
		
		JLabel lblfileGrade = new JLabel("Grades Data");
		GridBagConstraints gbc_lblfileGrade = new GridBagConstraints();
		gbc_lblfileGrade.anchor = GridBagConstraints.EAST;
		gbc_lblfileGrade.insets = new Insets(10, 30, 5, 5);
		gbc_lblfileGrade.gridx = 0;
		gbc_lblfileGrade.gridy = 0;
		panelDataFiles.add(lblfileGrade, gbc_lblfileGrade);
		
		fileGrade = new JTextField();
		GridBagConstraints gbc_fileGrade = new GridBagConstraints();
		gbc_fileGrade.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fileGrade.insets = new Insets(10, 0, 10, 5);
		gbc_fileGrade.gridx = 1;
		gbc_fileGrade.gridy = 0;
		panelDataFiles.add(fileGrade, gbc_fileGrade);
		fileGrade.setColumns(30);
		
		JButton btnFileGrade = new JButton("Browse");
		btnFileGrade.setPreferredSize(new Dimension(87, 20));
		btnFileGrade.setMaximumSize(new Dimension(87, 20));
		btnFileGrade.setMinimumSize(new Dimension(87, 20));
		btnFileGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileGradeChooser = new JFileChooser();
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(new Frame());
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					fileGrade.setText(fileGradeChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		GridBagConstraints gbc_btnFileGrade = new GridBagConstraints();
		gbc_btnFileGrade.insets = new Insets(10, 0, 10, 0);
		gbc_btnFileGrade.gridx = 2;
		gbc_btnFileGrade.gridy = 0;
		panelDataFiles.add(btnFileGrade, gbc_btnFileGrade);
		
		JLabel lblFileCentroAsociado = new JLabel("Centro Asociado Data");
		GridBagConstraints gbc_lblFileCentroAsociado = new GridBagConstraints();
		gbc_lblFileCentroAsociado.anchor = GridBagConstraints.EAST;
		gbc_lblFileCentroAsociado.insets = new Insets(0, 30, 5, 5);
		gbc_lblFileCentroAsociado.gridx = 0;
		gbc_lblFileCentroAsociado.gridy = 1;
		panelDataFiles.add(lblFileCentroAsociado, gbc_lblFileCentroAsociado);
		
		fileCentroAsociado = new JTextField();
		GridBagConstraints gbc_fileCentroAsociado = new GridBagConstraints();
		gbc_fileCentroAsociado.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fileCentroAsociado.insets = new Insets(0, 0, 10, 5);
		gbc_fileCentroAsociado.gridx = 1;
		gbc_fileCentroAsociado.gridy = 1;
		panelDataFiles.add(fileCentroAsociado, gbc_fileCentroAsociado);
		fileCentroAsociado.setColumns(30);
		
		JButton btnFileCentroAsociado = new JButton("Browse");
		btnFileCentroAsociado.setPreferredSize(new Dimension(87, 20));
		btnFileCentroAsociado.setMinimumSize(new Dimension(87, 20));
		btnFileCentroAsociado.setMaximumSize(new Dimension(87, 20));
		btnFileCentroAsociado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileGradeChooser = new JFileChooser();
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(new Frame());
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					fileCentroAsociado.setText(fileGradeChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		GridBagConstraints gbc_btnFileCentroAsociado = new GridBagConstraints();
		gbc_btnFileCentroAsociado.insets = new Insets(0, 0, 10, 0);
		gbc_btnFileCentroAsociado.gridx = 2;
		gbc_btnFileCentroAsociado.gridy = 1;
		panelDataFiles.add(btnFileCentroAsociado, gbc_btnFileCentroAsociado);
		
		JLabel lblFileExamTime = new JLabel("Exam Time Data");
		GridBagConstraints gbc_lblFileExamTime = new GridBagConstraints();
		gbc_lblFileExamTime.anchor = GridBagConstraints.EAST;
		gbc_lblFileExamTime.insets = new Insets(0, 30, 5, 5);
		gbc_lblFileExamTime.gridx = 0;
		gbc_lblFileExamTime.gridy = 2;
		panelDataFiles.add(lblFileExamTime, gbc_lblFileExamTime);
		
		fileExamTime = new JTextField();
		GridBagConstraints gbc_fileExamTime = new GridBagConstraints();
		gbc_fileExamTime.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fileExamTime.insets = new Insets(0, 0, 10, 5);
		gbc_fileExamTime.gridx = 1;
		gbc_fileExamTime.gridy = 2;
		panelDataFiles.add(fileExamTime, gbc_fileExamTime);
		fileExamTime.setColumns(30);
		
		JButton btnFileExamTime = new JButton("Browse");
		btnFileExamTime.setPreferredSize(new Dimension(87, 20));
		btnFileExamTime.setMinimumSize(new Dimension(87, 20));
		btnFileExamTime.setMaximumSize(new Dimension(87, 20));
		btnFileExamTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileGradeChooser = new JFileChooser();
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(new Frame());
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					fileExamTime.setText(fileGradeChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		GridBagConstraints gbc_btnFileExamTime = new GridBagConstraints();
		gbc_btnFileExamTime.insets = new Insets(0, 0, 10, 0);
		gbc_btnFileExamTime.gridx = 2;
		gbc_btnFileExamTime.gridy = 2;
		panelDataFiles.add(btnFileExamTime, gbc_btnFileExamTime);
		
		JLabel lblFileCourse = new JLabel("Course Data");
		GridBagConstraints gbc_lblFileCourse = new GridBagConstraints();
		gbc_lblFileCourse.anchor = GridBagConstraints.EAST;
		gbc_lblFileCourse.insets = new Insets(0, 30, 5, 5);
		gbc_lblFileCourse.gridx = 0;
		gbc_lblFileCourse.gridy = 3;
		panelDataFiles.add(lblFileCourse, gbc_lblFileCourse);
		
		fileCourse = new JTextField();
		GridBagConstraints gbc_fileCourse = new GridBagConstraints();
		gbc_fileCourse.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fileCourse.insets = new Insets(0, 0, 10, 5);
		gbc_fileCourse.gridx = 1;
		gbc_fileCourse.gridy = 3;
		panelDataFiles.add(fileCourse, gbc_fileCourse);
		fileCourse.setColumns(30);
		
		JButton btnFileCourse = new JButton("Browse");
		btnFileCourse.setPreferredSize(new Dimension(87, 20));
		btnFileCourse.setMinimumSize(new Dimension(87, 20));
		btnFileCourse.setMaximumSize(new Dimension(87, 20));
		btnFileCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileGradeChooser = new JFileChooser();
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(new Frame());
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					fileCourse.setText(fileGradeChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		GridBagConstraints gbc_btnFileCourse = new GridBagConstraints();
		gbc_btnFileCourse.insets = new Insets(0, 0, 10, 0);
		gbc_btnFileCourse.gridx = 2;
		gbc_btnFileCourse.gridy = 3;
		panelDataFiles.add(btnFileCourse, gbc_btnFileCourse);
		
		JLabel lblFileEnrolment = new JLabel("Enrolment Data");
		GridBagConstraints gbc_lblFileEnrolment = new GridBagConstraints();
		gbc_lblFileEnrolment.anchor = GridBagConstraints.EAST;
		gbc_lblFileEnrolment.insets = new Insets(0, 30, 0, 5);
		gbc_lblFileEnrolment.gridx = 0;
		gbc_lblFileEnrolment.gridy = 4;
		panelDataFiles.add(lblFileEnrolment, gbc_lblFileEnrolment);
		
		fileEnrolment = new JTextField();
		GridBagConstraints gbc_fileEnrolment = new GridBagConstraints();
		gbc_fileEnrolment.insets = new Insets(0, 0, 0, 5);
		gbc_fileEnrolment.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fileEnrolment.gridx = 1;
		gbc_fileEnrolment.gridy = 4;
		panelDataFiles.add(fileEnrolment, gbc_fileEnrolment);
		fileEnrolment.setColumns(30);
		
		JButton btnFileEnrolment = new JButton("Browse");
		btnFileEnrolment.setPreferredSize(new Dimension(87, 20));
		btnFileEnrolment.setMinimumSize(new Dimension(87, 20));
		btnFileEnrolment.setMaximumSize(new Dimension(87, 20));
		btnFileEnrolment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileGradeChooser = new JFileChooser();
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(new Frame());
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					fileEnrolment.setText(fileGradeChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		GridBagConstraints gbc_btnFileEnrolment = new GridBagConstraints();
		gbc_btnFileEnrolment.gridx = 2;
		gbc_btnFileEnrolment.gridy = 4;
		panelDataFiles.add(btnFileEnrolment, gbc_btnFileEnrolment);
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Checks lacks
				working.setVisible(true);
				mainFrame.resetFromLoadData();
				dataUnedController.setOptimalFitness(Integer.parseInt(firstFitnessLevel.getText()), Integer.parseInt(secondFitnessLevel.getText()), Integer.parseInt(thirdFitnessLevel.getText()));
				dataUnedController.setPercentagePresented(Double.parseDouble(presented.getText()));
				dataUnedController.setFileGradePath(fileGrade.getText());
				dataUnedController.setFileCentroAsociadoPath(fileCentroAsociado.getText());
				dataUnedController.setFileExamTimePath(fileExamTime.getText());
				dataUnedController.setFileCoursePath(fileCourse.getText());
				dataUnedController.setFileEnrolmentPath(fileEnrolment.getText());
				working.setVisible(false);
				try {
					dataUnedController.parseData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				mainFrame.setGeneticAlgorithmConfigTab();
			}
		});
		
	}
	
	public void disableLoadData() {
		btnLoad.setEnabled(false);
	}
	
	public void enableLoadData() {
		btnLoad.setEnabled(true);
	}

}
