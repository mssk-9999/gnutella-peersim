package peersim.vector;

import peersim.config.*;
import peersim.core.*;

import java.util.Random;

public class GnutellaDistribution extends VectControl
{

//--------------------------------------------------------------------------
//Parameters
//--------------------------------------------------------------------------

//private static final String PAR_MAX = "max";

//private static final String PAR_MIN = "min";

// --------------------------------------------------------------------------
// Fields
// --------------------------------------------------------------------------


//private final Number min;


//private final Number max;


//private final double step;

private static final int file_number=20;
private static int[] percentageRandom=new int[file_number];
private static int[] filenumberActual=new int[file_number];


// --------------------------------------------------------------------------
// Initialization
// --------------------------------------------------------------------------

public GnutellaDistribution(String prefix)
{
	super(prefix);
}

// --------------------------------------------------------------------------
// Methods
// --------------------------------------------------------------------------

public boolean execute() {

	Random r = new Random();

	for(int a=0; a < file_number; a++)
	{
		percentageRandom[a]=r.nextInt(45) + 5;
		System.out.println(percentageRandom[a]);
	}
	
	/*
	if ( setter.isInteger() )
	{
		int n=Network.size();
		for(int i=0; i<n; ++i)
		{
			int ran=r.nextInt(5000);
			if( ran < 20 && filenumberActual[ran] < percentageRandom[ran])
			{
				setter.set(i, ran);
				filenumberActual[ran]++;
			}
			else
			{
				setter.set(i, r.nextInt()+20);
			}
		}
		for(int i=0; i<n; ++i) { System.out.println(getter.get(i));
		}
	}
	else
	{*/
		//	setter.set(i,i*step+min.doubleValue());
		//	System.out.println(i*step+min.doubleValue());
		int n=Network.size();
		for(int i=0; i<n; ++i)
		{
			int ran=r.nextInt(20);
			if(ran < 20 && filenumberActual[ran] < percentageRandom[ran])
			{
				setter.set(i, (double) ran);
				filenumberActual[ran]++;
				//System.out.println("filenumberActual["+ran+"]: "+filenumberActual[ran]);
				//System.out.println("percentageRandom["+ran+"]: "+percentageRandom[ran]);
			}
			else
			{
				ran=r.nextInt(4980)+20;
				setter.set(i, (double)ran);
	//			System.out.println(ran+" "+getter.get(i));
			}
		}

		/*
		for(int i=0; i<file_number; i++)
		{
			if(i==1)
			System.out.println(i + " " + filenumberActual[i]);
		}
		int[] cnt=new int[20];
		for(int j=0; j<n; j++)
		{

		for(int i=0; i<n; ++i)
		{
			if(getter.get(i).intValue() == j) 
				cnt[j]++;

			//System.out.println(getter.get(i));
		}
		System.out.println("found " + cnt[j]);

		}
		*/
	//}

	return false;
}


}
