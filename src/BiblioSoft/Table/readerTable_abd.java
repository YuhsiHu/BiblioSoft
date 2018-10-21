package BiblioSoft.Table;
import java.io.*;
/**
 * 
 * @author Hu Yuxi
 *
 */
public class readerTable_abd implements Serializable {

	private int readerID;
	private String password;
	private String sex;
	private String readername;
	private String email;
	private String tele;
	private String MaxBorrow;
	private String LongestTime;													
	private Float Deposit;
	/**
	 * @return the readerID
	 */
	public Integer getReaderID() {
		return readerID;
	}
	/**
	 * @param integer the readerID to set
	 */
	public void setReaderID(Integer integer) {
		this.readerID = integer;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the readername
	 */
	public String getReadername() {
		return readername;
	}
	/**
	 * @param readername the readername to set
	 */
	public void setReadername(String readername) {
		this.readername = readername;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tele
	 */
	public String getTele() {
		return tele;
	}
	/**
	 * @param tele the tele to set
	 */
	public void setTele(String tele) {
		this.tele = tele;
	}
	/**
	 * @return the maxBorrow
	 */
	public String getMaxBorrow() {
		return MaxBorrow;
	}
	/**
	 * @param maxBorrow the maxBorrow to set
	 */
	public void setMaxBorrow(String maxBorrow) {
		MaxBorrow = maxBorrow;
	}
	/**
	 * @return the longestTime
	 */
	public String getLongestTime() {
		return LongestTime;
	}
	/**
	 * @param longestTime the longestTime to set
	 */
	public void setLongestTime(String longestTime) {
		LongestTime = longestTime;
	}
	/**
	 * @return the deposit
	 */
	public Float getDeposit() {
		return Deposit;
	}
	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(Float deposit) {
		Deposit = deposit;
	}
	
	
}
