package assign07;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;


import java.util.ArrayList;
public class Graph <Type> {
    private ArrayList<Vertex<Type>> nodes = new ArrayList<>();

    /**
     * Constructor to create a weighted or unweighted graph
     * @param sources - A list of raw data, where each element at a given index corresponds with the same index in 'destinations'
     * @param destinations - A list of raw data, corresponding with sources
     * @param weights - A list of edge weights, representing the relationship between 'sources' and 'destinations'
     */
    public Graph(List<Type> sources, List<Type> destinations, List<Double> weights)
    {
        //Ensure there are no edges with incomplete relationships
        if (sources.size() != destinations.size()|| sources.size() != weights.size())
        {
            throw new IllegalArgumentException("Sources and Destinations must be equivalent sizes.");
        }

        for(int i = 0; i < sources.size(); i++) {
			Vertex<Type> source = findOrCreateVertex(sources.get(i));
			Vertex<Type> destination = findOrCreateVertex(destinations.get(i));
			source.neighborEdges.add(new Edge<>(weights.get(i), destination));
            destination.indegree++;
      
        }
    }

    public List<Type> breadthFirstSearch(Type source, Type destination)
    {
        int costSoFar = 0;
        LinkedListQueue<Type> queue = new LinkedListQueue<>();
        Vertex<Type> sourceNode = findVertex(source);
        Vertex<Type> destNode = findVertex(destination);
        if (sourceNode == null || destination == null)
        {
            throw new IllegalArgumentException("Nodes must exist in graph.");
        }
        queue.offer(sourceNode);
        return new ArrayList<Type>();
    }

    public List<Type> dijkstrasAlgorithm(Type source, Type destination)
    {
        return new ArrayList<Type>();
    }
    public List<Type> topoSort()
    {
        return new ArrayList<Type>();
    }
    
    /**
     * find vertex that has type data
     * return null if it does not exist in the verticies list
     * 
     * @param data
     * @return - found vertex or null if it does not exist
     */
    private Vertex<Type> findVertex(Type data) {
		for(int i = 0; i < this.nodes.size(); i++) {
			if(nodes.get(i).data.equals(data))
				return nodes.get(i);
		}
		return null;
	}
	
    
    /**
     * call findVertex or create a new vertex and add to verticies list if vertex does not exist
     * 
     * @param data - Type object
     * @return - created or found vertex
     */
	private Vertex<Type> findOrCreateVertex(Type data) {
		Vertex<Type> vertex = findVertex(data);
		
		if(vertex == null) {
			vertex = new Vertex<>(data);
			nodes.add(vertex);
		}
		
		return vertex;
	}




    public static class Edge<T>
    {
        Vertex<T> next;
        double weight;
        public Edge(double weight, Vertex<T> next)
        {
            this.weight = weight;
            this.next = next;
        }
    }

    @Override
    public String toString()
    {
        for (Vertex<Type> node: nodes)
        {
            for (Edge<Type> edge: node.neighborEdges)
            {
                System.out.println(node.data + "(" + Double.toString(edge.weight) + ") ->" + edge.next.data + " (indegree: " + Integer.toString(edge.next.indegree) + ")");
            }
        }
        return "Done!";
    }
}




