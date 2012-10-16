import java.util.ArrayList;


public class Print extends Statement
{

	public Print(ArrayList<String> tokens)
	{
		super(tokens);
	}
	
	public int execute(boolean skip) throws ParserException, VariableException 
	{
		super.match("print");
		String nexttoken = super.getCurrentToken();
		if(!skip)
			System.out.println(TokenHandler.readTokenValue(nexttoken));
		super.match(nexttoken);
		return resetTokens();
	}

}
