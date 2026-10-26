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

    @BeforeEach
    void setUp() {
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
        weights = new ArrayList<>();
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
    void testToString()
    {
        graph.toString();
    }

    @Test
    void testBFS()
    {
        LinkedList<String> result = new LinkedList<>(graph.breadthFirstSearch("A", "D"));
        for (String str: result)
        {
            System.out.println(str);
        }
    }
}
