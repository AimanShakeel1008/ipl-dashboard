package com.aiman.ipldashboard.repositiry;

import org.springframework.data.repository.CrudRepository;

import com.aiman.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findByTeamName(String teamName);

}
