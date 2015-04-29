package view;

public class Dic implements Command {

	@Override
	public String toString() {
		return "dic";
	}

	@Override
	public void doCommand(String strParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsage() {
		return this.toString() + " <filename>";		
	}
}
