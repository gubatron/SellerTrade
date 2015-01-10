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

    /** Returns the http port under which the peer is being announced.
     * All further HTTP interaction between servers should be done through this
     * port*/
    public final int getHttpPort() {
        return this.endp.getPort();
    }
}
