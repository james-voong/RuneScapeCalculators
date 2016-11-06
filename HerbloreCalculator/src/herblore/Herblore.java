package herblore;

public class Herblore {
	private static int amount = 10000;
	private static int grimy = 3760;
	private static int clean = 3925;
	private static int unf = 4487;
	private static int vial = 58;
	private static int secondary = 9;
	private static int dose3 = 3875;
	private static int dose4 = 5167;

	public static void fromGrimy() {
		int profit = (clean - grimy) * amount;
		int seed = amount * grimy;
		System.out.println("From Grimy:");
		System.out.println("Total seed money for " + amount + " herbs:" + seed);
		System.out.println("Profit from cleaning:" + profit);

		int extras = (int) (amount * 1.11) - amount;
		int extraVials = extras * vial;
		int SoC = (int) (unf * extras) - extraVials;
		// System.out.println(SoC);
		int unfProf = ((unf - (grimy + vial)) * amount);
		System.out.println("Profit from making unf:" + (unfProf + SoC));

		int cost = (grimy + vial + secondary) * amount;
		int savedSecondary = extras * secondary;
		int extra3 = (int) (dose3 * amount * 0.05);
		int outputOf3 = (int) (dose3 * amount * 0.9);
		int outputOf4 = (int) (dose4 * amount * 0.1);
		int totalPots = extra3 + outputOf3 + outputOf4;
		int potProf = totalPots - cost + savedSecondary;
		System.out.println("Total seed money for " + amount + " potions:" + cost);
		System.out.println("Profit from making potion: " + potProf);
		int converted4 = (int) (((amount * 0.1 * 4) / 3) * dose3);
		int total3 = outputOf3 + converted4;
		int potProf2 = total3 + extra3 + savedSecondary - cost;
		System.out.println("Profit after converting 4dose: " + potProf2);
	}

	public static void fromClean() {
		int seed = clean * amount;
		System.out.println("\nFrom Clean:");
		System.out.println("Total seed money for " + amount + " herbs:" + seed);

		int extras = (int) (amount * 1.11) - amount;
		int extraVials = extras * vial;
		int SoC = (int) (unf * extras) - extraVials;
		int unfProf = ((unf - (clean + vial)) * amount);
		System.out.println("Profit from making unf:" + (unfProf + SoC));

		int cost = (clean + vial + secondary) * amount;
		int savedSecondary = extras * secondary;
		int extra3 = (int) (dose3 * amount * 0.05);
		int outputOf3 = (int) (dose3 * amount * 0.9);
		int outputOf4 = (int) (dose4 * amount * 0.1);
		int totalPots = extra3 + outputOf3 + outputOf4;
		int potProf = totalPots - cost + savedSecondary;
		System.out.println("Total seed money for " + amount + " potions:" + cost);
		System.out.println("Profit from making potion: " + potProf);
		int converted4 = (int) (((amount * 0.1 * 4) / 3) * dose3);
		int total3 = outputOf3 + converted4;
		int potProf2 = total3 + extra3 + savedSecondary - cost;
		System.out.println("Profit after converting 4dose: " + potProf2);
	}

	public static void fromUnf() {
		System.out.println("\nFrom Unf:");

		int extras = (int) (amount * 1.11) - amount;
		int cost = (unf + secondary) * amount;
		int savedSecondary = extras * secondary;
		int extra3 = (int) (dose3 * amount * 0.05);
		int outputOf3 = (int) (dose3 * amount * 0.9);
		int outputOf4 = (int) (dose4 * amount * 0.1);
		int totalPots = extra3 + outputOf3 + outputOf4;
		int potProf = totalPots - cost + savedSecondary;
		System.out.println("Total seed money for " + amount + " potions:" + cost);
		System.out.println("Profit from making potion: " + potProf);
		int converted4 = (int) (((amount * 0.1 * 4) / 3) * dose3);
		int total3 = outputOf3 + converted4;
		int potProf2 = total3 + extra3 + savedSecondary - cost;
		System.out.println("Profit after converting 4dose: " + potProf2);
	}

	public static void main(String[] args) {
		fromGrimy();
		fromClean();
		fromUnf();
	}

}
