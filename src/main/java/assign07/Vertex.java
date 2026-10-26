package assign07;

import java.util.ArrayList;

public class Vertex<T>
{
    public T data;
    public int distance;
    public Vertex<T> cameFrom;
    public int indegree;
    public ArrayList<Graph.Edge<T>> neighborEdges;

    public Vertex(T data)
    {
        neighborEdges = new ArrayList<>();
        this.data = data;
        this.distance = -1;
    }
}



