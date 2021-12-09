package data;

import appearence.*;

import java.awt.Container;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	private String accessToken;// = "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"; // ogait
	private String projectOwner;// = "mapa95";
	private String repository;// = "ES-LETI-1Sem-2021-Grupo7";

/////////////////
//Constructors
////////////////

	/**
	 * Create GitConnect without any previous Layout made.
	 * 
	 * @param pane Uses a <code>Container</code> as base to define where the
	 *             implementation of the <code>getData()</code> functions.
	 * @throws IOException
	 */
	public GitConnect(Container pane) throws IOException {
		getDataLayout(pane);
		connected = false;
	}

	/**
	 * Create GitConnect when there's already a Layout.
	 * 
	 * @param layout Uses a predefined <code>Layout</code> as a parameter, to define
	 *               where will be added the <code>getData()</code> functions.
	 * @throws IOException
	 */
	public GitConnect(Layout layout) throws IOException {
		getDataLayout(layout);
		connected = false;
	}

/////////////////
//Methods
////////////////

	/**
	 * Get GitHub data when there's no previous layout.
	 * 
	 * @param pane Uses a <code>Container</code> as base to define where will be
	 *             created the new <code>Layout</code> (which is the layout
	 *             instance).
	 */
	@Override
	public void getDataLayout(Container pane) {
		getData();
		layout = new Layout(LayoutType.LAYOUT_SPRING, pane, title, labels, textFields, STARTING_POINT);
	}

	/**
	 * Get GitHub data when exists a previous layout.
	 * 
	 * @param pane Uses a <code>Layout</code> as base to define where will be added
	 *             the <code>getData()</code> function and defines it as the layout
	 *             instance.
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
		TextLabel token_label = new TextLabel("Login (AccessToken): ", 15, FontType.FONT_BOLD);
		TextField accessToken = new TextField("Insert your AccessToken...", 30);
		TextLabel owner_label = new TextLabel("Project Owner: ", 15, FontType.FONT_BOLD);
		TextField projectOwner = new TextField("Insert user name owner of the Repository...", 30);
		TextLabel rep_label = new TextLabel("Repository: ", 15, FontType.FONT_BOLD);
		TextField repository = new TextField("Insert Repository Name...", 30);

		TextLabel[] labels = { token_label, owner_label, rep_label };
		this.labels = labels;
		TextField[] textFields = { accessToken, projectOwner, repository };
		this.textFields = textFields;
	}

	/**
	 * Uses data inserted by the user as login instances.
	 * 
	 * @throws IOException
	 */
	@Override
	public void assumeData() throws IOException {
		accessToken = textFields[0].getText();
		projectOwner = textFields[1].getText();
		repository = textFields[2].getText();
		connectTo();

		System.out.println("Access Token: " + accessToken);
		System.out.println("Project Owner: " + projectOwner);
		System.out.println("Repository: " + repository);
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
		connected = true;
	}

/////////////////
//Getters & Setters
////////////////

	/**
	 * Get GitHub API.
	 * 
	 * @return
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
	 * 
	 * @return
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Get GitHub's repository project owner.
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * Set GitHub's accessToken to the current user logged in.
	 * 
	 * @param accessToken
	 */
	public void setRepository(String repository) {
		this.repository = repository;
	}

	/**
	 * Get GitHub's repository name, according to the project owner and repository's
	 * name.
	 * 
	 * @return
	 */
	public String getRepositoryName() {
		return projectOwner + "/" + repository;
	}

	/**
	 * Get GitHub's Repository.
	 * 
	 * @return
	 */
	public GHRepository getGitRepo() {
		return gitRepo;
	}

	/**
	 * Gets project identification from GithubRepo's name.
	 * 
	 * @return
	 */
	public String getProjectIentification() {
		return gitRepo.getName();
	}

	/**
	 * Get from GitHub's repository the project description.
	 * 
	 * @return
	 * @throws IOException
	 */
	public String[] getProjectDescription() throws IOException {
		return gitRepo.getFileContent("README.md").getContent().split("\n");
	}

	/**
	 * Get from GitHub's repository the starting date of the project.
	 * 
	 * @return
	 * @throws IOException
	 */
	public Date getProjectStartDate() throws IOException {
		return gitRepo.getCreatedAt();
	}

	/**
	 * Get from GitHub's repository the tags from the master branch.
	 * 
	 * @return
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
	public String[] getTagsWithDate() throws IOException {
		String[] tagsAndDates = new String[getTagsFromMaster().size()];
		for (GHTag gt : getTagsFromMaster()) {
			int i = 0;
			tagsAndDates[i] = "Nome tag: " + gt.getName() + " | " + "Data Criada: " + gt.getOwner().getCreatedAt();
			i++;
		}
		return tagsAndDates;
	}

//	/**
//	 * Get from GitHub's repository the commits' list.
//	 * 
//	 * @throws IOException
//	 */
//	public void getCommits() throws IOException {
//		System.out.println("-------------------------------------------------");
//		commitsFromUser("mapa95");
//		List<GHCommit> list = gitRepo.listCommits().asList();
//		for (GHCommit g : list)
//			System.out.println("Data Commit: " + g.getCommitDate() + " Autor: " + g.getAuthor().getName()
//					+ " Descri��o: " + g.getCommitShortInfo().getMessage());
//	}

	private void getBranches() throws IOException {
		Map<String, GHBranch> map = gitRepo.getBranches();
		for (Entry<String, GHBranch> entry : map.entrySet()) {
			System.out.println(entry.getValue().getName());
		}
	}

	public String[] commitsFromUser(String username) throws IOException {

//		Set<GHUser> names = gitRepo.getCollaborators();
//		for(GHUser g : names)
//			System.out.println(g.getLogin());

		List<GHCommit> list = gitRepo.listCommits().toList();
		String[] result = new String[list.size()];
		for (GHCommit g : list) {
			int i = 0;
			if (g.getAuthor().getLogin().equals(username) && g.getAuthor().getName() != null) {
				result[i] = ("Commit Date: " + g.getCommitDate() + " Author: " + g.getAuthor().getName()
						+ " Description: " + g.getCommitShortInfo().getMessage());
			}
			i++;
		}
		return result;
	}
}