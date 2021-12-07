package data;

import appearence.*;

import java.awt.Container;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.kohsuke.github.*;

public class GitConnect implements Connection {

	private Layout layout;
	private GitHub gitMvn;
	private GHRepository gitRepo;
	private static final int STARTING_POINT = 150;
	private boolean connected;

	private TextLabel title;
	private TextLabel[] labels;
	private TextField[] textFields;

	// DataTest
	private String projectOwner = "mapa95";
	private String accessToken = "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"; // ogait
	private String repository = "ES-LETI-1Sem-2021-Grupo7";

/////////////////
//Constructors
////////////////

	/**
	 * Create GitConnect without any previous Layout made.
	 * 
	 * @param <code>pane</code> Uses a Container as base to define where the
	 *                          implementation of the getData() functions.
	 * 
	 * @throws IOException
	 */
	public GitConnect(Container pane) throws IOException {
		getDataLayout(pane);
		connected = false;
		connectTo();
		
	}

	/**
	 * Create GitConnect when there's already a Layout.
	 * 
	 * @param <code>layout</code> Uses a predefined layout as a parameter, to define
	 *                            where will be added the getData() functions.
	 * 
	 * @throws IOException
	 */
	public GitConnect(Layout layout) throws IOException {
		getDataLayout(layout);
		connected = false;
		connectTo();
	}

/////////////////
//Methods
////////////////

	/**
	 * Get GitHub data when there's no previous layout.
	 * 
	 * @param <code>pane</code> Uses a Container as base to define where will be
	 *                          created the new Layout (which is the layout
	 *                          instance).
	 */
	@Override
	public void getDataLayout(Container pane) {
		getData();
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get GitHub data when exists a previous layout.
	 * 
	 * @param <code>pane</code> Uses a Layout as base to define where will be added
	 *                          the getData() function and defines it as the layout
	 *                          instance.
	 */
	@Override
	public void getDataLayout(Layout layout) {
		getData();
		this.layout = layout;

		if (layout.getLayoutType() == LayoutType.LAYOUT_SPRING)
			this.layout.addToSpringLayout(title, labels, textFields, STARTING_POINT);
	}

	/**
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
	 * Establish connection to GitHub.
	 * 
	 * @throws IOException
	 */
	@Override
	public void connectTo() throws IOException {
		gitMvn = new GitHubBuilder().withOAuthToken(accessToken).build();
		gitRepo = gitMvn.getRepository(getRepositoryName());
//		System.out.println(getProjectDescription());
		connected = true;
	}

/////////////////
//Getters & Setters
////////////////

	/**
	 * Get GitHub API.
	 */
	public GitHub getGitHub() {
		return gitMvn;
	}

	/**
	 * Check if user is connected to GitHub.
	 */
	@Override
	public boolean connected() {
		return connected;
	}

	/**
	 * Get GitConnect layout.
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Get GitHub's repository project owner.
	 */
	public String getProjectOwner() {
		return projectOwner;
	}

	/**
	 * Set the project owner of the GitHub repository.
	 * 
	 * @param projectOwner
	 */
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}

	/**
	 * Get GitHub's accessToken of the current user logged in.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Set GitHub's accessToken to the current user logged in.
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Get GitHub's repository name, according to the project owner and repository's
	 * name.
	 */
	private String getRepositoryName() {
		return projectOwner + "/" + repository;
	}

	/**
	 * Get GitHub's Repository.
	 */
	public GHRepository getGitRepo() {
		return gitRepo;
	}

	/**
	 * Get from GitHub's repository the project description.
	 * 
	 * @throws IOException
	 */
	public String getProjectDescription() throws IOException {
		return gitRepo.getFileContent("README.md").getContent();
	}

	/**
	 * Get from GitHub's repository the starting date of the project.
	 * 
	 * @throws IOException
	 */
	public Date getProjectStartDate() throws IOException {
		return gitRepo.getCreatedAt();
	}

	/**
	 * Get from GitHub's repository the tags from the master branch.
	 * 
	 * @throws IOException
	 */
	public List<GHTag> getTagsFromMaster() throws IOException {
		return gitRepo.listTags().asList();
	}

	/**
	 * Get from GitHub's repository the tags' dates from the master branch.
	 * 
	 * @throws IOException
	 */
	public void getTagsWithDate() throws IOException {
		for (GHTag gt : getTagsFromMaster())
			System.out.println("Nome tag: " + gt.getName() + "\n" + "Data Criada: " + gt.getOwner().getCreatedAt());
	}

	/**
	 * Get from GitHub's repository the commits' list.
	 * 
	 * @throws IOException
	 */
	public void getCommits() throws IOException {
		List<GHCommit> list = gitRepo.listCommits().asList();
		for (GHCommit g : list)
			System.out.println("Data Commit: " + g.getCommitDate() + " Autor: " + g.getAuthor().getName()
					+ " Descri��o: " + g.getCommitShortInfo().getMessage());
	}
}