package learn.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import scala.Option;

public class SimpleActor extends UntypedActor {
    protected ActorRef childActor;

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("Restarting " + self());

        super.preRestart(reason, message);
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("Stopping " + self());

        super.postStop();
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("Starting " + self());

        super.preStart();
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if ("Born".equals(o)) {
            final ActorSystem system = context().system();

            childActor = system.actorOf(Props.create(SimpleActor.class), "child");

            getContext().watch(childActor);

            System.out.println("Child created");
        } else if ("Kill".equals(o)) {
            System.out.println("Kill child");

            childActor.tell(PoisonPill.getInstance(), self());
        } else if (o instanceof Terminated) {
            Terminated t = (Terminated)o;

            System.out.println("Child "+t.actor()+" was killed. And I've got message");
        } else {
            System.out.println("Got message "+o);
        }
    }

}
