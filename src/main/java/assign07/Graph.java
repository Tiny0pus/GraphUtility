package assign07;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;


import java.util.ArrayList;
public class Graph <Type> {
    private ArrayList<Vertex> nodes = new ArrayList<>();

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

    public List<Type> breadthFirstSearch(Type source, Type destination)
    {
        LinkedList<Type> path = new LinkedList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        Vertex sourceNode = findVertex(source);
        Vertex destNode = findVertex(destination);
        if (sourceNode == null || destination == null)
        {
            throw new IllegalArgumentException("Nodes must exist in graph.");
        }
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

    public List<Type> dijkstrasAlgorithm(Type source, Type destination)
    {
        Vertex srcVertex = findVertex(source);
        Vertex destVertex = findVertex(destination);
        if (srcVertex == null || destVertex == null)
        {
            throw new IllegalArgumentException("Source and destination must exist in graph!");
        }
        ArrayList<Type> path = new ArrayList<>();
        ArrayList<Vertex> unvisitedNodes = new ArrayList<>(nodes);
        srcVertex.bestKnownCost = 0;
        while (!unvisitedNodes.isEmpty())
        {
            unvisitedNodes.sort((node1, node2) -> Double.compare(node2.bestKnownCost, node1.bestKnownCost));
            Vertex currentNode = unvisitedNodes.getLast();
            for (Edge edge : currentNode.neighborEdges)
            {
                double newCost = edge.weight;
                if (edge.next.bestKnownCost + newCost > currentNode.bestKnownCost + newCost || edge.next.bestKnownCost == -1)
                {
                    edge.next.bestKnownCost = currentNode.bestKnownCost + newCost;
                    edge.next.cameFrom = currentNode;
                }
            }

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
        return path;


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
	private Vertex findOrCreateVertex(Type data) {
		Vertex vertex = findVertex(data);
		
		if(vertex == null) {
			vertex = new Vertex(data);
			nodes.add(vertex);
		}
		
		return vertex;
	}


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
            bestKnownCost = -1;
        }
    }


    @Override
    public String toString()
    {
        for (Vertex node: nodes)
        {
            for (Edge edge: node.neighborEdges)
            {
                System.out.println(node.data + "(" + Double.toString(edge.weight) + ") ->" + edge.next.data + " (indegree: " + Integer.toString(edge.next.indegree) + ")");
            }
        }
        return "Done!";
    }
}




