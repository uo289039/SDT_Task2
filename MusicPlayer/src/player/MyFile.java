package player;
import java.io.File;
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

}
