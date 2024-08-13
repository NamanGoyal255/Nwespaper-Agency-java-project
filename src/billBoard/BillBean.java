package billBoard;

public class BillBean 
{

	String mobile;
	String dos;
	String doe;
	float bill;
	String billstatus;
	
	public BillBean() {}
	public BillBean(String mobile, String dos, String doe, float bill, String billstatus) {
		super();
		this.mobile = mobile;
		this.dos = dos;
		this.doe = doe;
		this.bill = bill;
		this.billstatus = billstatus;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
	public String getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}
	
}
