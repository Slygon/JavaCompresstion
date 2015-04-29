package boot;

import view.CLI;
import view.Command;
import view.Dic;
import view.Dir;
import view.Huf;
import view.Size;
import view.Undic;
import view.Unhuf;
import view.Unzip;
import view.Zip;


public class SimpleRun {

	public static void main(String[] args) {
		Command[] comms = new Command[] {
			new Dir(),
			new Zip(),
			new Unzip(),
			new Huf(),
			new Unhuf(),
			new Dic(),
			new Undic(),
			new Size()
		};
		new CLI(comms).start();
	}

}
