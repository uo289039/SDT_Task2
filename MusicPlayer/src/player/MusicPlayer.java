package player;

import java.io.File;
import java.util.Map;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer implements BasicPlayerListener {
	
private BasicPlayer basicPlayer = null;
	
	private double volumen;
	
	private double maximo;
	private Runnable onSongEnd;
	
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
		basicPlayer.addBasicPlayerListener(this);
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
	
	public void setOnSongEnd(Runnable r) {
        this.onSongEnd = r;
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

	@Override
	public void opened(Object arg0, Map arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {
		// TODO Auto-generated method stub
		 if (arg0.getCode() == BasicPlayerEvent.EOM) { // End Of Media
	            if (onSongEnd != null)
	                onSongEnd.run();
	}
	
	

	}
}
