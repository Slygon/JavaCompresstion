package boot;

import model.Model;
import model.MyModel;
import view.MyView;
import view.View;
import controller.Controller;
import controller.MyController;

public class Run {

	public static void main(String[] args) {
		
		Model myModel = new MyModel();
		View myView = new MyView(myModel);
		Controller myController = new MyController(myView, myModel);
		
		((MyModel)myModel).setController(myController);
		
		((MyController)myController).start();
		
	}
}
