package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanAlg extends Compressor {

	private HashMap<Character, Hchar> _dicCharToBin;
	PriorityQueue<Hchar> _queHTree;
	Hchar _hTop;
	
	// For quick de-compression - a reverse dictionary
	private HashMap<String, Character> _dicBinToChar;

	public HuffmanAlg(HashMap<Character, Hchar> dicCharToBin) {
		_queHTree = new PriorityQueue<Hchar>(new Comparator<Hchar>() {
			@Override
			public int compare(Hchar o1, Hchar o2) {
				return o1.count - o2.count;
			}
		});
		
		_dicCharToBin = dicCharToBin;
	}
	
	public HuffmanAlg() {
		_queHTree = new PriorityQueue<Hchar>(new Comparator<Hchar>() {
			@Override
			public int compare(Hchar o1, Hchar o2) {
				return o1.count - o2.count;
			}
		});
	}

	@Override
	public String compress(String input) {
		
		// If we don't already have a dictionary
		if (_dicBinToChar == null) {
			
			buildQueueFromString(input);

			buildHTreeFromQueue();
		}
		
		String sEncoded = "";
		for (char cChar : input.toCharArray()) {
			sEncoded = sEncoded.concat(_dicCharToBin.get(cChar).binRep);
		}

		return sEncoded;
	}
	
	@Override
	public String decompress(Object stream) {

		if (_dicBinToChar == null) {
			reverseDictionary();
		}
		
		String input = (String) stream;
		
		Character cChar = null;
		String decompressed = "";
		
		for (int iStart = 0, iEnd = 0; iEnd < input.length();) {
			boolean bFound = false;
			while (!bFound && iEnd <= input.length()) {
				cChar = _dicBinToChar.get(input.substring(iStart, iEnd));
				
				if (cChar != null) {
					decompressed += cChar;
					bFound = true;
					iStart = iEnd;
				}
				iEnd++;
			}
			
		}
		
		return decompressed;
	}

	private void buildQueueFromString(String input) {
		// Count appearances
		_dicCharToBin = countAppearances(input);
		
		// Populate the queue by appearace count
		_queHTree.addAll(_dicCharToBin.values());
	}

	/* For quick de-compression - a reverse dictionary
	 * 
	 */
	private void reverseDictionary() {
		_dicBinToChar = new HashMap<String, Character>();
		
		if (_dicCharToBin == null)
			System.out.println("Huffman: No dictionary found!");
		
		for (Entry<Character, Hchar> pair : _dicCharToBin.entrySet()) {
			_dicBinToChar.put(pair.getValue().binRep, pair.getKey());
		}
	}

	private void buildHTreeFromQueue() {
		while (_queHTree.size() > 1) {
			Hchar hc0 = _queHTree.poll();
			Hchar hc1 = _queHTree.poll();
			Hchar hc2 = new Hchar();
			hc2.count = hc0.count + hc1.count;
			hc2.character = hc0.character + hc1.character;
			hc2.left = hc0;
			hc2.right = hc1;
			_queHTree.add(hc2);
		}
		
		_hTop = _queHTree.peek();
		
		DFSbinRep(_hTop, "");
		
		// Print the outcome for test purposes
		System.out.println("Dictionary:");
		for (Hchar hc : _dicCharToBin.values()) {
			System.out.println(hc.character + " " + hc.binRep);
		}
	}

	private void DFSbinRep(Hchar node, String bin) {
		node.binRep += bin;
		if (node.left != null) {
			DFSbinRep(node.left, node.binRep.concat("0"));
		}
		if (node.right != null) {
			DFSbinRep(node.right, node.binRep.concat("1"));
		}
	}

	private HashMap<Character, Hchar> countAppearances(String input) {
		// count appearances of characters
		HashMap<Character, Hchar> countAppearance = new HashMap<Character, Hchar>();
		for (char c : input.toCharArray()) {
			Hchar hc = countAppearance.get(c);
			if (hc == null) {
				hc = new Hchar();
				hc.character = "" + c;
				hc.count = 0;
				countAppearance.put(c, hc);
			}
			hc.count++;
		}

		return countAppearance;
	}

	public class Hchar {
		int count;
		String character;
		Hchar left = null, right = null;
		// an inefficient representation, change it to real bits!
		String binRep = "";

		@Override
		public int hashCode() {
			return character.hashCode();
		}
	}
}
