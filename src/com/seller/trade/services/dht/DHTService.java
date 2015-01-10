package com.seller.trade.services.dht;

import java.util.List;

public interface DHTService {

    public List<DHTNode> getNodes();

    public List<DHTNode> getNodes(String keyword);

    public void announceNode(int httpPort);

    public void announceKeywords(List<String> keywords, int httpPort);
}
