package peersim.core;

import peersim.config.FastConfig;
import peersim.config.Configuration;
import peersim.vector.SingleValueHolder;
import peersim.cdsim.CDProtocol;

/**
 * This class provides an implementation for the averaging function in the
 * aggregation framework. When a pair of nodes interact, their values are
 * averaged. The class subclasses {@link SingleValueHolder} in
 * order to provide a consistent access to the averaging variable value.
 *
 * Note that this class does not override the clone method, because it does
 * not have any state other than what is inherited from
 * {@link SingleValueHolder}.
 * 
 */
public class FloodingQuery extends SingleValueHolder implements CDProtocol {

protected static final String PAR_TARGET = "target";
protected static final String PAR_START = "start";
private final int target;
private final int start;
    /**
     * Creates a new {@link example.aggregation.FloodingQuery} protocol
     * instance.
     * 
     * @param prefix
     *            the component prefix declared in the configuration file.
     */
    public FloodingQuery(String prefix) {
        super(prefix);
        target = (Configuration.getInt(prefix + "." + PAR_TARGET, 0));
        start = (Configuration.getInt(prefix + "." + PAR_START, 0));
    }

	//public static int count=0;

    /**
     * Using an underlying {@link Linkable} protocol choses a neighbor and
     * performs 
     * 
     * @param node
     *            the node on which this component is run.
     * @param protocolID
     *            the id of this protocol in the protocol array.
     */
    public void nextCycle(Node node, int protocolID) 
	{
		//	if( node.isUp() ) node.setFailState(2);;
		if ( node.getID() != start && node.isUp())
		{

			System.out.println( "sleeping "+node.getID() );
			if( node.isUp() ) node.setFailState(2);;
			return; 
		}
		//if( !node.isUp() )
		//if ( node.getID() == start && !node.isUp())
		//{ System.out.println("wake up " + node.getID()) ;node.setFailState(Fallible.OK);}



		//else
		//setAllDown();

		//((GnuNode) node).addToTable(2,1);
		//if( (f=((GnuNode) node).whichEmpty())!= -1)
		//	((GnuNode) node).addToTable(2,f);

		//System.out.println(node  + "value: "+this.value+"\n");
		//System.out.println(node.routingTable[1]);
		//System.out.println( this.value + " ");
			/*
        int linkableID = FastConfig.getLinkable(protocolID);
        Linkable linkable = (Linkable) node.getProtocol(linkableID);
		System.out.print( node.getID());
        if (linkable.degree() > 0) {

            //Node peer = linkable.getNeighbor(CommonState.r.nextInt(linkable
             //       .degree()));

			//System.out.print("ID " + node.getID() + " Value " + this.value + " Neighbors: ");
			for( int i=0; i < linkable.degree(); i++)
			{
				Node peer = linkable.getNeighbor(i);
				//if(!peer.isUp()){ System.out.println("wake up " + peer.getID()) ;peer.setFailState(1);}
				FloodingQuery neighbor = (FloodingQuery) peer
				       .getProtocol(protocolID);
				//System.out.print(" " +peer.getID());
				//System.out.println(peer.getID() + " ");
				//System.out.print(neighbor.value+" ");
				//System.out.println(node.routingTable[1]);
				if( target==neighbor.value)
				{
					//System.out.println(target + " in "+ node.getID()+ "'s neighborhood") ;
				}

			}
			System.out.println();


			*/



			

            //FloodingQuery neighbor = (FloodingQuery) peer
             //      .getProtocol(protocolID);

			//System.out.print("before "+this.value+"\t"+neighbor.value);
			//System.out.println(this.value+"\t"+neighbor.value);
			//System.out.println(count++ + "\t"+this.value);
			//System.out.println(this.ID+ " " + this.value);

//            double mean = (this.value + neighbor.value) / 2;
 //           this.value = mean;
  //          neighbor.value = mean;
			//System.out.println("\tafter " + this.value+"\t" + neighbor.value);
        //}
    }

	//public void setAllDown()
	//{
	//	if( !node.isUp() ) node.setFailState(2);;
	//}




}
