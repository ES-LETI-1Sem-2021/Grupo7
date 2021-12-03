package data;

import java.io.IOException;

import javax.swing.Action;

import gui.MainWindow;

public interface Conection {
	void getData(MainWindow window);

	void connectTo() throws IOException;

	void getAPI();

	boolean connected();
}
