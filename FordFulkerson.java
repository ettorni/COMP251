

import java.io.*;
import java.util.*;

//sources: geeksforgeeks dfs with graphs, wikipedia folk-fulkerson

public class FordFulkerson {
	
	// utility program
	public static ArrayList<Integer> getAdjList(int n, WGraph graph) {
		ArrayList<Integer> aj = new ArrayList<Integer>();
		for (Edge e : graph.getEdges()) {
			if (e.nodes[0] == n && e.weight > 0) {
				aj.add(e.nodes[1]);
			}
		}
		return aj;
	}

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph) {
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		// ADD CODE HERE
		ArrayList<Integer> v = new ArrayList<Integer>();
		Stack<Integer> s = new Stack<Integer>();
		s.push(source);

		while (!s.empty()) {
			int n1 = s.pop();
			v.add(n1);
			while (Stack.size() != 0 && (graph.getEdge(Stack.get(Stack.size() - 1), n1) == null
					|| graph.getEdge(Stack.get(Stack.size() - 1), n1).weight == 0)) {
				Stack.remove(Stack.size() - 1);
			}

			ArrayList<Integer> adj = getAdjList(n1, graph);
			Stack.add(n1);
			int temp = 0;

			while (temp < adj.size()) {
				if (!v.contains(adj.get(temp))) {
					if (adj.get(temp) == destination) {
						Stack.add(destination);
						s.clear();
						break;
					} else {
						s.push(adj.get(temp));
					}
				}
				temp++;
			}
		}
		return Stack;
	}

	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath) {
		String answer = "";
		String myMcGillID = "260738557"; // Please initialize this variable with your McGill ID
		int maxFlow = 0;
		// CODE HEREE/*
		WGraph r = new WGraph(graph);
		WGraph c = new WGraph(graph);

		for (Edge e : graph.listOfEdgesSorted()) {
			e.weight = 0;
		}

		while (pathDFS(source, destination, r).contains(source)
				&& pathDFS(source, r.getDestination(), r).contains(destination)) {
			ArrayList<Integer> path = pathDFS(source, destination, r);
			int bn = Integer.MAX_VALUE;

			for (int i = 0; i < path.size() - 1; i++) {
				Edge e = r.getEdge(path.get(i), path.get(i + 1));
				if (e != null && e.weight < bn) {
					bn = e.weight;
				}
			}

			for (int j = 0; j < path.size() - 1; j++) {
				int n0 = path.get(j);
				int n1 = path.get(j + 1);
				Edge e = graph.getEdge(n0, n1);
				if (e == null) {
					graph.setEdge(n0, n1, e.weight - bn);
				} else {
					graph.setEdge(n0, n1, e.weight + bn);

				}
			}

			for (int k = 0; k < path.size() - 1; k++) {
				int n0 = path.get(k);
				int n1 = path.get(k + 1);
				Edge e = graph.getEdge(n0, n1);
				Edge cape = c.getEdge(n0, n1);

				if (e.weight <= cape.weight) {
					r.setEdge(n0, n1, cape.weight - e.weight);
				} else if (e.weight > 0) {
					Edge re = r.getEdge(n0, n1);
					if (re == null) {
						Edge backe = new Edge(n0, n1, e.weight);
						r.addEdge(backe);
					} else {
						r.setEdge(n1, n0, e.weight);
					}
				}

			}

			maxFlow += bn;
		}

		answer += maxFlow + "\n" + graph.toString();
		writeAnswer(filePath + myMcGillID + ".txt", answer);
		System.out.println(answer);
	}

	public static void writeAnswer(String path, String line) {
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(line + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
		fordfulkerson(g.getSource(), g.getDestination(), g, f.getAbsolutePath().replace(".txt", ""));
	}
}