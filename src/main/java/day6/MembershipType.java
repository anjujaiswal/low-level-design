package day6;

public enum MembershipType {
    REGULAR(3,14,10),
    PREMIUM(5,30,5);

    private final int maxBooks;
    private final int  loanDays;
    private final int feePerDay;

    MembershipType(int maxBooks, int loanDays, int feePerDay) {
        this.maxBooks = maxBooks;
        this.loanDays = loanDays;
        this.feePerDay = feePerDay;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public int getLoanDays() {
        return loanDays;
    }

    public int getFeePerDay() {
        return feePerDay;
    }

    public double calculateFee(long daysLate){
        if(daysLate < 0){
            throw new IllegalStateException("Days cannot be neegative");
        }
        return daysLate * feePerDay;
    }
}
