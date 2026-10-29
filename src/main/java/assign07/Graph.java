package assign07;

import javax.swing.*;

import assign07.Graph.Edge;
import assign07.Graph.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

import java.util.ArrayList;
/**
 * this class represents a graph object
 * 
 * @param <Type> of what elements the graph holds
 * 
 * @author - Steven Fletcher and Ducan Mcguire
 * @version - Oct 28 2026
 */
public class Graph <Type> {
    private HashMap<Type, Vertex> nodes = new HashMap<>();

    
    public Graph() {
    	this.nodes = new HashMap<>;
    }
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
			source.neighborEdges.add(new Edge(weights.get(i), destination));
            destination.indegree++;
      
        }
    }

    /**
     * constructor of graph
     * 
     * @param sources - from what vertex
     * @param destination - to what vetex
     */
    public Graph(List<Type> sources, List<Type> destinations)
    {
        //Ensure there are no edges with incomplete relationships
        if (sources.size() != destinations.size())
        {
            throw new IllegalArgumentException("Sources and Destinations must be equivalent sizes.");
        }

        for(int i = 0; i < sources.size(); i++) {
            Vertex source = findOrCreateVertex(sources.get(i));
            Vertex destination = findOrCreateVertex(destinations.get(i));
            source.neighborEdges.add(new Edge(0.0, destination));
            destination.indegree++;

        }
    }
    
    /**
     * performs a breadth frist serach from given source to given destination vetex
     * return the path if exists, throws IllegalArgument exception if the path does not exist or the given vertex does not exist
     * 
     * @param source - starting vertex
     * @param destination - target vertex
     * @return the path in a from of a list containing all the vertex it goes through
     */
    public List<Type> breadthFirstSearch(Type source, Type destination)
    {
        LinkedList<Type> path = new LinkedList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        Vertex sourceNode = findVertex(source);
        Vertex destNode = findVertex(destination);
        if (sourceNode == null || destination == null)
        {
            throw new IllegalArgumentException("Vertex must exist in graph.");
        }
        
        //the start is given a distance of 0
        queue.offer(sourceNode);
        sourceNode.distance = 0;
        
        while (!queue.isEmpty())
        {
            Vertex currentVertex = queue.poll();
            if (currentVertex.data.equals(destination))
            {
                currentVertex = destNode;
                for (int i = 0; i < destNode.distance + 1; i++)
                {
                    path.addFirst(currentVertex.data);
                    currentVertex = currentVertex.cameFrom;
                }
                return path;
            }
            for (Edge edge : currentVertex.neighborEdges)
            {
                if (edge.next.distance > 0)
                {
                    continue;
                }
                queue.offer(edge.next);
                edge.next.distance = currentVertex.distance + 1;
                edge.next.cameFrom = currentVertex;
            }
        }

        if (path.isEmpty())
        {
            throw new IllegalArgumentException("No path between source and destination exists.");
        }
        return path;
    }

    /**
     * perform dijkstrasAlgorithm(find shortest path by weight)
     * thorws Illegal arugment excpetion if path does not exist or the given vertex does not exist
     * 
     * @param source - starting vertex
     * @param destination - the target vertex
     * @return the shortest path by weight in a form of a list containing all the vertex it went through
     */
    public List<Type> dijkstrasAlgorithm(Type source, Type destination)
    {
        Vertex srcVertex = findVertex(source);
        Vertex destVertex = findVertex(destination);
        LinkedList<Type> path = new LinkedList<>();
        if (srcVertex == null || destVertex == null)
        {
            throw new IllegalArgumentException("Source and destination must exist in graph!");
        }
        if (source.equals(destination))
        {
            path.add(source);
            return path;
        }
        ArrayList<Vertex> unvisitedNodes = new ArrayList<>(nodes.values());
        srcVertex.bestKnownCost = 0;
        while (!unvisitedNodes.isEmpty())
        {
            unvisitedNodes.sort((node1, node2) -> Double.compare(node2.bestKnownCost, node1.bestKnownCost));
            Vertex currentNode = unvisitedNodes.getLast();
            if (currentNode.data.equals(destination))
            {
                break;
            }
            for (Edge edge : currentNode.neighborEdges)
            {
            	//check if the new path it is going to take is cheaper(shorter) than the cheapest path now
            	double newCost = currentNode.bestKnownCost+edge.weight;
                if (edge.next.bestKnownCost > newCost)
                {
                    edge.next.bestKnownCost = newCost;
                    edge.next.cameFrom = currentNode;
                }
            }
            unvisitedNodes.removeLast();

        }
        Vertex pathFinder = destVertex;
        while (pathFinder.cameFrom != null)
        {
            path.addFirst(pathFinder.data);
            pathFinder = pathFinder.cameFrom;
        }
        if (path.isEmpty())
        {
            throw new IllegalArgumentException("No path between source and destination exists!");
        }
        path.addFirst(pathFinder.data);
        return path;


    }
    /**
     * perform a topological sort on the graph
     * 
     * @return - topologically sorted graph
     */
    public List<Type> topoSort()
    {
        LinkedList<Type> sortedGraph = new LinkedList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex node : nodes.values())
        {
            if (node.indegree == 0)
            {
                queue.offer(node);
            }
        }
        Vertex currentNode;
        while (!queue.isEmpty())
        {
            currentNode = queue.poll();
            sortedGraph.offer(currentNode.data);
            for (Edge edge : currentNode.neighborEdges)
            {

                edge.next.indegree--;
                if (edge.next.indegree == 0 && !queue.contains(edge.next))
                {
                    queue.offer(edge.next);
                    edge.next.cameFrom = currentNode;
                }
            }
        }
        if (!(sortedGraph.size() == nodes.size()))
            throw new IllegalArgumentException("Can only sort Directed Acyclic Graphs (DAGs)");
        return sortedGraph;
    }

    /**
     * find vertex that has type data
     * return null if it does not exist in the verticies list
     * 
     * @param data
     * @return - found vertex or null if it does not exist
     */
    private Vertex findVertex(Type data) {
		return nodes.get(data);
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
			nodes.put(data, vertex);
		}
		return vertex;
	}


	/**
	 * class representing the edge of the graph
	 */
    public class Edge
    {
        Vertex next;
        double weight;
        public Edge(double weight, Vertex next)
        {
            this.weight = weight;
            this.next = next;
        }
    }


    /**
     * class representing the vertex of the graph
     */
    public class Vertex
    {
        public Type data;
        public int distance;
        public double bestKnownCost;
        public Vertex cameFrom;
        public int indegree;
        public ArrayList<Edge> neighborEdges;

        public Vertex(Type data)
        {
            neighborEdges = new ArrayList<>();
            this.data = data;
            this.distance = -1;
            bestKnownCost = Double.POSITIVE_INFINITY;
        }
    }
    
    /**
     * add a new edge in between two given vertex
     * 
     * @param source - from this vertex
     * @param destination - to this vertex
     */
    public void addEdge(Type source, Type destination) {
  	 Vertex sourceVertex = this.findOrCreateVertex(source);
  	 Vertex destinationVertex = this.findOrCreateVertex(destination);
  	 if(sourceVertex != null && destinationVertex != null) {
  		 Edge addedEdge = new Edge(0.0, destinationVertex);
  		 sourceVertex.neighborEdges.add(addedEdge);
  	 }

    /**
     * class used to represent the graph as a text(use for trouble shooting)
     */
    @Override
    public String toString()
    {
        for (Vertex node: nodes.values())
        {
            for (Edge edge: node.neighborEdges)
            {
                System.out.println(node.data + " -> " + edge.next.data);
            }
        }
        return "Done!";
    }
}




