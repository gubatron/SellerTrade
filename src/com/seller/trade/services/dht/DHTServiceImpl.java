package com.seller.trade.services.dht;

import com.frostwire.jlibtorrent.*;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.DhtBootstrapAlert;
import com.frostwire.jlibtorrent.alerts.DhtGetPeersReplyAlert;
import com.frostwire.jlibtorrent.alerts.ExternalIpAlert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DHTServiceImpl implements DHTService {

    private final Session s;
    private final DHT dht;

    public DHTServiceImpl() {
        s = new Session();
        dht = new DHT(s);

        s.addListener(new AlertListener() {

            @Override
            public int[] types() {
                return null;
            }

            @Override
            public void alert(Alert<?> alert) {
                //System.out.println(alert);

                if (alert instanceof DhtBootstrapAlert) {
                    System.out.println(alert);
                }

                if (alert instanceof DhtGetPeersReplyAlert) {
                    System.out.println(alert);
                }

                if (alert instanceof ExternalIpAlert) {
                    System.out.println(alert);
                }
            }
        });
    }

    @Override
    public List<DHTNode> getNodes() {
        return getNodes("hello.seller.trade");
    }

    @Override
    public List<DHTNode> getNodes(String keyword) {
        Sha1Hash hash = DHT.itemTargetId(new Entry(keyword));
        ArrayList<TcpEndpoint> peers = dht.getPeers(hash.toHex(), 30, TimeUnit.SECONDS);

        ArrayList<DHTNode> nodes = new ArrayList<>(peers.size());

        for (TcpEndpoint p : peers) {
            nodes.add(new DHTNode(p));
        }

        return nodes;
    }

    @Override
    public void announceNode() {
        Sha1Hash hash = DHT.itemTargetId(new Entry("hello.seller.trade"));
        dht.announce(hash.toHex());
    }

    @Override
    public void announceKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            Sha1Hash hash = DHT.itemTargetId(new Entry(keyword));
            dht.announce(hash.toHex());
        }
    }
}
