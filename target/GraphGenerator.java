
package assign07;

import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {

    private Random rand = new Random();

    public ArrayList<String> sources = new ArrayList<>();
    public ArrayList<String> destinations = new ArrayList<>();
    public ArrayList<Double> weights = new ArrayList<>();

    public void generate(int numVertices, int numEdges) {

        sources.clear();
        destinations.clear();
        weights.clear();

        if (numVertices < 0 || numEdges < 0 ||
                (long) numEdges > (long) numVertices * (numVertices - 1)) {
            throw new IllegalArgumentException("Invalid graph size");
        }

        ArrayList<String> vertices = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            vertices.add("V" + i);
        }

        while (sources.size() < numEdges) {

            String source = vertices.get(rand.nextInt(numVertices));
            String destination = vertices.get(rand.nextInt(numVertices));

            // Prevent self-loops
            if (source.equals(destination)) {
                continue;
            }

            // Prevent duplicate directed edges
            boolean duplicate = false;

            for (int i = 0; i < sources.size(); i++) {
                if (sources.get(i).equals(source) &&
                        destinations.get(i).equals(destination)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                continue;
            }

            sources.add(source);
            destinations.add(destination);
            weights.add(rand.nextDouble(10.0));
        }
    }
}
