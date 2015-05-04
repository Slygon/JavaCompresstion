package controller;

import model.Model;
import model.MyModel;


public class Undic implements Command {

	Model _model;
	
	public Undic(Model model) {
		_model = model;
	}
	
	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).Undic(strParam);
	}

	@Override
	public String toString() {
		return "undic";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";		
	}
}
