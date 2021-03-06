package hi.cord.com.log.dynamo.keywords.domain;

public enum KeywordType {
    BLOG("blog"),
    NEWS("news"),
    QUESTION("question");

    private String type;

    private KeywordType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
