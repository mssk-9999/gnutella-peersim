import java.util.Random;

public class Files {

	String FileName;
	Random rand = new Random();
	
	public Files(){
		
		if(rand.nextBoolean())
			FileName = "file" + (1 + rand.nextInt(19));
		else
			FileName = "file" + (21 + rand.nextInt(79));
		
	}
	
}
