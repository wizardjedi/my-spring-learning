package learn.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

public class ParserTest extends TestCase {
	public void testParseSimpleFunctionDeclaration(){
		Token[] tokens = new Token[]{
			Token.FUNCTION("function"), 
			Token.ATOM("testFunc"), 
			Token.LBRACE("("), 
			Token.RBRACE(")"),
			Token.LFBRACE("{"), 
			Token.RFBRACE("}"),
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseFunctionDeclaration(0);
		
		assertTrue(r!=null);
		
		AST ast = r.getAst();
		
		AST expectedAst = new AST("function_declaration");
		expectedAst.addChild(new AST("testFunc"));
		expectedAst.addChild(new AST("formal_params"));
		expectedAst.addChild(new AST("function_body"));
		
		assertEquals(expectedAst.toString(), ast.toString());
	}
	
	public void testNoFunctionBody() {
		Token[] tokens = new Token[]{
			Token.LFBRACE("{"),
			Token.RFBRACE("}")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseFunctionBody(0);
		AST ast = r.getAst();
		
		AST exp = new AST("function_body");
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testParseParams(){
		Token[] tokens = new Token[]{
			Token.ATOM("param1"), 
			Token.COMMA(","),
			Token.ATOM("param2")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseFormalParams(0);
		AST ast = r.getAst();
		
		AST exp = new AST("formal_params");
		exp.addChild(new AST("param1"));
		exp.addChild(new AST("param2"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testParseSingleParams(){
		Token[] tokens = new Token[]{
			Token.ATOM("param1"), 
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseFormalParams(0);
		AST ast = r.getAst();
		
		AST exp = new AST("formal_params");
		exp.addChild(new AST("param1"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testParseNoParams(){
		Token[] tokens = new Token[]{
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseFormalParams(0);
		
		AST ast = r.getAst();
		
		AST exp = new AST("formal_params");
		
		assertEquals(exp.toString(), ast.toString());
		assertEquals(0, r.getStart());
		assertEquals(0, r.getStop());
	}
	
	public void testRBraceParseNoParams(){
		Token[] tokens = new Token[]{
			Token.RBRACE(")")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseFormalParams(0);

		AST ast = r.getAst();
		
		AST exp = new AST("formal_params");
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testEmptyExpression(){
		Token[] tokens = new Token[]{
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpression(0);

		assertEquals(null, r);
	}
	
	public void testSemicolonExpression(){
		Token[] tokens = new Token[]{
			Token.SEMICOLON(";")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpression(0);

		AST ast = r.getAst();
		
		AST exp = new AST("expression");
		exp.addChild(new AST("statement"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testNumberExpr(){
		Token[] tokens = new Token[]{
			Token.NUMBER("123")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpr(0);

		AST ast = r.getAst();
		
		AST exp = new AST("expr");
		AST num = new AST("number");
		exp.addChild(num);
		num.addChild(new AST("123"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testStringExpr(){
		Token[] tokens = new Token[]{
			Token.STRING("testString")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpr(0);

		AST ast = r.getAst();
		
		AST exp = new AST("expr");
		AST num = new AST("string");
		exp.addChild(num);
		num.addChild(new AST("testString"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testMacroExpr(){
		Token[] tokens = new Token[]{
			Token.MACRO("__LINE__")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpr(0);

		AST ast = r.getAst();
		
		AST exp = new AST("expr");
		AST num = new AST("macro");
		exp.addChild(num);
		num.addChild(new AST("__LINE__"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testVariableExpr(){
		Token[] tokens = new Token[]{
			Token.ATOM("var123")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpr(0);

		AST ast = r.getAst();
		
		AST exp = new AST("expr");
		AST num = new AST("variable");
		exp.addChild(num);
		num.addChild(new AST("var123"));
		
		assertEquals(exp.toString(), ast.toString());
	}
	
	public void testVariableIncrementExpr(){
		Token[] tokens = new Token[]{
			Token.ATOM("var123"),
			Token.INCREMENT("++")
		};
		
		List<Token> list = Arrays.asList(tokens);
		
		Parser p = new Parser(list);
		
		Parser.Return r = p.parseExpr(0);

		AST ast = r.getAst();
		
		AST exp = new AST("expr");
		AST inc = new AST("increment");
		exp.addChild(inc);
		AST num = new AST("variable");
		num.addChild(new AST("var123"));
		inc.addChild(num);
		
		assertEquals(exp.toString(), ast.toString());
	}
}
