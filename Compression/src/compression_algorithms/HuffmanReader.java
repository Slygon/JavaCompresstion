package compression_algorithms;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;

import compression_algorithms.HuffmanAlg.Hchar;

public class HuffmanReader extends Reader {

	Reader _in;
	HuffmanStringAlg _huff;
	
	public HuffmanReader(Reader in, HuffmanStringAlg huff) {
		_in = in;
		_huff = huff;;
	}
	
//	public HuffmanReader(Reader in, HashMap<Character, Hchar> dicCharToBin) {
//		_in = in;
//		_huff = new HuffmanStringAlg(dicCharToBin);
//	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int a = _in.read(cbuf, off, len);
		char[] arrDecompressed = Arrays.copyOf(cbuf, len);
		String decompressed = _huff.decompress(new String(arrDecompressed));
		cbuf = decompressed.toCharArray();
		return len;
	}

	@Override
	public void close() throws IOException {
		_in.close();
	}

}
