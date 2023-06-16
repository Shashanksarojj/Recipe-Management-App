package dao;

import java.util.List;

import entity.Report;

public interface ReportDAO {

    void addReport(Report report);

    void updateReport(Report report);

    void deleteReport(Report report);

    Report getReportById(int reportId);

    List<Report> getAllReports();
}

