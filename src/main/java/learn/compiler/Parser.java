package learn.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * code = code_block*
 * code_block = function_declaration | event_declaration
 * + function_declaration = KW_FUNCTION ATOM LBRACE params RBRACE function_body_declaration {global.functions.add(ATOM.text)}
 * + formal_params = formal_param? | formal_param (COMMA formal_param)+ 
 * + formal_param = ATOM
 * + function_body_declaration = LFBRACE expression* LFBRACE
 * + event_declaration = KW_BEGIN? LFBRACE expression* RFBRACE
 * + expression = statement? DELIMETER
 * + statement = assign | function_call | expr
 * + assign = ATOM ASSIGN expr {global.variables.add(ATOM.text)}
 * + function_call = ATOM LBRACE statement? RBRACE | ATOM LBRACE statement? (COMMA statement?)* RBRACE
 * 
 * + variable = ATOM {IF ATOM.text IN global.variables}
 * + expr = NUMBER | variable | variable INCREMENT | STRING | MACRO
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
		Return codeReturn = parseCode(0);
		
		AST codeAST = codeReturn.getAst();
		
		return codeAST;
	}
	
	protected boolean hasTokens(){
		if (currentTokenPosition < length) {
			return true;
		} else {
			return false;
		}
	}
	
	public Return parseCode(int base) {
		AST ast = new AST("code");
		
		int next = base;
		int stop = base;
		
		Return codeBlockReturn = parseCodeBlock(base);
		while (codeBlockReturn != null) {
			ast.addChild(codeBlockReturn.getAst());
			next = codeBlockReturn.getNext();
			stop = codeBlockReturn.getStop();
			codeBlockReturn = parseCodeBlock(next);
		}
		
		return new Return(base, stop, next, ast);
	}
	
	/*
	 * code_block = function_declaration | event_declaration
	 */
	public Return parseCodeBlock(int base){
		AST ast = new AST("code_block");
		
		Return functionReturn = parseFunctionDeclaration(base);
		if (functionReturn != null) {
			ast.addChild(functionReturn.getAst());
			
			return new Return(functionReturn.getStart(), functionReturn.getStop(), functionReturn.getNext(), ast);
		} else {
			Return eventDeclarationReturn = parseEventDeclaration(base);
			
			if (eventDeclarationReturn != null) {
				ast.addChild(eventDeclarationReturn.getAst());
			
				return new Return(eventDeclarationReturn.getStart(), eventDeclarationReturn.getStop(), eventDeclarationReturn.getNext(), ast);
			}
		}
		
		return null;
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
			return new Return(pos, pos, pos+1, tokenStream.get(pos));
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
			
			Return formalParamsReturn = parseFormalParams(r2.getNext());
			
			ast.addChild(formalParamsReturn.getAst());
			
			Return r4 = look(formalParamsReturn.getNext(),0);

			if (
				r4 != null
				&& r4.getToken().getTokenType() == TokenTypeEnum.RBRACE
			) {
				Return functionBody = parseFunctionBody(r4.getNext());

				if (functionBody!=null) {
					ast.addChild(functionBody.getAst());
				}
				
				return new Return(r0.getStart(), functionBody.getStop(),functionBody.getNext(), ast );
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
			return new Return(base, base, base, ast);
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
			return new Return(base, base, base, ast);
		}
		
		return new Return(start, stop, stop +1, ast);
	}

	public Return parseFunctionBody(int base) {
		Return r0 = look(base, 0);
		
		AST ast = new AST("function_body");
		
		if (
			r0 != null
			&& r0.getToken().getTokenType() == TokenTypeEnum.LFBRACE
		) {
			int next = r0.getNext();
			
			Return expressionReturn = parseExpression(next);
			
			if (expressionReturn!=null) {
				while(expressionReturn.getStop() != expressionReturn.getNext()) {
					if (
						expressionReturn.getStop() != expressionReturn.getNext()
					) {
						ast.addChild(expressionReturn.getAst());
					}

					next = expressionReturn.getNext();
				}
			}
			
			Return r1 = look(next, 0);
			
			if (
				r1 != null
				&& r1.getToken().getTokenType() == TokenTypeEnum.RFBRACE
			) {
				return new Return(r0.getStart(), r1.getStop(), r1.getNext(), ast);
			}
			
		} 
		
		return null;
	}
	
	/**
	 * expression = statement? DELIMETER
	 */
	public Return parseExpression(int base) {
		Return r = parseStatement(base);
		
		Return delim = look(r.getNext(), 0);
		
		if (
			delim != null
			&& delim.getToken() != null
			&& delim.getToken().getTokenType() == TokenTypeEnum.SEMICOLON
		) {
			AST ast = new AST("expression");

			ast.addChild(r.getAst());
			
			return new Return(r.getStart(),delim.getStop(),delim.getNext(),ast);
		} else {
			return null;
		}
	}
	
	/**
	 * statement = assign | function_call | expr
	 */
	public Return parseStatement(int base) {
		Return parseAssign = parseAssign(base);
		
		AST ast = new AST("statement");
		
		if (parseAssign != null) {
			ast.addChild(parseAssign.getAst());
					
			return new Return(parseAssign.getStart(), parseAssign.getStop(), parseAssign.getNext(), ast);
		} else {
			Return parseFunctionCall = parseFunctionCall(base);
			
			if (parseFunctionCall != null) {
				ast.addChild(parseFunctionCall.getAst());
					
				return new Return(parseFunctionCall.getStart(), parseFunctionCall.getStop(), parseFunctionCall.getNext(), ast);
			} else {
				Return parseExpr = parseExpr(base);
				
				if (parseExpr != null) {
					ast.addChild(parseExpr.getAst());
					
					return new Return(parseExpr.getStart(), parseExpr.getStop(), parseExpr.getNext(), ast);
				} else {
					return new Return(base, base, base, ast);
				}
			}
		}
	}
	
	/*
	 * assign = ATOM ASSIGN expr {global.variables.add(ATOM.text)}
	 */
	public Return parseAssign(int base) {
		Return r1 = look(base, 0);
		
		if (
			r1 != null
			&& r1.getToken() != null
			&& r1.getToken().getTokenType() == TokenTypeEnum.ATOM
		) {
			Return r2 = look(base, 1);
			
			if (
				r2 != null
				&& r2.getToken() != null
				&& r2.getToken().getTokenType() == TokenTypeEnum.ASSIGN
			) {
				Return r3 = parseExpr(r2.getNext());
				
				if (r3 != null) {
					AST ast = new AST("assign");
					
					ast.addChild(r3.getAst());
					
					return new Return(r1.getStart(), r3.getStop(), r3.getNext(), ast);
				}
			}
		}
		
		return null;
	}
	
	/*
	 * function_call = ATOM LBRACE statement? RBRACE | ATOM LBRACE statement? (COMMA statement?)* RBRACE
	 */
	public Return parseFunctionCall(int base) {
		Return r0 = look(base, 0);
		Return r1 = look(base, 1);
		
		AST ast = new AST("function_call");
		
		if (
			r0 != null
			&& r0.getToken() != null
			&& r0.getToken().getTokenType() == TokenTypeEnum.ATOM
			&& r1 != null
			&& r1.getToken() != null
			&& r1.getToken().getTokenType() == TokenTypeEnum.LBRACE
		) {
			ast.addChild(new AST(r0.getToken().getText()));
			
			int next = r1.getNext();
			
			Return returnStatement = parseStatement(next);
			
			AST paramsAst = new AST("params");
			
			if (returnStatement!=null) {
				paramsAst.addChild(returnStatement.getAst());
				
				next = returnStatement.getNext();
				
				boolean fl = true;
				
				while (fl) {
					fl = false;
					
					Return commaReturn=look(next, 0);
					
					if (
						commaReturn!=null
						&& commaReturn.getToken() != null
						&& commaReturn.getToken().getTokenType() == TokenTypeEnum.COMMA
					) {
						Return nParamStatement = parseStatement(commaReturn.getNext());
						
						if (nParamStatement != null) {
							fl = true;
							next = nParamStatement.getNext();
							
							paramsAst.addChild(nParamStatement.getAst());
						}
					}
				}
			}
			
			ast.addChild(paramsAst);
			
			Return r2 = look(next, 0);
			
			if (
				r2 != null
				&& r2.getToken() != null
				&& r2.getToken().getTokenType() == TokenTypeEnum.RBRACE
			) {
				return new Return(r0.getStart(), r2.getStop(), r2.getNext(), ast);
			}
		}
		
		return null;
	}
	
	/*
	 * expr = NUMBER | variable | variable INCREMENT | STRING | MACRO
	 */
	public Return parseExpr(int base) {
		AST ast = new AST("expr");
		
		Return r0 = look(base, 0);
		Return r1 = look(base, 1);
		
		if (
			r0 != null
			&& r0.getToken() != null
		) {
			AST childAst = null;
			
			int stop = r0.getStop();
			int next = r0.getNext();
			
			boolean checkVariable = false;
			
			switch (r0.getToken().getTokenType()) {
				case STRING:
					childAst = new AST("string");
					childAst.addChild(new AST(r0.getToken().getText()));
					break;
				case NUMBER:
					childAst = new AST("number");
					childAst.addChild(new AST(r0.getToken().getText()));
					break;
				case MACRO:
					childAst = new AST("macro");
					childAst.addChild(new AST(r0.getToken().getText()));
					break;
				default:
					checkVariable = true;
			}
			
			// check variable
			if (checkVariable) {
				Return var = parseVariable(base);
				
				if (var != null) {
					if (
						r1 != null
						&& r1.getToken() != null
						&& r1.getToken().getTokenType() == TokenTypeEnum.INCREMENT
					) {
						next = r1.getNext();
						stop = r1.getStop();
						
						childAst = new AST("increment");
						childAst.addChild(var.getAst());
					} else {
						childAst = var.getAst();
					}
				}
			}
			
			
			if (childAst != null) {
				ast.addChild(childAst);
				
				return new Return(r0.getStart(), stop, next, ast);
			}
		}
		
		return null;
	}
	
	/*
	 * variable = ATOM {IF ATOM.text IN global.variables}
	 */
	public Return parseVariable(int base) {
		AST ast = new AST("variable");
		
		Return r0 = look(base, 0);
		
		if (
			r0 != null
			&& r0.getToken() != null
			&& r0.getToken().getTokenType() == TokenTypeEnum.ATOM
		) {
			ast.addChild(new AST(r0.getToken().getText()));
			
			return new Return(r0.getStart(), r0.getStop(), r0.getNext(), ast);
		}
		
		return null;
	}
	
	/*
	 * event_declaration = KW_BEGIN? LFBRACE expression* RFBRACE
	 */
	public Return parseEventDeclaration(int base) {
		AST ast = new AST("event_declaration");
		
		Return r0 = look(base,0);
		
		int next = base;
		
		if (
			r0 != null
			&& r0.getToken() != null
			&& r0.getToken().getTokenType() == TokenTypeEnum.KW_BEGIN
		) {
			next = r0.getNext();
			
			ast.addChild(new AST("begin"));
		} else {
			ast.addChild(new AST("each"));
		}
		
		Return r1 = look(next,0);
		
		if (
			r1 != null
			&& r1.getToken() != null
			&& r1.getToken().getTokenType() == TokenTypeEnum.LFBRACE
		) {
			next = r1.getNext();
			
			AST bodyAst = new AST("body");
			
			// parse expressions
			Return exprReturn = parseExpression(next);
			if (exprReturn!=null) {
				do {
					bodyAst.addChild(exprReturn.getAst());
					next = exprReturn.getNext();
					exprReturn = parseExpression(next);
				} while (exprReturn!=null);
			}
			
			ast.addChild(bodyAst);
			
			Return r2 = look(next,0);
			
			if (
				r2 != null
				&& r2.getToken() != null
				&& r2.getToken().getTokenType() == TokenTypeEnum.RFBRACE
			) {
				return new Return(base, r2.getStop(), r2.getNext(), ast);
			}
		}
		
		return null;
	}
	
	public class Return {
		protected int start;
		protected int stop;
		protected int next;
		protected AST ast;
		protected Token token;
		
		public Return(int start, int stop, int next, Token t) {
			this.start = start;
			this.stop = stop;
			this.next = next;
			this.token = t;
		}
		
		public Return(int start, int stop, int next, AST ast) {
			this.start = start;
			this.stop = stop;
			this.next = next;
			this.ast = ast;
		}
		
		public int getNext() {
			return next;
		}

		public void setNext(int next) {
			this.next = next;
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

		@Override
		public String toString() {
			return 
				"Return{" 
					+ "start=" + start 
					+ ", stop=" + stop 
					+ ", next=" + next 
					+ (ast != null ? ", ast=" + ast : "") 
					+ (token != null ? ", token=" + token : "") 
					+ '}';
		}
	}
}
