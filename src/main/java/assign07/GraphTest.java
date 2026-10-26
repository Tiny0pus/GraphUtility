package assign07;
import java.util.ArrayList;
import java.util.Arrays;
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
        graph = new Graph<>(sources, destinations, weights);
    }


    @Test
    void testToString()
    {
        graph.toString();
    }
}
