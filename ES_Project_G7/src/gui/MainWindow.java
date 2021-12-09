package gui;

import java.io.IOException;
import java.util.Date;

import javax.swing.*;

import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appearence.*;
import appearence.TextField;
import charts.HumanitaryCost;
import charts.PieChartEstimatedTimeporSprint;
import charts.PieChartTimeporSprint;
import data.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static MainWindow FRAME;
	private Layout layout;
	private static final int X_LOCATION = 150;
	private static final int Y_LOCATION = 150;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int BTN_LEFT = 50;
	private static final int BTN_RIGHT = BTN_LEFT + 50;

	private TrelloConnect trello;
	private GitConnect github;

	/**
	 * Create singleton of Window class.
	 * 
	 * @throws IOException
	 */
	public static MainWindow getInstance() throws IOException {
		if (FRAME == null)
			FRAME = new MainWindow();
		return FRAME;
	}

	/**
	 * Create a simple GUI window.
	 * 
	 * @throws IOException
	 */
	private MainWindow() throws IOException {
		super("API");
//		setBounds(X_LOCATION+100, Y_LOCATION+100, WIDTH-200, HEIGHT-200);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		defineWindow(-150, -115);
		initialize();
	}

	private void clearPage() {
		FRAME.getContentPane().removeAll();
		FRAME.getContentPane().repaint();
	}

	private void defineWindow(int x, int y) {
		setBounds(X_LOCATION - x, Y_LOCATION - y, WIDTH + (2 * x), HEIGHT + (2 * y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

/////////////////
//Different windows
////////////////

	/**
	 * Initialization of GUI and submission of login data to Trello and GitHub.
	 * Makes the "request" to initialize the next section (window).
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {

		trello = new TrelloConnect(this.getContentPane());
		github = new GitConnect(trello.getLayout());

		JButton submit = new JButton("SUBMIT");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPage();
				try {
					trello.assumeData();
					github.assumeData();
					chooseView();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		github.getLayout().addToSpringLayout_xCentered(submit, 275);

		setVisible(true);
	}

	private void chooseView() {
		defineWindow(-150, -250);
		layout = new Layout(LayoutType.LAYOUT_GRID, this.getContentPane());

		JButton trelloBtn = new JButton("TRELLO");
		trelloBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPage();
				insertSprintName();
			}
		});
		layout.addToGridLayout(trelloBtn);

		JButton githubBtn = new JButton("GITHUB");
		githubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPage();
				try {
					githubWindow();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		layout.addToGridLayout(githubBtn);

		setVisible(true);
	}

	private void buttonToChooseView() {
		JButton back = new JButton("GO BACK");
		layout.addToSpringLayout_xCentered(back, 500);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPage();
				chooseView();
			}
		});
	}

/////////////////
//Trello view
////////////////

	private void insertSprintName() {
		defineWindow(-135, -155);

		layout = new Layout(LayoutType.LAYOUT_SPRING, this.getContentPane());

		layout.addToSpringLayout_xCentered(new TextLabel("Board members", 10, FontType.FONT_BOLD), 15);
		addStringsToLayout(trello.getMembers().split("\n"), 20);

		TextLabel text = new TextLabel("Insert here the name of the sprint from which you want", 15,
				FontType.FONT_BOLD);
		TextLabel text2 = new TextLabel("to gather information from.", 15, FontType.FONT_BOLD);
		TextField sprintName = new TextField("This is the name of the list correspondent to the sprint.", 40);

		JButton go = new JButton("GO");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPage();
				trelloWindow(sprintName.getText());
			}
		});

		Component[] comps = { text, text2, sprintName, go };
		layout.addToSpringLayout(comps, 142, 22);
		setVisible(true);
	}

	/**
	 * Displays in the window: sprint dates.
	 * 
	 * @param sprintName
	 */
	private void trelloWindow(String sprintName) {
		clearPage();
		defineWindow(0, 0);
//		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		layout = new Layout(LayoutType.LAYOUT_SPRING, this.getContentPane());
		
		TList sprint = trello.getSprint(sprintName);

		layout.addToSpringLayout_xCentered(new TextLabel(sprintName, 10, FontType.FONT_TITLE), 15);
		
		getTasks(sprint);

		addTimes(sprintName);
		addSprintDescriptions(sprintName);
		buttonToChooseView();
		
		setVisible(true);
	}

	private void addTimes(String sprintName) {
		JButton timePerSprint = new JButton("Time per Sprint");
		timePerSprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTimePerSprint(sprintName);
			}
		});
		
		JButton estimatedTimePerSprint = new JButton("Estimated time per Sprint");
		estimatedTimePerSprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEstimatedTimePerSprint(sprintName);
			}
		});
		
		JButton humanitaryCostPerSprint = new JButton("Humanitary Cost per Sprint");
		humanitaryCostPerSprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHumanitaryCostPerSprint(sprintName);
			}
		});
		
		layout.addToSpringLayout(timePerSprint, -250, 400);
		layout.addToSpringLayout_xCentered(estimatedTimePerSprint, 400);
		layout.addToSpringLayout(humanitaryCostPerSprint, 125, 400);
	} 
	
	private void addSprintDescriptions(String sprintName) {
		TList sprint = trello.getSprint(sprintName);
		
		JButton sprintPlann = new JButton("SPRINT PLANNING");
		sprintPlann.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, trello.getSprintPlanningDesc(sprint),
						sprintName + " | Sprint Planning", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		});

		JButton sprintReview = new JButton("SPRINT REVIEW");
		sprintReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, trello.getSprintReviewDesc(sprint), sprintName + " | Sprint Review",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		});

		JButton sprintRetrospective = new JButton("SPRINT RETROSPECTIVE");
		sprintRetrospective.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, trello.getSprintRetrospectiveDesc(sprint),
						sprintName + " | Sprint Retrospective", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		});

		layout.addToSpringLayout(sprintPlann, -250, 450);
		layout.addToSpringLayout_xCentered(sprintReview, 450);
		layout.addToSpringLayout(sprintRetrospective, 100, 450);
	}
	
	private void showTimePerSprint(String sprintName) {
		SwingUtilities.invokeLater(() -> {
			PieChartTimeporSprint timePerSprint = new PieChartTimeporSprint("Tempo por Sprint", sprintName);
			timePerSprint.setSize(800, 400);
//			timePerSprint.setLocationRelativeTo(null);
			timePerSprint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			timePerSprint.setVisible(true);
		});
	}
	
	private void showEstimatedTimePerSprint(String sprintName) {
		SwingUtilities.invokeLater(() -> {
			PieChartEstimatedTimeporSprint estimatedTimePerSprint = new PieChartEstimatedTimeporSprint("Tempo estimado por Sprint", sprintName);				estimatedTimePerSprint.setSize(800, 400);
//			estimatedTimePerSprint.setLocationRelativeTo(null);
			estimatedTimePerSprint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			estimatedTimePerSprint.setVisible(true);
		});
	}
	
	private void showHumanitaryCostPerSprint(String sprintName) {
		SwingUtilities.invokeLater(() -> {
			HumanitaryCost humanitaryCostPerSprint = new HumanitaryCost("Custo do Sprint", sprintName);
			humanitaryCostPerSprint.setSize(800, 400);
//			timePerSprint.setLocationRelativeTo(null);
			humanitaryCostPerSprint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			humanitaryCostPerSprint.setVisible(true);
		});
	}

	private void getTasks(TList sprint) {
		int i = 50;
		for (Card c : trello.listCardsSprint(sprint)) {
			layout.addToSpringLayout_xCentered(new TextLabel(c.getName(), 12, FontType.FONT_REGULAR), i);
			i += 15;
		}
		setVisible(true);

	}

	private void addStringsToLayout(String[] strings, int startingPoint) {
		Component[] comps = new Component[strings.length];
		for (int i = 0; i < strings.length; i++) {
			TextLabel label = new TextLabel(strings[i], 13, FontType.FONT_REGULAR);
			comps[i] = label;
		}
		layout.addToSpringLayout(comps, startingPoint, 20);
	}

/////////////////
//GitHub view
////////////////

	private void githubWindow() throws IOException {
		defineWindow(0, 0);
		layout = new Layout(LayoutType.LAYOUT_SPRING, this.getContentPane());

		TextLabel projectName = new TextLabel(github.getProjectIentification(), 15, FontType.FONT_TITLE);
		layout.addToSpringLayout_xCentered(projectName, 15);
		TextLabel startingDate = new TextLabel("Created at " + github.getProjectStartDate().toString(), 13,
				FontType.FONT_BOLD);
		layout.addToSpringLayout_xCentered(startingDate, 40);
		addStringsToLayout(github.getProjectDescription(), 65);
//		addStringsToLayout(github.getTagsWithDate(), 60);
//		commits();

		buttonToChooseView();
		setVisible(true);
	}

//	private void commits() {
//
//		JButton getCommits = new JButton("GET COMMITS");
//		layout.addToSpringLayout(getCommits, BTN_LEFT, 100);
//		getCommits.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String username = JOptionPane.showInputDialog(null, "Insert your username here:", "GitHub Username", JOptionPane.PLAIN_MESSAGE);
//				if (username.equals(""))
//					throw new NullPointerException("Please insert a valid name.");
//				try {
//					clearPage();
//					JPanel container = new JPanel();
//					JScrollPane scrPane = new JScrollPane(container);
//					FRAME.getContentPane().add(scrPane);
//					addStringsToLayout(github.commitsFromUser(username), 15);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//	}

/////////////////
//Getters & Setters
////////////////

	/**
	 * Get Window Frame.
	 */
	public static MainWindow getFrame() {
		return FRAME;
	}

	/**
	 * Get Trello API.
	 * 
	 * @return
	 */
	public TrelloConnect getTrelloConnect() {
		return trello;
	}

	public GitConnect getGitConnect() {
		return github;
	}

	/**
	 * Get horizontal location of the window.
	 * 
	 * @return
	 */
	public static int getxLocation() {
		return X_LOCATION;
	}

	/**
	 * Get vertical location of the window.
	 * 
	 * @return
	 */
	public static int getyLocation() {
		return Y_LOCATION;
	}

	/**
	 * Get width of the window.
	 * 
	 * @return
	 */
	public static int getHorizontalSize() {
		return WIDTH;
	}

	/**
	 * Get height of the window.
	 * 
	 * @return
	 */
	public static int getVerticalSize() {
		return HEIGHT;
	}
}