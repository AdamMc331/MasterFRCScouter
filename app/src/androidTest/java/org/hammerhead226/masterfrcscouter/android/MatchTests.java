package org.hammerhead226.masterfrcscouter.android;

import org.hammerhead226.masterfrcscouter.model.Stronghold.Stronghold;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by AdiSai on 1/8/16.
 */
@RunWith(JUnit4.class)
public class MatchTests {
    private Stronghold stronghold;

    @Before public void initFIRST() { stronghold = new Stronghold(1, 226); }

    @Test public void testCreation() { assertEquals(226, stronghold.teamNumber); }
}
