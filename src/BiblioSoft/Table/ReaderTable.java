/**
 * 
 */
package BiblioSoft.Table;

import java.io.Serializable;

/**
 * @author ∫Œ¡˙œË
 *
 */
public class ReaderTable implements Serializable {
	private int reader_id;
	private String password;
	private String sex;
	private String reader_name;
	private String email;
	private String tele;
	private int maxborrow;
	private int longesttime;
	private double deposit;
	
	/*
	 * @return reader_id
	 */
	public int getID() {
		return reader_id;
	}
	
	/*
	 * @param set the int reader_id
	 */
	public void setID(int i) {
		reader_id = i;
	}
	/*
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/*
	 * @param set the String password
	 */
	public void setPassword(String str) {
		password = str;
	}
	
	/*
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}
	
	/*
	 * @param set the String sex
	 */
	public void setSex(String str) {
		sex = str;
	}
	
	/*
	 * @return reader_name
	 */
	public String getReaderName() {
		return reader_name;
	}
	
	/*
	 * @param set the String reader_name
	 */
	public void setReaderName(String str) {
		reader_name = str;
	}
	
	/*
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/*
	 * @param set the String email
	 */
	public void setEmail(String str) {
		email = str;
	}
	
	/*
	 * @return tele
	 */
	public String getTele() {
		return tele;
	}
	
	/*
	 * @param set the String tele
	 */
	public void setTele(String str) {
		tele = str;
	}
	
	/*
	 * @return maxborrow
	 */
	public int getMaxborrow() {
		return maxborrow;
	}
	
	/*
	 * @param set the double maxborrow
	 */
	public void setMaxborrow(int d) {
		maxborrow = d;
	}
	
	/*
	 * @return longesttime
	 */
	public int getLongesttime() {
		return longesttime;
	}
	
	/*
	 * @param set the int longesttime
	 */
	public void setLongesttime(int i) {
		longesttime = i;
	}
	
	/*
	 * @return deposit
	 */
	public double getDeposit() {
		return deposit;
	}
	
	/*
	 * @param set the double deposit
	 */
	public void setDeposit(double d) {
		deposit = d;
	}
}
