package data;

import java.awt.Container;
import java.io.IOException;

import javax.swing.Action;

import appearence.Layout;
import gui.MainWindow;

public interface Connection {
	void getDataLayout(Container pane);
	
	void getDataLayout(Layout layout);

	void getData();
	
	void connectTo() throws IOException;

	boolean connected();
}
