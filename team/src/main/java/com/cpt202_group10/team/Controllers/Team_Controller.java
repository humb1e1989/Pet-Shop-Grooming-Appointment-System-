package com.cpt202_group10.team.Controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cpt202_group10.team.Models.Team;


// Spring Anotation
@RestController
@RequestMapping("/team")
public class Team_Controller
{
    private List<Team> teams = new ArrayList<Team>();

    @GetMapping("/list")
    public List<Team> getList()
    {
        return teams;
    }

    @PostMapping("/add")
    public void addTeam(@RequestBody Team team)
    {
        teams.add(team);
    }

    
}
