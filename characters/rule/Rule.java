package characters.rule;

import characters.Actor;
import characters.floor.Floor;

public interface Rule {
    // 設定此地板基本參數
    void setFloorState(Floor floor);
    // 設定 要對player做的事情
    void execute(Floor floor, Actor player);
}
