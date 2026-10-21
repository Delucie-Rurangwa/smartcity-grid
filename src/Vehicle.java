public class Vehicle {
    private String id;
    private int speedKmh;
    private String type;
    private boolean isAutonomous;

    public Vehicle(String id, int speedKmh, String type, boolean isAutonomous) {
        this.id = id;
        this.speedKmh = speedKmh;
        setType(type);
        this.isAutonomous = isAutonomous;
    }

    public String getId() { return id; }
    public int getSpeedKmh() { return speedKmh; }
    public String getType() { return type; }
    public boolean isAutonomous() { return isAutonomous; }

    public void setType(String type) {
        String upperType = type != null ? type.trim().toUpperCase() : "";
        if (upperType.equals("CIVILIAN") || upperType.equals("AMBULANCE") ||
                upperType.equals("POLICE") || upperType.equals("PUBLIC_TRANSIT")) {
            this.type = upperType;
        } else {
            throw new IllegalArgumentException("Invalid vehicle type: '" + type +
                    "'. Allowed types: CIVILIAN, AMBULANCE, POLICE, PUBLIC_TRANSIT.");
        }
    }
}