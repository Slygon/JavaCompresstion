package controller;

import model.Model;
import model.MyModel;

public class Dic implements Command {

	Model _model;
	
	public Dic(Model model) {
		_model = model;
	}
	
	@Override
	public String toString() {
		return "dic";
	}

	@Override
	public void doCommand(String strParam) {
		((MyModel)_model).Dic(strParam);
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";		
	}
}
