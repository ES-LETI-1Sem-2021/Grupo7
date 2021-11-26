package data;

import java.io.IOException;

import javax.swing.JPanel;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class GitDetails extends JPanel {
	
	private static GitDetails gitPanel;
	
	private GitHub gitMvn;
	private String login;
	private String accessToken;
	private String rep;
	


	
	
	
	
	

	/**
	 * Establish connection to GitHub.
	 */
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
	
	/**
	 * Create GitHub JPanel.
	 */
	public static GitDetails getGitPanel()	{
		return gitPanel;
	}
}
