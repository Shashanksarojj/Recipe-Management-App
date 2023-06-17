package entity;

public class LoggedInUserId {
	static public long loggedInUserId;

	public static long getLoggedInUserId() {
		return loggedInUserId;
	}

	public static void setLoggedInUserId(long loggedInUserId) {
		LoggedInUserId.loggedInUserId = loggedInUserId;
	}
	
	
	
}
