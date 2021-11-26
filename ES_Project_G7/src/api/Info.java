package api;

import java.io.IOException;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

public class Info {

	//private JPanel content = new JPanel();
	private GitHub gitMvn;
	private Trello trelloMvn;


	public Info(String gitLogin, String gitAccessToken, String rep, String trelloLogin, String trelloToken) throws IOException {
		connectGit(gitLogin, gitAccessToken, rep);
		connectTrello(trelloLogin, trelloToken);
	}

	/**
	 * Establish connection to GitHub.
	 */
	private void connectGit (String login, String accessToken, String rep) throws IOException {
		gitMvn.getUser(login);
		gitMvn.getRepository(login+"/"+rep);
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken, login).build();
	}

	/**
	 * Establish connection to Trello.
	 */
	private void connectTrello (String login, String accessToken) throws IOException {
		trelloMvn = new TrelloImpl(login, accessToken, new ApacheHttpClient());
	}

	/**
	 * Get GitHub API.
	 */
	public GitHub getGitHub() {
		return gitMvn;
	}

	/**
	 * Get Trello API.
	 */
	public Trello getTrello() {
		return trelloMvn;
	}
}
