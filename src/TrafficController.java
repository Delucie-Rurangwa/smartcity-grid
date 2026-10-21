import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class TrafficController {
    // BiPredicate Stop Logic: Returns true if car must STOP (RED light, unless emergency vehicle)
    public static BiPredicate<Vehicle, Intersection> getStopLogic() {
        return (vehicle, intersection) -> {
            boolean isRed = intersection.getCurrentLight().equals("RED");
            boolean isEmergency = vehicle.getType().equals("AMBULANCE") || vehicle.getType().equals("POLICE");
            return isRed && !isEmergency;
        };
    }

    // BiConsumer Action Executor
    public static BiConsumer<Vehicle, Intersection> getActionExecutor() {
        BiPredicate<Vehicle, Intersection> stopLogic = getStopLogic();
        return (vehicle, intersection) -> {
            boolean mustStop = stopLogic.test(vehicle, intersection);
            if (mustStop) {
                System.out.println("-> [" + intersection.getIntersectionId() + "] Vehicle " + vehicle.getId() +
                        " (" + vehicle.getType() + ") commanded to STOP.");
            } else {
                System.out.println("-> [" + intersection.getIntersectionId() + "] Vehicle " + vehicle.getId() +
                        " (" + vehicle.getType() + ") routing THROUGH.");
            }
        };
    }
}