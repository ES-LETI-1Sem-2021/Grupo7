package data;

import java.io.IOException;

import javax.swing.Action;

import api.Win;

public interface Conection extends Action{
	void getData(Win window);
	void connectTo(String login, String accessToken) throws IOException;
	void getAPI();
	boolean connected();
}
