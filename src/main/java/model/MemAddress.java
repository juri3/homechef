package model;

import java.io.Serializable;
import java.util.Date;

public class MemAddress implements Serializable {

	private int addressNum;
	private int memNum;
	private String memName;
	private String address1;
	private String address2;
	private String address3;
	private String zipcode;
	private String recipient;
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public int getAddressNum() {
		return addressNum;
	}
	public void setAddressNum(int addressNum) {
		this.addressNum = addressNum;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return "MemAddress [addressNum=" + addressNum + ", memNum=" + memNum + ", memName=" + memName + ", address1="
				+ address1 + ", address2=" + address2 + ", address3=" + address3 + ", zipcode=" + zipcode
				+ ", recipient=" + recipient + "]";
	}
	
}