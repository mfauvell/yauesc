package es.uned.yauesc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.ScrollPaneConstants;

public class ObtainResultGui extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4350197841151246760L;
	
	private JComboBox<String> comboBoxGrade; 
	private JList<String> listCourse;
	private JScrollPane panelList;
	
	private JTextField path;
	private JTextField name;

	/**
	 * Create the panel.
	 */
	public ObtainResultGui() {
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
				//TODO
			}
		});
		panelNorthWest.add(btnReset);
		
		JButton btnDefault = new JButton("Default Values");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		panelNorthWest.add(btnDefault);
		
		JPanel panelNorthEast = new JPanel();
		panelNorth.add(panelNorthEast, BorderLayout.EAST);
		panelNorthEast.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		JButton btnLoad = new JButton("Export Data");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		panelNorthEast.add(btnLoad);
		
		JPanel panelMain = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMain.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panelMain);
		
		JPanel panelFileConfiguration = new JPanel();
		panelFileConfiguration.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "File output configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFileConfiguration.setPreferredSize(new Dimension(509, 110));
		panelMain.add(panelFileConfiguration);
		
		JLabel lblPath = new JLabel("Path");
		
		path = new JTextField();
		path.setColumns(10);
		
		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filePathChooser = new JFileChooser();
				filePathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValor = filePathChooser.showOpenDialog(new Frame());
				if (returnValor == JFileChooser.APPROVE_OPTION) {
					path.setText(filePathChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		button.setPreferredSize(new Dimension(87, 20));
		button.setMinimumSize(new Dimension(87, 20));
		button.setMaximumSize(new Dimension(87, 20));
		
		JLabel lblName = new JLabel("Name");
		
		name = new JTextField();
		name.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		
		ButtonGroup extensionGroup = new ButtonGroup();
		
		JRadioButton rdbtnCSV = new JRadioButton("CSV");
		rdbtnCSV.setSelected(true);
		extensionGroup.add(rdbtnCSV);
		
		JRadioButton rdbtnXML = new JRadioButton("XML");
		extensionGroup.add(rdbtnXML);
		
		JRadioButton rdbtnPDF = new JRadioButton("PDF");
		extensionGroup.add(rdbtnPDF);
		GroupLayout gl_panelFileConfiguration = new GroupLayout(panelFileConfiguration);
		gl_panelFileConfiguration.setHorizontalGroup(
			gl_panelFileConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFileConfiguration.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelFileConfiguration.createSequentialGroup()
							.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPath)
								.addComponent(lblName))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(name)
								.addComponent(path, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(77))
						.addGroup(gl_panelFileConfiguration.createSequentialGroup()
							.addComponent(lblType)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnXML)
						.addComponent(rdbtnPDF)
						.addComponent(rdbtnCSV))
					.addGap(574))
		);
		gl_panelFileConfiguration.setVerticalGroup(
			gl_panelFileConfiguration.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFileConfiguration.createSequentialGroup()
					.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFileConfiguration.createSequentialGroup()
							.addGap(15)
							.addComponent(rdbtnCSV)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnXML)
								.addComponent(lblType))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnPDF))
						.addGroup(gl_panelFileConfiguration.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(path, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPath))
							.addGap(18)
							.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName))))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		panelFileConfiguration.setLayout(gl_panelFileConfiguration);
		
		JPanel panelFilterGrade = new JPanel();
		panelFilterGrade.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter by Grade", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelFilterGrade.setPreferredSize(new Dimension(509, 110));
		panelMain.add(panelFilterGrade);
		
		JCheckBox chckbxGradeEnable = new JCheckBox("Enable");
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setEnabled(false);
		
		comboBoxGrade = new JComboBox<>();
		comboBoxGrade.setEnabled(false);
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
						.addComponent(chckbxGradeEnable))
					.addContainerGap())
		);
		gl_panelFilterGrade.setVerticalGroup(
			gl_panelFilterGrade.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterGrade.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxGradeEnable)
					.addGap(18)
					.addGroup(gl_panelFilterGrade.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGrade)
						.addComponent(comboBoxGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelFilterGrade.setLayout(gl_panelFilterGrade);
		
		JPanel panelFilterCourse = new JPanel();
		panelFilterCourse.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter by Course", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFilterCourse.setPreferredSize(new Dimension(509, 300));
		panelMain.add(panelFilterCourse);
		
		JCheckBox chckbxCourseEnable = new JCheckBox("Enable");
		
		panelList = new JScrollPane();
		panelList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listCourse = new JList<>();
		panelList.setViewportView(listCourse);
		listCourse.setEnabled(false);
		panelList.getVerticalScrollBar().setEnabled(false);
		panelList.getViewport().getView().setEnabled(false);
		panelList.setWheelScrollingEnabled(false);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setEnabled(false);
		GroupLayout gl_panelFilterCourse = new GroupLayout(panelFilterCourse);
		gl_panelFilterCourse.setHorizontalGroup(
			gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterCourse.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxCourseEnable)
						.addComponent(lblCourse)
						.addComponent(panelList, GroupLayout.PREFERRED_SIZE, 453, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panelFilterCourse.setVerticalGroup(
			gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterCourse.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxCourseEnable)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(lblCourse)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelList, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelFilterCourse.setLayout(gl_panelFilterCourse);
		
		chckbxGradeEnable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxGradeEnable.isSelected()) {
					chckbxCourseEnable.setSelected(false);
					lblGrade.setEnabled(true);
					comboBoxGrade.setEnabled(true);
				} else {
					lblGrade.setEnabled(false);
					comboBoxGrade.setEnabled(false);
				}
			}
		});
		
		chckbxCourseEnable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxCourseEnable.isSelected()) {
					chckbxGradeEnable.setSelected(false);
					lblCourse.setEnabled(true);
					listCourse.setEnabled(true);
					panelList.getVerticalScrollBar().setEnabled(true);
					panelList.getViewport().getView().setEnabled(true);
					panelList.setWheelScrollingEnabled(true);
				} else {
					lblCourse.setEnabled(false);
					listCourse.setEnabled(false);
					panelList.getVerticalScrollBar().setEnabled(false);
					panelList.getViewport().getView().setEnabled(false);
					panelList.setWheelScrollingEnabled(false);
				}
			}
		});
		
		//TO ERASE WHEN FUNCTIONALITY ARE MADE.
		rdbtnXML.setEnabled(false);
		rdbtnPDF.setEnabled(false);

	}
	
	public void preparePanel(List<String> gradeCodeList, List<String> courseCodeList) {
		gradeCodeList.stream().forEachOrdered(gradeCode -> comboBoxGrade.addItem(gradeCode));
		DefaultListModel<String> listModel = new DefaultListModel<>();
		courseCodeList.stream().forEachOrdered(courseCode -> listModel.addElement(courseCode));
		listCourse.setModel(listModel);
	}
}
