package com.lrn.nettymysqlprotocol.protocol;

public class Capabilities {
    protected long capabilities;

    public long getHighWord() {
        return capabilities >> 16;
    }
    
    public long getLowWord() {
        return capabilities & 0xFFFF;
    }
    
    public long getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(long capabilities) {
        this.capabilities = capabilities;
    }
    
    public boolean isClientLongPassword(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_LONG_PASSWORD ) != 0;
    }
    public void setClientLongPassword(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_LONG_PASSWORD ;
    }
    public boolean isClientFoundRows(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_FOUND_ROWS ) != 0;
    }
    public void setClientFoundRows(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_FOUND_ROWS ;
    }
    public boolean isClientLongFlag(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_LONG_FLAG ) != 0;
    }
    public void setClientLongFlag(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_LONG_FLAG ;
    }
    public boolean isClientConnectWithDb(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_WITH_DB ) != 0;
    }
    public void setClientConnectWithDb(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_WITH_DB ;
    }
    public boolean isClientNoSchema(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_NO_SCHEMA ) != 0;
    }
    public void setClientNoSchema(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_NO_SCHEMA ;
    }
    public boolean isClientCompress(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_COMPRESS ) != 0;
    }
    public void setClientCompress(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_COMPRESS ;
    }
    public boolean isClientOdbc(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_ODBC ) != 0;
    }
    public void setClientOdbc(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_ODBC ;
    }
    public boolean isClientLocalFiles(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_LOCAL_FILES ) != 0;
    }
    public void setClientLocalFiles(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_LOCAL_FILES ;
    }
    public boolean isClientIgnoreSpace(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SPACE ) != 0;
    }
    public void setClientIgnoreSpace(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SPACE ;
    }
    public boolean isClientProtocol41(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PROTOCOL_41 ) != 1;
    }
    public void setClientProtocol41(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PROTOCOL_41 ;
    }
    public boolean isClientInteractive(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_INTERACTIVE ) != 0;
    }
    public void setClientInteractive(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_INTERACTIVE ;
    }
    public boolean isClientSsl(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SSL ) != 0;
    }
    public void setClientSsl(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SSL ;
    }
    public boolean isClientIgnoreSigpipe(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SIGPIPE ) != 0;
    }
    public void setClientIgnoreSigpipe(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_IGNORE_SIGPIPE ;
    }
    public boolean isClientTransactions(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_TRANSACTIONS ) != 0;
    }
    public void setClientTransactions(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_TRANSACTIONS ;
    }
    public boolean isClientReserved(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_RESERVED ) != 0;
    }
    public void setClientReserved(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_RESERVED ;
    }
    public boolean isClientSecureConnection(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SECURE_CONNECTION ) != 0;
    }
    public void setClientSecureConnection(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SECURE_CONNECTION ;
    }
    public boolean isClientMultiStatements(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_STATEMENTS ) != 0;
    }
    public void setClientMultiStatements(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_STATEMENTS ;
    }
    public boolean isClientMultiResults(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_RESULTS ) != 0;
    }
    public void setClientMultiResults(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_MULTI_RESULTS ;
    }
    public boolean isClientPsMultiResults(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PS_MULTI_RESULTS ) != 0;
    }
    public void setClientPsMultiResults(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PS_MULTI_RESULTS ;
    }
    public boolean isClientPluginAuth(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH ) != 0;
    }
    public void setClientPluginAuth(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH ;
    }
    public boolean isClientConnectAttrs(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_ATTRS ) != 0;
    }
    public void setClientConnectAttrs(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_CONNECT_ATTRS ;
    }
    public boolean isClientPluginAuthLenencClientData(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA ) != 0;
    }
    public void setClientPluginAuthLenencClientData(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA ;
    }
    public boolean isClientCanHandleExpiredPasswords(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS ) != 0;
    }
    public void setClientCanHandleExpiredPasswords(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS ;
    }
    public boolean isClientSessionTrack(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SESSION_TRACK ) != 0;
    }
    public void setClientSessionTrack(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SESSION_TRACK ;
    }
    public boolean isClientDeprecateEof(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_DEPRECATE_EOF ) != 0;
    }
    public void setClientDeprecateEof(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_DEPRECATE_EOF ;
    }
    public boolean isClientSslVerifyServerCert(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_SSL_VERIFY_SERVER_CERT ) != 0;
    }
    public void setClientSslVerifyServerCert(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_SSL_VERIFY_SERVER_CERT ;
    }
    public boolean isClientRememberOptions(){
        return (capabilities & MysqlConstants.CapabilitiesConstants.CLIENT_REMEMBER_OPTIONS ) != 0;
    }
    public void setClientRememberOptions(){
        capabilities = capabilities | MysqlConstants.CapabilitiesConstants.CLIENT_REMEMBER_OPTIONS ;
    }

    
    
}
