package learn.akka;

import akka.actor.ActorRef;
import akka.actor.DeadLetter;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;
import scala.concurrent.duration.Duration;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;

public class MyActor extends UntypedActor {

    protected ActorRef child;

    protected  static final SupervisorStrategy strategy = new OneForOneStrategy(10,
        Duration.create("10 second"), new Function<Throwable, Directive>() {
            @Override
            public Directive apply(Throwable t) {
                if (t instanceof ArithmeticException) {
                    return resume();
                } else if (t instanceof NullPointerException) {
                    return restart();
                } else if (t instanceof IllegalArgumentException) {
                    return stop();
                } else {
                    return escalate();
                }
            }
        });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DeadLetter) {
            System.out.println("DEAD LETTER x_x "+message);
        } else {
            if ("born".equals(message)) {
                child = context().actorOf(Props.create(MyChildActor.class),"childActor");
            } else {
                child.tell(message, self());
            }
        }
    }

}
