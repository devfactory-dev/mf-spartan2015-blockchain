package org.vasileirimia;

import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Blockchain {

    public static void main(String[] args) {

        List<Block> blockchain = new LinkedList();

        blockchain.add(new Block("start","Hello World",System.currentTimeMillis()));
        blockchain.add(new Block(blockchain.get(blockchain.size()-1).getHash(),"Next in chain",System.currentTimeMillis
                ()));
        blockchain.add(new Block(blockchain.get(blockchain.size()-1).getHash(),"Next ",System.currentTimeMillis
                ()));

        System.out.println(isChainValid(blockchain));
    }

    public static Boolean isChainValid(List<Block> blockchain) {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(currentBlock.computeHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
