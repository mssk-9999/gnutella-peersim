import java.util.*;

public class RunGnutella {

	public static void main(String[] args) {
		Random rand = new Random();
		boolean flag = true;
		long startTime=0;
		long stopTime =0;
		double averageRunTime=0.0;
		double averageHops=0.0;
		Files file = new Files();
		
		//make new graph
		GenerateGraph TestGraph = new GenerateGraph();
		
		int times= 10;
		for(int i=0; i<times; i++){
			//choose node to start search from
			int start =  rand.nextInt(TestGraph.n);
			//System.out.println("Start from " + start);
			
			//choose file to search for
			while(flag){
				file = new Files();
				
				//if the node already has this file keep generating
				if(TestGraph.graph.get(start).fileList.contains(file)){
					flag = true;
				}
				else{
					flag = false;
				}
			
			}

			//System.out.println("File to find is " + file.FileName);
			
			//take down start time
			startTime = System.currentTimeMillis();
			
			
			//execute a search
			//TestGraph.graph.get(start).Flood(TestGraph.graph.get(start).GenerateQuery(file));
			TestGraph.graph.get(start).RandomWalk(TestGraph.graph.get(start).GenerateQuery(file));
			
			//take down search end time
			stopTime = System.currentTimeMillis();
			
			averageRunTime += (stopTime-startTime);
			averageHops += TestGraph.graph.get(start).currentQuery.hopCount;
		
		}
		
		System.out.println("Average Time for 1000 runs is: " + (averageRunTime/times));
		System.out.println("Average Hops for 1000 runs is: " + (averageHops/times));
		
		System.out.println("Time for "+times+" run is: " + (averageRunTime));
		System.out.println("Hops for "+times+" run is: " + (averageHops));

	}

}
