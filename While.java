import java.util.ArrayList;


public class While extends Statement
{
	public While(ArrayList<String> tokens) 
	{
		super(tokens);	
	}

	public int execute(boolean skip) throws ParserException, VariableException 
	{
		super.match("while");
		
		ArrayList<String> conditiontokens = new ArrayList<String>();
		boolean conditionistrue = super.ConditionIsTrue(tokens, conditiontokens);
		super.match("do");
		Statement s = super.createStatement();
		
		int execTokens = 0;
		while(conditionistrue)
		{
			execTokens = s.execute();
			ArrayList<String> tokencopy = new ArrayList<String>(conditiontokens);
			conditiontokens.clear();
			conditionistrue = super.ConditionIsTrue(tokencopy, conditiontokens);
			int size = executedTokens.size();
			for(int i = size - 1; i >= (size - conditiontokens.size()); i--)
				{
					executedTokens.remove(i);
				}
		}
		
		moveAhead(execTokens);
		
		return resetTokens();
	}

}
