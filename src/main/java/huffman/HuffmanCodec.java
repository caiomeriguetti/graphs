package huffman;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dijkstra.DijkstraPathFinder;
import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;

public class HuffmanCodec {
	
	public EncodedText encodeText(String text) throws Exception {
		HashMap<String, Integer> ocurrences = new HashMap<>();
		for (int i = 0; i < text.length(); i++) {
			String charAt = Character.toString(text.charAt(i));
			
			if (!ocurrences.keySet().contains(charAt)) {
				ocurrences.put(charAt, 1);
			} else {
				ocurrences.put(charAt, ocurrences.get(charAt) + 1);
			}
		}
		
		Graph graph = new Graph();
		List<Vertex> currentList = new ArrayList<Vertex>();
		Map<String, Vertex> charToVertex = new HashMap<>();
		Map<String, String> charToCode = new HashMap<>();
		Map<String, String> codeToChar = new HashMap<>();
		
		for(Map.Entry<String,Integer> entry : ocurrences.entrySet()) {
		  String key = entry.getKey();
		  Integer value = entry.getValue();
		  Vertex v = new Vertex(key);
		  v.setValue(value);
		  currentList.add(v);
		  graph.addVertex(v);
		  charToVertex.put(key, v);
		}
		
		Vertex root = null;
		while(currentList.size() > 1) {
			sort(currentList);
			Vertex first = currentList.remove(0);
			Vertex second = currentList.remove(0);
			
			Integer nodeValue = (Integer)first.getValue() + (Integer)second.getValue();
			Vertex mergeNode = new Vertex(first.getName() + second.getName());
			mergeNode.setValue(nodeValue);
			Edge e1 = new Edge(mergeNode, first, 0);
			Edge e2 = new Edge(mergeNode, second, 1);
			graph.addVertex(mergeNode);
			graph.addEdge(e1);
			graph.addEdge(e2);
			currentList.add(mergeNode);
			root = mergeNode;
		}
		
		DijkstraPathFinder finder = new DijkstraPathFinder(graph, root);
		
		for(Map.Entry<String,Vertex> entry : charToVertex.entrySet()) {
			String key = entry.getKey();
			Vertex vertex = entry.getValue();
			
			Path path = finder.getPath(vertex);
			String code = "";
			for(Edge e: path.getEdges()) {
				code += new Double(e.getValue()).intValue();
			}
			charToCode.put(key, code);
			codeToChar.put(code, key);
		}

		BitSet encoded = new BitSet();
		int bitIndex = 0;
		for (int i = 0; i < text.length(); i++) {
			String charAt = Character.toString(text.charAt(i));
			String code = charToCode.get(charAt);
			for (int t = 0; t < code.length(); t++) {
				encoded.set(bitIndex, code.charAt(t) == '1');
				bitIndex++;
			}
		}

		return new EncodedText(encoded, codeToChar);
	}
	
	public String decode(EncodedText encoded) {
		BitSet encodedData = encoded.getEncodedData();
		String buffer = "";
		String result = "";
		for(int i = 0; i <= encodedData.length(); i++){
			if (encodedData.get(i) == true) {
				buffer += "1";
			} else {
				buffer += "0";
			}
			
			if (encoded.getCodes().containsKey(buffer)) {
				result += encoded.getCodes().get(buffer);
				buffer = "";
			}
		}
		
		return result;
	}
	
	private void sort(List<Vertex> list) {
		Collections.sort(list, new Comparator<Vertex>() {
		    @Override
		    public int compare(Vertex v1, Vertex v2) {
		    	
		    	Integer value1 = (Integer)v1.getValue();
		    	Integer value2 = (Integer)v2.getValue();
		    	
		        if (value1 > value2) return 1;
		        if (value1 < value2) return -1;
		        
		        return 0;
		    }
		});
	}
	
	public String decodeText(Byte[] bytes) {
		return null;
	}
	
}
