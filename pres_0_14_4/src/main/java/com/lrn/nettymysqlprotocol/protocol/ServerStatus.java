package com.lrn.nettymysqlprotocol.protocol;

import com.lrn.nettymysqlprotocol.HexUtils;

public class ServerStatus {

    protected long status;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public boolean isServerStatusInTrans() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS) != 0;
    }

    public void setServerStatusInTrans() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS;
    }

    public boolean isServerStatusAutocommit() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_AUTOCOMMIT) != 0;
    }

    public void setServerStatusAutocommit() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_AUTOCOMMIT;
    }

    public boolean isServerMoreResultsExists() {
        return (status & MysqlConstants.StatusConstants.SERVER_MORE_RESULTS_EXISTS) != 0;
    }

    public void setServerMoreResultsExists() {
        status = status | MysqlConstants.StatusConstants.SERVER_MORE_RESULTS_EXISTS;
    }

    public boolean isServerStatusNoGoodIndexUsed() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_NO_GOOD_INDEX_USED) != 0;
    }

    public void setServerStatusNoGoodIndexUsed() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_NO_GOOD_INDEX_USED;
    }

    public boolean isServerStatusNoIndexUsed() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_NO_INDEX_USED) != 0;
    }

    public void setServerStatusNoIndexUsed() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_NO_INDEX_USED;
    }

    public boolean isServerStatusCursorExists() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_CURSOR_EXISTS) != 0;
    }

    public void setServerStatusCursorExists() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_CURSOR_EXISTS;
    }

    public boolean isServerStatusLastRowSent() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_LAST_ROW_SENT) != 0;
    }

    public void setServerStatusLastRowSent() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_LAST_ROW_SENT;
    }

    public boolean isServerStatusDbDropped() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_DB_DROPPED) != 0;
    }

    public void setServerStatusDbDropped() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_DB_DROPPED;
    }

    public boolean isServerStatusNoBackslashEscapes() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_NO_BACKSLASH_ESCAPES) != 0;
    }

    public void setServerStatusNoBackslashEscapes() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_NO_BACKSLASH_ESCAPES;
    }

    public boolean isServerStatusMetadataChanged() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_METADATA_CHANGED) != 0;
    }

    public void setServerStatusMetadataChanged() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_METADATA_CHANGED;
    }

    public boolean isServerQueryWasSlow() {
        return (status & MysqlConstants.StatusConstants.SERVER_QUERY_WAS_SLOW) != 0;
    }

    public void setServerQueryWasSlow() {
        status = status | MysqlConstants.StatusConstants.SERVER_QUERY_WAS_SLOW;
    }

    public boolean isServerPsOutParams() {
        return (status & MysqlConstants.StatusConstants.SERVER_PS_OUT_PARAMS) != 0;
    }

    public void setServerPsOutParams() {
        status = status | MysqlConstants.StatusConstants.SERVER_PS_OUT_PARAMS;
    }

    public boolean isServerStatusInTransReadonly() {
        return (status & MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS_READONLY) != 0;
    }

    public void setServerStatusInTransReadonly() {
        status = status | MysqlConstants.StatusConstants.SERVER_STATUS_IN_TRANS_READONLY;
    }

    public boolean isServerSessionStateChanged() {
        return (status & MysqlConstants.StatusConstants.SERVER_SESSION_STATE_CHANGED) != 0;
    }

    public void setServerSessionStateChanged() {
        status = status | MysqlConstants.StatusConstants.SERVER_SESSION_STATE_CHANGED;
    }

    @Override
    public String toString() {
        return "ServerStatus{status=" + Long.toHexString(status) + '}';
    }

    public String toPrettyString() {
        return toPrettyString(true);
    }

    public String toPrettyString(boolean significant) {
        StringBuilder sb = new StringBuilder("ServerStatus{status=" + Long.toHexString(status) + "}[\n");

        boolean resServerStatusInTrans = isServerStatusInTrans();
        if (!significant || resServerStatusInTrans) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusInTrans,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_IN_TRANS,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_IN_TRANS
                            )
                    ).append("\n");
        }

        boolean resServerStatusAutocommit = isServerStatusAutocommit();
        if (!significant || resServerStatusAutocommit) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusAutocommit,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_AUTOCOMMIT,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_AUTOCOMMIT
                            )
                    ).append("\n");
        }

        boolean resServerMoreResultsExists = isServerMoreResultsExists();
        if (!significant || resServerMoreResultsExists) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerMoreResultsExists,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_MORE_RESULTS_EXISTS,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_MORE_RESULTS_EXISTS
                            )
                    ).append("\n");
        }

        boolean resServerStatusNoGoodIndexUsed = isServerStatusNoGoodIndexUsed();
        if (!significant || resServerStatusNoGoodIndexUsed) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusNoGoodIndexUsed,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_NO_GOOD_INDEX_USED,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_NO_GOOD_INDEX_USED
                            )
                    ).append("\n");
        }

        boolean resServerStatusNoIndexUsed = isServerStatusNoIndexUsed();
        if (!significant || resServerStatusNoIndexUsed) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusNoIndexUsed,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_NO_INDEX_USED,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_NO_INDEX_USED
                            )
                    ).append("\n");
        }

        boolean resServerStatusCursorExists = isServerStatusCursorExists();
        if (!significant || resServerStatusCursorExists) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusCursorExists,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_CURSOR_EXISTS,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_CURSOR_EXISTS
                            )
                    ).append("\n");
        }

        boolean resServerStatusLastRowSent = isServerStatusLastRowSent();
        if (!significant || resServerStatusLastRowSent) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusLastRowSent,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_LAST_ROW_SENT,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_LAST_ROW_SENT
                            )
                    ).append("\n");
        }

        boolean resServerStatusDbDropped = isServerStatusDbDropped();
        if (!significant || resServerStatusDbDropped) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusDbDropped,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_DB_DROPPED,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_DB_DROPPED
                            )
                    ).append("\n");
        }

        boolean resServerStatusNoBackslashEscapes = isServerStatusNoBackslashEscapes();
        if (!significant || resServerStatusNoBackslashEscapes) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusNoBackslashEscapes,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_NO_BACKSLASH_ESCAPES,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_NO_BACKSLASH_ESCAPES
                            )
                    ).append("\n");
        }

        boolean resServerStatusMetadataChanged = isServerStatusMetadataChanged();
        if (!significant || resServerStatusMetadataChanged) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusMetadataChanged,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_METADATA_CHANGED,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_METADATA_CHANGED
                            )
                    ).append("\n");
        }

        boolean resServerQueryWasSlow = isServerQueryWasSlow();
        if (!significant || resServerQueryWasSlow) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerQueryWasSlow,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_QUERY_WAS_SLOW,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_QUERY_WAS_SLOW
                            )
                    ).append("\n");
        }

        boolean resServerPsOutParams = isServerPsOutParams();
        if (!significant || resServerPsOutParams) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerPsOutParams,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_PS_OUT_PARAMS,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_PS_OUT_PARAMS
                            )
                    ).append("\n");
        }

        boolean resServerStatusInTransReadonly = isServerStatusInTransReadonly();
        if (!significant || resServerStatusInTransReadonly) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerStatusInTransReadonly,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_STATUS_IN_TRANS_READONLY,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_STATUS_IN_TRANS_READONLY
                            )
                    ).append("\n");
        }

        boolean resServerSessionStateChanged = isServerSessionStateChanged();
        if (!significant || resServerSessionStateChanged) {
            sb
                    .append("  ")
                    .append(
                            HexUtils
                            .renderBit(
                                    resServerSessionStateChanged,
                                    16,
                                    MysqlConstants.StatusConstants.BIT_NUM_SERVER_SESSION_STATE_CHANGED,
                                    MysqlConstants.StatusConstants.DESCRIPTION_SERVER_SESSION_STATE_CHANGED
                            )
                    ).append("\n");
        }

        sb.append("]");

        return sb.toString();
    }
}
