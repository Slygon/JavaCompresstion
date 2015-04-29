package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import compression_algorithms.HuffmanReader;
import compression_algorithms.HuffmanStringAlg;
import compression_algorithms.HuffmanWriter;

public class Unhuf implements Command {

	HuffmanStringAlg _alg;

	public Unhuf(HuffmanStringAlg alg) {
		_alg = alg;
	}

	@Override
	public void doCommand(String strParam) {

		try {

			if (Utils.checkFileExists(strParam, ".huf")) {
				BufferedReader in = new BufferedReader(new HuffmanReader(
						new FileReader(strParam), _alg));

				List<String> arrLines = new ArrayList<String>();
				String strTemp;
				while ((strTemp = in.readLine()) != null) {
					arrLines.add(strTemp);
				}

				PrintWriter _out = new PrintWriter(new FileWriter(strParam.replace(".huf", "")));

				for (String strLine : arrLines) {
					_out.print(strLine);
				}			

				_out.flush();
				_out.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "unhuf";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";
	}
}
