package learn.compiler;

import java.util.ArrayList;
import java.util.List;
import learn.compiler.Token;

/**
 * code = code_block*
 * code_block = function_declaration | event_declaration
 * function_declaration = KW_FUNCTION ATOM LBRACE params RBRACE function_body_declaration {global.functions.add(ATOM.text)}
 * params = param? (COMMA param)
 * param = statement 
 * function_body_declaration = LFBRACE expression* LFBRACE
 * event_declaration = KW_BEGIN? LFBRACE expression* RFBRACE
 * expression = statement DELIMETER
 * statement = assign | function_call | expr
 * assign = ATOM ASSIGN expr {global.variables.add(ATOM.text)}
 * function_call = ATOM LBRACE statement? RBRACE | ATOM LBRACE statement (COMMA statement)* RBRACE
 * 
 * variable = ATOM {IF ATOM.text IN global.variables}
 * expr = NUMBER | variable | variable INCREMENT | STRING
 */
public class Parser {
	protected List<Token> tokenStream;

	protected int currentTokenPosition = 0;
	protected int length = 0;
	
	protected List<String> variables = new ArrayList<String>();
	protected List<String> functions = new ArrayList<String>();
	
	public Parser(List<Token> tokens) 
	{
		this.tokenStream = tokens;
		
		this.length = tokens.size();
	}

	public AST parse(){
		AST codeAST = parseCode();
		
		return codeAST;
	}
	
	protected boolean hasTokens(){
		if (currentTokenPosition < length) {
			return true;
		} else {
			return false;
		}
	}
	
	public AST parseCode() {
		AST ast = new AST("code");
		
		//while (hasTokens()) {
			ast.addChild(parseCodeBlock());
		//}
		return ast;
	}
	
	public AST parseCodeBlock(){
		AST ast = null;
		/*AST ast = parseFunctionDeclaration();
		
		if (ast == null) {
			ast = parseEventDeclaration();
		}*/
		
		return ast;
	}
	
	protected void nextToken(){
		if (currentTokenPosition<length-1) {
			currentTokenPosition++;

			while (
				tokenStream.get(currentTokenPosition).getTokenType() == TokenTypeEnum.COMMENT
				&& currentTokenPosition<length
			) {
				currentTokenPosition++;
			}
		}
	}
	
	protected boolean lookAhead(int base, TokenTypeEnum tt, int pos){
		if (look(base, pos).getToken().getTokenType() == tt) {
			return true;
		} else {
			return false;
		}
	}
	
	protected Return look(int base,int lookPeriod){
		int step = lookPeriod >= 0 ? +1 : -1;
		
		int period = Math.abs(lookPeriod);
		
		int pos = base;
		
		while (
			pos >= 0
			&& pos < this.length
			&& tokenStream.get(pos).getTokenType() == TokenTypeEnum.COMMENT
		) {
			pos += step;
		}
		
		while (period > 0) {
			pos += step;
				
			while (
				pos >= 0
				&& pos < this.length
				&& tokenStream.get(pos).getTokenType() == TokenTypeEnum.COMMENT
			) {
				pos += step;
			}
				
			period--;
		}
		
		try {
			return new Return(pos, pos, tokenStream.get(pos));
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public Return parseFunctionDeclaration(int base) {
		AST ast = null;

		Return r0 = look(base, 0);
		Return r1 = look(base, 1);
		Return r2 = look(base, 2);
		
		if (
			r0 != null
			&& r0.getToken().getTokenType() == TokenTypeEnum.KW_FUNCTION
			&& r1 != null
			&& r1.getToken().getTokenType() == TokenTypeEnum.ATOM
			&& r2 != null
			&& r2.getToken().getTokenType() == TokenTypeEnum.LBRACE
		) {
			ast = new AST("function_declaration");

			ast.addChild(new AST(r1.getToken().getText()));
			
			int start = r0.getStart();
			
			Return formalParamsReturn = parseFormalParams(r2.getStop()+1);
			
			Return r4 = look(formalParamsReturn.getStop()+1,1);

			System.out.println(r4.getToken());
			
			if (
				r4 != null
				&& r4.getToken().getTokenType() == TokenTypeEnum.RBRACE
			) {
				Return functionBody = parseFunctionBody(r4.getStop()+1);
				
				ast.addChild(r4.getAst());
				
				return new Return(r0.getStart(), functionBody.getStop(), ast );
			}
		}
		
		return null;
	}

	public AST parseEventDeclaration() {
		nextToken();
		
		return null;
	}

	public Return parseFormalParams(int base) {
		AST ast = new AST("formal_params");
		
		Return r0 = look(base, 0);
		
		if (r0 == null) {
			return new Return(base-1, base-1, ast);
		}
		
		Token t0 = r0.getToken();
		
		int start = t0.getStart();
		int stop = t0.getStart();
		
		if (
			t0 != null
			&& t0.getTokenType() == TokenTypeEnum.ATOM
		) {
			ast.addChild(new AST(t0.getText()));
			
			int pos = 1;
			
			Return r1,r2;
			
			while (
				((r1 = look(base, pos)) != null)
				&& (r1.getToken().getTokenType() == TokenTypeEnum.COMMA)
				&& ((r2 = look(base, pos+1)) != null)
				&& (r2.getToken().getTokenType() == TokenTypeEnum.ATOM)
			) {
				ast.addChild(new AST(r2.getToken().getText()));
				
				stop = r2.getStop();
				
				pos+=2;
			}
		} else {
			return new Return(base-1, base-1, ast);
		}
		
		return new Return(start, stop, ast);
	}

	public Return parseFunctionBody(int base) {
		Return r0 = look(base, 0);
		// HACK!
		Return r1 = look(base, 1);
		
		if (
			r0 != null
			&& r0.getToken().getTokenType() == TokenTypeEnum.LFBRACE
			&& r1 != null
			&& r1.getToken().getTokenType() == TokenTypeEnum.RFBRACE
		) {
			return new Return(r0.getStart(), r1.getStop(), new AST("function_body"));
		} else {
			return new Return(base-1, base-1, new AST(""));
		}
	}
	
	public class Return {
		protected int start;
		protected int stop;
		protected AST ast;
		protected Token token;
		
		public Return(int start, int stop, Token t) {
			this.start = start;
			this.stop = stop;
			this.token = t;
		}
		
		public Return(int start, int stop, AST ast) {
			this.start = start;
			this.stop = stop;
			this.ast = ast;
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

		public AST getAst() {
			return ast;
		}

		public void setAst(AST ast) {
			this.ast = ast;
		}

		public Token getToken() {
			return token;
		}

		public void setToken(Token token) {
			this.token = token;
		}
	}
}
