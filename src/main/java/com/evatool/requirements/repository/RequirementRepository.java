
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, UUID> {

    Collection<Requirement> findByRequirementsAnalysis(RequirementsAnalysis requirementsAnalysis);
}

