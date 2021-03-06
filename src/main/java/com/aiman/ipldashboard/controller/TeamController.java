package com.aiman.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aiman.ipldashboard.model.Match;
import com.aiman.ipldashboard.model.Team;
import com.aiman.ipldashboard.repositiry.MatchRepository;
import com.aiman.ipldashboard.repositiry.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

	private TeamRepository teamRepository;
	private MatchRepository matchRepository;

	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}
	
	@GetMapping("/team")
	public Iterable<Team> getAllTeam(){
		return teamRepository.findAll();
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {

		Team team = teamRepository.findByTeamName(teamName);

		team.setMatches(matchRepository.findLatestMatch(teamName, 4));

		return team;

	}

	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {

		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);

		return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);

	}

}
