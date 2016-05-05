package learn.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.DeadLetter;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Config config = ConfigFactory.parseString("akka.log-dead-letters = off");

        ActorSystem system = ActorSystem.create("system",config);

        ActorRef actorOf = system.actorOf(Props.create(MyActor.class),"actor");

        system.eventStream().subscribe(actorOf, DeadLetter.class);

        TimeUnit.SECONDS.sleep(1);

        actorOf.tell("born", ActorRef.noSender());

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("----Some message----");

        System.out.println(" ! arith");TimeUnit.MILLISECONDS.sleep(1500);

        actorOf.tell("arith", ActorRef.noSender()); // resume

        TimeUnit.MILLISECONDS.sleep(1500);

        System.out.println(" ! null");TimeUnit.MILLISECONDS.sleep(1500);

        actorOf.tell("null", ActorRef.noSender()); // restart

        TimeUnit.MILLISECONDS.sleep(1500);

        System.out.println(" ! illegal");TimeUnit.MILLISECONDS.sleep(500);

        actorOf.tell("illegal", ActorRef.noSender()); // stop

        TimeUnit.MILLISECONDS.sleep(1500);

        System.out.println(" ! exception");TimeUnit.MILLISECONDS.sleep(500);

        actorOf.tell("exception", ActorRef.noSender()); // should invoke escalate, but actor is dead. msg send to dead lettes.

        TimeUnit.MILLISECONDS.sleep(1500);

        System.out.println(" ! another");TimeUnit.MILLISECONDS.sleep(500);

        actorOf.tell("another", ActorRef.noSender()); // actor is dead. msg send to dead lettes.

        TimeUnit.SECONDS.sleep(1);

        System.out.println("Wait 3 and shutdown system");

        TimeUnit.SECONDS.sleep(3);

        system.shutdown();
    }
}
