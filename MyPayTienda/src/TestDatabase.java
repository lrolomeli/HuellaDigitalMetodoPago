import com.mypay.model.Database;

public abstract class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test");
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.disconnect();
	}

}
