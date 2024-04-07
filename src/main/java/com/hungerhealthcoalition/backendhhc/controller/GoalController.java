package com.hungerhealthcoalition.backendhhc.controller;


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


    @GetMapping("/{clientID}")
    public List<Goals> getClientsGoal(@PathVariable("clientID") String id) {
        Optional<Goals> optionalGoals = goalsRepository.findById(id);
        List<Goals> result = new ArrayList<>();
        optionalGoals.ifPresent(result::add);
        return result;
    }

    @PostMapping
    public Goals addGoal(@RequestBody Goals goals) {
        goalsRepository.save(goals);
        return goals;
    }

    @PutMapping("/{id}")
    public List<Goals> updateGoal(@PathVariable("id") String id, @RequestBody Goals goals)
    {
        Optional<Goals> goalsOptional = goalsRepository.findById(id);
        List<Goals> result = new ArrayList<>();

        if(goalsOptional.isPresent())
        {
            Goals goals1 = goalsOptional.get();
            goals1.setGoalDesc(goals.getGoalDesc());
            goals1.setClientID(goals.getClientID());
            goals1.setGoalName(goals.getGoalName());
            goals1.setStartValue(goals.getStartValue());
            goals1.setGoalValue(goals.getGoalValue());
            goals1.setCurrentValue(goals.getCurrentValue());
            goalsRepository.save(goals1);
            result.add(goals1);
        }
        return result;
    }

    @DeleteMapping("/{id}/{clientID}")
    @Transactional
    public ResponseEntity<Void> deletePairingByIdandClientID(@PathVariable("id") String id, @PathVariable("clientID") String clientID) {
        goalsRepository.deleteGoalsbyIdandClientID(id, clientID);
        return  ResponseEntity.noContent().build();
    }

}
