package com.palagen.organizer.reminders.dao;

import com.palagen.organizer.reminders.model.Rem;

import java.util.List;

public interface RemDao {

	void delete(int id);
	void update(Rem rem);
	Rem  get(int id);
	List<Rem> findAll(long userId);

}