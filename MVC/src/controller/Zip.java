package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import model.Model;
import model.MyModel;

public class Zip implements Command {
	
	Model _model;
	
	public Zip(Model model) {
		_model = model;
	}

	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).zipFile(strParam);
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
