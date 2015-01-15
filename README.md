## Seller.Trade
The world's simplest, legal, p2p Bitcoin Marketplace.

Created by Alden Torres and Angel Leon.

[Screenshots](http://imgur.com/a/h2jb3)

_Inspired by the [OpenBazaar project](http://www.openbazaar.org) ideals we've built a simple server-sided p2p Bitcoin marketplace that harnesses the indexing and routing powers of the BitTorrent DHT._

[_Winner of the 2nd place at the Miami Bitcoin Hackathon 2015_](http://miamiherald.typepad.com/the-starting-gate/2015/01/miami-bitcoin-hackathon-and-the-winners-were.html)

[See the video manifesto](https://www.youtube.com/watch?v=lW5deqZl5qE)

## Why?

Hello judges, participants and organizers of the Miami Bitcoin Hackathon.
Hello fellow Redditors and Bitcoiners worldwide.

One of Bitcoin's biggest problem is the fact that it's not valued by itself, Bitcoin's intrinsic value should come through its usefulness. You see, a currency is only as valuable as it's useful, and it's only as useful as it is accepted in exchange for goods and services.

The current state of affairs for e-commerce requires participants to have credit cards, merchant accounts, and pay high fees that puts e-commerce out of reach in the developing world, and a potential multibillion dollar market of microtransactions. (Where's my 99 cent store online?)

Bitcoin has the power to enable the unbanked cash societies to enter a world of global and local electronic commerce. 

Another problem Bitcoin has is that users in developed countries don't feel the need to use it, and this is because they haven't found out outlets which will let them buy things their credit cards can't.

We believe that if we enable any entrepreneur, in any part of the world, to sell any product no matter its price this will create a powerful cycle of value.

Bitcoins will become more useful and necessary as they allow Bitcoin holders to buy more things, which in turn is what makes any currency valuable and useful. The more things you can buy, the more Bitcoin you will need, the higher the demand for Bitcoin, the higher its value.

When you look at Internet powerhouses like Amazon, eBay or even social networks like Facebook, these companies realized that they could create businesses that wouldn't have existed before the internet, what they did was aggregate store owners, content creators, and personal pages into centralized hubs with good enough tools to create what seem like endless catalogs of products and content available 24/7.

Now that we have Bitcoin we can create an entire new industry that has the potential to leave the likes of Amazon, eBay and Facebook on its tracks, what happens when you can finally decentralize the ownership of content and e-commerce in the world?

## What if?

What if we could have all of those wordpress blogs connected to a marketplace that no central entity owns, what if we could have social network profiles that belonged to us without censorship all connected in a marketplace fashion, what if we could have our own stores all together to achieve the same infinite catalog effect amazon and ebay have, and what if those stores wouldn't suffer from the limitations imposed by our old controlling, limited and inneficient banking systems?

## How do we get there?

Some of us have heard Andreas M. Antonopoulos talk about the other six billion and how they can be empowered by Bitcoin. The truth is, unless you make it easy for those six billion to buy/sell things for Bitcoin it's not going anywhere.

We have built a proof of concept called SellerTrade, a simple decentralized network of stores which harnesses the power of the BitTorrent DHT network, a proven, free, opensource, scalable, decentralized network that connects tens of millions of computers.

By adding Bitcoin to the technology stack we now have a platform that can be used to decentralize commerce and content, the two together are finally here to decentralize legal e-commerce and make it reachable to any entrepreneur in the world.

Product listings may be published to the network from any existing e-commerce catalog.

Stores owners can create as many product listings as they can for free, and when they sell their products they don't need to pay third parties for comissions (except for miner fees which are paid by the customers), and best of all, the money from the purchases is available in about 15 minutes in electronic cash and settleable to fiat in any proportion you choose thanks to Bitpay. 

If you have any experience with Amazon forget about long settlement periods, or eBay accounts frozen due to recent changes in their terms of service.

Users don't need to install any special software to access the network, they can access the catalog via web browser, the entry nodes randomly redirect shoppers to any of the peer stores, these in turn act as search servers for the entire network and store fronts for their own products.
 
Bitcoin integration is done using Bitpay's API, as we still live in a fiat world, all product listings are created using fiat currency (USD), sellers don't need to deal with price changes or exchange rate anxiety.

Store owners are required to have a Bitpay account by design, this means seller anonymity and illegal goods are not a features of this network.

It is our goal to connect sellers from every country in the world to put together the longest tail of product offering the internet has ever seen, and there's nothing Amazon, ebay and Alibaba will be able to do about it, unless they integrate with Bitcoin.

## Technology Requirements
### Seller
- Java 1.7 or higher.
- frostwire-jlibtorrent
- One machine hosting Bootstrap DHT server, and HTTP redirector to announced peers.
- Jetty (or any servlet container)
- Server with public IP address, one open TCP port (preferably proxied via a web server's port 80) and all UDP ports open on the firewall.

### Buyer
- A Web browser.
- A Bitcoin wallet.

