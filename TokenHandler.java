import java.util.HashMap;

public class TokenHandler
{
	public static HashMap<Character, Integer> variables = new HashMap <Character, Integer>();
	
	public static boolean isVariable(String token)
	{
		return token.matches("[a-zA-Z]");
	}
	
	public static boolean isMathOperator(String token)
	{
		return (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"));
	}
	
	public static boolean isConstant(String token)
	{
		return token.matches("\\d+");
	}
	
	public static boolean isComparisonOperator(String token)
	{
		return (token.equals("<") || token.equals(">") || token.equals("<=") || token.equals(">=") || token.equals("=") || token.equals("/="));
	}
	
	public static int readTokenValue(String token) throws VariableException
	{
		if(isVariable(token))
		{
			Integer variablevalue = variables.get(token.charAt(0));
			if(variablevalue == null)
				throw new VariableException("Variable " + token + " not declared.");
			return variablevalue;
		}
		
		else if(isConstant(token))
		{
			return Integer.parseInt(token);
		}
		
		else
			throw new VariableException("Not a valid variable: " + token);	
	}
}
