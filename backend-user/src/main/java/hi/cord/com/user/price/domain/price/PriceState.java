package hi.cord.com.user.price.domain.price;

public enum PriceState {

    ACTIVE("active"),
    WAITING("waiting"),
    LOCKED("locked"),
    REFUND("refund");

    private String state;

    PriceState(final String state) {
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
