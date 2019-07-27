package app.view.main;

import app.view.custom_component.MyImageButton;

public interface ISidePanel
{
	MyImageButton getHomeButton();

	MyImageButton getManageUserButton();

	MyImageButton getManageProductButton();

	MyImageButton getPOSButton();

	MyImageButton getGameButton();
	
	MyImageButton getLogoutButton();
}
