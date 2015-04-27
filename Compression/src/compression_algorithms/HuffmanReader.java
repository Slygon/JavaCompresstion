package compression_algorithms;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;

import compression_algorithms.HuffmanAlg.Hchar;

public class HuffmanReader extends Reader {

	Reader _in;
	HuffmanStringAlg _huff;
	int lastIndex = 0;
	
	public HuffmanReader(Reader in, HuffmanStringAlg huff) {
		_in = in;
		_huff = huff;
	}
	
//	public HuffmanReader(Reader in, HashMap<Character, Hchar> dicCharToBin) {
//		_in = in;
//		_huff = new HuffmanStringAlg(dicCharToBin);
//	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		char[] temp = new char[len];
		int a = _in.read(temp, off, len);
		
		if (a != -1) {
			String decompressed = _huff.decompress(new String(temp));
			System.arraycopy(decompressed.toCharArray(), 0, cbuf, lastIndex, decompressed.length());
			lastIndex += decompressed.length();
			
			return decompressed.length();
		}
		
		return a;
	}

	@Override
	public void close() throws IOException {
		_in.close();
	}

}
