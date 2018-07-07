package es.uned.yauesc.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PanelGeneticAlgorithmSingle extends JPanel {
	private JTextField textField;
	private JTextField probabilityRecombination;
	private JTextField probabilityMutation;
	private JTextField numberBattle;
	private JTextField survivors;
	private JTextField populatonSize;
	private JTextField populationMaxSize;
	private JTextField populationMinSize;
	private JLabel lblPopulationMaxSize;

	/**
	 * Create the panel.
	 */
	public PanelGeneticAlgorithmSingle() {
		setPreferredSize(new Dimension(1030, 160));
		setMaximumSize(new Dimension(1030, 160));
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Genetic Algorithm First", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panelParentSelector = new JPanel();
		panelParentSelector.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelParentSelector);
		
		JLabel lblParentSelector = new JLabel("Parent Selector");
		
		JComboBox parentSelector = new JComboBox();
		parentSelector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//TODO
			}
		});
		
		JLabel lblSParameter = new JLabel("S Parameter");
		
		textField = new JTextField();
		textField.setColumns(10);
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
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelParentSelector.setLayout(gl_panelParentSelector);
		
		JPanel panelRecombinationOperator = new JPanel();
		panelRecombinationOperator.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelRecombinationOperator);
		
		JLabel lblRecombinationOperator = new JLabel("Recombination Operator");
		
		JComboBox recombinationOperator = new JComboBox();
		
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
		
		JComboBox mutationOperator = new JComboBox();
		
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
		
		JComboBox survivorSelector = new JComboBox();
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
		
		JPanel panelPopulation = new JPanel();
		panelPopulation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelPopulation);
		
		JLabel lblPopulation = new JLabel("Population:");
		
		JLabel lblPopulationSize = new JLabel("Size");
		
		populatonSize = new JTextField();
		populatonSize.setColumns(10);
		
		lblPopulationMaxSize = new JLabel("Max");
		
		populationMaxSize = new JTextField();
		populationMaxSize.setColumns(10);
		
		JLabel lblPopulationMinSize = new JLabel("Min");
		
		populationMinSize = new JTextField();
		populationMinSize.setColumns(10);
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

	}
}
