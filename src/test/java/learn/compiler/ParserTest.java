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
}
