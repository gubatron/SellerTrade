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

    private final String HELLO_SELLER_TRADE_SHA1 = "de97662e7ba270abbf67485f41bdfb8995f7960b";
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

        dht.waitNodes(1);
    }

    @Override
    public List<DHTNode> getNodes() {
        return getNodes(HELLO_SELLER_TRADE_SHA1);
    }

    @Override
    public List<DHTNode> getNodes(String sha1string) {
        ArrayList<TcpEndpoint> peers = dht.getPeers(sha1string, 30, TimeUnit.SECONDS);

        ArrayList<DHTNode> nodes = new ArrayList<>(peers.size());

        for (TcpEndpoint p : peers) {
            nodes.add(new DHTNode(p));
        }

        return nodes;
    }

    @Override
    public void announceNode() {
        System.out.println("Announcing sha1('hello.seller.trade') -> " + HELLO_SELLER_TRADE_SHA1);
        dht.announce(HELLO_SELLER_TRADE_SHA1);
    }

    @Override
    public void announceKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            Sha1Hash hash = DHT.itemTargetId(new Entry(keyword));
            dht.announce(hash.toHex());
        }
    }
}
