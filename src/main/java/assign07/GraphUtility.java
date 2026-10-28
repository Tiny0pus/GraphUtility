package assign07;

import java.util.List;

public class GraphUtility {

    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
    {
            return new Graph<Type>(sources, destinations).breadthFirstSearch(srcData, dstData);
    }

    public static <Type> List<Type> shortestWeightedPath(List<Type> sources, List<Type> destinations, List<Double> weights, Type srcData, Type dstData)
    {
        return new Graph<Type>(sources, destinations, weights).dijkstrasAlgorithm(srcData, dstData);
    }
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations)
    {
        return new Graph<Type>(sources, destinations).topoSort();
    }
}
