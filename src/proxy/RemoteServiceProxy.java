package proxy;

import interfaces.IRateLimitStrategy;
import interfaces.IRemoteService;

public class RemoteServiceProxy implements IRemoteService {
    private final IRemoteService realService;
    private final IRateLimitStrategy rateLimiter;
    private final String clientId;

    public RemoteServiceProxy(IRemoteService realService , IRateLimitStrategy strategy , String clientId){
        this.realService  =realService;
        this.rateLimiter = strategy;
        this.clientId = clientId;
    }

    @Override
    public void processTask(String data){
        if (rateLimiter.isAllowed(clientId)){
            realService.processTask(data);
        }else {
            System.out.println("Error 429: Too much requests for Client: "+clientId);
        }
    }
}
