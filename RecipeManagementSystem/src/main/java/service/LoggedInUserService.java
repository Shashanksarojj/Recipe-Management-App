package service;

public interface LoggedInUserService {
	  void setLoggedInUserId(long userId);
	    long getLoggedInUserId();
	    void clearLoggedInUserId();
	    
}
