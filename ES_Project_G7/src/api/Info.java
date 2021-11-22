package api;

import java.io.IOException;

import javax.swing.JPanel;
import org.kohsuke.github.GitHub;
import com.julienvey.trello.Trello;

public class Info {

	private JPanel content = new JPanel();
	private GitHub gitMvn;
	private Trello trelloMvn;

	
	public Info(GitHub gitMvn, Trello trelloMvn) {
		this.gitMvn = gitMvn;
		this.trelloMvn = trelloMvn;
	}
	
	/**
	 * Get repository from GitHub.
	 */
	private void connectGit (String login, String accessToken, String rep) throws IOException {
		gitMvn.getUser(login);
		gitMvn.getRepository(login+"/"+rep);
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
