package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanByteAlg extends Compressor {

	private HashMap<Character, Hchar> _dicCharToBin;
	
	public HashMap<Character, Hchar> getTable() {
		return _dicCharToBin;
	}

	PriorityQueue<Hchar> _queHTree;
	Hchar _hTop;
	
	// For quick de-compression - a reverse dictionary
	private HashMap<ByteArrayWrapper, Character> _dicBinToChar;

	public HuffmanByteAlg(HashMap<Character, Hchar> dicCharToBin) {
		_queHTree = new PriorityQueue<Hchar>(new Comparator<Hchar>() {
			@Override
			public int compare(Hchar o1, Hchar o2) {
				return o1.count - o2.count;
			}
		});
		
		_dicCharToBin = dicCharToBin;
	}
	
	public HuffmanByteAlg() {
		_queHTree = new PriorityQueue<Hchar>(new Comparator<Hchar>() {
			@Override
			public int compare(Hchar o1, Hchar o2) {
				return o1.count - o2.count;
			}
		});
	}

	@Override
	public byte[] compress(String input) {
		
		// If we don't already have a dictionary
		if (_dicBinToChar == null) {
			
			buildQueueFromString(input);

			buildHTreeFromQueue();
		}
		
		byte[] arrEncoded= new byte[] {};
		for (char cChar : input.toCharArray()) {
			arrEncoded = joinByteArray(arrEncoded, _dicCharToBin.get(cChar).binRep);
		}

		return arrEncoded;
	}
	
	@Override
	public String decompress(Object stream) {

		if (_dicBinToChar == null) {
			reverseDictionary();
		}
		
		byte[] encodedInput = (byte[]) stream;
		String decodedInput = "";
		
		byte[] encodedChar = new byte[] {}; 
		Character decodedChar = null;
		
		
		for (int iStart = 0, iEnd = 0; iEnd < encodedInput.length;) {
			boolean bFound = false;
			while (!bFound && iEnd <= encodedInput.length) {
				
				encodedChar = Arrays.copyOfRange(encodedInput, iStart, iEnd);
				decodedChar = _dicBinToChar.get(new ByteArrayWrapper(encodedChar));
				
				if (decodedChar != null) {
					decodedInput += decodedChar;
					bFound = true;
					iStart = iEnd;
				}
				iEnd++;
			}
			
		}
		
		return decodedInput;
	}

	private void buildQueueFromString(String input) {
		// Count appearances
		_dicCharToBin = countAppearances(input);
		
		// Populate the queue by appearace count
		_queHTree.addAll(_dicCharToBin.values());
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
		
		DFSbinRep(_hTop, new byte[] {});
		
		// Print the outcome for test purposes
		System.out.println("Dictionary:");
		for (Hchar hc : _dicCharToBin.values()) {
			System.out.println(hc.character + " " + hc.binRep);
		}
	}
	
	/* For quick de-compression - a reverse dictionary
	 * 
	 */
	private void reverseDictionary() {
		_dicBinToChar = new HashMap<ByteArrayWrapper, Character>();
		for (Entry<Character, Hchar> pair : _dicCharToBin.entrySet()) {
			_dicBinToChar.put(new ByteArrayWrapper(pair.getValue().binRep), pair.getKey());
		}
	}

	private void DFSbinRep(Hchar node, byte[] bin) {
		node.binRep = joinByteArray(node.binRep, bin);
		if (node.left != null) {
			DFSbinRep(node.left, joinByteArray(node.binRep, new byte[] {0}));
		}
		if (node.right != null) {
			DFSbinRep(node.right, joinByteArray(node.binRep, new byte[] {1}));
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
	
	private byte[] joinByteArray(byte[] arr1, byte[] arr2) {
		byte[] joined = new byte[arr1.length + arr2.length];
		
		System.arraycopy(arr1,0,joined,0 ,arr1.length);
		System.arraycopy(arr2,0,joined,arr1.length,arr2.length);
		
		return joined;
	}

	public class Hchar {
		int count;
		String character;
		Hchar left = null, right = null;
		// an inefficient representation, change it to real bits!
		byte[] binRep = new byte[0];

		@Override
		public int hashCode() {
			return character.hashCode();
		}
	}
	
	public final class ByteArrayWrapper
	{
	    private final byte[] data;

	    public ByteArrayWrapper(byte[] data)
	    {
	        if (data == null)
	        {
	            throw new NullPointerException();
	        }
	        this.data = data;
	    }

	    @Override
	    public boolean equals(Object other)
	    {
	        if (!(other instanceof ByteArrayWrapper))
	        {
	            return false;
	        }
	        return Arrays.equals(data, ((ByteArrayWrapper)other).data);
	    }

	    @Override
	    public int hashCode()
	    {
	        return Arrays.hashCode(data);
	    }
	}
}
