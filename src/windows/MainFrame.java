package windows;

import java.awt.*;

import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started.
 * @author Keven-Matthew
 */
public class MainFrame extends JFrame{
	/**
	 * This is the identifier string for the level select JPanel.
	 */
	private static final String LEVELSELECTPANEL = "LevelSelectPanel";
	/**
	 * This is the identifier string for the main menu JPanel.
	 */
	private static final String MAINMENUPANEL = "MainMenuPanel";
	/**
	 * The level select menu that will be used by the user.
	 */
	private LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
	/**
	 * The main menu that will be used by the user.
	 */
	private MainMenu mainMenu = new MainMenu();
	/**
	 * A JPanel with a cardLayout used to hold all other JPanels.
	 */
	private static JPanel menus;
	
	/**
	 * This creates a new main JFrame
	 */
	public MainFrame(){
		setLayout(new BorderLayout());
		getContentPane().setPreferredSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game: Tito Escapes the Zoo");
		setVisible(true);
		pack();
		
		menus = new JPanel(new CardLayout());
		menus.add(levelSelectMenu, LEVELSELECTPANEL);
		menus.add(mainMenu, MAINMENUPANEL);
		
		add(menus, BorderLayout.CENTER);
		
		CardLayout cardLayout = (CardLayout) menus.getLayout();
		cardLayout.show(menus, MAINMENUPANEL);
	}

	public static JPanel getMenus() {
		return menus;
	}

	public void setMenus(JPanel menus) {
		MainFrame.menus = menus;
	}

	public static String getLevelselectpanel() {
		return LEVELSELECTPANEL;
	}

	public static String getMainmenupanel() {
		return MAINMENUPANEL;
	}

	public void setLevelSelectMenu(LevelSelectMenu levelSelectMenu) {
		this.levelSelectMenu = levelSelectMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public LevelSelectMenu getLevelSelectMenu() {
		return levelSelectMenu;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}
}