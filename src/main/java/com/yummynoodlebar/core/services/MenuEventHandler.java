package com.yummynoodlebar.core.services;

import lombok.Delegate;

import com.yummynoodlebar.persistence.services.MenuPersistenceService;

public class MenuEventHandler implements MenuService {
	@Delegate
	private MenuPersistenceService menuPersistenceService;

	public MenuEventHandler(MenuPersistenceService menuPersistenceService) {
		this.menuPersistenceService = menuPersistenceService;
	}
}
