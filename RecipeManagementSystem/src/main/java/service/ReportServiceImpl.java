package service;

import java.util.List;

import dao.ReportDAO;
import entity.Report;
import exception.ServiceException;

public class ReportServiceImpl implements ReportService {
    private ReportDAO reportDAO;

    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public void addReport(Report report) throws ServiceException {
        try {
            reportDAO.addReport(report);
        } catch (Exception e) {
            throw new ServiceException("Failed to add report");
        }
    }

    @Override
    public void updateReport(Report report) throws ServiceException {
        try {
            reportDAO.updateReport(report);
        } catch (Exception e) {
            throw new ServiceException("Failed to update report");
        }
    }

    @Override
    public void deleteReport(Report report) throws ServiceException {
        try {
            reportDAO.deleteReport(report);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete report");
        }
    }

    @Override
    public Report getReportById(int reportId) throws ServiceException {
        try {
            return reportDAO.getReportById(reportId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get report by ID");
        }
    }

    @Override
    public List<Report> getAllReports() throws ServiceException {
        try {
            return reportDAO.getAllReports();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all reports", e);
        }
    }
}