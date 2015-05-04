package controller;

public interface Command {

	public void doCommand(String strParam);
	
	public String getUsage();
}
