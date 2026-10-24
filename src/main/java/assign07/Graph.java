package assign07;

import java.util.List;
import java.util.ArrayList;
public class Graph <Type> {
    private ArrayList<Vertex<Type>> nodes;
    private ArrayList<Edge<Type>> edges;


    /**
     * Constructor to create a weighted or unweighted graph
     * @param sources - A list of raw data, where each element at a given index corresponds with the same index in 'destinations'
     * @param destinations - A list of raw data, corresponding with sources
     * @param weights
     */
    public Graph(List<Type> sources, List<Type> destinations, List<Double> weights)
    {
        if (sources.size() != destinations.size())
        {
            throw new IllegalArgumentException("Sources and Destinations must be equivalent sizes.");
        }

        for (int i = 0; i < sources.size(); i++)
        {
         Vertex<Type> node = new Vertex<>();
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
        public ArrayList<T> neighborEdges;

        public Vertex()
        {

        }

        public Vertex(T data)
        {
            this.data = data;
            this.distance = -1;
        }

        public Vertex(T data, int indegree)
        {
            this.data = data;
            this.distance = -1;
            this.indegree = indegree;
        }



    }

}




