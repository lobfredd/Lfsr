/**
 * Created by Fredrik on 11.10.2016.
 */
public class Client {

    private Lfsr lfsr;

    public Client(Lfsr lfsr) {
        this.lfsr = lfsr;
    }

    public String encrypt(String s, int letterLength){
        return substitute(s, letterLength);
    }

    public String decrypt(String s, int letterLength){
        return substitute(s, letterLength);
    }

    private String substitute(String s, int letterLength){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            byte letter = encode(s.charAt(i));
            byte out = 0;

            for (int j = 0; j < letterLength; j++) {
                out <<= 1;
                out += lfsr.performClock();
            }

            out ^= letter;
            sb.append(decode(out));
        }

        return sb.toString();
    }

    private byte encode(char c){
        return (byte) ((c >= 0x41 && c < 0x41 + 26) ? (c - 0x41)
                : (c == 'Æ') ? 26
                : (c == 'Ø') ? 27
                : (c == 'Å') ? 28
                : (c == ' ') ? 29
                : (c == '.') ? 30
                : (c == ',') ? 31
                : 0);
    }

    private char decode(byte b){
        return (char) ((b >= 0 && b < 26) ? (b + 0x41)
                : (b == 26) ? 'Æ'
                : (b == 27) ? 'Ø'
                : (b == 28) ? 'Å'
                : (b == 29) ? ' '
                : (b == 30) ? '.'
                : (b == 31) ? ','
                : 0);
    }

    public static void main(String[] args){
//        Lfsr lfsr = new Lfsr(178, 5, 3, 2);
        Lfsr lfsr = new Lfsr(178, 8, 6, 5, 3);

        Client client = new Client(lfsr);

//        System.out.println(client.decrypt("NQFTRQBNCJK,ØDXDUVZØ,EAQDX", 5));
        System.out.println(client.decrypt("RPPSTTOXPVFMAVØD,UÅÅPLYWUQÆLZFÅJVÅS UN.ODH", 5));
    }
}