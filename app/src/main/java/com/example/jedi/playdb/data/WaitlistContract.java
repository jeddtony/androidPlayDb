package com.example.jedi.playdb.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jedi on 7/25/2017.
 */

public class WaitlistContract {

    // This is how your code know which content provider to access
    public static final String AUTHORITY = "com.example.jedi.playdb";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_TASKS = "Waitlist";

        private WaitlistContract(){};

    public static class WaitlistEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();
        public static final String TABLE_NAME = "Waitlist";
        public static final String COLUMN_PRIORITY = "Priority";
        public static final String COLUMN_GUEST_NAME = "Guestlist";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
