package container;

import repository.CardRepository;
import repository.ProfileRepository;
import repository.TerminalRepository;
import repository.TransactionRepository;

public class ComponentRepository {
    public static CardRepository cardRepository = new CardRepository();
    public static ProfileRepository profileRepository = new ProfileRepository();
    public static TerminalRepository terminalRepository = new TerminalRepository();
    public static TransactionRepository transactionRepository = new TransactionRepository();
}
