package lab10;

public class User {
	String name;
	String phone;
	String email;
	int age;
	
	User(String name, String phone, String email, int age) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + "\t" + age + "\t" + phone + "\t" + email;
	}
	
}
