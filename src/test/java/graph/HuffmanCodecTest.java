package graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import huffman.EncodedText;
import huffman.HuffmanCodec;

public class HuffmanCodecTest {
	protected HuffmanCodec codec;
	
	@Before
	public void init() {
		codec = new HuffmanCodec();
	}
	
	@Test
	public void testEncodeDecode() throws Exception {
		String text = "MISSISSIPI RIVER";
		EncodedText encoded = codec.encodeText(text);
		Assert.assertEquals(text, codec.decode(encoded));
	}
}
