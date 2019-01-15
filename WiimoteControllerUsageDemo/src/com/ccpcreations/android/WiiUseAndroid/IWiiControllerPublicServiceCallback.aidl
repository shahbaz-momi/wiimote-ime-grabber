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
 * DO NOT CHANGE THIS FILE!    This file defines a callback interface to the
 * WiimoteController app. Changing it will most probably break the interface
 * and your app will not work correctly!
 *  
 */

package com.ccpcreations.android.WiiUseAndroid;

interface IWiiControllerPublicServiceCallback {
	/**
	 * Gets called by the service when a key on any attached controller is pressed.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichButton Index of the key that was pressed. See the key appendix for the list of key indexes.
	 */
	void buttonPressed(int whichWiimote, int whichButton);
	
	/**
	 * Gets called by the service when a key on any attached controller is released.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichButton Index of the key that was released. See the key appendix for the list of key indexes.
	 */
	void buttonReleased(int whichWiimote, int whichButton);
	
	/**
	 * Gets called when there is a change in peripheral configuration with one of the controllers.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichPeripheral Index of the event. Currently supported values are:<br />
	 * 			<ul><li>0 - a peripheral previously connected was just disconnected</li><br />
	 * 			<li>1 - nunchuk was just connected</li><br />
	 * 			<li>2 - classic controller was just connected</li></ul>
	 */
	void newPeripheralDetected(int whichWiimote, int whichPeripheral);
	
	/**
	 * Gets called when all the controllers have been disconnected and the engine has been halted.
	 */
	void wiimotesDisconnected();
	
	/**
	 * Gets called when one of the controllers has been disconnected.
	 * @param whichWiimote Index of the controller which was disconnected
	 */
	void wiimoteDisconnected(int whichWiimote);
	
	/**
	 * Gets called when the connection process has been completed and at least one controller has been connected.
	 * @param numberOfConnectedWiimotes Number of connected controllers.
	 */
	void wiimotesConnected(int numberOfConnectedWiimotes);
	
	/**
	 * Gets called when one of the analog values on one of the controllers changes.
	 * This is only ever called if analog reporting is enabled (see setAnalogFeedback(boolean) of IWiiControllerPublicService).
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichAnalogInput Index of the analog input. Currently supported values are:<br />
	 * 			<ul><li>0 - horizontal axis of the nunchuk's analog stick or the classic controller's left analog stick</li><br />
	 * 			<li>1 - vertical axis of the nunchuk's analog stick or the classic controller's left analog stick</li><br />
	 * 			<li>2 - horizontal axis of the classic controller's right analog stick</li><br />
	 * 			<li>3 - vertical axis of the classic controller's right analog stick</li></ul>
	 * @param newValue New value of the analog input in the range [-1,1]
	 */
	void analogInputStatusChanged(int whichWiimote, int whichAnalogInput, double newValue);
}