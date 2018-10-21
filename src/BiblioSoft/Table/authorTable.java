package BiblioSoft.Table;

import java.io.Serializable;

public class authorTable implements Serializable{

	  private int authorID;
	  private String authorName;
	  private String authorDescription;
	/**
	 * @return the authorID
	 */
	public int getAuthorID() {
		return authorID;
	}
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the authorDescription
	 */
	public String getAuthorDescription() {
		return authorDescription;
	}
	/**
	 * @param authorDescription the authorDescription to set
	 */
	public void setAuthorDescription(String authorDescription) {
		this.authorDescription = authorDescription;
	}
	  
	  
	
}
