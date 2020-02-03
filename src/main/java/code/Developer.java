package code;

public class Developer {
    //Все поля POJO – классов должны иметь модификатор доступа private:
    private int id;//Все классы должны иметь ID для простой идентификации наших объектов в БД и в Hibernate.Это поле
    // класса соединяется с первичным ключём (primary key) таблицы БД.
    private String firstName;
    private String lastName;
    private String specialty;
    private int experience;

    /**
     * Все POJO – классы должны иметь конструктор по умолчанию (пустой)
     */
    public Developer() {
    }

    /**
     * обычный конструктор
     */
    public Developer(String firstName, String lastName, String specialty, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.experience = experience;
    }


    /**
     * Getters и Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Переопределяем метод toString()
     */
    @Override
    public String toString() {
        return "Developer:\n" +
                "id: " + id +
                "\nFirst Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Specialty: " + specialty + "\n" +
                "Experience: " + experience + "\n";
    }
}
/*
Создание таблицы в БД:

CREATE TABLE HIBERNATE_DEVELOPERS(
   ID INT NOT NULL AUTO_INCREMENT,
   FIRST_NAME VARCHAR(50) DEFAULT NULL,
   LAST_NAME VARCHAR(50) DEFAULT NULL,
   SPECIALTY VARCHAR(50) DEFAULT NULL,
   EXPERIENCE INT DEFAULT NULL,
   PRIMARY KEY(ID)
);
*/
