/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Java\\androidworkspace\\WiimoteControllerUsageDemo\\src\\com\\ccpcreations\\android\\WiiUseAndroid\\IWiiControllerPublicService.aidl
 */
package com.ccpcreations.android.WiiUseAndroid;
public interface IWiiControllerPublicService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService
{
private static final java.lang.String DESCRIPTOR = "com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService interface,
 * generating a proxy if needed.
 */
public static com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService))) {
return ((com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService)iin);
}
return new com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_checkVersion:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.checkVersion(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_registerToCallback:
{
data.enforceInterface(DESCRIPTOR);
com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback _arg0;
_arg0 = com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback.Stub.asInterface(data.readStrongBinder());
this.registerToCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterFromCallback:
{
data.enforceInterface(DESCRIPTOR);
com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback _arg0;
_arg0 = com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterFromCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_displayConnectActivity:
{
data.enforceInterface(DESCRIPTOR);
this.displayConnectActivity();
reply.writeNoException();
return true;
}
case TRANSACTION_setAnalogFeedback:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setAnalogFeedback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getAnalogFeedback:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.getAnalogFeedback();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getNumberOfConnectedWiimotes:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getNumberOfConnectedWiimotes();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getButtonStatus:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.getButtonStatus(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getPeripheralId:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.getPeripheralId(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getAnalogInputStatus:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
double _result = this.getAnalogInputStatus(_arg0, _arg1);
reply.writeNoException();
reply.writeDouble(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
	 * Checks the version of the interface. <b>You should call this function prior to doing anything else</b>, to
	 * avoid interfacing with an incompatible interface. If this function returns false, <b>you should not
	 * use the interface</b> and should instead prompt the user to update the WiimoteController app.
	 * @param whatVersionIsSupposedToBe Version of the interface that your app expects <b>Use 1</b> 
	 * @return true if the expected version is compatible with the installed version of WiimoteController, false otherwise
	 */
public boolean checkVersion(int whatVersionIsSupposedToBe) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(whatVersionIsSupposedToBe);
mRemote.transact(Stub.TRANSACTION_checkVersion, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * Registers the callback service. This service is made and exposed by your app. It can be used to make
	 * the WiimoteController app notify your app about certain events such as button presses. However registring the
	 * stub is not required. The interface can also be used in the polling mode by using the
	 * getNumberOfConnectedWiimotes, getButtonStatus, getPeripheralId and getAnalogInputStatus functions.
	 * @param me The implementation of the WiiControllerPublicService's stub
	 */
public void registerToCallback(com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback me) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((me!=null))?(me.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerToCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Unregisters the callback service. Your service stub will no longer be notified of events.
	 * @param me The implementation of the WiiControllerPublicService's stub
	 */
public void unregisterFromCallback(com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback me) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((me!=null))?(me.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterFromCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Displays the WiimoteController's stripped down main activity. In particular, the "SelectWiiControllerIME" button is hidden.
	 * This activity should be offered to the user to connect controllers.<br /><br />
	 * This function starts an Activity with the following Intent:
	 * <ul><li>action: com.ccpcreations.android.WiiUseAndroid.WITHOUTIME</li><br />
	 * <li>component: com.ccpcreations.android.WiiUseAndroid/.WiiController</li></ul>
	 */
public void displayConnectActivity() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_displayConnectActivity, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
public void setAnalogFeedback(boolean enabled) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((enabled)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setAnalogFeedback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Gets the status of the ability to automatically acquire analog input.
	 * @return true if the ability to acquire analog input is enabled, false otherwise.
	 */
public boolean getAnalogFeedback() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAnalogFeedback, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * Gets the number of controllers currently connected. 
	 * @return the number of connected controllers.
	 */
public int getNumberOfConnectedWiimotes() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getNumberOfConnectedWiimotes, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * Get the current status of the single key on a single controller.
	 * @param wiimote Index of the controller which has the button. Valid values are 0, 1, 2 and 3.
	 * @param button Index of the key. See the key appendix for the list of key indexes.
	 * @return true if the button is pressed, false otherwise.
	 */
public boolean getButtonStatus(int wiimote, int button) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(wiimote);
_data.writeInt(button);
mRemote.transact(Stub.TRANSACTION_getButtonStatus, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * Gets the index of the current peripheral (if any) attached to a single controller.
	 * @param wiimote Index of the controller. Valid values are 0,1,2 and 3.
	 * @return Index of the peripheral. Currently supported values are:<br />
	 * 			<ul><li>0 - no peripheral</li><br />
	 * 			<li>1 - nunchuk</li><br />
	 * 			<li>2 - classic controller</li></ul>
	 */
public int getPeripheralId(int wiimote) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(wiimote);
mRemote.transact(Stub.TRANSACTION_getPeripheralId, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
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
public double getAnalogInputStatus(int wiimote, int analogId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
double _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(wiimote);
_data.writeInt(analogId);
mRemote.transact(Stub.TRANSACTION_getAnalogInputStatus, _data, _reply, 0);
_reply.readException();
_result = _reply.readDouble();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_checkVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerToCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unregisterFromCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_displayConnectActivity = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setAnalogFeedback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getAnalogFeedback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getNumberOfConnectedWiimotes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getButtonStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getPeripheralId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getAnalogInputStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
}
/**
	 * Checks the version of the interface. <b>You should call this function prior to doing anything else</b>, to
	 * avoid interfacing with an incompatible interface. If this function returns false, <b>you should not
	 * use the interface</b> and should instead prompt the user to update the WiimoteController app.
	 * @param whatVersionIsSupposedToBe Version of the interface that your app expects <b>Use 1</b> 
	 * @return true if the expected version is compatible with the installed version of WiimoteController, false otherwise
	 */
public boolean checkVersion(int whatVersionIsSupposedToBe) throws android.os.RemoteException;
/**
	 * Registers the callback service. This service is made and exposed by your app. It can be used to make
	 * the WiimoteController app notify your app about certain events such as button presses. However registring the
	 * stub is not required. The interface can also be used in the polling mode by using the
	 * getNumberOfConnectedWiimotes, getButtonStatus, getPeripheralId and getAnalogInputStatus functions.
	 * @param me The implementation of the WiiControllerPublicService's stub
	 */
public void registerToCallback(com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback me) throws android.os.RemoteException;
/**
	 * Unregisters the callback service. Your service stub will no longer be notified of events.
	 * @param me The implementation of the WiiControllerPublicService's stub
	 */
public void unregisterFromCallback(com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback me) throws android.os.RemoteException;
/**
	 * Displays the WiimoteController's stripped down main activity. In particular, the "SelectWiiControllerIME" button is hidden.
	 * This activity should be offered to the user to connect controllers.<br /><br />
	 * This function starts an Activity with the following Intent:
	 * <ul><li>action: com.ccpcreations.android.WiiUseAndroid.WITHOUTIME</li><br />
	 * <li>component: com.ccpcreations.android.WiiUseAndroid/.WiiController</li></ul>
	 */
public void displayConnectActivity() throws android.os.RemoteException;
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
public void setAnalogFeedback(boolean enabled) throws android.os.RemoteException;
/**
	 * Gets the status of the ability to automatically acquire analog input.
	 * @return true if the ability to acquire analog input is enabled, false otherwise.
	 */
public boolean getAnalogFeedback() throws android.os.RemoteException;
/**
	 * Gets the number of controllers currently connected. 
	 * @return the number of connected controllers.
	 */
public int getNumberOfConnectedWiimotes() throws android.os.RemoteException;
/**
	 * Get the current status of the single key on a single controller.
	 * @param wiimote Index of the controller which has the button. Valid values are 0, 1, 2 and 3.
	 * @param button Index of the key. See the key appendix for the list of key indexes.
	 * @return true if the button is pressed, false otherwise.
	 */
public boolean getButtonStatus(int wiimote, int button) throws android.os.RemoteException;
/**
	 * Gets the index of the current peripheral (if any) attached to a single controller.
	 * @param wiimote Index of the controller. Valid values are 0,1,2 and 3.
	 * @return Index of the peripheral. Currently supported values are:<br />
	 * 			<ul><li>0 - no peripheral</li><br />
	 * 			<li>1 - nunchuk</li><br />
	 * 			<li>2 - classic controller</li></ul>
	 */
public int getPeripheralId(int wiimote) throws android.os.RemoteException;
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
public double getAnalogInputStatus(int wiimote, int analogId) throws android.os.RemoteException;
}
