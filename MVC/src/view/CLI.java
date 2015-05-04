package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import controller.Command;

public class CLI {
	HashMap<String, Command> _comms;

	public CLI(Command[] comms) {

		initComms(comms);
	}

	private void initComms(Command[] comms) {
		_comms = new HashMap<String, Command>();

		for (Command command : comms) {
			_comms.put(command.toString(), command);
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
