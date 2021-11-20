import java.awt.desktop.SystemEventListener;

public class Message {

    public static String welcome = Board.ANSI_CYAN+ "\n" +
            "          _______  _        _______  _______  _______  _______ \n" +
            "|\\     /|(  ____ \\( \\      (  ____ \\(  ___  )(       )(  ____ \\\n" +
            "| )   ( || (    \\/| (      | (    \\/| (   ) || () () || (    \\/\n" + Board.ANSI_RESET+Board.ANSI_GREEN+
            "| | _ | || (__    | |      | |      | |   | || || || || (__    \n" +
            "| |( )| ||  __)   | |      | |      | |   | || |(_)| ||  __)   \n" +Board.ANSI_RESET+Board.ANSI_RED+
            "| || || || (      | |      | |      | |   | || |   | || (      \n" +
            "| () () || (____/\\| (____/\\| (____/\\| (___) || )   ( || (____/\\\n" +Board.ANSI_RESET+Board.ANSI_PURPLE+
            "(_______)(_______/(_______/(_______/(_______)|/     \\|(_______/"+Board.ANSI_RESET;

    public static String hero =Board.ANSI_CYAN+ "    =.                            .=    \n" +
            "    =:                            :=    \n" +
            "    =:                            :=    \n" +
            "   .=:          .:-==-:           :=.   \n" +
            "   ==.        :+########-.        .==   \n" +
            "   ==-       =#####=+###*=:       -==   \n" +
            "    ++-::. :=#####* .#####=-: .::-++    \n" +
            "     .=+=-**######-..+######**-=+=.     \n" +
            "        -**+##****-::=*++++*+**-        \n" +
            "          .**###***********+=:          \n" +
            "          .+#*+++++=-=====+#+:          \n" +
            "          :*+-  .:*+=#-.  :*+-          \n" +
            "          -#.**+==++=+===**:*=          \n" +
            "          =#. .::--===-::.  #+          \n" +
            "          =#:               #*          \n" +
            "          +#:              .#*          \n" +
            "           :                :.          "+Board.ANSI_RESET;


   public static String monster = "\n" +
           "                                        \n" +
           "                                        \n" +
           "         . =:..                         \n" +
           "        =@%@%+--.  :**@@@#=             \n" +
           "       *#@@@@@*:    .@@@@@@@=           \n" +
           "      .%@@@%@@@=-    @@@@@@@@%.         \n" +
           "      #@%- -@@@#:   .@@@@@@@@@@-        \n" +
           "          +@@@*+    #@@@@@@@@@@@=       \n" +
           "       .+@@@@%-   :#@@@@@@@@@@@@@=      \n" +
           "      *@@@@@@+=+*%@@@@@@@@@@@@@@@@-     \n" +
           "     +@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:    \n" +
           "     +@@@@@@@@@@+=++#*:#@@@@@@@@@@@@.   \n" +
           "      %@@@@@@@@*+:- :.-=.*@@@@@@@@@@#   \n" +
           "       #@@@@@@@@@@%+.    *#=+%@@*::+@:  \n" +
           "       :@@#=%@@@@@@@@+:.-.    *@    #=  \n" +
           "       :@%. +@@@@@@@@@#=      =*    #   \n" +
           "     --#%--:.+@@@@@*-=%@%*-:.      .    \n" +
           "    :%%*=@@%%%%%%%%#-  .-+#%@##+=-:     \n" +
           "                                      "+Board.ANSI_RESET;

   public static String fight = "                                                           \n" +
           Board.ANSI_CYAN+ "                    =##-                                    \n" +
           "            -=**=. .@@@+                                    \n" +
           "           *@@@@@% +@@:                                     \n" +
           "           *@@@@@#*@*:                                      \n" +
           "            %@@@@@@@+-      "+Board.ANSI_RESET+Board.ANSI_RED+"    :#@@@#-                     \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "          -*@@@@@@@@@@*:     "+Board.ANSI_RESET+Board.ANSI_RED+"    +@@@@@#                     \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "          @@@@@@@@@@@@@@%*+-.  "+Board.ANSI_RESET+Board.ANSI_RED+"  +@@@@@=                     \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "          =@@@@@@@@@%@@==*%@"+Board.ANSI_RESET+Board.ANSI_RED+" @@%@@@@@@@@#=                   \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "           :%@@@@@@@+:@@%*  "+Board.ANSI_RESET+Board.ANSI_RED+"  ::::+@@@@@@@%.                 \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "            .@@@@@@@+.@@@@:  "+Board.ANSI_RESET+Board.ANSI_RED+"   .*%@@@@@@@@#                 \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "             #@@@@@@% .--:  "+Board.ANSI_RESET+Board.ANSI_RED+"    +@@@@@@@@@@@*                \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "             *@@@@@@@%=    "+Board.ANSI_RESET+Board.ANSI_RED+"      :-=#@@@@@@@@+               \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "            =@@@@@@@@@@@=     "+Board.ANSI_RESET+Board.ANSI_RED+"        .%@@@@@@=              \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "            @@@@@@@@@@@@@=    "+Board.ANSI_RESET+Board.ANSI_RED+"         *@@@@@@@*             \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "           =@@@@@@@@@@@@@@:  "+Board.ANSI_RESET+Board.ANSI_RED+"      .-*%@@@@@@@@@+            \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "           %@@@@@@@+@@@@%-. "+Board.ANSI_RESET+Board.ANSI_RED+"     :*@@@@@@@@@@@@@@*           \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "          +@@@%%@@#  =@@@:   "+Board.ANSI_RESET+Board.ANSI_RED+"  =%@@@@@@@#+=*@@@@@@=          \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "         -@@@:        :@@%  "+Board.ANSI_RESET+Board.ANSI_RED+"  .@@@*-..:     .=*%@@@*:        \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "       -%@@+.          *@@* "+Board.ANSI_RESET+Board.ANSI_RED+"  .@@@=             .+@@@#=      \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "      =@@#.             +@@:"+Board.ANSI_RESET+Board.ANSI_RED+"  =@@@.                -#@@%-    \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "     =@%-                =@@:"+Board.ANSI_RESET+Board.ANSI_RED+" *@@=                   :*@@*   \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "    *@@:                 :@@@"+Board.ANSI_RESET+Board.ANSI_RED+" @@@                       +@%: \n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "   +@@:                 .+@@"+Board.ANSI_RESET+Board.ANSI_RED+" @@@@                        *%%.\n" +Board.ANSI_RESET+Board.ANSI_CYAN+
           "  .%%+                  .===="+Board.ANSI_RESET+Board.ANSI_RED+" ===                            "+Board.ANSI_RESET;


   public static String fail = "                                        \n" +
           "                 .:--:.                 \n" +
           "              =#%%%%%%%%#=              \n" +
           "            .%%%%%%%%%%%%%%.            \n" +
           "            -%%%%%%%%%%%%%%-            \n" +
           "             #%+=-+%%+-=+%#             \n" +
           "             #%:..=##=:.:%#             \n" +
           "             =##%%%::%%%##=             \n" +
           "               +##%%%%#%+               \n" +
           "       .%%#=    +%%%%%%+    =#%%        \n" +
           "       -%%%%%#*=::====::=+#%%%%%-       \n" +
           "            .:-+#%#**#%#+-:.            \n" +
           "        -+--=*#%#+=::=+#%#*=--+-        \n" +
           "        #%%*=-.          .-=*%%#        \n" +
           "       =%%:                  :%%-       \n" +
           "                                        "+Board.ANSI_RESET;

//    private static final String ANSI_CYAN = "\u001B[36m";
//    private static final String ANSI_RESET = "\u001B[0m";
//    private static final String ANSI_RED = "\u001B[31m";
//    private static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        System.out.println(welcome);
        System.out.println(hero);
        System.out.println(fight);
        System.out.println(fail);
    }
}
