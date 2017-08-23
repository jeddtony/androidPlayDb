package com.example.jedi.playdb;

import android.provider.BaseColumns;

/**
 * Created by jedi on 7/25/2017.
 */

public class ContractClassUnitTest {
    private ContractClassUnitTest(){};

    public static class WaitlistEntry implements BaseColumns {
        public static final String TABLE_NAME = "Waitlist";
        public static final String COLUMN_GUEST_NAME = "Guestlist";
        public static final String COLUMN_PARTY_SIZE = "partysize";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
