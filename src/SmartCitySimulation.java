import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiConsumer;

public class SmartCitySimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== METRO AI SMART CITY TRAFFIC SIMULATION ===");

        // Mandatory user inputs
        System.out.print("Enter Intersection ID (e.g., Main St & 5th Ave): ");
        String intId = scanner.nextLine();

        System.out.print("Enter Current Light State (RED, YELLOW, GREEN): ");
        String light = scanner.nextLine();

        System.out.print("Enter Congestion Level (1 to 10): ");
        int congestion = scanner.nextInt();

        System.out.print("Enter City Speed Limit (km/h): ");
        int speedLimit = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Intersection intersection = new Intersection(intId, light, congestion);

        // Pre-configured simulation scenarios
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("V-101", 75, "CIVILIAN", true),
                new Vehicle("V-102", 85, "AMBULANCE", false),
                new Vehicle("V-103", 40, "PUBLIC_TRANSIT", true),
                new Vehicle("V-104", 60, "POLICE", false)
        );

        System.out.println("\n--- SIMULATION RESULTS ---");
        System.out.println("Intersection: " + intersection.getIntersectionId() +
                " | Light: " + intersection.getCurrentLight() +
                " | Congestion: " + intersection.getCongestionLevel());

        // Factory generated rules
        Predicate<Vehicle> speedRule = RuleFactory.createSpeedLimitRule(speedLimit);
        Function<Intersection, Integer> greenDurationFunc = RuleFactory.calculateGreenLightDuration();
        BiConsumer<Vehicle, Intersection> actionExecutor = TrafficController.getActionExecutor();

        // Custom Functional Interface Rule: "Sirens First"
        EmergencyOverride sirensFirst = (v, i) ->
                (v.getType().equals("AMBULANCE") || v.getType().equals("POLICE")) && i.getCongestionLevel() > 7;

        System.out.println("Calculated Green Light Duration: " + greenDurationFunc.apply(intersection) + " seconds\n");

        for (Vehicle v : vehicles) {
            System.out.println("Evaluating Vehicle: " + v.getId() + " [" + v.getType() + ", Speed: " + v.getSpeedKmh() + " km/h]");

            if (speedRule.test(v)) {
                System.out.println("   [WARNING] Speed Trap Triggered! Exceeding limit of " + speedLimit + " km/h.");
            }

            if (sirensFirst.shouldOverride(v, intersection)) {
                System.out.println("   [PRIORITY] 'Sirens First' Override Activated! Emergency right-of-way granted.");
            }

            actionExecutor.accept(v, intersection);
            System.out.println("--------------------------------------------------");
        }

        scanner.close();
    }
}