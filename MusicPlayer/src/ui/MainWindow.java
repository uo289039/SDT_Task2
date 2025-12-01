package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.MusicPlayer;
import player.MyFile;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButtonMenuItem;

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
	private JScrollPane scrollPaneLibrary;
	private JList<MyFile> listLibrary;
	private JPanel panelBtnLIbrary;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnClearLibrary;
	private JScrollPane scrollPaneLIst;
	private JList<MyFile> listPlay;
	private JPanel panelBtnLIst;
	private JButton btnPrevious;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnNext;
	private JButton btnDel;
	private JButton btnClearList;
	private JFileChooser selector;
	private DefaultListModel<MyFile> modeloListLibrary;
	private DefaultListModel<MyFile> modeloListPlay;
	private MyFile song;
	private JMenuItem mntmMaxVolumen;
	private JMenuItem mntmSilence;
	private JSeparator separator_1;
	private JSeparator separator_2;

	/**
	 * Create the frame.
	 */
	public MainWindow(MusicPlayer mP) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		this.mPlayer=mP;
		mPlayer.setOnSongEnd(() -> {
		    nextSong();   // avanzar automáticamente
		});
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
		contentPane.add(getPanelCenter(), BorderLayout.CENTER);

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
			mnPlay.add(getSeparator_1());
			mnPlay.add(getMntmMaxVolumen());
			mnPlay.add(getSeparator_2());
			mnPlay.add(getMntmSilence());
		}
		return mnPlay;
	}
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openFiles();
				}

				
			});
		}
		return mntmOpen;
	}
	
	private void openFiles() {
		// TODO Auto-generated method stub
		int response=getSelector().showOpenDialog(null);
		if(response==JFileChooser.APPROVE_OPTION) {
			for(int i=0;i<getSelector().getSelectedFiles().length;i++) {
				MyFile mFile=new MyFile(getSelector().getSelectedFiles()[i]);
				if(!modeloListLibrary.contains(mFile))
					modeloListLibrary.add(i, mFile);
			}
		}
		btnAdd.setEnabled(true);
		btnDelete.setEnabled(true);
		btnClearLibrary.setEnabled(true);
	}
	
	private JFileChooser getSelector() {
		// TODO Auto-generated method stub
		if(selector==null) {
			selector= new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3", "mp3"));
			String dir=System.getProperty("user.home")+"/Music";
			selector.setCurrentDirectory(new File(dir));
		}
		return selector;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exit();
				}
			});
		}
		return mntmExit;
	}
	
	private void exit() {
		int response=JOptionPane.showConfirmDialog(null, "Do you want to close the application?");
		if(response==JOptionPane.YES_OPTION) {
			clearLibraryList();
			System.exit(0);
		}
			
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
			mntmPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playMusic();
				}

				
			});
		}
		return mntmPlay;
	}
	
	private void playMusic() {
		// TODO Auto-generated method stub
		
		
		MyFile file=listPlay.getSelectedValue();
		if(file!=null) {
			mPlayer.play(file.getMyFile());
			song=file;
		}
		else {
			file=modeloListPlay.get(0);
			mPlayer.play(file.getMyFile());
			song=file;
		}
		if(listPlay.getSelectedIndex()<0)
			listPlay.setSelectedIndex(0);
		else
			listPlay.setSelectedIndex(listPlay.getSelectedIndex());
		
		
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
			panelLibrary.add(getScrollPaneLibrary(), BorderLayout.CENTER);
			panelLibrary.add(getPanelBtnLIbrary(), BorderLayout.SOUTH);
		}
		return panelLibrary;
	}
	private JPanel getPanelPlay() {
		if (panelPlay == null) {
			panelPlay = new JPanel();
			panelPlay.setBackground(Color.BLACK);
			panelPlay.setLayout(new BorderLayout(0, 0));
			panelPlay.add(getLblPlayList(), BorderLayout.NORTH);
			panelPlay.add(getScrollPaneLIst(), BorderLayout.CENTER);
			panelPlay.add(getPanelBtnLIst(), BorderLayout.SOUTH);
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
	private JScrollPane getScrollPaneLibrary() {
		if (scrollPaneLibrary == null) {
			scrollPaneLibrary = new JScrollPane();
			scrollPaneLibrary.setBackground(Color.BLACK);
			scrollPaneLibrary.setBorder(new LineBorder(new Color(255, 0, 0), 5, true));
			scrollPaneLibrary.setViewportView(getListLibrary());
		}
		return scrollPaneLibrary;
	}
	private JList<MyFile> getListLibrary() {
		if (listLibrary == null) {
			modeloListLibrary= new DefaultListModel<MyFile>();
			listLibrary = new JList<MyFile>(modeloListLibrary);
			listLibrary.setBackground(Color.BLACK);
			listLibrary.setForeground(Color.WHITE);
		}
		return listLibrary;
	}
	private JPanel getPanelBtnLIbrary() {
		if (panelBtnLIbrary == null) {
			panelBtnLIbrary = new JPanel();
			panelBtnLIbrary.setLayout(new GridLayout(1, 3, 0, 0));
			panelBtnLIbrary.add(getBtnAdd());
			panelBtnLIbrary.add(getBtnDelete());
			panelBtnLIbrary.add(getBtnClearLibrary());
		}
		return panelBtnLIbrary;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add to PlayList");
			btnAdd.setEnabled(false);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addPlayList();
				}

				
			});
		}
		return btnAdd;
	}
	
	private void addPlayList() {
		List<MyFile> selected=listLibrary.getSelectedValuesList();
		 if(!selected.isEmpty()) {
		        for(MyFile file : selected)
		            if(!modeloListPlay.contains(file))
		                modeloListPlay.addElement(file);
		
			btnPlay.setEnabled(true);
			btnPrevious.setEnabled(true);
			btnNext.setEnabled(true);
			btnPause.setEnabled(true);
			btnDel.setEnabled(true);
			btnClearList.setEnabled(true);
		}
		
	}
	
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteLibraryList();
				}

				
			});
			btnDelete.setEnabled(false);
		}
		return btnDelete;
	}
	
	private void deleteLibraryList() {
		// TODO Auto-generated method stub
		List<MyFile> selected = listLibrary.getSelectedValuesList();

	    for (MyFile file : selected) {

	        // Si está en playlist también se borra
	        if (modeloListPlay.contains(file)) {

	            if (song != null && song.equals(file))
	                mPlayer.stop();

	            modeloListPlay.removeElement(file);
	        }

	        modeloListLibrary.removeElement(file);
	    
		}
		if(modeloListLibrary.size()==0) {
			
			deshabilitaBotonesPlay();
			
			deshabilitaBotonesLibrary();
		}
		
			
		
	}
	
	private void deshabilitaBotonesLibrary() {
		// TODO Auto-generated method stub
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(false);
		btnClearLibrary.setEnabled(false);
	}

	private JButton getBtnClearLibrary() {
		if (btnClearLibrary == null) {
			btnClearLibrary = new JButton("Clear");
			btnClearLibrary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearLibraryList();
				}
			});
			btnClearLibrary.setEnabled(false);
		}
		return btnClearLibrary;
	}
	
	protected void clearLibraryList() {
		// TODO Auto-generated method stub
		if (song != null)
	        mPlayer.stop();

	    modeloListLibrary.removeAllElements();
	    modeloListPlay.removeAllElements();
		clearPlay();
			
		
	}

	private void clearPlay() {
		// TODO Auto-generated method stub
		modeloListPlay.removeAllElements();
		
		deshabilitaBotonesPlay();
		
		deshabilitaBotonesLibrary();
	}
	
	private void deshabilitaBotonesPlay() {
		btnPlay.setEnabled(false);
		btnPrevious.setEnabled(false);
		btnNext.setEnabled(false);
		btnPause.setEnabled(false);
		btnDel.setEnabled(false);
		btnClearList.setEnabled(false);
	}

	private JScrollPane getScrollPaneLIst() {
		if (scrollPaneLIst == null) {
			scrollPaneLIst = new JScrollPane();
			scrollPaneLIst.setBorder(new LineBorder(Color.RED, 5, true));
			scrollPaneLIst.setBackground(Color.BLACK);
			scrollPaneLIst.setViewportView(getListPlay());
		}
		return scrollPaneLIst;
	}
	private JList<MyFile> getListPlay() {
		if (listPlay == null) {
			modeloListPlay=new DefaultListModel<MyFile>();
			listPlay = new JList<MyFile>(modeloListPlay);
			listPlay.setForeground(Color.WHITE);
			listPlay.setBackground(Color.BLACK);
		}
		return listPlay;
	}
	private JPanel getPanelBtnLIst() {
		if (panelBtnLIst == null) {
			panelBtnLIst = new JPanel();
			panelBtnLIst.setLayout(new GridLayout(1, 6, 0, 0));
			panelBtnLIst.add(getBtnPrevious());
			panelBtnLIst.add(getBtnPlay());
			panelBtnLIst.add(getBtnPause());
			panelBtnLIst.add(getBtnNext());
			panelBtnLIst.add(getBtnDel());
			panelBtnLIst.add(getBtnClearList());
		}
		return panelBtnLIst;
	}
	private JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("◄◄");
			btnPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousSong();
				}
			});
			btnPrevious.setEnabled(false);
		}
		return btnPrevious;
	}
	protected void previousSong() {
		// TODO Auto-generated method stub
		int index=listPlay.getSelectedIndex();
		--index;
		if(index<0)
			index=modeloListPlay.getSize()-1;
		
		listPlay.setSelectedIndex(index);
		mPlayer.play(modeloListPlay.get(index).getMyFile());
			
		
		
		
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("►");
			btnPlay.setEnabled(false);
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playMusic();
				}
			});
		}
		return btnPlay;
	}
	private JButton getBtnPause() {
		if (btnPause == null) {
			btnPause = new JButton("■");
			btnPause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stopMusic();
				}
			});
			btnPause.setEnabled(false);
		}
		return btnPause;
	}
	protected void stopMusic() {
		// TODO Auto-generated method stub
		mPlayer.stop();
		
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("►►");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nextSong();
				}
			});
			btnNext.setEnabled(false);
		}
		return btnNext;
	}
	protected void nextSong() {
		// TODO Auto-generated method stub
		int index=listPlay.getSelectedIndex();
		index++;
		if(index>=modeloListPlay.getSize())
			index=0;
		
		listPlay.setSelectedIndex(index);
		mPlayer.play(modeloListPlay.get(index).getMyFile());
	}
	

	private JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton("Delete");
			btnDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deletePlay();
				}
			});
			btnDel.setEnabled(false);
		}
		return btnDel;
	}
	protected void deletePlay() {
		// TODO Auto-generated method stub
		List<MyFile> selected = listPlay.getSelectedValuesList();

	    for (MyFile file : selected) {

	        // Si se está reproduciendo una canción que se va a borrar
	        if (song != null && song.equals(file)) {
	            mPlayer.stop();
	        }

	        modeloListPlay.removeElement(file);
			
		}
		if(modeloListPlay.size()==0) {
			deshabilitaBotonesPlay();
			
		}
		
	}

	private JButton getBtnClearList() {
		if (btnClearList == null) {
			btnClearList = new JButton("Clear");
			btnClearList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearPlay();
				}
			});
			btnClearList.setEnabled(false);
		}
		return btnClearList;
	}
	private JMenuItem getMntmMaxVolumen() {
		if (mntmMaxVolumen == null) {
			mntmMaxVolumen = new JMenuItem("Max Volumen");
			mntmMaxVolumen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getLblVolumen().setText(String.valueOf(100));
					setVolumen(100);
					getSliderVolumen().setValue(100);
				}
			});
		}
		return mntmMaxVolumen;
	}
	private JMenuItem getMntmSilence() {
		if (mntmSilence == null) {
			mntmSilence = new JMenuItem("Silence");
			mntmSilence.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getLblVolumen().setText(String.valueOf(0));
					System.out.println("Silence");
					setVolumen(0);
					getSliderVolumen().setValue(0);
				}
			});
		}
		return mntmSilence;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
		}
		return separator_2;
	}
}
