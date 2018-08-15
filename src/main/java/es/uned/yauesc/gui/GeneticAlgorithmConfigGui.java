package es.uned.yauesc.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.Dimension;
import javax.swing.border.TitledBorder;

import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmDefaultConfig;
import es.uned.yauesc.geneticAlgorithm.IllegalParameterValueCheckedException;
import es.uned.yauesc.geneticAlgorithm.MutationOperatorType;
import es.uned.yauesc.geneticAlgorithm.ParentSelectorType;
import es.uned.yauesc.geneticAlgorithm.RecombinationOperatorType;
import es.uned.yauesc.geneticAlgorithm.SurvivorSelectorType;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

/**
 * Clase que encapsula la gui que permite al usuario configurar el algoritmo usado en la aplicación
 */
public class GeneticAlgorithmConfigGui extends JPanel {
	
	private static final long serialVersionUID = 9045072862616129261L;
	
	private JTextField generations;
	private JTextField generationsToMigrate;
	private JTextField numberMigrants;
	
	private JRadioButton rdbtnSingle;
	private JButton btnSetConfig;
	private PanelAlgorithmSingle firstGeneticAlgorithmPanel;
	private PanelAlgorithmSingle secondGeneticAlgorithmPanel;
	private PanelAlgorithmSingle thirdGeneticAlgorithmPanel;
	
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithmConfigGui.class.getName());

	/**
	 * Crea el panel que permite la configuración del algoritmo genético en la aplicación por parte del usuario
	 */
	public GeneticAlgorithmConfigGui(GeneticAlgorithmController geneticAlgorithmController, MainFrame mainFrame) {
		LOGGER.log(Level.INFO, "GeneticAlgorithmConfig gui created");
		
		setSize(new Dimension(1000, 600));
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
		
		btnSetConfig = new JButton("Set Config");
		panelNorthEast.add(btnSetConfig);

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
		panelMain.setMinimumSize(new Dimension(1030, 10));
		panelMain.setMaximumSize(new Dimension(1030, 32767));
		add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelBasicConfig = new JPanel();
		panelBasicConfig.setPreferredSize(new Dimension(1030, 70));
		panelBasicConfig.setMaximumSize(new Dimension(1030, 32767));
		panelBasicConfig.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Basic Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelMain.add(panelBasicConfig);
		
		JLabel lblType = new JLabel("Type");
		
		ButtonGroup btnGrpType = new ButtonGroup();
		
		rdbtnSingle = new JRadioButton("Single");
		rdbtnSingle.setSelected(true);
		btnGrpType.add(rdbtnSingle);
		
		JRadioButton rdbtnParallel = new JRadioButton("Parallel");
		btnGrpType.add(rdbtnParallel);
		
		
		JLabel lblGenerations = new JLabel("Generations");
		generations = new JTextField();
		generations.setToolTipText("Number of generations of genetic algorithm. Must be a positive intger withou zero");
		generations.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				generations.setBackground(Color.WHITE);
			}
		});
		generations.setColumns(5);		
		
		JLabel lblGenerationsToMigrate = new JLabel("Generations to Migrate");
		generationsToMigrate = new JTextField();
		generationsToMigrate.setToolTipText("Number of generations to make a migration. Must be a positive integer with zero");
		generationsToMigrate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				generationsToMigrate.setBackground(Color.WHITE);
			}
		});
		generationsToMigrate.setColumns(5);
		lblGenerationsToMigrate.setVisible(false);
		generationsToMigrate.setVisible(false);
	
		JLabel lblNumberMigrants = new JLabel("Number Migrants");
		numberMigrants = new JTextField();
		numberMigrants.setToolTipText("Number of individuals migrants in each migration. Must be a positive integer with zero");
		numberMigrants.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				numberMigrants.setBackground(Color.WHITE);
			}
		});
		numberMigrants.setColumns(5);
		lblNumberMigrants.setVisible(false);
		numberMigrants.setVisible(false);
		GroupLayout gl_panelBasicConfig = new GroupLayout(panelBasicConfig);
		gl_panelBasicConfig.setHorizontalGroup(
			gl_panelBasicConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBasicConfig.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblType)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBasicConfig.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnSingle)
						.addComponent(rdbtnParallel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGenerations)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(generations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGenerationsToMigrate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(generationsToMigrate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNumberMigrants)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numberMigrants, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelBasicConfig.setVerticalGroup(
			gl_panelBasicConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBasicConfig.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblType))
				.addGroup(gl_panelBasicConfig.createSequentialGroup()
					.addComponent(rdbtnSingle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnParallel))
				.addGroup(gl_panelBasicConfig.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBasicConfig.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGenerations)
						.addComponent(generations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGenerationsToMigrate)
						.addComponent(generationsToMigrate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumberMigrants)
						.addComponent(numberMigrants, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panelBasicConfig.setLayout(gl_panelBasicConfig);
		
		firstGeneticAlgorithmPanel = new PanelAlgorithmSingle("First genetic algorithm config");
		panelMain.add(firstGeneticAlgorithmPanel);
		
		secondGeneticAlgorithmPanel = new PanelAlgorithmSingle("Second genetic algorithm config");
		panelMain.add(secondGeneticAlgorithmPanel);
		secondGeneticAlgorithmPanel.setVisible(false);
		
		thirdGeneticAlgorithmPanel = new PanelAlgorithmSingle("Third genetic algorithm config");
		panelMain.add(thirdGeneticAlgorithmPanel);
		thirdGeneticAlgorithmPanel.setVisible(false);
		
		rdbtnSingle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnSingle.isSelected()) {
					lblGenerationsToMigrate.setVisible(false);
					generationsToMigrate.setVisible(false);
					lblNumberMigrants.setVisible(false);
					numberMigrants.setVisible(false);
					secondGeneticAlgorithmPanel.setVisible(false);
					thirdGeneticAlgorithmPanel.setVisible(false);
				} else {
					lblGenerationsToMigrate.setVisible(true);
					generationsToMigrate.setVisible(true);
					lblNumberMigrants.setVisible(true);
					numberMigrants.setVisible(true);
					secondGeneticAlgorithmPanel.setVisible(true);
					thirdGeneticAlgorithmPanel.setVisible(true);
				}
			}
		});
		rdbtnParallel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnParallel.isSelected()) {
					lblGenerationsToMigrate.setVisible(true);
					generationsToMigrate.setVisible(true);
					lblNumberMigrants.setVisible(true);
					numberMigrants.setVisible(true);
					secondGeneticAlgorithmPanel.setVisible(true);
					thirdGeneticAlgorithmPanel.setVisible(true);
				} else {
					lblGenerationsToMigrate.setVisible(false);
					generationsToMigrate.setVisible(false);
					lblNumberMigrants.setVisible(false);
					numberMigrants.setVisible(false);
					secondGeneticAlgorithmPanel.setVisible(false);
					thirdGeneticAlgorithmPanel.setVisible(false);
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetBackground();
				if (GeneticAlgorithmDefaultConfig.isParallel) {
					rdbtnParallel.setSelected(true);
				} else {
					rdbtnSingle.setSelected(false);
				}
				generations.setText("" + GeneticAlgorithmDefaultConfig.generations);
				generationsToMigrate.setText("" + GeneticAlgorithmDefaultConfig.generationsToMigrate);
				numberMigrants.setText("" + GeneticAlgorithmDefaultConfig.numberMigrants);
				firstGeneticAlgorithmPanel.setDefaultValues(GeneticAlgorithmDefaultConfig.parentSelectorFirst,
						GeneticAlgorithmDefaultConfig.sParameterFirst, GeneticAlgorithmDefaultConfig.recombinationOperatorFirst,
						GeneticAlgorithmDefaultConfig.probabilityRecombinationFirst, GeneticAlgorithmDefaultConfig.mutationOperatorFirst,
						GeneticAlgorithmDefaultConfig.probabilityMutationFirst, GeneticAlgorithmDefaultConfig.survivorSelectorFirst,
						GeneticAlgorithmDefaultConfig.survivorsFirst, GeneticAlgorithmDefaultConfig.numberBattleFirst, 
						GeneticAlgorithmDefaultConfig.populationSizeFirst, GeneticAlgorithmDefaultConfig.maxPopulationFirst, 
						GeneticAlgorithmDefaultConfig.minPopulationFirst);
				secondGeneticAlgorithmPanel.setDefaultValues(GeneticAlgorithmDefaultConfig.parentSelectorSecond,
						GeneticAlgorithmDefaultConfig.sParameterSecond, GeneticAlgorithmDefaultConfig.recombinationOperatorSecond,
						GeneticAlgorithmDefaultConfig.probabilityRecombinationSecond, GeneticAlgorithmDefaultConfig.mutationOperatorSecond,
						GeneticAlgorithmDefaultConfig.probabilityMutationSecond, GeneticAlgorithmDefaultConfig.survivorSelectorSecond,
						GeneticAlgorithmDefaultConfig.survivorsSecond, GeneticAlgorithmDefaultConfig.numberBattleSecond, 
						GeneticAlgorithmDefaultConfig.populationSizeSecond, GeneticAlgorithmDefaultConfig.maxPopulationSecond, 
						GeneticAlgorithmDefaultConfig.minPopulationSecond);
				thirdGeneticAlgorithmPanel.setDefaultValues(GeneticAlgorithmDefaultConfig.parentSelectorThird,
						GeneticAlgorithmDefaultConfig.sParameterThird, GeneticAlgorithmDefaultConfig.recombinationOperatorThird,
						GeneticAlgorithmDefaultConfig.probabilityRecombinationThird, GeneticAlgorithmDefaultConfig.mutationOperatorThird,
						GeneticAlgorithmDefaultConfig.probabilityMutationThird, GeneticAlgorithmDefaultConfig.survivorSelectorThird,
						GeneticAlgorithmDefaultConfig.survivorsThird, GeneticAlgorithmDefaultConfig.numberBattleThird, 
						GeneticAlgorithmDefaultConfig.populationSizeThird, GeneticAlgorithmDefaultConfig.maxPopulationThird, 
						GeneticAlgorithmDefaultConfig.minPopulationThird);
				LOGGER.log(Level.INFO, "Loaded default values");
			}
		});
		btnSetConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				working.setVisible(true);
				working.repaint();
				
				SwingWorker<Boolean, Object> worker = new SwingWorker<Boolean, Object>() {
					@Override
					protected Boolean doInBackground() throws Exception {
						mainFrame.resetFromGeneticAlgorithmConfig();
						boolean parallel = rdbtnParallel.isSelected();
						int generationsNumber = 0;
						try {
							generationsNumber = Integer.parseInt(generations.getText());
							if (generationsNumber <= 0) {
								LOGGER.log(Level.WARNING, "Generations value: " + generations.getText() + " is not correct");
								generations.setBackground(Color.RED);
								return false;
							}
						} catch (Exception e) {
							LOGGER.log(Level.WARNING, "Generations value: " + generations.getText() + " is not correct");
							generations.setBackground(Color.RED);
							return false;
						}
						geneticAlgorithmController.setBasicOptions(generationsNumber, parallel);
						try {
							geneticAlgorithmController.setFirstGeneticAlgorithmSingle(firstGeneticAlgorithmPanel.getPopulationConfig(), firstGeneticAlgorithmPanel.getParentSelectorConfig(),
							firstGeneticAlgorithmPanel.getRecombinationOperatorConfig(), firstGeneticAlgorithmPanel.getMutationOperatorConfig(), firstGeneticAlgorithmPanel.getSurvivorSelectorConfig());
							if (parallel) {
								geneticAlgorithmController.setSecondGeneticAlgorithmSingle(secondGeneticAlgorithmPanel.getPopulationConfig(), secondGeneticAlgorithmPanel.getParentSelectorConfig(),
										secondGeneticAlgorithmPanel.getRecombinationOperatorConfig(), secondGeneticAlgorithmPanel.getMutationOperatorConfig(), secondGeneticAlgorithmPanel.getSurvivorSelectorConfig());
								geneticAlgorithmController.setThirdGeneticAlgorithmSingle(thirdGeneticAlgorithmPanel.getPopulationConfig(), thirdGeneticAlgorithmPanel.getParentSelectorConfig(),
										thirdGeneticAlgorithmPanel.getRecombinationOperatorConfig(), thirdGeneticAlgorithmPanel.getMutationOperatorConfig(), thirdGeneticAlgorithmPanel.getSurvivorSelectorConfig());
							}
						} catch (IllegalParameterValueCheckedException | IllegalArgumentException e1) {
							LOGGER.log(Level.WARNING, "Genetic algorithm was not created");
							return false;
						}
						if (parallel) {
							int generationsToMigrateValor = 0;
							try {
								generationsToMigrateValor = Integer.parseInt(generationsToMigrate.getText());
								if (generationsToMigrateValor <= 0) {
									LOGGER.log(Level.WARNING, "Generations to migrate value: " + generationsToMigrate.getText() + " is not correct");
									generationsToMigrate.setBackground(Color.RED);
									return false;
								}
							} catch (Exception e) {
								LOGGER.log(Level.WARNING, "Generations to migrate value: " + generationsToMigrate.getText() + " is not correct");
								generationsToMigrate.setBackground(Color.RED);
								return false;
							}
							int numberMigrantsValor = 0;
							try {
								numberMigrantsValor = Integer.parseInt(numberMigrants.getText());
								if (numberMigrantsValor <= 0) {
									LOGGER.log(Level.WARNING, "Number of migrants value: " + numberMigrants.getText() + " is not correct");
									numberMigrants.setBackground(Color.RED);
									return false;
								}
							} catch (Exception e) {
								LOGGER.log(Level.WARNING, "Number of migrants value: " + numberMigrants.getText() + " is not correct");
								numberMigrants.setBackground(Color.RED);
								return false;
							}
							geneticAlgorithmController.setMainGeneticAlgorithm(generationsToMigrateValor, numberMigrantsValor);
						} else {
							geneticAlgorithmController.setMainGeneticAlgorithm();
						}
						
						mainFrame.setGeneticAlgorithmExecutionTab();
						
						LOGGER.log(Level.INFO, "Genetic Algorithm created succesfully");
						
						return true;
					}
					
					@Override
					protected void done() {
						working.setVisible(false);
					}
				};
				worker.execute();
			}
		});
	}
	
	/**
	 * Inicializa el panel
	 */
	public void initialize() {
		LOGGER.log(Level.INFO, "Initializate Panel");
		reset();
	}
	
	private void reset() {
		resetBackground();
		rdbtnSingle.setSelected(true);
		generations.setText("");
		generationsToMigrate.setText("");
		numberMigrants.setText("");
		firstGeneticAlgorithmPanel.resetValues();
		secondGeneticAlgorithmPanel.resetValues();
		thirdGeneticAlgorithmPanel.resetValues();
		LOGGER.log(Level.INFO, "Reset values");
	}
	
	private void resetBackground() {
		generations.setBackground(Color.WHITE);
		generationsToMigrate.setBackground(Color.WHITE);
		numberMigrants.setBackground(Color.WHITE);
	}
	
	/**
	 * Desactiva el botón Set Config
	 */
	public void disableSetConfig() {
		btnSetConfig.setEnabled(false);
	}
	
	/**
	 * Activa el botón Set Config
	 */
	public void enableSetConfig() {
		btnSetConfig.setEnabled(true);
	}
	
	private class PanelAlgorithmSingle extends JPanel {
		
		private static final long serialVersionUID = -5288395881259277815L;
		
		private JTextField sParameter;
		private JTextField probabilityRecombination;
		private JTextField probabilityMutation;
		private JTextField numberBattle;
		private JTextField survivors;
		private JTextField populatonSize;
		private JTextField populationMaxSize;
		private JTextField populationMinSize;
		private JComboBox<ParentSelectorType> parentSelector;
		private JComboBox<RecombinationOperatorType> recombinationOperator;
		private JComboBox<MutationOperatorType> mutationOperator;
		private JComboBox<SurvivorSelectorType> survivorSelector;
		
		
		public PanelAlgorithmSingle(String title) {
			setPreferredSize(new Dimension(1030, 160));
			setMaximumSize(new Dimension(1030, 160));
			
			setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
			setLayout(new GridLayout(3, 2, 0, 0));
			
			JPanel panelParentSelector = new JPanel();
			panelParentSelector.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			add(panelParentSelector);
			
			JLabel lblParentSelector = new JLabel("Parent Selector");
			
			parentSelector = new JComboBox<>(ParentSelectorType.values());
			
			JLabel lblSParameter = new JLabel("S Parameter");
			sParameter = new JTextField();
			sParameter.setToolTipText("S parameter to ranking parent selector. Must be in interval: 1 < x <= 2");
			sParameter.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					sParameter.setBackground(Color.WHITE);
				}
			});
			sParameter.setColumns(5);
			lblSParameter.setVisible(true);
			sParameter.setVisible(true);
			GroupLayout gl_panelParentSelector = new GroupLayout(panelParentSelector);
			gl_panelParentSelector.setHorizontalGroup(
				gl_panelParentSelector.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelParentSelector.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblParentSelector)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(parentSelector, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblSParameter)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(sParameter, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(92, Short.MAX_VALUE))
			);
			gl_panelParentSelector.setVerticalGroup(
				gl_panelParentSelector.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelParentSelector.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelParentSelector.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblParentSelector)
							.addComponent(parentSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSParameter)
							.addComponent(sParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panelParentSelector.setLayout(gl_panelParentSelector);
			
			JPanel panelRecombinationOperator = new JPanel();
			panelRecombinationOperator.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			add(panelRecombinationOperator);
			
			JLabel lblRecombinationOperator = new JLabel("Recombination Operator");
			
			recombinationOperator = new JComboBox<>(RecombinationOperatorType.values());
			
			JLabel lblRecombinationProbability = new JLabel("Probability");
			
			probabilityRecombination = new JTextField();
			probabilityRecombination.setToolTipText("The probability of recombination. Must be between 0 and 1");
			probabilityRecombination.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					probabilityRecombination.setBackground(Color.WHITE);
				}
			});
			probabilityRecombination.setColumns(10);
			GroupLayout gl_panelRecombinationOperator = new GroupLayout(panelRecombinationOperator);
			gl_panelRecombinationOperator.setHorizontalGroup(
				gl_panelRecombinationOperator.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelRecombinationOperator.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblRecombinationOperator)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(recombinationOperator, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblRecombinationProbability)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(probabilityRecombination, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_panelRecombinationOperator.setVerticalGroup(
				gl_panelRecombinationOperator.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelRecombinationOperator.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panelRecombinationOperator.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblRecombinationOperator)
							.addComponent(recombinationOperator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblRecombinationProbability)
							.addComponent(probabilityRecombination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panelRecombinationOperator.setLayout(gl_panelRecombinationOperator);
			
			JPanel panelMutationOperator = new JPanel();
			panelMutationOperator.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			add(panelMutationOperator);
			
			JLabel lblMutationOperator = new JLabel("Mutation Operator");
			
			mutationOperator = new JComboBox<>(MutationOperatorType.values());
			
			JLabel lblMutationProbability = new JLabel("Probability");
			
			probabilityMutation = new JTextField();
			probabilityMutation.setToolTipText("The probability of mutation. Must be between 0 and 1");
			probabilityMutation.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					probabilityMutation.setBackground(Color.WHITE);
				}
			});
			probabilityMutation.setColumns(10);
			GroupLayout gl_panelMutationOperator = new GroupLayout(panelMutationOperator);
			gl_panelMutationOperator.setHorizontalGroup(
				gl_panelMutationOperator.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelMutationOperator.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblMutationOperator)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(mutationOperator, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblMutationProbability)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(probabilityMutation, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(49, Short.MAX_VALUE))
			);
			gl_panelMutationOperator.setVerticalGroup(
				gl_panelMutationOperator.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelMutationOperator.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panelMutationOperator.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMutationOperator)
							.addComponent(mutationOperator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMutationProbability)
							.addComponent(probabilityMutation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panelMutationOperator.setLayout(gl_panelMutationOperator);
			
			JPanel panelSurvivorSelector = new JPanel();
			panelSurvivorSelector.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			add(panelSurvivorSelector);
			panelSurvivorSelector.setLayout(new BorderLayout(0, 0));
			
			JPanel panelSurvivorSelectorCenter = new JPanel();
			panelSurvivorSelector.add(panelSurvivorSelectorCenter, BorderLayout.CENTER);
			
			JLabel lblSurvivorSelector = new JLabel("Survivor Selector");
			
			survivorSelector = new JComboBox<>(SurvivorSelectorType.values());
			GroupLayout gl_panelSurvivorSelectorCenter = new GroupLayout(panelSurvivorSelectorCenter);
			gl_panelSurvivorSelectorCenter.setHorizontalGroup(
				gl_panelSurvivorSelectorCenter.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelSurvivorSelectorCenter.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblSurvivorSelector)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(survivorSelector, 0, 141, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_panelSurvivorSelectorCenter.setVerticalGroup(
				gl_panelSurvivorSelectorCenter.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panelSurvivorSelectorCenter.createSequentialGroup()
						.addContainerGap(18, Short.MAX_VALUE)
						.addGroup(gl_panelSurvivorSelectorCenter.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSurvivorSelector)
							.addComponent(survivorSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panelSurvivorSelectorCenter.setLayout(gl_panelSurvivorSelectorCenter);
			
			JPanel panelSurvivorSelectorEast = new JPanel();
			panelSurvivorSelectorEast.setPreferredSize(new Dimension(200, 10));
			panelSurvivorSelectorEast.setMaximumSize(new Dimension(200, 32767));
			panelSurvivorSelector.add(panelSurvivorSelectorEast, BorderLayout.EAST);
			panelSurvivorSelectorEast.setLayout(new CardLayout(0, 0));
			
			JPanel panelAged = new JPanel();
			panelSurvivorSelectorEast.add(panelAged);
			
			JPanel panelRounRobin = new JPanel();
			panelSurvivorSelectorEast.add(panelRounRobin, "name_25156092643474");
			
			JLabel lblNumberBattle = new JLabel("Number Battle");
			
			numberBattle = new JTextField();
			numberBattle.setToolTipText("Number of battles in RoundRobin selector. Must be a positive integer without zero.");
			numberBattle.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					numberBattle.setBackground(Color.WHITE);
				}
			});
			numberBattle.setColumns(10);
			GroupLayout gl_panelRounRobin = new GroupLayout(panelRounRobin);
			gl_panelRounRobin.setHorizontalGroup(
				gl_panelRounRobin.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelRounRobin.createSequentialGroup()
						.addComponent(lblNumberBattle)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(numberBattle, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(69, Short.MAX_VALUE))
			);
			gl_panelRounRobin.setVerticalGroup(
				gl_panelRounRobin.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panelRounRobin.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panelRounRobin.createParallelGroup(Alignment.TRAILING)
							.addComponent(numberBattle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNumberBattle))
						.addContainerGap())
			);
			panelRounRobin.setLayout(gl_panelRounRobin);
			
			JPanel panelSteadyState = new JPanel();
			panelSurvivorSelectorEast.add(panelSteadyState, "name_25170290611662");
			
			JLabel lblSurvivor = new JLabel("Survivors");
			
			survivors = new JTextField();
			survivors.setToolTipText("Number of best individuals survived to next generations. Must be a positive integer or zero.");
			survivors.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					survivors.setBackground(Color.WHITE);
				}
			});
			survivors.setColumns(10);
			GroupLayout gl_panelSteadyState = new GroupLayout(panelSteadyState);
			gl_panelSteadyState.setHorizontalGroup(
				gl_panelSteadyState.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelSteadyState.createSequentialGroup()
						.addComponent(lblSurvivor)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(survivors, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(74, Short.MAX_VALUE))
			);
			gl_panelSteadyState.setVerticalGroup(
				gl_panelSteadyState.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panelSteadyState.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panelSteadyState.createParallelGroup(Alignment.TRAILING)
							.addComponent(survivors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSurvivor))
						.addContainerGap())
			);
			panelSteadyState.setLayout(gl_panelSteadyState);
			
			panelAged.setVisible(false);
			panelRounRobin.setVisible(false);
			panelSteadyState.setVisible(true);
			
			JPanel panelPopulation = new JPanel();
			panelPopulation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			add(panelPopulation);
			
			JLabel lblPopulation = new JLabel("Population:");
			
			JLabel lblPopulationSize = new JLabel("Size");
			
			populatonSize = new JTextField();
			populatonSize.setToolTipText("Size of population. Must be a positive integer without zero");
			populatonSize.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					populatonSize.setBackground(Color.WHITE);
				}
			});
			populatonSize.setColumns(10);
			
			JLabel lblPopulationMaxSize = new JLabel("Max");
			populationMaxSize = new JTextField();
			populationMaxSize.setToolTipText("Max size of population. Must be a positive integer without zero but it must be greater or equal than size.");
			populationMaxSize.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					populationMaxSize.setBackground(Color.WHITE);
				}
			});
			populationMaxSize.setColumns(10);					
			lblPopulationMaxSize.setVisible(false);
			populationMaxSize.setVisible(false);
			
			JLabel lblPopulationMinSize = new JLabel("Min");
			populationMinSize = new JTextField();
			populationMinSize.setToolTipText("Min size of population. Must be a positive integer without zero but it must be lower or equal than size.");
			populationMinSize.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					populationMinSize.setBackground(Color.WHITE);
				}
			});
			populationMinSize.setColumns(10);
			lblPopulationMinSize.setVisible(false);
			populationMinSize.setVisible(false);
			GroupLayout gl_panelPopulation = new GroupLayout(panelPopulation);
			gl_panelPopulation.setHorizontalGroup(
				gl_panelPopulation.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelPopulation.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblPopulation)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblPopulationSize)
						.addGap(4)
						.addComponent(populatonSize, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addGap(3)
						.addComponent(lblPopulationMaxSize)
						.addGap(4)
						.addComponent(populationMaxSize, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addGap(4)
						.addComponent(lblPopulationMinSize)
						.addGap(1)
						.addComponent(populationMinSize, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(89, Short.MAX_VALUE))
			);
			gl_panelPopulation.setVerticalGroup(
				gl_panelPopulation.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panelPopulation.createSequentialGroup()
						.addContainerGap(14, Short.MAX_VALUE)
						.addGroup(gl_panelPopulation.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPopulation)
							.addComponent(lblPopulationSize)
							.addComponent(populatonSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPopulationMaxSize)
							.addComponent(populationMaxSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPopulationMinSize)
							.addComponent(populationMinSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panelPopulation.setLayout(gl_panelPopulation);
			
			parentSelector.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (parentSelector.getSelectedItem().equals(ParentSelectorType.Ranking)) {
						lblSParameter.setVisible(true);
						sParameter.setVisible(true);
					} else {
						lblSParameter.setVisible(false);
						sParameter.setVisible(false);
					}
				}
			});
			survivorSelector.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					SurvivorSelectorType itemSelected = (SurvivorSelectorType) survivorSelector.getSelectedItem();
					if (itemSelected.equals(SurvivorSelectorType.AgeBased)) {
						panelAged.setVisible(true);
						panelRounRobin.setVisible(false);
						panelSteadyState.setVisible(false);
						lblPopulationMaxSize.setVisible(true);
						populationMaxSize.setVisible(true);
						lblPopulationMinSize.setVisible(true);
						populationMinSize.setVisible(true);
					} else if (itemSelected.equals(SurvivorSelectorType.RoundRobin)) {
						panelAged.setVisible(false);
						panelRounRobin.setVisible(true);
						panelSteadyState.setVisible(false);
						lblPopulationMaxSize.setVisible(false);
						populationMaxSize.setVisible(false);
						lblPopulationMinSize.setVisible(false);
						populationMinSize.setVisible(false);
					} else {
						panelAged.setVisible(false);
						panelRounRobin.setVisible(false);
						panelSteadyState.setVisible(true);
						lblPopulationMaxSize.setVisible(false);
						populationMaxSize.setVisible(false);
						lblPopulationMinSize.setVisible(false);
						populationMinSize.setVisible(false);
					}
				}
			});
		}
		
		public void resetValues() {
			resetBackground();
			sParameter.setText("");
			probabilityRecombination.setText("");
			probabilityMutation.setText("");
			numberBattle.setText("");
			survivors.setText("");
			populatonSize.setText("");
			populationMaxSize.setText("");
			populationMinSize.setText("");
			parentSelector.setSelectedIndex(0);
			recombinationOperator.setSelectedIndex(0);;
			mutationOperator.setSelectedIndex(0);
			survivorSelector.setSelectedIndex(0);
		}
		
		public void setDefaultValues(ParentSelectorType parentSelector, double sParameter, RecombinationOperatorType recombinationOperator,
				double probabilityRecombination, MutationOperatorType mutationOperator, double probabilityMutation,
				SurvivorSelectorType survivorSelector, int survivors, int numberBattle, int populationSize, 
				int maxPopulation, int minPopulation) {
			resetBackground();
			this.sParameter.setText("" + sParameter);
			this.probabilityRecombination.setText("" + probabilityRecombination);
			this.probabilityMutation.setText("" + probabilityMutation);
			this.numberBattle.setText("" + numberBattle);
			this.survivors.setText("" + survivors);
			populatonSize.setText("" + populationSize);
			populationMaxSize.setText("" + maxPopulation);
			populationMinSize.setText("" + minPopulation);
			this.parentSelector.setSelectedIndex(parentSelector.ordinal());
			this.recombinationOperator.setSelectedIndex(recombinationOperator.ordinal());;
			this.mutationOperator.setSelectedIndex(mutationOperator.ordinal());
			this.survivorSelector.setSelectedIndex(survivorSelector.ordinal());
		}
		
		public int[] getPopulationConfig() {
			if (survivorSelector.getSelectedItem().equals(SurvivorSelectorType.AgeBased)) {
				int maxSize = 0;
				try {
					maxSize = Integer.parseInt(populationMaxSize.getText());
					if (maxSize <= 0) {
						LOGGER.log(Level.WARNING, "Max Population size: " + populationMaxSize.getText() + " is not correct");
						populationMaxSize.setBackground(Color.RED);
						throw new IllegalArgumentException("Max size is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "Max Population size: " + populationMaxSize.getText() + " is not correct");
					populationMaxSize.setBackground(Color.RED);
					throw new IllegalArgumentException("Max size is not valid");
				}
				int minSize = 0;
				try {
					minSize = Integer.parseInt(populationMinSize.getText());
					if (minSize <= 0) {
						LOGGER.log(Level.WARNING, "Min Population size: " + populationMinSize.getText() + " is not correct");
						populationMinSize.setBackground(Color.RED);
						throw new IllegalArgumentException("Min size is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "Min Population size: " + populationMinSize.getText() + " is not correct");
					populationMinSize.setBackground(Color.RED);
					throw new IllegalArgumentException("Min size is not valid");
				}
				int size = 0;
				try {
					size = Integer.parseInt(populatonSize.getText());
					if (size <= 0) {
						LOGGER.log(Level.WARNING, "Population size: " + populatonSize.getText() + " is not correct");
						populatonSize.setBackground(Color.RED);
						throw new IllegalArgumentException("Size is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "Population size: " + populatonSize.getText() + " is not correct");
					populatonSize.setBackground(Color.RED);
					throw new IllegalArgumentException("Size is not valid");
				}
				if (maxSize < size || maxSize < minSize) {
					LOGGER.log(Level.WARNING, "Max Population size: " + populationMaxSize.getText() + " is not correct");
					populationMaxSize.setBackground(Color.RED);
					throw new IllegalArgumentException("Max size is not valid");
				}
				if (minSize > size) {
					LOGGER.log(Level.WARNING, "Min Population size: " + populationMinSize.getText() + " is not correct");
					populationMinSize.setBackground(Color.RED);
					throw new IllegalArgumentException("Min size is not valid");
				}
				int[] result = {maxSize, minSize,size};
				return result;
			} else {
				int size = 0;
				try {
					size = Integer.parseInt(populatonSize.getText());
					if (size <= 0) {
						LOGGER.log(Level.WARNING, "Population size: " + populatonSize.getText() + " is not correct");
						populatonSize.setBackground(Color.RED);
						throw new IllegalArgumentException("Size is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "Population size: " + populatonSize.getText() + " is not correct");
					populatonSize.setBackground(Color.RED);
					throw new IllegalArgumentException("Size is not valid");
				}
				int[] result = {size};
				return result;
			}
		}
		
		public Object[] getParentSelectorConfig() {
			Object[] result = new Object[2];
			result[0] = parentSelector.getSelectedItem();
			if (parentSelector.getSelectedItem().equals(ParentSelectorType.Ranking)) {
				double sParameterValor = 0.0;
				try {
					sParameterValor = Double.parseDouble(sParameter.getText());
					if (sParameterValor <= 1 || sParameterValor > 2 ) {
						LOGGER.log(Level.WARNING, "S parameter value: " + sParameter.getText() + " is not correct");
						sParameter.setBackground(Color.RED);
						throw new IllegalArgumentException("sParameter is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "S parameter value: " + sParameter.getText() + " is not correct");
					sParameter.setBackground(Color.RED);
					throw new IllegalArgumentException("sParameter is not valid");
				}
				result[1] = sParameterValor;
			}
			return result;
		}
		
		public Object[] getRecombinationOperatorConfig() {
			Object[] result = new Object[2];
			result[0] = recombinationOperator.getSelectedItem();
			double probability = 0.0;
			try {
				probability = Double.parseDouble(probabilityRecombination.getText());
				if ( probability < 0 || probability > 1) {
					LOGGER.log(Level.WARNING, "Recombination probability value: " + probabilityRecombination.getText() + " is not correct");
					probabilityRecombination.setBackground(Color.RED);
					throw new IllegalArgumentException("Probability Recombination is not valid");
				}
			} catch (NumberFormatException e) {
				LOGGER.log(Level.WARNING, "Recombination probability value: " + probabilityRecombination.getText() + " is not correct");
				probabilityRecombination.setBackground(Color.RED);
				throw new IllegalArgumentException("Probability Recombination is not valid");
			}
			result[1] = probability;
			return result;
		}
		
		public Object[] getMutationOperatorConfig() {
			Object[] result = new Object[2];
			result[0] = mutationOperator.getSelectedItem();
			double probability = 0.0;
			try {
				probability = Double.parseDouble(probabilityMutation.getText());
				if ( probability < 0 || probability > 1) {
					LOGGER.log(Level.WARNING, "Mutation probability value: " + probabilityMutation.getText() + " is not correct");
					probabilityMutation.setBackground(Color.RED);
					throw new IllegalArgumentException("Probability Mutation is not valid");
				}
			} catch (NumberFormatException e) {
				LOGGER.log(Level.WARNING, "Mutation probability value: " + probabilityMutation.getText() + " is not correct");
				probabilityMutation.setBackground(Color.RED);
				throw new IllegalArgumentException("Probability Mutation is not valid");
			}
			result[1] = probability;
			return result;
		}
		
		public Object[] getSurvivorSelectorConfig() {
			Object[] result = new Object[2];
			result[0] = survivorSelector.getSelectedItem();
			if (survivorSelector.getSelectedItem().equals(SurvivorSelectorType.SteadyState)) {
				int survivorsValor = 0;
				try {
					survivorsValor = Integer.parseInt(survivors.getText());
					if (survivorsValor < 0) {
						LOGGER.log(Level.WARNING, "Survivors value: " + survivors.getText() + " is not correct");
						survivors.setBackground(Color.RED);
						throw new IllegalArgumentException("Survivors number is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "Survivors value: " + survivors.getText() + " is not correct");
					survivors.setBackground(Color.RED);
					throw new IllegalArgumentException("Survivors number is not valid");
				}
				result[1] = survivorsValor;
			} else if (survivorSelector.getSelectedItem().equals(SurvivorSelectorType.RoundRobin)) {
				int numberBattleValor = 0;
				try {
					numberBattleValor = Integer.parseInt(numberBattle.getText());
					if (numberBattleValor < 0) {
						LOGGER.log(Level.WARNING, "Number battles value: " + numberBattle.getText() + " is not correct");
						numberBattle.setBackground(Color.RED);
						throw new IllegalArgumentException("Battle number is not valid");
					}
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, "Number battles value: " + numberBattle.getText() + " is not correct");
					numberBattle.setBackground(Color.RED);
					throw new IllegalArgumentException("Battle number is not valid");
				}
				result[1] = numberBattleValor;
			}
			return result;
		}
		
		private void resetBackground() {
			populatonSize.setBackground(Color.WHITE);
			populationMaxSize.setBackground(Color.WHITE);
			populationMinSize.setBackground(Color.WHITE);
			sParameter.setBackground(Color.WHITE);
			probabilityRecombination.setBackground(Color.WHITE);
			probabilityMutation.setBackground(Color.WHITE);
			survivors.setBackground(Color.WHITE);
			numberBattle.setBackground(Color.WHITE);
		}
	}
}
