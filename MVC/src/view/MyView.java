package view;

import model.Model;
import controller.Command;

public class MyView implements View {

	Model _model;
	CLI myCLI;
	
	public MyView(Model model) {
		_model = model;
	}
	
	public void initCLI(Command[] comms) {
		myCLI = new CLI(comms);
	}

	public void start() {
		if (myCLI != null)
			myCLI.start();
	}
}
