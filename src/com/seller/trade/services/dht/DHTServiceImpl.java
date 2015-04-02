/**
 * The MIT License
 ===============

 Copyright (C) 2015 SellerTrade Developers

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject
 to the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.seller.trade.services.dht;

import com.frostwire.jlibtorrent.*;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.DhtBootstrapAlert;
import com.frostwire.jlibtorrent.alerts.DhtGetPeersReplyAlert;
import com.frostwire.jlibtorrent.alerts.ExternalIpAlert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DHTServiceImpl implements DHTService {

    private final String HELLO_SELLER_TRADE_SHA1 = "de97662e7ba270abbf67485f41bdfb8995f7960b";
    private final Session s;
    private final DHT dht;
    private final boolean useLanMappings;
    private final Map<Integer,String> LAN_MAPPINGS;

    public DHTServiceImpl(boolean useLanMappings) {
        s = new Session();
        dht = new DHT(s);
        this.useLanMappings = useLanMappings;
        LAN_MAPPINGS = (useLanMappings) ? getLanMappings() : new HashMap<Integer,String>();

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
        //dht.getPeers("86d0502ead28e495c9e67665340f72aa72fe304");
        //dht.waitNodes(1); method no longer here.
	System.out.println("Waiting for DHT Nodes 5 seconds");

	try {
	    Thread.sleep(5000);
	} catch (Throwable e) {
	}
    }

    @Override
    public List<DHTNode> getNodes() {
        return getNodesByHash(HELLO_SELLER_TRADE_SHA1);
    }

    @Override
    public List<DHTNode> getNodes(String keyword) {
        final String keywordSha1Str = sha1hex("sellertrade:product:keyword:" + keyword);
        return getNodesByHash(keywordSha1Str);
    }

    public List<DHTNode> getNodesByHash(String sha1string) {
        ArrayList<TcpEndpoint> peers = dht.getPeers(sha1string, 30, TimeUnit.SECONDS);
        ArrayList<DHTNode> nodes = new ArrayList<>(peers.size());

        for (TcpEndpoint p : peers) {
            if (useLanMappings) {
                //use for development purposes only when testing with local nodes and unfriendly routers.
                nodes.add(new DHTNode(LAN_MAPPINGS.get(p.getPort()),p.getPort()));
            } else {
                nodes.add(new DHTNode(p.getAddress(),p.getPort()));
            }
        }

        return nodes;
    }

    @Override
    public void announceNode(int httpPort) {
        System.out.println("Announcing sha1('hello.seller.trade') -> " + HELLO_SELLER_TRADE_SHA1);
        dht.announce(HELLO_SELLER_TRADE_SHA1, httpPort, 0);
    }

    @Override
    public void announceKeywords(List<String> keywords, int httpPort) {
        for (String keyword : keywords) {
            final String keywordSha1Str = sha1hex("sellertrade:product:keyword:" + keyword);
            dht.announce(keywordSha1Str,httpPort,0);
            System.out.println("announcing [sellertrade:product:keyword:" + keyword + "] -> " + keywordSha1Str);
        }
    }

    // copied from internet
    final protected static char[] hexArray = "0123456789abcdef".toCharArray();
    public static String toHex(byte[] bytes) {
	char[] hexChars = new char[bytes.length * 2];
	for ( int j = 0; j < bytes.length; j++ ) {
	    int v = bytes[j] & 0xFF;
	    hexChars[j * 2] = hexArray[v >>> 4];
	    hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	}
	return new String(hexChars);
    }

    private static byte[] sha1(String str) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(str.getBytes());
        return digest.digest();
    }

    private static String sha1hex(String str) {
        return toHex(sha1(str));
    }

    private static Map<Integer,String> getLanMappings() {
        Map<Integer,String> LAN_MAPPINGS = new HashMap<Integer,String>();
        // 2 nodes on machine A.
        LAN_MAPPINGS.put(8920,"172.16.2.129");
        LAN_MAPPINGS.put(8921,"172.16.2.129");

        // 2 nodes on machine B.
        LAN_MAPPINGS.put(8990,"172.16.2.118");
        LAN_MAPPINGS.put(8991,"172.16.2.118");
        return LAN_MAPPINGS;
    }
}
