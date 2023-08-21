package com.amazingtheme.colorcaller;

import org.jsoup.select.Elements;

public class AppInterfaces {

    public interface GetRingtones {
        void getRingtoneElements(Elements scrapedElementsList);
        void getSingleElementAsString(String singleElementAsString);
    }
    public interface AdapterClick {
        void getAdapterPosition(int position);
    }

}
