package application.model;

public class Facility {

    private int facilityId;
    private String facilityName;

    public Facility(int facilityId, String facilityName) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public String toString() {
        return "Facility{" + " Facility ID: " + facilityId +
                ", Name of Facility: " + facilityName +
                '}';
    }
}
