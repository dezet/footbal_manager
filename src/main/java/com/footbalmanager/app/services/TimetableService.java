package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

@Service("timetableService")
@Transactional
public class TimetableService {

    @Autowired
    private MatchService matchService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private TeamService teamService;

    private HashMap<Team, List<LocalDateTime>> teamSchedules = new HashMap<>();

    private Optional<LocalDateTime> findDateForTeams(Team home, Team away, HashMap<Team, List<LocalDateTime>> teamSchedules, List<LocalDateTime> weekends) {
        for (LocalDateTime date : weekends) {
            List<LocalDateTime> homeSchedule = teamSchedules.get(home);
            List<LocalDateTime> awaySchedule = teamSchedules.get(away);
            if (homeSchedule.stream().noneMatch(p -> p == date) && awaySchedule.stream().noneMatch(p -> p == date)) {
                return Optional.of(date);
            }
        }
        return Optional.empty();
    }

    private void prepareTeamSchedule(List<Team> teams) {
        for (Team t : teams) {
            teamSchedules.put(t, new ArrayList<>());
        }
    }

    List<Match> prepareMatches(List<Team> teams, Season season) throws Exception {
        prepareTeamSchedule(teams);
        List<Match> matches = new ArrayList<>();
        LinkedList<LocalDateTime> applicableWeekends = getSeasonWeekends(season);
        for (Team home : teams) {
            for (Team away : teams) {
                if (!home.getId().equals(away.getId())) {
                    Optional<LocalDateTime> matchDate = findDateForTeams(home, away, teamSchedules, applicableWeekends);
                    if (matchDate.isPresent()) {
                        matches.add(new Match(home, away, matchDate.get(),
                                0, null, false, home.getLeague()));
                        List<LocalDateTime> teamTimetable = teamSchedules.get(home);
                        teamTimetable.add(matchDate.get());
                    } else {
                        throw new Exception("chujchuj");
                    }
                }
            }
        }
        return matches;
    }

    private LinkedList<LocalDateTime> getSeasonWeekends(Season season) {
        return getWeekendsListInRange(
                LocalDateTime.of(Integer.valueOf(season.getYear()), 9, 1, 17, 0),
                LocalDateTime.of(Integer.valueOf(season.getYear()) + 1, 6, 1, 17, 0)
        );
    }

    private LinkedList<LocalDateTime> getWeekendsListInRange(LocalDateTime minDate, LocalDateTime maxDate) {
        LocalDateTime start = minDate;
        LinkedList<LocalDateTime> weekends = new LinkedList<>();
        while (start.isBefore(maxDate)) {
            if (start.getDayOfWeek() == DayOfWeek.SATURDAY || start.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekends.add(start);
            }
            start = start.plusDays(1);
        }
        return weekends;
    }

    public List<Match> generate(Season season) {
        List<Team> teams = teamService.findTeamsBySeason(season);
        try {
            return (List<Match>) matchService.save(prepareMatches(teams, season));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
