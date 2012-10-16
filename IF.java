import java.util.ArrayList;


public class IF extends Statement
{

	public IF(ArrayList<String> tokens) 
	{
		super(tokens);
	}
	
	public int execute(boolean skip) throws ParserException, VariableException 
	{
		super.match("if");
		String currenttoken = super.getCurrentToken();
		
		if(skip)
		{
			while(!currenttoken.equals("else"))
			{
				super.match(currenttoken);
				currenttoken = getCurrentToken();
			}
			super.match("else");
			
			Statement s = createStatement();
			int tokensexecuted = s.execute(skip);
			super.moveAhead(tokensexecuted);
		}
				
		if(super.ConditionIsTrue(tokens, (new ArrayList<String>())))
		{
			super.match("then");
			Statement s = createStatement();
			int tokensexecuted = s.execute(skip);
			super.moveAhead(tokensexecuted);
			
			super.match("else");
			s = createStatement();
			tokensexecuted = s.execute(true);
			super.moveAhead(tokensexecuted);
		}
		
		else
		{
			currenttoken = getCurrentToken();
			while(!currenttoken.equals("else"))
			{
				super.match(currenttoken);
				currenttoken = getCurrentToken();
			}
			super.match("else");
			
			Statement s = createStatement();
			int tokensexecuted = s.execute(skip);
			super.moveAhead(tokensexecuted);
		}
		
		return resetTokens();
	}
}
