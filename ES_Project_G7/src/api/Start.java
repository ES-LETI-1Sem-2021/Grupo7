package api;

import java.awt.EventQueue;

public class Start {

public static void main( String[] args )
{
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
			Win j = new Win();
				j.getFrame().setVisible(true);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});	
	
}}