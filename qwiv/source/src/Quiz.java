

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

public class Quiz {
    
    public static ArrayList<MultiChoice> constructFromFile(File csv)
    {
        ArrayList<MultiChoice> arrayList = new ArrayList<>();
        try {
            Scanner fscan = new Scanner(csv);
            while(fscan.hasNextLine())
                arrayList.add(new MultiChoice(fscan.nextLine()));
            fscan.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
            System.exit(0);
        }
        return arrayList;
    }
}
