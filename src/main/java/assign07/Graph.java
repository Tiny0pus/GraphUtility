package assign07;

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
        if (sources.size() != destinations.size())
        {
            throw new IllegalArgumentException("Sources and Destinations must be equivalent sizes.");
        }

        //Iterate through all 3 parameters and construct the graph
        for (int i = 0; i < sources.size(); i++)
        {
            Type srcData = sources.get(i);
            Type destData = destinations.get(i);
            Vertex<Type> sourceNode = null;
            Vertex<Type> destinationNode = null;
            Edge<Type> edge = null;
        	boolean srcExists = false;
            boolean destExists = false;
            //Before constructing nodes, check if either the source or destination nodes already exist
            for (Vertex<Type> node : nodes) {
                if (srcData.equals(node.data)) {
                    sourceNode = node;
                    srcExists = true;
                    destinationNode = new Vertex<>(destData);
                    edge = new Edge<>(weights.get(i), destinationNode);
                }
                if (destData.equals(node.data)) {
                    destinationNode = node;
                    destExists = true;
                    sourceNode = new Vertex<>(srcData);
                    edge = new Edge<>(weights.get(i), destinationNode);
                }
            }
            if (!srcExists && !destExists)
            {
                sourceNode = new Vertex<>(srcData);
                destinationNode = new Vertex<>(destData);
                edge = new Edge<>(weights.get(i), destinationNode);
            }

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




