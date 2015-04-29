package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import compression_algorithms.HuffmanReader;
import compression_algorithms.HuffmanStringAlg;
import compression_algorithms.HuffmanWriter;

public class CLI {
	BufferedReader _in;
	PrintWriter _out;
	HuffmanStringAlg _alg;

	HashMap<String, Command> _comms;

	public CLI(Command[] comms) {

		initComms(comms);
//		initReadWrite("");
	}

	private void initComms(Command[] comms) {
		_comms = new HashMap<String, Command>();

		for (Command command : comms) {
			_comms.put(command.toString(), command);
		}
	}

	private void initReadWrite(String strFileName) {
		_alg = new HuffmanStringAlg();

		try {
			_out = new PrintWriter(new HuffmanWriter(
					new FileWriter(strFileName), _alg));
			// out.print(s);
			// out.flush();
			// out.close();

			_in = new BufferedReader(new HuffmanReader(new FileReader(
					strFileName), _alg));
			// String sRead = in.readLine();
			// in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		printUsage();
		
		try {
			String strLine;
			String[] arrCommands;

			do {
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				strLine = bufferRead.readLine();
				
				arrCommands = strLine.split(" ");

				if (arrCommands.length == 2) {
					if (_comms.containsKey(arrCommands[0])) {
						_comms.get(arrCommands[0]).doCommand(arrCommands[1]);
					} else {
						System.out
								.println("Unknown command: \"" + arrCommands[0] + "\"");
						printUsage();
					}
				} else {
					printUsage();
				}

			} while (!strLine.equals("exit"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printUsage() {
		System.out.println("Usage: <Command> <Parameter>");
		for (Command command : _comms.values()) {
			System.out.println(command.getUsage());
		}
		System.out.println();
	}
}
