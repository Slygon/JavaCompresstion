package view;


public class Unhuf implements Command {

	@Override
	public void doCommand(String strParam) {

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
