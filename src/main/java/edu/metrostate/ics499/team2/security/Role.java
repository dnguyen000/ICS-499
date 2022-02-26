package edu.metrostate.ics499.team2.security;

public class Role {

    private String id;
    private String name;
    
    public Role(String name) {
    	this.name = name;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
