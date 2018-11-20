package user;

public class UserMain {
	public static void main(String[] args){
		User u1 = new User("JJalph","James", "Smiths", "07720655555",
				"10 Sesh Pad", "","SA2 KKL","Swan",0,
				"/path/path/path");
		u1.addAccountBalance(100);

		System.out.print(u1.toString());
		u1.subtractAccountBalance(150);
		System.out.print(u1.toString());
	}
}
