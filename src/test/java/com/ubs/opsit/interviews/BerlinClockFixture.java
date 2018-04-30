package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.exceptions.InvalidTimeException;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class BerlinClockFixture {

    private TimeConverter berlinClock = new TimeConverterImpl();
    private String theTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
        assertThat(berlinClock.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);
    }

    // custom test cases ====>>>>

    /**
     * Test for Invalid time format
     */
    @Test(expected = InvalidTimeException.class)
    public void testInvalidTimeFormat() {
        String invalidTimeStr = "12:12";
        assertThat(berlinClock.convertTime(invalidTimeStr)).hasSameClassAs(InvalidTimeException.class);
    }

    /**
     * Test for not a time value input
     */
    @Test(expected = InvalidTimeException.class)
    public void testNotATimeValue() {
        String invalidTimeStr = "abcd";
        assertThat(berlinClock.convertTime(invalidTimeStr)).hasSameClassAs(InvalidTimeException.class);
    }

    /**
     * Test for blank time input value
     */
    @Test(expected = InvalidTimeException.class)
    public void testBlankTimeValue() {
        String invalidTimeStr = "";
        assertThat(berlinClock.convertTime(invalidTimeStr)).hasSameClassAs(InvalidTimeException.class);
    }

    /**
     * Test for invalid time input
     */
    @Test(expected = InvalidTimeException.class)
    public void testInvalidTime() {
        String invalidTimeStr = "22:62:60";
        assertThat(berlinClock.convertTime(invalidTimeStr)).hasSameClassAs(InvalidTimeException.class);
    }

    /**
     * Test for null time input
     */
    @Test(expected = InvalidTimeException.class)
    public void testNullTimeValue() {
        String invalidTimeStr = null;
        assertThat(berlinClock.convertTime(invalidTimeStr)).hasSameClassAs(InvalidTimeException.class);
    }

    /**
     * Test for time input with spaces
     */
    @Test
    public void testTimeValueWithSpaces() {
        String invalidTimeStr = " 00:00:00 ";
        String expetcedValue = "Y\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO";
        assertThat(berlinClock.convertTime(invalidTimeStr)).isEqualTo(expetcedValue);
    }

}
