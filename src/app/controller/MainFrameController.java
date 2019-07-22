package app.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentSampleModel;

import app.view.main.IMainFrame;
import app.view.main.IMainFramePanel;
import app.view.main.SidePanel;

public class MainFrameController {
	private IMainFramePanel components;

	private static MainFrameController instance;

	private MainFrameController(IMainFramePanel components)
	{
		this.components = components;
	}

	public static MainFrameController getInstance(IMainFramePanel components) {
		if (instance == null)
			instance = new MainFrameController(components);

		return instance;
	}
	
	public ActionListener onHomeButtonClick()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
	}
	
	public ActionListener onManageUserButtonClick()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
	}
	
}
