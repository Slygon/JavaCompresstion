package compression_algorithms;

public interface Compressible {
	Object compress(String oStream);
	String decompress(Object oStream);
}
