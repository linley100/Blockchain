package blockchain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;

public class BlockChain implements Cloneable {
    private final ArrayList<Block> chain;
    
    public BlockChain() {
        this.chain = new ArrayList<>();
    }
    
    public BlockChain(ArrayList<Block> chain) {
        this.chain = chain;
    }
    
    public BlockChain BlockChainArchivo(JsonElement datos) {
        final Gson gson = new Gson();
	final Type tipoListaEmpleados = new TypeToken<List<Block>>(){}.getType();
	final ArrayList<Block> cadena = gson.fromJson(datos, tipoListaEmpleados);
        BlockChain blockchain = new BlockChain(cadena);
        return blockchain;
    }
    
    public ArrayList<Block> getBlocks() {
        return (ArrayList<Block>) chain.clone();
    }
    
    public long size() {
        return chain.size();
    }
    
    public void add(Block block) {
        if (validateBlock(chain.get(chain.size() - 1), block)) {
            chain.add(block);
        } else {
            throw new IllegalArgumentException("Invalid block");
        }
    }
    
    public String getLastHash() {
        return this.chain.get(this.chain.size() - 1).hash;
    }
       
    public ArrayList<Block> getChain() {
        return this.chain;
    }
       
    public boolean validateBlock(Block previousBlock, Block block) {
        return block.previousHash.equals(previousBlock.hash);
    }
    
    public boolean validateUsuario(String user) {        
        ArrayList<Block> subChain = this.chain;
        Block previousBlock = chain.get(0);

        for (Block currentBlock : subChain) {
            if (currentBlock.usuario.equals(user)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        
        return true;
    }
    
    public boolean validate() {        
        ArrayList<Block> subChain = (ArrayList<Block>) chain.subList(1, chain.size() - 1);
        Block previousBlock = chain.get(0);

        for (Block currentBlock : subChain) {
            if (!validateBlock(previousBlock, currentBlock)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        
        return true;
    }
}
