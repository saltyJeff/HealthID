/**
 * ImagePrint for printing
 *
 * @author Brother Industries, Ltd.
 * @version 2.2
 */
package org.woodbridgehigh.healthid.printprocess;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class ImagePrint extends BasePrint {

    private Bitmap mText;
    private Bitmap mAztec;
    public ImagePrint(Context context, MsgHandle mHandle, MsgDialog mDialog) {
        super(context, mHandle, mDialog);
    }

    /**
     * set print data
     */
//    public Bitmap getFiles() {
//        return mText;
//    }
    public ArrayList<String> getFiles(){ return  null ;}
    /**
     * set print data
     */
    public void setFiles(Bitmap text,Bitmap aztec) {
        mText = text; mAztec = aztec;
    }

    /**
     * do the particular print
     */
    @Override
    protected void doPrint() {
        mPrinter.printImage(mText);
        mPrinter.printImage(mAztec);
    }

}