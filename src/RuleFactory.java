import java.util.function.Function;
import java.util.function.Predicate;

public class RuleFactory {
    // Speed Trap Rule: returns true if vehicle speed exceeds maxSpeed
    public static Predicate<Vehicle> createSpeedLimitRule(int maxSpeed) {
        return vehicle -> vehicle.getSpeedKmh() > maxSpeed;
    }

    // Signal Timing Rule: Base 30s - (congestion * 2), minimum 10s
    public static Function<Intersection, Integer> calculateGreenLightDuration() {
        return intersection -> {
            int baseTime = 30;
            int deduction = intersection.getCongestionLevel() * 2;
            int calculatedTime = baseTime - deduction;
            return Math.max(calculatedTime, 10);
        };
    }
}