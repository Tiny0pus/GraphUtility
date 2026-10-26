package assign07;

import java.util.List;

import assign07.GraphUtility.Edge;
import assign07.GraphUtility.Vertex;

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
			Vertex source = findOrCreateVertex(sources.get(i));
			Vertex destination = findOrCreateVertex(destinations.get(i));
			
			source.edges.add(new Edge(destination, weights.get(i)));
      
        }
    }


    public List<Type> breadthFirstSearch(Type source, Type destination)
    {
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
    private Vertex findVertex(Type data) {
		for(int i = 0; i < this.verticies.size(); i++) {
			if(verticies.get(i).data.equals(data))
				return verticies.get(i);
		}
		return null;
	}
	
    
    /**
     * call findVertex or create a new vertex and add to verticies list if vertex does not exist
     * 
     * @param data - Type object
     * @return - created or found vertex
     */
	private Vertex findOrCreateVertex(Type data) {
		Vertex vertex = findVertex(data);
		
		if(vertex == null) {
			vertex = new Vertex(data);
			verticies.add(vertex);
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


    public static class Vertex<T>
    {
        public T data;
        public int distance;
        public Vertex<T> cameFrom;
        public int indegree;
        public ArrayList<Edge<T>> neighborEdges;

        public Vertex(T data)
        {
            this.data = data;
            this.distance = -1;
        }
        
        public void addEdge(Edge<T>neighbor) {
        	this.neighborEdges.add(neighbor);
        }


 
//        public Vertex(T data, int indegree)
//        {
//            this.data = data;
//            this.distance = -1;
//            this.indegree = indegree;
//        }
        



    }

}




