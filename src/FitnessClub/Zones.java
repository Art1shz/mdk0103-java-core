package FitnessClub;

import java.time.LocalTime;

public class Zones {
    public static boolean canAccessZone(String zone, String abonementType) {
        return switch (zone) {
            case "тренажерный зал" -> abonementType.equals("Полный") || abonementType.equals("Разовый") || abonementType.equals("Дневной");
            case "бассейн" -> abonementType.equals("Полный");
            case "групповые занятия" -> abonementType.equals("Полный") || abonementType.equals("Дневной");
            default -> false;
        };
    }

    public static boolean canAccessZoneByTimeAndType(String abonementType, String zone, LocalTime currentTime) {
        switch (abonementType) {
            case "Разовый":
                if (zone.equals("тренажерный зал") || zone.equals("бассейн")) {
                    return currentTime.isAfter(LocalTime.of(8, 0)) && currentTime.isBefore(LocalTime.of(22, 0));
                }
                return false;
            case "Дневной":
                if (zone.equals("тренажерный зал") || zone.equals("групповые занятия")) {
                    return currentTime.isAfter(LocalTime.of(8, 0)) && currentTime.isBefore(LocalTime.of(16, 0));
                }
                return false;
            case "Полный":
                return currentTime.isAfter(LocalTime.of(8, 0)) && currentTime.isBefore(LocalTime.of(22, 0));
            default:
                return false;
        }
    }
}
