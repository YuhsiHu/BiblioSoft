package BiblioSoft.Table;

public class AdminTable {
	private int Admin_id = 0;
    private String password = "";
    private String Admin_name = "";
    
    public AdminTable(){
    	
    }

	public int getAdmin_id() {
		return Admin_id;
	}

	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdmin_name() {
		return Admin_name;
	}

	public void setAdmin_name(String admin_name) {
		Admin_name = admin_name;
	}
    
}
