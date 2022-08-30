

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

// Copyright 2022 Kyle King
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class App{
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<MultiChoice> questSuite = Quiz.constructFromFile(new File("quiz.csv"));
        Player playSuite[] = new Player[]{
            new Player(),
            new Player()
        };
        
        boolean bInitalized = false;
        Consumer<Player> playerEditor = (p) -> {
            System.out.print("Enter name: ");
            p.setName(sc.nextLine());
        };

        do
        {
            if(!bInitalized)
            {
                System.out.println("Editing Player 1...");
                playerEditor.accept(playSuite[0]);
                System.out.println("Editing Player 2...");
                playerEditor.accept(playSuite[1]);
                bInitalized = true;
            }

            Collections.shuffle(questSuite);
            MultiChoice q = null;
            Player p = null;
            int curr = new Random().nextInt(2);
            for(int ln = 0 ; ln < questSuite.size() ; ln++ )
            {
                q = questSuite.get(ln);
                p = playSuite[++curr%2];
                System.out.print( "Question for player -> "+p.getName()+"\n"+ (ln+1) + ". ");
                q.ask(sc);
                if(q.isAnswered) ++p.score;
            }
            switch(playSuite[0].compareTo(playSuite[1])) {
                case 1 -> {
                    System.out.println(playSuite[1].getName() + " won");
                }

                case 0 -> {
                    System.out.println("draw");
                }

                case -1 -> {
                    System.out.println(playSuite[0].getName() + " won");
                }
            }

            questSuite.forEach((m)->{
                System.out.println(m.toString());
                m.reset();
            });

        }while(new Replay() {
            public boolean replay(Scanner sc) {
                System.out.println("Do you want to replay? ( y / n ): ");
                    return (sc.nextLine().toLowerCase().charAt(0) == 'y');
            }
        }.replay(sc));
    }
    private interface Replay{
        boolean replay(Scanner sc);
    }
}