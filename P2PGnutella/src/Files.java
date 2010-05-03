import java.util.Random;

public class Files {

	String FileName;
	Random rand = new Random();
	
	public Files(){
		
		FileName = "file" + (1 + rand.nextInt(19));
		
	}
	
}
