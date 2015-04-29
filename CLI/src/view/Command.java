package view;

public interface Command {

	public void doCommand(String strParam);
	
	public String getUsage();
}
