package launcher.abdelouahed.com.launcher.tools;

import launcher.abdelouahed.com.launcher.models.Pack;

/**
 * Created by geek on 20/09/17.
 */

public class SortApps {

    public static void sort(Pack[] packs) {
        for (int i = 0; i < packs.length - 1; i++) {
            for (int j = i + 1; j < packs.length; j++) {
                if (packs[i].getLabel().compareTo(packs[j].getLabel()) > 0) {
                    Pack pack = packs[i];
                    packs[i] = packs[j];
                    packs[j] = pack;
                }
            }
        }
    }
}
