import java.util.Random;

public class Files {

	String FileName;
	Random r = new Random();
	static int flag=0;
	static int fileNumber=20;
	static int[] filenumberActual=new int[fileNumber];
	static int[] percentageRandom=new int[fileNumber];
	static int n=5000;

	public void init()
	{

		for(int a=0; a < fileNumber; a++)
		{
			percentageRandom[a]=r.nextInt(45) + 5;
			System.out.println(percentageRandom[a]);
		}
	}
	
	public Files(){
		
		/*
		if(rand.nextBoolean())
			FileName = " " + (1 + rand.nextInt(19));
			//FileName = "file" + (1 + rand.nextInt(19));
		else
			FileName = " " + (21 + rand.nextInt(79));
			//FileName = "file" + (21 + rand.nextInt(79));
		*/

		
		/*
		for(int a=0; a < fileNumber; a++)
		{
			percentageRandom[a]=r.nextInt(45) + 5;
			//System.out.println(percentageRandom[a]);
		}
		*/

		if ( flag==0)
		{
			init();
			flag++;
		}


			int ran=r.nextInt(20);
			if(ran < 20 && filenumberActual[ran] < percentageRandom[ran])
			{
				FileName= " " + ran;
				filenumberActual[ran]++;
				//System.out.println("filenumberActual["+ran+"]: "+filenumberActual[ran]);
				//System.out.println("percentageRandom["+ran+"]: "+percentageRandom[ran]);
			}
			else
			{
				ran=r.nextInt(9980)+20;
				FileName=" " + ran;
			}
				System.out.println(FileName);

	}
	
}
