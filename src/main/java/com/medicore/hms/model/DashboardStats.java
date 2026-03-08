package com.medicore.hms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStats {
    private long totalPatients;
    private long appointmentsToday;
    private long totalWards;
    private String revenueMtd;
}
