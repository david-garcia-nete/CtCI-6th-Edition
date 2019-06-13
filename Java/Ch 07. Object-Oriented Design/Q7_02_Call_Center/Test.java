package Q7_02_Call_Center;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CallHandler ch = new CallHandler();
		Caller david = new Caller(0, "David");
                ch.dispatchCall(david); 
	}

}
