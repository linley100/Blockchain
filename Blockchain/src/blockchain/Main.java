package blockchain;

import com.google.gson.GsonBuilder;
import java.io.FileReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.io.FileWriter;
 
public class Main {

    public static BlockChain chain = new BlockChain(); 

    public static void main(String[] args) throws java.io.IOException { 
        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("blockchain.json");
        JsonElement datos = parser.parse(fr);
                 
        chain = chain.BlockChainArchivo(datos);     
        Block block = new Block("Erick","123xyz", chain.getLastHash());
        
        if(chain.validateUsuario(block.usuario)){
            chain.add(block);
        }else{
            System.out.println("Rechazado");
        }
        
        
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain.getChain());        
        System.out.println(blockchainJson);
        
        try{
            FileWriter file = new FileWriter("blockchain.json");
            file.write(blockchainJson);
            file.flush();
            file.close();
        }catch(Exception ex){
            System.out.println("Error: "+ex.toString());
        }
    }
}
