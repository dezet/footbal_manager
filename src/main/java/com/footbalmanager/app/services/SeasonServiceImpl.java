package com.footbalmanager.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.dto.season.PatchSeasonRequestDto;
import com.footbalmanager.app.dto.season.PostSeasonRequestDto;
import com.footbalmanager.app.repository.SeasonRepository;

@Service("seasonService")
@Transactional
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
	public void update(Long id, PatchSeasonRequestDto dto) {
        //TODO: zostawiam na później
    }

    @Override
    public Season save(Season entity) {
        return seasonRepository.save(entity);
    }

    @Override
    public Season findOne(Long id) {
        return seasonRepository.findOne(id);
    }

    @Override
    public Iterable<Season> findAll() {
        return seasonRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        seasonRepository.delete(id);
    }

    @Override
    public void delete(Season entity) {
        seasonRepository.delete(entity);
    }

    @Override
    public void save(PostSeasonRequestDto dto) {
        Season newSeason = new Season(dto.getName(), dto.getYear());
		seasonRepository.save(newSeason);
        //mozna ewentualnie zwrócić season.
    }

	@Override
	public void close(Long id) {
		Season season = seasonRepository.findOne(id);
		season.setOpen(false);
		seasonRepository.save(season);
	}

	@Override
    public Iterable<Season> save(Iterable<Season> entities) {
        return seasonRepository.save(entities);
    }
}
