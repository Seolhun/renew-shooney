package hi.cord.com.jpa.user.domain;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	GUEST("guest"),
	PLAYER("player"),
	CAPTAIN("captain"),
	ADMIN("admin"),
	MASTER("master");
	
	private String type;
	
	private UserProfileType(String type){
		this.type = type;
	}
	
	public String getUserProfileType(){
		return type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}
}
