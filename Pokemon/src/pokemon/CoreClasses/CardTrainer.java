package pokemon.CoreClasses;

public class CardTrainer extends Card {
	public static final int TRAINER_STADIUM = 0;
	public static final int TRAINER_SUPPORT = 1;
	public static final int TRAINER_ITEM 	= 2;

	public int trainer_type;
	public String name;
	public int trainer_ability;
	public AbilityTrainer abilityTrainer;
	
	public CardTrainer() {
	super(2);
	}
	
	public void initTrainer(String name,int trainer_type, int trainer_ability)
	{
		this.trainer_type=trainer_type;
		this.name=name;
		this.trainer_ability = trainer_ability;
		abilityTrainer = new AbilityTrainer();
	}
	
}
