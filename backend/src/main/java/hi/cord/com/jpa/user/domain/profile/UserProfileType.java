package hi.cord.com.jpa.user.domain.profile;

import java.io.Serializable;

/**
 * The enum User profile type.
 *
 * @Warning Never change order. I'm used ordinal. I will fix it.
 */
public enum UserProfileType implements Serializable {
    ADMIN("admin"),
    GUEST("guest"),
    USER("user"),
    CONTENT("content"),
    COMMENT("comment"),
    KEYWORDS("keywords"),
    NLP("nlp");

    private String type;

    UserProfileType(String type) {
        this.type = type;
    }

    public String getUserProfileType() {
        return type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.type;
    }
}
