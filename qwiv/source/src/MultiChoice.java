

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

public class MultiChoice extends Question{
    private String options[];

    public MultiChoice(String prompt, String[] answers, String[] options) {
        super(prompt, answers);
        this.options = options;
    }

    public MultiChoice(String s)
    {
        super();
        this.readFromText(s);
    }

    public void shuffle()
    {
        List<String> list = List.of(options);
        ArrayList<String> arList = new ArrayList<>(list);
        Collections.shuffle(arList);
        options = arList.toArray(this.options);
    }

    @Override
    public void ask(Scanner sc) {
        this.shuffle();
        int attempts = 3;
        System.out.println(prompt);
        for(int i = 0 ; i < options.length ; i++)
            System.out.println( "\t" + Character.toString('A' + i) + "." + options[i]);
        System.out.println("======================================================");
        boolean bValidInput;
        String res = "";
        do {
            bValidInput = false;
            System.out.print("Enter Here / ( a / A ) /> ");
            res = sc.nextLine().toLowerCase();
            bValidInput = res.matches("[a-"+Character.toString(options.length + 'a')+"A-"+Character.toString(options.length + 'A')+"]");
            if(!bValidInput) System.out.println("Input \'" + res + "\' unacceptable; ("+--attempts+") attempts left...");
        } while ( !bValidInput && attempts != 0 );
        if(bValidInput) bValidInput = checkResponse(options[res.charAt(0) - 'a']);
        System.out.printf("%scorrect\n",(bValidInput)?"":"in");
    }

    @Override
    public void readFromText(String s) {
        String toks[] = s.split(Constants.delim,-1);
        this.prompt = toks[0];
        this.answers = new String[]{toks[1]};
        this.options = toks[2].replaceAll("^\"|\"$", "").split(Constants.delim,-1);
    }

    @Override
    public void reset() {
        super.reset();
        this.shuffle();
    }

    @Override
    public String toString() {
        return String.format("[prompt= %-30s|answer= %-10s|response= \"%-20s\"|answered= %b]", this.prompt,Arrays.toString(this.answers),this.response,this.isAnswered);
    }
}