package app.view.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.factory.LabelFactory;
import util.FileHelper;

public class MainFrame extends JFrame implements IMainFrame
{
	private JLabel lblTitle;
	private SidePanel sidePanel;
	private ContentPanel contentPanel;

	public MainFrame()
	{
		setTitle("SIVle POS");
		setSize(825, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(FileHelper.getAssetsPath("logo-colored.png")).getImage());
		initializeComponent();
	}

	public void initializeComponent()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		sidePanel = getSidePanel();
		sidePanel.setBackground(Color.WHITE);

		contentPanel = getContentPanel();
		contentPanel.setBackground(Color.WHITE);

		panel.add(sidePanel, BorderLayout.WEST);
		panel.add(contentPanel, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);
	}

	@Override
	public JLabel getTitleLabel()
	{
		if (lblTitle == null)
		{
			lblTitle = LabelFactory.getInstance().create("");
			lblTitle.setIcon(new ImageIcon(FileHelper.getAssetsPath("logo.png")));
			lblTitle.setHorizontalAlignment(JButton.CENTER);
		}

		return lblTitle;
	}

	@Override
	public SidePanel getSidePanel()
	{
		if (sidePanel == null)
			sidePanel = new SidePanel(this);

		return sidePanel;
	}

	@Override
	public ContentPanel getContentPanel()
	{
		if (contentPanel == null)
			contentPanel = new ContentPanel();

		return contentPanel;
	}

	@Override
	public void close()
	{
		this.dispose();
	}
}
