package peersim.core;

import peersim.config.FastConfig;
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
    /**
     * Creates a new {@link example.aggregation.FloodingQuery} protocol
     * instance.
     * 
     * @param prefix
     *            the component prefix declared in the configuration file.
     */
    public FloodingQuery(String prefix) {
        super(prefix);
    }

	//public static int count=0;

    /**
     * Using an underlying {@link Linkable} protocol choses a neighbor and
     * performs a variance reduction step.
     * 
     * @param node
     *            the node on which this component is run.
     * @param protocolID
     *            the id of this protocol in the protocol array.
     */
    public void nextCycle(Node node, int protocolID) {
        int linkableID = FastConfig.getLinkable(protocolID);
        Linkable linkable = (Linkable) node.getProtocol(linkableID);

		int f;
			((GnuNode) node).addToTable(2,1);
		//if( (f=((GnuNode) node).whichEmpty())!= -1)
		//	((GnuNode) node).addToTable(2,f);

		System.out.println(node  + "value: "+this.value+"\n");
		//System.out.println(node.routingTable[1]);
        if (linkable.degree() > 0) {

            //Node peer = linkable.getNeighbor(CommonState.r.nextInt(linkable
             //       .degree()));

            // Failure handling
            //if (!peer.isUp())
             //   return;

			/*
			//System.out.print(this.value + " neighbors: ");
			for( int i=0; i < linkable.degree(); i++)
			{
				Node peer = linkable.getNeighbor(i);
				FloodingQuery neighbor = (FloodingQuery) peer
				       .getProtocol(protocolID);
				//System.out.print(" " + neighbor.value);
			}
			//System.out.println();
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
        }
    }

}
