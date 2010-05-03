import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Node {
	int nodeId;
	List<Files> fileList = new ArrayList<Files>();
	List<Node> neighbors= new ArrayList<Node>();
	Query currentQuery;
	
	public Node(int idNum)
	{
		nodeId = idNum;
	}
	
	//constructor
	public Node(int idNum, List<Node> nodeList){
		nodeId= idNum;
		AddFilesToList();
		neighbors = nodeList;
	}
	
	public void AddFilesToList(){
		//files should appear in between 5 and 50 times total in the network
		Random rand = new Random();
		
		/*there fore given max possibility is .20 and the total number of files is 20
		the maximum numbers of papers a node should hold is four.
		Randomly choose the number of papers in a node and randomly choose the paper 
		that will be in that node*/
		for(int i=0;i <= rand.nextInt(4); i++){
			fileList.add(new Files());
		}
	}//end addFilesToList
	
	//Flood asks every neighbor if they have the file
	public int Flood(Query in){
		boolean fileFound = false;
		in.nodeIdsVisited.add(new NodeIds(nodeId));
		in.hopCount++;

		if(this.fileList != null){
			for(int i=0; i< fileList.size(); i++){
				if(fileList.get(i).FileName.equals(in.file.FileName) ){
					fileFound=true;
				}
				
			}
		}
		
		if(fileFound){
			return nodeId;
		}
		else if(in.hopCount >= 7){
			return -1;
		}
		else{
			if(neighbors != null){
				for(int i= 0; i< neighbors.size(); i++){
					
					//zero node is special node and does not exist
					if(neighbors.get(i).nodeId !=0){
						return neighbors.get(i).Flood(in);
					}
				}
			}
		}
		
		

		return -100;
	}
	
	//random walk uses a random walker who chooses a random node to walk to
	public int RandomWalk(Query in){
		//pick random neighbor to ask
		Random rand = new Random();
		int neighborToAsk = 0;
		
		in.nodeIdsVisited.add(new NodeIds(nodeId));
		in.hopCount++;
		
		boolean fileFound=false;
		if(neighbors == null || (neighbors.size() <= 0)){
			return -100;
		}
		else{
			neighborToAsk =  rand.nextInt(neighbors.size());
		}
		
		if(this.fileList != null){
			for(int i=0; i< fileList.size(); i++){
				if(fileList.get(i).FileName.equals(in.file.FileName) ){
					fileFound=true;
				}
				
			}
		}
		
		if(fileFound){
			return nodeId;
		}
		else if(in.hopCount >= 100){
			return -1;
		}
		else{
			if(neighbors != null){
				return neighbors.get(neighborToAsk).RandomWalk(in);
			}
		}
		
		return -100;
		
		
	}//end method Random Walk
	
	/*Method RandomWalk with neighbor tables look ahead: This method checks to see if its neighbors lists of files have
	 * the desired query within them. If so that neighbor is walked to if not it randomly choosed*/
	public int RandomWalkWithNeighborTable(Query in){
		//pick random neighbor to ask
		Random rand = new Random();
		int neighborToAsk = 0;
		boolean neighborHasFile=false;
		int neighborWithFile =0;
		
		in.nodeIdsVisited.add(new NodeIds(nodeId));
		in.hopCount++;
		
		boolean fileFound=false;
		
		//Begin Lookup of neighborTable
		if(neighbors != null){
			for(int i= 0; i< neighbors.size(); i++){
				
				if(this.fileList != null){
					for(int j=0; j< fileList.size(); j++){
						if(fileList.get(j).FileName.equals(in.file.FileName) ){
							neighborHasFile=true;
							neighborWithFile = i;
							break;
						}
						
					}
				}
			}
		}
		
		
		
		if(neighbors == null || (neighbors.size() <= 0)){
			return -100;
		}
		else if(neighborHasFile){
			neighborToAsk = neighborWithFile;
		}
		else{
			neighborToAsk =  rand.nextInt(neighbors.size());
		}
		
		if(this.fileList != null){
			for(int i=0; i< fileList.size(); i++){
				if(fileList.get(i).FileName.equals(in.file.FileName) ){
					fileFound=true;
				}
				
			}
		}
		
		if(fileFound){
			return nodeId;
		}
		else if(in.hopCount >= 100){
			return -1;
		}
		else{
			if(neighbors != null){
				return neighbors.get(neighborToAsk).RandomWalkWithNeighborTable(in);
			}
		}
		
		return -100;
		
		
	}//end method Random Walk with neighbor table
	
	
	
	public Query GenerateQuery(Files search){
		
		
		Query query = new Query(nodeId, search);
		currentQuery =query;
		
		return query;
	} // end method GenerateQuery
	
	
	
}