package player;

import java.io.File;
import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	
private BasicPlayer basicPlayer = null;
	
	private double volumen;
	
	private double maximo;
	
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
	}
	
	public void play (File file){
		try {
			setVolumen(volumen);
			setMaximo(maximo);
			basicPlayer.open(file);
			basicPlayer.play();
			setVolumen(volumen);
			setMaximo(maximo);
		}
		catch (Exception e){	
			
		}
	}
	
	public void stop() {
		try{
			
			basicPlayer.stop();
			
		}
		catch (BasicPlayerException e){
		}
	}
	
	private void setMaximo(double maximo2) {
		this.maximo=maximo2;
		
	}

	private void setVolumen(double vol) {
		this.volumen = vol;
	}

	public void setVolumen(int vol, double volMax) {
		try{
			
			basicPlayer.setGain(vol/volMax);
			setVolumen(vol);
			setMaximo(volMax);
			
		}
		catch (BasicPlayerException e){
		}
		
	}

	public double getVolumen() {
		return volumen;
	}

	public double getMaximo() {
		return maximo;
	}
	
	

}
