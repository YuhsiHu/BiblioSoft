package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import BiblioSoft.core.ConnDB;

/**
 * 
 * @author Hu Yuxi
 *
 */
public class DefaultValue {

	private ConnDB conn = new ConnDB();
	int longesttime;
	double fine;
	double deposit;
	double reserve_time;

	/**
	 * 无参构造
	 */
	public DefaultValue() {

	}

	/**
	 * add default value at the beginning of this system, we can only use it once!
	 * 
	 * @param longgesttime
	 * @param fine
	 * @param deposit
	 * @param reserve_time
	 */
	public void addDefautValue(int longgesttime, double fine, double deposit, double reserve_time) {

		String sql = "insert into default_value values (" + longgesttime  + "," + fine + "," + deposit
				+ "," + reserve_time + ")";
		conn.executeUpdate(sql);
	}

	/**
	 * @return the longesttime
	 */
	public int getLongesttime() {
		String sql = "select longesttime from default_value";
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				longesttime = rs.getInt("longesttime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return longesttime;
	}

	/**
	 * @param longesttime
	 *            the longesttime to set
	 */
	public void setLongesttime(int longesttime) {
		String sql = "update default_value set longesttime = " + longesttime;
		conn.executeQuery(sql);
		this.longesttime = longesttime;
	}

	/**
	 * @return the fine
	 */
	public double getFine() {
		String sql = "select fine from default_value";
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				fine = rs.getDouble("fine");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fine;
	}

	/**
	 * @param fine
	 *            the fine to set
	 */
	public void setFine(double fine) {
		String sql = "update default_value set fine = " + fine;
		conn.executeQuery(sql);
		this.fine = fine;
	}

	/**
	 * @return the deposit
	 */
	public double getDeposit() {
		String sql = "select deposit from default_value";
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				deposit = rs.getDouble("deposit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deposit;
	}

	/**
	 * @param deposit
	 *            the deposit to set
	 */
	public void setDeposit(double deposit) {
		String sql = "update default_value set deposit = " + deposit;
		conn.executeQuery(sql);
		this.deposit = deposit;
	}

	/**
	 * @return the reserve_time
	 */
	public double getReserve_time() {
		String sql = "select reserve_time from default_value";
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				reserve_time = rs.getDouble("reserve_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reserve_time;
	}

	/**
	 * @param reserve_time
	 *            the reserve_time to set
	 */
	public void setReserve_time(double reserve_time) {
		String sql = "update default_value set reserve_time= " + reserve_time;
		conn.executeQuery(sql);
		this.reserve_time = reserve_time;
	}

}
