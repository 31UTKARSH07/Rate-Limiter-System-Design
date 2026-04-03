package strategies;

import interfaces.IRateLimitStrategy;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements IRateLimitStrategy {
    private final int limit;
    private final long windowSizeInMS;
    private final ConcurrentHashMap<String , Window> clientData = new ConcurrentHashMap<>();

    public FixedWindowStrategy(int limit , long windowSizeInMS){
        this.limit = limit;
        this.windowSizeInMS = windowSizeInMS;
    }

    @Override
    public boolean isAllowed(String clientId){
        long currentTime = System.currentTimeMillis();
        long currentWindow = currentTime / windowSizeInMS;

        Window window = clientData.get(clientId);

        if (window == null || window.windowId != currentWindow){
            window = new Window(currentWindow,1);
            clientData.put(clientId,window);
            return true;
        }

        if (window.count < limit) {
            window.count++;
            return true;
        }
        return false;
    }

    private static class Window{
        long windowId;
        int count;

        Window(long windowId , int count){
            this.windowId = windowId;
            this.count = count;
        }
    }


}

