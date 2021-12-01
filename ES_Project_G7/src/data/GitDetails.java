package data;

import java.io.IOException;

import javax.swing.*;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import api.Win;
import definitions.TextLabel;

@SuppressWarnings("serial")
public class GitDetails extends JPanel implements Conection {

	private GitHub gitMvn;
	private static final int STARTING_POINT = 150;
//	private String login;
//	private String accessToken;
//	private String rep;

	/**
	 * Create GitHub JPanel.
	 */
	public GitDetails(Win window) {
		super();
//		setAlignmentY();

		TextLabel g = new TextLabel("Login GitHub", 150);
//		add(g);

//		connectTo(login, accessToken);
	}


	/**
	 * Establish connection to GitHub.
	 * 
	 * @throws IOException 
	 */
	@Override
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
	@Override
	public void getAPI() {
		getGitHub();
	}
	private GitHub getGitHub() {
		return gitMvn;
	}
}
