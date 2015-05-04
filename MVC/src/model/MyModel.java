package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import controller.Controller;
import controller.Utils;

public class MyModel implements Model {

	private final String OUTPUT_FOLDER = "output";

	HuffmanAlg _alg;
	Controller _controller;

	public MyModel() {
		_alg = new HuffmanAlg();
	}
	
	public void setController(Controller controller){
		_controller = controller;
	}

	public void zipFile(String strParam) {
		try {

			byte[] buffer = new byte[1024];

			File file = new File(strParam);

			if (Utils.checkFileExists(strParam, null)) {
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
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void unzipFile(String strParam) {
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

	
	public void Huf(String strParam) {
		try {

			File file = new File(strParam);

			if (Utils.checkFileExists(strParam, null)) {

				// Original file
				BufferedReader in = new BufferedReader(new FileReader(strParam));
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

				System.out.println("Done! Wrote to file \"" + strParam
						+ ".huf\"");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void Unhuf(String strParam) {
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

	
	public void Dic(String strParam) {
		// TODO
	}
	

	public void Undic(String strParam) {
		// TODO 		
	}

	
	public void Size(String strParam) {
		File file = new File(strParam);
		if (file.exists() && file.isFile())
			System.out.println(strParam + "\t" + file.length() + "B");
		else
			System.out.println("Could not find file \"" + strParam + "\"");
	}
}
