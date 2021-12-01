package data;

import java.io.IOException;

public interface Conection {
	void connectTo(String login, String accessToken) throws IOException;
	void getAPI();
	
}

