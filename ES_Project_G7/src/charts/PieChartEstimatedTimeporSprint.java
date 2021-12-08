package charts;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Argument;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;

import data.TrelloConnect;
import gui.MainWindow;
import work.Sprint;
import work.Task;

public class PieChartEstimatedTimeporSprint extends JFrame {
	private static final long serialVersionUID = 6294689542092367723L;
	private String name1 = null;
	PieDataset dataset;
	private Map<String, Double> mapa = new HashMap<String, Double>();

	public PieChartEstimatedTimeporSprint(String title, String sprintname) {
		super(title);
		this.name1 = sprintname;
		// Create dataset
		TrelloConnect trelloconnect = MainWindow.getFrame().getTrelloConnect();

		this.dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createPieChart("Trello", dataset, true, true, false);

		// Format Label
		PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("Marks {0} : ({2})",
				new DecimalFormat("0"), new DecimalFormat("0%"));
		((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	/**
	 * function to initialize the map of username/membertimespent
	 * 
	 */
	private void createDatasetaux() {

		TrelloConnect trelloconnect = MainWindow.getFrame().getTrelloConnect();
		Trello trello = trelloconnect.getTrello();
		Sprint sprint = new Sprint();

		sprint.getTimeSpent(this.name1);
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Card card : trelloconnect.listCardsSprint(trelloconnect.getSprint(this.name1))) {
			for (Member m : trello.getCardMembers(card.getId())) {
				if (!mapa.containsKey(m.getUsername())) {
					mapa.put(m.getUsername(), sprint.membergetTimeEstimated(m.getUsername(), m.getId(), this.name1));

				}
			}
		}
	}

	/**
	 * Create Dataset
	 * 
	 * @return
	 */
	private PieDataset createDataset() {

		TrelloConnect trelloconnect = MainWindow.getFrame().getTrelloConnect();
		createDatasetaux();
		double valor = 0;
		String name = null;
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Entry<String, Double> pair : mapa.entrySet()) {
			name = pair.getKey();
			valor = pair.getValue();
			dataset.setValue(name, valor);
		}

		return dataset;

	}

	public static void main(String[] args) throws IOException {
		MainWindow.getInstance();
		SwingUtilities.invokeLater(() -> {
			PieChartEstimatedTimeporSprint example = new PieChartEstimatedTimeporSprint("Tempo por Sprint","3rd Sprint");
			example.setSize(800, 400);
			example.setLocationRelativeTo(null);
			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example.setVisible(true);

		});

	}

}