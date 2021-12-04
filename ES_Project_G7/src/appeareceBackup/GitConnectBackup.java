package appeareceBackup;

import java.awt.BorderLayout;
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
import appearence.LayoutType;
import appearence.TextField;
import appearence.TextLabel;
import data.Connection;
import gui.MainWindow;

@SuppressWarnings("serial")
public class GitConnectBackup implements Connection {

	private GitHub gitMvn;
	private GHRepository gitRepo;
	private static final int STARTING_POINT = 200;
	private boolean connected;
	private String projectOwner = "mapa95";
	private String accessToken = "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"; // ogait
	private String repository = "ES-LETI-1Sem-2021-Grupo7";

	/**
	 * Create GitHub JPanel.
	 * 
	 * @throws IOException
	 */
	public GitConnectBackup(MainWindow window) throws IOException {
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

		TextLabel[] labels = { user_lab, card_lab, token_lab };
		TextField[] fields = { projectOwner, repository, accessToken };

		new LayoutBackup(LayoutType.LAYOUT_SPRING, window, title, labels, fields, STARTING_POINT);
//		new LayoutBackup(LayoutType.LAYOUT_BORDER, window, title, labels, fields, BorderLayout.SOUTH);
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
