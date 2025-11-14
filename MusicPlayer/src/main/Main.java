package main;

import player.MusicPlayer;
import ui.MainWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MusicPlayer mp=new MusicPlayer();
		MainWindow mw=new MainWindow(mp);
		mw.setVisible(true);
	}

}
