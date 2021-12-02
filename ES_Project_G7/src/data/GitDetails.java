package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.UIDefaults.ActiveValue;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import api.Win;
import definitions.Button;
import definitions.FontType;
import definitions.Layout;
import definitions.TextField;
import definitions.TextLabel;

@SuppressWarnings("serial")
public class GitDetails extends JPanel implements Conection {

	private GitHub gitMvn;
	private static final int STARTING_POINT = 150;
	private boolean connected;
	private String username;
	private String accessToken;
	private String repository;

	/**
	 * Create GitHub JPanel.
	 * 
	 * @throws IOException
	 */
	public GitDetails(Win window) throws IOException {
		super();
		setAlignmentY(TOP_ALIGNMENT);
		getData(window);
//		connectTo(this.username, this.accessToken);
	}

	/**
	 * Get GitHub data from window frame.
	 */
	public void getData(Win window) {
		TextLabel title = new TextLabel("Login GitHub", 15, FontType.FONT_TITLE);
		TextLabel user_lab = new TextLabel("Username: ", 15, FontType.FONT_BOLD);
		TextField login = new TextField();
		TextLabel token_lab = new TextLabel("Access Token: ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField();
		TextLabel card_lab = new TextLabel("Repository: ", 15, FontType.FONT_BOLD);
		TextField repository = new TextField();

//		login.addActionListener(null);;
		
		this.username = login.getText();
		this.accessToken = accessToken.getText();
		this.repository = repository.getText();

//		System.out.println(login.getText());
//		System.out.println("User: " + username + " " + this.accessToken + " " + this.repository);

		TextLabel[] labels = { user_lab, token_lab, card_lab };
		TextField[] fields = { login, accessToken, repository };

		Layout layout = new Layout();
		layout.addToSpringLayout(window, title, labels, fields, STARTING_POINT);
	}

	/**
	 * Establish connection to GitHub.
	 * 
	 * @throws IOException
	 */
	public void connectTo(String login, String accessToken) throws IOException {
		gitMvn.getUser(login);
		gitMvn.getRepository(getRepName(login));
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken, login).build();
		connected = true;
	}

	/**
	 * Get GitHub repository.
	 */
	private String getRepName(String login) {
		String rep = "";
		String hash = login + "/" + rep;
		return hash;
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
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean connected() {
		return connected;
	}
}
