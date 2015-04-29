package view;

public class Huf implements Command {

	@Override
	public void doCommand(String strParam) {

	}

	@Override
	public String toString() {
		return "huf";
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";		
	}

}
