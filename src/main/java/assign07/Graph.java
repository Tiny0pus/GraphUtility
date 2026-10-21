package assign07;

import java.util.List;
import java.util.ArrayList;
public class Graph <T> {



    public Graph()
    {

    }


    public List<T> breadthFirstSearch(T source, T destination)
    {
        return new ArrayList<T>();
    }

    public List<T> dijkstrasAlgorithm(T source, T destination)
    {
        return new ArrayList<T>();
    }
    public List<T> topoSort()
    {
        return new ArrayList<T>();
    }




    public static class Edge<T>
    {
        boolean isWeighted = false;
        // isBiDirectional
        boolean isBi = false;
        Vertex<T> front;
        Vertex<T> rear;

        public Edge(boolean isWeighted, boolean isBi, Vertex<T> front, Vertex<T> rear)
        {
            this.isWeighted = isWeighted;
            this.isBi = isBi;
            this.front = front;
            this.rear = rear;
        }
    }
}



