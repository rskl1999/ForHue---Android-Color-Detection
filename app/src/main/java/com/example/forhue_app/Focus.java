package com.example.forhue_app;


import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.widget.Toast;

import org.opencv.android.JavaCameraView;

import java.util.List;

public class Focus extends JavaCameraView {
    public static android.hardware.Camera camera;
    public static android.hardware.Camera.Parameters params;
        public Focus(Context context, AttributeSet attrs) {
            super(context, attrs);
        }



        public void setFocusMode(Context item, int type) {

            android.hardware.Camera.Parameters params = mCamera.getParameters();
            mCamera.cancelAutoFocus();
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {

                }

            });

            List<String> FocusModes = params.getSupportedFocusModes();

            switch (type) {
                case 0:
                    if (FocusModes.contains(params.FOCUS_MODE_AUTO))
                        params.setFocusMode(params.FOCUS_MODE_AUTO);
                    else
                        Toast.makeText(item, "Auto Mode is not supported", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    if (FocusModes.contains(params.FOCUS_MODE_CONTINUOUS_VIDEO))
                        params.setFocusMode(params.FOCUS_MODE_CONTINUOUS_VIDEO);
                    else
                        Toast.makeText(item, "Continuous Mode is not supported", Toast.LENGTH_SHORT).show();
                    break;
                case 2:

                    if (FocusModes.contains(params.FOCUS_MODE_EDOF))
                        params.setFocusMode(params.FOCUS_MODE_EDOF);
                    else
                        Toast.makeText(item, "EDOF Mode is not supported", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    if (FocusModes.contains(params.FOCUS_MODE_FIXED))
                        params.setFocusMode(params.FOCUS_MODE_FIXED);
                    else
                        Toast.makeText(item, "Fixed Mode is not supported", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    if (FocusModes.contains(params.FOCUS_MODE_INFINITY))
                        params.setFocusMode(params.FOCUS_MODE_INFINITY);
                    else
                        Toast.makeText(item, "Infinity Mode is not supported", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    if (FocusModes.contains(params.FOCUS_MODE_MACRO))
                        params.setFocusMode(params.FOCUS_MODE_MACRO);
                    else
                        Toast.makeText(item, "Macro Mode is not supported", Toast.LENGTH_SHORT).show();
                    break;
            }

            mCamera.setParameters(params);
        }


}
