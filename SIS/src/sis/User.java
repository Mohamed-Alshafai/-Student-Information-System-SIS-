package sis;

public class User {
	private String name;
	private String id;
	private String username;
	private String password;
	private String term;
	public User(String name, String id, String username, String password, String term) {
		this.name = name;
		this.id = id;
		this.username = username;
		this.password = password;
		this.term = term;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTerm() {
		return this.term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
}
