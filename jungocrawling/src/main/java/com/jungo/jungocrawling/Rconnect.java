package com.jungo.jungocrawling;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Rconnect {

    RConnection connection;

    public Rconnect(){
        this.connection=null;
        try{
            this.connection = new RConnection();

        } catch (RserveException e) {
            e.printStackTrace();
        }


    }

}
