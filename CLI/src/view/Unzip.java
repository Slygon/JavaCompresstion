package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip implements Command {

	private final String OUTPUT_FOLDER = "output";

	@Override
	public void doCommand(String strParam) {

		byte[] buffer = new byte[1024];

		try {

			File file = new File(strParam);

			if (Utils.checkFileExists(strParam, ".zip")) {

				// create output directory is not exists
				File folder = new File(OUTPUT_FOLDER);

				if (!folder.exists()) {
					folder.mkdir();
				}

				// get the zip file content
				ZipInputStream zis = new ZipInputStream(new FileInputStream(
						strParam));

				// get the zipped file list entry
				ZipEntry ze = zis.getNextEntry();

				while (ze != null) {

					String fileName = ze.getName();
					File newFile = new File(folder.getPath() + File.separator
							+ fileName);

					System.out.println("file unzip : "
							+ newFile.getAbsoluteFile());

					// create all non exists folders
					// else you will hit FileNotFoundException for
					// compressed folder
					new File(newFile.getParent()).mkdirs();

					FileOutputStream fos = new FileOutputStream(newFile);

					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();
					ze = zis.getNextEntry();
				}

				zis.closeEntry();
				zis.close();

				System.out.println("Done");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "unzip";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";
	}
}
