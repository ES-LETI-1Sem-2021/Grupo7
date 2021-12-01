package data;

import java.io.IOException;

import javax.swing.*;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import definitions.TextField;
import definitions.TextLabel;

@SuppressWarnings("serial")
public class GitDetails extends JPanel implements Conection {

	private GitHub gitMvn;
//	private String login;
//	private String accessToken;
//	private String rep;

	/**
	 * Create GitHub JPanel.
	 */
	public GitDetails() {
		super();
		/*
		TextLabel g = new TextLabel("User GitHub", 150);
		add(g);
		TextField user = new TextField();
		add(user);	
		
		TextLabel g3 = new TextLabel("Token GitHub", 150);
		TextField user2 = new TextField();
		add(g3);
		add(user2);
		*/
//		connectTo(login, accessToken);
	}


	/**
	 * Establish connection to GitHub.
	 * 
	 * @throws IOException 
	 */
	public void connectTo(String login, String accessToken) throws IOException {
		gitMvn.getUser(login);
		gitMvn.getRepository(getRep(login));
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken, login).build();
	}
	
	/**
	 * Get GitHub repository.
	 */
	private String getRep(String login) {
		String rep = "";
		String hash = login+"/"+rep;
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
}
