package compression_algorithms;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class HuffmanWriter extends Writer {

	Writer _out;
	HuffmanStringAlg _huff;

	public HuffmanWriter(Writer out, HuffmanStringAlg huff) {
		_out = out;
		_huff = huff;
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		char[] arrCompressed = Arrays.copyOf(cbuf, len);
		arrCompressed = _huff.compress(new String(arrCompressed)).toCharArray();
		_out.write(arrCompressed, off, arrCompressed.length);
	}

	@Override
	public void flush() throws IOException {
		_out.flush();
	}

	@Override
	public void close() throws IOException {
		_out.close();
	}

}
