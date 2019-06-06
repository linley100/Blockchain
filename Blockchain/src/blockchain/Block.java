package blockchain;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    public String usuario;
    private String contraseña;
    private long timeStamp;

    //Block Constructor.

    public Block(String usuario, String contraseña, String previousHash ) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    
    public static Block getGenesisBlock() {
        Block block = new Block("Genesis", "0", "0");
        block.timeStamp = 0;
        block.hash = block.calculateHash();
        return block;
    }
    
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256( 
                previousHash +
                Long.toString(timeStamp) +
                usuario 
                );
        return calculatedhash;
    }
    
}
