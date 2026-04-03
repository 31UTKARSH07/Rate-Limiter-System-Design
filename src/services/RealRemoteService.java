package services;

import interfaces.IRemoteService;

public class RealRemoteService implements IRemoteService {
    @Override
    public void processTask(String data) {
        System.out.println("Processing " + data + " on Remote Server...");
    }
}


