package service;

import container.ComponentRepository;
import dto.Card;
import dto.Terminal;
import enums.EnumStatus;

import java.time.LocalDate;
import java.util.List;

public class TerminalService {
    public void addTerminal(Terminal terminal) {
        Terminal exist = ComponentRepository.terminalRepository.getTerminal(terminal.getCode());
        if (exist != null) {
            System.out.println(" Terminal exist: ");
        }
        terminal.setStatus(EnumStatus.ACTIVE.name());
        terminal.setCreated_date(LocalDate.now());
        terminal.setVisible(true);
        ComponentRepository.terminalRepository.createTerminal(terminal);
    }

    public void getListTerminal() {
        List<Terminal> terminalList = ComponentRepository.terminalRepository.getListAll();
        for (Terminal terminal : terminalList) {
            System.out.println(terminal.toString());
        }
    }
    public boolean updateAddress(String code, String address) {
        Terminal terminal = ComponentRepository.terminalRepository.getTerminalCode(code);
        if (terminal.getCode() == null) {
            return false;
        }
        if (terminal.getCode().equals(code)) {
            ComponentRepository.terminalRepository.updateTerminalAddress(code, address);
        }
        return true;
    }

    public boolean updateStatusTerminal(String code) {
        Terminal terminal = ComponentRepository.terminalRepository.getTerminalCode(code);
        if (terminal.getCode() == null) {
            return false;
        }
        if (terminal.getStatus().equals(EnumStatus.ACTIVE.name())) {
            ComponentRepository.terminalRepository.updateTerminalStatus(EnumStatus.BLOCE.name(),code);
            return true;
        } else if (terminal.getStatus().equals(EnumStatus.BLOCE.name())) {
            ComponentRepository.terminalRepository.updateTerminalStatus(EnumStatus.ACTIVE.name(), code);
        }
        return true;
    }

    public boolean deleteTerminal(String code) {
        Terminal terminal = ComponentRepository.terminalRepository.getTerminalCode(code);
        if (terminal != null){
            ComponentRepository.terminalRepository.removeTerminalByQuery(code);
            return true;
        }
        return false;
    }
    //    public boolean updateCode(String code, String code1) {
//        Terminal terminal = ComponentRepository.terminalRepository.getTerminalCode(code);
//        if (terminal == null) {
//            return false;
//        }
//            ComponentRepository.terminalRepository.updateTerminalCode(code, code1);
//        return true;
//    }
}
