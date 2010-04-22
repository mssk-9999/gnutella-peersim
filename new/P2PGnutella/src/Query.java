import java.util.*;

public class Query {
	int from;
	Files file;
	List<NodeIds> nodeIdsVisited = new ArrayList<NodeIds>();
	int hopCount;
	
	public Query(int nodeId, Files fileName){
		from = nodeId;
		file = fileName;
		hopCount =0;
	}
	
}
