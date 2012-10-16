import java.util.ArrayList;

public class Assignment extends Statement 
{

	public Assignment(ArrayList<String> tokens) 
	{
		super(tokens);
	}
	
	public int execute(boolean skip) throws ParserException, VariableException 
	{
		String currenttoken = super.getCurrentToken();		
		if(!TokenHandler.isVariable(currenttoken))
			throw new VariableException("Not a variable");
		char variablebeingassigned = currenttoken.charAt(0);
		super.match(currenttoken);
		super.match(":=");
		currenttoken = super.getCurrentToken();
		int variablevalue = 0;
		if (!skip)
		{
			variablevalue = TokenHandler.readTokenValue(currenttoken);
			TokenHandler.variables.put(variablebeingassigned, variablevalue);
		}
		super.match(currenttoken);
		
		currenttoken = super.getCurrentToken();
		while(TokenHandler.isMathOperator(currenttoken))
		{
	    	if(currenttoken.equals("+"))
	    	{
	    		super.match("+");
	    		currenttoken = super.getCurrentToken();
	    		if (!skip) 
	    		{
					int operand = TokenHandler.readTokenValue(currenttoken);
					variablevalue += operand;
					TokenHandler.variables.put(variablebeingassigned, variablevalue);
				}
				super.match(currenttoken);
	    	}
	    	
	    	else if(currenttoken.equals("-"))
	    	{
	    		super.match("-");
	    		currenttoken = super.getCurrentToken();
	    		if (!skip)
	    		{
					int operand = TokenHandler.readTokenValue(currenttoken);
					variablevalue -= operand;
					TokenHandler.variables.put(variablebeingassigned, variablevalue);
				}
				super.match(currenttoken);
	    	}
	    	
	    	else if(currenttoken.equals("*"))
	    	{
	    		super.match("*");
	    		currenttoken = super.getCurrentToken();
	    		if (!skip)
	    		{
					int operand = TokenHandler.readTokenValue(currenttoken);
					variablevalue *= operand;
					TokenHandler.variables.put(variablebeingassigned, variablevalue);
				}
				super.match(currenttoken);
	    	}
	    	
	    	else if(currenttoken.equals("/"))
	    	{
	    		super.match("/");
	    		currenttoken = super.getCurrentToken();
	    		if (!skip) 
	    		{
					int operand = TokenHandler.readTokenValue(currenttoken);
					variablevalue /= operand;
					TokenHandler.variables.put(variablebeingassigned, variablevalue);
				}
				super.match(currenttoken);
	    	}
	    	
	    	currenttoken = super.getCurrentToken();
		}
		
		return resetTokens();
	}

}
