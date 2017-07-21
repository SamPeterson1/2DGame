import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel{
	int x = 0;
	int y = 0;
	int realPos1 = 0;
	int i = 3;
	int k = 7;
	int realPos2 = 0;
	int pos1 = 0; //x
	int pos2 = 0; //y
	int offsetX = 0;
	int offsetY = 0;
	int offsetboundaryX = 35;
	int offsetboundaryY = 35;
	int going = 0;
	int inventorySlot = 1;
	boolean toggleHealthBar = true;
	boolean peaOffscreen = false;
	boolean peaOutOfRange = false;
	boolean drawInventory = false;
	boolean TimerStarted = false;
	boolean firstTime = true;
	boolean stop = false;
	boolean swing = false;
	int moveMode = 1;
	int toggleMove = 0;
	int toggle = 0;
	int moveID = 0;
	ArrayList<Tile> tiles;
	ArrayList<Tile> items;
	ArrayList<Shooter> shooters;
	ArrayList<Projectile> projectiles;
	ArrayList<Sword> swords;
	Timer damageTimer;
	Timer AITimer;
	Timer ProjectileTimer;
	Timer ToolWaitTimer;
	Image currentImage;
	Image YoshiRight = new ImageIcon("src/Assets/yoshicut.png").getImage();
	Image YoshiLeft = new ImageIcon("src/Assets/yoshicutLeft.png").getImage();
	int pastSelect = 0;
	int[] pastPos = {0,0};
	int[] inventorySelect = {0,0};
	int map[][] = {
		{2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,2,1,5,5,5,5,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
		{1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1},
		{1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1},
		{1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1},
		{1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1},
		{1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
		{1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1},
		{1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1},
		{1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1},
		{1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1},
		{1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
		{1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1},
		{1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1},
		{1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1},
		{1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1},
		{1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
		{1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1},
		{1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1},
		{1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1},
		{1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1},
		{1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1},
		{1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1},
		{1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1,1,1,4,3,3,4,4,4,1,1,1},
		{1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1,1,1,4,3,3,3,3,4,4,4,1},
		{1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1,1,1,4,4,4,4,3,3,3,3,1},
		{1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	int inventory[][] = {
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0},
			{101,0,0,0,0,0,0}
	};
	int itemLayer[][] = {
		{0,0,11,11,11,0,10,10,10,10,0,0,0,12,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,10,0,0,0,10,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
	};
	Player player1 = new Player();
	EnemyYoshi yoshi = new EnemyYoshi();
	TileHotRock hotrock = new TileHotRock();
	TileSand sand = new TileSand();
	TileWater water = new TileWater();
	TileTree tree = new TileTree();
	TileGrass grass = new TileGrass();
	TileMountain mountain = new TileMountain();
	TileChest1 chest1 = new TileChest1();
	TileChest2 chest2 = new TileChest2();
	TileHarpSeal seal = new TileHarpSeal();
	ItemBanana banana = new ItemBanana();
	S17Shooter shooter17 = new S17Shooter(3, 8, 9, 17);
	PeaShooter peaShooter2 = new PeaShooter(2, 5, 5, 2);
	PeaShooter peaShooter = new PeaShooter(3, 10, 10, 1);
	Seventeen seventeen = new Seventeen(17);
	Fireball fireball = new Fireball(3);
	FireShooter fireShooter = new FireShooter(2, 15, 7, 3);
	TestSword sword = new TestSword();
	Pea pea = new Pea(1);
	Pea pea2 = new Pea(2);
	public GameFrame() {
		super();
		swords = new ArrayList<Sword>();
		projectiles = new ArrayList<Projectile>();
		shooters = new ArrayList<Shooter>();
		tiles = new ArrayList<Tile>();
		items = new ArrayList<Tile>();
		projectiles.add(fireball);
		projectiles.add(pea);
		projectiles.add(pea2);
		projectiles.add(seventeen);
		shooters.add(shooter17);
		shooters.add(fireShooter);
		shooters.add(peaShooter);
		shooters.add(peaShooter2);
		tiles.add(banana);
		tiles.add(chest1);
		tiles.add(chest2);
		tiles.add(hotrock);
		tiles.add(tree);
		tiles.add(grass);
		tiles.add(sand);
		tiles.add(water);
		tiles.add(mountain);
		tiles.add(seal);
		items.add(banana);
		swords.add(sword);
		setFocusable(true);	
		addKeyListener(new YoshiRider());
		addMouseWheelListener(new mouse());
		this.AITimer = new Timer(1500, new AITimer());
		this.damageTimer = new Timer(1000, new DamageTimer());
		this.ProjectileTimer = new Timer(31, new ProjectileTimer());
		this.ToolWaitTimer = new Timer(31, new ToolWaitTimer());
		ToolWaitTimer.start();
	}

	public void paint(Graphics g){
			if(stop == false){
			for(int a = offsetY; a < 20 + offsetY; a ++){
				for(int i = offsetX; i < 20 + offsetX; i ++){
					/*switch(map[a][i]){
						case 1:
							g.drawImage(this.grass.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);
							break;
						 case 2:
							g.drawImage(this.tree.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);
							break;
						 case 3:
							 g.drawImage(this.water.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);
							 break;
						 case 4:
							 g.drawImage(this.sand.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);	
							 break;
						 case 5:
							 g.drawImage(this.mountain.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);	
							 break;	 
					}
					*/
					for(Tile tile: tiles){
						if(tile.getID() == map[a][i]){
							g.drawImage(tile.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);
						}
					}
				}
			}
			AITimer.start();
			g.drawImage(this.YoshiRight, yoshi.getX() * 32, yoshi.getY() * 32, null);
			for(int a = offsetY; a < 20 + offsetY; a ++){
				for(int i = offsetX; i < 20 + offsetX; i ++){
					for(Tile tile: tiles){
						if(tile.getID() == itemLayer[a][i]){
							g.drawImage(tile.getImage(), i * 32 - offsetX * 32, a * 32 - offsetY * 32, null);
						}
					}
				}
			}
			for(Tile tile1: tiles){
				if(tile1.getID() == map[realPos2][realPos1] && tile1.getDamage() > 0 && TimerStarted == false){
					TimerStarted = true;
					damageTimer.start();
				} 
				if(tile1.getID() == map[realPos2][realPos1] && tile1.getDamage() == 0){
					TimerStarted = false;
					damageTimer.stop();
				}
			}
			g.drawImage(currentImage, x, y, null);
			for(Sword sword: swords) {
				if(sword.getCurrentImage() == sword.image2) {
					if(currentImage == YoshiRight) {
						sword.changeImage(2);
						g.drawImage(sword.getCurrentImage(),x + 20,y + 7, null);
					} else if(currentImage == YoshiLeft) {
						sword.changeImage(3);
						g.drawImage(sword.getCurrentImage(),x - 20,y + 7, null);
					}
					break;
				}
			}
			for(Shooter shooter1 : shooters){
				if(!shooter1.getDead()) {
					g.drawImage(shooter1.getImage(), shooter1.getX() * 32, shooter1.getY() * 32, null);
				}
			}
			for(Shooter shooter: shooters){
				for(Projectile projectile: projectiles){
					if(shooter.getID() == projectile.getID()){
							if(projectile.getMotion() == true){
								g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), null);
							}
						if(shooter.shoot(realPos1, realPos2) == 1){
							ProjectileTimer.start();
							break;
						}
					}
				}
			}
			Graphics2D g2D = (Graphics2D)g;
			AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
			AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
			AlphaComposite light = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
			if(toggleHealthBar == true){
				g2D.setComposite(light);
				g.setColor(Color.BLACK);
				g.fillRect(0,0,210,5);
				g.fillRect(0,0,5,25);
				g.fillRect(205,0,5,25);
				g.fillRect(0,0,210,30);
				g.setColor(Color.red);
				g.fillRect(5,5, 200, 20);
				g.setColor(Color.green);
				g.fillRect(5,5, player1.getHP() * 10, 20);
				Integer h = player1.getHP();
				String hp = h.toString() + " / " + "20";
				g.setColor(Color.WHITE);
				g2D.setComposite(opaque);
				g.drawString(hp, 0, 40);
			}
			if(drawInventory == true){
				g.setColor(Color.BLACK);
				g2D.setComposite(opaque);
				for(int x = 1; x < 8; x ++){
					for(int y = 3; y < 8; y ++){
						if(x - 1 == inventorySelect[0] && y - 3 == inventorySelect[1] || x - 1 == inventorySelect[0] && y - 4 == inventorySelect[1]){
							g2D.setColor(Color.GRAY);
						}
						g2D.fillRect(x * 72, y * 72, 67, 5);
						g2D.setColor(Color.BLACK);
					}
				}
				for(int x = 1; x < 9; x ++){
					for(int y = 3; y < 7; y ++){
						if(x - 1 == inventorySelect[0] && y - 3 == inventorySelect[1] || x - 2 == inventorySelect[0] && y - 3 == inventorySelect[1]){
							g2D.setColor(Color.GRAY);
						}
						g2D.fillRect(x * 72 - 5, y * 72, 5, 77);
						g2D.setColor(Color.BLACK);
					}
				}
				g2D.setComposite(transparency);
				g2D.setColor(Color.LIGHT_GRAY);
				for(int x = 1; x < 8; x ++){
					for(int y = 3; y < 7; y ++){
						if(y == 6){
							g2D.setComposite(light);
							g2D.setColor(Color.BLUE);
						} else {
							g2D.setComposite(transparency);
							g2D.setColor(Color.LIGHT_GRAY);
						}
						g2D.fillRect(x * 72, y * 72 + 5, 67, 67);
					}
				}
				g2D.setComposite(opaque);
				for(int i = 0; i < 4; i ++){
					for(int p = 0; p < 7; p ++){
						for(Tile tile: tiles){
							if(tile.getID() == inventory[i][p]){
								g2D.drawImage(tile.getImage(),(p + 1) * 72,221 + 72 * i, 67, 67, null);
							}
						}
						for(Sword sword: swords) {
							if(sword.getID() == inventory[i][p]){
								g2D.drawImage(sword.getImage(1),(p + 1) * 72,221 + 72 * i, 67, 67, null);
							}
						}
					}
				}
			} else {
				g.setColor(Color.BLACK);
				g2D.setComposite(opaque);
				for(int x = 1; x < 8; x ++){
					for(int y = 8; y < 10; y ++){
						if(inventorySlot == 1 && x == 1){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 2 && x == 2){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 3 && x == 3){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 4 && x == 4){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 5 && x == 5){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 6 && x == 6){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 7 && x == 7){
							g2D.setColor(Color.GRAY);
						}
						g2D.fillRect(x * 72, y * 72 - 20, 67, 5);
						g2D.setColor(Color.BLACK);
					}
				}
				for(int x = 1; x < 9; x ++){
					for(int y = 8; y < 9; y ++){
						if(inventorySlot == 1 && x == 1 || inventorySlot == 1 && x - 1 == 1){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 2 && x == 2 || inventorySlot == 2 && x - 1 == 2){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 3 && x == 3 || inventorySlot == 3 && x - 1 == 3){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 4 && x == 4 || inventorySlot == 4 && x - 1 == 4){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 5 && x == 5 || inventorySlot == 5 && x - 1 == 5){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 6 && x == 6 || inventorySlot == 6 && x - 1 == 6){
							g2D.setColor(Color.GRAY);
						} else if(inventorySlot == 7 && x == 7 || inventorySlot == 7 && x - 1 == 7){
							g2D.setColor(Color.GRAY);
						}
						g2D.fillRect(x * 72 - 5, y * 72 - 20, 5, 77);
						g2D.setColor(Color.BLACK);
					}
				}
				g2D.setComposite(transparency);
				g2D.setColor(Color.LIGHT_GRAY);
				for(int x = 1; x < 8; x ++){
					for(int y = 8; y < 9; y ++){
						g2D.fillRect(x * 72, y * 72 + 5 - 20, 67, 67);
					}
				}
				g2D.setComposite(opaque);
				for(int p = 0; p < 7; p ++){
					for(Tile tile: tiles){
						if(tile.getID() == inventory[i][p]){
							g2D.drawImage(tile.getImage(),(p + 1) * 72, 72 * i + 345, 67, 67, null);
						}
					}
					for(Sword sword: swords) {
						if(sword.getID() == inventory[i][p]){
							g2D.drawImage(sword.getImage(1),(p + 1) * 72, 72 * i + 345, 67, 67, null);
						}
					}
				}
				Font large = new Font("Helvetica", Font.BOLD, 100);
				if(player1.getHP() <= 0){
					stop = true;
				}
			}
		} else {
			Font large = new Font("Helvetica", Font.BOLD, 100);
			g.setFont(large);
			g.setColor(Color.BLACK);
			g.fillRect(0,0,640,640);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER",15,300);
		}
	}
	
	/*public class MouseEvent implements MouseListener{

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	*/
	public class mouse implements MouseWheelListener{
		int notches;
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			 notches = e.getWheelRotation();
			if(notches < -1){
				if(inventorySlot > 1){
					inventorySlot --;
				}
			} else if(notches > 1){
				if(inventorySlot < 7){
					inventorySlot ++;
				}
			}
		}
		
	}
	public class DamageTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(Tile tile: tiles){
				if(tile.getID() == map[realPos2][realPos1]){
					player1.DoDamage(tile.getDamage());
				}
			}
		}
		
	}
	public class AITimer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			yoshi.AI(realPos1, realPos2, tiles, map);
			repaint();
		}
		
	}
	public class ToolWaitTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(Sword sword: swords) {
				System.out.println(sword.getWait() + " " + sword.getSpeed());
				if(sword.getWait() <= sword.getSpeed()) {
					sword.increaseWait(1);
				}
			}
		}
		
	}	
	public class ProjectileTimer implements ActionListener{
		boolean quit = false;
		boolean going = false;
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Shooter shooter: shooters){
				for(Projectile projectile: projectiles){
					if(shooter.getID() == projectile.getID() && !shooter.getDead()){
							if(projectile.getDistance() >= projectile.getRange() * 32){
								going = false;
								projectile.resetPea();
								shooter.resetWait();
								projectile.setOrigin(shooter.getX() * 32, shooter.getY() * 32);
								quit = true;
								projectile.setShoot(false);
								peaOutOfRange = true;
								firstTime = true;
								projectile.setMotion(false);
							}
							if(shooter.getPassiveWait() >= shooter.getwaitTime() && shooter.shoot(realPos1, realPos2) == 1){
								projectile.setMotion(true);
								projectile.setShoot(true);
								quit = false;
								going = true;
							}
							System.out.println(shooter.getPassiveWait());
							if(quit == false){
								if(projectile.getShoot() == true && projectile.getShoot() && shooter.getPassiveWait() >= shooter.getwaitTime()){
									switch(shooter.getOrientation()){
										case 1:
											if(projectile.getMoving() == 1){
												projectile.setMotion(true);
												projectile.setDistance(projectile.getSpeed());
												projectile.setY(projectile.getSpeed(), 3);
												peaOutOfRange = false;
											} else if(projectile.getMoving() == 0){
												projectile.setOrigin(shooter.getX() * 32, shooter.getY() * 32);
												projectile.setMoving(1);
											}
											break;
										case 2:
											if(projectile.getMoving() == 2){
												projectile.setMotion(true);
												projectile.setDistance(projectile.getSpeed());
												projectile.setX(projectile.getSpeed(), 2);
												peaOutOfRange = false;
											} else if(projectile.getMoving() == 0){
												projectile.setOrigin(shooter.getX() * 32, shooter.getY() * 32);
												projectile.setMoving(2);
											}
											break;
										case 3:
											if(projectile.getMoving() == 3){
												projectile.setMotion(true);
												projectile.setDistance(projectile.getSpeed());
												projectile.setY(projectile.getSpeed(), 2);
												peaOutOfRange = false;
											} else if(projectile.getMoving() == 0){
												projectile.setOrigin(shooter.getX() * 32, shooter.getY() * 32);
												projectile.setMoving(3);
											}
											break;
										case 4:
											if(projectile.getMoving() == 4){
												projectile.setMotion(true);
												projectile.setDistance(projectile.getSpeed());
												peaOutOfRange = false;
												projectile.setX(projectile.getSpeed(), 3);
											} else if(projectile.getMoving() == 0){
												projectile.setOrigin(shooter.getX() * 32, shooter.getY() * 32);
												projectile.setMoving(4);
											}
											break;
									}
								} else{
									if(shooter.getPassiveWait() < shooter.getwaitTime() && shooter.shoot(realPos1, realPos2) == 1){
										shooter.setPassiveWait(1);
									}
									projectile.setMotion(false);
								}
							} else {
								
								quit = false; 
							}
								repaint();
							for(Projectile projectile1: projectiles){
								for(int i = -31; i < 32; i ++){
									if(pos1 * 32 == projectile1.getX() - i && pos2 * 32 == projectile1.getY()){
										for(Shooter shooter1: shooters) {
											if(projectile1.getMotion()) {
												projectile1.resetPea();
												projectile1.setMotion(false);
												player1.DoDamage(projectile1.getDamage());
											}
											shooter1.resetWait();
										}
									} else if(pos1 * 32 == projectile1.getX() && pos2 * 32 == projectile1.getY() - i){
										for(Shooter shooter1: shooters) {
											if(projectile1.getMotion()) {
												projectile1.resetPea();
												projectile1.setMotion(false);
												shooter1.resetWait();
												player1.DoDamage(projectile1.getDamage());
											}
											shooter1.resetWait();
										}
									}
								}
							}
					} else if(shooter.getDead()) {
						projectile.resetPea();
						projectile.setMotion(false);
					}
				}
			}
		}
	}
	public class YoshiRider implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == 'w'){
				if(drawInventory == false){
					for(Tile tile: tiles) {
						if(realPos2 != 0){
							if(tile.getID() == map[realPos2 - 1][realPos1] && tile.getPassable()) {
								realPos2--;
								y -= 32;
								going = 1;
								pos2 --;
								break;
							}
							if(tile.getID() == itemLayer[realPos2 - 1][realPos1]){
								if(tile.getID() != 10 && tile.getID() != 11){
									if(k == 0){
										i--;
										k = 7;
									}
									if(i > -1){
										k--;
										inventory[i][k] = tile.getID();
										itemLayer[realPos2 - 1][realPos1] = 0;
									}
								}
							}
						}
					}	
				} else {
					if(inventorySelect[1] > 0){
						inventorySelect[1] --;
					}
				}
			}
			else if(e.getKeyChar() == 'a'){
				if(drawInventory == false){
					currentImage = YoshiLeft;
						for(Tile tile: tiles) {
							if(realPos1 != 0){
							if(tile.getID() == map[realPos2][realPos1 - 1] && tile.getPassable()) {
								realPos1 --;
								x -= 32;
								going = 2;
								pos1 --;
								break;
							} 
							if(tile.getID() == itemLayer[realPos2][realPos1 - 1]){
								if(tile.getID() != 10 && tile.getID() != 11){
									if(k == 0){
										i--;
										k = 7;
									}
									if(i > -1){
										k--;
										inventory[i][k] = tile.getID();
										itemLayer[realPos2][realPos1 - 1] = 0;
									}
								}
							}
						}
					}
				} else {
					if(inventorySelect[0] > 0){
						inventorySelect[0] --;
					}
				}
			}
			else if(e.getKeyChar() == 's'){
				if(drawInventory == false){
					if(realPos2 != 54){
						for(Tile tile: tiles) {
							if(tile.getID() == map[realPos2 + 1][realPos1] && tile.getPassable()) {
								realPos2 ++;
								y += 32;
								going = 3;
								pos2 ++;
								break;
							}
							if(tile.getID() == itemLayer[realPos2 + 1][realPos1]){
								if(tile.getID() != 10 && tile.getID() != 11){
									if(k == 0){
										i--;
										k = 7;
									}
									if(i > -1){
										k--;
										inventory[i][k] = tile.getID();
										itemLayer[realPos2 + 1][realPos1] = 0;
									}
								}
							}
						}
					}
				} else {
					if(inventorySelect[1] < 3){
						inventorySelect[1] ++;
					}
				}
			}
			else if(e.getKeyChar() == 'd'){
				if(drawInventory == false){
					if(realPos1 != 54){
						currentImage = YoshiRight;
						for(Tile tile: tiles) {
							if(tile.getID() != 10 && tile.getID() != 11){
								if(tile.getID() == map[realPos2][realPos1 + 1] && tile.getPassable()) {
									realPos1 ++;
									x += 32;
									going = 4;
									pos1 ++;
									break;
								}
								if(tile.getID() == itemLayer[realPos2][realPos1 + 1]){
									if(k == 0){
										i--;
										k = 7;
									}
									if(i > -1){
										k--;
										inventory[i][k] = tile.getID();
										itemLayer[realPos2][realPos1 + 1] = 0;
									}
								}
							}
						}
					}
				} else {
					if(inventorySelect[0] < 6){
						inventorySelect[0] ++;
					}
				}
			} else if(e.getKeyChar() == 'i'){
				if(toggle == 0){
					toggle = 1;
					drawInventory = true;
				} else if(toggle == 1){
					toggle = 0;
					drawInventory = false;
				}
			} else if(e.getKeyChar() == '1'){
				inventorySlot = 1;
			} else if(e.getKeyChar() == '2'){
				inventorySlot = 2;
			} else if(e.getKeyChar() == '3'){
				inventorySlot = 3;
			} else if(e.getKeyChar() == '4'){
				inventorySlot = 4;
			} else if(e.getKeyChar() == '5'){
				inventorySlot = 5;
			} else if(e.getKeyChar() == '6'){
				inventorySlot = 6;
			} else if(e.getKeyChar() == '7'){
				inventorySlot = 7;
			} else if(e.getKeyChar() == 'm'){
				if(moveMode == 1){
					moveMode = 2;
					pastPos[0] = inventorySelect[0];
					pastPos[1] = inventorySelect[1];
					pastSelect = inventory[inventorySelect[1]][inventorySelect[0]];
 				} else if(moveMode == 2){
 					moveID = inventory[inventorySelect[1]][inventorySelect[0]];
 					inventory[pastPos[1]][pastPos[0]] = moveID;
 					inventory[inventorySelect[1]][inventorySelect[0]] = pastSelect;
					moveID = 0;
					moveMode = 1;
				}
			} else if(e.getKeyChar() == 'u'){
				if(inventory[inventorySelect[1]][inventorySelect[0]] > 0 && drawInventory){
					for(Tile item: items){
						if(item.getID() == inventory[inventorySelect[1]][inventorySelect[0]]){
							item.doItemAction(player1);
							inventory[inventorySelect[1]][inventorySelect[0]] = 0;
						}
					}
				} else if(inventory[3][inventorySlot - 1] > 0 && !drawInventory) {
					for(Tile item: items){
						if(item.getID() == inventory[3][inventorySlot - 1]){
							item.doItemAction(player1);
							inventory[3][inventorySlot - 1] = 0;
						}
					}
				}
			} else if(e.getKeyChar() == 'h'){
				if(toggleHealthBar){
					toggleHealthBar = false;
				} else {
					toggleHealthBar = true;
				}
			} else if(e.getKeyChar() == ' ') {
				for(Sword sword: swords) {
					if(inventory[3][inventorySlot - 1] == sword.getID() && !drawInventory && sword.getWait() >= sword.getSpeed()) {
						for(Shooter shooter: shooters) {
							if(shooter.getX() == realPos1 + 1 && shooter.getY() == realPos2 && currentImage == YoshiRight || shooter.getX() == realPos1 - 1 && shooter.getY() == realPos2 && currentImage == YoshiLeft) {
								shooter.doDamage(sword.getDamage());
								shooter.updateHP();
								System.out.println(shooter.getHP());
							}
						}
						sword.changeImage(2);
						ToolWaitTimer.stop();
					}
				}
			}
			switch(going){
			
			case 1:
				if(pos2 == 2){
					if(offsetY - 1 > -1){
						y += 32;
						offsetY --;
						pos2 ++;
						for(Shooter shooter: shooters){
							shooter.decreaseY(1, 2);
						}
						for(Projectile projectile: projectiles){
							if(projectile.getMoving() != 0){
								projectile.originY += 32;
								projectile.setY(33, 2);
							}
						}
					}
				}
				break;
			case 2: 
				if(pos1 == 2){
					if(offsetX - 1 > -1){
						x += 32;
						offsetX --;
						pos1 ++;
						for(Shooter shooter: shooters){
							shooter.decreaseX(1, 2);
						}
						for(Projectile projectile: projectiles){
							if(projectile.getMoving() != 0){
								projectile.originX += 32;
								projectile.setX(33, 2);
							}
						}
					}
				}
				break;
			case 3:
				if(pos2 == 17){
					if(offsetY - 1 < offsetboundaryY - 1){
						y -= 32;
						offsetY ++;
						pos2 --;
						for(Shooter shooter: shooters){
							shooter.decreaseY(1, 1);
						}
						for(Projectile projectile: projectiles){
							if(projectile.getMoving() != 0){
								projectile.originY -= 32;
								projectile.setY(33, 3);
							}
						}
					}
				}
				break;
			case 4:
				if(pos1 == 17){
					if(offsetX + 1 < offsetboundaryX + 1){
						x -= 32;
						offsetX ++;
						pos1 --;
						for(Shooter shooter: shooters){
							shooter.decreaseX(1, 1);
						}
						for(Projectile projectile: projectiles){
							if(projectile.getMoving() != 0){
								projectile.originX -= 32;
								projectile.setX(33, 3);
							}
						}
					}
				}
				break;
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyChar() == ' ') {
				for(Sword sword: swords) {
					if(inventory[3][inventorySlot - 1] == sword.getID() && !drawInventory) {
						sword.changeImage(1);
						sword.increaseWait(0 - sword.getWait());
						ToolWaitTimer.start();
					}
				}
			}
		}
		
	}

}
