package huffman;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class EncodedText {
	private BitSet encodedData;
	private Map<String, String> codes;
	
	public BitSet getEncodedData() {
		return encodedData;
	}

	public void setEncodedData(BitSet encodedData) {
		this.encodedData = encodedData;
	}

	public Map<String, String> getCodes() {
		return codes;
	}

	public void setCodes(HashMap<String, String> codes) {
		this.codes = codes;
	}

	public EncodedText(BitSet encodedData, Map<String, String> codes) {
		this.encodedData = encodedData;
		this.codes = codes;
	}
}
