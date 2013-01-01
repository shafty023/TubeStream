package com.qs.tubestream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Receiver extends Activity {                                   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        CharSequence text = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);

        if (TubeStream.DEBUG) {
	        Log.d(TubeStream.LOG_TAG, "Receiver: Intent dump -> " + intent.toString());
	        Log.d(TubeStream.LOG_TAG, "Receiver: Intent text: " + text.toString());
        }

        processUrl(text.toString());

        finish();
    }

    /**
     * Validate the url passed in is a valid youtube video url.
     * 
     * @param url a youtube video url
     */
    private void processUrl(final String url) {
        /*
         * A youtube share url looks as follows:
         * https://www.youtube.com/watch?v=h9QS28m1j9w&feature=youtube_gdata_player
         * 
         * We only validate it here just to confirm we received a youtube share and not some
         * other erroneous data. The youtube api will handle the rest in case someone supplied
         * an invalid video url.
         */
        if (!url.matches("^(?i)(http|https){1}:\\/\\/www\\.youtube\\.com\\/watch\\?v=[a-zA-Z0-9]+.*")) {
            if (TubeStream.DEBUG) {
                Log.d(TubeStream.LOG_TAG, "Receiver: erroneous data passed in, finishing");
            }
            finish();
            return;
        }
    }
}