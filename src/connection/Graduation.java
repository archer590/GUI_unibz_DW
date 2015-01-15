package connection;

public class Graduation {
	
	int key_one;
	int key_two;
	String name;
	String name_one;
	
	public Graduation(int key_one, int key_two, String name, String name_one) {
		super();
		this.key_one = key_one;
		this.key_two = key_two;
		this.name = name;
		this.name_one = name_one;
	}
	
	public int getKey_one() {
		return key_one;
	}
	
	public void setKey_one(int key_one) {
		this.key_one = key_one;
	}
	
	public int getKey_two() {
		return key_two;
	}
	
	public void setKey_two(int key_two) {
		this.key_two = key_two;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName_one() {
		return name_one;
	}
	
	public void setName_one(String name_one) {
		this.name_one = name_one;
	}
}
