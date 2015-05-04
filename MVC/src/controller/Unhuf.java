package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.HuffmanReader;
import model.HuffmanAlg;
import model.Model;
import model.MyModel;

public class Unhuf implements Command {

	Model _model;

	public Unhuf(Model model) {
		_model = model;
	}

	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).Unhuf(strParam);
	}

	@Override
	public String toString() {
		return "unhuf";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";
	}
}
