package hi.cord.com.user.mongo.domain.ask;

public enum AskWebSite {
    //	TECHHIVE("http://www.techhive.com/article/"),
    OKKY("https://okky.kr/article/");

    private String address;

    private AskWebSite(final String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.address;
    }
}
