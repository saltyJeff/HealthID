/**
 * ImagePrint for printing
 *
 * @author Brother Industries, Ltd.
 * @version 2.2
 */
package org.woodbridgehigh.healthid.printprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class ImagePrint extends BasePrint {

    private Bitmap mText;
    private Bitmap mQr;
    public ImagePrint(Context context, MsgHandle mHandle, MsgDialog mDialog) {
        super(context, mHandle, mDialog);
    }

    /**
     * set print data
     */
//    public Bitmap getFiles() {
//        return mText;
//    }
    public ArrayList<String> getFiles(){ return null;}
    /**
     * set print data
     */
    public void setFiles(Bitmap text,Bitmap qr) {
        mText = text; mQr = qr;
    }

    /**
     * do the particular print
     */
    @Override
    protected void doPrint() {
        if(mText != null && mQr != null) {
			Log.w("PRINT", "we gots them both!");
		}
        if(mText != null) {
            mPrinter.printImage(mText);
            Log.w("PRINT", "txt print complt");
        }
        if(mQr != null) {
            mPrinter.printImage(mQr);
            Log.w("PRINT", "qr print compmlt");
        }
    }

}