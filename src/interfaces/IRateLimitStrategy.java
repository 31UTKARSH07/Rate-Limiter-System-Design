package interfaces;

public interface IRateLimitStrategy {
    boolean isAllowed(String clientId);
}
