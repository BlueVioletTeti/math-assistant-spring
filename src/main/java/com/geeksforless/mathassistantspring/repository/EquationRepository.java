package com.geeksforless.mathassistantspring.repository;

import com.geeksforless.mathassistantspring.model.Equation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquationRepository extends JpaRepository<Equation, Long> {
    @Override
    @Query("SELECT e FROM Equation e LEFT JOIN FETCH e.roots r ")
    List<Equation> findAll();

    @Query("SELECT e FROM Equation e LEFT JOIN FETCH e.roots r "
            + "WHERE r.id = :rootId")
    List<Equation> findAllByRoot(@Param("rootId")Long rootId);

    @Query(value = "SELECT MAX(e.id) id, e.equation equation FROM equation e " +
            "LEFT JOIN equation_root er ON e.id = er.equation_id " +
            "GROUP BY equation HAVING COUNT(e.id) = 1", nativeQuery = true)
    List<Equation> findAllByRootUniqueness();
}
