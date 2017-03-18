package Model;

public class Human {

	private Name name;
	private Address address;
	private Account acc;
	private Birthday birthday;
	private int id;

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

	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

}