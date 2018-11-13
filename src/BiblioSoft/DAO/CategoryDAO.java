package BiblioSoft.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BiblioSoft.Table.BookTable;
import BiblioSoft.Table.CategoryTable;
import BiblioSoft.core.CharacterFilter;
import BiblioSoft.core.ConnDB;

/**
 * 
 * @author Hu Yuxi
 *
 *         Add Search List Delete
 */
public class CategoryDAO {
	private ConnDB conn = new ConnDB();

	/**
	 * Add
	 * 
	 * @param category1
	 * @return
	 */
	public int addCategory(String category1) {
		int flag = 0;
		String category = CharacterFilter.filterStr(category1);

		if (category != null) {
			String statussql = "select * from category where category_name = " + "\'" + category + "\'";
			ResultSet rs = conn.executeQuery(statussql);
			try {
				if (rs.next()) {
					// there is already exists this category
					flag = 2;
					return flag;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "insert into category values (" + "\'" + category + "\'" + ")";
			conn.executeQuery(sql);
			flag = 1;
			return flag;

		} else {
			// category is null
			flag = 3;
		}
		return flag;
	}

	/**
	 * Search
	 * 
	 * @param category
	 * @return
	 */
	public Collection searchCategory(String category) {
		CategoryTable categoryTable = null;
		Collection categoryColle = new ArrayList();
		String sql = null;
		if (category != "all" && category != "" && category != null) {
			sql = "select * from " + "category where category_name like " + "\'%" + category + "%\'";
		} else {

		}
		System.out.println("按照category查找的信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				categoryTable = new CategoryTable();
				categoryTable.setCategory(rs.getString("category_name"));
				categoryColle.add(categoryTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return categoryColle;
	}

	/**
	 * List All
	 * 
	 * @return
	 */
	public Collection listAllCategory() {
		CategoryTable categoryTable = null;
		Collection categoryColle = new ArrayList();
		String sql = "select * from category";
		System.out.println("按照category查找的信息:" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				categoryTable = new CategoryTable();
				categoryTable.setCategory(rs.getString("category_name"));
				categoryColle.add(categoryTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return categoryColle;
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	public int deleteCategory(String category) {
		String statussql = "select * from book where catagory like " + "\'%" + category + "%\'";
		ResultSet rs = conn.executeQuery(statussql);
		
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("Some books are below this category!");
					int can = 2;
					return can;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String sql = "delete from category where category_name = " + "\'" + category + "\'";
			int flag = conn.executeUpdate(sql);
			if (flag == 0) {
				flag = 3;
				return flag;
			}
			System.out.println("删除时的SQL：" + sql);
			conn.close();
			return flag;
		}
		return 1;
	}
}
