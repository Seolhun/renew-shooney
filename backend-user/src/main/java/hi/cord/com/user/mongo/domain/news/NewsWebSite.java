package hi.cord.com.user.mongo.domain.news;

public enum NewsWebSite {
    //	TECHHIVE("http://www.techhive.com/article/"),
    PCWORLD("http://www.pcworld.com/article/"),
    NETWORKWORLD("http://www.networkworld.com/article/"),
    COMPUTERWORLD("http://www.computerworld.com/article/"),
    INFOWORLD("http://www.infoworld.com/article/"),
    CIO("http://www.cio.com/article/");

    private String webAddress;

    private NewsWebSite(final String webAddress) {
        this.webAddress = webAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    @Override
    public String toString() {
        return this.webAddress;
    }
}
