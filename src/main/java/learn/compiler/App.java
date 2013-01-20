package learn.compiler;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		String testcode = "\n"
				+ "\n"
				+ "/* this function will \n"
				+ "print all values */\n"
				+ "\n\n\n"
				+ "function doPrint (i,b )	{\n"
				+ "\tprint (\"String number is:\", a,\" values is \",b);\n"
				+ "}\n"
				+ "BEGIN {i=1;}\n"
				+ "{\n"
				+ "i++;\n"
				+ "doPrint(i, __LINE__);\n"
				+ "}\n";
		
		String testData = "5\n"
				+ "6\n"
				+ "7\n"
				+ "8\n"
				+ "9";
		
		System.out.println("<testcode>");
		System.out.println(testcode);
		System.out.println("</testcode>");
		
		Tokenizer t = new Tokenizer(testcode);
		
		List<Token> list = t.tokenize();
		
		System.out.println("<tokens>");
		System.out.println(list);
		System.out.println("</tokens>");
		
		Parser p = new Parser(list);
		
		AST ast = p.parse();
		
		System.out.println("<rules>");
		System.out.println(ast);
		System.out.println("</rules>");
    }
}
