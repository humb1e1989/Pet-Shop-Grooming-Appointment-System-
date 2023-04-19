package com.cpt202_group10.team.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt202_group10.team.Models.Team;
import com.cpt202_group10.team.repositories.TeamRepo;

@Service
public class TeamService {
    @Autowired
    private TeamRepo teamRepo;
    
    public Team newTeam(Team team) {
        return teamRepo.save(team);
    }

    public List<Team> getTeamList() {
        return teamRepo.findAll();
    }
}
