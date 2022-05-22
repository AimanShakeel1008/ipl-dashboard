package com.aiman.ipldashboard.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aiman.ipldashboard.model.Team;
import com.aiman.ipldashboard.repositiry.MatchRepository;
import com.aiman.ipldashboard.repositiry.TeamRepository;

@RestController
public class TeamController {
	
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	
	
	public TeamController(TeamRepository teamRepository,MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}



	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		
		Team team= teamRepository.findByTeamName(teamName);
		
		team.setMatches(matchRepository.findLatestMatch(teamName,4));
		
		return team;
		
	}

}
