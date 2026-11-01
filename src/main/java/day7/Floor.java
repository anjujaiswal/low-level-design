package day7;

import java.util.*;

public class Floor {
    private final String floorId;
    private final SortedSet<Spot> spots ;
    private final int floorNo;

    public Floor(String floorId, List<Spot> spots, int floorNo) {
        if (floorNo < 0) throw new IllegalArgumentException("Floor number can't be negative");
        if (spots == null || spots.isEmpty()) throw new IllegalArgumentException("A floor needs at least one spot");
        this.floorId = floorId;
        this.floorNo = floorNo;
        this.spots = new TreeSet<>(Comparator.comparingInt(Spot::getSpotNo));
        for (Spot spot : spots) {
            if (!this.spots.add(spot)) {
                throw new IllegalArgumentException("Duplicate spot " + spot.getSpotNo() + " on floor " + floorNo);
            }
        }
    }

    public String getFloorId() {
        return floorId;
    }

    public SortedSet<Spot> getSpots() {
        return spots;
    }

    public int getFloorNo() {
        return floorNo;
    }
}
