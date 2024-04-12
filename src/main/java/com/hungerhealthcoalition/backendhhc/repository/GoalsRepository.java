package com.hungerhealthcoalition.backendhhc.repository;


import com.hungerhealthcoalition.backendhhc.model.ClientInfo;
import com.hungerhealthcoalition.backendhhc.model.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, String> {
    public List<Goals> findGoalsByClientID(ClientInfo id);

    public Optional<Goals> findGoalsByClientIDAndGoalName(ClientInfo id, String name);


    //delete all goals from a certain client
    public void deleteGoalsByClientID(ClientInfo id);

    //delete specific goal
    public void deleteGoalsByClientIDAndGoalName(ClientInfo id, String name);


    public boolean existsGoalsByClientID(ClientInfo id);


}
