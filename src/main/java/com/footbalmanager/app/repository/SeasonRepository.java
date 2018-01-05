package com.footbalmanager.app.repository;

import com.footbalmanager.app.domain.Season;
import org.springframework.data.repository.CrudRepository;

public interface SeasonRepository extends CrudRepository<Season, Long> {
}
