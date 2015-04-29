package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip implements Command {
	@Override
	public void doCommand(String strParam) {
		try {
			
			byte[] buffer = new byte[1024];

			File file = new File(strParam);
			if (file.exists() && file.isFile()) {
				// Input - file
				FileInputStream in = new FileInputStream(strParam);
				
				// Output - zip
				FileOutputStream fos = new FileOutputStream(strParam + ".zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				ZipEntry ze = new ZipEntry(strParam);
				zos.putNextEntry(ze);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
				zos.closeEntry();

				// remember close it
				zos.close();

				System.out.println("Done");
			} else {
				System.out.println("Could not find file \"" + strParam + "\"");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "zip";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";
	}
}
