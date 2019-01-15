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

package com.example.wiimotecontrollerusagedemo;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService;

public class WiimoteController {
	private static final Intent wcIntent=new Intent(IWiiControllerPublicService.class.getName());

	/**
	 * Checks whether the WiimoteController with the correctly exposed service is installed.
	 * @param context Application context
	 * @return true if WiimoteController is correctly installed, false otherwise. If this function returns false,
	 * the user should be asked to install or update the app (see wiimoteControllerOnMarket()).
	 */
	public static boolean isWiimoteControllerInstalled(Context context) {
		final PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentServices(wcIntent, 0);
		return list.size()>0;
	}

	/**
	 * A convenience method to display the WiimoteController's Android Market page. This function can be used to
	 * direct the user to the Android Market after failing the WiimoteController app check (see isWiimoteControllerInstalled())
	 * and asking the user to install or update the WiimoteController app.
	 * @param context Application context
	 */
	public static void wiimoteControllerOnMarket(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ccpcreations.android.WiiUseAndroid"));
		context.startActivity(intent);		
	}
	
	/**
	 * Attempts the connection to the WiimoteController public service. Note that you should check if the intent exists
	 * (see isWiimoteControllerInstalled()) prior to calling this function.
	 * @param context Application context
	 * @param wcServiceConnection ServiceConnection object that will handle the service connection
	 * @return true on success (wcServiceConnection will be notified shortly after), false otherwise.
	 */
	public static boolean connectToWCService(Context context, ServiceConnection wcServiceConnection) {
		return context.bindService(wcIntent, wcServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	
}
