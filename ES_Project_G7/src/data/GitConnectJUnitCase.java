package data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.julienvey.trello.Trello;

import com.julienvey.trello.impl.http.ApacheHttpClient;

import gui.MainWindow;

class GitConnectJUnitCase {
	private String accessToken ; // ogait
	private String repository ;
	private GitHub github;
	private GHRepository gitRepo;
	private GitConnect gitconnect;
	
	@BeforeEach
	void setUp() throws Exception {
		accessToken = "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"; // ogait
		 repository = "ES-LETI-1Sem-2021-Grupo7";
		 gitconnect = MainWindow.getInstance().getGitConnect();
			 github = gitconnect.getGitHub();
			 gitRepo=gitconnect.getGitRepo();
	}
	
	@Test
	public void testConnectTo() throws Exception {
		GitHub git_test  = new GitHubBuilder().withOAuthToken(accessToken).build();
		GHRepository gitRepotest = git_test.getRepository(repository);
		assertNotNull(git_test );
		assertEquals(github,git_test );
		assertNotNull(gitRepotest );
		assertEquals(gitRepo,gitRepotest );
	}
	
	@Test
	public void testgetProjectStartDate() throws Exception {
		assertNotNull(gitconnect.getProjectStartDate());
		String data1=gitconnect.getProjectStartDate().toString();
		String data2="Mon Nov 15 16:09:46 WET 2021";
				assertEquals(data1,data2);
	}
	
	@Test
	public void testgetProjectDescription() throws Exception {
		assertNotNull(gitconnect.getProjectStartDate());
		String string1=gitRepo.getFileContent("README.md").getContent();
		String string2="# ES-LETI-1Sem-2021-Grupo7\r\n"
				+ "\r\n"
				+ "Trabalho realizado no âmbito da UC de Engenharia de Software:\r\n"
				+ "- Gabriel Monteiro (92458)\r\n"
				+ "- Hugo Santos (92585)\r\n"
				+ "- Tiago Almeida (92985)\r\n"
				+ "- Mara Alves (94013)";
				assertEquals(string1,string2);
	}
	
	
	
	
	@Test
	public void testGetGitRepo() {
		assertNotNull(gitconnect.getGitRepo());
	}
	
	@Test
	public void testGetGitHub() {
		assertNotNull(gitconnect.getGitHub());
	}

}
