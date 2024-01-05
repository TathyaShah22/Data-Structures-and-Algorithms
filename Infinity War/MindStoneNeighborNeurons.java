package avengers;

import java.util.HashMap;

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the 
 * vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 *    1. v (int): number of neurons (vertices in the graph)
 *    2. v lines, each with a String referring to a neuron's name (vertex name)
 *    3. e (int): number of synapses (edges in the graph)
 *    4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 * 
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex. 
 * Output these vertices, one per line, to the output file.
 * 
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are 
 * no edges leaving the vertex.
 * 
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 * 
 */


public class MindStoneNeighborNeurons {
    
    public static void main (String [] args) {
        
    	if ( args.length < 2 ) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }
    	
    	// WRITE YOUR CODE HERE
        String input_file = args[0];
        String output_file = args[1];
        StdIn.setFile(input_file);
        StdOut.setFile(output_file);
        int neuron = StdIn.readInt();
        HashMap<String,Integer> neuron_name = new HashMap<>();
        HashMap<Integer,String> neighbor_neuron = new HashMap<>();
        for (int i = 0; i < neuron; i++) {
            String t = StdIn.readString();
            neuron_name.put(t, i);
            neighbor_neuron.put(i, t);
        }
        int synapses = StdIn.readInt();
        int[][] direct_synapses = new int[neuron][neuron];
        for (int i = 0; i < synapses; i++) {
            String S = StdIn.readString(), to = StdIn.readString();
            int fromRow = neuron_name.get(S), toCol = neuron_name.get(to);
            direct_synapses[fromRow][toCol] = 1;
        }
        int not_degree_out=-1;
        for (int i = 0; i < neuron; i++) {
            int outDegree = 0;
            for (int j = 0; j < neuron; j++) {
                if (direct_synapses[i][j] == 1) 
                outDegree++;
            }
            if (outDegree == 0) {
                not_degree_out = i;
                break;
            }
        }
        for (int i = 0; i < neuron; i++) {
            if (direct_synapses[i][not_degree_out] == 1) {
                StdOut.println(neighbor_neuron.get(i));
            }
        }
    }
}
        
