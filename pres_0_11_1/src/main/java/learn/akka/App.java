package learn.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Actor system create");

        ActorSystem system = ActorSystem.create("system");

        System.out.println("Actor system created");

        ActorRef actor = system.actorOf(Props.create(SimpleActor.class));

        System.out.println("Actor created");

        actor.tell("Hello", ActorRef.noSender());

        System.out.println("Wait 2 seconds");

        TimeUnit.SECONDS.sleep(2);

        System.out.println("Shutting down");

        system.shutdown();
    }

}
