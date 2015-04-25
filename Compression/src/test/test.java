package test;

import java.util.Arrays;

import compression_algorithms.Compressor;
import compression_algorithms.HuffmanAlg;
import compression_algorithms.HuffmanStringAlg;

public class test {

	public static void main(String[] args) {

		String s = "mississippi river";
		System.out.println("length: " + s.length()); // 17
		
//		testHuffmanString(s);
//		System.out.println();
//		
//		testHuffmanByte(s);
//		System.out.println();

		testHuffmanBit(s);
		System.out.println();
	}
	
	private static void testHuffmanBit(String input) {
		
		Compressor comp = new HuffmanAlg();
		byte[] sEncoded = (byte[])comp.compress(input);
		System.out.println(Arrays.toString(sEncoded));
		
		String sDecoded = comp.decompress(sEncoded);
		System.out.println(sDecoded);
	}

	private static void testHuffmanByte(String input) {
		
		Compressor comp = new HuffmanAlg();
		byte[] sEncoded = (byte[])comp.compress(input);
		System.out.println(Arrays.toString(sEncoded));
		
		String sDecoded = comp.decompress(sEncoded);
		System.out.println(sDecoded);
	}
	

	private static void testHuffmanString(String input) {

		Compressor comp = new HuffmanStringAlg();
		String sEncoded = (String) comp.compress(input);
		System.out.println(sEncoded);
		
		String sDecoded = comp.decompress(sEncoded);
		System.out.println(sDecoded);
	}
}
