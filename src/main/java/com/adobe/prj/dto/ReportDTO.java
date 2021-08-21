package com.adobe.prj.dto;

import java.util.Date;

public class ReportDTO {
	
	private String firstName;
	private Date orderDate;
	private double total;
	public ReportDTO() {
	}
	public ReportDTO(String firstName, Date orderDate, double total) {
		this.firstName = firstName;
		this.orderDate = orderDate;
		this.total = total;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
