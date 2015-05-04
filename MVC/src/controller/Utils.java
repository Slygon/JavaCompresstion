package controller;

import java.io.File;

import model.Model;
import model.MyModel;

public class Utils {
	public static Boolean checkFileExists(String strFile, String makeSureEndsWith) {
		
		File file = new File(strFile);
		if (file.exists() && file.isFile()) {
			
			if (makeSureEndsWith != null && !file.getName().endsWith(makeSureEndsWith)) {
				System.out.println("file \"" + strFile + 
						"\" is not a \"" + makeSureEndsWith + "\" file");
				
				return false;
			}
			return true;
			
				
		} else {
			System.out.println("Could not find file \"" + strFile + "\"");
			return false;
		}
	}
}
