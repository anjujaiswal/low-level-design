package day7;

public class Spot {
    private final String spotId;
    private final SizeType sizeType;
    private Status status;
    private final int spotNo;

    public Spot(String spotId, SizeType sizeType, int spotNo) {
        this.spotId = spotId;
        this.sizeType = sizeType;
        this.status = Status.OPEN;
        this.spotNo = spotNo;

    }

    public String getSpotId() {
        return spotId;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public boolean isFree() {
        return status == Status.OPEN;
    }

    public int getSpotNo() {
        return spotNo;
    }
    public void setStatus(Status status){
        if(this.status == status){
            throw new IllegalStateException("Spot is already in status "+ status);
        }
        this.status = status;
    }
}
