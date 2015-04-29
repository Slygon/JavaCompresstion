package view;


public class Undic implements Command {

	@Override
	public void doCommand(String strParam) {

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
