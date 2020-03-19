package com.jungo.jungocrawling;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Rconnect {

    public RConnection[] rConnection = new RConnection[3];
    public int port = 1;
    public int max_port = 65535;

    public boolean[] port_use = new boolean[65534];

    public int getPort() {
        for (int i = 0; i < max_port;i++){
            if(!(port_use[i])) {
                port_use[i] = true;
                return i + 1;
            }
        }
         return 0;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void retrunPort(int port){
        port_use[port - 1] = false;
    }

    public Rconnect() throws RserveException {
        Arrays.fill(port_use,false);
            for(int i = 0; i < 3; i++){
                rConnection[i] = new RConnection(null,6311 + i);
                rConnection[i].eval("i<-" + i + 100);
                rConnection[i].eval("library(RSelenium)");
                rConnection[i].eval("setwd('C:\\\\R')");
                rConnection[i].eval("source(\"login.R\")");
            }
    }
}
