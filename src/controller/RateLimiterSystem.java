package controller;

import interfaces.IRateLimitStrategy;
import interfaces.IRemoteService;
import proxy.RemoteServiceProxy;
import services.RealRemoteService;
import strategies.FixedWindowStrategy;

public class RateLimiterSystem {
    public static void main(String[] args) {
        IRateLimitStrategy fixedWindow = new FixedWindowStrategy(1,1000);

        IRemoteService s1 = new RealRemoteService();

        IRemoteService proxy = new RemoteServiceProxy(s1,fixedWindow , "User123");

        System.out.println("Hit 1:");
        proxy.processTask("Data A");

        System.out.println(" Hit 2 (Immediate):");
        proxy.processTask("Data B");

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        System.out.println("Hit 3 (After 1 sec): ");
        proxy.processTask("Data C");
    }
}
