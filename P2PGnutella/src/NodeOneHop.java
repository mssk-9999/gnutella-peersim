import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//public class NodeOneHop extends Node{
public class NodeOneHop {
	int nodeId;
	List<Files> fileList = new ArrayList<Files>();
	//List<List<OneHopNode>> neighborNeighbors;
	Query currentQuery;

	List<NodeOneHop> neighbors= new ArrayList<NodeOneHop>();
	
	public NodeOneHop(int idNum)
	{
		nodeId = idNum;
		AddFilesToList();
	
	}
	
	//constructor
	public NodeOneHop(int idNum, List<NodeOneHop> nodeList){
		nodeId= idNum;
		AddFilesToList();
		neighbors = nodeList;
	}

	public void addNeighbors( List<NodeOneHop> nodeList)
	{
		neighbors = nodeList;
	}

	
	public void AddFilesToList(){
		//files should appear in between 5 and 50 times total in the network
		Random rand = new Random();
		
		/*there fore given max possibility is .20 and the total number of files is 20
		the maximum numbers of papers a node should hold is four.
		Randomly choose the number of papers in a node and randomly choose the paper 
		that will be in that node*/
		//for(int i=0;i <= rand.nextInt(4); i++){
			fileList.add(new Files());
		//}
		
	}//end addFilesToList
	
	
	public int Flood(Query in){
		boolean fileFound = false;
		in.nodeIdsVisited.add(new NodeIds(nodeId));
		in.hopCount++;
		System.out.println(in.hopCount + " " +nodeId);

		if(this.fileList != null){
			for(int i=0; i< fileList.size(); i++){
				System.out.println(fileList.get(i).FileName+ " "+" " +in.file.FileName+"		" + nodeId);
				if(fileList.get(i).FileName.equals(in.file.FileName) ){
					fileFound=true;
				}
			}
		}

		for(int j= 0; j< neighbors.size(); j++){
			if(neighbors.get(j).nodeId !=0)
				for(int m=0; m< neighbors.get(j).fileList.size(); m++){
					System.out.println("	" + neighbors.get(j).fileList.get(m).FileName+ " "+" " +in.file.FileName+"		" + nodeId);
					if(neighbors.get(j).fileList.get(m).FileName.equals(in.file.FileName) )
						fileFound=true;
				}
		}
				
		
		if(fileFound){
			System.out.println("Found at " + nodeId);
			return nodeId;
		}
		else if(in.hopCount >= 7){
			//System.out.println("Exceed max limit ");
			return -1;
		}
		else{
			if(neighbors != null){
				for(int i= 0; i< neighbors.size(); i++){
					
					//zero node is special node and does not exist
					if(neighbors.get(i).nodeId !=0){
						/*
						for(int j=0; j< fileList.size(); j++){
							//System.out.print(" Files are: " + fileList.get(j).FileName);
							System.out.print(" " + fileList.get(j).FileName);
							
						}
						System.out.println("		"+ nodeId);
						//System.out.println();
						*/
						return neighbors.get(i).Flood(in);
					}
				}
			}
		}
			//System.out.println("Not Found ");

		return -100;
	}


	public int RandomWalk(Query in){
		boolean fileFound = false;
		in.nodeIdsVisited.add(new NodeIds(nodeId));
		in.hopCount++;
	//	System.out.println(in.hopCount + " " +nodeId);


		if(this.fileList != null){
			for(int i=0; i< fileList.size(); i++){
				//System.out.println(fileList.get(i).FileName+ " "+" " +in.file.FileName+"		" + nodeId);
				if(fileList.get(i).FileName.equals(in.file.FileName) ){
					fileFound=true;
				}
			}
		}


		for(int j= 0; j< neighbors.size(); j++){
			if(neighbors.get(j).nodeId !=0)
				for(int m=0; m< neighbors.get(j).fileList.size(); m++){
					//System.out.println("	" + neighbors.get(j).fileList.get(m).FileName+ " "+" " +in.file.FileName+"		" + nodeId);
					if(neighbors.get(j).fileList.get(m).FileName.equals(in.file.FileName) )
						fileFound=true;
				}
		}




		if(fileFound){
			//System.out.println("Found at " + nodeId);
			//System.out.println(in.hopCount);
			return nodeId;
		}
		else{
			if(neighbors != null){
				//pick random neighbor to ask
				Random rand = new Random();
				int neighborToAsk= rand.nextInt(neighbors.size());
				return neighbors.get(neighborToAsk).RandomWalk(in);
			}
		}

		return -2000;

		/*
		//pick random neighbor to ask
		Random rand = new Random();
		int neighborToAsk= rand.nextInt(neighbors.size());
		neighbors.get(neighborToAsk).QueryResponse(GenerateQuery());
		*/
	}
	
	public Query GenerateQuery(Files search){
		
		Query query = new Query(nodeId, search);
		currentQuery =query;
		
		return query;
	}
	
}
