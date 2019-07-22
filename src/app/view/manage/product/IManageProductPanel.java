package app.view.manage.product;

import java.util.ArrayList;

import app.view.custom_component.MyImageButton;

public interface IManageProductPanel {
	MyImageButton getAddButton();
	
	ArrayList<ProductComponent> getProductComponents();
}
