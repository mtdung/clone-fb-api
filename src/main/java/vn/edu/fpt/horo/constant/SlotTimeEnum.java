package vn.edu.fpt.horo.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum SlotTimeEnum {
    SLOT_1(8, 9,1),
    SLOT_2(9, 10,2),
    SLOT_3(10, 11,3),
    SLOT_4(11, 12,4),
    SLOT_5(12, 13,5),
    SLOT_6(13, 14,6),
    SLOT_7(14, 15,7),
    SLOT_8(15, 16,8),
    SLOT_9(16, 17,9),
    SLOT_10(18, 19,10),
    SLOT_11(20, 21,11),
    SLOT_12(21, 22,12);

    private static final TreeMap<Integer, SlotTimeEnum> BY_SLOT = new TreeMap<>();
    static {
        for (SlotTimeEnum slot : values()) {
            BY_SLOT.put(slot.slot, slot);
        }
    }

    private static final TreeMap<Integer, SlotTimeEnum> BY_START_HOUR = new TreeMap<>();
    static {
        for (SlotTimeEnum slot : values()) {
            BY_START_HOUR.put(slot.startTime, slot);
        }
    }

    public static int getSlotsBefore(int checkTime) {
        int previous = 0;
        for (Map.Entry<Integer, SlotTimeEnum> slot : BY_START_HOUR.entrySet()) {
            if (slot.getValue().startTime <= checkTime) {
                previous = slot.getValue().slot;
            }
        }
        return previous;
    }

    private static final Map<Integer, SlotTimeEnum> BY_END_HOUR = new HashMap<>();
    static {
        for (SlotTimeEnum slot : values()) {
            BY_END_HOUR.put(slot.endTime, slot);
        }
    }

    private final int startTime;
    private final int endTime;
    private final int slot;

    SlotTimeEnum(int hour, int endTime, int slot) {
        this.startTime = hour;
        this.endTime = endTime;
        this.slot = slot;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getSlot() {
        return slot;
    }

    public static SlotTimeEnum getByStartHour(int hour) {
        return BY_START_HOUR.get(hour);
    }

    public static SlotTimeEnum getByEndHour(int hour) {
        return BY_END_HOUR.get(hour);
    }

    public static SlotTimeEnum getBySlot(int slot) {
        return BY_SLOT.get(slot);
    }

    public static int countSlot(){
        return BY_SLOT.size();
    }
}
