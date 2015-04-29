package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import compression_algorithms.HuffmanReader;
import compression_algorithms.HuffmanAlg;
import compression_algorithms.HuffmanWriter;

public class Huf implements Command {

	HuffmanAlg _alg;

	public Huf(HuffmanAlg alg) {
		_alg = alg;
	}

	@Override
	public void doCommand(String strParam) {

		try {

			File file = new File(strParam);

			if (Utils.checkFileExists(strParam, null)) {

				// Original file
				BufferedReader in = new BufferedReader(
						new FileReader(strParam));
				List<String> arrLines = new ArrayList<String>();
				String strTemp;
				while ((strTemp = in.readLine()) != null) {
					arrLines.add(strTemp);
				}

				in.close();

				// Write to .huf file
				PrintWriter _out = new PrintWriter(new HuffmanWriter(
						new FileWriter(strParam + ".huf"), _alg));

				for (String strLine : arrLines) {
					_out.print(strLine);
				}
				
				_out.flush();
				_out.close();
				
				System.out.println("Done! Wrote to file \"" + strParam + ".huf\"");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "huf";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";
	}

}
