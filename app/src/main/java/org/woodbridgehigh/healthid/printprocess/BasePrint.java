/**
 * BasePrint for printing
 *
 * @author Brother Industries, Ltd.
 * @version 2.2
 */

package org.woodbridgehigh.healthid.printprocess;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.brother.ptouch.sdk.LabelInfo;
import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.brother.ptouch.sdk.PrinterInfo.ErrorCode;
import com.brother.ptouch.sdk.PrinterInfo.Model;
import com.brother.ptouch.sdk.PrinterStatus;

import org.woodbridgehigh.healthid.R;


@SuppressWarnings("ALL")
public abstract class BasePrint {

    static Printer mPrinter;
    static boolean mCancel;
    final MsgHandle mHandle;
    final MsgDialog mDialog;
    private final SharedPreferences sharedPreferences;
    private final Context mContext;
    PrinterStatus mPrintResult;
    private String customSetting;
    private PrinterInfo mPrinterInfo;

    BasePrint(Context context, MsgHandle handle, MsgDialog dialog) {

        mContext = context;
        mDialog = dialog;
        mHandle = handle;
        mDialog.setHandle(mHandle);
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        mCancel = false;
        // initialization for print
        mPrinterInfo = new PrinterInfo();
        mPrinter = new Printer();
        mPrinterInfo = mPrinter.getPrinterInfo();
        mPrinter.setMessageHandle(mHandle, Common.MSG_SDK_EVENT);
    }

    public static void cancel() {
        if (mPrinter != null)
            mPrinter.cancel();
        mCancel = true;
    }

    protected abstract void doPrint();

    /**
     * set PrinterInfo
     */
    public void setPrinterInfo(String ip, String macAddress, String printer) {

        getPreferences();
        setCustomPaper();
        mPrinterInfo.macAddress = macAddress;
        mPrinterInfo.ipAddress = ip;
        mPrinter.setPrinterInfo(mPrinterInfo);
        if (mPrinterInfo.port == PrinterInfo.Port.USB) {
            while (true) {
                if (Common.mUsbRequest != 0)
                    break;
            }
            if (Common.mUsbRequest != 1) {
            }
        }
    }
    public void setPrinterInfo() {

        getPreferences();
        setCustomPaper();
        mPrinter.setPrinterInfo(mPrinterInfo);
        if (mPrinterInfo.port == PrinterInfo.Port.USB) {
            while (true) {
                if (Common.mUsbRequest != 0)
                    break;
            }
            if (Common.mUsbRequest != 1) {
            }
        }
    }

    /**
     * get PrinterInfo
     */
    public PrinterInfo getPrinterInfo() {
        getPreferences();
        return mPrinterInfo;
    }

    /**
     * get Printer
     */
    public Printer getPrinter() {

        return mPrinter;
    }

    /**
     * get Printer
     */
    public PrinterStatus getPrintResult() {
        return mPrintResult;
    }

    /**
     * get Printer
     */
    public void setPrintResult(PrinterStatus printResult) {
        mPrintResult = printResult;
    }

    public void setBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {

        mPrinter.setBluetooth(bluetoothAdapter);
    }

    @TargetApi(12)
    public UsbDevice getUsbDevice(UsbManager usbManager) {
        return mPrinter.getUsbDevice(usbManager);
    }

    /**
     * get the PRINTER settings from the SharedPreferences
     */
    private void getPreferences() {
        if (mPrinterInfo == null) {
            mPrinterInfo = new PrinterInfo();
            return;
        }
        mPrinterInfo.printerModel = Model.QL_820NWB;
        mPrinterInfo.port= PrinterInfo.Port.BLUETOOTH;
        mPrinterInfo.labelNameIndex = LabelInfo.QL700.valueOf("W62RB").ordinal();
        mPrinterInfo.isAutoCut = true;
        mPrinterInfo.isCutAtEnd = true;
        mPrinterInfo.isHalfCut = false;
        mPrinterInfo.isSpecialTape = false;
        mPrinterInfo.numberOfCopies = 1;
        mPrinterInfo.pjDensity = 5;
        mPrinterInfo.margin.left = 0;
        mPrinterInfo.margin.top = 0;
        mPrinterInfo.customPaperWidth = 0;
        mPrinterInfo.customPaperLength = 0;
        mPrinterInfo.customFeed = 0;
        mPrinterInfo.pjSpeed = 2;
        mPrinterInfo.pjPaperKind = PrinterInfo.PjPaperKind.PJ_CUT_PAPER;
        mPrinterInfo.rollPrinterCase = PrinterInfo.PjRollCase.PJ_ROLLCASE_OFF;
        mPrinterInfo.skipStatusCheck = false;
        mPrinterInfo.checkPrintEnd = PrinterInfo.CheckPrintEnd.CPE_CHECK;
        mPrinterInfo.printQuality = PrinterInfo.PrintQuality.HIGH_RESOLUTION;
        mPrinterInfo.thresholdingValue = 127;
        mPrinterInfo.scaleValue = 1.0;
        mPrinterInfo.timeout.processTimeoutSec = 0;
        mPrinterInfo.timeout.sendTimeoutSec = 60;
        mPrinterInfo.timeout.receiveTimeoutSec = 180;
        mPrinterInfo.timeout.connectionWaitMSec = 0;
        mPrinterInfo.timeout.closeWaitDisusingStatusCheckSec = 3;

    }

    /**
     * Launch the thread to print
     */
    public void print() {
        mCancel = false;
        PrinterThread printTread = new PrinterThread();
        printTread.start();
    }

    /**
     * Launch the thread to get the PRINTER's status
     */
    public void getPrinterStatus() {
        mCancel = false;
        getStatusThread getTread = new getStatusThread();
        getTread.start();
    }

    /**
     * Launch the thread to print
     */
    public void sendFile() {


//        SendFileThread getTread = new SendFileThread();
//        getTread.start();
    }

    /**
     * set custom paper for RJ and TD
     */
    private void setCustomPaper() {

//        LabelInfo mLabelInfo = new LabelInfo();
//        mLabelInfo.labelNameIndex = 5;
//        mLabelInfo.isAutoCut = true;
//        mLabelInfo.isEndCut = true;
//        mLabelInfo.isHalfCut = false;
//        mLabelInfo.isSpecialTape = false;
//        getPrinter().setLabelInfo(mLabelInfo);
    }

    /**
     * get the end message of print
     */
    @SuppressWarnings("UnusedAssignment")
    public String showResult() {

        String result;

        if (mPrintResult.errorCode == ErrorCode.ERROR_NONE) {
            result = mContext.getString(R.string.error_message_none);
        } else {
            result = mPrintResult.errorCode.toString();
        }

        return result;
    }

    /**
     * show information of battery
     */
    public String getBattery() {
        String battery = "";
        return battery;
    }

    private boolean isLabelPrinter(PrinterInfo.Model model) {
        switch (model) {
            case QL_710W:
            case QL_720NW:
            case PT_E550W:
            case PT_E500:
            case PT_P750W:
            case PT_D800W:
            case PT_E800W:
            case PT_E850TKW:
            case PT_P900W:
            case PT_P950NW:
            case QL_810W:
            case QL_800:
            case QL_820NWB:
            case QL_1100:
            case QL_1110NWB:
            case QL_1115NWB:
                return true;
            default:
                return false;
        }
    }

    /**
     * Thread for printing
     */
    private class PrinterThread extends Thread {
        @Override
        public void run() {

            // set info. for printing
            setPrinterInfo();

            // start message
            Message msg = mHandle.obtainMessage(Common.MSG_PRINT_START);
            mHandle.sendMessage(msg);

            mPrintResult = new PrinterStatus();

            mPrinter.startCommunication();
            if (!mCancel && mPrinter != null) {
                Log.e("BasePrint","Printing");
                doPrint();
                Log.e("BasePrint","Printed");
            } else {
                Log.e("BasePrint","Canceled");

                mPrintResult.errorCode = ErrorCode.ERROR_CANCEL;
            }
            mPrinter.endCommunication();

            // end message
            mHandle.setResult(showResult());
            mHandle.setBattery(getBattery());
            msg = mHandle.obtainMessage(Common.MSG_PRINT_END);
            mHandle.sendMessage(msg);
        }
    }

    /**
     * Thread for getting the PRINTER's status
     */
    private class getStatusThread extends Thread {
        @Override
        public void run() {

            // set info. for printing
            setPrinterInfo();

            // start message
            Message msg = mHandle.obtainMessage(Common.MSG_PRINT_START);
            mHandle.sendMessage(msg);

            mPrintResult = new PrinterStatus();
            if (!mCancel) {
                mPrintResult = mPrinter.getPrinterStatus();
            } else {
                mPrintResult.errorCode = ErrorCode.ERROR_CANCEL;
            }
            // end message
            mHandle.setResult(showResult());
            mHandle.setBattery(getBattery());
            msg = mHandle.obtainMessage(Common.MSG_PRINT_END);
            mHandle.sendMessage(msg);

        }
    }

    /**
     * Thread for getting the PRINTER's status
     */
//    private class SendFileThread extends Thread {
//        @Override
//        public void run() {
//
//            // set info. for printing
//            setPrinterInfo();
//
//            // start message
//            Message msg = mHandle.obtainMessage(Common.MSG_PRINT_START);
//            mHandle.sendMessage(msg);
//
//            mPrintResult = new PrinterStatus();
//
//            mPrinter.startCommunication();
//
//            doPrint();
//
//            mPrinter.endCommunication();
//            // end message
//            mHandle.setResult(showResult());
//            mHandle.setBattery(getBattery());
//            msg = mHandle.obtainMessage(Common.MSG_PRINT_END);
//            mHandle.sendMessage(msg);
//
//        }
//    }
}
