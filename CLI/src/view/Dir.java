package view;

import java.io.File;

public class Dir implements Command {

	@Override
	public void doCommand(String strParam) {
		File folder = new File(strParam);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File \t\t" + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory \t" + listOfFiles[i].getName());
		      }
		    }
	}

	@Override
	public String toString() {
		return "dir";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <path>";		
	}
}
