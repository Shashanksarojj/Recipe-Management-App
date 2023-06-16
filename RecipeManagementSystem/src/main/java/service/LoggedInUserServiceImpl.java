package service;

import dao.LoggedInUserDAO;

public class LoggedInUserServiceImpl implements LoggedInUserService {
    private LoggedInUserDAO loggedInUserDAO;

    public LoggedInUserServiceImpl(LoggedInUserDAO loggedInUserDAO) {
        this.loggedInUserDAO = loggedInUserDAO;
    }

    @Override
    public void setLoggedInUserId(long userId) {
        loggedInUserDAO.setLoggedInUserId(userId);
    }

    @Override
    public long getLoggedInUserId() {
        return loggedInUserDAO.getLoggedInUserId();
    }

    @Override
    public void clearLoggedInUserId() {
        loggedInUserDAO.clearLoggedInUserId();
    }
}
