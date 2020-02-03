package code;

import code.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
//Создание основного класса приложения DeveloperRunner.java:
public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();//создание sessionFactory

        DeveloperRunner developerRunner = new DeveloperRunner();//создание объекта класса DeveloperRunner

        System.out.println("Добавление записей в БД");
        /**
         *  Добавление записей в БД
         */
        developerRunner.addDeveloper("Иван", "Богачев", "Java Developer", 2);
        developerRunner.addDeveloper("Катя", "Попова", "C++ Developer", 2);
        developerRunner.addDeveloper("Евгений", "Кургалин", "UI Developer", 4);

        System.out.println("Список разработчиков:");
        /**
         * Список разработчиков
         */
        List<Developer> developers = developerRunner.listDevelopers();
        for (Developer developer : developers) {
            System.out.println(developer);
        }
        System.out.println("===================================");
        System.out.println("Обновление опыта Ивана и удаление Поповой Кати разработчиков");
        /**
         *  Обновление и удаление разработчиков
         */
        developerRunner.updateDeveloper(1, 378191);
        developerRunner.removeDeveloper(2);

        System.out.println("Окончательный список разработчиков после обновления и удаления:");//вывод на экран добавленных разработчиков
        /**
         *  Окончательный список разработчиков после обновления и удаления
         */
        developers = developerRunner.listDevelopers();
        for (Developer developer : developers) {
            System.out.println(developer);
        }
        System.out.println("===================================");

    }

    public void addDeveloper(String firstName, String lastName, String specialty, int experience) {//метод добавления разработчиков
        Session session = sessionFactory.openSession();//создание сессии
        Transaction transaction = null;//создание ссылки на транзакцию

        transaction = session.beginTransaction();//начало транзакции
        Developer developer = new Developer(firstName, lastName, specialty, experience);//создание объекта класса Developer
        session.save(developer);//вставляем запись в БД
        transaction.commit();//Транзакция Transaction включает одно или несколько изменений в базе данных, которые после выполнения фиксируются commit()
        session.close();//закрытие сессии
    }

    public List<Developer> listDevelopers() {//создание списка разработчиков
        Session session = this.sessionFactory.openSession();//создание сессии
        Transaction transaction = null;//создание ссылки на транзакцию

        transaction = session.beginTransaction();//начало транзакции
        List<Developer> developers = session.createQuery("FROM Developer").list();//Если мы хотим загрузить в память наши сохраняемые объекты, то мы будем использовать
//ключевое слово FROM далее получаем список выбранных сущностей (это HQL-запрос)
        transaction.commit();//фиксируем изменения в БД
        session.close();//закрытие сессии
        return developers;//возвращаем список developers
    }

    public void updateDeveloper(int developerId, int experience) {//метод обновления информации о разработчиках
        Session session = this.sessionFactory.openSession();//создание сессии
        Transaction transaction = null;//создание ссылки на транзакцию

        transaction = session.beginTransaction();//начало транзакции
        Developer developer = (Developer) session.get(Developer.class, developerId);
        developer.setExperience(experience);//устанавливаем новое значение опыта
        session.update(developer);//обновляем данные
        transaction.commit();//фиксируем изменения в БД
        session.close();//закрытие сессии
    }

    public void removeDeveloper(int developerId) {//метод удаления информации о разработчиках
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        session.delete(developer);//удаляем данные
        transaction.commit();//фиксируем изменения в БД
        session.close();//закрытие сессии
    }

}

