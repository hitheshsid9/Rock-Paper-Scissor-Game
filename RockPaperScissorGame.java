package io.javabrains;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Player1 implements Runnable {
	static int randomNumber = 0;
	Random random = new Random();

	@Override
	public void run() {
		generateRandomNumber();
	}

	protected int generateRandomNumber() {
		randomNumber = random.nextInt(3);
		return randomNumber;
	}

}

class Player2 implements Runnable {

	static int randomNumber = 0;
	Random random = new Random();

	@Override
	public void run() {
		generateRandomNumber();
	}

	public int generateRandomNumber() {
		randomNumber = random.nextInt(3);
		return randomNumber;
	}

}

public class RockPaperScissorGame {

	/**
	 * Rock > Scissors ; Scissors > Paper ; Paper > Rock
	 */

	static String node[] = { "rock", "paper", "scissors" };

	public static void main(String[] args) throws InterruptedException {

		int counterP1 = 0;
		int counterP2 = 0;
		List<String> winnerList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			Thread t1 = new Thread(new Play1());
			Thread t2 = new Thread(new Play2());

			t1.start();
			t2.start();

			t1.join();
			t2.join();

			int p1Case = Player1.randomNumber;
			int p2Case = Player2.randomNumber;

			String winner = checkWhoWin(p1Case, p2Case);
			winnerList.add(winner);
		}
		System.out.println(winnerList);

		for (String s : winnerList) {
			if ("p1".equals(s)) {
				counterP1++;
			} else if ("p2".equals(s)) {
				counterP2++;
			}
		}
		if (counterP1 > counterP2) {
			System.out.println("p1 wins");
		} else {
			System.out.println("p2 wins");
		}

	}

	private static String checkWhoWin(int p1Case, int p2Case) {

		String winner = "";
		String s1 = node[p1Case];
		String s2 = node[p2Case];

		if (s1.equals(s2)) {
			winner = "draw";
			return winner;
		}
		if (s1.equals("rock") && s2.equals("scissors")) {
			winner = "p1";
		} else if (s1.equals("scissors") && s2.equals("rock")) {
			winner = "p2";
		}
		if (s1.equals("scissors") && s2.equals("paper")) {
			winner = "p1";
		} else if (s1.equals("paper") && s2.equals("scissors")) {
			winner = "p2";
		}
		if (s1.equals("paper") && s2.equals("rock")) {
			winner = "p1";
		} else if (s1.equals("rock") && s2.equals("paper")) {
			winner = "p2";
		}
		return winner;
	}
}
