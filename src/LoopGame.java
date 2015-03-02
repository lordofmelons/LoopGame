

import java.util.Scanner;

public class LoopGame {

    public static void main(String[] args) {
        
        
        int[] intArray = new int[5];
              
        for(int i = 0; i < intArray.length; i++)
        {
            intArray[i] = i++;
        }
        
       // int[] intArray = new int[]{1,2,3,4,5};
        String[] strArray = new String[]{"First", "Second"};
        for(int i = 0; i < strArray.length; i++)
        {
            System.out.println(strArray[i]);
        }
        
        Scanner in = new Scanner(System.in);
        String command;
        int playerX = 0;
        int enemy1X = 10;
        int enemy2X = -15;
        int enemy1Health = 5;
        int enemy2Health = 7;
        int playerHealth = 10;
        int playerDamage = 0;
        int playerRange = 0;
        int numOfPotions = 1;
        boolean isWeaponPicked = false;
        boolean isPlayerDead = false;
        boolean isEnemy1Alive = true;
        boolean isEnemy2Alive = true;
        
        while(!isWeaponPicked)
        {
        	System.out.println("Pick your weapon: sword or bow");
        	System.out.println("Enter 's' for sword and 'b' for bow");
        	command = in.nextLine();
        	switch(command)
        	{
        	case "s":
        		playerDamage = 2;
        		playerRange = 1;
        		isWeaponPicked = true;
        		break;
        	case "b":
        		playerDamage = 1;
        		playerRange = 3;
        		isWeaponPicked = true;
        		break;
        	}
        	
        }
        
        while (!isPlayerDead && (isEnemy1Alive || isEnemy2Alive)) 
        {
            // Next turn message
            System.out.println("Next move; Please enter command...");
            command = in.nextLine();

            // move player
            switch (command) 
            {
                case "<":
                    playerX -= 2; // move left
                    break;
                case ">":
                    playerX += 2; // move right
                    break;
                case "a":
                    if(Math.abs(playerX - enemy1X) <= playerRange && Math.abs(playerX - enemy2X) <= playerX && isEnemy1Alive && isEnemy2Alive)
                    {
                    		System.out.println("Both enemies were hit");
                        	enemy1Health -= playerDamage;
                        	enemy2Health -= playerDamage;
                    }
                    else if(Math.abs(playerX - enemy1X) <= playerRange && isEnemy1Alive)
                    {
                    		System.out.println("Enemy1 was hit");
                    		enemy1Health -= playerDamage;
                    }
                    else if(Math.abs(playerX - enemy2X) <= playerRange && isEnemy2Alive)
                    {
                    		System.out.println("Enemy2 was hit");
                    		enemy2Health -= playerDamage;
                    }
                    break;
                case "h":
                	if(numOfPotions > 0)
                	{
                		System.out.println("You drank potion! You regained health!");
                		playerHealth += 3;
                		numOfPotions--;
                	}
                	else
                	{
                		System.out.println("Out of potions");
                	}
                	break;
            }
            System.out.println("Player stats. Position: " + playerX + " Health: " + playerHealth);

            // Do enemy stuff
            if (enemy1Health > 0) 
            {
                // enemy chases player
                enemy1X += Math.signum(playerX - enemy1X);

                // Attack player
                if (enemy1X == playerX) 
                {
                    System.out.println("Enemy attacked player!");
                    playerHealth -= 1;
                }
                System.out.println("Enemy1 stats. Position: " + enemy1X + " Health: " + enemy1Health);
            }
            else
            {
            	System.out.println("Enemy1 is dead!");
            	isEnemy1Alive = false;
            }
           //Second Enemy
            if (enemy2Health > 0) 
            {
                // enemy chases player
                enemy2X += Math.signum(playerX - enemy2X);

                // Attack player
                if (enemy2X == playerX) 
                {
                    System.out.println("Enemy attacked player!");
                    playerHealth -= 1;
                }
                System.out.println("Enemy2 stats. Position: " + enemy2X + " Health: " + enemy2Health);
            }
            else
            {
            	System.out.println("Enemy2 is dead!");
            	isEnemy2Alive = false;
            }
           

            // See if player loses
            if (playerHealth <= 0) 
            {
                System.out.println("Oh noes! Player died!");
            }
            if(!isEnemy1Alive && !isEnemy2Alive)
            {
            	System.out.println("You win!");
            }
        }
    }
}