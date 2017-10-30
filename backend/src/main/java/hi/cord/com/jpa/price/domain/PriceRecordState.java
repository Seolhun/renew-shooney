package hi.cord.com.jpa.price.domain;

public enum PriceRecordState {

    ACTIVE("active"),
    WAITING("waiting"),
    LOCKED("locked"),
    REFUND("refund");

    private String state;

    PriceRecordState(final String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.state;
    }


}
