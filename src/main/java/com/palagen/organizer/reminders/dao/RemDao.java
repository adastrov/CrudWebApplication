package com.palagen.organizer.reminders.dao;

import com.palagen.organizer.reminders.model.Rem;

import java.util.List;

public interface RemDao {

	List<Rem> findAll();

}