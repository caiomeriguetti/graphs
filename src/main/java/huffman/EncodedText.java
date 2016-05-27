package huffman;

import java.util.BitSet;

import graph.Tree;

public class EncodedText {
	private BitSet encodedData;
	private Tree tree;
	
	public BitSet getEncodedData() {
		return encodedData;
	}

	public void setEncodedData(BitSet encodedData) {
		this.encodedData = encodedData;
	}

	public EncodedText(BitSet encodedData, Tree tree) {
		this.encodedData = encodedData;
		this.tree = tree;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}
}
