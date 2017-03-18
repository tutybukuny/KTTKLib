package Model;

public abstract class Human {

	private Name name;
	private Address address;
	private Account acc;
	private Birthday birthday;
	private int ID;

	public Human() {
		
	}

	public Name getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAcc() {
		return this.acc;
	}

	/**
	 * 
	 * @param acc
	 */
	public void setAcc(Account acc) {
		this.acc = acc;
	}

	public Birthday getBirthday() {
		return this.birthday;
	}

	/**
	 * 
	 * @param birthday
	 */
	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}

	public abstract String getDiscriminator();

	public int getID() {
		
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		
	}

}