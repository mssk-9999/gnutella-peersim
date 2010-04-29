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
	
	
	public int Flood(Query in){
		boolean fileFound = false;
		in.nodeIdsVisited.add(new NodeIds(nodeId));
		in.hopCount++;

		if(this.fileList != null){
			for(int i=0; i< fileList.size(); i++){
				//System.out.println(fileList.get(i).FileName+ " "+" " +in.file.FileName);
				if(fileList.get(i).FileName.equals(in.file.FileName) ){
					fileFound=true;
				}
				
			}
		}
		
		if(fileFound){
			//System.out.println("Found at " + nodeId);
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
						/*for(int j=0; j< fileList.size(); j++){
							System.out.println("Files are: " + fileList.get(j).FileName);
							
						}*/
						//System.out.println(nodeId);
						return neighbors.get(i).Flood(in);
					}
				}
			}
		}
		
		

		return -100;
	}
	
/*	public void RandomWalk(){
		//pick random neighbor to ask
		Random rand = new Random();
		int neighborToAsk= rand.nextInt(neighbors.size());
		neighbors.get(neighborToAsk).QueryResponse(GenerateQuery());
		
	}*/
	
	public Query GenerateQuery(Files search){
		
		
		Query query = new Query(nodeId, search);
		currentQuery =query;
		
		return query;
	}
	
}
