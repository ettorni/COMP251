import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	ArrayList<Edge> sorted = g.listOfEdgesSorted();
    	ArrayList<Edge> inOrder  = new ArrayList<Edge>();
    	WGraph temp1 = new WGraph();
    	DisjointSets set = new DisjointSets(g.getNbNodes());
    	
    	for (int i = 0; i < sorted.size(); i++) {
    		if (IsSafe(set,sorted.get(i))) {
    			inOrder.add(sorted.get(i));
    			set.union(sorted.get(i).nodes[0], sorted.get(i).nodes[1]);
    		}
    	}
    	
    	for (int i = 0; i < inOrder.size(); i++) {
    		temp1.addEdge(inOrder.get(i));
    	}
    	
        return temp1;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
    	 /* Fill this method (The statement return 0 is here only to compile) */
    	int node1 = p.find(e.nodes[0]);
    	int node2 = p.find(e.nodes[1]);
    	if (node1 != node2) {
            return true;
    	} else {
    		return false;
    	}	
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}

