@FunctionalInterface
public interface EmergencyOverride {
    boolean shouldOverride(Vehicle v, Intersection i);
}