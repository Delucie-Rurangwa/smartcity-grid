public class Intersection {
    private String intersectionId;
    private String currentLight;
    private int congestionLevel;

    public Intersection(String intersectionId, String currentLight, int congestionLevel) {
        this.intersectionId = intersectionId;
        setCurrentLight(currentLight);
        setCongestionLevel(congestionLevel);
    }

    public String getIntersectionId() { return intersectionId; }
    public String getCurrentLight() { return currentLight; }
    public int getCongestionLevel() { return congestionLevel; }

    public void setCurrentLight(String currentLight) {
        String upperLight = currentLight != null ? currentLight.trim().toUpperCase() : "";
        if (upperLight.equals("RED") || upperLight.equals("YELLOW") || upperLight.equals("GREEN")) {
            this.currentLight = upperLight;
        } else {
            throw new IllegalArgumentException("Invalid traffic light: '" + currentLight +
                    "'. Allowed lights: RED, YELLOW, GREEN.");
        }
    }

    public void setCongestionLevel(int congestionLevel) {
        if (congestionLevel >= 1 && congestionLevel <= 10) {
            this.congestionLevel = congestionLevel;
        } else {
            throw new IllegalArgumentException("Congestion level must be between 1 and 10. Provided: " + congestionLevel);
        }
    }
}