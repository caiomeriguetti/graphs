package graph;

import org.apache.commons.io.IOUtils;
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
		String sampleText = IOUtils.toString(
	      this.getClass().getResourceAsStream("/sampleText.txt"),
	      "UTF-8"
	    );
		
		EncodedText encoded = codec.encodeText(sampleText);
		Assert.assertEquals(sampleText, codec.decode(encoded));
	}
}
