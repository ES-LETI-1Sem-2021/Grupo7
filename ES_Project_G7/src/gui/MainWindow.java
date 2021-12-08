package gui;

import java.io.IOException;
import javax.swing.*;

import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import appearence.*;
import appearence.TextField;
import data.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static MainWindow FRAME;
	private Layout layout;
	private static final int X_LOCATION = 150;
	private static final int Y_LOCATION = 150;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

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
				githubWindow();
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

	private void trelloWindow(String sprintName) {
		defineWindow(0, 0);
		layout = new Layout(LayoutType.LAYOUT_SPRING, this.getContentPane());
		trelloSprintOptions(sprintName);
		buttonToChooseView();
		setVisible(true);
	}

	/**
	 * Displays in the window: sprint dates.
	 * 
	 * @param sprintName
	 */
	private void trelloSprintOptions(String sprintName) {
		TList sprint = trello.getSprint(sprintName);

//		addStringsToLayout(trello.getSprintDates(sprint), 15);
		
		// Descrição: Planning, Review e Retrospective
		// Mostrar: tarefas do sprint
		// Número de horas + gráficos + custo
		// ProductBacklog(?)
		
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

	private void githubWindow() {
		defineWindow(0, 0);
		layout = new Layout(LayoutType.LAYOUT_SPRING, this.getContentPane());

		// data de início do projeto - getProjectStartDate
		
		buttonToChooseView();
		setVisible(true);
	}

	private void readME() {

	}

	private void tags() {
		//getTagsWithDate
	}

	private void commits() {

	}

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