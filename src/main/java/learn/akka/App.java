package learn.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class App {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("system");
		ActorRef actor = system.actorOf(Props.create(SimpleActor.class));
		
		actor.tell("Hello", ActorRef.noSender());
		
		system.shutdown();
    }

}
