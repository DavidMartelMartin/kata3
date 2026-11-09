package software.ulpgc.katas;

public class UserDto {
    public String gender;
    public Name name;
    public String email;
    public Dob dob;
    public Picture picture;

    public static class Name {
        public String first;
        public String last;
        public String title;
    }

    public static class Dob {
        public String date;
        public int age;
    }
    public static class Picture {
        public String large;
    }
}