package data;

import java.io.IOException;

import javax.swing.Action;

import api.Win;

public interface Conection {
	void getData(Win window);
	void connectTo() throws IOException;
	void getAPI();
	boolean connected();
}
