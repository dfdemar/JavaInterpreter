import java.util.ArrayList;


public class ProgramStatement extends Statement
{
	public String programname;

	public ProgramStatement(ArrayList<String> tokens) 
	{
		super(tokens);
	}

	@Override
	public int execute(boolean skip) throws ParserException, VariableException 
	{
		super.match("program");
		String currentToken  = super.getCurrentToken();
		if(!skip)
			programname = currentToken;
		
		super.match(currentToken);
		CompoundStatement c = new CompoundStatement(tokens);
		int tokensexecuted = c.execute(skip);
		super.moveAhead(tokensexecuted);
		return resetTokens();
	}

}
