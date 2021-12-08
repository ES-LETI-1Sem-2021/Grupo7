package data;

import java.awt.Container;
import java.io.IOException;

import javax.swing.Action;

import appearence.Layout;
import gui.MainWindow;

public interface Connection {
	void getDataLayout(Container pane) throws IOException;

	void getDataLayout(Layout layout) throws IOException;

	void getData() throws IOException;

	void assumeData() throws IOException;

	void connectTo() throws IOException;

	boolean connected();
}
