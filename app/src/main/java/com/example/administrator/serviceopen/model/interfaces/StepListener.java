package com.example.administrator.serviceopen.model.interfaces;

public interface StepListener {

    /**
     * define interface  about  user running
     *
     * @param step
     */
    // start
    void start(String step);

    // pause
    void pause();

    //stop
    void stop(String time);
}
