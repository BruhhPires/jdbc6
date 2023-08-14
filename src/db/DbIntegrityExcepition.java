package db;

public class DbIntegrityExcepition extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DbIntegrityExcepition(String msg) {
		super(msg);
	}

}
