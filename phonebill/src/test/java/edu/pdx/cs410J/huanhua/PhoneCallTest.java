package edu.pdx.cs410J.huanhua;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneCallTest {

  @Test(expected = UnsupportedOperationException.class)
  public void getStartTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall();
   call.getStartTimeString();
  }

  @Test
  public void initiallyAllPhoneCallsHaveTheSameCallee() {
	  
	  String callee = "callee";
	  
	  PhoneCall call = new PhoneCall(callee);
	  assertThat(call.getCallee(), containsString(callee));
  }

  
  @Test
  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getStartTime(), is(nullValue()));
  }
  
}
