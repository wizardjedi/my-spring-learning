package com.lrn.nettymysqlprotocol.protocol.impl;

import com.lrn.nettymysqlprotocol.protocol.BasePacket;
import com.lrn.nettymysqlprotocol.protocol.Capabilities;
import com.lrn.nettymysqlprotocol.protocol.MysqlByteBufUtil;
import com.lrn.nettymysqlprotocol.protocol.ServerStatus;
import com.lrn.nettymysqlprotocol.transcoder.TranscoderContext;
import io.netty.buffer.ByteBuf;

/**
 * @see https://dev.mysql.com/doc/internals/en/packet-OK_Packet.html
 */
public class OkPacket extends BasePacket {

    protected long affectedRows = 0;
    
    protected long lastInsertId = 0;
    
    protected String humanReadableStatus = "";

    protected String sessionStateChanges = "";

    protected long warningCount = 0;

    public long getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(long warningCount) {
        this.warningCount = warningCount;
    }
    
    public String getSessionStateChanges() {
        return sessionStateChanges;
    }

    public void setSessionStateChanges(String sessionStateChanges) {
        this.sessionStateChanges = sessionStateChanges;
    }
    
    public String getHumanReadableStatus() {
        return humanReadableStatus;
    }

    public void setHumanReadableStatus(String humanReadableStatus) {
        this.humanReadableStatus = humanReadableStatus;
    }
    
    public long getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(long affectedRows) {
        this.affectedRows = affectedRows;
    }

    public long getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(long lastInsertId) {
        this.lastInsertId = lastInsertId;
    }
    
    @Override
    public void readBody(ByteBuf bb, TranscoderContext context) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeBody(ByteBuf bb, TranscoderContext context) throws Exception {
        Capabilities capabilities = context.getCapabilities();
        ServerStatus status = context.getServerStatus();
        
        // int<1>	header	[00] or [fe] the OK packet header
        MysqlByteBufUtil.writeInt1(bb, 0x00); 
        //int<lenenc>	affected_rows	affected rows
        MysqlByteBufUtil.writeLenencInteger(bb, getAffectedRows()); 
        //int<lenenc>	last_insert_id	last insert-id
        MysqlByteBufUtil.writeLenencInteger(bb, getLastInsertId()); 
        
        if (capabilities.isClientProtocol41()) {  //if capabilities & CLIENT_PROTOCOL_41 {
            //  int<2>	status_flags	Status Flags
            MysqlByteBufUtil.writeInt2(bb, status.getStatus()); 
            //  int<2>	warnings	number of warnings
            MysqlByteBufUtil.writeInt2(bb, getWarningCount()); 
        } else {
            if (capabilities.isClientTransactions()) { //} elseif capabilities & CLIENT_TRANSACTIONS {
                //  int<2>	status_flags	Status Flags
                MysqlByteBufUtil.writeInt2(bb, status.getStatus()); 
            } //}
        }
        
        if (capabilities.isClientSessionTrack()) { //if capabilities & CLIENT_SESSION_TRACK {
            //  string<lenenc>	info	human readable status information
            MysqlByteBufUtil.writeLenencString(bb, getHumanReadableStatus().getBytes()); 
            
            //  if status_flags & SERVER_SESSION_STATE_CHANGED {
            if (status.isServerSessionStateChanged()) { 
                //    string<lenenc>	session_state_changes	session state info
                MysqlByteBufUtil.writeLenencString(bb, getSessionStateChanges().getBytes()); 
            }
        } else {
            //  string<EOF>	info	human readable status information
            MysqlByteBufUtil.writeStringEof(bb, getHumanReadableStatus().getBytes()); 
        }
    }

    @Override
    public int calculateBodyLength(TranscoderContext context) {
        Capabilities capabilities = context.getCapabilities();
        ServerStatus status = context.getServerStatus();
        
        int result = 0;
        
        // int<1>	header	[00] or [fe] the OK packet header
        result += 1; 
        //int<lenenc>	affected_rows	affected rows
        result += MysqlByteBufUtil.getLenencIntegerLength(getAffectedRows()); 
        //int<lenenc>	last_insert_id	last insert-id
        result += MysqlByteBufUtil.getLenencIntegerLength(getLastInsertId()); 
        
        if (capabilities.isClientProtocol41()) {  //if capabilities & CLIENT_PROTOCOL_41 {
            //  int<2>	status_flags	Status Flags
            result += 2; 
            //  int<2>	warnings	number of warnings
            result += 2; 
        } else {
            if (capabilities.isClientTransactions()) { //} elseif capabilities & CLIENT_TRANSACTIONS {
                //  int<2>	status_flags	Status Flags
                result += 2; 
            } //}
        }
        
        if (capabilities.isClientSessionTrack()) { //if capabilities & CLIENT_SESSION_TRACK {
            //  string<lenenc>	info	human readable status information
            result += MysqlByteBufUtil.getLenencStringLength(getHumanReadableStatus().getBytes()); 
            
            //  if status_flags & SERVER_SESSION_STATE_CHANGED {
            if (status.isServerSessionStateChanged()) { 
                //    string<lenenc>	session_state_changes	session state info
                result += MysqlByteBufUtil.getLenencStringLength(getSessionStateChanges().getBytes()); 
            }
        } else {
            //  string<EOF>	info	human readable status information
            result += MysqlByteBufUtil.getStringEofLength(getHumanReadableStatus().getBytes()); 
        }

        return result;
    }
}
