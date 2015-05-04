package controller;

import model.HuffmanAlg;
import model.Model;
import view.MyView;
import view.View;

public class MyController implements Controller {
	View _view;
	Model _model;
	
	public MyController(View view, Model model) {
		_view = view;
		_model = model;
		
		initView();
	}
	
	public void start() {
		((MyView)_view).start();
	}

	private void initView() {
		HuffmanAlg alg = new HuffmanAlg();
		Command[] comms = new Command[] {
			new Dir(_model),
			new Zip(_model),
			new Unzip(_model),
			new Huf(_model),
			new Unhuf(_model),
			new Dic(_model),
			new Undic(_model),
			new Size(_model)
		};
		((MyView)_view).initCLI(comms);
	}
}
