package es.uned.yauesc.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.FlowLayout;
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
import es.uned.yauesc.dataUned.FileCentroAsociadoException;
import es.uned.yauesc.dataUned.FileCourseException;
import es.uned.yauesc.dataUned.FileEnrolmentException;
import es.uned.yauesc.dataUned.FileExamTimeException;
import es.uned.yauesc.dataUned.FileGradeException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

/**
 * Clase que encapsula la gui que permite al usuario especificar el origen de los datos sobre los que trabajará la UNED
 */
public class DataUnedGui extends JPanel {

	
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

	//This class log
	private final static Logger LOGGER = Logger.getLogger(DataUnedGui.class.getName());
	
	/**
	 * Crea el panel que permite la carga de datos sobre la uned en la aplicación
	 */
	public DataUnedGui(DataUnedController dataUnedController, MainFrame mainFrame) {
		
		LOGGER.log(Level.INFO, "DataUnedGui created");
		
		setSize(new Dimension(1030, 600));
		setLayout(new BorderLayout(0, 0));
		
		ImageIcon icon = new ImageIcon("./images/logo.png");
		JFrame frame = new JFrame();
		frame.setIconImage(icon.getImage());
		
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
				resetBackground();
				firstFitnessLevel.setText("");
				secondFitnessLevel.setText("");
				thirdFitnessLevel.setText("");
				presented.setText("");
				fileGrade.setText("");
				fileCentroAsociado.setText("");
				fileExamTime.setText("");
				fileCourse.setText("");
				fileEnrolment.setText("");
				LOGGER.log(Level.INFO, "Reset values");
			}
		});
		panelNorthWest.add(btnReset);
		
		JButton btnDefault = new JButton("Default Values");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetBackground();
				firstFitnessLevel.setText("" + DataUnedDefaultConfiguration.FIRST_LEVEL_OPTIMAL_FITNESS);
				secondFitnessLevel.setText("" + DataUnedDefaultConfiguration.SECOND_LEVEL_OPTIMAL_FITNESS);
				thirdFitnessLevel.setText("" + DataUnedDefaultConfiguration.THIRD_LEVEL_OPTIMAL_FITNESS);
				presented.setText("" + DataUnedDefaultConfiguration.PRESENTED);
				fileGrade.setText(DataUnedDefaultConfiguration.FILE_GRADE);
				fileCentroAsociado.setText(DataUnedDefaultConfiguration.FILE_CENTROASOCIADO);
				fileExamTime.setText(DataUnedDefaultConfiguration.FILE_EXAMTIME);
				fileCourse.setText(DataUnedDefaultConfiguration.FILE_COURSE);
				fileEnrolment.setText(DataUnedDefaultConfiguration.FILE_ENROLMENT);
				LOGGER.log(Level.INFO, "Load default values");
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
		firstFitnessLevel.setToolTipText("The first level of fitness, must be a 0 or positive integer");
		firstFitnessLevel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				firstFitnessLevel.setBackground(Color.WHITE);
			}
		});
		panelBasicConfigurations.add(firstFitnessLevel);
		firstFitnessLevel.setColumns(5);
		
		JLabel lblSecond = new JLabel("Second");
		panelBasicConfigurations.add(lblSecond);
		
		secondFitnessLevel = new JTextField();
		secondFitnessLevel.setToolTipText("The second level of fitness, must be a 0 or positive integer");
		secondFitnessLevel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				secondFitnessLevel.setBackground(Color.WHITE);
			}
		});
		panelBasicConfigurations.add(secondFitnessLevel);
		secondFitnessLevel.setColumns(5);
		
		JLabel lblThird = new JLabel("Third");
		panelBasicConfigurations.add(lblThird);
		
		thirdFitnessLevel = new JTextField();
		thirdFitnessLevel.setToolTipText("The third level of fitness, must be a 0 or positive integer");
		thirdFitnessLevel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				thirdFitnessLevel.setBackground(Color.WHITE);
			}
		});
		panelBasicConfigurations.add(thirdFitnessLevel);
		thirdFitnessLevel.setColumns(5);
		
		JLabel lblPresented = new JLabel("% Presented");
		panelBasicConfigurations.add(lblPresented);
		
		presented = new JTextField();
		presented.setToolTipText("Expectes percentage of studenst presented to exams. Must be in interval: 0 < x <= 1.0");
		panelBasicConfigurations.add(presented);
		presented.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
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
		fileGrade.setToolTipText("The path to grade-data file, the file must be exist and it must contain correctly formatted data.");
		fileGrade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				fileGrade.setBackground(Color.WHITE);
			}
		});
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
				JFileChooser fileGradeChooser = new JFileChooser("./data/");
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(frame);
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
		fileCentroAsociado.setToolTipText("The path to CentroAsociado-data file, the file must be exist and it must contain correctly formatted data.");
		fileCentroAsociado.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				fileCentroAsociado.setBackground(Color.WHITE);
			}
		});
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
				JFileChooser fileGradeChooser = new JFileChooser("./data/");
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(frame);
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
		fileExamTime.setToolTipText("The path to ExamTime-data file, the file must be exist and it must contain correctly formatted data.");
		fileExamTime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				fileExamTime.setBackground(Color.WHITE);
			}
		});
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
				JFileChooser fileGradeChooser = new JFileChooser("./data/");
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(frame);
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
		fileCourse.setToolTipText("The path to Course-data file, the file must be exist and it must contain correctly formatted data.");
		fileCourse.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				fileCourse.setBackground(Color.WHITE);
			}
		});
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
				JFileChooser fileGradeChooser = new JFileChooser("./data/");
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(frame);
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
		fileEnrolment.setToolTipText("The path to Enrolment-data file, the file must be exist and it must contain correctly formatted data.");
		fileEnrolment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				fileEnrolment.setBackground(Color.WHITE);
			}
		});
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
				JFileChooser fileGradeChooser = new JFileChooser("./data/");
				fileGradeChooser.setFileFilter(filter);
				int returnValor = fileGradeChooser.showOpenDialog(frame);
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
				working.setVisible(true);
				boolean fail = false;
				mainFrame.resetFromLoadData();
				
				int first = 0;
				try {
					first = Integer.parseInt(firstFitnessLevel.getText());
					if (first < 0) {
						fail = true;
						firstFitnessLevel.setBackground(Color.RED);
						LOGGER.log(Level.WARNING, "First value: " + firstFitnessLevel.getText() + " is not correct");
					}
				} catch (NumberFormatException e2) {
					LOGGER.log(Level.WARNING, "First value: " + firstFitnessLevel.getText() + " is not correct");
					fail = true;
					firstFitnessLevel.setBackground(Color.RED);
				}			
				int second = 0;
				try {
					second = Integer.parseInt(secondFitnessLevel.getText());
					if (second < 0) {
						LOGGER.log(Level.WARNING, "Second value: " + secondFitnessLevel.getText() + " is not correct");
						fail = true;
						secondFitnessLevel.setBackground(Color.RED);
					}
				} catch (NumberFormatException e2) {
					LOGGER.log(Level.WARNING, "Second value: " + secondFitnessLevel.getText() + " is not correct");
					fail = true;
					secondFitnessLevel.setBackground(Color.RED);
				} 
				int third = 0;
				try {
					third = Integer.parseInt(thirdFitnessLevel.getText());
					if (third < 0) {
						LOGGER.log(Level.WARNING, "Third value: " + thirdFitnessLevel.getText() + " is not correct");
						fail = true;
						thirdFitnessLevel.setBackground(Color.RED);
					}
				} catch (NumberFormatException e2) {
					LOGGER.log(Level.WARNING, "Third value: " + thirdFitnessLevel.getText() + " is not correct");
					fail = true;
					thirdFitnessLevel.setBackground(Color.RED);
				} 
				if (!fail) {
					dataUnedController.setOptimalFitness(first, second, third);
				}
				
				double presentedNumber = 0.0;
				try {
					presentedNumber = Double.parseDouble(presented.getText());
					if ((presentedNumber <= 0) || (presentedNumber > 1.0)) {
						LOGGER.log(Level.WARNING, "Presented value: " + presented.getText() + " is not correct");
						fail = true;
						presented.setBackground(Color.RED);
					}
				} catch (NumberFormatException e2) {
					LOGGER.log(Level.WARNING, "Presented value: " + presented.getText() + " is not correct");
					fail = true;
					presented.setBackground(Color.RED);
				} 
				if (!fail) {
					dataUnedController.setPercentagePresented(presentedNumber);
				}
				
				if (fileGrade.getText().equals("")) {
					LOGGER.log(Level.WARNING, "File grade path value is not set");
					fail = true;
					fileGrade.setBackground(Color.RED);
				} else {
					dataUnedController.setFileGradePath(fileGrade.getText());
				}
								
				if (fileCentroAsociado.getText().equals("")) {
					LOGGER.log(Level.WARNING, "File centro asociado path value is not set");
					fail = true;
					fileCentroAsociado.setBackground(Color.RED);
				} else {
					dataUnedController.setFileCentroAsociadoPath(fileCentroAsociado.getText());
				}
								
				if (fileExamTime.getText().equals("")) {
					LOGGER.log(Level.WARNING, "File exam time path value is not set");
					fail = true;
					fileExamTime.setBackground(Color.RED);
				} else {
					dataUnedController.setFileExamTimePath(fileExamTime.getText());
				}
				
				if (fileCourse.getText().equals("")) {
					LOGGER.log(Level.WARNING, "File course path value is not set");
					fail = true;
					fileCourse.setBackground(Color.RED);
				} else {
					dataUnedController.setFileCoursePath(fileCourse.getText());
				}
								
				if (fileEnrolment.getText().equals("")) {
					LOGGER.log(Level.WARNING, "File enrolment path value is not set");
					fail = true;
					fileEnrolment.setBackground(Color.RED);
				} else {
					dataUnedController.setFileEnrolmentPath(fileEnrolment.getText());
				}
						
				if (!fail) {
					try {
						dataUnedController.parseData();
					} catch (FileGradeException e1) {
						LOGGER.log(Level.WARNING, "File grade: " + fileGrade.getText() + " is not correct");
						fail = true;
						fileGrade.setBackground(Color.RED);
					} catch (FileCentroAsociadoException e1) {
						LOGGER.log(Level.WARNING, "File centro asociado: " + fileCentroAsociado.getText() + " is not correct");
						fail = true;
						fileCentroAsociado.setBackground(Color.RED);
					} catch (FileExamTimeException e1) {
						LOGGER.log(Level.WARNING, "File exam time: " + fileExamTime.getText() + " is not correct");
						fail = true;
						fileExamTime.setBackground(Color.RED);
					} catch (FileCourseException e1) {
						LOGGER.log(Level.WARNING, "File course: " + fileCourse.getText() + " is not correct");
						fail = true;
						fileCourse.setBackground(Color.RED);
					} catch (FileEnrolmentException e1) {
						LOGGER.log(Level.WARNING, "File enrolment: " + fileEnrolment.getText() + " is not correct");
						fail = true;
						fileEnrolment.setBackground(Color.RED);
					} 
				}
				
				working.setVisible(false);
				
				if (!fail) {
					LOGGER.log(Level.INFO, "Data Uned data load succesfully");
					mainFrame.setGeneticAlgorithmConfigTab();
				}
				
			}
		});
		
	}
	
	private void resetBackground() {
		firstFitnessLevel.setBackground(Color.WHITE);
		secondFitnessLevel.setBackground(Color.WHITE);
		thirdFitnessLevel.setBackground(Color.WHITE);
		presented.setBackground(Color.WHITE);
		fileGrade.setBackground(Color.WHITE);
		fileCentroAsociado.setBackground(Color.WHITE);
		fileExamTime.setBackground(Color.WHITE);
		fileCourse.setBackground(Color.WHITE);
		fileEnrolment.setBackground(Color.WHITE);
	}
	
	/**
	 * Desactiva el botón load
	 */
	public void disableLoadData() {
		btnLoad.setEnabled(false);
	}
	
	/**
	 * Activa el botón load
	 */
	public void enableLoadData() {
		btnLoad.setEnabled(true);
	}

}
