/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Java\\androidworkspace\\WiimoteControllerUsageDemo\\src\\com\\ccpcreations\\android\\WiiUseAndroid\\IWiiControllerPublicServiceCallback.aidl
 */
package com.ccpcreations.android.WiiUseAndroid;
public interface IWiiControllerPublicServiceCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback
{
private static final java.lang.String DESCRIPTOR = "com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback interface,
 * generating a proxy if needed.
 */
public static com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback))) {
return ((com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback)iin);
}
return new com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback.Stub.Proxy(obj);
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
case TRANSACTION_buttonPressed:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.buttonPressed(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_buttonReleased:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.buttonReleased(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_newPeripheralDetected:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.newPeripheralDetected(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_wiimotesDisconnected:
{
data.enforceInterface(DESCRIPTOR);
this.wiimotesDisconnected();
reply.writeNoException();
return true;
}
case TRANSACTION_wiimoteDisconnected:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.wiimoteDisconnected(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_wiimotesConnected:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.wiimotesConnected(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_analogInputStatusChanged:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
double _arg2;
_arg2 = data.readDouble();
this.analogInputStatusChanged(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ccpcreations.android.WiiUseAndroid.IWiiControllerPublicServiceCallback
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
	 * Gets called by the service when a key on any attached controller is pressed.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichButton Index of the key that was pressed. See the key appendix for the list of key indexes.
	 */
public void buttonPressed(int whichWiimote, int whichButton) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(whichWiimote);
_data.writeInt(whichButton);
mRemote.transact(Stub.TRANSACTION_buttonPressed, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Gets called by the service when a key on any attached controller is released.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichButton Index of the key that was released. See the key appendix for the list of key indexes.
	 */
public void buttonReleased(int whichWiimote, int whichButton) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(whichWiimote);
_data.writeInt(whichButton);
mRemote.transact(Stub.TRANSACTION_buttonReleased, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Gets called when there is a change in peripheral configuration with one of the controllers.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichPeripheral Index of the event. Currently supported values are:<br />
	 * 			<ul><li>0 - a peripheral previously connected was just disconnected</li><br />
	 * 			<li>1 - nunchuk was just connected</li><br />
	 * 			<li>2 - classic controller was just connected</li></ul>
	 */
public void newPeripheralDetected(int whichWiimote, int whichPeripheral) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(whichWiimote);
_data.writeInt(whichPeripheral);
mRemote.transact(Stub.TRANSACTION_newPeripheralDetected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Gets called when all the controllers have been disconnected and the engine has been halted.
	 */
public void wiimotesDisconnected() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_wiimotesDisconnected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Gets called when one of the controllers has been disconnected.
	 * @param whichWiimote Index of the controller which was disconnected
	 */
public void wiimoteDisconnected(int whichWiimote) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(whichWiimote);
mRemote.transact(Stub.TRANSACTION_wiimoteDisconnected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Gets called when the connection process has been completed and at least one controller has been connected.
	 * @param numberOfConnectedWiimotes Number of connected controllers.
	 */
public void wiimotesConnected(int numberOfConnectedWiimotes) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(numberOfConnectedWiimotes);
mRemote.transact(Stub.TRANSACTION_wiimotesConnected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
public void analogInputStatusChanged(int whichWiimote, int whichAnalogInput, double newValue) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(whichWiimote);
_data.writeInt(whichAnalogInput);
_data.writeDouble(newValue);
mRemote.transact(Stub.TRANSACTION_analogInputStatusChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_buttonPressed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_buttonReleased = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_newPeripheralDetected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_wiimotesDisconnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_wiimoteDisconnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_wiimotesConnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_analogInputStatusChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
/**
	 * Gets called by the service when a key on any attached controller is pressed.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichButton Index of the key that was pressed. See the key appendix for the list of key indexes.
	 */
public void buttonPressed(int whichWiimote, int whichButton) throws android.os.RemoteException;
/**
	 * Gets called by the service when a key on any attached controller is released.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichButton Index of the key that was released. See the key appendix for the list of key indexes.
	 */
public void buttonReleased(int whichWiimote, int whichButton) throws android.os.RemoteException;
/**
	 * Gets called when there is a change in peripheral configuration with one of the controllers.
	 * @param whichWiimote Index of the controller on which the event occurred
	 * @param whichPeripheral Index of the event. Currently supported values are:<br />
	 * 			<ul><li>0 - a peripheral previously connected was just disconnected</li><br />
	 * 			<li>1 - nunchuk was just connected</li><br />
	 * 			<li>2 - classic controller was just connected</li></ul>
	 */
public void newPeripheralDetected(int whichWiimote, int whichPeripheral) throws android.os.RemoteException;
/**
	 * Gets called when all the controllers have been disconnected and the engine has been halted.
	 */
public void wiimotesDisconnected() throws android.os.RemoteException;
/**
	 * Gets called when one of the controllers has been disconnected.
	 * @param whichWiimote Index of the controller which was disconnected
	 */
public void wiimoteDisconnected(int whichWiimote) throws android.os.RemoteException;
/**
	 * Gets called when the connection process has been completed and at least one controller has been connected.
	 * @param numberOfConnectedWiimotes Number of connected controllers.
	 */
public void wiimotesConnected(int numberOfConnectedWiimotes) throws android.os.RemoteException;
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
public void analogInputStatusChanged(int whichWiimote, int whichAnalogInput, double newValue) throws android.os.RemoteException;
}
