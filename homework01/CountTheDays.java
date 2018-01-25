public class CountTheDays {
	public static void main(String args[]) {
		long[] params = new long[6];
		for (int i = 0; i < 6; i++) {
			params[i] = Long.parseLong(args[i]);
		}
		System.out.println("days " + CalendarStuff.daysBetween(params[0], params[1], params[2], params[3], params[4], params[5]));

	}
}
