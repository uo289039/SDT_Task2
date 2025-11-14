package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.MusicPlayer;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnPlay;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	private JSeparator separator;
	private JMenuItem mntmPlay;
	private JPanel panelNorth;
	private JLabel lblIcon;
	private JSlider sliderVolumen;
	private JPanel panelVolumen;
	private JLabel lblVOL;
	private JLabel lblVolumen;
	private MusicPlayer mPlayer;
	private JPanel panelCenter;
	private JPanel panelLibrary;
	private JPanel panelPlay;
	private JLabel lblLibrary;
	private JLabel lblPlayList;
	private JScrollPane scrollPane;
	

	/**
	 * Create the frame.
	 */
	public MainWindow(MusicPlayer mP) {
		this.mPlayer=mP;
		setTitle("Music Player");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logoTitulo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 562);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorth(), BorderLayout.NORTH);
		contentPane.add(getPanelCenter(), BorderLayout.SOUTH);

	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.add(getMntmPlay());
		}
		return mnPlay;
	}
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
		}
		return mntmOpen;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
		}
		return mntmExit;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmPlay() {
		if (mntmPlay == null) {
			mntmPlay = new JMenuItem("Play Music");
		}
		return mntmPlay;
	}
	private JPanel getPanelNorth() {
		if (panelNorth == null) {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.BLACK);
			panelNorth.setLayout(new GridLayout(1, 3, 0, 0));
			panelNorth.add(getLblIcon());
			panelNorth.add(getSliderVolumen());
			panelNorth.add(getPanelVolumen());
		}
		return panelNorth;
	}
	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));
		}
		return lblIcon;
	}
	private JSlider getSliderVolumen() {
		if (sliderVolumen == null) {
			sliderVolumen = new JSlider();
			sliderVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					getLblVolumen().setText(String.valueOf(getSliderVolumen().getValue()));
					setVolumen(getSliderVolumen().getValue());
				}
			});
			sliderVolumen.setForeground(Color.WHITE);
			sliderVolumen.setBackground(Color.BLACK);
			sliderVolumen.setPaintTicks(true);
			sliderVolumen.setPaintLabels(true);
			sliderVolumen.setMinorTickSpacing(5);
			sliderVolumen.setMajorTickSpacing(20);
		}
		return sliderVolumen;
	}
	
	private void setVolumen(int value) {
		double maxVol=getSliderVolumen().getMaximum();
		mPlayer.setVolumen(value,maxVol);
		
	}
	private JPanel getPanelVolumen() {
		if (panelVolumen == null) {
			panelVolumen = new JPanel();
			panelVolumen.setBackground(Color.BLACK);
			panelVolumen.setLayout(new GridLayout(1, 2, 0, 0));
			panelVolumen.add(getLblVOL());
			panelVolumen.add(getLblVolumen());
		}
		return panelVolumen;
	}
	private JLabel getLblVOL() {
		if (lblVOL == null) {
			lblVOL = new JLabel("   VOL: ");
			lblVOL.setBackground(Color.BLACK);
			lblVOL.setForeground(Color.ORANGE);
			lblVOL.setFont(new Font("Tahoma", Font.PLAIN, 40));
		}
		return lblVOL;
	}
	private JLabel getLblVolumen() {
		if (lblVolumen == null) {
			lblVolumen = new JLabel("50");
			lblVolumen.setFont(new Font("Tahoma", Font.PLAIN, 40));
			lblVolumen.setBackground(Color.BLACK);
			lblVolumen.setForeground(Color.WHITE);
		}
		return lblVolumen;
	}
	private JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel();
			panelCenter.setBackground(Color.BLACK);
			panelCenter.setLayout(new GridLayout(1, 2, 3, 0));
			panelCenter.add(getPanelLibrary());
			panelCenter.add(getPanelPlay());
		}
		return panelCenter;
	}
	private JPanel getPanelLibrary() {
		if (panelLibrary == null) {
			panelLibrary = new JPanel();
			panelLibrary.setBackground(Color.BLACK);
			panelLibrary.setLayout(new BorderLayout(0, 0));
			panelLibrary.add(getLblLibrary(), BorderLayout.NORTH);
			panelLibrary.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelLibrary;
	}
	private JPanel getPanelPlay() {
		if (panelPlay == null) {
			panelPlay = new JPanel();
			panelPlay.setBackground(Color.BLACK);
			panelPlay.setLayout(new BorderLayout(0, 0));
			panelPlay.add(getLblPlayList(), BorderLayout.NORTH);
		}
		return panelPlay;
	}
	private JLabel getLblLibrary() {
		if (lblLibrary == null) {
			lblLibrary = new JLabel("  ♪ Library");
			lblLibrary.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblLibrary.setForeground(Color.ORANGE);
		}
		return lblLibrary;
	}
	private JLabel getLblPlayList() {
		if (lblPlayList == null) {
			lblPlayList = new JLabel(" ♪ PlayList");
			lblPlayList.setForeground(Color.ORANGE);
			lblPlayList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblPlayList;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
		}
		return scrollPane;
	}
}
