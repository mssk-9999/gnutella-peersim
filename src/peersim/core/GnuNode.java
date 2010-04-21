
package peersim.core;


import peersim.config.*;


public class GnuNode implements Node 
{

// ================= fields ========================================
// =================================================================

/** used to generate unique IDs */
private static long counterID = -1;

/**
* The protocols on this node.
*/
protected Protocol[] protocol = null;

/**
* The current index of this node in the node
* list of the {@link Network}. It can change any time.
* This is necessary to allow
* the implementation of efficient graph algorithms.
*/
private int index;

/**
* The fail state of the node.
*/
protected int failstate = Fallible.OK;
//protected int failstate = Fallible.DOWN;

/**
* The ID of the node. It should be final, however it can't be final because
* clone must be able to set it.
*/
private long ID;


private final static int maxReach = 5;
private long[] routingTable = new long[maxReach];

// ================ constructor and initialization =================
// =================================================================

/** Used to construct the prototype node. This class currently does not
* have specific configuration parameters and so the parameter
* <code>prefix</code> is not used. It reads the protocol components
* (components that have type {@value peersim.core.Node#PAR_PROT}) from
* the configuration.
*/
public GnuNode(String prefix) {
	
	String[] names = Configuration.getNames(PAR_PROT);
	CommonState.setNode(this);
	ID=nextID();
	protocol = new Protocol[names.length];
	for (int i=0; i < names.length; i++) {
		CommonState.setPid(i);
		Protocol p = (Protocol) 
			Configuration.getInstance(names[i]);
		protocol[i] = p; 
	}
	for( int i=0; i < maxReach; i++)
	{	routingTable[i]=-1; }
}


// -----------------------------------------------------------------

public Object clone() {
	
	GnuNode result = null;
	try { result=(GnuNode)super.clone(); }
	catch( CloneNotSupportedException e ) {} // never happens
	result.protocol = new Protocol[protocol.length];
	CommonState.setNode(result);
	result.ID=nextID();
	for(int i=0; i<protocol.length; ++i) {
		CommonState.setPid(i);
		result.protocol[i] = (Protocol)protocol[i].clone();
	}
	return result;
}

// -----------------------------------------------------------------

/** returns the next unique ID */
private long nextID() {

	return counterID++;
}

// =============== public methods ==================================
// =================================================================


public void setFailState(int failState) {
	
	// after a node is dead, all operations on it are errors by definition
	if(failstate==DEAD && failState!=DEAD) throw new IllegalStateException(
		"Cannot change fail state: node is already DEAD");
	switch(failState)
	{
		case OK:
			failstate=OK;
			break;
		case DEAD:
			//protocol = null;
			index = -1;
			failstate = DEAD;
			for(int i=0;i<protocol.length;++i)
				if(protocol[i] instanceof Cleanable)
					((Cleanable)protocol[i]).onKill();
			break;
		case DOWN:
			failstate = DOWN;
			break;
		default:
			throw new IllegalArgumentException(
				"failState="+failState);
	}
}

// -----------------------------------------------------------------

public int getFailState() { return failstate; }

// ------------------------------------------------------------------

public boolean isUp() { return failstate==OK; }

// -----------------------------------------------------------------

public Protocol getProtocol(int i) { return protocol[i]; }

//------------------------------------------------------------------

public int protocolSize() { return protocol.length; }

//------------------------------------------------------------------

public int getIndex() { return index; }

//------------------------------------------------------------------

public void setIndex(int index) { this.index = index; }
	
//################################################################################
//

protected int whichEmpty()
{   
	int i=-1;
	while(routingTable[++i]!=-1) {}
	if(i<maxReach) return  i;
	else return -1;
}


//protected boolean addToTable(int nodeID, int pos) 
protected boolean addToTable(long nodeID) 
{
	int i=0;
	while( i<maxReach-1 && routingTable[i] != -1) 
	{
		i++;
	}
	if(i>=maxReach-1) return false;
	routingTable[i]=nodeID;
	//System.out.println("end of routing table");
	return true;
}

	
//################################################################################
//------------------------------------------------------------------

/**
* Returns the ID of this node. The IDs are generated using a counter
* (i.e. they are not random).
*/
public long getID() { return ID; }

//------------------------------------------------------------------

public String toString() 
{
	StringBuffer buffer = new StringBuffer();
	buffer.append("ID: "+ID+" index: "+index+"\n");
	for(int i=0; i<protocol.length; ++i)
	{
		buffer.append("protocol["+i+"]="+protocol[i]+"\n");
	}
	for(int i=0; i<maxReach; i++)
	{
		buffer.append("routingTable["+i+"]="+routingTable[i]+"\n");
	}
	return buffer.toString();
}

//------------------------------------------------------------------

/** Implemented as <code>(int)getID()</code>. */
public int hashCode() { return (int)getID(); }

}


