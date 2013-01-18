package learn.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AST {
	protected List<AST> children = null;
	
	protected Object value = null;

	public List<AST> getChildren() {
		return children;
	}

	public void setChildren(List<AST> children) {
		this.children = children;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public AST(Object value) {
		this.value = value;
	}
	
	public void addChild(AST ast) {
		if (children == null) {
			children = new ArrayList<AST>();
		}
		
		children.add(ast);
	}
	
	public boolean hasChildren(){
		return children != null;
	}
	
	public String toString()
	{
		return toString(1);
	}
	
	public String toString(int level)
	{
		String padding = String.format("%"+level+"s", " ");
		
		String currentValue = padding + value.toString() + "\n";
		if (hasChildren()) {
			for (AST a:getChildren()) {
				currentValue+= a.toString(level+2);
			}
		}
		
		return currentValue;
	}
}
