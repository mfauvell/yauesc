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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

import es.uned.yauesc.geneticAlgorithm.MutationOperatorType;
import es.uned.yauesc.geneticAlgorithm.ParentSelectorType;
import es.uned.yauesc.geneticAlgorithm.RecombinationOperatorType;
import es.uned.yauesc.geneticAlgorithm.SurvivorSelectorType;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class GeneticAlgorithmConfigGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9045072862616129261L;
	
	private JTextField generations;
	private JTextField generationsToMigrate;
	private JTextField numberMigrants;

	/**
	 * Create the panel.
	 */
	public GeneticAlgorithmConfigGui() {
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
		
		JButton btnSetConfig = new JButton("Set Config");
		btnSetConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		panelNorthEast.add(btnSetConfig);
		
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
		
		JRadioButton rdbtnSingle = new JRadioButton("Single");
		rdbtnSingle.setSelected(true);
		btnGrpType.add(rdbtnSingle);
		
		JRadioButton rdbtnParallel = new JRadioButton("Parallel");
		btnGrpType.add(rdbtnParallel);
		
		
		JLabel lblGenerations = new JLabel("Generations");
		generations = new JTextField();
		generations.setColumns(5);		
		
		JLabel lblGenerationsToMigrate = new JLabel("Generations to Migrate");
		generationsToMigrate = new JTextField();
		generationsToMigrate.setColumns(5);
		lblGenerationsToMigrate.setVisible(false);
		generationsToMigrate.setVisible(false);
	
		JLabel lblNumberMigrants = new JLabel("Number Migrants");
		numberMigrants = new JTextField();
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
		
		PanelAlgorithmSingle firstGeneticAlgorithmPanel = new PanelAlgorithmSingle("First genetic algorithm config");
		panelMain.add(firstGeneticAlgorithmPanel);
		
		PanelAlgorithmSingle secondGeneticAlgorithmPanel = new PanelAlgorithmSingle("Second genetic algorithm config");
		panelMain.add(secondGeneticAlgorithmPanel);
		secondGeneticAlgorithmPanel.setVisible(false);
		
		PanelAlgorithmSingle thirdGeneticAlgorithmPanel = new PanelAlgorithmSingle("Third genetic algorithm config");
		panelMain.add(thirdGeneticAlgorithmPanel);
		thirdGeneticAlgorithmPanel.setVisible(false);
		
		rdbtnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
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
		rdbtnParallel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
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
		
		
	}
	
	private class PanelAlgorithmSingle extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -5288395881259277815L;
		
		private JTextField sParameter;
		private JTextField probabilityRecombination;
		private JTextField probabilityMutation;
		private JTextField numberBattle;
		private JTextField survivors;
		private JTextField populatonSize;
		private JTextField populationMaxSize;
		private JTextField populationMinSize;
		
		public PanelAlgorithmSingle(String title) {
			setPreferredSize(new Dimension(1030, 160));
			setMaximumSize(new Dimension(1030, 160));
			
			setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
			setLayout(new GridLayout(3, 2, 0, 0));
			
			JPanel panelParentSelector = new JPanel();
			panelParentSelector.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			add(panelParentSelector);
			
			JLabel lblParentSelector = new JLabel("Parent Selector");
			
			JComboBox<ParentSelectorType> parentSelector = new JComboBox<>(ParentSelectorType.values());
			
			JLabel lblSParameter = new JLabel("S Parameter");
			sParameter = new JTextField();
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
			
			JComboBox<RecombinationOperatorType> recombinationOperator = new JComboBox<>(RecombinationOperatorType.values());
			
			JLabel lblRecombinationProbability = new JLabel("Probability");
			
			probabilityRecombination = new JTextField();
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
			
			JComboBox<MutationOperatorType> mutationOperator = new JComboBox<>(MutationOperatorType.values());
			
			JLabel lblMutationProbability = new JLabel("Probability");
			
			probabilityMutation = new JTextField();
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
			
			JComboBox<SurvivorSelectorType> survivorSelector = new JComboBox<>(SurvivorSelectorType.values());
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
			populatonSize.setColumns(10);
			
			JLabel lblPopulationMaxSize = new JLabel("Max");
			populationMaxSize = new JTextField();
			populationMaxSize.setColumns(10);
			lblPopulationMaxSize.setVisible(false);
			populationMaxSize.setVisible(false);
			
			JLabel lblPopulationMinSize = new JLabel("Min");
			populationMinSize = new JTextField();
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
	}
}
