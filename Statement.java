import java.util.ArrayList;


public abstract class Statement 
{
	protected ArrayList<String> tokens = new ArrayList<String>();
	protected ArrayList<String> executedTokens = new ArrayList<String>();

	public Statement(ArrayList<String> _tokens) 
	{
		tokens.addAll(_tokens);
	}
	
	protected String getCurrentToken()
	{
		return tokens.get(0);
	}
	
	protected Statement createStatement() throws ParserException
	{
		String currenttoken = this.getCurrentToken();
		
		if(currenttoken.equals("print")) 
		{
			Print p = new Print(tokens);
			return p;
		}

		else if (TokenHandler.isVariable(currenttoken)) 
		{
			Assignment a = new Assignment(tokens);
			return a;
		}

		else if(currenttoken.equals("if"))
		{
			IF i = new IF(tokens);
			return i;
		}
			
		else if(currenttoken.equals("while"))
		{
			While w = new While(tokens);
			return w;
		}
		
		else if(currenttoken.equals("begin"))
		{
			CompoundStatement s = new CompoundStatement(tokens);
			return s;
		}
		
		else
			throw new ParserException("No valid syntax: " + currenttoken);
	}
	
	protected boolean ConditionIsTrue(ArrayList<String> tokens, ArrayList<String> outCondition) throws VariableException, ParserException
	{
		String currenttoken = tokens.get(0);
		int leftvalue = TokenHandler.readTokenValue(currenttoken);
		outCondition.add(currenttoken);
		match(currenttoken, tokens);
		currenttoken = tokens.get(0);
		
    	if(currenttoken.equals("<"))
    	{
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		currenttoken = tokens.get(0);
    		int rightvalue = TokenHandler.readTokenValue(currenttoken);
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		return (leftvalue < rightvalue);
    	}
    	
    	if(currenttoken.equals(">"))
    	{
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		currenttoken = tokens.get(0);
    		int rightvalue = TokenHandler.readTokenValue(currenttoken);
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		return (leftvalue > rightvalue);
    	}
    	
    	if(currenttoken.equals("<="))
    	{
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		currenttoken = tokens.get(0);
    		int rightvalue = TokenHandler.readTokenValue(currenttoken);
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		return (leftvalue <= rightvalue);
    	}
    	
    	if(currenttoken.equals(">="))
    	{
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		currenttoken = tokens.get(0);
    		int rightvalue = TokenHandler.readTokenValue(currenttoken);
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		return (leftvalue >= rightvalue);
    	}
    	
    	if(currenttoken.equals("="))
    	{
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		currenttoken = tokens.get(0);
    		int rightvalue = TokenHandler.readTokenValue(currenttoken);
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		return (leftvalue == rightvalue);
    	}
    	
    	if(currenttoken.equals("/="))
    	{
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		currenttoken = tokens.get(0);
    		int rightvalue = TokenHandler.readTokenValue(currenttoken);
    		outCondition.add(currenttoken);
    		match(currenttoken, tokens);
    		return (leftvalue != rightvalue);
    	}
    	
    	throw new ParserException("Not a valid condition");
	}
	
	private void match(String token, ArrayList<String> matchTokens) throws ParserException 
	{
		if(token.equals(matchTokens.get(0)))
		{
			executedTokens.add(token);
			matchTokens.remove(0);
		}
		
		else
			throw new ParserException("Unexpected token");
	}

	protected void match(String token) throws ParserException
	{
		match(token, tokens);
	}
	
	protected int resetTokens()
	{
		tokens.clear();
		tokens.addAll(executedTokens);
		int tokensSize = executedTokens.size();
		executedTokens.clear();
		return tokensSize;
	}
	
	protected void moveAhead(int numberofElements) throws ParserException
	{
		for(int i = 0; i < numberofElements; i++)
		{
			match(getCurrentToken());
		}
	}
	
	public abstract int execute(boolean skip) throws ParserException, VariableException;
	
	public int execute() throws ParserException, VariableException
	{
		return execute(false);
	}
}
