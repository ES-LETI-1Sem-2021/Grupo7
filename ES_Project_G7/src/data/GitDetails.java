package data;

import java.io.IOException;

import javax.swing.*;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import definitions.TextLabel;

@SuppressWarnings("serial")
public class GitDetails extends JPanel {

	private GitHub gitMvn;
//	private String login;
//	private String accessToken;
//	private String rep;

	/**
	 * Create GitHub JPanel.
	 */
	public GitDetails() {
		super();
		
		TextLabel g = new TextLabel("Login GitHub", 150);
		add(g);

//		connectGit(login, accessToken, rep);
	}

	/**
	 * Establish connection to GitHub.
	 * 
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	private void connectGit (String login, String accessToken, String rep) throws IOException {
		gitMvn.getUser(login);
		gitMvn.getRepository(login+"/"+rep);
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken, login).build();
	}

	/**
	 * Get GitHub API.
	 */
	public GitHub getGitHub() {
		return gitMvn;
	}
}
