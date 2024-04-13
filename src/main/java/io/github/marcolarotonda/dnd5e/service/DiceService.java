package io.github.marcolarotonda.dnd5e.service;
import io.github.marcolarotonda.dicerollerclient.service.DiceRollerService;
import io.github.marcolarotonda.dicerollerutil.enumeration.DiceType;
import io.github.marcolarotonda.dicerollerutil.model.RollOption;
import io.github.marcolarotonda.dicerollerutil.model.RollResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiceService {

    private final DiceRollerService client;

    @Autowired
    public DiceService(DiceRollerService client) {
        this.client = client;
    }

    public RollResult roll(RollOption rollOption) {
        setDefaultDice(rollOption);
        return client.roll(rollOption);
    }

    public int rollGetTotal(RollOption rollOption) {
        return roll(rollOption).getTotal();
    }

    private void setDefaultDice(RollOption rollOption) {
        if (rollOption.getDiceType() == null) {
            rollOption.setDiceType(DiceType.D20);
        }
    }



}
