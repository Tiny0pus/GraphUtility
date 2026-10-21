package assign07;

/**
 * Class that represents the Vertices of a Graph
 * @param <T> - Generic data that each Vertex holds
 */
public class Vertex<T>
{
    private T data;
    private int distance;
    private Vertex<T> cameFrom;
    private int indegree;

    private Vertex(T data)
    {
        this.data = data;
    }


    public int getDistance() {
        return distance;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
