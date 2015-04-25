package compression_algorithms;

import java.io.ObjectOutputStream;

public interface Compressible {
	Object compress(String oStream);
	String decompress(Object oStream);
}
