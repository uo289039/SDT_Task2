package player;
import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
public class MyFile {
	
	File myFile;


	public MyFile(File myFile) {
		super();
		this.myFile = myFile;
	}

	public File getMyFile() {
		return myFile;
	}
	
	@Override
	public String toString() {
		
		return myFile.getName();
	}
	
	public double getDuration() {
		Mp3File mp3;
		try {
			mp3 = new Mp3File(myFile);
			return mp3.getLengthInMilliseconds();
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}

}
