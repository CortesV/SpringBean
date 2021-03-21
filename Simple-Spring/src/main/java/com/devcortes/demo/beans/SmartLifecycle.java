package com.devcortes.demo.beans;

import org.springframework.context.Lifecycle;
import org.springframework.context.Phased;

public interface SmartLifecycle extends Lifecycle, Phased{
    boolean isAutoStartup();

    void stop(Runnable callback);
}
