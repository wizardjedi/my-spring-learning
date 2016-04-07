package com.lrn.nettymysqlprotocol.transcoder;

import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;

public class TranscoderContext {
    protected Capabilities capabilities = new Capabilities();

    protected ServerStatus serverStatus = new ServerStatus();
    
    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }   
}
