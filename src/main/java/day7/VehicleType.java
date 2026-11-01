package day7;

public enum VehicleType {
    CAR(20),
    BIKE(10),
    TRUCK(50);

    private final int fee;
    VehicleType(int fee){
        this.fee = fee;
    }



    public double calculateFee(long hours){
        return hours*fee;
    }


    public boolean canFitIn(SizeType sizeType){
        if (sizeType == null) throw new IllegalArgumentException("Size is required");
        return switch (this) {
            case BIKE  -> true;                                          // any spot
            case CAR   -> sizeType == SizeType.MEDIUM || sizeType == SizeType.LARGE;
            case TRUCK -> sizeType == SizeType.LARGE;
        };
    }
}
