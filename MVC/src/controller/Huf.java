package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.HuffmanAlg;
import model.HuffmanWriter;
import model.Model;
import model.MyModel;

public class Huf implements Command {

	Model _model;

	public Huf(Model _model) {
		_model = _model;
	}

	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).Huf(strParam);
	}

	@Override
	public String toString() {
		return "huf";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";
	}

}
