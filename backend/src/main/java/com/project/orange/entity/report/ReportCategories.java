package com.project.orange.entity.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
public class ReportCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_category_id")
    private Long reportCategoryId;
    @Column(name = "report_category_name")
    private String reportCategoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "reportId", fetch = FetchType.LAZY) //하나의 reportCategory는 여러 개의 report를 가진다.
    List<Reports> reportsList = new ArrayList<>();
}
