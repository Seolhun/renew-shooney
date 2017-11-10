package hi.cord.com.user.main.user.domain.privilege;

import java.io.Serializable;

public enum ProfilePrivilegeType implements Serializable {
    ALL("all"),
    WRITE("write"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete");

    private String privilege;

    ProfilePrivilegeType(String privilege) {
        this.privilege = privilege;
    }

    public String getUserPorfileTypeDetail(){
        return this.privilege;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return this.privilege;
    }

    public String getName() {
        return this.name();
    }
}
