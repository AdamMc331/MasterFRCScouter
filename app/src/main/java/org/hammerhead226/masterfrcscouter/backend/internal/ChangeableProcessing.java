package org.hammerhead226.masterfrcscouter.backend.internal;

import com.adithyasairam.utils.annotations.Changeable;
import com.adithyasairam.utils.annotations.processing.changeableprocessor.ChangeableProcessor;
import com.adithyasairam.utils.misc.GetClasses;

/**
 * Created by Adi on 8/30/2015.
 */
public class ChangeableProcessing {
    public static void main(String[] args) {
        Class[] classes = GetClasses.getClassesAnnotatedWith("org.hammerhead226.masterfrcscouter", Changeable.class);
        ChangeableProcessor changeableProcessor = new ChangeableProcessor(classes);
        System.out.println("Count: " + changeableProcessor.getClasses().length + "\n");
        changeableProcessor.run();
    }
}
