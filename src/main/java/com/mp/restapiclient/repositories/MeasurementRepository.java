package com.mp.restapiclient.repositories;

import com.mp.restapiclient.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findMeasurementsByRainingIsTrue();

    int countAllByRainingIsTrue();
}
