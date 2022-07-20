package sticker.excecoes;

public class TrataExecoes extends RuntimeException{
	
	private static final long serialVersionUID = 1149241039409861914L;
	
	
    public TrataExecoes(String msg){
        super(msg);
    }
    
    public TrataExecoes(String msg, Throwable cause){
        super(msg, cause);
    }

}
