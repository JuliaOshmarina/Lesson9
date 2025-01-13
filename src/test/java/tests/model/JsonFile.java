package tests.model;

import java.util.List;

public class JsonFile {
    private String name;
    private String age;
    private String secretIdentity;
    private List<String> powers;


    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public List<String> getPowers() {
        return powers;
    }
}
