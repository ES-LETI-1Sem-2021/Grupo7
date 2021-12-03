package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.UIDefaults.ActiveValue;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import appearence.Button;
import appearence.FontType;
import appearence.Layout;
import appearence.TextField;
import appearence.TextLabel;
import gui.MainWindow;

@SuppressWarnings("serial")
public class GitConnect extends JPanel implements Conection {

	private GitHub gitMvn;
	private GHRepository gitRepo;
	private static final int STARTING_POINT = 150;
	private boolean connected;
	private String projectOwner = "mapa95";
	private String accessToken = "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"; // ogait
	private String repository = "ES-LETI-1Sem-2021-Grupo7";

	/**
	 * Create GitHub JPanel.
	 * 
	 * @throws IOException
	 */
	public GitConnect(MainWindow window) throws IOException {
		super();
		setAlignmentY(TOP_ALIGNMENT);
		getData(window);
		connected = false;
		connectTo();
	}

	/**
	 * Get GitHub data from window frame.
	 */
	public void getData(MainWindow window) {
		TextLabel title = new TextLabel("Login GitHub", 15, FontType.FONT_TITLE);
		TextLabel user_lab = new TextLabel("Project Owner: ", 15, FontType.FONT_BOLD);
		TextField projectOwner = new TextField("Insert user name owner of the Repository...");
		TextLabel card_lab = new TextLabel("Repository: ", 15, FontType.FONT_BOLD);
		TextField repository = new TextField("Insert Repository Name..");
		TextLabel token_lab = new TextLabel("Login (AccessToken): ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField("Insert your AccessToken...");

//		login.addActionListener(null);;

//		this.projectOwner = projectOwner.getText();
//		this.accessToken = accessToken.getText();
//		this.repository = repository.getText();

//		System.out.println(login.getText());
//		System.out.println("User: " + projectOwner + " " + this.accessToken + " " + this.repository);

		TextLabel[] labels = { user_lab, card_lab, token_lab };
		TextField[] fields = { projectOwner, repository, accessToken };

		Layout layout = new Layout();
		layout.addToSpringLayout(window, title, labels, fields, STARTING_POINT);
	}

	/**
	 * Establish connection to GitHub.
	 * 
	 * @throws IOException
	 */
	public void connectTo() throws IOException {
		// gitMvn.getUser(username);
		// gitMvn.getRepository(getRepName(username));
		// gitMvn = new GitHubBuilder().withOAuthToken(accessToken, username).build();
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken).build();
		gitRepo = gitMvn.getRepository(getRepository());
		System.out.println(getProjectDescription());
		connected = true;
	}

	/**
	 * Get GitHub repository.
	 */
	private String getRepository() {
		return projectOwner + "/" + repository;
	}

	/**
	 * Get Project Description.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private String getProjectDescription() throws IOException {
		return gitRepo.getFileContent("README.md").getContent();
	}

	/**
	 * Get GitHub API.
	 */
	public void getAPI() {
		getGitHub();
	}

	private GitHub getGitHub() {
		return gitMvn;
	}

	public String getUsername() {
		return projectOwner;
	}

	public void setUsername(String username) {
		this.projectOwner = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public boolean connected() {
		return connected;
	}

}
