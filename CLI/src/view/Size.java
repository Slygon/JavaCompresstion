package view;

import java.io.File;


public class Size implements Command {

	@Override
	public void doCommand(String strParam) {
		File file = new File(strParam);
		if (file.exists() && file.isFile())
			System.out.println(strParam + "\t" + file.length() + "B");
		else
			System.out.println("Could not find file \"" + strParam + "\"");
	}

	@Override
	public String toString() {
		return "size";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";		
	}
}
