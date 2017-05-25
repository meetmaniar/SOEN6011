package pokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pokemon.CoreClasses.Card;
import pokemon.CoreClasses.CardEnergy;
import pokemon.CoreClasses.CardPokemon;
import pokemon.CoreClasses.CardTrainer;

//String name,int hitPoints,int attackPoints, int specialAttackPoints

public class CardView extends JPanel implements MouseListener {
	public String Publicname;
	GameView gameView;
	JLabel nameLabel;
	JLabel hpLabel;
	JLabel apLabel;
	JLabel sapLabel;
	
	int index_hand = -1;
	int index_bench = -1;
	int index_active = -1;
	int IS_AI=0;
	String place="";
	public CardView(CardPokemon e, GameView ge, int index_hand, int index_bench, int index_active,int IS_AI,String place) {
		this.place=place;
		this.index_hand = index_hand;
		this.index_bench = index_bench;
		this.index_active = index_active;
		this.IS_AI=IS_AI;
		this.gameView = ge;
		String name = e.m_name;
		int hitPoints = e.m_hp;
		int energy= e.m_energy;

		this.setPreferredSize(new Dimension(63, 63));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setLayout(null);

		nameLabel = new JLabel(name);
		nameLabel.setBounds(5, 0, 700, 20);

		hpLabel = new JLabel("HP:" + String.valueOf(hitPoints));
		hpLabel.setBounds(5, 14, 700, 20);

		System.out.print("Energy "+energy);
		
		apLabel = new JLabel("E:" + String.valueOf(energy));
		apLabel.setBounds(5, 29, 700, 20);
		
		this.add(nameLabel);
		this.add(hpLabel);
		this.add(apLabel);
		this.addMouseListener(this);

	}

	public CardView(CardTrainer e, GameView ge, int index,String place) {
		this.place=place;
		index_hand = index;
		this.gameView = ge;
		int type = e.trainer_type;
		int heal_amount = 10;
		this.setPreferredSize(new Dimension(63, 63));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setLayout(null);

		JLabel typeLabel = new JLabel(Integer.toString(type));
		typeLabel.setBounds(5, 0, 700, 20);

		hpLabel = new JLabel("Heal Amount:" + String.valueOf(heal_amount));
		hpLabel.setBounds(5, 14, 700, 20);

		
		this.add(typeLabel);
		this.add(hpLabel);
		this.addMouseListener(this);

	}

	public CardView(CardEnergy e, GameView ge, int index,String place) {
		this.place=place;
		index_hand = index;
		this.gameView = ge;
		int type = e.m_type;
		int energy_level = 10; // 10 because the energy amount for the damage is
								// same for each pokemon right now.
		this.setPreferredSize(new Dimension(63, 63));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setLayout(null);

		JLabel typeLabel = new JLabel(Integer.toString(type));
		typeLabel.setBounds(5, 0, 700, 20);

		hpLabel = new JLabel("Energy Amount:" + String.valueOf(energy_level));
		hpLabel.setBounds(5, 14, 700, 20);

		nameLabel = new JLabel("Energy Amount:" + String.valueOf(energy_level));
		nameLabel.setBounds(5, 0, 700, 20);

		this.add(typeLabel);
		this.add(hpLabel);
		this.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		System.out.println(index_hand);

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if (gameView == null) {
			System.out.println("Null Gameview");
		}
		if (gameView.toolTip == null) {
			System.out.println("Null Tool");
		}
		if (nameLabel == null) {
			System.out.println("Null nameLabel");
		}
		try{
		gameView.toolTip.add(nameLabel);
		gameView.toolTip.add(hpLabel);
		gameView.toolTip.repaint();
		}
		catch(Exception ee)
		{
			
		}
		// gameView.toolTip.revalidate();

	}

	@Override
	public void mouseExited(MouseEvent e) {

		try{
			
		
		gameView.toolTip.removeAll();
		gameView.toolTip.repaint();
		this.add(nameLabel);
		this.add(hpLabel);
		}
		catch(Exception eead)
		{
		
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		
		
		
		CardView temp = (CardView) e.getSource();
		
		if(temp.place.equals("E"))
		if(temp.IS_AI==0)
		{
			GameView.HUMAN.attachEnergyActive(temp.index_hand);	
			System.out.println("Energy for the Human AP"+((CardPokemon)GameView.HUMAN.active).m_energy);
			gameView.refreshUI();
			
			return;
		}
		else
		{
			GameView.AI.attachEnergyActive(temp.index_hand);	
			gameView.refreshUI();
			return;
		}
		
		
		try {
			if (GameView.HUMAN.hand[temp.index_hand].m_type != 0)
				return;
		} catch (Exception ed) {

		}
		if (temp.index_bench > -1) {
			System.out.println("Moving to Active");
			GameView.HUMAN.placeBenchtoActive(temp.index_bench);
		} else {
			System.out.println("Moving to Bench");
			GameView.HUMAN.placeBench(temp.index_hand);
			temp.index_bench = GameView.AI.bench_top;
		}
		gameView.refreshUI();

		// System.out.println(((CardPokemon)GameView.HUMAN.active).m_name);
		// gameView.refreshUI();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// gameView.refreshUI();
	}

	
	
}