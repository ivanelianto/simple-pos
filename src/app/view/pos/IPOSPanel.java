package app.view.pos;

import app.view.custom_component.MyImageButton;
import app.view.pos.datapanel.DataPanel;

public interface IPOSPanel {
	MyImageButton getProcessButton();
	
	MyImageButton getPendingButton();
	
	DataPanel getDataPanel();
}
