package service;

import java.util.List;

import entity.Report;
import exception.ServiceException;

public interface ReportService {
	void addReport(Report report) throws ServiceException;
    void updateReport(Report report) throws ServiceException;
    void deleteReport(Report report) throws ServiceException;
    Report getReportById(int reportId) throws ServiceException;
    List<Report> getAllReports() throws ServiceException;
}
