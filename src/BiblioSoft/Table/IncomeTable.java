package BiblioSoft.Table;

import java.util.Date;

public class IncomeTable {
    private int income_id;
    private Date income_date;
    private double income_value;
    private String income_from;

	public IncomeTable() {
	}

	public IncomeTable(int income_id, Date income_date, double income_value, String income_from) {
		this.income_id = income_id;
		this.income_date = income_date;
		this.income_value = income_value;
		this.income_from = income_from;
	}

	public int getIncome_id() {
		return income_id;
	}
	
	public void setIncome_id(int income_id) {
		this.income_id = income_id;
	}
	
	public Date getIncome_date() {
		return income_date;
	}
	
	public void setIncome_date(Date income_date) {
		this.income_date = income_date;
	}
	
	public double getIncome_value() {
		return income_value;
	}
	
	public void setIncome_value(double income_value) {
		this.income_value = income_value;
	}
	
	public String getIncome_from() {
		return income_from;
	}
	
	public void setIncome_from(String income_from) {
		this.income_from = income_from;
	}
}
