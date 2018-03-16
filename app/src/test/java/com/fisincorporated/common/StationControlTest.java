package com.fisincorporated.common;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// A nice tutorial on testing is at http://www.vogella.com/tutorials/JUnit/article.html
public class StationControlTest {

    IStationModel iStationModel;

    @Before
    public void createStation(){
        iStationModel = StationModel.getIStationModel();
    }

    @Test
    @Category(NonExceptionTest.class)
    public void stationControlsShouldNotBeNull(){
        assertNotNull("StationControls should not be null", iStationModel.getStationControls());
    }
    @Test
    @Category(NonExceptionTest.class)
    public void stationSwitch0ShouldBeTrue(){
        iStationModel.setStationSwitchValue(0, true);
        assertEquals("Switch 0 should be true", iStationModel.getStationControls().get(0).isOnOff(), true);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    @Category(ExceptionTest.class)
    public void throwsIndexOutOfBoundsExceptionIfIndexToLarge() {
        exception.expect(IndexOutOfBoundsException.class);
        int i = iStationModel.getStationControls().size();
        iStationModel.setStationSwitchValue(i, true);
    }

}
