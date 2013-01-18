package learn.compiler;

/**
 *
 */
public class Token {
	protected TokenTypeEnum tokenType = TokenTypeEnum.UNKNOWN;
	protected int start;
	protected int stop;
	protected String text;

	public static Token ATOM(String text) {
		return new Token(TokenTypeEnum.ATOM, text);
	}
	
	public static Token STRING(String text) {
		return new Token(TokenTypeEnum.STRING, text);
	}
	
	public static Token COMMA(String text) {
		return new Token(TokenTypeEnum.COMMA, text);
	}
	
	public static Token FUNCTION(String text) {
		return new Token(TokenTypeEnum.KW_FUNCTION, text);
	}
	
	public static Token LBRACE(String text) {
		return new Token(TokenTypeEnum.LBRACE, text);
	}
	
	public static Token RBRACE(String text) {
		return new Token(TokenTypeEnum.RBRACE, text);
	}
	
	public static Token LFBRACE(String text) {
		return new Token(TokenTypeEnum.LFBRACE, text);
	}
	
	public static Token RFBRACE(String text) {
		return new Token(TokenTypeEnum.RFBRACE, text);
	}
	
	public Token(){
		
	}
	
	public Token(TokenTypeEnum tt) {
		this.tokenType = tt;
	}
	
	public Token(TokenTypeEnum tt, String text) {
		this.tokenType = tt;
		this.text = text;
	}
	
	public Token(String text) {
		this.text = text;
	}
	
	public TokenTypeEnum getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenTypeEnum tokenType) {
		this.tokenType = tokenType;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return 
			getTokenType().toString()
				+"["
				+getStart()
				+":"
				+getStop()
				+"]"
				+"("
				+getText()
				+")";
	}
}
