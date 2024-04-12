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
    public List<Goals> getClientGoalsbyID(@PathVariable("id") ClientInfo id) {
        return goalsRepository.findGoalsByClientID(id);
    }

    @GetMapping("/{id}/{goalName}")
    public Optional<Goals> getClientGoalByIDandName(@PathVariable("id") ClientInfo id, @PathVariable("goalName") String goalName) {
        return goalsRepository.findGoalsByClientIDAndGoalName(id, goalName);
    }

    @PostMapping
    public Goals addGoal(@RequestBody Goals goals) {
        goalsRepository.save(goals);
        return goals;
    }

    @PutMapping("/{id}/{goalName}")
    public List<Goals> updateGoal(@PathVariable("id") ClientInfo id, @RequestBody Goals goals, @PathVariable("goalName") String goalName) {
        if (!goalsRepository.existsGoalsByClientID(id)) {
            throw new RuntimeException("Client with ID " + id + " not found");
        }
        Optional<Goals> exisitingGoalOptional = goalsRepository.findGoalsByClientIDAndGoalName(id, goalName);
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

    @DeleteMapping("/{id}/{goalId}")
    @Transactional
    public ResponseEntity<Goals> deleteGoalbyClientIDAndGoalID(@PathVariable("id") ClientInfo clientId, @PathVariable("goalId") String goalName) {
        goalsRepository.deleteGoalsByClientIDAndGoalName(clientId, goalName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/")
    @Transactional
    public ResponseEntity<Goals> deleteAllGoalByClientID(@PathVariable("id") ClientInfo clientId) {
        goalsRepository.deleteGoalsByClientID(clientId);
        return ResponseEntity.noContent().build();
    }

}
