package com.cpt202_group10.team.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpt202_group10.team.Models.Team;

public interface TeamRepo extends JpaRepository<Team, Integer>{
    
}
