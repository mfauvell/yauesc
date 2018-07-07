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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ObtainResultGui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

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
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button = new JButton("Browse");
		button.setPreferredSize(new Dimension(87, 20));
		button.setMinimumSize(new Dimension(87, 20));
		button.setMaximumSize(new Dimension(87, 20));
		
		JLabel lblName = new JLabel("Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		
		JRadioButton rdbtnCSV = new JRadioButton("CSV");
		
		JRadioButton rdbtnXML = new JRadioButton("XML");
		
		JRadioButton rdbtnPDF = new JRadioButton("PDF");
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
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
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
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPath))
							.addGap(18)
							.addGroup(gl_panelFileConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
		
		JComboBox comboBoxGrade = new JComboBox();
		GroupLayout gl_panelFilterGrade = new GroupLayout(panelFilterGrade);
		gl_panelFilterGrade.setHorizontalGroup(
			gl_panelFilterGrade.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterGrade.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panelFilterGrade.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFilterGrade.createSequentialGroup()
							.addComponent(lblGrade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxGrade, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
						.addComponent(chckbxGradeEnable))
					.addContainerGap(130, Short.MAX_VALUE))
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
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panelFilterGrade.setLayout(gl_panelFilterGrade);
		
		JPanel panelFilterCourse = new JPanel();
		panelFilterCourse.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter by Course", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFilterCourse.setPreferredSize(new Dimension(509, 300));
		panelMain.add(panelFilterCourse);
		
		JCheckBox chckbxCourseEnable = new JCheckBox("Enable");
		
		JList list = new JList();
		list.setPreferredSize(new Dimension(100, 100));
		list.setSize(new Dimension(100, 100));
		
		JLabel lblCourse = new JLabel("Course:");
		GroupLayout gl_panelFilterCourse = new GroupLayout(panelFilterCourse);
		gl_panelFilterCourse.setHorizontalGroup(
			gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFilterCourse.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilterCourse.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxCourseEnable)
						.addComponent(lblCourse)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 453, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelFilterCourse.setLayout(gl_panelFilterCourse);

	}
}
