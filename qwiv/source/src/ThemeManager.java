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
import java.awt.Color;

public class ThemeManager {

    /**
     * Checks if theme is available for use.
     * <p>
     * 
     * @param id index of color.
     * @return true if the theme is unused; otherwise false.
     */
    public static boolean isIndexAvailable(int id) throws ArrayIndexOutOfBoundsException {
        return !( keyList[id] );
    };

    /**
     * Checks if theme index is available the uses it.
     * @param id
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public static boolean SetIndex(int id) throws ArrayIndexOutOfBoundsException
    {
        if(isIndexAvailable(id))
        {
            keyList[id] = true;
            return true;
        }
        return false;
    }

    /**
     * 
     * @param id
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void FreeIndex(int id) throws ArrayIndexOutOfBoundsException
    {
        keyList[id] = false;
    }

    public static int GetAvailableIndex()
    {
        for(int i = 0 ; i < keyList.length ; i++)
            if( !keyList[i] )
                return i;
        return -1;
    }

    public static int CheckIndex(int id) throws IllegalArgumentException
    {
        if( !isIndexAvailable(id) ) throw new IllegalArgumentException("Theme is already in use");
        return id;
    }

    

    public static Color[] getColorList() {
        return colorList;
    }

    public static String[] getTitleList() {
        return titleList;
    }



    private static boolean keyList[] = new boolean[6];
    private static Color colorList[] = new Color[]{
        Color.red,
        Color.orange,
        Color.yellow,
        Color.blue,
        Color.green,
        Color.magenta
    };

    private static String titleList[] = new String[]{
        "red",
        "orange",
        "yellow",
        "blue",
        "green",
        "magenta"
    };

    public static final int NO_THEME = 59345;
}