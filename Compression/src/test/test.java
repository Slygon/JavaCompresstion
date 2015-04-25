package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;

import compression_algorithms.Compressor;
import compression_algorithms.HuffmanAlg;
import compression_algorithms.HuffmanBitAlg;
import compression_algorithms.HuffmanReader;
import compression_algorithms.HuffmanStringAlg;
import compression_algorithms.HuffmanWriter;

public class test {

	public static void main(String[] args) {

		String s = "mississippi river";
		System.out.println("length: " + s.length()); // 17
		
		testHuffmeanReadWrite(s);
		System.out.println();

//		testHuffmanByte(s);
//		System.out.println();
		
//		testHuffmanString(s);
//		System.out.println();

//		testHuffmanBit(s);
//		System.out.println();
	}

	private static void testHuffmeanReadWrite(String s) {
		try {
			HuffmanStringAlg alg = new HuffmanStringAlg();
			
			PrintWriter out = new PrintWriter(new HuffmanWriter(new FileWriter("out.hff"), alg));
			out.print(s);
			out.flush();
			out.close();
		
			BufferedReader in = new BufferedReader(new HuffmanReader(new FileReader("out.hff"), alg));
			String sRead = in.readLine();
			in.close();
			
			if (s.equals(sRead)) {
				System.out.println("Huffman wrote and read: " + s);
			} else {
				System.out.println("Error ! Huffman writer/reader error");
			}

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private static void testHuffmanBit(String input) {
		
		Compressor comp = new HuffmanBitAlg();
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
