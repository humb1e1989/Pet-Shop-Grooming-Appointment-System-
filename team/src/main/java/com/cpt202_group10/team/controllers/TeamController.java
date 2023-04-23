package com.cpt202_group10.team.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpt202_group10.team.Models.Team;
import com.cpt202_group10.team.services.TeamService;

// Spring Anotation
@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("teamList", teamService.getTeamList());
        return "allTeams";
    }

    @GetMapping("/add")
    public String addTeam(Model model) {
        model.addAttribute("team", new Team());

        return "addTeam";
    }

    @PostMapping("/add")
    public String confirmNewTeam(@ModelAttribute("team") Team team) {
        teamService.newTeam(team);
        
        return "home";
    }

}
