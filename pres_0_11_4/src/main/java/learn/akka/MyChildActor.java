package learn.akka;

import akka.actor.UntypedActor;
import scala.Option;

public class MyChildActor extends UntypedActor {

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("> preRestart "+self());

        super.preRestart(reason, message);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        System.out.println("> postRestart "+self());

        super.postRestart(reason);
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("> postStop" + self());

        super.postStop();
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("> preStart" + self());

        super.preStart();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("Processing "+self().path()+" msg:"+message);

        if ("arith".equals(message)) {
            throw new ArithmeticException();
        } else if ("null".equals(message)) {
            throw new NullPointerException();
        } else if ("illegal".equals(message)) {
            throw new IllegalArgumentException();
        } else if ("exception".equals(message)) {
            throw new Exception("Other");
        } else {
            System.out.println("::"+message);
        }
    }

}
