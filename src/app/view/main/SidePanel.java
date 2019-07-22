package app.view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import app.view.custom_component.MyColor;

public class SidePanel extends JPanel {
	
	private IMainFrame components;
	
	public SidePanel(IMainFrame components)
	{
		this.setBorder(new MatteBorder(new Insets(0, 0, 0, 1), MyColor.getDarkBlueGrayBackground()));
		
		this.setBackground(Color.WHITE);
		
		this.components = components;
		
		GridBagLayout gbLayout = new GridBagLayout();
		this.setLayout(gbLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		this.setPreferredSize(new Dimension(300, this.getHeight()));
		
		c.weighty = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(components.getTitleLabel(), c);
		
		c.weighty = 1;
		c.gridy = 1;
		this.add(components.getHomeButton(), c);
		
		c.gridy = 2;
		this.add(components.getManageUserButton(), c);
		
		c.gridy = 3;
		this.add(components.getManageProductButton(), c);
		
		c.gridy = 4;
		this.add(components.getPOSButton(), c);
		
		c.gridy = 5;
		this.add(components.getReportButton(), c);
		
		c.gridy = 6;
		this.add(components.getLogoutButton(), c);
		
		c.gridy = 7;
		c.weighty = 3;
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(MyColor.getPrimaryBackground());
		this.add(blankPanel, c);
	}
}
