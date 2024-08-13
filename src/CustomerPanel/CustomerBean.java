package CustomerPanel;

public class CustomerBean 
{

	String mobile;
	String name;
	String spapers;
	String email;
	String address;
	String dos;
	
	public CustomerBean() {}
	public CustomerBean(String mobile, String name, String spapers, String email, String address, String dos) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.spapers = spapers;
		this.email = email;
		this.address = address;
		this.dos = dos;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpapers() {
		return spapers;
	}
	public void setSpapers(String spapers) {
		this.spapers = spapers;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	
}
