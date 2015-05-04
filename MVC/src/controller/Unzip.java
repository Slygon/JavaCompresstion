package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import model.Model;
import model.MyModel;

public class Unzip implements Command {

	Model _model;
	
	public Unzip(Model model) {
		_model = model;
	}

	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).unzipFile(strParam);
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
