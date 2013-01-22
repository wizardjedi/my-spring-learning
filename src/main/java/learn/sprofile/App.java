package learn.sprofile;

import org.springframework.context.support.GenericXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("app-context.xml");
		
		Service service = (Service)ctx.getBean("service");
		
		System.out.println("Requester " + service.serve());
		System.out.println("Data provider" + service.getData());
    }
}
