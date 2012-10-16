import java.util.ArrayList;


public class CompoundStatement extends Statement
{

	public CompoundStatement(ArrayList<String> tokens) 
	{
		super(tokens);
	}
	
	public int execute(boolean skip) throws ParserException, VariableException 
	{
		super.match("begin");
		String currenttoken = super.getCurrentToken();
		
		while (!currenttoken.equals("end"))
		{
			Statement currentstatement = createStatement();
			int tokensexecuted = currentstatement.execute(skip);
			super.moveAhead(tokensexecuted);
			
			currenttoken = super.getCurrentToken();
		}
		super.match("end");
		return resetTokens();		
	}

}
