package com.seller.trade.services.dht;

import com.frostwire.jlibtorrent.TcpEndpoint;

public class DHTNode {

    public final TcpEndpoint endp;

    public DHTNode(TcpEndpoint endp) {
        this.endp = endp;
    }

    public final String getIPAddress() {
        return this.endp.getAddress();
    }
}
