/*
 * Author: Shelby Matlock 
 */

import java.util.Random;
import java.util.Scanner;

public class aaPractice 
{
	public static void main(String[] args)
	{
	String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" };
	
	String[] FULL_NAMES = 
		{ "alanine","arginine", "asparagine", 
		"aspartic acid", "cysteine",
		"glutamine",  "glutamic acid",
		"glycine" ,"histidine","isoleucine",
		"leucine",  "lysine", "methionine", 
		"phenylalanine", "proline", 
		"serine","threonine","tryptophan", 
		"tyrosine", "valine"};
	
	Random random = new Random();
	Scanner s = new Scanner(System.in);
	
//	User selects time or # of questions 
	System.out.println("******************~~~~~******************\n"
					+ "Let's study!\nType quit to exit study session.\n"
					+ "******************~~~~~******************\n\n"
					+ "Study based on time (time) or number of questions (questions)?\n");
	String t_or_q = System.console().readLine().toUpperCase();
	
//	Permit user to enter how long they'd like to study
	float studyTime = 0;
	float max_q = 0;
	if(t_or_q.equals("TIME"))
		{
			System.out.println("How long would you like to study? (in seconds)");
			studyTime = s.nextFloat();
		}
	if(t_or_q.equals("QUESTIONS"))
		{
			System.out.println("How many questions would you like to answer?");
			max_q = s.nextFloat();
		}
	
	int aa = random.nextInt(20);
	String quiz_question = FULL_NAMES[aa];
	System.out.println("What is the abbreviation for " + quiz_question + "?");
	String userInput = System.console().readLine().toUpperCase();
	String quiz_answer = SHORT_NAMES[aa];
	
	float count = 0;
	float wrong = 0;
	long start = System.currentTimeMillis();
	float passedTime = 0;
	while(passedTime <= studyTime && t_or_q.equals("TIME"))
		{
			
			if(userInput.equals("QUIT"))
				{
					System.exit(0);
				}
			if(quiz_answer.equals(userInput))
				{
					count ++;
					passedTime = (System.currentTimeMillis() - start)/1000f;
					System.out.println("Remaining Time: " + passedTime);
				}
			else
				{
					passedTime = (System.currentTimeMillis() - start)/1000f;
					System.out.println("Remaining Time: " + passedTime);
					wrong ++;
					System.out.println("Wrong. Answer: " + userInput + " " + quiz_answer);
				}
//			System.out.println(count + " " + wrong);
			aa = random.nextInt(20);
			quiz_question = FULL_NAMES[aa];
			System.out.println("What is the abbreviation for " + quiz_question + "?");
			userInput = System.console().readLine().toUpperCase();
			quiz_answer = SHORT_NAMES[aa];
		
		}

	if(t_or_q.equals("TIME"))
		{
			float score = (count/(count + wrong))*100;
			System.out.println("Score: " + score);
		}

	count = 0;
	float right = 0;
	while(count < (max_q-1))
	{
		if(userInput.equals("QUIT"))
			{
				System.exit(0);
			}
		if(quiz_answer.equals(userInput))
			{
				count ++;
				right ++;
			}
		else
			{
				System.out.println("Wrong.");
				count ++;
			}
		aa = random.nextInt(20);
		quiz_question = FULL_NAMES[aa];
		System.out.println("What is the abbreviation for " + quiz_question + "?");
		userInput = System.console().readLine().toUpperCase();
		quiz_answer = SHORT_NAMES[aa];
	
	}
	
	if(t_or_q.equals("QUESTIONS"))
		{
		float q_score = (count/max_q)*100;
		System.out.println("Score: " + q_score);
		}
	}
	
}
