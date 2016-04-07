package com.lrn.nettymysqlprotocol.protocol;

public class ServerStatus {
    protected long status;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public boolean isServerStatusInTrans(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS) != 0;
    }
    public void setServerStatusInTrans(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS;
    }
    public boolean isServerStatusAutocommit(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_AUTOCOMMIT) != 0;
    }
    public void setServerStatusAutocommit(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_AUTOCOMMIT;
    }
    public boolean isServerMoreResultsExists(){
        return (status & MysqlConstants.StatusConstants.SERVER_MORE_RESULTS_EXISTS) != 0;
    }
    public void setServerMoreResultsExists(){
        status = status | MysqlConstants.StatusConstants.SERVER_MORE_RESULTS_EXISTS;
    }
    public boolean isServerStatusNoGoodIndexUsed(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_NO_GOOD_INDEX_USED) != 0;
    }
    public void setServerStatusNoGoodIndexUsed(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_NO_GOOD_INDEX_USED;
    }
    public boolean isServerStatusNoIndexUsed(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_NO_INDEX_USED) != 0;
    }
    public void setServerStatusNoIndexUsed(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_NO_INDEX_USED;
    }
    public boolean isServerStatusCursorExists(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_CURSOR_EXISTS) != 0;
    }
    public void setServerStatusCursorExists(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_CURSOR_EXISTS;
    }
    public boolean isServerStatusLastRowSent(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_LAST_ROW_SENT) != 0;
    }
    public void setServerStatusLastRowSent(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_LAST_ROW_SENT;
    }
    public boolean isServerStatusDbDropped(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_DB_DROPPED) != 0;
    }
    public void setServerStatusDbDropped(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_DB_DROPPED;
    }
    public boolean isServerStatusNoBackslashEscapes(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_NO_BACKSLASH_ESCAPES) != 0;
    }
    public void setServerStatusNoBackslashEscapes(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_NO_BACKSLASH_ESCAPES;
    }
    public boolean isServerStatusMetadataChanged(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_METADATA_CHANGED) != 0;
    }
    public void setServerStatusMetadataChanged(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_METADATA_CHANGED;
    }
    public boolean isServerQueryWasSlow(){
        return (status & MysqlConstants.StatusConstants.SERVER_QUERY_WAS_SLOW) != 0;
    }
    public void setServerQueryWasSlow(){
        status = status | MysqlConstants.StatusConstants.SERVER_QUERY_WAS_SLOW;
    }
    public boolean isServerPsOutParams(){
        return (status & MysqlConstants.StatusConstants.SERVER_PS_OUT_PARAMS) != 0;
    }
    public void setServerPsOutParams(){
        status = status | MysqlConstants.StatusConstants.SERVER_PS_OUT_PARAMS;
    }
    public boolean isServerStatusInTransReadonly(){
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS_READONLY) != 0;
    }
    public void setServerStatusInTransReadonly(){
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS_READONLY;
    }
    public boolean isServerSessionStateChanged(){
        return (status & MysqlConstants.StatusConstants.SERVER_SESSION_STATE_CHANGED) != 0;
    }
    public void setServerSessionStateChanged(){
        status = status | MysqlConstants.StatusConstants.SERVER_SESSION_STATE_CHANGED;
    }

       
}
