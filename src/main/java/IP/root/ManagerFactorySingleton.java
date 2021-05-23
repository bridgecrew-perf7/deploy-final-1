package IP.root;

import javax.persistence.*;

public class ManagerFactorySingleton {
    private static EntityManagerFactory fact = null;
    private static String name;

    public static EntityManagerFactory getFactory() {
        if (fact == null) {
            fact = Persistence.createEntityManagerFactory(name);
        }

        return fact;
    }

    public static void setName(String name) {
        ManagerFactorySingleton.name = name;
    }

    public static void closeFact() {
        fact.close();
    }
}
