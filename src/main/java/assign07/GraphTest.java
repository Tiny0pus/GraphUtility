package assign07;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GraphTest {
    ArrayList<String> sources;
    ArrayList<String> destinations;
    ArrayList<Double> weights;
    Graph<String> graph;
    GraphGenerator graphGen;

    @BeforeEach
    void setUp() {
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
        weights = new ArrayList<>();
        graphGen = new GraphGenerator();
        sources.add("A");
        destinations.add("B");
        weights.add(0.0);
        sources.add("B");
        destinations.add("C");
        weights.add(0.0);
        sources.add("A");
        destinations.add("C");
        weights.add(1.0);
        sources.add("C");
        destinations.add("D");
        weights.add(2.0);
        graph = new Graph<>(sources, destinations, weights);
    }


    @Test
    void testToString() {
        graph.toString();
    }


    @Test
    void testBFS() {
        LinkedList<String> result = new LinkedList<>(graph.breadthFirstSearch("A", "D"));
        assertArrayEquals(new String[]{"A", "C", "D"}, result.toArray());

    }

    @Test
    void testRandomGraph() {
        graphGen.generate(10, 11);
        graph = new Graph<>(graphGen.sources, graphGen.destinations, graphGen.weights);
        graph.toString();
        String source = graphGen.sources.getFirst();
        String destination =graphGen.destinations.getLast();
        System.out.println(source + " -> " + destination);
        System.out.println(graph.breadthFirstSearch(source, destination));
    }

    @Test
    void testRandomGraphDij()
    {
        graphGen.generate(4, 9);
        graph = new Graph<>(graphGen.sources, graphGen.destinations, graphGen.weights);
        graph.toString();
        String source = graphGen.sources.getLast();
        String destination = graphGen.destinations.getLast();
        System.out.println(source + " -> " + destination);
        System.out.println(graph.dijkstrasAlgorithm(source, destination));
    }
}
