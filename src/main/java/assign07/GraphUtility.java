package assign07;

import java.util.List;

/**
 * driver class to create and perform tasks on the Graph Object
 * 
 * @author - Steven Fletcher and Ducan McGuire
 * @version - Oct 28 2026
 */
public class GraphUtility {

	/**
	 * find shortest path of the graph from source to destination
	 * 
	 * @param <Type> what the graph stores
	 * 
	 * @param sources - a list of Type elements that represents a Vertex to start from
	 * @param destinations - a list of Type elements that represents a Vertext to end connecting from start
	 * @param srcData - The starting vertex of the shortest path to look for
	 * @param dstData - The ending vertex of the shortest path to look for
	 * 
	 * @return - the shortest path from srcData to dstData, throws IllegalArgumenException if path does not exist or given Vertex for srcData or dstData does not exist in this graph
	 */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
    {
            return new Graph<Type>(sources, destinations).breadthFirstSearch(srcData, dstData);
    }

    /**
     * find the shortest path considering the weight of the edges
     * 
     * @param <Type> what the graph stores
	 * 
	 * @param sources - a list of Type elements that represents a Vertex to start from
	 * @param destinations - a list of Type elements that represents a Vertext to end connecting from start
	 * @param weights - the list of weight for the edges connecting sources and the destination
	 * @param srcData - The starting vertex of the shortest path to look for
	 * @param dstData - The ending vertex of the shortest path to look for
	 * 
     * @return the shortest by weight from srcData to dstData, throws IllegalArgumentException if path does not exist or given Vertex for srcData or dstData does not exist in this graph
     */
    public static <Type> List<Type> shortestWeightedPath(List<Type> sources, List<Type> destinations, List<Double> weights, Type srcData, Type dstData)
    {
        return new Graph<Type>(sources, destinations, weights).dijkstrasAlgorithm(srcData, dstData);
    }
    
    /**
     *@param <Type> what the graph stores
	 * 
	 * @param sources - a list of Type elements that represents a Vertex to start from
	 * @param destinations - a list of Type elements that represents a Vertext to end connecting from start
	 * 
     * @return - topologically sorted graph, IllegalArgumentException if given graph was not a DAG(Directed Acylic Graph)
     */
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations)
    {
        return new Graph<Type>(sources, destinations).topoSort();
    }
}
