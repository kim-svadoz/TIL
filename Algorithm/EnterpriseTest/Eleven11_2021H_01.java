import java.util.*;

public class Eleven11_2021H_01 {
    public static void main(String[] args) {

    }

    static int n;
    public static String solution(String[] phone_numbers, String[] phone_owners, String number) {
        n = phone_numbers.length;
        HashMap<String, String> phonebook = new HashMap<>();

        for (int i = 0; i < n; i++) {
            phonebook.put(phone_numbers[i], phone_owners[i]);
        }

        if (phonebook.containsKey(number)) {
            return phonebook.get(number);
        }
        return number;
    }
}
