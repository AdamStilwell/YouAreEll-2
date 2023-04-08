package youareell;

import controllers.*;

public class YouAreEll {

    TransactionController tt;


    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll (MessageController m, IdController i){this.tt = new TransactionController(m,i);}

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    private String MakeURLCall(String s, String get, String s1) {
        return null;
    }

    public String get_ids() {
        return tt.makeCall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }


}
