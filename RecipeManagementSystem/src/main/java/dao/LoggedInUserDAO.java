package dao;

public interface LoggedInUserDAO {
    void setLoggedInUserId(long userId);
    long getLoggedInUserId();
    void clearLoggedInUserId();
}
