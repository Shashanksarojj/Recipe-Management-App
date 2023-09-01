package dao;

import java.util.List;

import entity.Report;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ReportDAOImpl implements ReportDAO {

    // Adds a new Report to the database
    @Override
    public void addReport(Report report) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(report);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Updates an existing Report in the database
    @Override
    public void updateReport(Report report) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(report);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Deletes a Report from the database
    @Override
    public void deleteReport(Report report) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            Report managedReport = entityManager.find(Report.class, report.getId());
            if (managedReport != null) {
                entityManager.remove(managedReport);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Retrieves a Report by its ID from the database
    @Override
    public Report getReportById(int reportId) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            return entityManager.find(Report.class, reportId);
        } finally {
            entityManager.close();
        }
    }

    // Retrieves all Reports from the database
    @Override
    public List<Report> getAllReports() {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Report r", Report.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
