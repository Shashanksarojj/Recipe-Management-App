package dao;

public class LoggedInUserDAOImpl implements LoggedInUserDAO {

    private static long loggedInUserId;

    @Override
    public void setLoggedInUserId(long userId) {
        loggedInUserId = userId;
    }

    @Override
    public long getLoggedInUserId() {
        return loggedInUserId;
    }

    @Override
    public void clearLoggedInUserId() {
        loggedInUserId = 0;
    }

}
