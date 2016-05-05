package learn.akka;

import akka.actor.UntypedActor;
import scala.Option;

public class SimpleActor extends UntypedActor {

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
        System.out.println("I'm actor " + self() + " from " + self().path());

        System.out.println("I've got message " + o);
    }

}
