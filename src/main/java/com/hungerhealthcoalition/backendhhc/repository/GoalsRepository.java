package com.hungerhealthcoalition.backendhhc.repository;


import com.hungerhealthcoalition.backendhhc.model.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, String> {
//    public void deleteGoalsbyIdandClientInfoClientID(int id, int eventID);

}
