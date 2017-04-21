package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import ie.gmit.sw.ai.sprites.FuzzySprite;
import ie.gmit.sw.ai.sprites.ItemSprite;
import ie.gmit.sw.ai.sprites.NeuralSprite;
import ie.gmit.sw.ai.sprites.Sprite;
public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 50;
	private static final int IMAGE_COUNT = 14;
	private GameView view;
	private Maze model;
	//private Node[][] maze;
	private int currentRow;
	private int currentCol;
	
	private Player player;
	private FuzzySprite fsprite;
	private NeuralSprite nsprite;
	private JLayeredPane lpane = new JLayeredPane();
    private JPanel panel = new JPanel();
    private JPanel panelEnd = new JPanel();	
	JLabel healthBar;
	
	public GameRunner() throws Exception{
		currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
		player = new Player(currentRow, currentCol);
		//System.out.println("player col: " + player.getCol());
		model = new Maze(MAZE_DIMENSION, player);
    	view = new GameView(model);
    	placePlayer();
    	Sprite[] sprites = getSprites();
    	view.setSprites(sprites);
    	
    	
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
        healthBar = new JLabel("Healh Status: " + player.getHealth());
        panel.add(healthBar);
        	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        view.add(panel);
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.setVisible(true);
        endGame();
	}
        
        private void endGame(){
        	panelEnd.setBackground(Color.BLUE);
            panelEnd.setBounds(0, 0, 600, 400);
            panelEnd.setOpaque(true);
            lpane.add(panelEnd, new Integer(0), 0);
        }
	
	private void placePlayer(){   	
//    	currentRow = (int) (MAZE_DIMENSION * Math.random());
//    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	model.set(currentRow, currentCol, '5'); //A Spartan warrior is at index 5
    	updateView(); 		
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	private boolean isValidMove(int row, int col){
		if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0020'){
			model.set(currentRow, currentCol, '\u0020');
			//System.out.println(player.getCol());
			model.set(row, col, '5');
			return true;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0031')){
			model.getMaze()[row][col].setNodeType('0');
			player.addSword();
			player.setSwordStrength(20);
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0032')){
			model.getMaze()[row][col].setNodeType('0');
			player.setHealth(player.getHealth()+25);
			healthBar.setText("Health Status: " + Double.toString(Math.round(player.getHealth())));
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0033')){
			model.getMaze()[row][col].setNodeType('0');
			player.addBombs();
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0034')){
			model.getMaze()[row][col].setNodeType('0');
			player.addHbomb();
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0036')){
			
			fsprite = model.getFuzzySprite(row, col);
			fsprite.engageFuzzy();
			if(player.getHealth() > 0){
				fsprite.setId(-1);
				System.out.println("sprite id game runner: " + fsprite.getId());
				model.set(currentRow, currentCol, '\u0020');
				model.set(row, col, '\u0020');
				//Thread.currentThread().interrupt();
				healthBar.setText("Health Status: " + Double.toString(Math.round(player.getHealth())));
			}
			else{
				// end game ?
				System.exit(0);
			}
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0037'))
		{
			nsprite = model.getNeuralSprite(row, col);
			nsprite.engageNN();
//			if(player.getHealth() > 0){
//				nsprite.setId(-1);
//				model.set(currentRow, currentCol, '\u0020');
//				model.set(row, col, '\u0020');
//				healthBar.setText("Health Status: " + Double.toString(Math.round(player.getHealth())));
//			}
//			else{
//				// end game ?
//				System.exit(0);
//			}
			return false;
		}
		else{
			return false; //Can't move
		}
	}
	
	private Sprite[] getSprites() throws Exception{
		//Read in the images from the resources directory as sprites. Note that each
		//sprite will be referenced by its index in the array, e.g. a 3 implies a Bomb...
		//Ideally, the array should dynamically created from the images... 
		Sprite[] sprites = new Sprite[IMAGE_COUNT];
		sprites[0] = new ItemSprite("Hedge", "resources/hedge.png");
		sprites[1] = new ItemSprite("Sword", "resources/sword.png");
		sprites[2] = new ItemSprite("Help", "resources/help.png");
		sprites[3] = new ItemSprite("Bomb", "resources/bomb.png");
		sprites[4] = new ItemSprite("Hydrogen Bomb", "resources/h_bomb.png");
		sprites[5] = new ItemSprite("Spartan Warrior", "resources/spartan_1.png", "resources/spartan_2.png");
		sprites[6] = new FuzzySprite("Black Spider", "resources/black_spider_1.png", "resources/black_spider_2.png");
		sprites[7] = new FuzzySprite("Blue Spider", "resources/blue_spider_1.png", "resources/blue_spider_2.png");
		sprites[8] = new FuzzySprite("Brown Spider", "resources/brown_spider_1.png", "resources/brown_spider_2.png");
		sprites[9] = new FuzzySprite("Green Spider", "resources/green_spider_1.png", "resources/green_spider_2.png");
		sprites[10] = new NeuralSprite("Grey Spider", "resources/grey_spider_1.png", "resources/grey_spider_2.png");
		sprites[11] = new NeuralSprite("Orange Spider", "resources/orange_spider_1.png", "resources/orange_spider_2.png");
		sprites[12] = new NeuralSprite("Red Spider", "resources/red_spider_1.png", "resources/red_spider_2.png");
		sprites[13] = new NeuralSprite("Yellow Spider", "resources/yellow_spider_1.png", "resources/yellow_spider_2.png");
		return sprites;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}