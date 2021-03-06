package com.project.orange.repository.report;

import com.project.orange.entity.report.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
