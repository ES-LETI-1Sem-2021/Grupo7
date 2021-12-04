package data;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import appearence.FontType;
import appearence.Layout;
import appearence.LayoutType;
import appearence.TextField;
import appearence.TextLabel;
import gui.MainWindow;

public class GitConnect implements Connection {

	private Layout layout;
	private Container pane;
	private GitHub gitMvn;
	private GHRepository gitRepo;
	private static final int STARTING_POINT = 150;
	private boolean connected;
	private boolean hasButton;

	private TextLabel title;
	private TextLabel[] labels;
	private TextField[] textFields;

	// DataTest
	private String projectOwner = "mapa95";
	private String accessToken = "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"; // ogait
	private String repository = "ES-LETI-1Sem-2021-Grupo7";

	/**
	 * Create GitConnect without any previous Layout made.
	 */
	public GitConnect(Container pane, boolean hasButton) throws IOException {
		getDataLayout(pane);
		connected = false;
		connectTo();
	}

	/**
	 * Create GitConnect when there's already a Layout.
	 */
	public GitConnect(Layout layout, boolean hasButton) throws IOException {
		getDataLayout(layout);
		connected = false;
		connectTo();
	}

	/**
	 * Create GitConnect when there's already a Layout.
	 */
	public GitConnect(Layout layout) throws IOException {
		getDataLayout(layout);
		connected = false;
		connectTo();
	}

	/*
	 * Get GitHub data when there's no previous layout.
	 */
	@Override
	public void getDataLayout(Container pane) {
		getData();
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	/*
	 * Get GitHub data when exists a previous layout.
	 */
	@Override
	public void getDataLayout(Layout layout) {   //tentar get pane
		getData();
		this.layout = layout;
		this.pane = layout.getPane();

		if (layout.getLayoutType() == LayoutType.LAYOUT_SPRING)
			this.layout.addToSpringLayout(title, labels, textFields, STARTING_POINT);
	}

	/*
	 * Establish connection to GitHub.
	 */
	@Override
	public void connectTo() throws IOException {
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken).build();
		gitRepo = gitMvn.getRepository(getRepository());
		System.out.println(getProjectDescription());
		connected = true;
	}

	/*
	 * Get GitHub data from user in order to establish connection.
	 */
	@Override
	public void getData() {
		title = new TextLabel("Login GitHub", 15, FontType.FONT_TITLE);
		TextLabel user_lab = new TextLabel("Project Owner: ", 15, FontType.FONT_BOLD);
		TextField projectOwner = new TextField("Insert user name owner of the Repository...", 30);
		TextLabel card_lab = new TextLabel("Repository: ", 15, FontType.FONT_BOLD);
		TextField repository = new TextField("Insert Repository Name...", 30);
		TextLabel token_lab = new TextLabel("Login (AccessToken): ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField("Insert your AccessToken...", 30);

		TextLabel[] labels = { user_lab, card_lab, token_lab };
		this.labels = labels;
		TextField[] textFields = { projectOwner, repository, accessToken };
		this.textFields = textFields;
	}

	/**
	 * Get GitHub repository.
	 */
	private String getRepository() {
		return projectOwner + "/" + repository;
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

	@Override
	public boolean connected() {
		// TODO Auto-generated method stub
		return connected;
	}

	/* GETTERS & SETTERS */

	/**
	 * Get GitConnect layout.
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Get from GitHub's repository the project description.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private String getProjectDescription() throws IOException {
		return gitRepo.getFileContent("README.md").getContent();
	}
}
