package learn.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Tokenizer {
	protected int currentPosition = 0;

	protected String currentText="";
	
	protected TokenTypeEnum tokenType=null;
	
	protected String code;
	
	protected int tokenStart;
	
	protected List<Token> tokenList;
	
	public Tokenizer(String code) {
		this.code = code;
		
		this.tokenList = new ArrayList<Token>();
	}
	
	public List<Token> tokenize() {
		currentPosition=0;
		currentText="";
		while (currentPosition < code.length()) {
			if (
				tokenType == null
				&& isWs()
			) {
				currentPosition++;
			} else if (
				tokenType == null
				&& isCommentBegin()
			){
				tokenType = TokenTypeEnum.COMMENT;
				currentPosition+=2;
				tokenStart=currentPosition;
			} else if (
				tokenType == TokenTypeEnum.COMMENT
				&& !isCommentEnd()
			) {
				currentText+=curChar();
				currentPosition++;
			} else if (
				tokenType == TokenTypeEnum.COMMENT
				&& isCommentEnd()
			) {
				addToken(tokenStart, currentPosition-1, currentText, TokenTypeEnum.COMMENT);
				currentPosition+=2;
				currentText="";
				tokenType=null;
			} else if (
				tokenType == null
				&& isStringStart()
			){
				tokenType = TokenTypeEnum.STRING;
				currentPosition++;
				tokenStart=currentPosition;
			} else if (
				tokenType == TokenTypeEnum.STRING
				&& !isStringEnd()
			) {
				currentText+=curChar();
				currentPosition++;
			} else if (
				tokenType == TokenTypeEnum.STRING
				&& isStringEnd()
			) {
				addToken(tokenStart, currentPosition-1, currentText, TokenTypeEnum.STRING);
				currentPosition++;
				currentText="";
				tokenType=null;	
			} else if (
				tokenType == null
				&& isMacroStart()
			){
				tokenType = TokenTypeEnum.MACRO;
				currentPosition+=2;
				tokenStart=currentPosition;
			} else if (
				tokenType == TokenTypeEnum.MACRO
				&& !isMacroEnd()
			) {
				currentText+=curChar();
				currentPosition++;
			} else if (
				tokenType == TokenTypeEnum.MACRO
				&& isMacroEnd()
			) {
				addToken(tokenStart, currentPosition-1, currentText, TokenTypeEnum.MACRO);
				currentPosition+=2;
				currentText="";
				tokenType=null;
			} else if (
				tokenType == null
				&& isControlCharacter()
			) {
				currentText = Character.toString(curChar());
				
				addToken(currentPosition, currentPosition, currentText, guesToken(currentText));
				
				currentText = "";
				currentPosition++;
			} else if (
				isIncrement()	
			) {
				if (tokenType!=null) {
					addToken(tokenStart, currentPosition-1, currentText, guesToken(currentText));
				}
				
				addToken(currentPosition, currentPosition+1, "++", TokenTypeEnum.INCREMENT);
				currentPosition+=2;
				currentText="";
				tokenType=null;
			} else if (
				tokenType == null
				&& !isWs()
				&& !isControlCharacter()
			) {
				tokenStart=currentPosition;
				currentText=Character.toString(curChar());
				currentPosition++;
				tokenType=TokenTypeEnum.UNKNOWN;
			} else if (
				tokenType != null
				&& !isWs()
				&& !isControlCharacter()
			) {	
				currentText+=curChar();
				currentPosition++;
			} else if (
				tokenType != null
				&& (
					isWs()
					|| isControlCharacter()
				)
			) {
				addToken(tokenStart, currentPosition-1, currentText, guesToken(currentText));
				
				if (isControlCharacter()) {
					currentText = Character.toString(curChar());
				
					addToken(currentPosition, currentPosition, currentText, guesToken(currentText));
				}
				
				tokenType=null;
				currentText = "";
				currentPosition++;
			}
		}
		
		return tokenList;
	}
	
	protected TokenTypeEnum guesToken(String token){
		String[] tokens = new String[]{",",";","(",")","{","}","BEGIN", "FUNCTION","="};
		TokenTypeEnum[] tokenTypes = new TokenTypeEnum[]{
			TokenTypeEnum.COMMA,
			TokenTypeEnum.SEMICOLON,
			TokenTypeEnum.LBRACE,
			TokenTypeEnum.RBRACE,
			TokenTypeEnum.LFBRACE,
			TokenTypeEnum.RFBRACE,
			TokenTypeEnum.KW_BEGIN,
			TokenTypeEnum.KW_FUNCTION,
			TokenTypeEnum.ASSIGN
		};
		
		for (int i=0;i<tokens.length;i++) {
			if (token == null ? tokens[i] == null : token.toUpperCase().equals(tokens[i])) {
				return tokenTypes[i];
			}
		}
		
		try {
			int value = Integer.parseInt(token);
			
			return TokenTypeEnum.NUMBER;
		} catch (NumberFormatException e) {
		}
		
		return TokenTypeEnum.ATOM;
	}
	
	protected boolean isControlCharacter()
	{
		if (
			code.charAt(currentPosition) == ','
			|| code.charAt(currentPosition) == ';'
			|| code.charAt(currentPosition) == '('
			|| code.charAt(currentPosition) == ')'
			|| code.charAt(currentPosition) == '{'
			|| code.charAt(currentPosition) == '}'
			|| code.charAt(currentPosition) == '='
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void addToken(int start,int stop,String text, TokenTypeEnum type)
	{
		Token t = new Token();
		t.setStart(start);
		t.setStop(stop);
		t.setText(text);
		t.setTokenType(type);
		
		tokenList.add(t);
	}
	
	protected char curChar(){
		return code.charAt(currentPosition);
	}
	
	protected boolean isIncrement(){
		if (
			code.charAt(currentPosition) == '+'
			&& code.charAt(currentPosition+1) == '+'
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isStringStart(){
		if (code.charAt(currentPosition) == '"') {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isMacroStart(){
		if (
			code.charAt(currentPosition) == '_'
			&& code.charAt(currentPosition+1) == '_'
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isMacroEnd(){
		if (
			code.charAt(currentPosition) == '_'
			&& code.charAt(currentPosition+1) == '_'
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isStringEnd(){
		if (
			code.charAt(currentPosition) == '"'
			&& code.charAt(currentPosition-1) != '\\'
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isCommentBegin(){
		if (
			code.charAt(currentPosition) == '/'
			&& code.charAt(currentPosition+1) == '*'
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isCommentEnd(){
		if (
			code.charAt(currentPosition) == '*'
			&& code.charAt(currentPosition+1) == '/'
		) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isWs(){
		char curChar = this.code.charAt(currentPosition);
		
		if (
			curChar == ' '
			|| curChar == '\t'
			|| curChar == '\n'
			|| curChar == '\r'
		) {
			return true;
		} else {
			return false;
		}
	}
}
