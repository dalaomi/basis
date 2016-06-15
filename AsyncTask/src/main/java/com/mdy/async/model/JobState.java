/**
 * Project:      AsyncTask
 * FileName:     TaskState.java
 */
package com.mdy.async.model;

public enum JobState {
	initialize(0),
    queued(1),
    running(2),
    success(3),
    failed(4),
    timeout(5);

    private int _val;

    private JobState(int val) {
        _val = val;
    }

    public int value() {
        return _val;
    }

    public static boolean isFinished(JobState state) {
        if (state == success || state == failed || state == timeout) {
            return true;
        }
        return false;
    }

    public static JobState valueOf(int value) {
        switch (value) {
            case 1:
                return queued;
            case 2:
                return running;
            case 3:
                return success;
            case 4:
                return failed;
            case 5:
                return timeout;

            default:
                return initialize;
        }
    }
}
