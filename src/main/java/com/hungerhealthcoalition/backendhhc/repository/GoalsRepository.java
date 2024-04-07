package com.hungerhealthcoalition.backendhhc.repository;


import com.hungerhealthcoalition.backendhhc.model.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, String> {
    public void deleteGoalsbyIdandClientID(String id, String eventID);

}
