/*
 * Copyright 2011 Cvetko Pirš. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 * 
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY CVETKO PIRŠ "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

/*
 ***************************************************************************
 ********************* I M P O R T A N T   N O T I C E *********************
 ***************************************************************************
 *
 * DO NOT CHANGE THIS FILE!    This file defines the direct interface of the
 * WiimoteController app. Changing it will most probably break the interface
 * and your app will not work correctly!
 *  
 */

package com.ccpcreations.android.WiiUseAndroid;
import com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback;

interface IWiiControllerPublicService {
	/**
	 * Checks the version of the interface. <b>You should call this function prior to doing anything else</b>, to
	 * avoid interfacing with an incompatible interface. If this function returns false, <b>you should not
	 * use the interface</b> and should instead prompt the user to update the WiimoteController app.
	 * @param whatVersionIsSupposedToBe Version of the interface that your app expects <b>Use 1</b> 
	 * @return true if the expected version is compatible with the installed version of WiimoteController, false otherwise
	 */
	boolean checkVersion(int whatVersionIsSupposedToBe);

	/**
	 * Registers the callback service. This service is made and exposed by your app. It can be used to make
	 * the WiimoteController app notify your app about certain events such as button presses. However registring the
	 * stub is not required. The interface can also be used in the polling mode by using the
	 * getNumberOfConnectedWiimotes, getButtonStatus, getPeripheralId and getAnalogInputStatus functions.
	 * @param me The implementation of the WiiControllerPublicService's stub
	 */
	void registerToCallback(IWiiControllerPublicServiceCallback me);
	
	/**
	 * Unregisters the callback service. Your service stub will no longer be notified of events.
	 * @param me The implementation of the WiiControllerPublicService's stub
	 */
	void unregisterFromCallback(IWiiControllerPublicServiceCallback me);
	
	/**
	 * Displays the WiimoteController's stripped down main activity. In particular, the "SelectWiiControllerIME" button is hidden.
	 * This activity should be offered to the user to connect controllers.<br /><br />
	 * This function starts an Activity with the following Intent:
	 * <ul><li>action: com.ccpcreations.android.WiiUseAndroid.WITHOUTIME</li><br />
	 * <li>component: com.ccpcreations.android.WiiUseAndroid/.WiiController</li></ul>
	 */
	void displayConnectActivity();
	
	//These methods get and set the ability of the app to acquire analog input. By default this is disabled.
	/**
	 * Sets the ability of your app to automatically acquire analog input. If this ability is enabled then your app's
	 * registered WiiControllerPublicServiceCallback stubs's analogInputStatusChanged(int, int, double) function will
	 * get called whenever a change in the status of any analog input on any of the connected controllers
	 * is detected. Enabling this ability might generate a lot of events when analog input is actively used and
	 * thus might slow down your app or even the entire system on low powered Android devices.<br>
	 * Note that the getAnalogInputStatus(int, int) function of this interface works regardless of whether this
	 * ability is enabled or not.
	 * @param enabled true if you want to get the ability to acquire analog input, false otherwise
	 */
	void setAnalogFeedback(boolean enabled);
	
	/**
	 * Gets the status of the ability to automatically acquire analog input.
	 * @return true if the ability to acquire analog input is enabled, false otherwise.
	 */
	boolean getAnalogFeedback();
	
	/**
	 * Gets the number of controllers currently connected. 
	 * @return the number of connected controllers.
	 */
	int getNumberOfConnectedWiimotes();
	
	/**
	 * Get the current status of the single key on a single controller.
	 * @param wiimote Index of the controller which has the button. Valid values are 0, 1, 2 and 3.
	 * @param button Index of the key. See the key appendix for the list of key indexes.
	 * @return true if the button is pressed, false otherwise.
	 */
	boolean getButtonStatus(int wiimote, int button);
	
	/**
	 * Gets the index of the current peripheral (if any) attached to a single controller.
	 * @param wiimote Index of the controller. Valid values are 0,1,2 and 3.
	 * @return Index of the peripheral. Currently supported values are:<br />
	 * 			<ul><li>0 - no peripheral</li><br />
	 * 			<li>1 - nunchuk</li><br />
	 * 			<li>2 - classic controller</li></ul>
	 */
	int getPeripheralId(int wiimote);
	
	/**
	 * Gets the status of one of the analog inputs on a single controller. 
	 * @param wiimote Index of the controller. Valid values are 0,1,2 and 3
	 * @param analogId Index of the analog input. Currently supported values are:<br />
	 * 			<ul><li>0 - horizontal axis of the nunchuk's analog stick or the classic controller's left analog stick</li><br />
	 * 			<li>1 - vertical axis of the nunchuk's analog stick or the classic controller's left analog stick</li><br />
	 * 			<li>2 - horizontal axis of the classic controller's right analog stick</li><br />
	 * 			<li>3 - vertical axis of the classic controller's right analog stick</li></ul>
	 * @return the value of the analog input in the range [-1, 1].
	 */
	double getAnalogInputStatus(int wiimote, int analogId);
}