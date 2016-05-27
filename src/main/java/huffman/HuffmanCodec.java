package huffman;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Edge;
import graph.Tree;
import graph.TreeVertex;

public class HuffmanCodec {
	
	private Comparator<TreeVertex> sortComparator = new Comparator<TreeVertex>() {
	    @Override
	    public int compare(TreeVertex v1, TreeVertex v2) {
	    	
	    	Integer value1 = (Integer)v1.getValue();
	    	Integer value2 = (Integer)v2.getValue();
	    	
	        if (value1 > value2) return 1;
	        if (value1 < value2) return -1;
	        
	        return 0;
	    }
	};
	
	public EncodedText encodeText(String text) throws Exception {
		
		//calculating frequencies
		HashMap<Character, Integer> ocurrences = new HashMap<>();
		
		for (int i = 0; i < text.length(); i++) {
			Character charAt = text.charAt(i);
			
			if (!ocurrences.keySet().contains(charAt)) {
				ocurrences.put(charAt, 1);
			} else {
				ocurrences.put(charAt, ocurrences.get(charAt) + 1);
			}
		}
		
		//creating binary tree
		Tree tree = new Tree();
		List<TreeVertex> currentList = new ArrayList<TreeVertex>();
		Map<Character, TreeVertex> charToVertex = new HashMap<>();
		
		for(Map.Entry<Character, Integer> entry : ocurrences.entrySet()) {
		  Character key = entry.getKey();
		  Integer value = entry.getValue();
		  TreeVertex v = new TreeVertex(key);
		  v.setValue(value);
		  currentList.add(v);
		  tree.addVertex(v);
		  charToVertex.put(key, v);
		}
		
		TreeVertex root = null;
		
		while(currentList.size() > 1) {
			sort(currentList);
			TreeVertex first = currentList.remove(0);
			TreeVertex second = currentList.remove(0);
			
			Integer nodeValue = (Integer)first.getValue() + (Integer)second.getValue();
			TreeVertex mergeNode = new TreeVertex();
			mergeNode.setValue(nodeValue);
			Edge e1 = new Edge(mergeNode, first, false);
			Edge e2 = new Edge(mergeNode, second, true);
			first.setParent(mergeNode);
			second.setParent(mergeNode);
			tree.addVertex(mergeNode);
			tree.addEdge(e1);
			tree.addEdge(e2);
			currentList.add(mergeNode);
			root = mergeNode;
		}
		
		tree.setRoot(root);
		
		//encoding

		BitSet encoded = new BitSet();
		int bitIndex = 0;
		
		for (int i = 0; i < text.length(); i++) {
			Character chr = text.charAt(i);

			TreeVertex vertex = charToVertex.get(chr);
			List<Boolean> code = new ArrayList<Boolean>();
			
			while (vertex != root) {
				Edge e = tree.getEdge(vertex, vertex.getParent());
				boolean edgeValue = (boolean) e.getValue();
				code.add(0, edgeValue);
				vertex = vertex.getParent();
			}
			
			for (int t = 0; t < code.size(); t++) {
				encoded.set(bitIndex, code.get(t));
				bitIndex++;
			}
		}

		return new EncodedText(encoded, tree);
	}
	
	public String decode(EncodedText encoded) throws Exception {
		BitSet encodedData = encoded.getEncodedData();
		String result = "";
		Tree tree = encoded.getTree();
		TreeVertex currentNode = tree.getRoot();
		for(int i = 0; i <= encodedData.length(); i++){
			boolean value = encodedData.get(i);
			
			List<Edge> edges = tree.getChildEdges(currentNode);
			Edge selectedEdge = edges.get(0);
			
			if ((boolean)selectedEdge.getValue() != value) {
				selectedEdge = edges.get(1);
			}
			
			currentNode = (TreeVertex)selectedEdge.getEnd(currentNode);
			
			if (tree.isLeaf(currentNode)) {
				result += currentNode.getName();
				currentNode = tree.getRoot();
			}
		}
		
		return result;
	}
	
	private void sort(List<TreeVertex> list) {
		Collections.sort(list, sortComparator);
	}
	
}
