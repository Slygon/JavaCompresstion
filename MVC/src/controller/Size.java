package controller;

import java.io.File;

import model.Model;
import model.MyModel;


public class Size implements Command {

	Model _model;
	
	public Size(Model model) {
		_model = model;
	}

	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).Size(strParam);
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
