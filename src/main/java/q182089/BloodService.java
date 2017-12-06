package q182089;

import java.util.*;

class BloodService {
    private static HashMap<String, String> COMPATIBILITY = new LinkedHashMap<>();
    static {
        COMPATIBILITY.put("O-", "O-");
        COMPATIBILITY.put("O+", "O+, O-");
        COMPATIBILITY.put("A-", "O-, A-");
        COMPATIBILITY.put("A+", "O-, O+, A-, A+");
        COMPATIBILITY.put("B-", "O-, B- ");
        COMPATIBILITY.put("B+", "O-, O+, B-, B+");
        COMPATIBILITY.put("AB-", "O-, A-, B-, AB-");
        COMPATIBILITY.put("AB+", "O-, O+, A-, A+, B-, B+, AB-, AB+");
    }

    public List<String> getAllTypes() {
        return Collections.unmodifiableList(new ArrayList<>(COMPATIBILITY.keySet()));
    }

    public String getCompatibility(String type) {
        return COMPATIBILITY.get(type);
    }
}
