package peersim.vector;

import peersim.config.*;
import peersim.core.*;

import java.util.Random;

/**
 * Initializes a protocol vector with values in the range [{@value #PAR_MIN}, 
 * {@value #PAR_MAX}] (inclusive both ends), linearly increasing.
 * @see VectControl
 * @see peersim.vector
 */
public class GnutellaDistribution extends VectControl
{

//--------------------------------------------------------------------------
//Parameters
//--------------------------------------------------------------------------

/**
 * Upper end of the interval..
 * @config
 */
//private static final String PAR_MAX = "max";

/**
 * Lower end of the interval. Defaults to -{@value #PAR_MAX}.
 * @config
 */
//private static final String PAR_MIN = "min";

// --------------------------------------------------------------------------
// Fields
// --------------------------------------------------------------------------

/** Minimum value */
//private final Number min;

/** Maximum value */
//private final Number max;

/** Step value */
//private final double step;

private final int file_number=20;
private static double[] percentage=new double[file_number];


// --------------------------------------------------------------------------
// Initialization
// --------------------------------------------------------------------------

/**
 * Standard constructor that reads the configuration parameters.
 * Invoked by the simulation engine.
 * @param prefix the configuration prefix for this class
 */
public GnutellaDistribution(String prefix)
{
	super(prefix);
	
	/*
	// Read parameters based on type
	if (setter.isInteger()) {
		max=Long.valueOf(Configuration.getLong(prefix + "." + PAR_MAX));
		min=Long.valueOf(Configuration.getLong(prefix + "." + PAR_MIN, 
				-max.longValue()));
		step= (max.longValue()-min.longValue())/
			((double)(Network.size()-1));
	} else { // we know it's double or float
		max = new Double(Configuration.getDouble(prefix+"."+PAR_MAX));
		min = new Double(Configuration.getDouble(prefix+"."+PAR_MIN, 
				-max.doubleValue()));
		step= (max.doubleValue()-min.doubleValue())/(Network.size()-1);
	}
	*/
}

// --------------------------------------------------------------------------
// Methods
// --------------------------------------------------------------------------

/**
 * Initializes a protocol vector with values in the range [{@value #PAR_MIN}, 
 * {@value #PAR_MAX}] (inclusive both ends), linearly increasing.
 * @return always false
 */
public boolean execute() {

	Random r = new Random();

	for(int a=0; a < file_number; a++)
	{
		percentage[a]=r.nextDouble()*0.9 + 0.1;
	}
	
	if ( setter.isInteger() )
	{
		double n=Network.size();
		for(int i=0; i<n; ++i)
		{
			double p=0;
			// we avoid the entire expression being cast to double
			if(p < percentage[i] && i < 20)
			{
				ran=r.nextInt(20);
				setter.set(i, ran);
				p= (ran)/n; 

			}
			else
			{
				setter.set(i, r.nextInt());
			}
			System.out.println(Math.round(i*step)+min.longValue());
		}
	}
	/*
	else
	{
		for(int i=0; i<Network.size(); ++i)
		{
			setter.set(i,i*step+min.doubleValue());
			System.out.println(i*step+min.doubleValue());
		}
	}
	*/

	return false;
}


}


