
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Blackjack extends JFrame implements ActionListener{
	
	GridLayout experimentLayout = new GridLayout(5, 1);
	GridLayout handLayout = new GridLayout(1, 8);
	GridLayout drawLayout = new GridLayout(1, 9);
	
	private JLabel[] dhLabels = new JLabel[8];
	private JLabel[] phLabels = new JLabel[8];
	private JLabel[] ddLabels = new JLabel[9];
	private JLabel[] pdLabels = new JLabel[9];
	private boolean betPlaced;
	private JButton betBtn;
	private JLabel handScoreLabel;
	private JLabel betAmtLabel;
	private JButton hitBtn;
	private JButton stayBtn;
	private int betAmt;
	//private int[] cards = new int[52];
	private int[] playerHand = new int[11];
	private int[] computerHand = new int[11];
	private int playerTop;
	private int computerTop;
	private Cards c;
	
	private ImageIcon[] cardImgs = {new ImageIcon("cards/CA.png"), new ImageIcon("cards/C2.png"), new ImageIcon("cards/C3.png"), new ImageIcon("cards/C4.png"), new ImageIcon("cards/C5.png"), new ImageIcon("cards/C6.png"), new ImageIcon("cards/C7.png"), new ImageIcon("cards/C8.png"), new ImageIcon("cards/C9.png"), new ImageIcon("cards/C10.png"), new ImageIcon("cards/CJ.png"), new ImageIcon("cards/CQ.png"), new ImageIcon("cards/CK.png"),
				new ImageIcon("cards/DA.png"), new ImageIcon("cards/D2.png"), new ImageIcon("cards/D3.png"), new ImageIcon("cards/D4.png"), new ImageIcon("cards/D5.png"), new ImageIcon("cards/D6.png"), new ImageIcon("cards/D7.png"), new ImageIcon("cards/D8.png"), new ImageIcon("cards/D9.png"), new ImageIcon("cards/D10.png"), new ImageIcon("cards/DJ.png"), new ImageIcon("cards/DQ.png"), new ImageIcon("cards/DK.png"),
				new ImageIcon("cards/HA.png"), new ImageIcon("cards/H2.png"), new ImageIcon("cards/H3.png"), new ImageIcon("cards/H4.png"), new ImageIcon("cards/H5.png"), new ImageIcon("cards/H6.png"), new ImageIcon("cards/H7.png"), new ImageIcon("cards/H8.png"), new ImageIcon("cards/H9.png"), new ImageIcon("cards/H10.png"), new ImageIcon("cards/HJ.png"), new ImageIcon("cards/HQ.png"), new ImageIcon("cards/HK.png"),
				new ImageIcon("cards/SA.png"), new ImageIcon("cards/S2.png"), new ImageIcon("cards/S3.png"), new ImageIcon("cards/S4.png"), new ImageIcon("cards/S5.png"), new ImageIcon("cards/S6.png"), new ImageIcon("cards/S7.png"), new ImageIcon("cards/S8.png"), new ImageIcon("cards/S9.png"), new ImageIcon("cards/S10.png"), new ImageIcon("cards/SJ.png"), new ImageIcon("cards/SQ.png"), new ImageIcon("cards/SK.png"),
				new ImageIcon("cards/cardback.png")};
	
	//Need to set private arrays for panels so we can use get methods?
	
	public Blackjack(){
		
		//logic
		c = new Cards();
		c.shuffle();
		betAmt = 0;
		playerTop = 0;
		computerTop = 0;
		 for(int i = 0; i < playerHand.length; i++) { 
			  playerHand[i] = -1;
			  computerHand[i] = -1;
		  }
		
		
		//Card images
		betPlaced = false;
		ImageIcon image = new ImageIcon("/Users/killazilla/Documents/javaworkspace/Blackjack/src/blackjack/images/felt.jpeg");
		JLabel label = new JLabel();
		label.setIcon(image);
		final JPanel panel = new JPanel();
//		panel.add(label);
		panel.setLayout(experimentLayout);
//		panel.setBackground(Color.decode("#004F00"));
		getContentPane().add(panel);
		setSize(1500, 900);
		
		//****************************************************
		//Dealer Hand row
		//****************************************************
		
		JPanel dealerHand = new JPanel();
		dealerHand.setLayout(handLayout);
		dealerHand.setBackground(Color.decode("#006633"));
			//Generates the panels to fill the Dealer Hand Row
			JPanel[] dhPanels = new JPanel[8];
			for(int i =0; i < 8 ; i++){
				dhPanels[i] = new JPanel();
				dhPanels[i].setOpaque(false);
				dhLabels[i] = new JLabel();
				dhLabels[i].setIcon(null);
				dhPanels[i].add(dhLabels[i]);
				dealerHand.add(dhPanels[i]);
			}
			
			
		panel.add(dealerHand);
			
		//****************************************************
		//Dealer Draw row
		//****************************************************
		
		JPanel dealerDraw = new JPanel();
		dealerDraw.setBackground(Color.decode("#006633"));
			JPanel[] ddPanels = new JPanel[9];
			for(int i = 0; i < 9 ; i++){
				ddPanels[i] = new JPanel();
				ddPanels[i].setOpaque(false);
				ddLabels[i] = new JLabel();
				ddLabels[i].setIcon(null);
				ddPanels[i].add(ddLabels[i]);
				dealerDraw.add(ddPanels[i]);
			}

		
		panel.add(dealerDraw);
		
		//**************************************************
		// Command Panel Row
		//**************************************************
		JPanel commands = new JPanel();
		commands.setBackground(Color.decode("#006633"));
			JPanel[] comPanels = new JPanel[8];
			for(int i = 0; i < 8 ; i++){
				comPanels[i] = new JPanel();
				comPanels[i].setOpaque(false);
			}
			//Makes the panel transparent.  Does not affect the label (cards)

			JButton tempHit = new JButton(new AbstractAction("Hit") {
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		            //Call draw method and add a card to the player's hand.
		        	//Add that card to playerDraw row
		        	//Make sure to update player's hand score
		        	System.out.println("HIT BUTTON YO!");
		        	playerDraw();
		        	handScoreLabel.setText("Hand: " + playerHandValue());
		            
		        }
		    });
			hitBtn = tempHit;
			hitBtn.setEnabled(false);
			JButton tempStay = new JButton(new AbstractAction("Stay") {
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		        	//Stop player turn, start computer AI
		            System.out.println("STAY BUTTON YO!");
		            getComputerInput();
		        }
		    });
			stayBtn = tempStay;
			stayBtn.setEnabled(false);
			comPanels[3].add(hitBtn);
			comPanels[4].add(stayBtn);
			
			for(int i =0; i < 8 ; i++){
				commands.add(comPanels[i]);
			}
		panel.add(commands);
		
		//**************************************************
		// Player Draw Row
		//**************************************************
		JPanel playerDraw = new JPanel();
		playerDraw.setBackground(Color.decode("#006633"));
		JPanel[] pdPanels = new JPanel[9];
		for(int i = 0; i < 9 ; i++){
			pdPanels[i] = new JPanel();
			pdPanels[i].setOpaque(false);
			pdLabels[i] = new JLabel();
			pdLabels[i].setIcon(null);
			pdPanels[i].add(pdLabels[i]);
			dealerDraw.add(pdPanels[i]);
			}

			
		panel.add(playerDraw);
		
		//*************************************************
		//Player Hand row
		//************************************************
		
		JPanel playerHand = new JPanel();
		playerHand.setBackground(Color.decode("#006633"));
		playerHand.setLayout(handLayout);
		playerHand.setBackground(Color.decode("#006633"));
			//Generates the panels to fill the Player Hand Row
		JPanel[] phPanels = new JPanel[8];
		for(int i = 0; i < 8 ; i++){
			phPanels[i] = new JPanel();
			phPanels[i].setOpaque(false);
			phLabels[i] = new JLabel();
			phLabels[i].setIcon(null);
			phPanels[i].add(phLabels[i]);
			dealerDraw.add(phPanels[i]);
			}
			
			
			////////////
			// STATS
			////////////
			int playerWinnings = 209;
			JLabel playerScore = new JLabel("Total Winnings:\n " + playerWinnings);
			phPanels[5].add(playerScore);
			
			int handScore = 0;
			handScoreLabel = new JLabel("Hand: 0");
			phPanels[2].add(handScoreLabel);
			
			// Make these inactive once placeBet is pressed until the end of the round
			//Probably use a boolean variable to determine which elements (hit/stay or bet)
			// are active at any given time.  A click on one activates the others and 
			// freezes itself.
			final int betAmt = 0;
			betAmtLabel = new JLabel("Bet Amount:\n " + betAmt);
			final JTextField betInput = new JTextField(10);
			JButton temp = new JButton(new AbstractAction("Bet Text") {
		        @Override
		        public void actionPerformed( ActionEvent e ) {
		        	//Clicking this will enter the bet
		        	//Must deduct bet amount from total player score
		        	//Update "Bet Amount" label
		        	//NOTE:  An entry of null or zero should trigger game end.
		            
		            //Reset betPlaced to false at end of round
		            //This makes the button invisible to avoid messing up the bets
		            betPlaced = true;
		            
		            betAmtLabel.setText("Bet amount: " + betInput.getText());
		           
		            	
		            	
			            this.setEnabled(false);
//			            this.setEnabled(true);
			            hitBtn.setEnabled(true);
			            stayBtn.setEnabled(true);
			            playerDraw();
			            playerDraw();
			            computerDraw();
			            computerDraw();
			            handScoreLabel.setText("Hand after bet: " + playerHandValue());
		            	
		            

		            
		            
		        }
		    });
			betBtn = temp;
			
			phPanels[6].add(betAmtLabel);
			phPanels[6].add(betInput);
			phPanels[7].add(betBtn);
			
			
			//Adds the panels to the row
			for(int i =0; i < 8 ; i++){
				playerHand.add(phPanels[i]);
			}
		panel.add(playerHand);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public ImageIcon[] getCardImgs(){
		return cardImgs;
	}
	
	public JLabel[] getDealerHandLabels(){
		return dhLabels;
	}
	public JLabel[] getDealerDrawLabels(){
		return ddLabels;
	}
	public JLabel[] getPlayerDrawLabels(){
		return pdLabels;
	}
	public JLabel[] getPlayerHandLabels(){
		return phLabels;
	}
	public void setCardImg(int rowID, int position, int cardID){
		switch(rowID){
		case 1://Dealer Hand
			getDealerHandLabels()[position].setIcon(getCardImgs()[cardID]);
			break;
		case 2://Dealer Draw
			getDealerDrawLabels()[position].setIcon(getCardImgs()[cardID]);
			break;
		case 3://Player Draw
			getPlayerDrawLabels()[position].setIcon(getCardImgs()[cardID]);
			break;
		case 4://Player Hand
			getPlayerHandLabels()[position].setIcon(getCardImgs()[cardID]);
			break;
		}
	
	}
	public void actionPerformed(ActionEvent e) {
       //Just here to make eclipse happy.  Actual listeners are on the button
		//Initialization lines above
 	}
	public void setBetBtn(boolean value){
		betPlaced = value;
	}
	public boolean checkBetPlaced(){
		return betPlaced;
	}
	public JButton getBetBtn(){
		return betBtn;
	}
	public JButton getHitBtn(){
		return hitBtn;
	}
	public JButton getStayBtn(){
		return stayBtn;
	}
	
	 public void gameEnd() {
		 //End the game
		 //give player winnings/take players losings
		 //reset hands
		 //enable bet button
		 System.out.println("GAME OVER");
		 getHitBtn().setEnabled(false);
		  getStayBtn().setEnabled(false);
		  getBetBtn().setEnabled(true);  
		 
		  
		 if(playerHandValue() > 21) {
			  System.out.println("PLAYER LOSES");
			
		  }
		 else  if(computerHandValue() > 21) {
			  System.out.println("COMPUTER BUSTS");
			 
		  }
		 else if(playerHandValue() == computerHandValue()) {
			  System.out.println("PUSH!");
			  
		  }
		 else  if(playerHandValue() > computerHandValue()) {
			  System.out.println("player wins with: " + playerHandValue());
			 
		 
		  }
		  else {
			  System.out.println("computer wins with: " + computerHandValue());
			  
		  }
			
		  
		  for(int i = 0; i < playerHand.length; i++) { 
			  playerHand[i] = -1;
			  computerHand[i] = -1;
			  
		  }
		
		  playerTop = 0;
		  computerTop = 0;
		  	
		 
	 }
	 

	 
	 
	 public void getComputerInput() {
		
		 if(computerHandValue() > playerHandValue()) {	
			 gameEnd();
			 return;
		 }
		 while(computerHandValue() < 17) {
		 	System.out.println("computer draw: " + computerHandValue());
			 computerDraw();
		 }
		 
		 gameEnd();
		 
	 }
	 
	 public void playerDraw() {
		 if(playerHandValue() >= 21) {
			 
			 gameEnd();
		 }
		 else {
		 if(playerTop < playerHand.length) {
		 playerHand[playerTop] = c.draw();
		 	if(playerTop < 2 )
		 		setCardImg(3,playerTop,playerHand[playerTop]);
		 	else
		 		setCardImg(4,playerTop-2,playerHand[playerTop]);
		 
		 	playerTop++;
		 }
		 if(playerHandValue() >= 21) { 
			 gameEnd();
		 }
	 }
		 System.out.println("Player hand: " + playerHandValue());
	}
	 
	 public void computerDraw() {
		 
		 if(computerTop < computerHand.length) {
		computerHand[computerTop] = c.draw(); 
		if(computerTop < 2 )
	 		setCardImg(2,computerTop,computerHand[computerTop]);
	 	else
	 		setCardImg(1,computerTop-2,computerHand[computerTop]);
		 computerTop++;
		 }
		
		 System.out.println("Computer Hand: " + computerHandValue());
	 }
	 
	 public int playerHandValue() {
		 
		    int sum = 0;
		    boolean ace = false;
		    for(int i : playerHand) {
		    	
		    	
		      if(i == 0) sum+=0;
		      else if(i%13+1 >= 10) {
		        sum+=10;
		      }
		      else {
		        sum+=i%13+1;
		        if(i%13+1 == 1) {
		          ace = true;
		        }
		      }
		    }
		    
		     if(sum < 12 && ace) {
		      sum+=10;
		    }
		 
		    return sum;
		  }
		  
		  public int computerHandValue() {
		    int sum = 0;
		    boolean ace = false;
		    for(int i : computerHand) {
		    	if(i%13+1 >= 10) {
		        sum+=10;
		      }
		      else { 
		        sum+=(i%13+1);
		        if(i%13+1 == 1) {
		          ace = true;
		        }
		      }
		    }
		    
		    if(sum < 12 && ace) {
		      sum+=10;
		    }
		    //System.out.println("CompHand: " + sum); 
		    return sum;
		  
		  }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Blackjack instance = new Blackjack();			
		}
		
	}


