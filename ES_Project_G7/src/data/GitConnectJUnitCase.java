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
	private String accessToken = "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"; // aram
	private String projectOwner = "mapa95";
	private String repository = "ES-LETI-1Sem-2021-Grupo7";
	private GitHub github;
	private GHRepository gitRepo;
	private GitConnect gitconnect;

	@BeforeEach
	void setUp() throws Exception {
		gitconnect = MainWindow.getInstance().getGitConnect();
		gitconnect.setAccessToken(accessToken); // aram
		gitconnect.setProjectOwner(projectOwner);
		gitconnect.setRepository(repository);
		gitconnect.connectTo();
		github = gitconnect.getGitHub();
		gitRepo = gitconnect.getGitRepo();
	}

	@Test
	public void testConnectTo() throws Exception {
		GitHub git_test = new GitHubBuilder().withOAuthToken(accessToken).build();
		GHRepository gitRepotest = git_test.getRepository(projectOwner + "/" + repository);

		assertNotNull(gitconnect);
		assertEquals(github, git_test);
		assertNotNull(gitRepo);
		assertEquals(gitRepo, gitRepotest);
	} 

	@Test
	public void testgetProjectStartDate() throws Exception {
		assertNotNull(gitconnect.getProjectStartDate());
		String data1 = gitconnect.getProjectStartDate().toString();
		String data2 = "Mon Nov 15 16:09:46 WET 2021";
		assertEquals(data1, data2);
	}

	@Test
	public void testgetProjectDescription() throws Exception {
		assertNotNull(gitconnect.getProjectStartDate());
		String string1 = gitRepo.getFileContent("README.md").getContent();
//		String string2 = "# ES-LETI-1Sem-2021-Grupo7\r\n" + "\r\n"
//				+ "Trabalho realizado no �mbito da UC de Engenharia de Software:\r\n" + "- Gabriel Monteiro (92458)\r\n"
//				+ "- Hugo Santos (92585)\r\n" + "- Tiago Almeida (92985)\r\n" + "- Mara Alves (94013)";

		String string2 = "# ES-LETI-1Sem-2021-Grupo7\r\n"
				+ "Trabalho realizado no âmbito da UC de Engenharia de Software:\r\n" + "\r\n"
				+ "Gabriel Monteiro (92458)\r\n" + "Hugo Santos (92585)\r\n" + "Tiago Almeida (92985)\r\n"
				+ "Mara Alves (94013)\r\n" + "Não foi possível integrar os seguintes elementos:\r\n" + "\r\n"
				+ "ProductBacklog: 6, 10, 11 e 15.\r\n" + "CodeSmells\r\n" + "Funcionalidades incompletas:\r\n" + "\r\n"
				+ "Janela - houve muitas dificuldades iniciais no manuseamento das ferramentas associadas à janela (e ao JFrame), visto que nenhum elemento do grupo tinha alguma vez trabalhado com ferramentas de frontend em java, o que acabou por levar a um atraso geral do projeto.\r\n"
				+ "Obtenção das horas totais para cada elemento do grupo mediante o sprint.\r\n"
				+ "Representação dos elementos de ProductBacklog em cada janela.\r\n"
				+ "Problemas de implementação:\r\n" + "\r\n"
				+ "Apesar de se ter chegado a uma solução mediana para a janela, o grupo continuou a deparar-se com vários (pelo menos aparentes) bugs o que acabou por levar a várias horas perdidas na pesquisa de uma forma de os resolver.";
		assertEquals(string1, string2);
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
