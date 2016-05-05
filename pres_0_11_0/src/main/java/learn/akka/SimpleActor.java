package learn.akka;

import akka.actor.UntypedActor;

public class SimpleActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        System.out.println("I'm actor "+self()+" from "+self().path());
		
		System.out.println("I've got message "+o);
    }

}
