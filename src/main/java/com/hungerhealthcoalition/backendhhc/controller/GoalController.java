package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.ClientInfo;
import com.hungerhealthcoalition.backendhhc.model.Goals;
import com.hungerhealthcoalition.backendhhc.repository.GoalsRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Goals")
public class GoalController {
    private GoalsRepository goalsRepository;

    public GoalController(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

    @GetMapping
    public List<Goals> getAllGoals() {
        return goalsRepository.findAll();
    }


    @GetMapping("/{id}")
    public List<Goals> getClientGoalsbyID(@PathVariable("id") int id) {
        return goalsRepository.findGoalsByClientInfoClientID(id);
    }

    @GetMapping("/{id}/{goalName}")
    public Optional<Goals> getClientGoalByIDandName(@PathVariable("id") int id, @PathVariable("goalName") String goalName) {
        return goalsRepository.findGoalsByClientInfoClientIDAndGoalName(id, goalName);
    }

    @PostMapping
    public Goals addGoal(@RequestBody Goals goals) {
        Optional<Goals> exisitingGoal = goalsRepository.findGoalsByClientInfoClientIDAndGoalName(
                goals.getClientInfo().getClientID(), goals.getGoalName()
        );
        if (exisitingGoal.isPresent()) {
            throw new RuntimeException("Goal with that client Id" + goals.getClientInfo().getClientID() + " and goalName" + goals.getGoalName() + "already exists");
        }
        goalsRepository.save(goals);
        return goals;
    }

    @PutMapping("/{id}/{goalName}")
    public List<Goals> updateGoal(@PathVariable("id") int id, @RequestBody Goals goals, @PathVariable("goalName") String goalName) {
        if (!goalsRepository.existsGoalsByClientInfoClientID(id)) {
            throw new RuntimeException("Client with ID " + id + " not found");
        }
        Optional<Goals> exisitingGoalOptional = goalsRepository.findGoalsByClientInfoClientIDAndGoalName(id, goalName);
        List<Goals> result = new ArrayList<>();
        if (exisitingGoalOptional.isPresent()) {
            Goals existingGoal = exisitingGoalOptional.get();
            existingGoal.setGoalName(goals.getGoalName());
            existingGoal.setGoalValue(goals.getGoalValue());
            existingGoal.setStartValue(goals.getStartValue());
            existingGoal.setCurrentValue(goals.getCurrentValue());
            existingGoal.setGoalDesc(goals.getGoalDesc());
            goalsRepository.save(existingGoal);
            result.add(existingGoal);
        }
        return result;

    }

    @DeleteMapping("/{id}/{goalName}")
    @Transactional
    public ResponseEntity<Goals> deleteGoalbyClientIDAndGoalID(@PathVariable("id") int clientId, @PathVariable("goalName") String goalName) {
        goalsRepository.deleteGoalsByClientInfoClientIDAndGoalName(clientId, goalName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/")
    @Transactional
    public ResponseEntity<Goals> deleteAllGoalByClientID(@PathVariable("id") int clientId) {
        goalsRepository.deleteGoalsByClientInfoClientID(clientId);
        return ResponseEntity.noContent().build();
    }

}
